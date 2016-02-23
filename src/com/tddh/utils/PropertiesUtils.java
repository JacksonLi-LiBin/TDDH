package com.tddh.utils;

import java.util.ResourceBundle;

/**
 * class read properties file
 * 
 * @author jackson
 *
 */
public class PropertiesUtils {
	// get property value according to key
	public static String readProperties(String sourceName, String proKey) {
		String proValue = ResourceBundle.getBundle("com.tddh.properties." + sourceName).getString(proKey);
		return proValue;
	}
}
