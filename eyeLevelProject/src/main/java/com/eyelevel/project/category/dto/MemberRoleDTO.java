package com.eyelevel.project.category.dto;

public class MemberRoleDTO {

	private int memberauthorityNo;
	private int teacherNo;
	private int studentNo;
	private int authorityCode;
	
	public MemberRoleDTO() {}
	
	public MemberRoleDTO(int memberauthorityNo, int teacherNo, int studentNo, int authorityCode) {
		super();
		this.memberauthorityNo = memberauthorityNo;
		this.teacherNo = teacherNo;
		this.studentNo = studentNo;
		this.authorityCode = authorityCode;
	}
	public int getMemberauthorityNo() {
		return memberauthorityNo;
	}
	public void setMemberauthorityNo(int memberauthorityNo) {
		this.memberauthorityNo = memberauthorityNo;
	}
	public int getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(int teacherNo) {
		this.teacherNo = teacherNo;
	}
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	public int getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}
	@Override
	public String toString() {
		return "MemberRoleDTO [memberauthorityNo=" + memberauthorityNo + ", teacherNo=" + teacherNo + ", studentNo="
				+ studentNo + ", authorityCode=" + authorityCode + "]";
	}
	
}
