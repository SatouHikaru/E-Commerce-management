package com.satouhikaru.service;

import com.satouhikaru.entity.WirelessRouter;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface WirelessRouterService {

	/**
	 * Count list of wireless routers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

	/**
	 * Count list of wireless routers quantity by filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @return long
	 */
	long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo);

	/**
	 * Get list of wireless routers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<WirelessRouter> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of wireless routers by brand
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<WirelessRouter> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get wireless router by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id wireless router ID
	 * @return WirelessRouter
	 */
	WirelessRouter getById(long id);

	/**
	 * Get wireless router by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name wireless router name
	 * @return WirelessRouter
	 */
	WirelessRouter getByName(String name);

	/**
	 * Add new wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param wirelessRouter new wireless router
	 */
	void add(WirelessRouter wirelessRouter);

	/**
	 * Update wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param wirelessRouter wireless router need updating
	 */
	void update(WirelessRouter wirelessRouter);

	/**
	 * Delete wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id wireless router ID
	 */
	void delete(long id);
	
}