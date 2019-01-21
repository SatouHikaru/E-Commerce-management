package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.BrandDAO;
import com.satouhikaru.entity.Brand;
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
public class BrandDAOImpl extends BaseDAO implements BrandDAO {
	
	@Override
	public List<Brand> getAll() {
		String query = "select * from ecommerce.brand";
		return getJdbcTemplate().query(query, new BrandMapper());
	}

	@Override
	public List<Brand> getFilter(String productType) {
		String query = "select distinct ecommerce.brand.* from ecommerce.brand "
				+ "join ecommerce.product on ecommerce.brand.id = ecommerce.product.brandID "
				+ "where ecommerce.product.productType = ?";
		return getJdbcTemplate().query(query, new Object[] { productType }, new BrandMapper());
	}

	private static final class BrandMapper implements RowMapper<Brand> {
		
		public Brand mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Brand(resultSet.getInt("id"), resultSet.getString("name"));
		}
		
	}
	
}