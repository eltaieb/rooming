package com.iti.rooming.common.utils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class FacesContextManager {
	private static FacesContext context = FacesContext.getCurrentInstance();

	public static String getParameter(String param) {
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		return params.get(param);
	}

	public static HttpSession getSession() {
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return session;
	}

	public static void setInvalidSession() {
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		session.invalidate();
	}

	public static void addMessage(String message) {
		context.addMessage(null, new FacesMessage(message));
	}

	public static void addMessage(String message, Severity severity) {
		context.addMessage(null,
				new FacesMessage(severity, "Warning!", message));
	}

}
