package com.satouhikaru.dao;

import com.satouhikaru.entity.Refrigerator;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface RefrigeratorDAO {

	/**
	 * Count list of refrigerators quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

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
	long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo);

	/**
	 * Get list of refrigerators per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Refrigerator> getPerPage(long startRow, long maxRows);

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
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Refrigerator> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get refrigerator by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id refrigerator ID
	 * @return Refrigerator
	 */
	Refrigerator getById(long id);

	/**
	 * Get refrigerator by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name refrigerator name
	 * @return Refrigerator
	 */
	Refrigerator getByName(String name);

	/**
	 * Add new refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param refrigerator a new refrigerator
	 */
	void add(Refrigerator refrigerator);

	/**
	 * Update refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param refrigerator refrigerator need updating
	 */
	void update(Refrigerator refrigerator);

	/**
	 * Delete refrigerator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id refrigerator ID
	 */
	void delete(long id);
	
}