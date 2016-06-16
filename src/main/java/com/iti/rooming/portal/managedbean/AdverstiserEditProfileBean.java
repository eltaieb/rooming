package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Mail;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ViewScoped
public class AdverstiserEditProfileBean extends BaseBean implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private RoomAdvertiser roomAdvertiser;

	@PostConstruct
	public void init() {

		roomAdvertiser = getCurrentlyLoggedRoomAdvertiser();

	}

	public RoomAdvertiser getRoomAdvertiser() {
		return roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

	public String saveAdvisorVerfications() {

		try {

			roomAdvertiser = roomingManagment
					.addOrUpdateRoomAdvertiser(roomAdvertiser);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Profile Updated Successfully",""));

		} catch (RoomingException e) {
			e.printStackTrace();
		}
		return null;

	}

}
