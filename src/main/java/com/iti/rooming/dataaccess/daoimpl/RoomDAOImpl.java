package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.RoomDAO;

@Stateless
public class RoomDAOImpl extends BaseDAO implements RoomDAO {

	@Override
	public Room addOrUpdateRoom(Room room) throws RoomingException {
		return (Room) super.persist(room);
	}

	@Override
	public void addOrUpdateRoomsImage(RoomImage image) throws RoomingException {
		super.persist(image);
	}

	@Override
	public List<Room> getRoomByFacility(Facility facility) {
		Query query = em
				.createQuery("SELECT r FROM Room r WHERE r.facility = :facility ");
		query = query.setParameter("facility", facility);
		return query.getResultList();
	}

	@Override
	public List<RoomImage> getRoomImageByRoom(Room room) {
		Query query = em
				.createQuery("SELECT r FROM RoomImage r WHERE r.room = :room ");
		query = query.setParameter("room", room);
		return query.getResultList();
	}

	@Override
	public List<Room> getRentAvailableFacilityRooms(Long facilityId) {
		Query query = em
				.createQuery("select x from Room x where x.rentAvailable =:rentAvailable and x.facility.id =:facilityId");
		query.setParameter("facilityId", facilityId);
		query.setParameter("rentAvailable", true);
		return query.getResultList();

	}

	@Override
	public void removeRoomNotInFacilityRooms(List<Room> facilityRooms,
			Facility facility) throws RoomingException {
		if (facilityRooms != null && facilityRooms.size() > 0) {
			Query query = em
					.createQuery("UPDATE Room r SET r.isDeleted=1 WHERE r NOT IN ( :rooms )");
			query.setParameter("rooms", facilityRooms).executeUpdate();
		} else {// Case Rooms existed but then where all deleted
			Query query = em
					.createQuery("UPDATE Room r SET r.isDeleted=1 WHERE r.facility = :facility ");
			query.setParameter("facility", facility).executeUpdate();
		}

	}

}
