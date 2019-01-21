package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.CustomerDAO;
import com.satouhikaru.entity.Customer;
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
public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer'";
		return getJdbcTemplate().queryForObject(query, Long.class);
	}

	@Override
	public long countByKeySearch(String keySearch) {
		String query = "select count(*) from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer' and (name LIKE ? or user.userName LIKE ?)";
		return getJdbcTemplate().queryForObject(query, new Object[] {
				"%" + keySearch + "%", "%" + keySearch + "%" }, Long.class);
	}

	@Override
	public List<Customer> getPerPage(long startRow, long maxRows) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer' order by user.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new CustomerMapper());
	}

	@Override
	public List<Customer> getByKeySearch(String keySearch, long startRow, long maxRows) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer' and (name LIKE ? or user.userName LIKE ?)" +
				" order by user.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] {
				"%" + keySearch + "%", "%" + keySearch + "%", startRow, maxRows },
				new CustomerMapper());
	}

	@Override
	public Customer getById(long id) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer' and id = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { id },
				new CustomerMapper());
	}

	@Override
	public Customer getByUserName(String userName) {
		String query = "select id, user.name, gender, birthday, phone, email, user.userName, avatar" +
				" from ecommerce.user" +
				" join ecommerce.account" +
				" on ecommerce.user.userName = ecommerce.account.userName" +
				" join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID" +
				" where roleName = 'customer' and user.userName = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { userName },
				new CustomerMapper());
	}

	@Override
	public void add(Customer customer) {
		String query = "insert ecommerce.user (name, gender, birthday, phone, email, userName)"
				+ "values (?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				customer.getName(),
				customer.getGender(),
				customer.getBirthday(),
				customer.getPhone(),
				customer.getEmail(),
				customer.getUserName());
	}

	@Override
	public void update(Customer customer) {
		String query = "update ecommerce.user set name = ?, gender = ?, birthday = ?, phone = ?,"
				+ "email = ? where id = ?";
		getJdbcTemplate().update(query,
				customer.getName(),
				customer.getGender(),
				customer.getBirthday(),
				customer.getPhone(),
				customer.getEmail(),
				customer.getId());
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {
		
		public Customer mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Customer(resultSet.getLong("id"),
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