package com.satouhikaru.controller;

import com.satouhikaru.entity.Employee;
import com.satouhikaru.service.impl.EmployeeServiceImpl;
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
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	/**
	 * Count list of employees quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/employees/countAll")
	public long countAll() {
		return employeeServiceImpl.countAll();
	}

	/**
	 * Count list of employees quantity by filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @return long
	 */
	@GetMapping("/employees/count")
	public long countByKeySearch(@RequestParam("keySearch") String keySearch) {
		String t = CommonUtil.checkKeySearchIsEmpty(keySearch);

		long result = 0;
		if (t != null) {
			result = employeeServiceImpl.countByKeySearch(keySearch);
		}

		return result;
	}

	/**
	 * Get list of employees per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/employees/page/{page}")
	public List<Employee> getPerPage(@PathVariable("page") long page) {
		List<Employee> employeeList = employeeServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Employee employee : employeeList) {
			if (employee.getAvatar().equals("avatar.jpg")) {
				employee.setAvatar("assets\\images\\" + employee.getAvatar());
			} else {
				employee.setAvatar((Constant.AVATAR_PATH + employee.getAvatar()));
			}
		}

		return employeeList;
	}

	/**
	 * Get list of employees by keySearch
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param page
	 * @return List
	 */
	@GetMapping("/employees/search/{page}")
	public List<Employee> getByKeySearch(@RequestParam("keySearch") String keySearch,
			@PathVariable("page") long page) {
		List<Employee> employeeList = employeeServiceImpl.getByKeySearch(keySearch,
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (Employee employee : employeeList) {
			if (employee.getAvatar().equals("avatar.jpg")) {
				employee.setAvatar("assets\\images\\" + employee.getAvatar());
			} else {
				employee.setAvatar((Constant.AVATAR_PATH + employee.getAvatar()));
			}
		}

		return employeeList;
	}

	/**
	 * Get employee by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id employee ID
	 * @return Employee
	 */
	@GetMapping("/employees/id/{id}")
	public Employee getById(@PathVariable("id") long id) {
		Employee employee = employeeServiceImpl.getById(id);
		if (employee.getAvatar().equals("avatar.jpg")) {
			employee.setAvatar("assets\\images\\" + employee.getAvatar());
		} else {
			employee.setAvatar((Constant.AVATAR_PATH + employee.getAvatar()));
		}

		return employee;
	}

	/**
	 * Get employee by employee name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param employeeName employee's employee name
	 * @return Employee
	 */
	@GetMapping("/employees/{employeeName}")
	public Employee getByUserName(@PathVariable("employeeName") String employeeName) {
		return employeeServiceImpl.getByUserName(employeeName);
	}

	/**
	 * Add new employee
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param employee new employee
	 */
	@PostMapping("/employees")
	public void add(@RequestBody Employee employee) {
		employeeServiceImpl.add(employee);
	}

	/**
	 * Update employee
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id employee ID
	 * @param employeeParam employee need updating
	 */
	@PutMapping("/employees/{id}")
	public void update(@PathVariable("id") long id, @RequestBody Employee employeeParam) {
		Employee employee = employeeServiceImpl.getById(id);
		if (employee == null) {
			return;
		}

		employeeServiceImpl.update(employeeParam);
	}

}