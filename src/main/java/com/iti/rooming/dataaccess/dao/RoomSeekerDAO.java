package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoomSeekerDAO {

	public List<RoomSeeker> getAll();

	public RoomSeeker getRoomSeekerById(Long id);

	public void saveOrUpdate(RoomSeeker RoomSeeker);

	public void remove(RoomSeeker RoomSeeker);

	public RoomSeeker register(RoomSeeker roomSeeker);

	public RoomSeeker login(RoomSeeker roomSeeker);

	public RoomSeeker checkEmailValidation(String email);

	public Boolean validateResetPasswordToken(RoomSeeker seeker);

	public Boolean updatePassword(RoomSeeker roomSeeker);

	public void setResetValidationTime(RoomSeeker roomSeeker);

	public RoomSeeker activeAccount(RoomSeeker roomSeeker);

	public Boolean updateProfile(RoomSeeker roomSeeker);

}
