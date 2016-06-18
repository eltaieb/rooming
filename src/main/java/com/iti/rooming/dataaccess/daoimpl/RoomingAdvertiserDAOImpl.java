package com.iti.rooming.dataaccess.daoimpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.iti.rooming.common.dto.RoomAdvertiserCity;
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
		if (roomAdverstiser == null) {
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
	public RoomAdvertiser login(RoomAdvertiser roomAdvertiser) {
		Query query = em
				.createQuery("SELECT r FROM RoomAdvertiser r WHERE r.email=:email AND r.password=:password");
		query.setParameter("email", roomAdvertiser.getEmail());
		query.setParameter("password", roomAdvertiser.getPassword());
		roomAdvertiser = (RoomAdvertiser) query.getSingleResult();
		return roomAdvertiser;
	}

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

	@Override
	public List<RoomAdvertiser> loadAdvertisersLazyMode(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		String sql = "SELECT x FROM " + RoomAdvertiser.class.getCanonicalName()
				+ " x WHERE 1=1";
		String emailFilter = (String) filters.get("email");
		String firstNameFilter = (String) filters.get("firstName");
		String lastNameFilter = (String) filters.get("lastName");
		String userNameFilter = (String) filters.get("username");

		if (emailFilter != null) {
			sql += " AND x.email LIKE :email";
		}
		if (userNameFilter != null) {
			sql += " AND x.username LIKE :username";
		}
		if (firstNameFilter != null) {
			sql += " AND x.firstName LIKE :firstName";
		}
		if (lastNameFilter != null) {
			sql += " AND x.lastName LIKE :lastName";
		}
		TypedQuery<RoomAdvertiser> query = em
				.createQuery(sql, RoomAdvertiser.class).setFirstResult(first)
				.setMaxResults(pageSize);

		if (emailFilter != null) {
			query.setParameter("email", "%" + emailFilter + "%");
		}
		if (userNameFilter != null) {
			query.setParameter("username", "%" + userNameFilter + "%");
		}
		if (firstNameFilter != null) {
			query.setParameter("firstName", "%" + firstNameFilter + "%");
		}
		if (lastNameFilter != null) {
			query.setParameter("lastName", "%" + lastNameFilter + "%");
		}

		return query.getResultList();
	}

	@Override
	public int getNumOfAdvertiserRows(Map<String, Object> filters) {
		String sql = "SELECT COUNT(x) FROM "
				+ RoomAdvertiser.class.getCanonicalName() + " x WHERE 1=1";
		String emailFilter = (String) filters.get("email");
		String firstNameFilter = (String) filters.get("firstName");
		String lastNameFilter = (String) filters.get("lastName");
		String userNameFilter = (String) filters.get("username");

		if (emailFilter != null) {
			sql += " AND x.email LIKE :email";
		}
		if (userNameFilter != null) {
			sql += " AND x.username LIKE :username";
		}
		if (firstNameFilter != null) {
			sql += " AND x.firstName LIKE :firstName";
		}
		if (lastNameFilter != null) {
			sql += " AND x.lastName LIKE :lastName";
		}
		TypedQuery<Long> query = em.createQuery(sql, Long.class);

		if (emailFilter != null) {
			query.setParameter("email", "%" + emailFilter + "%");
		}
		if (userNameFilter != null) {
			query.setParameter("username", "%" + userNameFilter + "%");
		}
		if (firstNameFilter != null) {
			query.setParameter("firstName", "%" + firstNameFilter + "%");
		}
		if (lastNameFilter != null) {
			query.setParameter("lastName", "%" + lastNameFilter + "%");
		}
		return query.getSingleResult().intValue();
	}

	@Override
	public List<RoomAdvertiser> findAllUnValidateAdvisors(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		String sql = "SELECT x FROM " + RoomAdvertiser.class.getCanonicalName()
				+ " x WHERE 1=1";
		String emailFilter = (String) filters.get("email");
		String firstNameFilter = (String) filters.get("firstName");
		String lastNameFilter = (String) filters.get("lastName");
		String userNameFilter = (String) filters.get("username");

		if (emailFilter != null) {
			sql += " AND x.email LIKE :email";
		}
		if (userNameFilter != null) {
			sql += " AND x.username LIKE :username";
		}
		if (firstNameFilter != null) {
			sql += " AND x.firstName LIKE :firstName";
		}
		if (lastNameFilter != null) {
			sql += " AND x.lastName LIKE :lastName";
		}
		sql += " AND x.isVerified = :isVerified and x.identificationDocumentPath IS NOT NULL";

		TypedQuery<RoomAdvertiser> query = em
				.createQuery(sql, RoomAdvertiser.class).setFirstResult(first)
				.setMaxResults(pageSize);

		if (emailFilter != null) {
			query.setParameter("email", "%" + emailFilter + "%");
		}
		if (userNameFilter != null) {
			query.setParameter("username", "%" + userNameFilter + "%");
		}
		if (firstNameFilter != null) {
			query.setParameter("firstName", "%" + firstNameFilter + "%");
		}
		if (lastNameFilter != null) {
			query.setParameter("lastName", "%" + lastNameFilter + "%");
		}
		query.setParameter("isVerified", false);
		return query.getResultList();
	}

	@Override
	public List getAllRoomAdvertisers() {
		String sql = "SELECT x FROM RoomAdvertiser x";
		Query query = em.createQuery(sql, RoomAdvertiser.class);
		return query.getResultList();
	}

	@Override
	public List<RoomAdvertiserCity> getRoomAdvertiserInCities() {
		String sql = "SELECT r.city, COUNT(r.id) FROM RoomAdvertiser r GROUP BY r.city";
		Query query = em.createQuery(sql);
		List result = query.getResultList();
		List<RoomAdvertiserCity> roomAdvertiserCities = new LinkedList<RoomAdvertiserCity>();
		result.parallelStream().forEach(
				r -> {
					Object[] objects = (Object[]) r;
					roomAdvertiserCities.add(new RoomAdvertiserCity(
							(Long) objects[1], objects[0].toString()));
				});
		return roomAdvertiserCities;
	}

	@Override
	public Long getNofOfflineUsers() {
		String sql = "SELECT COUNT(u.id) FROM User u WHERE u.role != 'admin'";
		Query query = em.createQuery(sql, Long.class);
		return (Long) query.getSingleResult();
	}

}
