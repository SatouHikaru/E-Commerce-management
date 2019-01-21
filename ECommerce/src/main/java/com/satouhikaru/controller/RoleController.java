package com.satouhikaru.controller;

import com.satouhikaru.entity.Role;
import com.satouhikaru.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	/**
	 * Get list of roles
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @return List
	 */
	@GetMapping("/roles")
	public List<Role> getAll() {
		return roleServiceImpl.getAll();
	}

	/**
	 * Add new role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param role a new role
	 */
	@PostMapping("/roles")
	public void add(Role role) {
		roleServiceImpl.add(role);
	}

	/**
	 * Update role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id role ID
	 * @param roleParam role need editing
	 */
	@PutMapping("/roles/{id}")
	public void update(@PathVariable("id") long id, @RequestBody Role roleParam) {
		Role role = roleServiceImpl.getById(id);
		if (role == null) {
			return;
		}
		
		roleServiceImpl.update(roleParam);
	}

	/**
	 * Delete role
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id role ID
	 */
	@DeleteMapping("/roles/{id}")
	public void delete(@PathVariable("id") long id) {
		Role role = roleServiceImpl.getById(id);
		if (role == null) {
			return;
		}
		
		roleServiceImpl.delete(id);
	}

}