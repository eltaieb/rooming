package com.iti.rooming.portal.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.utils.JsonUtil;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;

@ManagedBean
@ViewScoped
public class MessageBean extends BaseBean {
	@EJB
	private RoomingManagment roomingManagment;
	private RoomAdvertiser roomAdvertiser;
	private List<SeekerFacilityWrapper> seekers;
	private SeekerFacilityWrapper seekerFacilityWrapper;
	private List<Message> messages;
	private Message message;
	private String text;

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		// HttpSession session = (HttpSession) context.getSession(false);
		RoomAdvertiser roomAdvertiser = new RoomAdvertiser();
		roomAdvertiser.setId(1L);
		seekers = roomingManagment.getSeekers(roomAdvertiser);
		message = new Message();
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

	public List<SeekerFacilityWrapper> getSeekers() {
		return seekers;
	}

	public void setSeekers(List<SeekerFacilityWrapper> seekers) {
		this.seekers = seekers;
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

	public SeekerFacilityWrapper getSeekerFacilityWrapper() {
		return seekerFacilityWrapper;
	}

	public void setSeekerFacilityWrapper(
			SeekerFacilityWrapper seekerFacilityWrapper) {
		this.seekerFacilityWrapper = seekerFacilityWrapper;
	}

	public void send() {
		Message message = new Message();
		message.setRoomAdvertiser(roomAdvertiser);
		message.setRoomSeeker(seekerFacilityWrapper.getRoomSeeker());
		message.setContent(text);
		roomingManagment.saveChatMessage(message);
	}

	public void showMessages() {
		messages = roomingManagment
				.getAllMessages(getCurrentlyLoggedRoomAdvertiser());
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String json = JsonUtil.getJson(messages);
		requestContext.execute("loadAllMessages(" + json + ")");

	}

}
