package com.tommystore.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RoleFilterBean {

	@NotNull
	@NotBlank
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
