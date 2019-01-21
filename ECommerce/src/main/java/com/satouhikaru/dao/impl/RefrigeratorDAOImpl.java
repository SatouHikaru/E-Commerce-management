package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.RefrigeratorDAO;
import com.satouhikaru.entity.Refrigerator;
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
public class RefrigeratorDAOImpl extends BaseDAO implements RefrigeratorDAO {

	
	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.refrigerator"
				+ " on ecommerce.product.id = ecommerce.refrigerator.productID"
				+ " where product.productType = 'Tủ lạnh'";
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
				+ " join ecommerce.refrigerator on ecommerce.product.id = ecommerce.refrigerator.productID"
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
	public List<Refrigerator> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.refrigerator"
				+ " on ecommerce.product.id = ecommerce.refrigerator.productID"
				+ " where product.productType = 'Tủ lạnh'" +
				" order by product.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new RefrigeratorMapper());
	}

	@Override
	public List<Refrigerator> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
										long startRow, long maxRows) {
		List<Refrigerator> refrigeratorList = null;

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
				+ " join ecommerce.refrigerator on ecommerce.product.id = ecommerce.refrigerator.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			refrigeratorList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new RefrigeratorMapper());
		} else if (hasKeySearch) {
			refrigeratorList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new RefrigeratorMapper());
		} else if (hasBrand) {
			refrigeratorList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new RefrigeratorMapper());
		} else if (hasPriceFrom){
			refrigeratorList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new RefrigeratorMapper());
		} else if (!hasPriceFrom){
			refrigeratorList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new RefrigeratorMapper());
		}
		
		return refrigeratorList;
	}

	@Override
	public Refrigerator getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.refrigerator"
				+ " on ecommerce.product.id = ecommerce.refrigerator.productID"
				+ " where product.id = ?";
		return (Refrigerator) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new RefrigeratorMapper());
	}
	
	@Override
	public Refrigerator getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.refrigerator"
				+ " on ecommerce.product.id = ecommerce.refrigerator.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new RefrigeratorMapper());
	}
	@Override
	public void add(Refrigerator refrigerator) {
		String query = "Call sp_AddRefrigerator(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				refrigerator.getName(),
				refrigerator.getPrice(),
				refrigerator.getQuantity(),
				refrigerator.getImage(),
				refrigerator.getBrand(),
				refrigerator.getDescription(),
				refrigerator.getCountryOfOrigin(),
				refrigerator.getSize(),
				refrigerator.getWeight(),
				refrigerator.getMaxVolumetric(),
				refrigerator.getFreezerVolumetric(),
				refrigerator.getIceCubesVolumetric(),
				refrigerator.getCoolingTechnology());
	}

	@Override
	public void update(Refrigerator refrigerator) {
		String query = "Call sp_UpdateRefrigerator(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				refrigerator.getName(),
				refrigerator.getPrice(),
				refrigerator.getQuantity(),
				refrigerator.getImage(),
				refrigerator.getBrand(),
				refrigerator.getDescription(),
				refrigerator.getCountryOfOrigin(),
				refrigerator.getSize(),
				refrigerator.getWeight(),
				refrigerator.getMaxVolumetric(),
				refrigerator.getFreezerVolumetric(),
				refrigerator.getIceCubesVolumetric(),
				refrigerator.getCoolingTechnology(),
				refrigerator.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}

	private static final class RefrigeratorMapper implements RowMapper<Refrigerator> {
		
		public Refrigerator mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Refrigerator(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getString("size"),
					resultSet.getDouble("weight"),
					resultSet.getDouble("maxVolumetric"),
					resultSet.getDouble("freezerVolumetric"),
					resultSet.getDouble("iceCubesVolumetric"),
					resultSet.getString("coolingTechnology"));
		}
		
	}
	
}