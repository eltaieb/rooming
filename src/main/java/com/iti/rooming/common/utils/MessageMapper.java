package com.iti.rooming.common.utils;

import java.sql.Date;
import java.util.Calendar;

import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomSeeker;

public class MessageMapper {

	public static Message createMessage(RoomingChatMessage roomingChatMessage) {
		Message message = new Message();

		RoomAdvertiser roomAdvertiser = new RoomAdvertiser();
		RoomSeeker roomSeeker = new RoomSeeker();
		Facility facility = new Facility();
		if (roomingChatMessage.getType() == 1) {
			roomAdvertiser.setId(roomingChatMessage.getFrom());
			roomSeeker.setId(roomingChatMessage.getTo());
		} else if (roomingChatMessage.getType() == 2) {
			roomAdvertiser.setId(roomingChatMessage.getTo());
			roomSeeker.setId(roomingChatMessage.getFrom());
		}
		facility.setId(roomingChatMessage.getAbout());
		message.setRoomAdvertiser(roomAdvertiser);
		message.setRoomSeeker(roomSeeker);
		message.setFacility(facility);
		message.setDate(new Date(Calendar.getInstance().getTime().getTime()));
		message.setContent(roomingChatMessage.getMessage());
		return message;
	}
}
