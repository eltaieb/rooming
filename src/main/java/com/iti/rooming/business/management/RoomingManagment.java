package com.iti.rooming.business.management;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.iti.rooming.common.dto.FacilityCity;
import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.RoomAdvertiserCity;
import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.dto.wrapper.ChatRequestWrapper;
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.dto.wrapper.FavouriteWrapper;
import com.iti.rooming.common.dto.wrapper.LoginWrapper;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.dto.wrapper.RegistrationWrapper;
import com.iti.rooming.common.dto.wrapper.ResetPasswordWrapper;
import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerIdentityWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerWrapper;
import com.iti.rooming.common.dto.wrapper.SocialRegistrationWrapper;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.Bounds;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;

@Local
public interface RoomingManagment {

	// Authlogin

	public List<Facility> getAllWithCritria() throws RoomingException;

	public List<SeekerRoomWrapper> getAllseekerRoomWrappers(
			RoomAdvertiser roomAdvertiser);

	public List<SeekerRoomWrapper> getAllseekerRoomWrappers(
			Facility selectedFacility, RoomAdvertiser roomAdvertiser);

	public List<String> getAllMessages(RoomAdvertiser roomAdvertiser,
			SeekerRoomWrapper selectedSeekerRoomWrapper);

	public List<RoomAdvertiser> findRoomAdvertisers() throws RoomingException;

	public List<Facility> getAllFacilities(RoomAdvertiser roomAdvertiser);

	public List<Facility> getAllFacilities() throws RoomingException;

	public List<Facility> getAllFacilitiesWithBounds(Bounds bounds)
			throws RoomingException;

	public void saveOrUpdateFacility(Facility facility) throws RoomingException;

	public Long getNumOfFacilityRows(Map<String, Object> filters);

	public void sendResetPasswordURL(String email) throws RoomingException,
			NoSuchAlgorithmException, AddressException, MessagingException,
			IOException;

	public void updatePassword(String password, String token)
			throws RoomingException;

	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public List<Amenity> getAllAmenity() throws RoomingException;

	public void saveOrUpdateAmenity(Amenity amenity) throws RoomingException;

	public void deleteAmenity(Amenity amenity) throws RoomingException;

	public List<Lookup> getLookupByCategory(Category identificationType);

	/* FACILITY AND FACILITY IMAGES */

	public Facility getFacility(Long long1);

	public List<FacilityImage> getFacilityImagesByFacility(Facility facility);

	public void addOrUpdateFacilityAmenity(Facility facility,
			List<Amenity> amenities) throws RoomingException;

	public Facility addOrUpdateFacility(Facility facility,
			List<Amenity> amenities, List<Role> roles) throws RoomingException;

	public Facility addOrUpdateFacility(Facility facility)
			throws RoomingException;

	/* ROOM AND ROOMIMAGE FUNCTIONALITY */
	public List<Room> getRoomsAndThierImageByFacility(Facility facility);

	/* AMENITY RELATED FUNCTIONALITY */
	public List<Amenity> getAmenitiesByFacility(Facility facility);

	public List<Amenity> getAllAmenities();

	public Amenity getAmenity(Long id);

	/* ROLES RELATED FUNCTIONALITY */
	public List<Role> getRolesByFacility(Facility facility);

	public List<Role> getAllRoles();

	public Role getRole(Long id);

	/* | ---------------------------------------- | */
	public Lookup getLookupByID(Long long1);

	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) throws RoomingException;

	public RoomAdvertiser loginRoomAdvertiser(RoomAdvertiser roomAdvertiser);

	public List<RoomAdvertiser> findUnValidAdvertisers();

	public void saveChatMessage(RoomingChatMessage roomingChatMessage);

	public void saveChatMessage(Message message);

	public List<SeekerFacilityWrapper> getSeekers(RoomAdvertiser roomAdvertiser);

	public RoomSeeker getSeekerById(Long id);

	public List<Message> getAllMessages(RoomAdvertiser roomAdvertiser);

	public List<Facility> getFacilitiesByBoundariesAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice, Bounds boundary);

	public User Authlogin(String username);

	public List<FacilityWrapper> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteria facilitySelectionCriteria);

	public List<RoomWrapper> getAllRoomOfFacility(Long facilityID);

	public void addFavouriteFacility(FavouriteWrapper favouriteWrapper);

	public void removeFavouriteFacility(FavouriteWrapper favouriteWrapper);

	public List<FacilityWrapper> getAllFavourite(Long seekerId);

	public SeekerIdentityWrapper registerSeeker(
			RegistrationWrapper registrationWrapper);

	public SeekerWrapper loginSeeker(LoginWrapper loginWrapper);

	public SeekerIdentityWrapper registerSeeker(
			SocialRegistrationWrapper registrationWrapper);

	public OwnerWrapper getOwnerProfile(Long ownerId);

	public Boolean resetSeekerPassword(ResetPasswordWrapper resetPasswordWrapper);

	public Boolean validateSeekerResetPasswordToken(RoomSeeker seeker);

	public Boolean updateSeekerPassword(RoomSeeker roomSeeker);

	public RoomSeeker activeSeekerAccount(RoomSeeker roomSeeker);

	public List<OwnerWrapper> getChatOwnersOfSeeker(Long seekerId);

	public List getAllMessagesTo(ChatRequestWrapper chatRequestWrapper);

	public Boolean updateSeekerProfile(SeekerWrapper seekerWrapper);

	public Facility updateFacility(Facility facility,
			List<Amenity> selectedAmenities, List<Role> selectedRoles)
			throws RoomingException;

	public int getNumOfAdvertiserRows(Map<String, Object> filters);

	public List<RoomAdvertiser> loadAdvertisersLazyMode(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	public List<RoomAdvertiser> findAllUnValidateAdvisors(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser);

	public List getAllRoomAdvertisers();

	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters);

	public List<FacilityCity> getFacilitiesInCities();

	public List<RoomAdvertiserCity> getRoomAdvertiserInCities();

	public Long getNofOfflineUsers();

	
	List<Amenity> loadAmenities(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);
	int getNumOfAmenitiesRows(Map<String, Object> filters);
	public void updateAmenity(Amenity amenity);
	public void updateRole(Role role);
	List<Role> loadRoles(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);
	int getNumOfRolesRows(Map<String, Object> filters);

}
