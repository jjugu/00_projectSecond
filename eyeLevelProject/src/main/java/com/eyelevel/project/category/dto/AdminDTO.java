package com.eyelevel.project.category.dto;

public class AdminDTO {
	
	private int adminCode;
	private String adminId;
	private String adminPw;
	private String adminName;
	
	public AdminDTO() {}

	public AdminDTO(int adminCode, String adminId, String adminPw, String adminName) {
		super();
		this.adminCode = adminCode;
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminName = adminName;
	}

	public int getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(int adminCode) {
		this.adminCode = adminCode;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "AdminDTO [adminCode=" + adminCode + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + "]";
	}
}