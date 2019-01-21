package com.satouhikaru.controller;

import com.satouhikaru.entity.Customer;
import com.satouhikaru.service.impl.CustomerServiceImpl;
import com.satouhikaru.utility.CommonUtil;
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
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	/**
	 * Count list of customers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/customers/countAll")
	public long countAll() {
		return customerServiceImpl.countAll();
	}

	/**
	 * Count list of customers quantity by filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @return long
	 */
	@GetMapping("/customers/count")
	public long countByKeySearch(@RequestParam("keySearch") String keySearch) {
		String t = CommonUtil.checkKeySearchIsEmpty(keySearch);

		long result = 0;
		if (t != null) {
			result = customerServiceImpl.countByKeySearch(keySearch);
		}

		return result;
	}

	/**
	 * Get list of customers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/customers/page/{page}")
	public List<Customer> getPerPage(@PathVariable("page") long page) {
		List<Customer> customerList = customerServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Customer customer : customerList) {
			if (customer.getAvatar().equals("avatar.jpg")) {
				customer.setAvatar("assets\\images\\" + customer.getAvatar());
			} else {
				customer.setAvatar((Constant.AVATAR_PATH + customer.getAvatar()));
			}
		}

		return customerList;
	}

	/**
	 * Get list of customers by keySearch
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param page
	 * @return List
	 */
	@GetMapping("/customers/search/{page}")
	public List<Customer> getByKeySearch(@RequestParam("keySearch") String keySearch,
			@PathVariable("page") long page) {
		List<Customer> customerList = customerServiceImpl.getByKeySearch(keySearch,
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Customer customer : customerList) {
			if (customer.getAvatar().equals("avatar.jpg")) {
				customer.setAvatar("assets\\images\\" + customer.getAvatar());
			} else {
				customer.setAvatar((Constant.AVATAR_PATH + customer.getAvatar()));
			}
		}

		return customerList;
	}

	/**
	 * Get customer by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id customer ID
	 * @return Customer
	 */
	@GetMapping("/customers/id/{id}")
	public Customer getById(@PathVariable("id") long id) {
		Customer customer = customerServiceImpl.getById(id);
		if (customer.getAvatar().equals("avatar.jpg")) {
			customer.setAvatar("assets\\images\\" + customer.getAvatar());
		} else {
			customer.setAvatar((Constant.AVATAR_PATH + customer.getAvatar()));
		}

		return customer;
	}

	/**
	 * Get customer by customer name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param customerName customer's customer name
	 * @return Customer
	 */
	@GetMapping("/customers/userName/{customerName}")
	public Customer getByUserName(@PathVariable("customerName") String customerName) {
		return customerServiceImpl.getByUserName(customerName);
	}

	/**
	 * Add new customer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param customer a new customer
	 */
	@PostMapping("/customers")
	public void add(@RequestBody Customer customer) {
		customerServiceImpl.add(customer);
	}

	/**
	 * Update customer
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id customer ID
	 * @param customerParam customer need updating
	 */
	@PutMapping("/customers/{id}")
	public void update(@PathVariable("id") long id, @RequestBody Customer customerParam) {
		Customer customer = customerServiceImpl.getById(id);
		if (customer == null) {
			return;
		}
		
		customerServiceImpl.update(customerParam);
	}
	
}