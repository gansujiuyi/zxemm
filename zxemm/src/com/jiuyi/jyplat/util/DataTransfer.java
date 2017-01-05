/**
 * <p>Title:             DataTransfer.java
 * <p>Description:       数据转换的一些常用方法
 * <p>Copyright:         Copyright (C), 2002-2003, UXUN Tech. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0
 * @see		             com.jiuyi.util.DataTransfer
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 02/11/2003	          xuf	                             	         v1.0
 * 05/18/2003			  zenglj   加入模糊查询转换字符串 toDBSearch()
 *						toDBInSentence() 将字符串数组转换为SQL条件的IN语句格式，即 (v1,v2,v3,v4,v5)
 */

package com.jiuyi.jyplat.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTransfer {
	/*
	* 金额的形式, 保留小数点后两位
	*/
	private static DecimalFormat df = new DecimalFormat("0.00");

	/**
	* 将字符串数组转换为SQL条件的IN语句格式，即 (v1,v2,v3,v4,v5)
	*@param String [] 字符串数组长度必须大于等于1 ，否则SQL语句语法错误
	*@return String
	*/
	public static String toDBInSentence(String[] p_strArray) {
		StringBuffer fieldStr = new StringBuffer("(");

		for (int i = 0; i < p_strArray.length; i++) {
			fieldStr.append(toDB(p_strArray[i]) + ",");
		}

		fieldStr.setCharAt(fieldStr.length() - 1, ')'); //将最后的,替换为)

		return fieldStr.toString();
	}

	/**
	* 将字符串两边加上"'" ，并替换原有内容的"'"，在数据内容两边加入"%"
	*@param String
	*@return String
	*/
	public static String toDBSearch(String str) {
		String p_strStr;

		if (str == null) {
			p_strStr = "";
		} else {
			p_strStr = str;
		}

		return "'%" + toDBString(p_strStr) + "%'";

	}

	/**
	* 将字符串两边加上"'"
	*@param String
	*@return String
	*/
	public static String toDB(String str) {
		if (str == null) {
			return null;
		} else {
			return "'" + toDBString(str) + "'";
		}
	}

	/**
	* overloading methods
	*/
	public static int toDB(int i) {
		return i;
	}

	/**
	* overloading methods
	*/
	public static double toDB(double d) {
		return d;
	}

	/**
	 * 将字段中的单引号转换为双引号
	 */
	public static String toDBString(String str) {
		return str.replaceAll("'", "''");
	}

	/**
	* 将日期的显示格式yyyy-mm-dd修改为日期的存储格式yyyymmdd
	*/
	public static String store_Date_Format(String dateStr) {
		//zhangzh 0829
		if (1 == 1)
			return dateStr;
		if ((dateStr == null) || (dateStr.equals(""))) {
			return dateStr;
		} else {
			return dateStr.replaceAll("-", "");
		}
	}

	/**
	* 将日期的存储格式yyyymmdd修改为日期的显示格式yyyy-mm-dd
	*/
	public static String view_Date_Format(String dateStr) {
		if ((dateStr == null) || (dateStr.equals(""))) {
			return dateStr;
		} else {
			return dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);
		}
	}

	/**
	*Function: currentFormat (静态方法)
	*Description: 转换货币型数值格式为保留小数点后两位
	*@param double 需要转换的double型数据
	*@return double 转换后的数值
	*/
	public static double currency_Format(double data) {
		return Double.parseDouble(df.format(data));
	}

	/**
	*Function: currentFormat (静态方法)
	*Description: 转换货币型数值格式为保留小数点后两位
	*@param String 需要转换的double型数据
	*@return double 转换后的数值
	*/
	public static double currency_Format(String data) {
		double value = Double.parseDouble(data);
		return Double.parseDouble(df.format(value));
	}

	/**
	*Function: currentFormat (静态方法)
	*Description: 转换货币型数值格式为保留小数点后两位
	*@param String 需要转换的double型数据
	*@return double 转换后的数值
	*/
	public static String currency_Format_str(String data) {
		double value = Double.parseDouble(data);
		return df.format(value);
	}

	public static String formatDateExpress(String express, Date date) {
		try {
			Pattern pattern = Pattern.compile("%[(yyyy)|(MM)|(dd)]+%");
			Matcher matcher = pattern.matcher(express);

			while (matcher.find()) {
				String group = matcher.group();

				String repString = DateUtils.dateToDateString(date, group.substring(1, group.length() - 1));
				express = express.replace(matcher.group(), repString);
			}
		} catch (Exception e) {

		} finally {
			return express;
		}

	}

}