package org.packt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class FlightUtils {
	private static String dateFormat;
	
	public static String getDateFormat() {
		return dateFormat;
	}

	@Inject
	public static void setDateFormat(@Named("dateFormat")String dateFormat) {
		FlightUtils.dateFormat = dateFormat;
	}
	
	public static Date parseDate(String date) throws ParseException{
		DateFormat formatter = new SimpleDateFormat(dateFormat);
	    return formatter.parse(date);
	}


}
