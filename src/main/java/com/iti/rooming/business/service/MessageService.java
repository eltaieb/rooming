package com.iti.rooming.business.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.dto.wrapper.ChatRequestWrapper;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;

@Local
public interface MessageService {

	public void save(RoomingChatMessage roomingChatMessage);

	public List<SeekerFacilityWrapper> getRoomSeekers(RoomAdvertiser roomAdvertiser);

	public List<Message> getAllMessages(RoomAdvertiser roomAdvertiser);

	public void save(Message message);

	public List<OwnerWrapper> getChatOwnersOfSeeker(Long seekerId);

	public List getAllMessagesTo(
			ChatRequestWrapper chatRequestWrapper);

	public List<SeekerRoomWrapper> getAll(RoomAdvertiser roomAdvertiser);

	public List<SeekerRoomWrapper> getAllMessages(Facility selectedFacility,
			RoomAdvertiser roomAdvertiser);

	public List<String> getAllMessages(RoomAdvertiser roomAdvertiser,
			SeekerRoomWrapper selectedSeekerRoomWrapper);

	
}
