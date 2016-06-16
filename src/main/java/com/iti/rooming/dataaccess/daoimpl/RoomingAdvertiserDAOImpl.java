package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.RoomingAdvertiserDAO;

@Stateless
public class RoomingAdvertiserDAOImpl extends BaseDAO implements
		RoomingAdvertiserDAO {

	@Override
	public RoomAdvertiser validateEmail(String email) throws RoomingException {
		RoomAdvertiser roomAdverstiser = null;
		Query query = em
				.createQuery("FROM RoomAdvertiser r WHERE r.email=:email");
		query.setParameter("email", email);
		roomAdverstiser = (RoomAdvertiser) query.getSingleResult();
		if (roomAdverstiser==null) {
			throw new RoomingException("invalid email");
		}
		return roomAdverstiser;
	}

	@Override
	public void saveToken(RoomAdvertiser roomAdvertiser)
			throws RoomingException {
		em.persist(roomAdvertiser);
	}

	@Override
	public void updatePassword(String password, String token)
			throws RoomingException {
		Query query = em
				.createQuery("UPDATE RoomAdvertiser r SET r.password=:password WHERE r.token=:token");
		query.setParameter("password", password);
		query.setParameter("token", token);
		query.executeUpdate();
	}

	@Override
	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) {
		return (RoomAdvertiser) super.persist(roomAdvertiser);
	}

	@Override
	public List<RoomAdvertiser> findRoomAdvertisers() {
		return super.getAll(RoomAdvertiser.class);
	}

	@Override
	public List<RoomAdvertiser> findUnValidAdvertisers() {

		Query query = em
				.createQuery("select r from RoomAdvertiser r WHERE r.isVerified = :isVerified and r.identificationDocumentPath IS NOT NULL");
		query.setParameter("isVerified", false);
		return query.getResultList();
	}

	@Override
	public RoomAdvertiser login(RoomAdvertiser roomAdvertiser) {
		Query query = em
				.createQuery("SELECT r FROM RoomAdvertiser r WHERE r.email=:email AND r.password=:password");
		query.setParameter("email", roomAdvertiser.getEmail());
		query.setParameter("password", roomAdvertiser.getPassword());
		roomAdvertiser = (RoomAdvertiser) query.getSingleResult();
		return roomAdvertiser;
	}

}
