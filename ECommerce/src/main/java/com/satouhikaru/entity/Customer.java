package com.satouhikaru.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Customer extends User {

	private Set<Order> orderList = new HashSet<>();
	private Set<Feedback> feedbackList = new HashSet<>();
	
	public Customer() {
	}

	public Customer(String name, String gender, Date birthday, String phone,
			String email, String userName, String avatar) {
		super(name, gender, birthday, phone, email, userName, avatar);
	}
	
	public Customer(long id, String name, String gender, Date birthday, String phone,
			String email, String userName, String avatar) {
		super(id, name, gender, birthday, phone, email, userName, avatar);
	}

	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}

	public Set<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(Set<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

}