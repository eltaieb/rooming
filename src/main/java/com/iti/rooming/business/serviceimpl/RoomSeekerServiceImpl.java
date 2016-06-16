package com.iti.rooming.business.serviceimpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.iti.rooming.business.service.RoomSeekerService;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.dto.wrapper.LoginWrapper;
import com.iti.rooming.common.dto.wrapper.RegistrationWrapper;
import com.iti.rooming.common.dto.wrapper.ResetPasswordWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerIdentityWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerWrapper;
import com.iti.rooming.common.dto.wrapper.SocialRegistrationWrapper;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.EncryptionMD5;
import com.iti.rooming.common.utils.Mail;
import com.iti.rooming.common.utils.RandomString;
import com.iti.rooming.common.utils.TokenGenerator;
import com.iti.rooming.common.utils.URLGenerator;
import com.iti.rooming.dataaccess.dao.RoomSeekerDAO;

@Stateless
public class RoomSeekerServiceImpl implements RoomSeekerService {

	@EJB
	private RoomSeekerDAO roomSeekerDao;

	@Override
	public List<RoomSeeker> getAll() {
		return roomSeekerDao.getAll();
	}

	@Override
	public RoomSeeker getRoomSeekerById(Long id) {
		return roomSeekerDao.getRoomSeekerById(id);
	}

	@Override
	public void saveOrUpdate(RoomSeeker RoomSeeker) throws RoomingException {
		roomSeekerDao.saveOrUpdate(RoomSeeker);

	}

	@Override
	public void remove(RoomSeeker RoomSeeker) throws RoomingException {
		roomSeekerDao.remove(RoomSeeker);
	}

	@Override
	public SeekerIdentityWrapper register(
			RegistrationWrapper registrationWrapper) {
		RoomSeeker roomSeeker = convertWrapperToRoomSeeker(registrationWrapper);
		SeekerIdentityWrapper roomSeekerByIdentity = getRoomSeekerByIdentity(roomSeeker);
		return roomSeekerByIdentity;

	}

	private RoomSeeker convertWrapperToRoomSeeker(
			RegistrationWrapper registrationWrapper) {
		RoomSeeker roomSeeker = new RoomSeeker();
		roomSeeker.setEmail(registrationWrapper.getEmail());
		roomSeeker.setFirstName(registrationWrapper.getfName());
		roomSeeker.setLastName(registrationWrapper.getlName());
		roomSeeker.setPassword(registrationWrapper.getPassword());
		roomSeeker.setGender(registrationWrapper.getGender());
		roomSeeker = roomSeekerDao.register(roomSeeker);
		return roomSeeker;
	}

	@Override
	public SeekerIdentityWrapper register(
			SocialRegistrationWrapper registrationWrapper) {
		RoomSeeker roomSeeker = convetSocialWrapperToSeeker(registrationWrapper);
		roomSeeker = roomSeekerDao.register(roomSeeker);
		return getRoomSeekerByIdentity(roomSeeker);
	}

	private RoomSeeker convetSocialWrapperToSeeker(
			SocialRegistrationWrapper registrationWrapper) {
		RoomSeeker roomSeeker = new RoomSeeker();
		roomSeeker.setEmail(registrationWrapper.getEmail());
		roomSeeker.setFirstName(registrationWrapper.getfName());
		roomSeeker.setLastName(registrationWrapper.getlName());
		roomSeeker.setGender(registrationWrapper.getGender());
		String token = registrationWrapper.getToken();
		if (registrationWrapper.getIsFacebook()) {
			roomSeeker.setFacebookToken(token);
		} else {
			roomSeeker.setGooglePlusToken(token);
		}
		return roomSeeker;
	}

	private SeekerIdentityWrapper getRoomSeekerByIdentity(RoomSeeker roomSeeker) {
		if (roomSeeker != null) {
			String token = TokenGenerator.generate(roomSeeker.hashCode() + 0L);
			SeekerIdentityWrapper seekerIdentityWrapper = createSeekerIdentityWrapper(
					roomSeeker.getId(), token);
			roomSeeker.setToken(token);
			String url = URLGenerator.generateUrl(roomSeeker.getId(),
					"seeker/active.xhtml");
			String activationToken = url.substring(url.indexOf("=") + 1);
			roomSeeker.setActivationToken(activationToken);
			roomSeekerDao.saveOrUpdate(roomSeeker);
			sendMail(roomSeeker.getEmail(), url);
			return seekerIdentityWrapper;
		} else {
			return null;
		}
	}

