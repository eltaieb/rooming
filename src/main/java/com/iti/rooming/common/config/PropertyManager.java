package com.iti.rooming.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	public static Properties getProperty(String file) throws IOException {
		InputStream inputStream = null;
		try {
			Properties props = new Properties();
			inputStream = Configurations.class
					.getResourceAsStream(file + ".properties");

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new IOException("no property file found!");
			}
			return props;
		} finally {
			if (inputStream != null)
				inputStream.close();
		}

	}
}
