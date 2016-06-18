package com.iti.rooming.business.serviceimpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.iti.rooming.business.service.RoomingAdvertiserService;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.dto.RoomAdvertiserCity;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.EncryptionMD5;
import com.iti.rooming.common.utils.Mail;
import com.iti.rooming.common.utils.RandomString;
import com.iti.rooming.common.utils.TokenGenerator;
import com.iti.rooming.common.utils.URLGenerator;
import com.iti.rooming.dataaccess.dao.RoomingAdvertiserDAO;
import com.iti.rooming.ws.controller.OwnerWS;
import com.sun.javafx.fxml.builder.URLBuilder;

@Stateless
public class RoomingAdvertiserServiceImpl implements RoomingAdvertiserService {
	private static final Integer MAX_TOKEN_LEN = 20;
	@EJB
	private RoomingAdvertiserDAO roomingAdverstierDAO;

	public void sendResetPasswordURL(String email) throws RoomingException,
			NoSuchAlgorithmException, AddressException, MessagingException,
			IOException {
		String token = null;
		RoomAdvertiser roomAdvertiser = roomingAdverstierDAO
				.validateEmail(email);
		if (roomAdvertiser != null) {
			token = TokenGenerator.generate(roomAdvertiser.hashCode() + 0L);
			persistAdvertiserWithResetToken(roomAdvertiser, token);
			String url = URLGenerator.generateUrl(roomAdvertiser.getId(),
					"newpassword.xhtml");
			Mail.sendMail(email, url, Mail.RESET_PASSWORD);
		} else {
			throw new RoomingException("invalid token process");
		}

	}

	private void persistAdvertiserWithResetToken(RoomAdvertiser roomAdvertiser,
			String token) throws RoomingException {
		roomAdvertiser.setResetToken(token);
		roomAdvertiser.setResetUrlValidationTime(new Date());
		persisteTokenINDB(roomAdvertiser);
	}

	private void persisteTokenINDB(RoomAdvertiser roomAdvertiser)
			throws RoomingException {
		roomingAdverstierDAO.saveToken(roomAdvertiser);
	}

	private String getRandomString() {
		RandomString randomString = new RandomString(MAX_TOKEN_LEN);
		return randomString.getRandomString();
	}

	private String encrypteToken(String initToken, Long id)
			throws NoSuchAlgorithmException {
		return EncryptionMD5.getMD5(initToken + id);
	}

	public void updatePassword(String password, String token)
			throws RoomingException {

		roomingAdverstierDAO.updatePassword(password, token);
	}

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) throws RoomingException {
		return roomingAdverstierDAO.addOrUpdateRoomAdvertiser(roomAdvertiser);
	}

	@Override
	public List<RoomAdvertiser> findRoomAdvertisers() throws RoomingException {
		return roomingAdverstierDAO.findRoomAdvertisers();

	}

	@Override
	public List<RoomAdvertiser> findUnValidAdvertisers() {
		return roomingAdverstierDAO.findUnValidAdvertisers();
	}

	@Override
	public RoomAdvertiser login(RoomAdvertiser roomAdvertiser) {
		return roomingAdverstierDAO.login(roomAdvertiser);
	}

	@Override
	public OwnerWrapper getProfile(Long ownerId) {
		RoomAdvertiser roomAdvertiser = roomingAdverstierDAO
				.getProfile(ownerId);
		if (roomAdvertiser == null)
			return null;
		OwnerWrapper ownerWrapper = convertToOwnerWrapper(roomAdvertiser);
		return ownerWrapper;
	}

	private OwnerWrapper convertToOwnerWrapper(RoomAdvertiser roomAdvertiser) {
		OwnerWrapper ownerWrapper = new OwnerWrapper();
		ownerWrapper.setId(roomAdvertiser.getId());
		ownerWrapper.setfName(roomAdvertiser.getFirstName());
		ownerWrapper.setlName(roomAdvertiser.getLastName());
		ownerWrapper.setIsVerified(roomAdvertiser.getIsVerified());
		return ownerWrapper;
	}

	@Override
	public List<RoomAdvertiser> loadAdvertisersLazyMode(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		return roomingAdverstierDAO.loadAdvertisersLazyMode(first, pageSize,
				sortField, b, filters);
	}

	@Override
	public int getNumOfAdvertiserRows(Map<String, Object> filters) {
		return roomingAdverstierDAO.getNumOfAdvertiserRows(filters);
	}

	@Override
	public List<RoomAdvertiser> findAllUnValidateAdvisors(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		return roomingAdverstierDAO.findAllUnValidateAdvisors(first, pageSize,
				sortField, b, filters);
	}

	@Override
	public List getAllRoomAdvertisers() {
		return roomingAdverstierDAO.getAllRoomAdvertisers();
	}

	@Override
	public List<RoomAdvertiserCity> getRoomAdvertiserInCities() {
		return roomingAdverstierDAO.getRoomAdvertiserInCities();
	}

	@Override
	public Long getNofOfflineUsers() {
		return roomingAdverstierDAO.getNofOfflineUsers();
	}

}
