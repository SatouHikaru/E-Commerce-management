package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.Account;
import com.satouhikaru.service.impl.AccountServiceImpl;
import com.satouhikaru.utility.CommonUtil;
import com.satouhikaru.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class AccountController {

	@Autowired
	private AccountServiceImpl accountServiceImpl;

	/**
	 * Login
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param accountParam
	 * @return Account
	 */
	@PostMapping("/login")
	public Account login(@RequestBody Account accountParam) {
		accountParam.setPassword(CommonUtil.md5(accountParam.getPassword()));
		Account account = accountServiceImpl.login(accountParam);

		if (account != null) {
			if (account.getAvatar().equals("avatar.jpg")) {
				account.setAvatar("assets\\images\\" + account.getAvatar());
			} else {
				account.setAvatar(Constant.AVATAR_PATH + account.getAvatar());
			}
		}

		return account;
	}

	/**
	 * Check if account is exists
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param userName
	 * @return int
	 */
	@GetMapping("/accounts/count")
	public int count(@RequestParam("userName") String userName) {
		return accountServiceImpl.count(userName);
	}

	/**
	 * Get account by user name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName
	 * @return Account
	 */
	@GetMapping("/accounts/{username}")
	public Account getByUserName(@PathVariable("username") String userName) {
		Account account = accountServiceImpl.getByUserName(userName);
		if (account != null) {
			if (account.getAvatar().equals("avatar.jpg")) {
				account.setAvatar("assets\\images\\" + account.getAvatar());
			} else {
				account.setAvatar(Constant.AVATAR_PATH + account.getAvatar());
			}
		}

		return account;
	}

	/**
	 * Add new account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param account new account
	 */
	@PostMapping("/accounts")
	public void add(@RequestBody Account account) {
		account.setPassword(CommonUtil.md5(account.getPassword()));
		accountServiceImpl.add(account);
	}

	/**
	 * Update account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param accountParam account need updating
	 * @param fileUpload image file
	 */
	@PostMapping("/accounts/change")
	public void update(@RequestParam("account") String accountParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			Account account = new ObjectMapper().readValue(accountParam, Account.class);
			Account acc = accountServiceImpl.getByUserName(account.getUserName());
			if (acc == null) {
				return;
			}

			boolean imageChanged = false;
			if (fileUpload != null) {
				account.setAvatar(CommonUtil.getImageName(account.getAvatar()));
				if (!account.getAvatar().equals(acc.getAvatar())) {
					imageChanged = true;
				}
			}

			accountServiceImpl.update(account);

			if (!imageChanged) {
				return;
			}

			// Delete old image file
			CommonUtil.deleteImageFile(Constant.AVATAR_PATH, acc.getAvatar());

			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.AVATAR_PATH, account.getAvatar(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName
	 */
	@DeleteMapping("/accounts/{username}")
	public void delete(@PathVariable("username") String userName) {
		Account account = accountServiceImpl.getByUserName(userName);
		if (account == null) {
			return;
		}
		
		accountServiceImpl.delete(userName);
		CommonUtil.deleteImageFile(Constant.AVATAR_PATH, account.getAvatar());
	}
	
}