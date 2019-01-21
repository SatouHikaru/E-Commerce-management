package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.AirConditionerDAO;
import com.satouhikaru.entity.AirConditioner;
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
public class AirConditionerDAOImpl extends BaseDAO implements AirConditionerDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.air_conditioner"
				+ " on ecommerce.product.id = ecommerce.air_conditioner.productID"
				+ " where product.productType = 'Điều hoà'";
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
				+ " join ecommerce.air_conditioner on ecommerce.product.id = ecommerce.air_conditioner.productID"
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
	public List<AirConditioner> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.air_conditioner"
				+ " on ecommerce.product.id = ecommerce.air_conditioner.productID"
				+ " where product.productType = 'Điều hoà' limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new AirConditionerMapper());
	}

	@Override
	public List<AirConditioner> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
										long startRow, long maxRows) {
		List<AirConditioner> airConditionerList = null;

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
				+ " join ecommerce.air_conditioner on ecommerce.product.id = ecommerce.air_conditioner.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			airConditionerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new AirConditionerMapper());
		} else if (hasKeySearch) {
			airConditionerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new AirConditionerMapper());
		} else if (hasBrand) {
			airConditionerList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new AirConditionerMapper());
		} else if (hasPriceFrom){
			airConditionerList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new AirConditionerMapper());
		} else if (!hasPriceFrom){
			airConditionerList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new AirConditionerMapper());
		}
		
		return airConditionerList;
	}
	
	@Override
	public AirConditioner getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.air_conditioner"
				+ " on ecommerce.product.id = ecommerce.air_conditioner.productID"
				+ " where product.id = ?";
		return (AirConditioner) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new AirConditionerMapper());
	}
	
	@Override
	public AirConditioner getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.air_conditioner"
				+ " on ecommerce.product.id = ecommerce.air_conditioner.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new AirConditionerMapper());
	}

	@Override
	public void add(AirConditioner airConditioner) {
		String query = "Call sp_AddAirConditioner(?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				airConditioner.getName(),
				airConditioner.getPrice(),
				airConditioner.getQuantity(),
				airConditioner.getImage(),
				airConditioner.getBrand(),
				airConditioner.getDescription(),
				airConditioner.getCountryOfOrigin(),
				airConditioner.getWeight(),
				airConditioner.getRoomVolume(),
				airConditioner.getColdCapacity(),
				airConditioner.getGas());
	}

	@Override
	public void update(AirConditioner airConditioner) {
		String query = "Call sp_UpdateAirConditioner(?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				airConditioner.getName(),
				airConditioner.getPrice(),
				airConditioner.getQuantity(),
				airConditioner.getImage(),
				airConditioner.getBrand(),
				airConditioner.getDescription(),
				airConditioner.getCountryOfOrigin(),
				airConditioner.getWeight(),
				airConditioner.getRoomVolume(),
				airConditioner.getColdCapacity(),
				airConditioner.getGas(),
				airConditioner.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class AirConditionerMapper implements RowMapper<AirConditioner> {
		
		public AirConditioner mapRow(ResultSet resultSet, int param) throws SQLException {
			return new AirConditioner(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getDouble("weight"),
					resultSet.getString("roomVolume"),
					resultSet.getString("coldCapacity"),
					resultSet.getString("gas"));
		}
		
	}

}