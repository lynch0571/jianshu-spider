package com.lynch.spider.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date parseDate(String date){
		String s=date.replace("T", " ").substring(0, date.indexOf("+")).trim();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
}
