package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.RoleDAOImpl;
import com.satouhikaru.entity.Role;
import com.satouhikaru.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAOImpl roleDAOImpl;
	
	@Override
	public List<Role> getAll() {
		return roleDAOImpl.getAll();
	}

	@Override
	public Role getById(long id) {
		return roleDAOImpl.getById(id);
	}

	@Override
	public void add(Role role) {
		roleDAOImpl.add(role);
	}

	@Override
	public void update(Role role) {
		roleDAOImpl.update(role);
	}

	@Override
	public void delete(long id) {
		roleDAOImpl.delete(id);
	}
	
}