	private SeekerIdentityWrapper createSeekerIdentityWrapper(Long id,
			String token) {
		SeekerIdentityWrapper seekerIdentityWrapper = new SeekerIdentityWrapper();
		seekerIdentityWrapper.setId(id);
		seekerIdentityWrapper.setToken(token);
		return seekerIdentityWrapper;
	}

	private void sendMail(String email, String url) {
		Mail.sendMail(email, url, Mail.ACTIVATION);
	}

	@Override
	public SeekerWrapper login(LoginWrapper loginWrapper) {
		RoomSeeker roomSeeker = new RoomSeeker();
		roomSeeker.setEmail(loginWrapper.getEmail());
		roomSeeker.setPassword(loginWrapper.getPassword());
		roomSeeker = roomSeekerDao.login(roomSeeker);
		return convertSeekerToWrapper(roomSeeker);
	}

	private SeekerWrapper convertSeekerToWrapper(RoomSeeker roomSeeker) {
		SeekerWrapper seekerWrapper = new SeekerWrapper();
		seekerWrapper.setEmail(roomSeeker.getEmail());
		seekerWrapper.setfName(roomSeeker.getFirstName());
		seekerWrapper.setlName(roomSeeker.getLastName());
		seekerWrapper.setGender(roomSeeker.getGender());
		seekerWrapper.setId(roomSeeker.getId());
		seekerWrapper.setToken(roomSeeker.getToken());
		return seekerWrapper;
	}

	@Override
	public Boolean resetPssword(ResetPasswordWrapper resetPasswordWrapper) {
		String email = resetPasswordWrapper.getEmail();
		RoomSeeker roomSeeker = checkEmailValidation(email);
		if (roomSeeker == null)
			return false;
		if (sendRestUrl(resetPasswordWrapper, roomSeeker)) {
			roomSeekerDao.saveOrUpdate(roomSeeker);
			return true;
		}
		return false;
	}

	private Boolean sendRestUrl(ResetPasswordWrapper resetPasswordWrapper,
			RoomSeeker roomSeeker) {
		String url = URLGenerator.generateUrl(resetPasswordWrapper.getId(),
				"pub/seeker/resetpassword.xhtml");

		roomSeeker.setResetUrlValidationTime(new Date());
		roomSeeker.setResetToken(url.substring(url.indexOf("=") + 1));
		roomSeekerDao.saveOrUpdate(roomSeeker);
		Mail.sendMail(resetPasswordWrapper.getEmail(), url, Mail.RESET_PASSWORD);
		return true;
	}

	private RoomSeeker checkEmailValidation(String email) {
		return roomSeekerDao.checkEmailValidation(email);

	}

	@Override
	public Boolean validateResetPasswordToken(RoomSeeker seeker) {
		return roomSeekerDao.validateResetPasswordToken(seeker);
	}

	@Override
	public Boolean updatePassword(RoomSeeker roomSeeker) {
		String plainText = roomSeeker.getPassword();
		String hash;
		try {
			hash = EncryptionMD5.getMD5(plainText);
			roomSeeker.setPassword(hash);
			return roomSeekerDao.updatePassword(roomSeeker);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public RoomSeeker activeAccount(RoomSeeker roomSeeker) {
		return roomSeekerDao.activeAccount(roomSeeker);
	}

	@Override
	public Boolean updateProfile(SeekerWrapper seekerWrapper) {
		RoomSeeker roomSeeker = new RoomSeeker();
		roomSeeker.setFirstName(seekerWrapper.getfName());
		roomSeeker.setLastName(seekerWrapper.getlName());
		roomSeeker.setEmail(seekerWrapper.getEmail());
		return roomSeekerDao.updateProfile(roomSeeker);

	}

}
