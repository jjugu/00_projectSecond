package com.eyelevel.project.category.entity;

import javax.persistence.*;

@Entity
@Table(name="STUDENT_FAMILY")
public class StudentFamily {
	
	@Id
	@Column(name = "FAMILY_NO")
	private Long familyNo;
	
	@Column(name = "FAMILY_REL")
	private String familyRel;
	
	@Column(name = "FAMILY_PHONE")
	private String familyPhone;
	
	@Column(name = "FAMILY_AGE")
	private String familyAge;
	
	
	@Column(name = "STUDENT_NO")
	private Long studentNo;
	
	public StudentFamily() {}

	public StudentFamily(Long familyNo, String familyRel, String familyPhone, String familyAge, Long studentNo) {
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

	public Long getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Long studentNo) {
		this.studentNo = studentNo;
	}

	@Override
	public String toString() {
		return "StudentFamily [familyNo=" + familyNo + ", familyRel=" + familyRel + ", familyPhone=" + familyPhone
				+ ", familyAge=" + familyAge + ", studentNo=" + studentNo + "]";
	}

	
	

}
