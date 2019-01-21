package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.CustomerDAOImpl;
import com.satouhikaru.entity.Customer;
import com.satouhikaru.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOImpl customerDAOImpl;

	@Override
	public long countAll() {
		return customerDAOImpl.countAll();
	}

	@Override
	public long countByKeySearch(String keySearch) {
		return customerDAOImpl.countByKeySearch(keySearch);
	}

	@Override
	public List<Customer> getPerPage(long startRow, long maxRows) {
		return customerDAOImpl.getPerPage(startRow, maxRows);
	}

	@Override
	public List<Customer> getByKeySearch(String keySearch, long startRow, long maxRows) {
		return customerDAOImpl.getByKeySearch(keySearch, startRow, maxRows);
	}

	@Override
	public Customer getById(long id) {
		return customerDAOImpl.getById(id);
	}

	@Override
	public Customer getByUserName(String userName) {
		return customerDAOImpl.getByUserName(userName);
	}

	@Override
	public void add(Customer customer) {
		customerDAOImpl.add(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDAOImpl.update(customer);
	}
}