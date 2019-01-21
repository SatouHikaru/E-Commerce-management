package com.satouhikaru.dao;

import com.satouhikaru.entity.Account;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface AccountDAO {

	/**
	 * Login
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param account
	 * @return Account
	 */
	Account login(Account account);

	/**
	 * Check if account is exists
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param userName
	 * @return int
	 */
	int count(String userName);
	
	/**
	 * Get account by user name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName
	 * @return Account
	 */
	Account getByUserName(String userName);
	
	/**
	 * Add new account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param account a new account
	 */
	void add(Account account);

	/**
	 * Update account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param account account need updating
	 */
	void update(Account account);
	
	/**
	 * Delete account
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param userName
	 */
	void delete(String userName);
	
}