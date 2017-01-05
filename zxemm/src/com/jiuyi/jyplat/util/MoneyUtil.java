package com.jiuyi.jyplat.util;

import java.math.BigDecimal;

public class MoneyUtil {

	public MoneyUtil() {
	}

	/**
	 * 加
	 * @param d
	 * @param d1
	 * @return
	 */
	public static double add(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	/**
	 * 加
	 * @param d
	 * @param d1
	 * @return
	 */
	public static double add(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	public static double sub(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	public static double sub(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	public static double mul(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	public static double mul(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	public static double div(double d, double d1) {
		return div(d, d1, 2);
	}

	public static double div(String s, String s1) {
		return div(Double.parseDouble(s), Double.parseDouble(s1), 2);
	}

	public static double div(double d, double d1, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double div(String s, String s1, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double round(double d, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal("1");
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double round(String s, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal("1");
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static void main(String args[]) {
		System.out.println(MoneyUtil.add(1000, -10));
		System.out.println(div(198, 276, 2));
	}

}
