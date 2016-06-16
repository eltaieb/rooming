package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utils.FacesContextManager;

@ManagedBean
@ViewScoped
public class SeekerResetPasswordBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private RoomSeeker roomSeeker;
	private String token;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		token = params.get("token");
		roomSeeker = new RoomSeeker();
		roomSeeker.setResetToken(token);
		roomSeeker.setResetUrlValidationTime(new Date());
		if (!validateResetValidationTime()) {
			FacesContextManager.addMessage("Invalid token",
					FacesMessage.SEVERITY_WARN);
			return;
		}

	}

	private boolean validateResetValidationTime() {
		return roomingManagment.validateSeekerResetPasswordToken(roomSeeker);
	}

	public void updatePassword() {
		Boolean status = roomingManagment.updateSeekerPassword(roomSeeker);
		if (status) {
			FacesContextManager.addMessage("password is updated");
		} else {
			FacesContextManager
					.addMessage("Error in updating password, please try again");
		}

	}

	public RoomSeeker getRoomSeeker() {
		return roomSeeker;
	}

	public void setRoomSeeker(RoomSeeker roomSeeker) {
		this.roomSeeker = roomSeeker;
	}

}
