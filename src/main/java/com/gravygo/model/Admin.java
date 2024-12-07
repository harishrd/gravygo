package com.gravygo.model;

public class Admin 
{
	private int adminId;
	private String adminName;
	
	
	public Admin() {
		super();
	}

	public Admin(int adminId, String adminName) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
	}

	public Admin(String adminName) {
		super();
		this.adminName = adminName;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + "]";
	}
}
