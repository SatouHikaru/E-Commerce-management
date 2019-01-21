package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.WasherDAO;
import com.satouhikaru.entity.Washer;
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
public class WasherDAOImpl extends BaseDAO implements WasherDAO {


	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.washer"
				+ " on ecommerce.product.id = ecommerce.washer.productID"
				+ " where product.productType = 'Máy giặt'";
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
				+ " join ecommerce.washer on ecommerce.product.id = ecommerce.washer.productID"
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
	public List<Washer> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.washer"
				+ " on ecommerce.product.id = ecommerce.washer.productID"
				+ " where product.productType = 'Máy giặt'" +
				" order by product.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new WasherMapper());
	}

	@Override
	public List<Washer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
										long startRow, long maxRows) {
		List<Washer> washerList = null;

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
				+ " join ecommerce.washer on ecommerce.product.id = ecommerce.washer.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			washerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new WasherMapper());
		} else if (hasKeySearch) {
			washerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new WasherMapper());
		} else if (hasBrand) {
			washerList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new WasherMapper());
		} else if (hasPriceFrom){
			washerList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new WasherMapper());
		} else if (!hasPriceFrom){
			washerList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new WasherMapper());
		}

		return washerList;
	}

	@Override
	public Washer getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.washer"
				+ " on ecommerce.product.id = ecommerce.washer.productID"
				+ " where product.id = ?";
		return (Washer) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new WasherMapper());
	}
	
	@Override
	public Washer getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.washer"
				+ " on ecommerce.product.id = ecommerce.washer.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new WasherMapper());
	}
	@Override
	public void add(Washer washer) {
		String query = "Call sp_AddWasher(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				washer.getName(),
				washer.getPrice(),
				washer.getQuantity(),
				washer.getImage(),
				washer.getBrand(),
				washer.getDescription(),
				washer.getCountryOfOrigin(),
				washer.getType(),
				washer.getLaundryCage(),
				washer.getSpinSpeed(),
				washer.getEngine(),
				washer.getSize(),
				washer.getWeight(),
				washer.getStandardTechnology());
	}

	@Override
	public void update(Washer washer) {
		String query = "Call sp_UpdateWasher(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				washer.getName(),
				washer.getPrice(),
				washer.getQuantity(),
				washer.getImage(),
				washer.getBrand(),
				washer.getDescription(),
				washer.getCountryOfOrigin(),
				washer.getType(),
				washer.getLaundryCage(),
				washer.getSpinSpeed(),
				washer.getEngine(),
				washer.getSize(),
				washer.getWeight(),
				washer.getStandardTechnology(),
				washer.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}

	private static final class WasherMapper implements RowMapper<Washer> {
		
		public Washer mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Washer(resultSet.getLong("id"),
			resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getString("type"),
					resultSet.getString("laundryCage"),
					resultSet.getInt("spinSpeed"),
					resultSet.getString("engine"),
					resultSet.getString("size"),
					resultSet.getDouble("weight"),
					resultSet.getString("standardTechnology"));
		}
		
	}

}