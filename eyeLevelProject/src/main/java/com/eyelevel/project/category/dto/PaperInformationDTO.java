package com.eyelevel.project.category.dto;

import java.util.Date;

public class PaperInformationDTO {

	private int paperNo;
	private String paperName;
	private String paperDiff;
	private String paperSub;
	
	public PaperInformationDTO() {};
	
	public PaperInformationDTO(int paperNo, String paperName, String paperDiff, String paperSub) {
		this.paperNo = paperNo;
		this.paperName = paperName;
		this.paperDiff = paperDiff;
		this.paperSub = paperSub;
	}


	public int getPaperNo() {
		return paperNo;
	}


	public void setPaperNo(int paperNo) {
		this.paperNo = paperNo;
	}


	public String getPaperName() {
		return paperName;
	}


	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}


	public String getPaperDiff() {
		return paperDiff;
	}


	public void setPaperDiff(String paperDiff) {
		this.paperDiff = paperDiff;
	}


	public String getPaperSub() {
		return paperSub;
	}


	public void setPaperSub(String paperSub) {
		this.paperSub = paperSub;
	}


	@Override
	public String toString() {
		return "CounselingDTO [paperNo=" + paperNo + ", paperName=" + paperName + ", paperDiff=" + paperDiff
				+ ", paperSub=" + paperSub + "]";
	}

	

}
