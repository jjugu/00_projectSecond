package com.eyelevel.project.category.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "StudentProfile")
@Table(name = "STUDENT_PROFILE")
public class StudentProfile {

	@Id
	@Column(name = "STUDENT_NO")
	private Long studentNo;

	@Column(name = "STUDENT_NAME")
	private String studentName;

	@Column(name = "STUDENT_ID")
	private String studentId;

	@Column(name = "STUDENT_PW")
	private String studentPw;

	@Column(name = "STUDENT_GENDER")
	private String studentGender;

	@Column(name = "STUDENT_PHONE")
	private String studentPhone;

	@Column(name = "STUDENT_ADDRESS")
	private String studentAddress;

	@Column(name = "STUDENT_REGIST")
	private Date studentRegist;

	@Column(name = "STUDENT_MEMO")
	private String studentMemo;

	@Column(name = "STUDENT_STATUS")
	private String studentStatus;

	@Column(name = "AREA_NO")
	private Long areaNo;

	public StudentProfile() {
	}

	public StudentProfile(Long studentNo, String studentName, String studentId, String studentPw, String studentGender,
			String studentPhone, String studentAddress, Date studentRegist, String studentMemo, String studentStatus,
			Long areaNo) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.studentRegist = studentRegist;
		this.studentMemo = studentMemo;
		this.studentStatus = studentStatus;
		this.areaNo = areaNo;
	}

	public Long getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Long studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentPw() {
		return studentPw;
	}

	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Date getStudentRegist() {
		return studentRegist;
	}

	public void setStudentRegist(Date studentRegist) {
		this.studentRegist = studentRegist;
	}

	public String getStudentMemo() {
		return studentMemo;
	}

	public void setStudentMemo(String studentMemo) {
		this.studentMemo = studentMemo;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public Long getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(Long areaNo) {
		this.areaNo = areaNo;
	}

	@Override
	public String toString() {
		return "StudentProfile [studentNo=" + studentNo + ", studentName=" + studentName + ", studentId=" + studentId
				+ ", studentPw=" + studentPw + ", studentGender=" + studentGender + ", studentPhone=" + studentPhone
				+ ", studentAddress=" + studentAddress + ", studentRegist=" + studentRegist + ", studentMemo="
				+ studentMemo + ", studentStatus=" + studentStatus + ", areaNo=" + areaNo + "]";
	}
}