package com.satouhikaru.entity;

import java.sql.Date;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class User {

	private long id;
	private String name;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	private Account account;
	private String userName;
	private String avatar;

	public User() {
	}

	public User(String name, String gender, Date birthday, String phone,
			String email, String userName, String avatar) {
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.avatar = avatar;
	}
	
	public User(long id, String name, String gender, Date birthday, String phone,
			String email, String userName, String avatar) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.avatar = avatar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}