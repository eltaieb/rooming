package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.LoginWrapper;
import com.iti.rooming.common.dto.wrapper.RegistrationWrapper;
import com.iti.rooming.common.dto.wrapper.ResetPasswordWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerIdentityWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerWrapper;
import com.iti.rooming.common.dto.wrapper.SocialRegistrationWrapper;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoomSeekerService {
	public List<RoomSeeker> getAll();

	public RoomSeeker getRoomSeekerById(Long id);

	public void saveOrUpdate(RoomSeeker RoomSeeker) throws RoomingException;

	public void remove(RoomSeeker RoomSeeker) throws RoomingException;

	public SeekerIdentityWrapper register(
			RegistrationWrapper registrationWrapper);

	public SeekerWrapper login(LoginWrapper loginWrapper);

	public SeekerIdentityWrapper register(
			SocialRegistrationWrapper registrationWrapper);

	public Boolean resetPssword(ResetPasswordWrapper resetPasswordWrapper);

	public Boolean validateResetPasswordToken(RoomSeeker seeker);

	public Boolean updatePassword(RoomSeeker roomSeeker);

	public RoomSeeker activeAccount(RoomSeeker roomSeeker);

	public Boolean updateProfile(SeekerWrapper seekerWrapper);

}
