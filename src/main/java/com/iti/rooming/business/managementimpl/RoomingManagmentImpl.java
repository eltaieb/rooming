package com.iti.rooming.business.managementimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.business.service.AmenityService;
import com.iti.rooming.business.service.AutherirtyService;
import com.iti.rooming.business.service.FacilityAmenityService;
import com.iti.rooming.business.service.FacilityRoleService;
import com.iti.rooming.business.service.FacilityService;
import com.iti.rooming.business.service.LookupService;
import com.iti.rooming.business.service.MessageService;
import com.iti.rooming.business.service.RoleService;
import com.iti.rooming.business.service.RoomSeekerService;
import com.iti.rooming.business.service.RoomService;
import com.iti.rooming.business.service.RoomingAdvertiserService;
import com.iti.rooming.business.service.SeekerFacilityService;
import com.iti.rooming.business.service.SeekerFavouriteFacilityService;
import com.iti.rooming.business.service.SelectionViewService;
import com.iti.rooming.common.dto.FacilitySelectionCriteria;
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
import com.iti.rooming.common.entity.FacilityAmenity;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.FacilityRole;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.common.entity.Message;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.entity.SeekerFavouriteFacility;
import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utills.SeekerRoomWrapper;
import com.iti.rooming.common.utils.Bounds;
import com.iti.rooming.common.utils.SeekerFacilityWrapper;

@Stateless
public class RoomingManagmentImpl implements RoomingManagment {

	@EJB
	private FacilityService facilityService;
	@EJB
	private RoomingAdvertiserService roomAdvertiserService;
	@EJB
	private SeekerFacilityService seekerFacilityService;
	@EJB
	private AmenityService amenityService;
	@EJB
	private LookupService lookupService;
	@EJB
	private RoomService roomService;
	@EJB
	private FacilityAmenityService facilityAmenityService;
	@EJB
	private MessageService messageService;
	@EJB
	private RoomSeekerService roomSeekerService;
	@EJB
	private RoleService roleService;
	@EJB
	private FacilityRoleService facilityRoleService;
	@EJB
	private SelectionViewService selectionViewService;
	@EJB
	private SeekerFavouriteFacilityService seekerFavouriteFacilityService;

	@EJB
	private AutherirtyService autherirtyService;

	public Facility addOrUpdateFacility(Facility facility)
			throws RoomingException {
		List<Room> facilityRooms = facility.getRooms();
		List<FacilityImage> facilityImages = facility.getImages();
		List<Amenity> facilityAmenities = facility.getAmenities();
		List<Role> facilityRoles = facility.getRoles();
		// list image
		Facility addedFacility = facilityService.addOrUpdateFacility(facility);
		for (FacilityImage image : facilityImages) {
			FacilityImage facilityImage = new FacilityImage();
			facilityImage.setFacility(addedFacility);
			facilityImage.setImage(image.getImage());
			facilityService.addOrUpdateFacilityImage(facilityImage);
		}
		// /Add Rooms
		for (Room room : facilityRooms) {
			room.setFacility(addedFacility);
			List<RoomImage> roomImages = room.getImages();
			// add room
			room = roomService.addOrUpdateRoom(room);

			for (RoomImage image : roomImages) {
				RoomImage roomImage = new RoomImage();
				roomImage.setImage(image.getImage());
				roomImage.setRoom(room);
				roomService.addOrUpdateRoomImage(roomImage);
			}

		}
		// insert new updates of amenities and roles
		for (Amenity amenity : facilityAmenities) {
			FacilityAmenity facilityAmenity = new FacilityAmenity();
			facilityAmenity.setAmenity(amenity);
			facilityAmenity.setFacility(addedFacility);
			facilityAmenityService.addOrUpdate(facilityAmenity);
		}
		for (Role role : facilityRoles) {
			FacilityRole facilityRole = new FacilityRole();
			facilityRole.setRole(role);
			facilityRole.setFacility(addedFacility);
			facilityRoleService.saveOrUpdate(facilityRole);
		}
		return addedFacility;

	}

