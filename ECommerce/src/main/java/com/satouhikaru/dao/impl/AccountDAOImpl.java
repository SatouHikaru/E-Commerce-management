package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.AccountDAO;
import com.satouhikaru.entity.Account;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Tran Thi Hoa Nhu
 * @since  17/11/2018
 */
@Component
public class AccountDAOImpl extends BaseDAO implements AccountDAO {

	@Override
	public Account login(Account account) {
		String query = "select account.userName, avatar, roleName"
				+ " from ecommerce.account join ecommerce.role on account.roleID = role.roleID"
				+ " where account.userName = ? and password = ?";
		try {
			return getJdbcTemplate().queryForObject(query, new Object[] {
					account.getUserName(), account.getPassword()}, new AccountMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}

		return null;
	}

	@Override
	public int count(String userName) {
		String query = "SELECT COUNT(*) FROM ecommerce.account WHERE userName LIKE ?";
		return getJdbcTemplate().queryForObject(query, new Object[] {
				userName }, Integer.class);
	}

	@Override
	public Account getByUserName(String userName) {
		String query = "select userName, avatar, roleName from ecommerce.account"
				+ " join ecommerce.role on ecommerce.account.roleID = ecommerce.role.roleID"
				+ " where userName = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { userName },
				new AccountMapper());
	}

	@Override
	public void add(Account account) {
		String query = "insert ecommerce.account values (?,?,?,?)";
		getJdbcTemplate().update(query,
				account.getUserName(),
				account.getPassword(),
				"avatar.jpg",
				account.getRoleID());
	}

	@Override
	public void update(Account account) {
		String query = "update ecommerce.account" +
				" set avatar = ?, password = ?" +
				" where userName = ?";
		getJdbcTemplate().update(query,
				account.getAvatar(),
				account.getPassword(),
				account.getUserName());
	}

	@Override
	public void delete(String userName) {
		String query = "delete from ecommerce.account where userName = ?";
		getJdbcTemplate().update(query, userName);
	}
	
	private static final class AccountMapper implements RowMapper<Account> {
		
		public Account mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Account(resultSet.getString("userName"),
					resultSet.getString("avatar"),
					resultSet.getString("roleName"));
		}
		
	}
	
}