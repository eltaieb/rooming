package com.iti.rooming.business.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoomingAdvertiserService  {
	public void sendResetPasswordURL(String email) throws RoomingException,
			NoSuchAlgorithmException, AddressException, MessagingException,
			IOException;

	public void updatePassword(String password, String token)
			throws RoomingException;

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) throws RoomingException;

	public List<RoomAdvertiser> findRoomAdvertisers() throws RoomingException;

	public List<RoomAdvertiser> findUnValidAdvertisers();

	public RoomAdvertiser login(RoomAdvertiser roomAdvertiser);

	public OwnerWrapper getProfile(Long ownerId);
	
	
}
