package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private List<String> images= new ArrayList();
	private Double price;
	private String area;
	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
