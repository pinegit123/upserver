package com.csl.ws.hotel.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统校验
 * 
 * @author LIUCS
 *
 */
public class SysCheck {

	// 判断字符串是否为数字
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 判断出生日期是否合法
	private static SimpleDateFormat dateFormat = null;
	static {
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);
	}

	public static boolean isValidDate(String s) {
		try {
			dateFormat.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	// 判断日期是否合法2
	public static boolean isValidDates(String sDate) {
		String datePattern1 = "\\d{4}\\d{2}\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	// 判断日期+时间是否合法--入住时间
	private static SimpleDateFormat dateFormats = null;
	static {
		dateFormats = new SimpleDateFormat("yyyyMMddHHmm");
		dateFormats.setLenient(false);
	}

	public static boolean isValidDatet(String s) {
		try {
			dateFormats.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public final static void main(String[] args) {
		// TODO Auto-generated method stub

		String aa = "19821511";
		 System.out.println(isNumeric(aa));
		 String bb="2018012811268";
		System.out.println(isValidDatet(bb));

	}
}
