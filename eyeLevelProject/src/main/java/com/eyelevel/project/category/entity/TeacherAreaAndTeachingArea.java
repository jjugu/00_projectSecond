package com.eyelevel.project.category.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="TacherAreaTeachingArea")
@Table(name = "TeacherArea")
public class TeacherAreaAndTeachingArea implements Serializable {
	
	private static final long serialVersionUID = -7776205890824496403L;

	@Id
	@Column(name = "COLUNM_NO")
	private Long colunmNo;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@Column(name = "AREA_NO")
	private Integer areaNo;
	
	/* 매핑을 위해 컬럼에 없는 값 추가 */
	@OneToOne
	@JoinColumn(name = "AREA_NO", insertable = false, updatable = false)
	private TeachingArea areaInfo;

	public TeacherAreaAndTeachingArea() {
	}

	public TeacherAreaAndTeachingArea(Long colunmNo, String teacherNo, Integer areaNo, TeachingArea areaInfo) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TeacherAreaTeachingArea [colunmNo=" + colunmNo + ", teacherNo=" + teacherNo + ", areaNo=" + areaNo
				+ ", areaInfo=" + areaInfo + "]";
	}
}