package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity(name = "TeacherAreaAndTeacherProfile")
@Table(name = "TEACHER_AREA")
public class TeacherAreaAndTeacherProfile implements java.io.Serializable {

	private static final long serialVersionUID = -5417734222425909610L;

	/* 매핑될 값 */
	@Id
	// ★ 인서트 수행 시 필요
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COLUNM_NO")
	private Long colunmNo;
	
	@ManyToOne
	@JoinColumn(name = "TEACHER_NO")
	private TeacherProfileAndTeacherArea teacherNo;
	
	@Column(name = "AREA_NO")
	private Integer areaNo;

	public TeacherAreaAndTeacherProfile() {
	}

	public TeacherAreaAndTeacherProfile(Long colunmNo, TeacherProfileAndTeacherArea teacherNo, Integer areaNo) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TeacherAreaAndTeacherProfile [colunmNo=" + colunmNo + ", teacherNo=" + teacherNo.getTeacherNo() + ", areaNo=" + areaNo
				+ "]";
	}
}