package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.DVDPlayerDAO;
import com.satouhikaru.entity.DVDPlayer;
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
public class DVDPlayerDAOImpl extends BaseDAO implements DVDPlayerDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.dvd_player"
				+ " on ecommerce.product.id = ecommerce.dvd_player.productID"
				+ " where product.productType = 'Đầu đĩa DVD'";
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
				+ " join ecommerce.dvd_player on ecommerce.product.id = ecommerce.dvd_player.productID"
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
	public List<DVDPlayer> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.dvd_player"
				+ " on ecommerce.product.id = ecommerce.dvd_player.productID"
				+ " where product.productType = 'Đầu đĩa DVD'" +
				" order by product.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new DVDPlayerMapper());
	}

	@Override
	public List<DVDPlayer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
										long startRow, long maxRows) {
		List<DVDPlayer> dvdPlayerList = null;

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
				+ " join ecommerce.dvd_player on ecommerce.product.id = ecommerce.dvd_player.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			dvdPlayerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new DVDPlayerMapper());
		} else if (hasKeySearch) {
			dvdPlayerList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new DVDPlayerMapper());
		} else if (hasBrand) {
			dvdPlayerList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new DVDPlayerMapper());
		} else if (hasPriceFrom){
			dvdPlayerList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new DVDPlayerMapper());
		} else if (!hasPriceFrom){
			dvdPlayerList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new DVDPlayerMapper());
		}
		
		return dvdPlayerList;
	}

	@Override
	public DVDPlayer getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.dvd_player"
				+ " on ecommerce.product.id = ecommerce.dvd_player.productID"
				+ " where product.id = ?";
		return (DVDPlayer) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new DVDPlayerMapper());
	}
	
	@Override
	public DVDPlayer getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.dvd_player"
				+ " on ecommerce.product.id = ecommerce.dvd_player.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new DVDPlayerMapper());
	}

	@Override
	public void add(DVDPlayer dvdPlayer) {
		String query = "Call sp_AddDVDPlayer (?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				dvdPlayer.getName(),
				dvdPlayer.getPrice(),
				dvdPlayer.getQuantity(),
				dvdPlayer.getImage(),
				dvdPlayer.getBrand(),
				dvdPlayer.getDescription(),
				dvdPlayer.getCountryOfOrigin(),
				dvdPlayer.getUsb(),
				dvdPlayer.getWeight(),
				dvdPlayer.getSize());
	}

	@Override
	public void update(DVDPlayer dvdPlayer) {
		String query = "Call sp_UpdateDVDPlayer (?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				dvdPlayer.getName(),
				dvdPlayer.getPrice(),
				dvdPlayer.getQuantity(),
				dvdPlayer.getImage(),
				dvdPlayer.getBrand(),
				dvdPlayer.getDescription(),
				dvdPlayer.getCountryOfOrigin(),
				dvdPlayer.getUsb(),
				dvdPlayer.getWeight(),
				dvdPlayer.getSize(),
				dvdPlayer.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class DVDPlayerMapper implements RowMapper<DVDPlayer> {
		
		public DVDPlayer mapRow(ResultSet resultSet, int param) throws SQLException {
			return new DVDPlayer(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"),
					resultSet.getString("usb"),
					resultSet.getDouble("weight"),
					resultSet.getString("size"));
		}
		
	}
	
}