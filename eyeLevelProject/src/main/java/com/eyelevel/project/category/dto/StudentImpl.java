package com.eyelevel.project.category.dto;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.eyelevel.project.category.entity.StudentProfile;

/* 스프링 시큐리티의 principal 객체에서 더 구체적인 정보를 얻기 위해 확장된 User 객체 생성용 클래스*/
public class StudentImpl extends User {

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
	
	
	public StudentImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public void setDetails(StudentProfile student) {
		this.studentNo = student.getStudentNo();
		this.studentName = student.getStudentName();
		this.studentId = student.getStudentId();
		this.studentPw = "[PROTECTED]";
		this.studentGender = student.getStudentGender();
		this.studentAddress = student.getStudentAddress();
		this.studentPhone = student.getStudentPhone();
		this.studentRegist = student.getStudentRegist();
		this.studentMemo = student.getStudentMemo();
		this.studentStatus = student.getStudentStatus();
		this.areaNo = student.getAreaNo();
	}

	public Long getStudentNo() {
		return studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getStudentPw() {
		return studentPw;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public Date getStudentRegist() {
		return studentRegist;
	}

	public String getStudentMemo() {
		return studentMemo;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public Long getAreaNo() {
		return areaNo;
	}

	@Override
	public String toString() {
		return "StudentImpl [studentNo=" + studentNo + ", studentName=" + studentName + ", studentId=" + studentId
				+ ", studentPw=" + studentPw + ", studentGender=" + studentGender + ", studentAddress=" + studentAddress
				+ ", studentPhone=" + studentPhone + ", studentRegist=" + studentRegist + ", studentMemo=" + studentMemo
				+ ", studentStatus=" + studentStatus + ", areaNo=" + areaNo + "]";
	}
}