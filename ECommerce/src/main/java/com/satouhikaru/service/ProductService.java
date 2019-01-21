package com.satouhikaru.service;

import com.satouhikaru.entity.Product;

import java.sql.Date;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface ProductService {

	/**
	 * Get list of new product
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @return List
	 */
	List<Product> getNewProduct();

	/**
	 * Get order detail
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  01/01/2019
	 *
	 * @param orderID order ID
	 * @param customerID customer ID
	 * @return List
	 */
	List<Product> getOrder(long orderID, long customerID);

	/**
	 * Revenue statistics
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  01/02/2019
	 *
	 * @param startDate
	 * @param endDate
	 * @return List
	 */
	List<Product> statistic(Date startDate, Date endDate);
	
}