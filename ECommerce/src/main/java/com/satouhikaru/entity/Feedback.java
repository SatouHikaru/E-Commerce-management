package com.satouhikaru.entity;

import java.sql.Date;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Feedback {

	private long id;
	private String title;
	private String content;
	private Date dateCreated;
	private Customer customer;

	public Feedback() {
	}
	
	public Feedback(long id, String title, String content, Date dateCreated) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.dateCreated = dateCreated;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}