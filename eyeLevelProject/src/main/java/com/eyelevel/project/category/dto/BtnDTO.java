package com.eyelevel.project.category.dto;

public class BtnDTO {

	private String btn;

	public BtnDTO() {
	}

	public BtnDTO(String btn) {
		this.btn = btn;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	@Override
	public String toString() {
		return "BtnDTO [btn=" + btn + "]";
	}
}