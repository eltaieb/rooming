package com.iti.rooming.business.service;
import javax.ejb.Local;

import com.iti.rooming.common.entity.User;
import com.iti.rooming.springsecurity.CustomUserDetails;

@Local
public interface AutherirtyService {

	public User LoginAuthentication(String username);
}
