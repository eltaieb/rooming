package com.iti.rooming.common.utils;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

import com.iti.rooming.common.config.Configurations;

public class URLGenerator {

	public static String generateUrl(Long id, String page) {

		Integer length = Integer.parseInt(Configurations
				.getProperty(Configurations.GENERAL_URL_LENGTH));
		if (length != null && length > 0) {
			RandomString randomString = new RandomString(length);
			String random = randomString.getRandomString();
			String hash = "";
			String url = "";
			hash = TokenGenerator.generate(id);
			url = Configurations.getProperty(Configurations.APPLICATION_URL)
					+ page + "?token=" + hash;

			return url;
		} else {
			return "";
		}
	}
}
