package com.iti.rooming.business.serviceimpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.MessageService;
import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.dto.wrapper.ChatMessageWrapper;
import com.iti.rooming.common.dto.wrapper.ChatRequestWrapper;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;
import com.iti.rooming.dataaccess.dao.MessageDAO;

@Stateless
public class MessageServiceImpl implements MessageService {

	@EJB
	private MessageDAO messageDAO;

	@Override
	public void save(RoomingChatMessage roomingChatMessage) {
		messageDAO.save(roomingChatMessage);
	}

	@Override
	public List<SeekerFacilityWrapper> getRoomSeekers(
			RoomAdvertiser roomAdvertiser) {
		return messageDAO.getRoomSeekers(roomAdvertiser);
	}

	@Override
	public void save(Message message) {
		messageDAO.save(message);
	}

	@Override
	public List<Message> getAllMessages(RoomAdvertiser roomAdvertiser) {
		return messageDAO.getAllMessages(roomAdvertiser);
	}

	@Override
	public List<OwnerWrapper> getChatOwnersOfSeeker(Long seekerId) {
		List<RoomAdvertiser> onwers = messageDAO
				.getChatOwnersOfSeeker(seekerId);
		List<OwnerWrapper> ownerWrappers = onwers
				.parallelStream()
				.map(ra -> new OwnerWrapper(ra.getId(), ra.getFirstName(), ra
						.getLastName(), ra.getIsVerified(), ra
						.getProfileImage())).collect(Collectors.toList());
		return ownerWrappers;
	}

	@Override
	public List getAllMessagesTo(ChatRequestWrapper chatRequestWrapper) {
		List<String> messages = messageDAO.getAllMessagesTo(chatRequestWrapper);
		return messages;
	}

	@Override
	public List<SeekerRoomWrapper> getAll(RoomAdvertiser roomAdvertiser) {
		return messageDAO.getAll(roomAdvertiser);
	}

	@Override
	public List<SeekerRoomWrapper> getAllMessages(Facility facility,
			RoomAdvertiser roomAdvertiser) {
		return messageDAO.getAllMessages(facility, roomAdvertiser);
	}

	@Override
	public List<String> getAllMessages(RoomAdvertiser roomAdvertiser,
			SeekerRoomWrapper selectedSeekerRoomWrapper) {
		return messageDAO.getAllMessages(roomAdvertiser,
				selectedSeekerRoomWrapper);
	}

}
