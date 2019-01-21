package com.satouhikaru.controller;

import com.satouhikaru.entity.Product;
import com.satouhikaru.service.impl.ProductServiceImpl;
import com.satouhikaru.utility.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	/**
	 * Get list of new product
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @return List
	 */
	@GetMapping("/products")
	public List<Product> getNewProduct() {
		List<Product> productList = productServiceImpl.getNewProduct();
		return CommonUtil.getImagesPath(productList);
	}
	
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
	@GetMapping("/order/detail/{orderID}/{customerID}")
	public List<Product> getOrder(@PathVariable("orderID") long orderID,
			@PathVariable("customerID") long customerID) {
		List<Product> order = productServiceImpl.getOrder(orderID, customerID);
		return CommonUtil.getImagesPath(order);
	}

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
	@GetMapping("/statistic/{startDate}/{endDate}")
	public List<Product> statistic(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate sd = LocalDate.parse(startDate, formatter);
		LocalDate ed = LocalDate.parse(endDate, formatter);
		return productServiceImpl.statistic(Date.valueOf(sd), Date.valueOf(ed));
	}
	
}