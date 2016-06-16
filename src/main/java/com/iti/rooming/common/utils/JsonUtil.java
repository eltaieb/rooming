package com.iti.rooming.common.utils;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class JsonUtil {

	private static Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(JsonUtil.class.getName());

	public static String getJson(Object obj) {
		try {
			return gson.toJson(obj);
		} catch (Exception e) {
			logger.error("Error in parsing object to json", e);
		}
		return "";
	}

	public static <T> T jsonToObject(String json, Class<T> clz) {
		try {
			return gson.fromJson(json, clz);
		} catch (Exception e) {
			logger.error("Error in parsing json to object", e);
		}
		return null;
	}
}
