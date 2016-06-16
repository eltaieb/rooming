package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoomService {

	public Room addOrUpdateRoom(Room room) throws RoomingException;

	public List<Room> getRoomsOfFacility(Facility facility);

	public void addOrUpdateRoomImage(RoomImage roomImage)
			throws RoomingException;

	public List<RoomImage> getRoomImageOfRoom(Room room);

	public List<RoomWrapper> getAllRoomOfFacility(Long facilityID);

	public void removeDeletedRooms(List<Room> facilityRooms, Facility facility)
			throws RoomingException;;
}
