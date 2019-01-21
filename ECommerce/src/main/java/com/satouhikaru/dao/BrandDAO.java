package com.satouhikaru.dao;

import com.satouhikaru.entity.Brand;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface BrandDAO {

	/**
	 * Get list of brands
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @return List
	 */
	List<Brand> getAll();
	
	/**
	 * Get product list of brands
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param productType
	 * @return List
	 */
	List<Brand> getFilter(String productType);
	
}