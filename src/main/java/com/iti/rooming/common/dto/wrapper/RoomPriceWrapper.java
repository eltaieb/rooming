package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

public class RoomPriceWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double minPrice;
	private Double maxPrice;
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

}
