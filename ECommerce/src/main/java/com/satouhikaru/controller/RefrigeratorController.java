package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.Refrigerator;
import com.satouhikaru.service.impl.RefrigeratorServiceImpl;
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
public class RefrigeratorController {

	@Autowired
	private RefrigeratorServiceImpl refrigeratorServiceImpl;

	/**
	 * Count list of refrigerators quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/refrigerators/countAll")
	public long countAll() {
		return refrigeratorServiceImpl.countAll();
	}

	/**
	 * Count list of refrigerators quantity by filter
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
	@GetMapping("/refrigerators/countFilter")
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
			result = refrigeratorServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of refrigerators per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/refrigerators/page/{page}")
	public List<Refrigerator> getPerPage(@PathVariable("page") long page) {
		List<Refrigerator> refrigeratorList = refrigeratorServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Refrigerator refrigerator : refrigeratorList) {
			refrigerator.setImage(Constant.REFRIGERATOR_PATH + refrigerator.getImage());
		}
		
		return refrigeratorList;
	}

	/**
	 * Get list of refrigerators by brand
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
	@GetMapping("/refrigerators/filter/{page}")
	public List<Refrigerator> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<Refrigerator> refrigeratorList = null;
		if (!(ks == null && br == null && pf == null)) {
			refrigeratorList = refrigeratorServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (Refrigerator refrigerator : refrigeratorList) {
				refrigerator.setImage(Constant.REFRIGERATOR_PATH + refrigerator.getImage());
			}
		}
		
		return refrigeratorList;
	}

	/**
	 * Get refrigerator by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name refrigerator name
	 * @return Refrigerator
	 */
	@GetMapping("/refrigerators/{name}")
	public Refrigerator getByName(@PathVariable("name") String name) {
		Refrigerator refrigerator = refrigeratorServiceImpl.getByName(name);
		refrigerator.setImage(Constant.REFRIGERATOR_PATH + refrigerator.getImage());
		return refrigerator;
	}

	/**
	 * Add new refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param refrigeratorParam new refrigerator
	 * @param fileUpload image file
	 */
	@PostMapping("/refrigerators")
	public void add(@RequestParam("refrigerator") String refrigeratorParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			Refrigerator refrigerator = new ObjectMapper().readValue(refrigeratorParam, Refrigerator.class);
			refrigerator.setImage(CommonUtil.getImageName(refrigerator.getImage()));
			refrigeratorServiceImpl.add(refrigerator);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.REFRIGERATOR_PATH, refrigerator.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id refrigerator ID
	 * @param refrigeratorParam refrigerator need updating
	 * @param fileUpload
	 */
	@PostMapping("/refrigerators/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("refrigerator") String refrigeratorParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			Refrigerator r = refrigeratorServiceImpl.getById(id);
			if (r == null) {
				return;
			}
			
			Refrigerator refrigerator = new ObjectMapper().readValue(refrigeratorParam, Refrigerator.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				refrigerator.setImage(CommonUtil.getImageName(refrigerator.getImage()));
				if (!refrigerator.getImage().equals(r.getImage())) {
					imageChanged = true;
				}
			}
			
			refrigeratorServiceImpl.update(refrigerator);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.REFRIGERATOR_PATH, r.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.REFRIGERATOR_PATH, refrigerator.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id refrigerator ID
	 */
	@DeleteMapping("/refrigerators/{id}")
	public void delete(@PathVariable("id") long id) {
		Refrigerator refrigerator = refrigeratorServiceImpl.getById(id);
		if (refrigerator == null) {
			return;
		}
		
		refrigeratorServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.REFRIGERATOR_PATH, refrigerator.getImage());
	}
	
}