package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.OrderDAOImpl;
import com.satouhikaru.entity.Order;
import com.satouhikaru.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAOImpl orderDAOImpl;
	
	@Override
	public long countAll() {
		return orderDAOImpl.countAll();
	}

	@Override
	public List<Order> getPerPage(long startRow, long maxRows) {
		return orderDAOImpl.getPerPage(startRow, maxRows);
	}

	@Override
	public List<Order> getList(long customerID) {
		return orderDAOImpl.getList(customerID);
	}

	@Override
	public Order getById(long id) {
		return orderDAOImpl.getById(id);
	}

	@Override
	public List<Order> getOrderDetail(long id) {
		return orderDAOImpl.getOrderDetail(id);
	}

	@Override
	public Order getByCustomerID(long customerID) {
		return orderDAOImpl.getByCustomerID(customerID);
	}

	@Override
	public void add(Order order) {
		orderDAOImpl.add(order);
	}

	@Override
	public void addNewProductToOrder(Order order) {
		orderDAOImpl.addNewProductToOrder(order);
	}

	@Override
	public void update(Order order) {
		orderDAOImpl.update(order);
	}

	@Override
	public void checkout(Order order) {
		orderDAOImpl.checkout(order);
	}

	@Override
	public void remove(long orderID, long productID, int quantity) {
		orderDAOImpl.remove(orderID, productID, quantity);
	}

	@Override
	public void delete(long id) {
		orderDAOImpl.delete(id);
	}

}