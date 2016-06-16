package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.Constants;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Mail;
import com.iti.rooming.common.utils.FacesContextManager;

@ManagedBean
@ViewScoped
public class RoomAdverstiserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private SocialAuthConfig config = SocialAuthConfig.getDefault();
	private String email;
	private String password;
	private RoomAdvertiser roomAdvertiser;

	@PostConstruct
	public void init() {
		String token = FacesContextManager.getParameter("token");
		roomAdvertiser = new RoomAdvertiser();
		if (token != null) {
			HttpSession session = FacesContextManager.getSession();
			session.setAttribute("token", token);
		}
	}

	public void facebookLogin() throws Exception {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		Properties props = new Properties();
		InputStream inputStream = Mail.class.getClassLoader()
				.getResourceAsStream("oauth_consumer.properties");

		if (inputStream != null) {
			props.load(inputStream);
		}
		config.load(props);
		SocialAuthManager manager = new SocialAuthManager();
		manager.setSocialAuthConfig(config);
		String successUrl = "http://localhost:8080/rooming/home.xhtml";
		String authenticaionURL = manager.getAuthenticationUrl(
				Constants.FACEBOOK, successUrl);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		session.setAttribute("manager", manager);
		ExternalContext excontext = context.getExternalContext();
		excontext.redirect(authenticaionURL);
	}

	public String resetPassword() throws RoomingException,
			NoSuchAlgorithmException, AddressException, MessagingException,
			IOException {
		roomingManagment.sendResetPasswordURL(email);
		return "resetpassword" + "";
	}

	public String login() {
		roomAdvertiser = roomingManagment.loginRoomAdvertiser(roomAdvertiser);
		if (roomAdvertiser != null) {
			addSession(roomAdvertiser);
			return "home";
		}
		return "login";
	}

	private void addSession(RoomAdvertiser roomAdvertiser2) {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.getSession(true);

	}

	public String updatePassword() throws RoomingException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		String token = (String) session.getAttribute("token");
		if (!token.equals(null)) {
			roomingManagment.updatePassword(password, token);
		}
		return "index";

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoomAdvertiser getRoomAdvertiser() {
		return roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

}
