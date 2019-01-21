package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.EmployeeDAOImpl;
import com.satouhikaru.entity.Employee;
import com.satouhikaru.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAOImpl employeeDAOImpl;


	@Override
	public long countAll() {
		return employeeDAOImpl.countAll();
	}

	@Override
	public long countByKeySearch(String keySearch) {
		return employeeDAOImpl.countByKeySearch(keySearch);
	}

	@Override
	public List<Employee> getPerPage(long startRow, long maxRows) {
		return employeeDAOImpl.getPerPage(startRow, maxRows);
	}

	@Override
	public List<Employee> getByKeySearch(String keySearch, long startRow, long maxRows) {
		return employeeDAOImpl.getByKeySearch(keySearch, startRow, maxRows);
	}

	@Override
	public Employee getById(long id) {
		return employeeDAOImpl.getById(id);
	}

	@Override
	public Employee getByUserName(String userName) {
		return employeeDAOImpl.getByUserName(userName);
	}

	@Override
	public void add(Employee employee) {
		employeeDAOImpl.add(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDAOImpl.update(employee);
	}

}