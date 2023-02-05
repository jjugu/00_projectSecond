package com.eyelevel.project.category.dto;

public class PwdDTO {

	private String originPw;
	private String newPw;
	private String checkPw;
	
	public PwdDTO() {
	}

	public PwdDTO(String originPw, String newPw, String checkPw) {
		this.originPw = originPw;
		this.newPw = newPw;
		this.checkPw = checkPw;
	}

	public String getOriginPw() {
		return originPw;
	}

	public void setOriginPw(String originPw) {
		this.originPw = originPw;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getCheckPw() {
		return checkPw;
	}

	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}

	@Override
	public String toString() {
		return "PwdDTO [originPw=" + originPw + ", newPw=" + newPw + ", checkPw=" + checkPw + "]";
	}
}