package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.Washer;
import com.satouhikaru.service.impl.WasherServiceImpl;
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
public class WasherController {

	@Autowired
	private WasherServiceImpl washerServiceImpl;

	/**
	 * Count list of washers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/washers/countAll")
	public long countAll() {
		return washerServiceImpl.countAll();
	}

	/**
	 * Count list of washers quantity by filter
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
	@GetMapping("/washers/countFilter")
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
			result = washerServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of washers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/washers/page/{page}")
	public List<Washer> getPerPage(@PathVariable("page") long page) {
		List<Washer> washerList = washerServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Washer washer : washerList) {
			washer.setImage(Constant.WASHER_PATH + washer.getImage());
		}
		
		return washerList;
	}

	/**
	 * Get list of washers by brand
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
	@GetMapping("/washers/filter/{page}")
	public List<Washer> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<Washer> washerList = null;
		if (!(ks == null && br == null && pf == null)) {
			washerList = washerServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (Washer washer : washerList) {
				washer.setImage(Constant.WASHER_PATH + washer.getImage());
			}
		}
		
		return washerList;
	}

	/**
	 * Get washer by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name washer name
	 * @return Washer
	 */
	@GetMapping("/washers/{name}")
	public Washer getByName(@PathVariable("name") String name) {
		Washer washer = washerServiceImpl.getByName(name);
		washer.setImage(Constant.WASHER_PATH + washer.getImage());
		return washer;
	}

	/**
	 * Add new washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param washerParam new washer
	 * @param fileUpload image file
	 */
	@PostMapping("/washers")
	public void add(@RequestParam("washer") String washerParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			Washer washer = new ObjectMapper().readValue(washerParam, Washer.class);
			washer.setImage(CommonUtil.getImageName(washer.getImage()));
			washerServiceImpl.add(washer);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.WASHER_PATH, washer.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id washer ID
	 * @param washerParam washer need updating
	 * @param fileUpload
	 */
	@PostMapping("/washers/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("washer") String washerParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			Washer w = washerServiceImpl.getById(id);
			if (w == null) {
				return;
			}
			
			Washer washer = new ObjectMapper().readValue(washerParam, Washer.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				washer.setImage(CommonUtil.getImageName(washer.getImage()));
				if (!washer.getImage().equals(w.getImage())) {
					imageChanged = true;
				}
			}
			
			washerServiceImpl.update(washer);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.WASHER_PATH, w.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.WASHER_PATH, washer.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id washer ID
	 */
	@DeleteMapping("/washers/{id}")
	public void delete(@PathVariable("id") long id) {
		Washer washer = washerServiceImpl.getById(id);
		if (washer == null) {
			return;
		}
		
		washerServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.WASHER_PATH, washer.getImage());
	}
	
}