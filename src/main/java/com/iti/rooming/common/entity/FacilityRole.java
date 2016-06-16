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

@Entity
@Table(name="facility_roles")
@NamedQuery(name="FacilityRole.findAll", query="SELECT f FROM FacilityRole f")
public class FacilityRole extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="id")
	private Role role;

	//bi-directional many-to-one association to Facility
	@ManyToOne
	@JoinColumn(name="facility_id", referencedColumnName="id")
	private Facility facility;

	public FacilityRole() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}