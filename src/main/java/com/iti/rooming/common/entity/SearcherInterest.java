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
 * The persistent class for the searcher_interests database table.
 * 
 */
@Entity
@Table(name = "searcher_interests")
@NamedQuery(name = "SearcherInterest.findAll", query = "SELECT s FROM SearcherInterest s")
public class SearcherInterest extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// bi-directional many-to-one association to RoomSeeker
	@ManyToOne
	@JoinColumn(name = "apartment_searcher_id", referencedColumnName = "id")
	private RoomSeeker roomSeeker;

	// bi-directional many-to-one association to Lookup
	@ManyToOne
	@JoinColumn(name = "lookup_id", referencedColumnName = "id")
	private Lookup lookup;

	public SearcherInterest() {
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

	public Lookup getLookup() {
		return this.lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

}