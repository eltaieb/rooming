package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.RoomAdvertiser;

@Local
public interface RoomAdvertiserDao {

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser);

	public List<RoomAdvertiser> findRoomAdvertisers();

	public List<RoomAdvertiser> findUnValidAdvertisers() ;

	public RoomAdvertiser getProfile(Long ownerId);
}
