package com.satouhikaru.entity;

import java.sql.Date;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Employee extends User {

	public Employee() {
	}

	public Employee(String name, String gender, Date birthday, String phone,
					String email, String userName, String avatar) {
		super(name, gender, birthday, phone, email, userName, avatar);
	}
	
	public Employee(long id, String name, String gender, Date birthday, String phone,
			String email, String userName, String avatar) {
		super(id, name, gender, birthday, phone, email, userName, avatar);
	}

}