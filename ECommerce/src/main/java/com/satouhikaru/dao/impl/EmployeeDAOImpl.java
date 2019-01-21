package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.EmployeeDAO;
import com.satouhikaru.entity.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Component
public class EmployeeDAOImpl extends BaseDAO implements EmployeeDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee'";
		return getJdbcTemplate().queryForObject(query, Long.class);
	}

	@Override
	public long countByKeySearch(String keySearch) {
		String query = "select count(*) from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee' and (name LIKE ? or user.userName LIKE ?)";
		return getJdbcTemplate().queryForObject(query, new Object[] {
				"%" + keySearch + "%", "%" + keySearch + "%" }, Long.class);
	}

	@Override
	public List<Employee> getPerPage(long startRow, long maxRows) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee' order by user.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new EmployeeMapper());
	}

	@Override
	public List<Employee> getByKeySearch(String keySearch, long startRow, long maxRows) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee' and (name LIKE ? or user.userName LIKE ?)" +
				" order by user.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] {
				"%" + keySearch + "%", "%" + keySearch + "%", startRow, maxRows }, new EmployeeMapper());
	}

	@Override
	public Employee getById(long id) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee' and id = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { id },
				new EmployeeMapper());
	}

	@Override
	public Employee getByUserName(String userName) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'employee' and user.userName = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { userName },
				new EmployeeMapper());
	}

	@Override
	public void add(Employee employee) {
		String query = "insert ecommerce.user (name, gender, birthday, phone, email, userName)"
				+ "values (?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				employee.getName(),
				employee.getGender(),
				employee.getBirthday(),
				employee.getPhone(),
				employee.getEmail(),
				employee.getUserName());
	}

	@Override
	public void update(Employee employee) {
		String query = "update ecommerce.user set name = ?, gender = ?, birthday = ?, phone = ?,"
				+ "email = ? where id = ?";
		getJdbcTemplate().update(query,
				employee.getName(),
				employee.getGender(),
				employee.getBirthday(),
				employee.getPhone(),
				employee.getEmail(),
				employee.getId());
	}

	private static final class EmployeeMapper implements RowMapper<Employee> {

		public Employee mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Employee(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("gender"),
					resultSet.getDate("birthday"),
					resultSet.getString("phone"),
					resultSet.getString("email"),
					resultSet.getString("userName"),
					resultSet.getString("avatar"));
		}

	}

}