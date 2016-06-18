package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iti.rooming.common.dto.OnlineUsers;
import com.iti.rooming.springsecurity.CustomAuthenticationProvider;
import com.iti.rooming.springsecurity.CustomUserDetails;

@ManagedBean
@ViewScoped
public class UserAuthenticationManagedBean extends BaseBean implements
		Serializable {

	// @EJB
	// private RoomingManagment roomingManagment;
	//
	@ManagedProperty(value = "#{customAuthenticationProvider}")
	private CustomAuthenticationProvider myAuthenticationBean;

	public CustomAuthenticationProvider getMyAuthenticationBean() {
		return myAuthenticationBean;
	}

	public void setMyAuthenticationBean(
			CustomAuthenticationProvider myAuthenticationBean) {
		this.myAuthenticationBean = myAuthenticationBean;
	}

	private String userName;
	private String password;

	public void login() {

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.getUserName(), this.getPassword());

			Authentication result = myAuthenticationBean.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
  
			PutInSession("currentUserDetails", obj);
			PutInSession("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

			String userRole = obj.getUser().getRole();
			if (userRole.equals("admin")) {

				try {
					FacesContext ctx = FacesContext.getCurrentInstance();

					ExternalContext extContext = ctx.getExternalContext();
					String url = extContext.encodeActionURL(ctx
							.getApplication().getViewHandler()
							.getActionURL(ctx, "/admin/home.xhtml"));

					extContext.redirect(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (userRole.equals("advertiser")) {

				try {
					OnlineUsers.incrementNOfOnlineUsers();
					FacesContext ctx = FacesContext.getCurrentInstance();

					ExternalContext extContext = ctx.getExternalContext();
					String url = extContext.encodeActionURL(ctx
							.getApplication().getViewHandler()
							.getActionURL(ctx, "/usr/home.xhtml"));

					extContext.redirect(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					FacesContext ctx = FacesContext.getCurrentInstance();

					ExternalContext extContext = ctx.getExternalContext();
					String url = extContext.encodeActionURL(ctx
							.getApplication().getViewHandler()
							.getActionURL(ctx, "/pub/login.xhtml"));

					extContext.redirect(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception ex) {
			addErrorMessage("Invalid username or password");
		}

	}

	public void logout() throws IOException {
		invalidateSession();
		OnlineUsers.decrementNOfOnlineUsers();
		redirect("/rooming/pub/index.xhtml");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
