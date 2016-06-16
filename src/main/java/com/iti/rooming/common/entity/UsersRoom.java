package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the users_rooms database table.
 * 
 */
@Entity
@Table(name = "users_rooms")
@NamedQuery(name = "UsersRoom.findAll", query = "SELECT u FROM UsersRoom u")
public class UsersRoom extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// bi-directional many-to-one association to RoomSeeker
	@ManyToOne
	@JoinColumn(name = "apartment_searcher_id", referencedColumnName = "id")
	private RoomSeeker roomSeeker;

	// bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;

	public UsersRoom() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomSeeker getRoomSeeker() {
		return this.roomSeeker;
	}

	public void setRoomSeeker(RoomSeeker roomSeeker) {
		this.roomSeeker = roomSeeker;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}