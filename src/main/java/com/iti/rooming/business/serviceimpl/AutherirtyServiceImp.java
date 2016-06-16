package com.iti.rooming.business.serviceimpl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.AutherirtyService;
import com.iti.rooming.common.entity.User;
import com.iti.rooming.dataaccess.dao.UserAuthenticationDAO;
import com.iti.rooming.springsecurity.CustomUserDetails;
@Stateless
public class AutherirtyServiceImp implements AutherirtyService {

	@EJB
	private UserAuthenticationDAO userAuthenticationDAO;

	
	public User LoginAuthentication(String username) {
		// TODO Auto-generated method stub
		return userAuthenticationDAO.LoginAuthentication(username);
	}

}
