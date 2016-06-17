package com.iti.rooming.dataaccess.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoomingAdvertiserDAO {
	public RoomAdvertiser validateEmail(String email) throws RoomingException;

	public void saveToken(RoomAdvertiser roomAdvertiser)
			throws RoomingException;

	public void updatePassword(String password, String token)
			throws RoomingException;

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser);

	public List<RoomAdvertiser> findRoomAdvertisers();

	public List<RoomAdvertiser> findUnValidAdvertisers();

	public RoomAdvertiser login(RoomAdvertiser roomAdvertiser);

	public RoomAdvertiser getProfile(Long ownerId);

	public List<RoomAdvertiser> loadAdvertisersLazyMode(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	public int getNumOfAdvertiserRows(Map<String, Object> filters);

	public List<RoomAdvertiser> findAllUnValidateAdvisors(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	public List getAllRoomAdvertisers();
}
