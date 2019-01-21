package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.AccountDAOImpl;
import com.satouhikaru.entity.Account;
import com.satouhikaru.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAOImpl accountDAOImpl;

	@Override
	public Account login(Account account) {
		return accountDAOImpl.login(account);
	}

	@Override
	public int count(String userName) {
		return accountDAOImpl.count(userName);
	}

	@Override
	public Account getByUserName(String userName) {
		return accountDAOImpl.getByUserName(userName);
	}

	@Override
	public void add(Account account) {
		accountDAOImpl.add(account);
	}

	@Override
	public void update(Account account) {
		accountDAOImpl.update(account);
	}

	@Override
	public void delete(String userName) {
		accountDAOImpl.delete(userName);
	}
	
}