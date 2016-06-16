package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;


@Local
public interface RoomDAO {
	
	public Room addOrUpdateRoom(Room room)throws RoomingException ;
	public void addOrUpdateRoomsImage(RoomImage image)throws RoomingException ;
	public List<Room> getRoomByFacility(Facility facility);
	public List<RoomImage> getRoomImageByRoom(Room room);
	public List<Room> getRentAvailableFacilityRooms(Long facilityID);
	public void removeRoomNotInFacilityRooms(List<Room> facilityRooms,
			Facility facility) throws RoomingException;


}
