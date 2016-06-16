package com.iti.rooming.common.utils;

import java.sql.Date;

public class DateConverter {

	public static Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
}
