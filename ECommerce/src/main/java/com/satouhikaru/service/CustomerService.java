package com.satouhikaru.service;

import com.satouhikaru.entity.Customer;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface CustomerService {

	/**
	 * Count list of customers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

	/**
	 * Count list of customers quantity by key search
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @return long
	 */
	long countByKeySearch(String keySearch);

	/**
	 * Get list of customers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Customer> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of customers by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Customer> getByKeySearch(String keySearch, long startRow, long maxRows);

	/**
	 * Get customer by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id customer ID
	 * @return Customer
	 */
	Customer getById(long id);

	/**
	 * Get customer by user name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName customer's user name
	 * @return Customer
	 */
	Customer getByUserName(String userName);

	/**
	 * Add new customer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param customer a new customer
	 */
	void add(Customer customer);

	/**
	 * Update customer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param customer customer need updating
	 */
	void update(Customer customer);
	
}