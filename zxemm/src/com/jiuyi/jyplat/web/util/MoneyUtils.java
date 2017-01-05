package com.jiuyi.jyplat.web.util;

public class MoneyUtils {
	/**
	 * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
	 * 
	 * @param value
	 * @return Sting
	 */
	public static String formatFloatNumber(double value) {
		if (value != 0.00) {
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					"########.00");
			return df.format(value);
		} else {
			return "0.00";
		}

	}

	public static String formatFloatNumber(Double value) {
		if (value != null) {
			if (value.doubleValue() != 0.00) {
				java.text.DecimalFormat df = new java.text.DecimalFormat(
						"########.00");
				return df.format(value.doubleValue());
			} else {
				return "0.00";
			}
		}
		return "";
	}
}
