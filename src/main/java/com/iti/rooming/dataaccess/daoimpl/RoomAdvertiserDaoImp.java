package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.dataaccess.dao.RoomAdvertiserDao;

@Stateless
public class RoomAdvertiserDaoImp extends BaseDAO implements RoomAdvertiserDao {

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) {
		super.persist(roomAdvertiser);
		return roomAdvertiser;

	}

	public List<RoomAdvertiser> findRoomAdvertisers() {
		return super.getAll(RoomAdvertiser.class);
	}

	public List<RoomAdvertiser> findUnValidAdvertisers() {

		Query query = em
				.createQuery("select r from RoomAdvertiser r WHERE r.isVerified = :isVerified and r.identificationDocumentPath IS NOT NULL");
		query.setParameter("isVerified", false);
		return query.getResultList();
	}

	@Override
	public RoomAdvertiser getProfile(Long ownerId) {
		String sql = "SELECT ra FROM RoomAdvertiser ra WHERE ra.id=:id";
		Query query = em.createQuery(sql, RoomAdvertiser.class);
		query.setParameter("id", ownerId);
		RoomAdvertiser roomAdvertiser = (RoomAdvertiser) query
				.getSingleResult();
		return roomAdvertiser;
	}

}
