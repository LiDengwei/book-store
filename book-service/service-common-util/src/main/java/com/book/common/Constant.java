package com.book.common;

/**
 * 静态常量
 *
 */
public final class Constant {
	//判断小数的正则式
	public static final String DOUBLEVALIDATE = "^(-)?[0-9]+(.[0-9]{1,10})?$";
	//验证日期格式
	public static final String DATEVALIDATE = "^(\\d{1,2})(-|\\/)(\\d{1,2})(-|\\/)(\\d{1,4})$";
	//验证日期格式
	public static final String DATEFORMAT = "^(\\d{1,4})(-|\\/)(\\d{1,2})(-|\\/)(\\d{1,2})$";
	// 数字字符串;
	public static final String NUMBSTRING = "^[0-9]*$";
	// 正int
	public static final String ZHENG_INTEGER = "^[1-9]\\d*|0$";
	// 正double int
	public static final String ZHENG_DOUBLE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|[1-9]\\d*|0|0.0$";

	public static final String CN = "CN";
	public static final String EN = "EN";

}