package com.gravygo.model;

// POJO Class/Model Class
// i.e., a class matching with the columns with the table in database, having all private members along with constructors, setters, getters, etc.

public class User 
{
	private int userId;
	private String username;
	private String email;
	private String phoneNumber;
	private String password;
	private String address;
//	private String createdDate;
//	private String lastLogin;

	// zero param constructor
	public User()
	{
		super();
	}

	// param constructor
	public User(int userId, String username, String email, String phoneNumber, String password, String address)
	{
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
	}

	// param constructor (without userId)
	public User(String username, String email, String phoneNumber, String password, String address)
	{
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
	}

	// setters and getters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
//	public String getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public String getLastLogin() {
//		return lastLogin;
//	}
//
//	public void setLastLogin(String lastLogin) {
//		this.lastLogin = lastLogin;
//	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", username=" + username + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", password=" + password + "]";
	}
	
}
