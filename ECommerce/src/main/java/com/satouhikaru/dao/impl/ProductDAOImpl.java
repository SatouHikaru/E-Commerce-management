package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.ProductDAO;
import com.satouhikaru.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Component
public class ProductDAOImpl extends BaseDAO implements ProductDAO {
	
	@Override
	public List<Product> getNewProduct() {
		String query = "select * from ("
				+ "SELECT * FROM ecommerce.product ORDER BY id DESC LIMIT 6"
				+ ") product"
				+ " ORDER BY id ASC";
		return getJdbcTemplate().query(query, new ProductMapper());
	}

	@Override
	public List<Product> getOrder(long orderID, long customerID) {
		String query = "SELECT product.id, product.name, product.productType,"
				+ " product.price, order_detail.quantity, product.image, product.brandID,"
				+ " product.description, product.countryOfOrigin"
				+ " FROM ecommerce.product "
				+ " join ecommerce.order_detail on product.id = ecommerce.order_detail.productID"
				+ " join ecommerce.order on order.id = ecommerce.order_detail.orderID"
				+ " where orderID = ? and customerID = ?";
		return getJdbcTemplate().query(query, new Object[] {
				orderID, customerID }, new ProductMapper());
	}

	@Override
	public List<Product> statistic(Date startDate, Date endDate) {
		String query = "SELECT product.id, product.name, product.productType," +
				" product.price, order_detail.quantity, product.image, product.brandID," +
				" product.description, product.countryOfOrigin" +
				" FROM ecommerce.product " +
				" join ecommerce.order_detail on product.id = ecommerce.order_detail.productID" +
				" join ecommerce.order on order.id = ecommerce.order_detail.orderID" +
				" where dateCreated between ? and ?";
		return getJdbcTemplate().query(query, new Object[] {
				startDate, endDate }, new ProductMapper());
	}
	
	private static final class ProductMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Product(resultSet.getLong("id"),
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