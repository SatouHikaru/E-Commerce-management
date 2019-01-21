package com.satouhikaru.controller;

import com.satouhikaru.entity.Order;
import com.satouhikaru.service.impl.OrderServiceImpl;
import com.satouhikaru.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	/**
	 * Count list of orders
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/orders/countAll")
	public long countAll() {
		return orderServiceImpl.countAll();
	}
	
	/**
	 * Get list of orders per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/orders/page/{page}")
	public List<Order> getPerPage(@PathVariable("page") long page) {
		return orderServiceImpl.getPerPage(Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
	}

	/**
	 * Get list of orders
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param customerID customer ID
	 */
	@GetMapping("/orders/customers/{customerID}")
	public List<Order> getList(@PathVariable("customerID") long customerID) {
		return orderServiceImpl.getList(customerID);
	}

	/**
	 * Get order by customer ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param customerID customer ID
	 */
	@GetMapping("/orders")
	public Order getByCustomerID(@RequestParam("customerID") long customerID) {
		return orderServiceImpl.getByCustomerID(customerID);
	}

	
	/**
	 * Add new order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param order new order
	 */
	@PostMapping("/orders")
	public void add(@RequestBody Order order) {
		orderServiceImpl.add(order);
	}

	/**
	 * Add new product to order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param order new order
	 */
	@PostMapping("/orders/new")
	public void addNewProductToOrder(@RequestBody Order order) {
		orderServiceImpl.addNewProductToOrder(order);
	}
	
	/**
	 * Update order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id order ID
	 * @param order order need updating
	 */
	@PutMapping("/orders/{id}")
	public void update(@PathVariable("id") long id,
			@RequestBody Order order) {
		Order o = orderServiceImpl.getById(id);
		if (o == null) {
			return;
		}

		orderServiceImpl.update(order);
	}

	/**
	 * Check out order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param orderID order ID
	 * @param orderParam order need checking out
	 */
	@PutMapping("/orders/checkout/{orderID}")
	public void checkout(@PathVariable("orderID") long orderID,
			@RequestBody Order orderParam) {
		Order order = orderServiceImpl.getById(orderID);
		if (order == null) {
			return;
		}

		orderServiceImpl.checkout(orderParam);
	}
	
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
	@DeleteMapping("/orders/{orderID}/{productID}/{quantity}")
	public void remove(@PathVariable("orderID") long orderID,
			@PathVariable("productID") long productID,
			@PathVariable("quantity") int quantity) {
		List<Order> order = orderServiceImpl.getOrderDetail(orderID);
		for (Order ord : order) {
			if (ord.getProductID() == productID) {
				orderServiceImpl.remove(orderID, productID, quantity);
				return;
			}
		}
	}

	/**
	 * Delete order
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id order ID
	 */
	@DeleteMapping("/orders/{id}")
	public void delete(@PathVariable("id") long id) {
		Order order = orderServiceImpl.getById(id);
		if (order == null) {
			return;
		}

		orderServiceImpl.delete(id);
	}
	
}