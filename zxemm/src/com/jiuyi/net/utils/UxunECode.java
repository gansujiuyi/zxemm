/**
 * <p>Title:             UxunECode.java
 * <p>Description:       数据转换的一些常用方法
 * <p>Copyright:         Copyright (C), 2009-2012.
 * <p>Company:           uxunchina
 * @author               zenglj
 * @version	             v1.0
 * @see		             com.jiuyi.util.DataTransfer
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 06/08/2012	          zenglj	                             	         v1.0
 */

package com.jiuyi.net.utils;

import java.util.Random;

public class UxunECode {
	
	/**
	 * 生成指定长度的随机数 产生指定区间的数，现在是6位
	 * 
	 * @return
	 */
	public static String genECode() {
		int max = 999999;
		int min = 100000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return String.valueOf(s);
	}

	public static void main(String[] args) {

		System.out.println(genECode());

	}
}