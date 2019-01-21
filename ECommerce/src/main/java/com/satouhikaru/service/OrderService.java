package com.satouhikaru.service;

import com.satouhikaru.entity.Order;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface OrderService {

	/**
	 * Count list of orders
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

	/**
	 * Get list of orders per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Order> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of orders
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param customerID customer ID
	 */
	List<Order> getList(long customerID);

	/**
	 * Get order by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id order ID
	 * @return Order
	 */
	Order getById(long id);

	/**
	 * Get order detail by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id order ID
	 * @return List
	 */
	List<Order> getOrderDetail(long id);

	/**
	 * Get order by customer ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param customerID customer ID
	 */
	Order getByCustomerID(long customerID);

	/**
	 * Add new order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param order new order
	 */
	void add(Order order);

	/**
	 * Add new product to order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param order new order
	 */
	void addNewProductToOrder(Order order);

	/**
	 * Update order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param order order need updating
	 */
	void update(Order order);

	/**
	 * Check out order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param order order need checking out
	 */
	void checkout(Order order);

	/**
	 * Delete order detail
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param orderID order ID
	 * @param productID product ID
	 * @param quantity product quantity
	 */
	void remove(long orderID, long productID, int quantity);

	/**
	 * Delete order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id order ID
	 */
	void delete(long id);
	
}