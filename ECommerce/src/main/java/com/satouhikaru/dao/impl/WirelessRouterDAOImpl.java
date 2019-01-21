package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.WirelessRouterDAO;
import com.satouhikaru.entity.WirelessRouter;
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
public class WirelessRouterDAOImpl extends BaseDAO implements WirelessRouterDAO {

	
	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.product"
				+ " join ecommerce.wireless_router"
				+ " on ecommerce.product.id = ecommerce.wireless_router.productID"
				+ " where product.productType = 'Bộ định tuyến'";
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
				+ " join ecommerce.wireless_router on ecommerce.product.id = ecommerce.wireless_router.productID"
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
	public List<WirelessRouter> getPerPage(long startRow, long maxRows) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.wireless_router"
				+ " on ecommerce.product.id = ecommerce.wireless_router.productID"
				+ " where product.productType = 'Bộ định tuyến'" +
				" order by product.id desc limit ?, ?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new WirelessRouterMapper());
	}

	@Override
	public List<WirelessRouter> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		List<WirelessRouter> wirelessRouterList = null;

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
				+ " join ecommerce.wireless_router on ecommerce.product.id = ecommerce.wireless_router.productID"
				+ " join ecommerce.brand on ecommerce.product.brandID = ecommerce.brand.id"
				+ sql + " order by product.id desc limit ?, ?";

		if ((hasKeySearch && hasBrand && hasPriceFrom) || (hasKeySearch && hasPriceFrom)) {
			wirelessRouterList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", priceFrom, priceTo, startRow, maxRows }, new WirelessRouterMapper());
		} else if (hasKeySearch) {
			wirelessRouterList = getJdbcTemplate().query(query, new Object[] {
					"%" + keySearch + "%", startRow, maxRows }, new WirelessRouterMapper());
		} else if (hasBrand) {
			wirelessRouterList = getJdbcTemplate().query(query, new Object[] {
					startRow, maxRows }, new WirelessRouterMapper());
		} else if (hasPriceFrom){
			wirelessRouterList = getJdbcTemplate().query(query, new Object[] {
					priceFrom, priceTo, startRow, maxRows }, new WirelessRouterMapper());
		} else if (!hasPriceFrom){
			wirelessRouterList = getJdbcTemplate().query(query, new Object[] {
					priceTo, startRow, maxRows }, new WirelessRouterMapper());
		}
		
		return wirelessRouterList;
	}
	
	@Override
	public WirelessRouter getById(long id) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.wireless_router"
				+ " on ecommerce.product.id = ecommerce.wireless_router.productID"
				+ " where product.id = ?";
		return (WirelessRouter) getJdbcTemplate().queryForObject(query, new Object[] { id },
				new WirelessRouterMapper());
	}
	
	@Override
	public WirelessRouter getByName(String name) {
		String query = "select * from ecommerce.product"
				+ " join ecommerce.wireless_router"
				+ " on ecommerce.product.id = ecommerce.wireless_router.productID"
				+ " where product.name = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { name },
				new WirelessRouterMapper());
	}
	@Override
	public void add(WirelessRouter wirelessRouter) {
		String query = "Call sp_AddWirelessRouter(?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				wirelessRouter.getName(),
				wirelessRouter.getPrice(),
				wirelessRouter.getQuantity(),
				wirelessRouter.getImage(),
				wirelessRouter.getBrand(),
				wirelessRouter.getDescription(),
				wirelessRouter.getCountryOfOrigin());
	}

	@Override
	public void update(WirelessRouter wirelessRouter) {
		String query = "Call sp_UpdateWirelessRouter(?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(query,
				wirelessRouter.getName(),
				wirelessRouter.getPrice(),
				wirelessRouter.getQuantity(),
				wirelessRouter.getImage(),
				wirelessRouter.getBrand(),
				wirelessRouter.getDescription(),
				wirelessRouter.getCountryOfOrigin(),
				wirelessRouter.getId());
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.product where id = ?";
		getJdbcTemplate().update(query, id);
	}
	
	private static final class WirelessRouterMapper implements RowMapper<WirelessRouter> {
		
		public WirelessRouter mapRow(ResultSet resultSet, int param) throws SQLException {
			return new WirelessRouter(resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getString("productType"),
					resultSet.getDouble("price"),
					resultSet.getInt("quantity"),
					resultSet.getString("image"),
					resultSet.getInt("brandID"),
					resultSet.getString("description"),
					resultSet.getString("countryOfOrigin"));
		}
		
	}
	
}