package com.jiuyi.jyplat.util;

import java.util.Hashtable;

import org.apache.log4j.Logger;

/* 程序通过servlet初始化获得的参数*/
public class Systemparas
{
	static Logger Log = Logger.getLogger(Systemparas.class);

	private static Hashtable m_htParaQ = new Hashtable();

	//
	public static void addUxunPara(String servletName, String itemName, String Value) {
		String strKey = servletName + "." + itemName;
		Log.debug("加入参数，参数名=" + strKey + "值=" + Value);
		m_htParaQ.put(strKey, Value);
	}

	//获取参数值
	public static String getUxunPara(String itemName) {
		return (String) m_htParaQ.get(itemName);
	}

	//获取参数值，带缺省值
	public static String getUxunPara(String itemName, String strDefault) {
		String value = (String) m_htParaQ.get(itemName);
		if (value == null || value.isEmpty())
			return strDefault;

		return value;
	}

}