package com.iti.rooming.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.map.LatLng;

import com.iti.rooming.common.dto.geolocation.GeoLocationDetails;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.enums.WebMethodType;
import com.iti.rooming.common.enums.WebParamType;
import com.iti.rooming.portal.utils.WebUtils;

public class MapUtil {
	
	public static GeoLocationDetails getLocationFromLatLng(LatLng event){
		
		String addressWS = "https://maps.googleapis.com/maps/api/geocode/json";
    	Map<String, String> addressParams = new HashMap<String,String>();
    	addressParams.put("latlng", event.getLat()+","+event.getLng());
    	String address = WebUtils.callWebService(addressWS , WebMethodType.GET, WebParamType.QUERY, addressParams);
        System.out.println(address);
       return Utils.jsonStringToObject(address, GeoLocationDetails.class);
        
	}

	public static GeoLocationDetails getLocationFromAddress (Facility value){
		
		String addressWS = "https://maps.googleapis.com/maps/api/geocode/json";
    	Map<String, String> addressParams = new HashMap<String,String>();
    	StringBuilder address = new StringBuilder();
    	if(! "".equals(value.getStreet().trim())){
    		address.append(value.getStreet()+",");
    	}
    	if(! "".equals(value.getCity().trim())){
    		address.append(value.getCity()+",");
    	}
    	if(! "".equals(value.getCountry().trim())){
    		address.append(value.getCountry());
    	}
    	String addressString = address.toString();
		addressParams.put("address", addressString);
    	String jsonLocation = WebUtils.callWebService(addressWS , WebMethodType.GET, WebParamType.QUERY, addressParams);
        System.out.println(jsonLocation);
       return Utils.jsonStringToObject(jsonLocation, GeoLocationDetails.class);

	}
}
