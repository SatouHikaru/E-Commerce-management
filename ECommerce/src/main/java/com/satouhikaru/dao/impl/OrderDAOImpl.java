package com.satouhikaru.dao.impl;

import com.satouhikaru.dao.OrderDAO;
import com.satouhikaru.entity.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Tran Thi Hoa Nhu
 * @since  17/11/2018
 */
@Component
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

	@Override
	public long countAll() {
		String query = "select count(*) from ecommerce.order";
		return getJdbcTemplate().queryForObject(query, Long.class);
	}

	@Override
	public List<Order> getPerPage(long startRow, long maxRows) {
		String query = "SELECT order.id, dateCreated, status, order.customerID, name" +
				" FROM ecommerce.order" +
				" JOIN ecommerce.user ON ecommerce.order.customerID = ecommerce.user.id" +
				" ORDER BY id DESC LIMIT ?,?";
		return getJdbcTemplate().query(query, new Object[] { startRow, maxRows },
				new OrderMapper());
	}

	@Override
	public List<Order> getList(long customerID) {
		String query = "SELECT order.id, dateCreated, status, order.customerID, name" +
				" FROM ecommerce.order" +
				" JOIN ecommerce.user ON ecommerce.order.customerID = ecommerce.user.id" +
				" WHERE status = 'Đặt hàng' AND customerID = ?";
		return getJdbcTemplate().query(query, new Object[] { customerID },
				new OrderMapper());
	}

	@Override
	public Order getById(long id) {
		String query = "SELECT order.id, dateCreated, status, order.customerID, name" +
				" FROM ecommerce.order" +
				" JOIN ecommerce.user ON ecommerce.order.customerID = ecommerce.user.id" +
				" WHERE order.id = ?";
		return getJdbcTemplate().queryForObject(query, new Object[] { id },
				new OrderMapper());
	}

	@Override
	public List<Order> getOrderDetail(long id) {
		String query = "select * from ecommerce.order_detail"
				+ " join ecommerce.order"
				+ " on ecommerce.order.id = ecommerce.order_detail.orderID"
				+ " where id = ?";
		return getJdbcTemplate().query(query, new Object[] { id },
				new OrderDetailMapper());
	}

	@Override
	public Order getByCustomerID(long customerID) {
		Order order = null;
		String query = "SELECT order.id, dateCreated, status, order.customerID, name" +
				" FROM ecommerce.order" +
				" JOIN ecommerce.user ON ecommerce.order.customerID = ecommerce.user.id" +
				" WHERE status = 'Đặt hàng' and customerID = ?";
		try {
			order = getJdbcTemplate().queryForObject(query, new Object[]{customerID},
					new OrderMapper());
		} catch (DataAccessException e) {
			order = null;
		} finally {
			return order;
		}
	}

	@Override
	public void add(Order order) {
		String query = "Call sp_AddProductToOrder(?,?,?,?)";
		getJdbcTemplate().update(query,
				order.getDateCreated(),
				order.getCustomerID(),
				order.getProductID(),
				order.getQuantity());
	}

	@Override
	public void addNewProductToOrder(Order order) {
		String query = "Call sp_AddNewProductToOrder(?,?,?)";
		getJdbcTemplate().update(query,
				order.getId(),
				order.getProductID(),
				order.getQuantity());
	}

	@Override
	public void update(Order order) {
		String query = "update ecommerce.order_detail" +
				" set quantity = ? where orderID = ? and productID = ?";
		getJdbcTemplate().update(query,
				order.getQuantity(),
				order.getId(),
				order.getProductID());
	}

	@Override
	public void checkout(Order order) {
		String query = "update ecommerce.order set status = 'Đã thanh toán'" +
				" where id = ? and customerID = ?";
		getJdbcTemplate().update(query, order.getId(), order.getCustomerID());
	}

	@Override
	public void remove(long orderID, long productID, int quantity) {
		String query = "call sp_DeleteProductFromOrder(?,?,?)";
		getJdbcTemplate().update(query, orderID, productID, quantity);
	}

	@Override
	public void delete(long id) {
		String query = "delete from ecommerce.order where id = ?";
		getJdbcTemplate().update(query, id);
	}

	private static final class OrderMapper implements RowMapper<Order> {

		public Order mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Order(resultSet.getLong("id"),
					resultSet.getDate("dateCreated"),
					resultSet.getString("status"),
					resultSet.getLong("customerID"),
					resultSet.getString("name"));
		}

	}

	private static final class OrderDetailMapper implements RowMapper<Order> {

		public Order mapRow(ResultSet resultSet, int param) throws SQLException {
			return new Order(resultSet.getLong("id"),
					resultSet.getDate("dateCreated"),
					resultSet.getString("status"),
					resultSet.getLong("customerID"),
					resultSet.getLong("productID"),
					resultSet.getInt("quantity"));
		}

	}

}