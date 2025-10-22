package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static final Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
			props.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}

	// Always check system property first, then config file
	public static String get(String key) {
		return System.getProperty(key, props.getProperty(key));
	}
}
