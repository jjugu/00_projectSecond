package com.eyelevel.project.category.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_PROFILE")
@SequenceGenerator(
		name = "STUDENTANDFAMILY_SEQ_GENERATOR",
		sequenceName = "SEQ_STUDENTANDFAMILY_NO",
		initialValue = 1,
		allocationSize = 1
)
public class StudentProfileAndStudentFamily {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "STUDENTANDFAMILY_SEQ_GENERATOR"
	)
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
	
	@Column(name = "STUDENT_ADDRESS")
	private String studentAddress;
	
	@Column(name = "STUDENT_PHONE")
	private String studentPhone;
	
	@Column(name = "STUDENT_REGIST")
	private Date studentRegist;
	
	@Column(name = "STUDENT_MEMO")
	private String studentMemo;
	
	@Column(name = "STUDENT_STATUS")
	private String studentStatus;
	
	@Column(name = "AREA_NO")
	private int areaNo;

	@OneToMany(mappedBy = "studentNo")
	private List<StudentFamilyAndStudentProfile> familyList = new ArrayList<>();
	
	public StudentProfileAndStudentFamily() {}

	public StudentProfileAndStudentFamily(Long studentNo, String studentName, String studentId, String studentPw,
			String studentGender, String studentAddress, String studentPhone, Date studentRegist, String studentMemo,
			String studentStatus, int areaNo, List<StudentFamilyAndStudentProfile> familyList) {
		super();
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
		this.familyList = familyList;
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

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public List<StudentFamilyAndStudentProfile> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(List<StudentFamilyAndStudentProfile> familyList) {
		this.familyList = familyList;
	}

	@Override
	public String toString() {
		return "StudentProfileAndStudentFamily [studentNo=" + studentNo + ", studentName=" + studentName
				+ ", studentId=" + studentId + ", studentPw=" + studentPw + ", studentGender=" + studentGender
				+ ", studentAddress=" + studentAddress + ", studentPhone=" + studentPhone + ", studentRegist="
				+ studentRegist + ", studentMemo=" + studentMemo + ", studentStatus=" + studentStatus + ", areaNo="
				+ areaNo + ", familyList=" + familyList + "]";
	}

	
}