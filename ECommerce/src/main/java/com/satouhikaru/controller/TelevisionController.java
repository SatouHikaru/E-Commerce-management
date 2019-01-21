package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.Television;
import com.satouhikaru.service.impl.TelevisionServiceImpl;
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
@CrossOrigin("cors.allowed.origins")
@RestController
public class TelevisionController {

	@Autowired
	private TelevisionServiceImpl televisionServiceImpl;

	/**
	 * Count list of televisions quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/televisions/countAll")
	public long countAll() {
		return televisionServiceImpl.countAll();
	}

	/**
	 * Count list of televisions quantity by filter
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
	@GetMapping("/televisions/countFilter")
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
            result = televisionServiceImpl.countByFilter(ks, br, pf, pt);
        }

		return result;
	}

	/**
	 * Count list of televisions quantity by key search
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param name
	 * @return long
	 */
	@GetMapping("/televisions/count/{keySearch}")
	public long count(@PathVariable("keySearch") String name) {
		return televisionServiceImpl.count(name);
	}

	/**
	 * Get list of televisions per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/televisions/page/{page}")
	public List<Television> getPerPage(@PathVariable("page") long page) {
		List<Television> televisionList = televisionServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Television television : televisionList) {
			television.setImage(Constant.TELEVISION_PATH + television.getImage());
		}
		
		return televisionList;
	}

	/**
	 * Get list of televisions by brand
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
	@GetMapping("/televisions/filter/{page}")
	public List<Television> getByFilter(@RequestParam("keySearch") String keySearch,
            @RequestParam("brands") String brands,
            @RequestParam("priceFrom") double priceFrom,
            @RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
	    String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

        List<Television> televisionList = null;
        if (!(ks == null && br == null && pf == null)) {
            televisionList = televisionServiceImpl.getByFilter(ks, br, pf, pt,
                    Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
            for (Television television : televisionList) {
                television.setImage(Constant.TELEVISION_PATH + television.getImage());
            }
        }

		return televisionList;
	}

	/**
	 * Get television by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name television name
	 * @return Television
	 */
	@GetMapping("/televisions/{name}")
	public Television getByName(@PathVariable("name") String name) {
		Television television = televisionServiceImpl.getByName(name);
		television.setImage(Constant.TELEVISION_PATH + television.getImage());
		return television;
	}

	/**
	 * Add new television
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param televisionParam new television
	 * @param fileUpload image file
	 */
	@PostMapping("/televisions")
	public void add(@RequestParam("television") String televisionParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			Television television = new ObjectMapper().readValue(televisionParam, Television.class);
			television.setImage(CommonUtil.getImageName(television.getImage()));
			televisionServiceImpl.add(television);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.TELEVISION_PATH, television.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update television
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id television ID
	 * @param televisionParam television need updating
	 * @param fileUpload image file
	 */
	@PostMapping("/televisions/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("television") String televisionParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			Television tv = televisionServiceImpl.getById(id);
			if (tv == null) {
				return;
			}
			
			Television television = new ObjectMapper().readValue(televisionParam, Television.class);

			boolean imageChanged = false;
			if (fileUpload != null) {
				television.setImage(CommonUtil.getImageName(television.getImage()));
				if (!television.getImage().equals(tv.getImage())) {
					imageChanged = true;
				}
			}
			
			televisionServiceImpl.update(television);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.TELEVISION_PATH, tv.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.TELEVISION_PATH, television.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete television
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id television ID
	 */
	@DeleteMapping("/televisions/{id}")
	public void delete(@PathVariable("id") long id) {
		Television television = televisionServiceImpl.getById(id);
		if (television == null) {
			return;
		}
		
		televisionServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.TELEVISION_PATH, television.getImage());
	}
	
}