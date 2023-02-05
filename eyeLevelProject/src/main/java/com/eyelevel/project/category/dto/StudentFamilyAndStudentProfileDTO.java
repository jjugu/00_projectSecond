package com.eyelevel.project.category.dto;

public class StudentFamilyAndStudentProfileDTO {

	private Long familyNo;
	private String familyRel;
	private String familyPhone;
	private String familyAge;
	private StudentProfileAndStudentFamilyDTO studentNo;
	
	public StudentFamilyAndStudentProfileDTO() {}

	public StudentFamilyAndStudentProfileDTO(Long familyNo, String familyRel, String familyPhone, String familyAge,
			StudentProfileAndStudentFamilyDTO studentNo) {
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

	public StudentProfileAndStudentFamilyDTO getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(StudentProfileAndStudentFamilyDTO studentNo) {
		this.studentNo = studentNo;
	}

	@Override
	public String toString() {
		return "StudentFamilyAndStudentProfileDTO [familyNo=" + familyNo + ", familyRel=" + familyRel + ", familyPhone="
				+ familyPhone + ", familyAge=" + familyAge + ", studentNo=" + studentNo + "]";
	}

	
}
