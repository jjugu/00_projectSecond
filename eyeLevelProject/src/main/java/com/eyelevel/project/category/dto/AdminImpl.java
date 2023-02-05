package com.eyelevel.project.category.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.eyelevel.project.category.entity.Admin;

public class AdminImpl extends User{
	
	private int adminCode;
	private String adminId;
	private String adminPw;
	private String adminName;

	public AdminImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(Admin admin) {
		this.adminCode = admin.getAdminCode();
		this.adminId = admin.getAdminId();
		this.adminPw = "[PROTECTED]";
		this.adminName = admin.getAdminName();
	}


	public int getAdminCode() {
		return adminCode;
	}

	public String getAdminId() {
		return adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public String getAdminName() {
		return adminName;
	}

	@Override
	public String toString() {
		return super.toString() + "AdminImpl [adminCode=" + adminCode + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + "]";
	}
}