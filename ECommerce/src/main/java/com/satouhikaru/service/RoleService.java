package com.satouhikaru.service;

import com.satouhikaru.entity.Role;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public interface RoleService {

	/**
	 * Get list of roles
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @return List
	 */
	List<Role> getAll();

	/**
	 * Get role by ID
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id role ID
	 * @return Role
	 */
	Role getById(long id);

	/**
	 * Add new role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param role a new role
	 */
	void add(Role role);

	/**
	 * Update role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param role role need editing
	 */
	void update(Role role);

	/**
	 * Delete role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id role ID
	 */
	void delete(long id);
	
}