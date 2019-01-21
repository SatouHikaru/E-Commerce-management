package com.satouhikaru.entity;

import java.sql.Date;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Order {

	private long id;
	private Date dateCreated;
	private String status;
	private long customerID;
	private String customerName;
	private List<Product> productList;
	private long productID;
	private int quantity;

	public Order() {
	}

	public Order(Date dateCreated, String status, long customerID) {
		this.dateCreated = dateCreated;
		this.status = status;
		this.customerID = customerID;
	}
	
	public Order(long id, Date dateCreated, String status, long customerID) {
		this.id = id;
		this.dateCreated = dateCreated;
		this.status = status;
		this.customerID = customerID;
	}

	public Order(long id, Date dateCreated, String status, long customerID, String customerName) {
		this.id = id;
		this.dateCreated = dateCreated;
		this.status = status;
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public Order(long id, Date dateCreated, String status, long customerID,
			long productID, int quantity) {
		this.id = id;
		this.dateCreated = dateCreated;
		this.status = status;
		this.customerID = customerID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}