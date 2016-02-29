package com.tddh.utils;

import java.text.SimpleDateFormat;

public class DateUtils {
	private static SimpleDateFormat sdf = null;

	public static SimpleDateFormat getDateFormat() {
		if (sdf == null) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return sdf;
	}
}
