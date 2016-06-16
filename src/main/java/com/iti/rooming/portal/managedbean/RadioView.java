package com.iti.rooming.portal.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.iti.rooming.common.entity.FacilityImage;

@ManagedBean
public class RadioView {

	private String city;
	private List<String> cities;

	private FacilityImage facilityImage;
	private List<FacilityImage> images;

	@PostConstruct
	public void init() {
		cities = new ArrayList<String>();
		cities.add("Miami");
		cities.add("London");
		cities.add("Paris");
		cities.add("Istanbul");
		cities.add("Berlin");
		cities.add("Barcelona");
		cities.add("Rome");
		cities.add("Brasilia");
		cities.add("Amsterdam");

		FacilityImage img = new FacilityImage();
		img.setImage("Hello.jpg");

		images.add(img);
	}

	public FacilityImage getFacilityImage() {
		return facilityImage;
	}

	public void setFacilityImage(FacilityImage facilityImage) {
		this.facilityImage = facilityImage;
	}

	public List<FacilityImage> getImages() {

		FacilityImage img = new FacilityImage();
		img.setImage("Hello.jpg");

		images.add(img);
		return images;
	}

	public void setImages(List<FacilityImage> images) {
		this.images = images;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getCities() {
		return cities;
	}
}