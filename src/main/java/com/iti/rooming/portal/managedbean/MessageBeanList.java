package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.JsonUtil;

@ManagedBean
@ViewScoped
public class MessageBeanList implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private RoomAdvertiser roomAdvertiser;
	private RoomSeeker selectedRoomSeeker;
	private List<SeekerRoomWrapper> seekerRoomWrappers;
	private SeekerRoomWrapper selectedSeekerRoomWrapper;
	private List<Message> messages;
	private List<Facility> facilities;
	private Facility selectedFacility;
	private Message message;
	private String text;

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		// HttpSession session = (HttpSession) context.getSession(false);
		roomAdvertiser = new RoomAdvertiser();
		roomAdvertiser.setId(1L);
		message = new Message();
		selectedFacility = new Facility();
		selectedFacility.setId(1L);
		loadAllSeekerRoomWrapper();
		loadAllFacilities();
	}

	private void loadAllSeekerRoomWrapper() {
		seekerRoomWrappers = roomingManagment
				.getAllseekerRoomWrappers(roomAdvertiser);

	}

	private void loadAllFacilities() {
		facilities = roomingManagment.getAllFacilities(roomAdvertiser);
	}

	public void newMessage() {
		message = new Message();
	}

	public RoomAdvertiser getRoomAdvertiser() {
		return roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void send() {
		Message message = new Message();
		message.setRoomAdvertiser(roomAdvertiser);
		// message.setRoomSeeker(seekerRoomWrapper.getRoomSeeker());
		message.setContent(text);
		roomingManagment.saveChatMessage(message);
	}

	// load all messages
	public void showMessages() {

		List<String> stringMessages = roomingManagment.getAllMessages(
				roomAdvertiser, selectedSeekerRoomWrapper);
		// call js function
		RequestContext requestContext = RequestContext.getCurrentInstance();
		// convert to json
		String json = JsonUtil.getJson(stringMessages);
		requestContext.execute("loadAllMessages(" + json + ")");

	}

	public void processValueChange(ValueChangeEvent vce) {
		selectedRoomSeeker = (RoomSeeker) vce.getNewValue();
		showMessages();
	}

	public RoomSeeker getSelectedRoomSeeker() {
		return selectedRoomSeeker;
	}

	public void setSelectedRoomSeeker(RoomSeeker selectedRoomSeeker) {
		this.selectedRoomSeeker = selectedRoomSeeker;
	}

	public void onFacilityRowToggle(ToggleEvent event) {
		selectedFacility = (Facility) event.getData();
		FacesMessage msg = new FacesMessage("Facility Selected",
				selectedFacility.getId() + "");
		seekerRoomWrappers = roomingManagment.getAllseekerRoomWrappers(
				selectedFacility, roomAdvertiser);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Facility getSelectedFacility() {
		return selectedFacility;
	}

	public void setSelectedFacility(Facility selectedFacility) {
		this.selectedFacility = selectedFacility;
	}

	public void sendMessage() {
		// call js function
		/*
		 * RequestContext requestContext = RequestContext.getCurrentInstance();
		 * requestContext .execute("send_message(" + roomAdvertiser.getId() +
		 * "," + selectedRoomSeeker.getId() + "," + selectedRoom.getId() + ")");
		 */

	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Selected Seeker is Selected",
				((SeekerRoomWrapper) event.getObject()).toString());
		selectedSeekerRoomWrapper = (SeekerRoomWrapper) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		showMessages();
	}

	public void buttonAction(ActionEvent actionEvent) {
		String url = "/rooming/usr/facilityview.xhtml?facilityId="
				+ selectedFacility.getId();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		try {
			ec.redirect(url);
		} catch (IOException ex) {

		}
	}

	public RoomingManagment getRoomingManagment() {
		return roomingManagment;
	}

	public void setRoomingManagment(RoomingManagment roomingManagment) {
		this.roomingManagment = roomingManagment;
	}

	public List<SeekerRoomWrapper> getSeekerRoomWrappers() {
		return seekerRoomWrappers;
	}

	public void setSeekerRoomWrappers(List<SeekerRoomWrapper> seekerRoomWrappers) {
		this.seekerRoomWrappers = seekerRoomWrappers;
	}

	public SeekerRoomWrapper getSelectedSeekerRoomWrapper() {
		return selectedSeekerRoomWrapper;
	}

	public void setSelectedSeekerRoomWrapper(
			SeekerRoomWrapper selectedSeekerRoomWrapper) {
		this.selectedSeekerRoomWrapper = selectedSeekerRoomWrapper;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

}
