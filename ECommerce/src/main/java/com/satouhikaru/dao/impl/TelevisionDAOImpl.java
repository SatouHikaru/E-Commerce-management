package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.TelevisionDAO;
import com.satouhikaru.entity.Television;
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
public class TelevisionDAOImpl extends BaseDAO implements TelevisionDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product" +
				" join ecommerce.television" +
				" on ecommerce.product.id = ecommerce.television.productID" +
				" where product.productType = 'Ti vi'";
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
				+ " join ecommerce.television on ecommerce.product.id = ecommerce.television.productID"
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
	public List<Television> getPerPage(long startRow, long maxRows) {
		String query = "select product.id, product.name, image, productType, price, quantity, brandID," +
				" description, countryOfOrigin, screenSize, screenResolution, imageQuality" +
				" from ecommerce.product" +
				" join ecommerce.television on ecommerce.product.id = ecommerce.television.productID" +
				" where product.productType = 'Ti vi' " +
				" order by id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new TelevisionMapper());
	}

	@Override
	public List<Television> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		List<Television> televisionList = null;

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
		
		String query = "select product.id, product.name, image, productType, price, quantity, brandID," +
				" description, countryOfOrigin, screenSize, screenResolution, imageQuality" +
				" from ecommerce.product" +
				" join ecommerce.television on ecommerce.product.id = ecommerce.television.productID" +
				" join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id" + sql +
				" order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			televisionList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new TelevisionMapper());
		} else if (hasKeySearch) {
			televisionList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new TelevisionMapper());
		} else if (hasBrand) {
			televisionList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new TelevisionMapper());
		} else if (hasPriceFrom){
			televisionList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new TelevisionMapper());
		} else if (!hasPriceFrom){
			televisionList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new TelevisionMapper());
		}
		
		return televisionList;
	}

	@Override
	public Television getById(long id) {
		String query = "select product.id, product.name, image, productType, price, quantity, brandID," +
				" description, countryOfOrigin, screenSize, screenResolution, imageQuality" +
				" from ecommerce.product" +
				" join ecommerce.television on ecommerce.product.id = ecommerce.television.productID" +
				" where product.id = ?";
		return (Television) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new TelevisionMapper());
	}
	
	@Override
	public Television getByName(String name) {
		String query = "select product.id, product.name, image, productType, price, quantity, brandID," +
				" description, countryOfOrigin, screenSize, screenResolution, imageQuality" +
				" from ecommerce.product" +
				" join ecommerce.television on ecommerce.product.id = ecommerce.television.productID" +
				" where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new TelevisionMapper());
	}

	@Override
	public void add(Television television) {
		String query = "Call sp_AddTelevision(?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				television.getName(),
				television.getPrice(),
				television.getQuantity(),
				television.getImage(),
				television.getBrandID(),
				television.getDescription(),
				television.getCountryOfOrigin(),
				television.getScreenSize(),
				television.getScreenResolution(),
				television.getImageQuality());
	}

	@Override
	public void update(Television television) {
		String query = "Call sp_UpdateTelevision(?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				television.getName(),
				television.getPrice(),
				television.getQuantity(),
				television.getImage(),
				television.getBrandID(),
				television.getDescription(),
				television.getCountryOfOrigin(),
				television.getId(),
				television.getScreenSize(),
				television.getScreenResolution(),
				television.getImageQuality());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class TelevisionMapper implements RowMapper<Television> {
		
		public Television mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Television(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getFloat("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getString("screenSize"),
					resultSet.getString("screenResolution"),
					resultSet.getString("imageQuality"));
		}
		
	}
	
}