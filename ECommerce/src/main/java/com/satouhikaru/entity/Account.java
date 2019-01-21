package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Account {

	private String userName;
	private String password;
	private String avatar;
	private Role role;
	private int roleID;
	private String roleName;

	public Account() {
	}

	public Account(String userName, String avatar, String roleName) {
		this.userName = userName;
		this.avatar = avatar;
		this.roleName = roleName;
	}

	public Account(String userName, String password, String avatar, int roleID) {
		this.userName = userName;
		this.password = password;
		this.avatar = avatar;
		this.roleID = roleID;
	}
	
	public Account(String userName, String password, String avatar, int roleID, String roleName) {
		this.userName = userName;
		this.password = password;
		this.avatar = avatar;
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}