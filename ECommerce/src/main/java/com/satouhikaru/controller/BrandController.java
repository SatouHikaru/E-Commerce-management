package com.satouhikaru.controller;

import com.satouhikaru.entity.Brand;
import com.satouhikaru.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class BrandController {

	@Autowired
	private BrandServiceImpl brandServiceImpl;

	/**
	 * Get list of brands
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @return List
	 */
	@GetMapping("/brands")
	public List<Brand> getAll() {
		return brandServiceImpl.getAll();
	}

	/**
	 * Get product list of brands
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param productType
	 * @return List
	 */
	@GetMapping("/brands/filter")
	public List<Brand> getFilter(@RequestParam("productType") String productType) {
		return brandServiceImpl.getFilter(productType);
	}
	
}