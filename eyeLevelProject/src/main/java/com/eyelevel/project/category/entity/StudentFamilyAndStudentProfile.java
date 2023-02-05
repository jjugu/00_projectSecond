package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT_FAMILY")
@SequenceGenerator(
		name = "FAMILYANDSTUDENT_SEQ_GENERATOR",
		sequenceName = "SEQ_FAMILYANDSTUDENT_NO",
		initialValue = 1,
		allocationSize = 1
		)
public class StudentFamilyAndStudentProfile {

	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "FAMILYANDSTUDENT_SEQ_GENERATOR"
	)
	@Column(name = "FAMILY_NO")
	private Long familyNo;
	
	@Column(name = "FAMILY_REL")
	private String familyRel;
	
	@Column(name = "FAMILY_PHONE")
	private String familyPhone;
	
	@Column(name = "FAMILY_AGE")
	private String familyAge;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private StudentProfileAndStudentFamily studentNo;
	
	public StudentFamilyAndStudentProfile() {}

	public StudentFamilyAndStudentProfile(Long familyNo, String familyRel, String familyPhone, String familyAge,
			StudentProfileAndStudentFamily studentNo) {
		super();
		this.familyNo = familyNo;
		this.familyRel = familyRel;
		this.familyPhone = familyPhone;
		this.familyAge = familyAge;
		this.studentNo = studentNo;
	}

	public Long getFamilyNo() {
		return familyNo;
	}

	public void setFamilyNo(Long familyNo) {
		this.familyNo = familyNo;
	}

	public String getFamilyRel() {
		return familyRel;
	}

	public void setFamilyRel(String familyRel) {
		this.familyRel = familyRel;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getFamilyAge() {
		return familyAge;
	}

	public void setFamilyAge(String familyAge) {
		this.familyAge = familyAge;
	}

	public StudentProfileAndStudentFamily getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(StudentProfileAndStudentFamily studentNo) {
		this.studentNo = studentNo;
	}

	@Override
	public String toString() {
		return "StudentFamilyAndStudentProfile [familyNo=" + familyNo + ", familyRel=" + familyRel + ", familyPhone="
				+ familyPhone + ", familyAge=" + familyAge + ", studentNo=" + studentNo.getStudentAddress() + "]";
	}

	
}
