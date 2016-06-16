package com.iti.rooming.common.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionManager {

	public static void addSession(String key, Object object) {
		HttpSession session = getSession(true);
		session.setAttribute(key, object);
	}

	private static HttpSession getSession(boolean b) {
		ExternalContext externalContext = getFacesContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		return session;
	}

	private static ExternalContext getFacesContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static void remove(String key, Object object) {
		getFacesContext().invalidateSession();

	}

}
