package com.jiuyi.net.utils;

import java.lang.reflect.Field;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

 
/**
 * 防止sql注入
 * @author Administrator
 */
public class FilterParam {
	private static Logger log = Logger.getLogger(FilterParam.class);
	
	//对单个字符串进行处理。
	public static String filterStr(String str){
		if(StringUtils.isBlank(str)){
			return str;
		}
		String tmp = str.replaceAll(".*([';]+|(--)+).*", "");
		if(!str.equals(tmp)){
			log.info("传入的参数“"+ str +"”被防注入过滤了。");
		}
		return tmp;
	}
	
	//通过反射修改string类型成员变量，对单个对象的成员变量进行处理。
	public static<T> T filterObj(T tmp){
		Class<?> obj = tmp.getClass();
		for (Field ff : obj.getDeclaredFields()) {
			ff.setAccessible(true);
			if(ff.getType().getName().equalsIgnoreCase("java.lang.String")){
				try {
					Object param = ff.get(tmp);
					if(param != null){						
						String param_str = (String)param;
						String result = param_str.replaceAll(".*([';]+|(--)+).*", "");
						if(!param_str.equals(result)){
							log.info("参数“" + param_str + "”被防sql注入过滤了。");
						}
						ff.set(tmp, result);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return tmp;
	}

	
}
