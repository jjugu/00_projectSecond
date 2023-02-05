package com.eyelevel.project.category.dto;

public class TeacherProfileDTO {

	private String teacherNo;
	private String teacherName;
	private String teacherPicture;
	private String teacherAddress;
	private String teacherPhone;
	private String teacherEmail;
	private String teacherId;
	private String teacherPw;
	private String teacherEnt;
	private String teacherLevel;
	private String teacherPr;
	
	public TeacherProfileDTO() {
	}

	public TeacherProfileDTO(String teacherNo, String teacherName, String teacherPicture, String teacherAddress,
			String teacherPhone, String teacherEmail, String teacherId, String teacherPw, String teacherEnt,
			String teacherLevel, String teacherPr) {
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.teacherPicture = teacherPicture;
		this.teacherAddress = teacherAddress;
		this.teacherPhone = teacherPhone;
		this.teacherEmail = teacherEmail;
		this.teacherId = teacherId;
		this.teacherPw = teacherPw;
		this.teacherEnt = teacherEnt;
		this.teacherLevel = teacherLevel;
		this.teacherPr = teacherPr;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPicture() {
		return teacherPicture;
	}

	public void setTeacherPicture(String teacherPicture) {
		this.teacherPicture = teacherPicture;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherPw() {
		return teacherPw;
	}

	public void setTeacherPw(String teacherPw) {
		this.teacherPw = teacherPw;
	}

	public String getTeacherEnt() {
		return teacherEnt;
	}

	public void setTeacherEnt(String teacherEnt) {
		this.teacherEnt = teacherEnt;
	}

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public String getTeacherPr() {
		return teacherPr;
	}

	public void setTeacherPr(String teacherPr) {
		this.teacherPr = teacherPr;
	}

	@Override
	public String toString() {
		return "TeacherProfileDTO [teacherNo=" + teacherNo + ", teacherName=" + teacherName + ", teacherPicture="
				+ teacherPicture + ", teacherAddress=" + teacherAddress + ", teacherPhone=" + teacherPhone
				+ ", teacherEmail=" + teacherEmail + ", teacherId=" + teacherId + ", teacherPw=" + teacherPw
				+ ", teacherEnt=" + teacherEnt + ", teacherLevel=" + teacherLevel + ", teacherPr=" + teacherPr + "]";
	}
}