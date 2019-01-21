package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.AirConditioner;
import com.satouhikaru.service.impl.AirConditionerServiceImpl;
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
public class AirConditionerController {

	@Autowired
	private AirConditionerServiceImpl airConditionerServiceImpl;

	/**
	 * Count list of air conditioners quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/airConditioners/countAll")
	public long countAll() {
		return airConditionerServiceImpl.countAll();
	}

	/**
	 * Count list of air conditioners quantity by filter
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
	@GetMapping("/airConditioners/countFilter")
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
			result = airConditionerServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of air conditioners per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/airConditioners/page/{page}")
	public List<AirConditioner> getPerPage(@PathVariable("page") long page) {
		List<AirConditioner> airConditionerList = airConditionerServiceImpl.getPerPage(Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (AirConditioner airConditioner : airConditionerList) {
			airConditioner.setImage(Constant.AIR_CONDITIONER_PATH + airConditioner.getImage());
		}
		
		return airConditionerList;
	}

	/**
	 * Get list of air conditioners by brand
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
	@GetMapping("/airConditioners/filter/{page}")
	public List<AirConditioner> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<AirConditioner> airConditionerList = null;
		if (!(ks == null && br == null && pf == null)) {
			airConditionerList = airConditionerServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (AirConditioner airConditioner : airConditionerList) {
				airConditioner.setImage(Constant.WASHER_PATH + airConditioner.getImage());
			}
		}

		return airConditionerList;
	}

	/**
	 * Get air conditioner by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name air conditioner name
	 * @return AirConditioner
	 */
	@GetMapping("/airConditioners/{name}")
	public AirConditioner getByName(@PathVariable("name") String name) {
		AirConditioner airConditioner = airConditionerServiceImpl.getByName(name);
		airConditioner.setImage(Constant.AIR_CONDITIONER_PATH + airConditioner.getImage());
		return airConditioner;
	}

	/**
	 * Add new air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param airConditionerParam new air conditioner
	 * @param fileUpload image file
	 */
	@PostMapping("/airConditioners")
	public void add(@RequestParam("airConditioner") String airConditionerParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			AirConditioner airConditioner = new ObjectMapper().readValue(airConditionerParam, AirConditioner.class);
			airConditioner.setImage(CommonUtil.getImageName(airConditioner.getImage()));
			airConditionerServiceImpl.add(airConditioner);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.AIR_CONDITIONER_PATH, airConditioner.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id air conditioner ID
	 * @param airConditionerParam air conditioner need updating
	 * @param fileUpload
	 */
	@PostMapping("/airConditioners/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("airConditioner") String airConditionerParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			AirConditioner ac = airConditionerServiceImpl.getById(id);
			if (ac == null) {
				return;
			}
			
			AirConditioner airConditioner = new ObjectMapper().readValue(airConditionerParam, AirConditioner.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				airConditioner.setImage(CommonUtil.getImageName(airConditioner.getImage()));
				if (!airConditioner.getImage().equals(ac.getImage())) {
					imageChanged = true;
				}
			}
			
			airConditionerServiceImpl.update(airConditioner);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.AIR_CONDITIONER_PATH, ac.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.AIR_CONDITIONER_PATH, airConditioner.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id air conditioner ID
	 */
	@DeleteMapping("/airConditioners/{id}")
	public void delete(@PathVariable("id") long id) {
		AirConditioner airConditioner = airConditionerServiceImpl.getById(id);
		if (airConditioner == null) {
			return;
		}
		
		airConditionerServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.AIR_CONDITIONER_PATH, airConditioner.getImage());
	}
	
}