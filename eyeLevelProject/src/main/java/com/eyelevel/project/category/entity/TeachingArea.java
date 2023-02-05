package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TeachingArea")
@Table(name = "TEACHING_AREA")
public class TeachingArea {

	@Id
	@Column(name = "AREA_NO")
	private Long areaNo;
	
	@Column(name = "AREA_SI")
	private String areaSi;
	
	@Column(name = "AREA_GU")
	private String areaGu;
	
	@Column(name = "AREA_DONG")
	private String areaDong;
	
	public TeachingArea() {
	}

	public TeachingArea(Long areaNo, String areaSi, String areaGu, String areaDong) {
		this.areaNo = areaNo;
		this.areaSi = areaSi;
		this.areaGu = areaGu;
		this.areaDong = areaDong;
	}

	public Long getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(Long areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaSi() {
		return areaSi;
	}

	public void setAreaSi(String areaSi) {
		this.areaSi = areaSi;
	}

	public String getAreaGu() {
		return areaGu;
	}

	public void setAreaGu(String areaGu) {
		this.areaGu = areaGu;
	}

	public String getAreaDong() {
		return areaDong;
	}

	public void setAreaDong(String areaDong) {
		this.areaDong = areaDong;
	}

	@Override
	public String toString() {
		return "TeachingArea [areaNo=" + areaNo + ", areaSi=" + areaSi + ", areaGu=" + areaGu + ", areaDong=" + areaDong
				+ "]";
	}
}