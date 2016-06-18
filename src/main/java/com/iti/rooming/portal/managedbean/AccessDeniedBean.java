package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class AccessDeniedBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public void redirectToHome() throws IOException {
		String role = getCurrentlyLoggedUser().getRole();
		if (role == "admin") {
			redirect("/rooming/admin/home.xhtml");
		} else {
			redirect("/rooming/usr/home.xhtml");
		}
	}
}
