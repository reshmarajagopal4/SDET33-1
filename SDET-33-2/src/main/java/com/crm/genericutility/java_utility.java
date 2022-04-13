package com.crm.genericutility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author Sumanth
 *
 */



public class java_utility {
	
	public static int generateRandomNumber(int limit) {
		Random r = new Random();
		int rnum=r.nextInt(limit);
		return rnum;
	}
	public static String getCurrentTimeAndDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date d =new Date();
		String requireFormatDate=sdf.format(d);
		return requireFormatDate;
	}
	
	public static long convertStringToLong(String timeout) {
		long timeouts=Long.parseLong(timeout);
		return timeouts;
	}
	


	
	

}
