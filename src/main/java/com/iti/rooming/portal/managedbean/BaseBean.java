package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.springsecurity.CustomUserDetails;

public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147052226457712799L;

	public RoomAdvertiser getCurrentlyLoggedRoomAdvertiser() {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
		RoomAdvertiser roomAdvertiser = (RoomAdvertiser) obj.getUser();

		return roomAdvertiser;

	}
	
	public void setCurrentLoggedRoomAdvertiser(RoomAdvertiser roomAdvertiser)
	{

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
		obj.setUser(roomAdvertiser);
	}

	public void PutInSession(String key, Object obj) {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put(key, obj);

	}
	
	
	public Object getFromSession(String key)
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get(key);
	}
	
	public void addErrorMessage(String message)
	{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));		
	}
	
	public void addInfoMessage(String message)
	{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));		
	}
	
	public void redirect(String url) throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}
	    
	public boolean isRoomAdvertiserRole()
	{
		CustomUserDetails customUserDetails = (CustomUserDetails) getFromSession("currentUserDetails");
		if(customUserDetails !=null)
		{
			return customUserDetails.getUser().getRole().equals("advertiser");
		}
		return false;
	}
	
	public boolean isAuthenticated()
	{
		CustomUserDetails customUserDetails = (CustomUserDetails) getFromSession("currentUserDetails");
		
		return customUserDetails !=null;
	}
	
	public void invalidateSession()
	{
		
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
	}

}
