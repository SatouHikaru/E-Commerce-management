package com.satouhikaru.dao;

import com.satouhikaru.entity.Washer;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface WasherDAO {

	/**
	 * Count list of washers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

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
	long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo);

	/**
	 * Get list of washers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Washer> getPerPage(long startRow, long maxRows);

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
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Washer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get washer by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id washer ID
	 * @return Washer
	 */
	Washer getById(long id);

	/**
	 * Get washer by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name washer name
	 * @return Washer
	 */
	Washer getByName(String name);

	/**
	 * Add new washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param washer new washer
	 */
	void add(Washer washer);

	/**
	 * Update washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param washer washer need updating
	 */
	void update(Washer washer);

	/**
	 * Delete washer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id washer ID
	 */
	void delete(long id);
	
}