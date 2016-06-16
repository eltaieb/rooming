package com.iti.rooming.common.utils;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

import com.iti.rooming.common.config.Configurations;

public class TokenGenerator {

	public static String generate(Long id) {
		Integer length = Integer.parseInt(Configurations
				.getProperty(Configurations.RANDOM_ACCESS_TOKEN_LENGTH));
		RandomString randomString = new RandomString(length);
		String random = randomString.getRandomString();
		String hash;
		try {
			hash = EncryptionMD5.getMD5(random
					+ id
					+ ThreadLocalRandom.current().nextLong(
							ThreadLocalRandom.current().nextLong(
									1000 * 1000 * 1000)));
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
