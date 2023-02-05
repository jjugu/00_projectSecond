package com.eyelevel.project.category.dto;

public class StudentFamilyDTO {

	private Long familyNo;
	private String familyRel;
	private String familyPhone;
	private String familyAge;
	private Long studentNo;
	
	public StudentFamilyDTO() {}

	public StudentFamilyDTO(Long familyNo, String familyRel, String familyPhone, String familyAge, Long studentNo) {
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
		return "StudentFamilyDTO [familyNo=" + familyNo + ", familyRel=" + familyRel + ", familyPhone=" + familyPhone
				+ ", familyAge=" + familyAge + ", studentNo=" + studentNo + "]";
	}

	
	
}