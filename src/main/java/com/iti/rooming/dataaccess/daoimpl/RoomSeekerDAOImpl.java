package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.RoomSeekerDAO;

@Stateless
public class RoomSeekerDAOImpl extends BaseDAO implements RoomSeekerDAO {

	@Override
	public List<RoomSeeker> getAll() {
		return super.getAll(RoomSeeker.class);
	}

	@Override
	public RoomSeeker getRoomSeekerById(Long id) {
		return (RoomSeeker) super.find(RoomSeeker.class, id);
	}

	@Override
	public void saveOrUpdate(RoomSeeker RoomSeeker) {
		super.persist(RoomSeeker);
	}

	@Override
	public void remove(RoomSeeker RoomSeeker) {
		super.remove(RoomSeeker);

	}

	@Override
	public RoomSeeker register(RoomSeeker roomSeeker) {
		String sql = "SELECT rs FROM RoomSeeker rs WHERE rs.email=:email";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("email", roomSeeker.getEmail());
		List result = query.getResultList();
		if (result.size() > 0)
			return roomSeeker = (RoomSeeker) result.get(0);

		roomSeeker = (RoomSeeker) super.persist(roomSeeker);
		if (roomSeeker.getId() != null && roomSeeker.getId() > 0)
			return roomSeeker;
		else
			return null;
	}

	@Override
	public RoomSeeker login(RoomSeeker roomSeeker) {
		String sql = "SELECT rs FROM RoomSeeker rs WHERE rs.email=:email AND rs.password=:password";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("email", roomSeeker.getEmail());
		query.setParameter("password", roomSeeker.getPassword());
		roomSeeker = (RoomSeeker) query.getSingleResult();
		return roomSeeker;
	}

	@Override
	public RoomSeeker checkEmailValidation(String email) {
		String sql = "SELECT rs FROM RoomSeeker rs WHERE rs.email=:email";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("email", email);
		RoomSeeker roomSeeker = (RoomSeeker) query.getSingleResult();
		return roomSeeker;
	}

	@Override
	public Boolean validateResetPasswordToken(RoomSeeker seeker) {
		String sql = "SELECT rs FROM RoomSeeker rs WHERE rs.resetToken=:token";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("token", seeker.getResetToken());
		if (query.getResultList().size() > 0)
			return true;
		return false;
	}

	@Override
	public Boolean updatePassword(RoomSeeker roomSeeker) {
		String sql = "UPDATE RoomSeeker rs SET rs.password=:newPassword, rs.resetToken='' WHERE rs.resetToken=:resetToken";
		Query query = em.createQuery(sql);
		query.setParameter("newPassword", roomSeeker.getPassword());
		query.setParameter("resetToken", roomSeeker.getResetToken());
		if (query.executeUpdate() > 0)
			return true;

		return false;
	}

	@Override
	public void setResetValidationTime(RoomSeeker roomSeeker) {
		super.saveOrUpdate(roomSeeker);
	}

	@Override
	public RoomSeeker activeAccount(RoomSeeker roomSeeker) {
		String sql = "SELECT rs FROM RoomSeeker rs WHERE rs.activationToken=:activeToken";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("activeToken", roomSeeker.getActivationToken());
		roomSeeker = (RoomSeeker) query.getSingleResult();
		if (roomSeeker == null)
			return null;
		roomSeeker.setActivationToken("");
		roomSeeker.setActivated(true);
		roomSeeker = (RoomSeeker) super.persist(roomSeeker);
		return roomSeeker;
	}

	@Override
	public Boolean updateProfile(RoomSeeker roomSeeker) {
		String sql = "SELECT r FROM RoomSeeker r WHERE r.email=:email";
		Query query = em.createQuery(sql, RoomSeeker.class);
		query.setParameter("email", roomSeeker.getEmail());
		if (query.getResultList().size() >= 1) {
			return false;
		}
		super.persist(roomSeeker);
		return true;
	}

}
