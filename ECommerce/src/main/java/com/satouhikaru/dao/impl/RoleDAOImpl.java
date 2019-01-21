package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.RoleDAO;
import com.satouhikaru.entity.Role;
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
public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	@Override
	public List<Role> getAll() {
		String query = "select * from ecommerce.role";
		return getJdbcTemplate().query(query, new RoleMapper());
	}

	@Override
	public Role getById(long id) {
		String query = "select * from ecommerce.role where id = ?";
		return (Role) getJdbcTemplate().query(query, new Object[] { id },
				new RoleMapper());
	}

	@Override
	public void add(Role role) {
		String query = "insert ecommerce.role(roleName) values (?)";
		getJdbcTemplate().update(query, role.getRoleName());
	}

	@Override
	public void update(Role role) {
		String query = "update ecommerce.role set roleName = ? where id = ?";
		getJdbcTemplate().update(query, role.getRoleName(), role.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.role where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class RoleMapper implements RowMapper<Role> {
		
		public Role mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Role(resultSet.getInt("id"), resultSet.getString("roleName"));
		}
		
	}

}