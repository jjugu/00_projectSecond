package com.eyelevel.project.category.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentProfileDTO {
	
	private Long studentNo;
	private String studentName;
	private String studentId;
	private String studentPw;
	private String studentGender;
	private String studentAddress;
	private String studentPhone;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date studentRegist;
	private String studentMemo;
	private String studentStatus;
	private Long areaNo;
	
	public StudentProfileDTO() {
	}

	public StudentProfileDTO(Long studentNo, String studentName, String studentId, String studentPw,
			String studentGender, String studentAddress, String studentPhone, Date studentRegist, String studentMemo,
			String studentStatus, Long areaNo) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.studentGender = studentGender;
		this.studentAddress = studentAddress;
		this.studentPhone = studentPhone;
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

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
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
		return "StudentProfileDTO [studentNo=" + studentNo + ", studentName=" + studentName + ", studentId=" + studentId
				+ ", studentPw=" + studentPw + ", studentGender=" + studentGender + ", studentAddress=" + studentAddress
				+ ", studentPhone=" + studentPhone + ", studentRegist=" + studentRegist + ", studentMemo=" + studentMemo
				+ ", studentStatus=" + studentStatus + ", areaNo=" + areaNo + "]";
	}
}