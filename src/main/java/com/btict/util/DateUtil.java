package com.btict.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getCurrentTimestamp(){
		
		Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		   String dateString = formatter.format(currentTime);
		   return dateString;
	}
}
