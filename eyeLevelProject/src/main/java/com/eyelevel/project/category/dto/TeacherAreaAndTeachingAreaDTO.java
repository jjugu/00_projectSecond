package com.eyelevel.project.category.dto;

import com.eyelevel.project.category.entity.TeachingArea;

public class TeacherAreaAndTeachingAreaDTO {

	private Long colunmNo;
	private String teacherNo;
	private Integer areaNo;
	
	/* 매핑을 위해 컬럼에 없는 값 추가 */
	private TeachingArea areaInfo;

	public TeacherAreaAndTeachingAreaDTO() {
	}

	public TeacherAreaAndTeachingAreaDTO(Long colunmNo, String teacherNo, Integer areaNo, TeachingArea areaInfo) {
		super();
		this.colunmNo = colunmNo;
		this.teacherNo = teacherNo;
		this.areaNo = areaNo;
		this.areaInfo = areaInfo;
	}

	public Long getColunmNo() {
		return colunmNo;
	}

	public void setColunmNo(Long colunmNo) {
		this.colunmNo = colunmNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public Integer getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(Integer areaNo) {
		this.areaNo = areaNo;
	}

	public TeachingArea getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(TeachingArea areaInfo) {
		this.areaInfo = areaInfo;
	}

	@Override
	public String toString() {
		return "TeacherAreaAndTeachingAreaDTO [colunmNo=" + colunmNo + ", teacherNo=" + teacherNo + ", areaNo=" + areaNo
				+ ", areaInfo=" + areaInfo + "]";
	}
}