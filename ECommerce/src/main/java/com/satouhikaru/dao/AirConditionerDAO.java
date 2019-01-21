package com.satouhikaru.dao;

import com.satouhikaru.entity.AirConditioner;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface AirConditionerDAO {

	/**
	 * Count list of air conditioners quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

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
	long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo);

	/**
	 * Get list of air conditioners per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<AirConditioner> getPerPage(long startRow, long maxRows);

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
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<AirConditioner> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get air conditioner by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id air conditioner ID
	 * @return AirConditioner
	 */
	AirConditioner getById(long id);

	/**
	 * Get air conditioner by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name air conditioner name
	 * @return AirConditioner
	 */
	AirConditioner getByName(String name);

	/**
	 * Add new air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param airConditioner new air conditioner
	 */
	void add(AirConditioner airConditioner);

	/**
	 * Update air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param airConditioner air conditioner need updating
	 */
	void update(AirConditioner airConditioner);

	/**
	 * Delete air conditioner
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id air conditioner ID
	 */
	void delete(long id);
	
}