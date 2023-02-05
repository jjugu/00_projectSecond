package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "TeacherProfile")
@Table(name = "TEACHER_PROFILE")
public class TeacherProfile {

	@Id
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@Column(name = "TEACHER_NAME")
	private String teacherName;
	
	@Column(name = "TEACHER_PICTURE")
	private String teacherPicture;

	@Column(name = "TEACHER_ADDRESS")
	private String teacherAddress;
	
	@Column(name = "TEACHER_PHONE")
	private String teacherPhone;
	
	@Column(name = "TEACHER_EMAIL")
	private String teacherEmail;
	
	@Column(name = "TEACHER_ID")
	private String teacherId;
	
	@Column(name = "TEACHER_PW")
	private String teacherPw;
	
	@Column(name = "TEACHER_ENT")
	private String teacherEnt;
	
	@Column(name = "TEACHER_LEVEL")
	private String teacherLevel;
	
	@Column(name = "TEACHER_PR")
	private String teacherPr;

	public TeacherProfile() {
	}

	public TeacherProfile(String teacherNo, String teacherName, String teacherPicture, String teacherAddress,
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
		return "TeacherProfileDTO [teacherNo=" + teacherNo + ", teacherName=" + teacherName 
				+ ", teacherId=" + teacherId + "]";
	}
}