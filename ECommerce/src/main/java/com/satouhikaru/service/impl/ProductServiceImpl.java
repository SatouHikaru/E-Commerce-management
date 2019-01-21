package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.ProductDAOImpl;
import com.satouhikaru.entity.Product;
import com.satouhikaru.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAOImpl productDAOImpl;
	
	@Override
	public List<Product> getNewProduct() {
		return productDAOImpl.getNewProduct();
	}
	
	@Override
	public List<Product> getOrder(long orderID, long customerID) {
		return productDAOImpl.getOrder(orderID, customerID);
	}

	@Override
	public List<Product> statistic(Date startDate, Date endDate) {
		return productDAOImpl.statistic(startDate, endDate);
	}

}