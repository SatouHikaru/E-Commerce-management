package com.satouhikaru.dao;

import com.satouhikaru.entity.DVDPlayer;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface DVDPlayerDAO {

	/**
	 * Count list of dvd players quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

	/**
	 * Count list of dvd players quantity by filter
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
	 * Get list of dvd players per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<DVDPlayer> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of dvd players by brand
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
	List<DVDPlayer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows);

	/**
	 * Get dvd player by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id dvd player ID
	 * @return DVDPlayer
	 */
	DVDPlayer getById(long id);

	/**
	 * Get dvd player by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name dvd player name
	 * @return DVDPlayer
	 */
	DVDPlayer getByName(String name);

	/**
	 * Add new dvd player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param dvdPlayer new dvd player
	 */
	void add(DVDPlayer dvdPlayer);

	/**
	 * Update dvd player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param dvdPlayer dvd player need updating
	 */
	void update(DVDPlayer dvdPlayer);

	/**
	 * Delete dvd player
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id dvd player ID
	 */
	void delete(long id);
	
}