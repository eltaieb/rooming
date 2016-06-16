package com.iti.rooming.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
	static Properties prop = null;
	static InputStream input = null;

	public static final String UPLOAD_PROFILE_IMAGES_PATH = "UPLOAD_PROFILE_IMAGES_PATH";
	public static final String UPLOAD_ADVERTISER_VERFICATION_DOCUMENTS_PATH = "UPLOAD_ADVERTISER_VERFICATION_DOCUMENTS_PATH";
	public static final String MAX_WEBSOCKET_THREAD_POOL = "MAX_WEBSOCKET_THREAD_POOL";
	public static final String UPLOADED_FACILITY_IMAGES_PATH = "UPLOADED_FACILITY_IMAGES_PATH";
	public static final String UPLOADED_ROOM_IMAGES_PATH = "UPLOADED_ROOM_IMAGES_PATH";
	public static final String FACILITY_IMAGES_PATH = "FACILITY_IAMGES_PATH";
	public static final String VERIFICATION_PATH = "VERIFICATION_PATH";
	public static final String OWNER_IMAGES_PATH = "OWNER_IMAGES_PATH";
	public static final String ROOM_PATH = "ROOM_PATH";
	public static final String RANDOM_ACCESS_TOKEN_LENGTH = "RANDOM_ACCESS_TOKEN_LENGTH";
	public static final String APPLICATION_URL = "APPLICATION_URL";
	public static final String GENERAL_URL_LENGTH = "GENERAL_URL_LENGTH";
	public static final String RESET_PASSWORD_LENGTH = "RESET_PASSWORD_LENGTH";
	public static final String PROFILE_IMAGE = "PROFILE_IMAGE";

	static {
		prop = new Properties();
		try {
			input = Configurations.class
					.getResourceAsStream("config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		return value;
	}

}
