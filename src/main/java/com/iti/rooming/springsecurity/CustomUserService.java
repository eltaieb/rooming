package com.iti.rooming.springsecurity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iti.rooming.business.management.RoomingManagment;
//import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.entity.UserRole;
import com.iti.rooming.common.exception.RoomingException;

@Service
public class CustomUserService implements UserDetailsService {

	RoomingManagment roomingManagement;

	@PostConstruct
	public void intit() {
		try {

			InitialContext contxt = new InitialContext();

			roomingManagement = (RoomingManagment) contxt
					.lookup("java:global/rooming/RoomingManagmentImpl");

			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public CustomUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = null;
		CustomUserDetails customUserDetails = null;

		user = roomingManagement.Authlogin(username);
		List<UserRole> userRoles = new ArrayList();
		UserRole userRole = new UserRole();
		userRole.setName(user.getRole());
		userRoles.add(userRole);


		customUserDetails = new CustomUserDetails(user);

		customUserDetails.setUserRole(userRoles);

		return customUserDetails;
	}

}