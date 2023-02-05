package com.eyelevel.project.category.dto;

import com.eyelevel.project.category.entity.TeacherProfileAndTeacherArea;

public class TeacherAreaAndTeacherProfileDTO {

	private Long colunmNo;
	private TeacherProfileAndTeacherArea teacherNo;
	private Integer areaNo;

	public TeacherAreaAndTeacherProfileDTO() {
	}

	public TeacherAreaAndTeacherProfileDTO(Long colunmNo, TeacherProfileAndTeacherArea teacherNo, Integer areaNo) {
		this.colunmNo = colunmNo;
		this.teacherNo = teacherNo;
		this.areaNo = areaNo;
	}

	public Long getColunmNo() {
		return colunmNo;
	}

	public void setColunmNo(Long colunmNo) {
		this.colunmNo = colunmNo;
	}

	public TeacherProfileAndTeacherArea getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(TeacherProfileAndTeacherArea teacherNo) {
		this.teacherNo = teacherNo;
	}

	public Integer getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(Integer areaNo) {
		this.areaNo = areaNo;
	}

	@Override
	public String toString() {
		return "TeacherAreaAndTeacherProfileDTO [colunmNo=" + colunmNo + ", teacherNo=" + teacherNo + ", areaNo="
				+ areaNo + "]";
	}
}