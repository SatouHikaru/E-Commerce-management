package com.satouhikaru.dao;

import com.satouhikaru.entity.Employee;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface EmployeeDAO {

	/**
	 * Count list of employees quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	long countAll();

	/**
	 * Count list of employees quantity by key search
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @return long
	 */
	long countByKeySearch(String keySearch);

	/**
	 * Get list of employees per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Employee> getPerPage(long startRow, long maxRows);

	/**
	 * Get list of employees by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param startRow
	 * @param maxRows
	 * @return List
	 */
	List<Employee> getByKeySearch(String keySearch, long startRow, long maxRows);

	/**
	 * Get employee by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id employee ID
	 * @return Employee
	 */
	Employee getById(long id);

	/**
	 * Get employee by user name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName employee's user name
	 * @return Employee
	 */
	Employee getByUserName(String userName);

	/**
	 * Add new employee
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param employee a new employee
	 */
	void add(Employee employee);

	/**
	 * Update employee
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param employee employee need updating
	 */
	void update(Employee employee);

}