	public void addOrUpdateFacilityAmenity(Facility facility,
			List<Amenity> amenities) throws RoomingException {
		FacilityAmenity singleAmenity;
		for (Amenity a : amenities) {
			singleAmenity = new FacilityAmenity();
			singleAmenity.setAmenity(a);
			singleAmenity.setFacility(facility);
			facilityAmenityService.addOrUpdate(singleAmenity);
		}
	}

	@Override
	public List<Facility> getAllFacilities() throws RoomingException {
		return null;
	}

	public List<Facility> getAllFacilitiesWithBounds(Bounds bounds)
			throws RoomingException {
		return facilityService.getAll(bounds);

	}

	@Override
	public void sendResetPasswordURL(String email) throws RoomingException,
			NoSuchAlgorithmException, AddressException, MessagingException,
			IOException {
		roomAdvertiserService.sendResetPasswordURL(email);
	}

	@Override
	public void updatePassword(String password, String token)
			throws RoomingException {
		roomAdvertiserService.updatePassword(password, token);

	}

	@Override
	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityService.subscribeSeeker(roomSeekerWrapper);
	}

	@Override
	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityService.unsubscribeSeeker(roomSeekerWrapper);
	}

	@Override
	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityService.updateSubscriptionSeeker(roomSeekerWrapper);
	}

	@Override
	public Long getNumOfFacilityRows(Map<String, Object> filters) {
		return facilityService.getNumOfFacilityRows(filters);
	}

	@Override
	public void saveOrUpdateAmenity(Amenity amenity) throws RoomingException {
		amenityService.saveOrUpdate(amenity);
	}

	@Override
	public void deleteAmenity(Amenity amenity) throws RoomingException {
		amenityService.remove(amenity);
	}

	@Override
	public List<Amenity> getAllAmenity() throws RoomingException {
		return amenityService.getAllAmenities();
	}

	@Override
	public void saveOrUpdateFacility(Facility facility) throws RoomingException {
		facilityService.addOrUpdateFacility(facility);

	}

	@Override
	public Lookup getLookupByID(Long id) {
		return lookupService.getLookupByID(id);
	}

	@Override
	public List<Lookup> getLookupByCategory(Category category) {
		return lookupService.getLookupByCategory(category);
	}

	@Override
	public RoomAdvertiser addOrUpdateRoomAdvertiser(
			RoomAdvertiser roomAdvertiser) throws RoomingException {
		return roomAdvertiserService.addOrUpdateRoomAdvertiser(roomAdvertiser);
	}

	@Override
	public List<RoomAdvertiser> findUnValidAdvertisers() {
		return roomAdvertiserService.findUnValidAdvertisers();
	}

	@Override
	public List<RoomAdvertiser> findRoomAdvertisers() throws RoomingException {
		return roomAdvertiserService.findRoomAdvertisers();
	}

	@Override
	public void saveChatMessage(RoomingChatMessage roomingChatMessage) {
		messageService.save(roomingChatMessage);
	}

	@Override
	public List<SeekerFacilityWrapper> getSeekers(RoomAdvertiser roomAdvertiser) {
		return messageService.getRoomSeekers(roomAdvertiser);
	}

	@Override
	public List<Message> getAllMessages(RoomAdvertiser roomAdvertiser) {
		return messageService.getAllMessages(roomAdvertiser);
	}

	@Override
	public RoomSeeker getSeekerById(Long id) {
		return roomSeekerService.getRoomSeekerById(id);
	}

	@Override
	public void saveChatMessage(Message message) {
		messageService.save(message);
	}

	@Override
	public RoomAdvertiser loginRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		return roomAdvertiserService.login(roomAdvertiser);
	}

	/* FACILITY AND FACILITY IMAGES RELATED FUNCTIONS */
	@Override
	public Facility getFacility(Long id) {
		Facility facility = facilityService.findFacility(id);
		if (facility == null) {
			return null;
		} else {
			/* 1 ROOMS */
			List<Room> facilityRooms = roomService.getRoomsOfFacility(facility);
			for (Room room : facilityRooms) {
				List<RoomImage> roomImages = new ArrayList<RoomImage>();
				roomImages = roomService.getRoomImageOfRoom(room);
				room.setImages(roomImages);
			}
			facility.setRooms(facilityRooms);
			if (facility.getRooms() == null)
				facility.setRooms(new ArrayList());
			/* 2 ADVERTISOR */
			facility.getRoomAdvertiser();
			/* 3 IMAGE */
			List<FacilityImage> facilityImages = facilityService
					.getFacilityImagesOfFacility(facility);
			facility.setImages(facilityImages);
			if (facility.getImages() == null)
				facility.setImages(new ArrayList());
			/* 4 AMENITIES */
			facility.setAmenities(facilityAmenityService
					.getAmenitiesOfFacility(facility));
			/* 5 ROLE */
			facility.setRoles(facilityRoleService.getRolesOfFacility(facility));

			return facility;
		}
	}

	@Override
	public Facility addOrUpdateFacility(Facility facility,
			List<Amenity> amenities, List<Role> roles) throws RoomingException {
		List<Room> facilityRooms = facility.getRooms();
		List<FacilityImage> facilityImages = facility.getImages();
		// add facility
		Facility addedFacility = facilityService.addOrUpdateFacility(facility);
		// add facility images
		if (facilityImages != null) {
			for (FacilityImage image : facilityImages) {
				FacilityImage facilityImage = new FacilityImage();
				facilityImage.setFacility(addedFacility);
				facilityImage.setImage(image.getImage());
				facilityService.addOrUpdateFacilityImage(facilityImage);
			}
		}

		// add facility rooms
		if (facilityRooms != null) {
			for (Room room : facilityRooms) {
				room.setFacility(addedFacility);
				List<RoomImage> roomImages = room.getImages();
				// add room
				room = roomService.addOrUpdateRoom(room);
				// add room images
				if (roomImages != null) {
					for (RoomImage image : roomImages) {
						RoomImage roomImage = new RoomImage();
						roomImage.setImage(image.getImage());
						roomImage.setRoom(room);
						roomService.addOrUpdateRoomImage(roomImage);
					}
				}
			}
		}

		// add facility amenities
		if (amenities != null) {
			for (Amenity a : amenities) {
				FacilityAmenity singleAmenity;
				singleAmenity = new FacilityAmenity();
				singleAmenity.setAmenity(a);
				singleAmenity.setFacility(addedFacility);
				facilityAmenityService.addOrUpdate(singleAmenity);
			}
		}

		// add facility roles
		if (roles != null) {
			for (Role role : roles) {
				FacilityRole facilityRole;
				facilityRole = new FacilityRole();
				facilityRole.setFacility(addedFacility);
				facilityRole.setRole(role);
				facilityRoleService.saveOrUpdate(facilityRole);
			}
		}

		return addedFacility;
	}

	@Override
	public List<FacilityImage> getFacilityImagesByFacility(Facility facility) {
		// TODO Auto-generated method stub
		return null;
	}

	/* AMENITY RELATED FUNCTIONS */
	public List<Amenity> getAllAmenities() {
		return amenityService.getAllAmenities();
	}

	public Amenity getAmenity(Long id) {
		return amenityService.getAmenityById(id);
	}

	public List<Amenity> getAmenitiesByFacility(Facility facility) {
		return amenityService.getAmenityByFacility(facility);
	}

	/* AMENITY RELATED FUNCTIONS */

	@Override
	public List<Role> getRolesByFacility(Facility facility) {
		return roleService.getRoleByFacility(facility);
	}

	public List<Role> getAllRoles() {
		return roleService.getAll();
	}

	public Role getRole(Long id) {
		return roleService.find(id);
	}

	@Override
	public List<Room> getRoomsAndThierImageByFacility(Facility facility) {
		List<Room> rooms = roomService.getRoomsOfFacility(facility);
		for (Room room : rooms) {
			List<RoomImage> images = roomService.getRoomImageOfRoom(room);
			room.setImages(images);
		}
		return rooms;
	}

	/* | ---------------------------------------- | */

	@Override
	public List<Facility> getAllWithCritria() throws RoomingException {

		return facilityService.getAllWithCritria();
	}

	@Override
	public List<Facility> getFacilitiesByBoundariesAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice, Bounds boundary) {
		List<Facility> facilities = facilityService
				.getFacilityByBoundaryAndPrice(minimumPrice, maximumPrice,
						boundary);
		return facilities;

	}

	@Override
	public List<FacilityWrapper> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteria facilitySelectionCriteria) {
		return selectionViewService
				.getAllFacilitiesWithCriteria(facilitySelectionCriteria);
	}

	@Override
	public List<RoomWrapper> getAllRoomOfFacility(Long facilityID) {
		return roomService.getAllRoomOfFacility(facilityID);
	}

	@Override
	public void addFavouriteFacility(FavouriteWrapper favouriteWrapper) {
		seekerFavouriteFacilityService
				.addFavourite(new SeekerFavouriteFacility(new RoomSeeker(
						favouriteWrapper.getSeekerId()), new Facility(
						favouriteWrapper.getFacilityId())));

	}

	@Override
	public void removeFavouriteFacility(FavouriteWrapper favouriteWrapper) {
		seekerFavouriteFacilityService.removeFavourite(
				favouriteWrapper.getSeekerId(),
				favouriteWrapper.getFacilityId());
	}

	@Override
	public List<FacilityWrapper> getAllFavourite(Long seekerId) {
		return seekerFavouriteFacilityService.getAll(seekerId);
	}

	@Override
	public SeekerIdentityWrapper registerSeeker(
			RegistrationWrapper registrationWrapper) {
		return roomSeekerService.register(registrationWrapper);
	}

	@Override
	public SeekerWrapper loginSeeker(LoginWrapper loginWrapper) {
		return roomSeekerService.login(loginWrapper);
	}

	@Override
	public SeekerIdentityWrapper registerSeeker(
			SocialRegistrationWrapper registrationWrapper) {
		return roomSeekerService.register(registrationWrapper);
	}

	@Override
	public OwnerWrapper getOwnerProfile(Long ownerId) {
		return roomAdvertiserService.getProfile(ownerId);
	}

	@Override
	public Boolean resetSeekerPassword(ResetPasswordWrapper resetPasswordWrapper) {
		return roomSeekerService.resetPssword(resetPasswordWrapper);
	}

	@Override
	public Boolean validateSeekerResetPasswordToken(RoomSeeker seeker) {
		return roomSeekerService.validateResetPasswordToken(seeker);
	}

	@Override
	public Boolean updateSeekerPassword(RoomSeeker roomSeeker) {
		return roomSeekerService.updatePassword(roomSeeker);
	}

	@Override
	public RoomSeeker activeSeekerAccount(RoomSeeker roomSeeker) {
		return roomSeekerService.activeAccount(roomSeeker);
	}

	@Override
	public List<OwnerWrapper> getChatOwnersOfSeeker(Long seekerId) {
		return messageService.getChatOwnersOfSeeker(seekerId);
	}

	@Override
	public List getAllMessagesTo(ChatRequestWrapper chatRequestWrapper) {
		return messageService.getAllMessagesTo(chatRequestWrapper);
	}

	@Override
	public User Authlogin(String username) {
		return autherirtyService.LoginAuthentication(username);
	}

	@Override
	public Boolean updateSeekerProfile(SeekerWrapper seekerWrapper) {
		return roomSeekerService.updateProfile(seekerWrapper);
	}

	@Override
	public Facility updateFacility(Facility facility, List<Amenity> amenities,
			List<Role> roles) throws RoomingException {

		List<Room> facilityRooms = facility.getRooms();
		List<FacilityImage> facilityImages = facility.getImages();
		// update facility
		facility = facilityService.addOrUpdateFacility(facility);
		// delete amenity And roles
		facilityAmenityService.removeAmenityByFacility(facility);
		facilityRoleService.removeRoleByFacility(facility);
		// insert new updates of amenities and roles
		for (Amenity amenity : amenities) {
			FacilityAmenity facilityAmenity = new FacilityAmenity();
			facilityAmenity.setAmenity(amenity);
			facilityAmenity.setFacility(facility);
			facilityAmenityService.addOrUpdate(facilityAmenity);
		}
		for (Role role : roles) {
			FacilityRole facilityRole = new FacilityRole();
			facilityRole.setRole(role);
			facilityRole.setFacility(facility);
			facilityRoleService.saveOrUpdate(facilityRole);
		}

		// Images
		// 1) Newly Added
		for (int index = 0; index < facilityImages.size(); index++) {
			// FacilityImage image = facilityImages.get(index);
			// image.setFacility(facility);
			facilityImages.get(index).setFacility(facility);
			FacilityImage image = facilityService
					.addOrUpdateFacilityImage(facilityImages.get(index));
			facilityImages.set(index, image);
			// image = facilityService.addOrUpdateFacilityImage(image);
			// facilityImages.
			// facilityImages.remove(index);
			// facilityImages.add(image);
		}
		// 2) Deleted
		facilityService.removeDeletedFacilityImages(facilityImages, facility);

		// Room Cases :
		// 1) Newly Added Rooms
		// 2) Updated Rooms
		for (int index = 0; index < facilityRooms.size(); index++) {
			Room room = facilityRooms.get(index);
			room.setFacility(facility);
			List<RoomImage> roomImages = room.getImages();
			room = roomService.addOrUpdateRoom(room);
			for (RoomImage roomImage : roomImages) {
				roomImage.setRoom(room);
				roomService.addOrUpdateRoomImage(roomImage);
			}
			facilityRooms.remove(index);
			facilityRooms.add(room);
		}

		// 3) Deleted Rooms
		roomService.removeDeletedRooms(facilityRooms, facility);
		return facility;
	}

	@Override
	public List<SeekerRoomWrapper> getAllseekerRoomWrappers(
			RoomAdvertiser roomAdvertiser) {
		return messageService.getAll(roomAdvertiser);
	}

	@Override
	public List<SeekerRoomWrapper> getAllseekerRoomWrappers(
			Facility selectedFacility, RoomAdvertiser roomAdvertiser) {
		return messageService.getAllMessages(selectedFacility, roomAdvertiser);
	}

	@Override
	public List<String> getAllMessages(RoomAdvertiser roomAdvertiser,
			SeekerRoomWrapper selectedSeekerRoomWrapper) {
		return messageService.getAllMessages(roomAdvertiser,
				selectedSeekerRoomWrapper);
	}

	@Override
	public List<Facility> getAllFacilities(RoomAdvertiser roomAdvertiser) {
		return facilityService.getAll(roomAdvertiser);
	}

	@Override
	public int getNumOfAdvertiserRows(Map<String, Object> filters) {
		return roomAdvertiserService.getNumOfAdvertiserRows(filters);
	}

	@Override
	public List<RoomAdvertiser> loadAdvertisersLazyMode(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		return roomAdvertiserService.loadAdvertisersLazyMode(first, pageSize,
				sortField, b, filters);
	}

	@Override
	public List<RoomAdvertiser> findAllUnValidateAdvisors(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		return roomAdvertiserService.findAllUnValidateAdvisors(first, pageSize,
				sortField, b, filters);
	}

	@Override
	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser) {
		return facilityService.getAllFacilitiesOf(roomAdvertiser);
	}

	@Override
	public List getAllRoomAdvertisers() {
		return roomAdvertiserService.getAllRoomAdvertisers();
	}

	@Override
	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters) {
		return facilityService.loadFacility(first, pageSize, sortField, b, filters);
	}
}