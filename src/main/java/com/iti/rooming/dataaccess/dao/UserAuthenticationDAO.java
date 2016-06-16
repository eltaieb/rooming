package com.iti.rooming.dataaccess.dao;

import javax.ejb.Local;

import com.iti.rooming.common.entity.User;

@Local
public interface UserAuthenticationDAO {

	public User LoginAuthentication(String username);
}
