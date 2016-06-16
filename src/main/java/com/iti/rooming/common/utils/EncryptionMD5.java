package com.iti.rooming.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionMD5 {

	public static String getMD5(String plain) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] hash = messageDigest.digest(plain.getBytes());
		BigInteger number = new BigInteger(1, hash);
		String hashText = number.toString(16);
		while (hashText.length() < 32) {
			hashText = "0" + hashText;
		}
		return hashText;

	}
}
