package com.eyelevel.project.category.entity;

import javax.persistence.*;


@Entity(name="Admin")
@Table(name = "ADMIN")
@SequenceGenerator(
		name = "ADMIN_SEQ_GENERATOR",
		sequenceName = "SEQ_STUDENT_NO",
		initialValue = 1,
		allocationSize = 1
)
public class Admin {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ADMIN_SEQ_GENERATOR"
	)
	@Column(name = "ADMIN_CODE")
	private int adminCode;
	
	@Column(name = "ADMIN_ID")
	private String adminId;
	
	@Column(name = "ADMIN_PW")
	private String adminPw;
	
	@Column(name = "ADMIN_NAME")
	private String adminName;
	
	public Admin() {}

	public Admin(int adminCode, String adminId, String adminPw, String adminName) {
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
		return "Admin [adminCode=" + adminCode + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + "]";
	}

}
