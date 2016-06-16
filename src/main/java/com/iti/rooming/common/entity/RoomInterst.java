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
 * The persistent class for the room_intersts database table.
 * 
 */
@Entity
@Table(name = "room_intersts")
@NamedQuery(name = "RoomInterst.findAll", query = "SELECT r FROM RoomInterst r")
public class RoomInterst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// bi-directional many-to-one association to Lookup
	@ManyToOne
	@JoinColumn(name = "interest_lookup_id", referencedColumnName = "id")
	private Lookup lookup;

	// bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;

	public RoomInterst() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lookup getLookup() {
		return this.lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}