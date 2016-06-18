package com.iti.rooming.springsecurity;

import java.util.Collection;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.iti.rooming.common.entity.User;
import com.iti.rooming.springsecurity.CustomUserService;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private CustomUserService userService;

	
	public Authentication authenticate(Authentication authentication)
	{
		
		return super.authenticate(authentication);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		User user = ((CustomUserDetails)userDetails).getUser();

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		
			
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		CustomUserDetails user = userService.loadUserByUsername(username);

		return user;
	}

}