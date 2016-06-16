package com.iti.rooming.common.entity;


import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthority() {
		return this.name;
	}
}
