package org.packt.client.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightUtils {
	public static Date parseDate(String date) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
	    return formatter.parse(date);
	}
}
