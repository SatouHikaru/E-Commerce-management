package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.RiceCooker;
import com.satouhikaru.service.impl.RiceCookerServiceImpl;
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
public class RiceCookerController {

	@Autowired
	private RiceCookerServiceImpl riceCookerServiceImpl;

	/**
	 * Count list of rice cookers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/riceCookers/countAll")
	public long countAll() {
		return riceCookerServiceImpl.countAll();
	}

	/**
	 * Count list of rice cookers quantity by filter
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
	@GetMapping("/riceCookers/countFilter")
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
			result = riceCookerServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of rice cookers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/riceCookers/page/{page}")
	public List<RiceCooker> getPerPage(@PathVariable("page") long page) {
		List<RiceCooker> riceCookerList = riceCookerServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (RiceCooker riceCooker : riceCookerList) {
			riceCooker.setImage(Constant.RICE_COOKER_PATH + riceCooker.getImage());
		}
		
		return riceCookerList;
	}

	/**
	 * Get list ofrice cookers by brand
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
	@GetMapping("/riceCookers/filter/{page}")
	public List<RiceCooker> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<RiceCooker> riceCookerList = null;
		if (!(ks == null && br == null && pf == null)) {
			riceCookerList = riceCookerServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (RiceCooker riceCooker : riceCookerList) {
				riceCooker.setImage(Constant.RICE_COOKER_PATH + riceCooker.getImage());
			}
		}
		
		return riceCookerList;
	}

	/**
	 * Get rice cooker by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name rice cooker name
	 * @return RiceCooker
	 */
	@GetMapping("/riceCookers/{name}")
	public RiceCooker getByName(@PathVariable("name") String name) {
		RiceCooker riceCooker = riceCookerServiceImpl.getByName(name);
		riceCooker.setImage(Constant.RICE_COOKER_PATH + riceCooker.getImage());
		return riceCooker;
	}

	/**
	 * Add new rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param riceCookerParam new rice cooker
	 * @param fileUpload image file
	 */
	@PostMapping("/riceCookers")
	public void add(@RequestParam("riceCooker") String riceCookerParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			RiceCooker riceCooker = new ObjectMapper().readValue(riceCookerParam, RiceCooker.class);
			riceCooker.setImage(CommonUtil.getImageName(riceCooker.getImage()));
			riceCookerServiceImpl.add(riceCooker);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.RICE_COOKER_PATH, riceCooker.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id rice cooker ID
	 * @param riceCookerParam rice cooker need updating
	 * @param fileUpload
	 */
	@PostMapping("/riceCookers/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("riceCooker") String riceCookerParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			RiceCooker rc = riceCookerServiceImpl.getById(id);
			if (rc == null) {
				return;
			}
			
			RiceCooker riceCooker = new ObjectMapper().readValue(riceCookerParam, RiceCooker.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				riceCooker.setImage(CommonUtil.getImageName(riceCooker.getImage()));
				if (!riceCooker.getImage().equals(rc.getImage())) {
					imageChanged = true;
				}
			}
			
			riceCookerServiceImpl.update(riceCooker);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.RICE_COOKER_PATH, rc.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.RICE_COOKER_PATH, riceCooker.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id rice cooker ID
	 */
	@DeleteMapping("/riceCookers/{id}")
	public void delete(@PathVariable("id") long id) {
		RiceCooker riceCooker = riceCookerServiceImpl.getById(id);
		if (riceCooker == null) {
			return;
		}
		
		riceCookerServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.RICE_COOKER_PATH, riceCooker.getImage());
	}
	
}