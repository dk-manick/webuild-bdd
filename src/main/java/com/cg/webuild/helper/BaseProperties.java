package com.cg.webuild.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseProperties extends UtilClass {

	public static String url, browser;

	public static void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("src/main/resources/configfile/config.properties");
			prop.load(input);
			url = prop.getProperty("url");
			browser = prop.getProperty("browser");			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}