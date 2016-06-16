package com.iti.rooming.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.dto.wrapper.ChatMessageWrapper;
import com.iti.rooming.common.dto.wrapper.ChatRequestWrapper;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.MessageMapper;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;
import com.iti.rooming.dataaccess.dao.MessageDAO;

@Stateless
public class MessageDAOImpl extends BaseDAO implements MessageDAO {

	@Override
	public void save(RoomingChatMessage roomingChatMessage) {
		Message message = MessageMapper.createMessage(roomingChatMessage);
		super.persist(message);
	}

	@Override
	public List<SeekerFacilityWrapper> getRoomSeekers(
			RoomAdvertiser roomAdvertiser) {
		Query query = em
				.createQuery("SELECT DISTINCT r, f  FROM Message e, RoomSeeker r, RoomAdvertiser ra, Facility f ");

		List obj = query.getResultList();
		Facility facility;
		RoomSeeker roomSeeker;

		for (int i = 0; i < obj.size(); i++) {

		}
		return null;
	}

	@Override
	public List<Message> getAllMessages(RoomAdvertiser roomAdvertiser) {
		Query query = em
				.createQuery("select e FROM Message e WHERE e.roomAdvertiser=:id");
		query.setParameter("id", roomAdvertiser);
		List<Message> messages = query.getResultList();
		return messages;
	}

	@Override
	public void save(Message message) {
		super.persist(message);

	}

	@Override
	public List<RoomAdvertiser> getChatOwnersOfSeeker(Long seekerId) {
		String sql = "SELECT DISTINCT m.roomAdvertiser FROM Message m WHERE m.roomSeeker.id=:seekerId";
		Query query = em.createQuery(sql, RoomAdvertiser.class);
		query.setParameter("seekerId", seekerId);
		List owners = query.getResultList();
		return owners;
	}

	@Override
	public List getAllMessagesTo(ChatRequestWrapper chatRequestWrapper) {
		String sql = "SELECT m.roomAdvertiser.firstName, m.content FROM Message m WHERE m.roomAdvertiser.id=:ownerId AND m.roomSeeker.id=:seekerId";
		Query query = em.createQuery(sql);
		query.setParameter("ownerId", chatRequestWrapper.getOwnerId());
		query.setParameter("seekerId", chatRequestWrapper.getSeekerId());
		List result = query.getResultList();
		ChatMessageWrapper chatMessageWrapper = new ChatMessageWrapper();

		return null;
	}

	@Override
	public List<SeekerRoomWrapper> getAllMessages(Facility facility,
			RoomAdvertiser roomAdvertiser) {
		String sql = "SELECT DISTINCT rs.username, rs.id, f.id FROM Message m, RoomSeeker rs, Facility f"
				+ " WHERE m.roomAdvertiser=:roomAdvertiser "
				+ " AND m.roomSeeker.id=rs.id";
		Query query = em.createQuery(sql);
		query.setParameter("roomAdvertiser", roomAdvertiser);
		query.setParameter("facility", facility);
		List<Object> objectList = query.getResultList();
		List<SeekerRoomWrapper> boundList = new LinkedList<>();
		List<SeekerRoomWrapper> seekerRoomWrappers = new ArrayList<>();
		for (int i = 0; i < objectList.size(); i++) {
			Object[] objects = (Object[]) objectList.get(i);
			SeekerRoomWrapper seekerRoomWrapper = new SeekerRoomWrapper();
			facility = new Facility();
			RoomSeeker roomSeeker = new RoomSeeker();
			roomSeeker.setUsername(objects[0].toString());
			roomSeeker.setId((Long) objects[1]);
			facility.setId((Long) objects[2]);
			seekerRoomWrapper.setRoomSeeker(roomSeeker);
			seekerRoomWrapper.setFacility(facility);
			seekerRoomWrappers.add(seekerRoomWrapper);

		}
		return seekerRoomWrappers;
	}

	@Override
	public List<SeekerRoomWrapper> getAll(RoomAdvertiser roomAdvertiser) {

		String sql = "SELECT DISTINCT rs.username, rs.profileImage, rs.id, m.facility.id FROM Message m, RoomSeeker rs"
				+ " WHERE m.roomAdvertiser.id=:roomAdvertiser "
				+ " AND m.roomSeeker.id=rs.id";
		Query query = em.createQuery(sql);
		query.setParameter("roomAdvertiser", roomAdvertiser.getId());
		List<Object> objectList = query.getResultList();
		List<SeekerRoomWrapper> boundList = new LinkedList<>();
		List<SeekerRoomWrapper> seekerRoomWrappers = new ArrayList<>();
		for (int i = 0; i < objectList.size(); i++) {
			Object[] objects = (Object[]) objectList.get(i);
			SeekerRoomWrapper seekerRoomWrapper = new SeekerRoomWrapper();
			Facility facility = new Facility();
			RoomSeeker roomSeeker = new RoomSeeker();
			roomSeeker.setUsername(objects[0].toString());
			roomSeeker.setProfileImage(objects[1].toString());
			roomSeeker.setId((Long) objects[2]);
			facility.setId((Long) objects[3]);
			seekerRoomWrapper.setRoomSeeker(roomSeeker);
			seekerRoomWrapper.setFacility(facility);
			seekerRoomWrappers.add(seekerRoomWrapper);
		}
		return seekerRoomWrappers;

	}

	@Override
	public List<String> getAllMessages(RoomAdvertiser roomAdvertiser,
			SeekerRoomWrapper selectedSeekerRoomWrapper) {
		String sql = "SELECT m.content FROM Message m"
				+ " WHERE m.roomAdvertiser=:roomAdvertiser "
				+ " AND m.roomSeeker=:seeker" + " AND m.facility=:facility";
		Query query = em.createQuery(sql, String.class);
		query.setParameter("roomAdvertiser", roomAdvertiser);
		query.setParameter("seeker", selectedSeekerRoomWrapper.getRoomSeeker());
		query.setParameter("facility", selectedSeekerRoomWrapper.getFacility());
		List<String> messages = query.getResultList();
		return messages;
	}

}
