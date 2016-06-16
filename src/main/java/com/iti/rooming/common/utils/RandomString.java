package com.iti.rooming.common.utils;

import java.util.Random;

public class RandomString {
	private static final char[] symbols;
	private final Random random = new Random();
	
	private char[] buffer;
	static {
		StringBuilder stringBuilder = new StringBuilder();
		for (char number = '0'; number < '9'; number++)
			stringBuilder.append(number);
		for (char alpha = 'a'; alpha < 'z'; alpha++)
			stringBuilder.append(alpha);
		symbols = stringBuilder.toString().toCharArray();
	}

	public RandomString(int length) {
		buffer = new char[length];
	}

	public String getRandomString() {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buffer);
	}
}
