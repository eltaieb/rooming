package com.iti.rooming.portal.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.iti.rooming.common.exception.RoomingException;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	public void codeRequest(ActionEvent evnet) throws RoomingException {
//		String furl = "https://www.facebook.com/dialog/oauth?"
//				+ "client_id=106715529744218&"
//				+ "redirect_uri=http://localhost:8080/rooming/home.xhtml&"
//				+ "scope=publish_stream,user_groups,status_update&"
//				+ "response_type=code";
//		HttpServletResponse response = (HttpServletResponse) FacesContext
//				.getCurrentInstance().getExternalContext().getResponse();
//		try {
//			response.sendRedirect(furl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		throw new RoomingException("test");
	}
}