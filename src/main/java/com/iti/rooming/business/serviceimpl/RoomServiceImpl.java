package com.iti.rooming.business.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.RoomService;
import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.RoomDAO;

@Stateless
public class RoomServiceImpl implements RoomService {

	@EJB
	RoomDAO roomDAO;

	public Room addOrUpdateRoom(Room room) throws RoomingException {
		return roomDAO.addOrUpdateRoom(room);
	}

	@Override
	public void addOrUpdateRoomImage(RoomImage roomImage)
			throws RoomingException {
		roomDAO.addOrUpdateRoomsImage(roomImage);
	}

	@Override
	public List<Room> getRoomsOfFacility(Facility facility) {
		return roomDAO.getRoomByFacility(facility);
	}

	@Override
	public List<RoomImage> getRoomImageOfRoom(Room room) {
		return roomDAO.getRoomImageByRoom(room);
	}

	@Override
	public List<RoomWrapper> getAllRoomOfFacility(Long facilityID) {
		List<Room> rooms = roomDAO.getRentAvailableFacilityRooms(facilityID);
		List<RoomWrapper> result = new ArrayList();
		for (Room r : rooms) {
			RoomWrapper roomWrapper = new RoomWrapper();
			roomWrapper.setId(r.getId());
			roomWrapper.setPrice(r.getPrice());
			roomWrapper.setArea(r.getArea());
			for (RoomImage roomImage : r.getRoomImages()) {
				roomWrapper.getImages().add(roomImage.getImage());
			}
			result.add(roomWrapper);
		}
		return result;
	}

	@Override
	public void removeDeletedRooms(List<Room> facilityRooms, Facility facility)
			throws RoomingException {
		roomDAO.removeRoomNotInFacilityRooms(facilityRooms,facility);
	}
}
