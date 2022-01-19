package com.emirates.flightsearch.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {
	private static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static DateTimeFormatter formatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static synchronized String getDateYYYYMMDD(Date date) {
		return yyyyMMddFormat.format(date);
	}

	public static synchronized LocalDate getLocalDateYYYYMMDD(String date) {
		return LocalDate.parse(date, formatteryyyyMMdd);
	}

	
	public static synchronized Date getDateFromLocalDate(LocalDate localDate) {
		Date d = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt= new Date();
		try {
			return format.parse(format.format(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
