package com.iti.rooming.springsecurity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.entity.UserRole;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	User user;

	List<UserRole> userRole;

	

	public CustomUserDetails() {

	}

	public CustomUserDetails(User user) {
		this.user = user;
		
	}

	public List<UserRole> getAuthorities() {
		return userRole;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

}
