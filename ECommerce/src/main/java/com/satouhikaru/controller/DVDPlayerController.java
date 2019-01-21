package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.DVDPlayer;
import com.satouhikaru.service.impl.DVDPlayerServiceImpl;
import com.satouhikaru.utility.CommonUtil;
import com.satouhikaru.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class DVDPlayerController {

	@Autowired
	private DVDPlayerServiceImpl dvdPlayerServiceImpl;

	/**
	 * Count list of DVD players quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/dvdPlayers/countAll")
	public long countAll() {
		return dvdPlayerServiceImpl.countAll();
	}

	/**
	 * Count list of DVD players quantity by filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @return long
	 */
	@GetMapping("/dvdPlayers/countFilter")
	public long countByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		long result = 0;
		if (!(ks == null && br == null && pf == null)) {
			result = dvdPlayerServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of DVD players per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/dvdPlayers/page/{page}")
	public List<DVDPlayer> getPerPage(@PathVariable("page") long page) {
		List<DVDPlayer> dvdPlayerList = dvdPlayerServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (DVDPlayer dvdPlayer : dvdPlayerList) {
			dvdPlayer.setImage(Constant.DVD_PLAYER_PATH + dvdPlayer.getImage());
		}
		
		return dvdPlayerList;
	}

	/**
	 * Get list of DVD players by brand
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @param page
	 * @return List
	 */
	@GetMapping("/dvdPlayers/filter/{page}")
	public List<DVDPlayer> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<DVDPlayer> dvdPlayerList = null;
		if (!(ks == null && br == null && pf == null)) {
			dvdPlayerList = dvdPlayerServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (DVDPlayer dvdPlayer : dvdPlayerList) {
				dvdPlayer.setImage(Constant.DVD_PLAYER_PATH + dvdPlayer.getImage());
			}
		}
		
		return dvdPlayerList;
	}

	/**
	 * Get DVD player by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name DVD player name
	 * @return DVDPlayer
	 */
	@GetMapping("/dvdPlayers/{name}")
	public DVDPlayer getByName(@PathVariable("name") String name) {
		DVDPlayer dvdPlayer = dvdPlayerServiceImpl.getByName(name);
		dvdPlayer.setImage(Constant.DVD_PLAYER_PATH + dvdPlayer.getImage());
		return dvdPlayer;
	}

	/**
	 * Add new DVD player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param dvdPlayerParam new DVD player
	 * @param fileUpload image file
	 */
	@PostMapping("/dvdPlayers")
	public void add(@RequestParam("dvdPlayerParam") String dvdPlayerParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			DVDPlayer dvdPlayer = new ObjectMapper().readValue(dvdPlayerParam, DVDPlayer.class);
			dvdPlayer.setImage(CommonUtil.getImageName(dvdPlayer.getImage()));
			dvdPlayerServiceImpl.add(dvdPlayer);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.DVD_PLAYER_PATH, dvdPlayer.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update DVD player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id DVD player ID
	 * @param dvdPlayerParam DVD player need updating
	 * @param fileUpload
	 */
	@PostMapping("/dvdPlayers/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("dvdPlayer") String dvdPlayerParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			DVDPlayer dvdP = dvdPlayerServiceImpl.getById(id);
			if (dvdP == null) {
				return;
			}
			
			DVDPlayer dvdPlayer = new ObjectMapper().readValue(dvdPlayerParam, DVDPlayer.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				dvdPlayer.setImage(CommonUtil.getImageName(dvdPlayer.getImage()));
				if (!dvdPlayer.getImage().equals(dvdP.getImage())) {
					imageChanged = true;
				}
			}
			
			dvdPlayerServiceImpl.update(dvdPlayer);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.DVD_PLAYER_PATH, dvdP.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.DVD_PLAYER_PATH, dvdPlayer.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete DVD player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id DVD player ID
	 */
	@DeleteMapping("/dvdPlayers/{id}")
	public void delete(@PathVariable("id") long id) {
		DVDPlayer dvdPlayer = dvdPlayerServiceImpl.getById(id);
		if (dvdPlayer == null) {
			return;
		}
		
		dvdPlayerServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.DVD_PLAYER_PATH, dvdPlayer.getImage());
	}
	
}