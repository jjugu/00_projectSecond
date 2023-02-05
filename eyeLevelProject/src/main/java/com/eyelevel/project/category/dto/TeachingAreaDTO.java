package com.eyelevel.project.category.dto;

public class TeachingAreaDTO {

	private Long areaNo;
	private String areaSi;
	private String areaGu;
	private String areaDong;
	
	public TeachingAreaDTO() {
	}

	public TeachingAreaDTO(Long areaNo, String areaSi, String areaGu, String areaDong) {
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
		return "TeachingAreaDTO [areaNo=" + areaNo + ", areaSi=" + areaSi + ", areaGu=" + areaGu + ", areaDong="
				+ areaDong + "]";
	}
}