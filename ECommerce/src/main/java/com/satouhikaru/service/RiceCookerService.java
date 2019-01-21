package com.satouhikaru.service;

import com.satouhikaru.entity.RiceCooker;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface RiceCookerService {

	/**
	 * Count list of rice cookers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

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
	long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo);

	/**
	 * Get list of rice cookers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<RiceCooker> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of rice cookers by brand
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<RiceCooker> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get rice cooker by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id rice cooker ID
	 * @return RiceCooker
	 */
	RiceCooker getById(long id);

	/**
	 * Get rice cooker by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name rice cooker name
	 * @return RiceCooker
	 */
	RiceCooker getByName(String name);

	/**
	 * Add new rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param riceCooker new rice cooker
	 */
	void add(RiceCooker riceCooker);

	/**
	 * Update rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param riceCooker rice cooker need updating
	 */
	void update(RiceCooker riceCooker);

	/**
	 * Delete rice cooker
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id rice cooker ID
	 */
	void delete(long id);
	
}