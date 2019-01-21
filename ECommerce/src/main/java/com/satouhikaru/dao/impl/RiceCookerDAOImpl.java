package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.RiceCookerDAO;
import com.satouhikaru.entity.RiceCooker;
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
public class RiceCookerDAOImpl extends BaseDAO implements RiceCookerDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.rice_cooker"
				+ " on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " where product.productType = 'Nồi cơm điện'";
		return getJdbcTemplate().queryForObject(query, Long.class);
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		long count = 0;

		String sql = " where ";
		boolean hasKeySearch = false;
		if (keySearch != null) {
			sql += "product.name like ?";
			hasKeySearch = true;
		}

		boolean hasBrand = false;
		if (brands != null) {
			if (hasKeySearch) {
				sql += " and ";
			}

			sql += brands;
			hasBrand = true;
		}

		boolean hasPriceFrom = false;
		if (priceTo != null) {
			if (hasKeySearch || hasBrand) {
				sql += " and ";
			}

			if (priceFrom != null) {
				sql += "product.price between ? and ?";
				hasPriceFrom = true;
			} else {
				sql += "product.price between 0 and ?";
			}
		}
		
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.rice_cooker on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql;

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			count = getJdbcTemplate().queryForObject(query, new Object[]{
					"%" + keySearch + "%", priceFrom, priceTo}, Long.class);
		} else if (hasKeySearch) {
			count = getJdbcTemplate().queryForObject(query, new Object[]{
					"%" + keySearch + "%"}, Long.class);
		} else if (hasBrand) {
			count = getJdbcTemplate().queryForObject(query, Long.class);
		} else if (hasPriceFrom){
			count = getJdbcTemplate().queryForObject(query, new Object[] {
					priceFrom, priceTo }, Long.class);
		} else if (!hasPriceFrom){
			count = getJdbcTemplate().queryForObject(query, new Object[] {
					priceTo }, Long.class);
		}
		
		return count;
	}

	@Override
	public List<RiceCooker> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.rice_cooker"
				+ " on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " where product.productType = 'Nồi cơm điện'" +
				" order by product.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new RiceCookerMapper());
	}

	@Override
	public List<RiceCooker> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		List<RiceCooker> riceCookerList = null;

		String sql = " where ";
		boolean hasKeySearch = false;
		if (keySearch != null) {
			sql += "product.name like ?";
			hasKeySearch = true;
		}

		boolean hasBrand = false;
		if (brands != null) {
			if (hasKeySearch) {
				sql += " and ";
			}

			sql += brands;
			hasBrand = true;
		}

		boolean hasPriceFrom = false;
		if (priceTo != null) {
			if (hasKeySearch || hasBrand) {
				sql += " and ";
			}

			if (priceFrom != null) {
				sql += "product.price between ? and ?";
				hasPriceFrom = true;
			} else {
				sql += "product.price between 0 and ?";
			}
		}
		
		String query = "select * from ecommerce.product"
				+ " join ecommerce.rice_cooker on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			riceCookerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new RiceCookerMapper());
		} else if (hasKeySearch) {
			riceCookerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new RiceCookerMapper());
		} else if (hasBrand) {
			riceCookerList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new RiceCookerMapper());
		} else if (hasPriceFrom){
			riceCookerList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new RiceCookerMapper());
		} else if (!hasPriceFrom){
			riceCookerList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new RiceCookerMapper());
		}
		
		return riceCookerList;
	}

	@Override
	public RiceCooker getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.rice_cooker"
				+ " on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " where product.id = ?";
		return (RiceCooker) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new RiceCookerMapper());
	}
	
	@Override
	public RiceCooker getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.rice_cooker"
				+ " on ecommerce.product.id = ecommerce.rice_cooker.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new RiceCookerMapper());
	}

	@Override
	public void add(RiceCooker riceCooker) {
		String query = "Call sp_AddRiceCooker(?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				riceCooker.getName(),
				riceCooker.getPrice(),
				riceCooker.getQuantity(),
				riceCooker.getImage(),
				riceCooker.getBrand(),
				riceCooker.getDescription(),
				riceCooker.getCountryOfOrigin(),
				riceCooker.getVolumetric(),
				riceCooker.getTimer(),
				riceCooker.getStandardTechnology());
	}

	@Override
	public void update(RiceCooker riceCooker) {
		String query = "Call sp_UpdateRiceCooker(?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				riceCooker.getName(),
				riceCooker.getPrice(),
				riceCooker.getQuantity(),
				riceCooker.getImage(),
				riceCooker.getBrand(),
				riceCooker.getDescription(),
				riceCooker.getCountryOfOrigin(),
				riceCooker.getVolumetric(),
				riceCooker.getTimer(),
				riceCooker.getStandardTechnology(),
				riceCooker.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class RiceCookerMapper implements RowMapper<RiceCooker> {
		
		public RiceCooker mapRow(ResultSet resultSet, int param) throws SQLException {
			return new RiceCooker(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getDouble("volumetric"),
					resultSet.getString("timer"),
					resultSet.getString("standardTechnology"));
		}
		
	}
	
}