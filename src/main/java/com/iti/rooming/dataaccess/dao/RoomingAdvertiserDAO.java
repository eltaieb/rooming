package com.iti.rooming.dataaccess.dao;

import java.util.List;

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

}
