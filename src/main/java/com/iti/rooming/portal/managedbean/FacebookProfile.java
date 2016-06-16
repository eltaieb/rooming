package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

@ManagedBean
public class FacebookProfile implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fname;
	
	@PostConstruct
	public void init() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		SocialAuthManager manager = (SocialAuthManager) session.getAttribute("manager");
		Map<String, String> params = SocialAuthUtil.getRequestParametersMap(request);
		AuthProvider provider = manager.connect(params);
		Profile profile = provider.getUserProfile();
		fname = profile.getEmail();
	}

	public String getFname() {
		return fname;
	}
}
