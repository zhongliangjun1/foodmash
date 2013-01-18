package com.food.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 18, 2013 12:29:11 PM
 */
public class TestCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar rightNow = Calendar.getInstance();
		System.out.println(rightNow.toString());
        
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
		String str = format.format(date); 
		System.out.println(str);
		

		

	}

}
