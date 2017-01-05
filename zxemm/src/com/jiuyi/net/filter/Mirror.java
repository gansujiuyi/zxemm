package com.jiuyi.net.filter;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;


public class Mirror
{
	/**
	 * 判断是否为简单类型
	 * @param obj
	 * @return
	 */
	public static boolean isSimple(Class<?> clazz)
	{
		return isString(clazz)
        || isBoolean(clazz)
        || isChar(clazz)
        || isNumber(clazz)
        || isDateTime(clazz);
	}
	
	public static boolean isCollection(Class<?> clazz)
	{
		return Collection.class.isAssignableFrom(clazz);
	}
	
	public static boolean isArray(Class<?> clazz)
	{
		return  clazz.isArray();
	}
	
	
	public static boolean isMap(Class<?> clazz)
	{
		return Map.class.isAssignableFrom(clazz);
	}
	
	
	private static boolean isDateTime(Class<?> clazz) {
        return Calendar.class.isAssignableFrom(clazz)
               || java.util.Date.class.isAssignableFrom(clazz)
               || java.sql.Date.class.isAssignableFrom(clazz)
               || java.sql.Time.class.isAssignableFrom(clazz);
    }
    
    private static boolean isNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz)
               || is(clazz,byte.class)
               || is(clazz,double.class)
               || is(clazz,float.class)
               || is(clazz,long.class)
               || is(clazz,int.class)
               || is(clazz,short.class);
    }
    
    private static boolean isBoolean(Class<?> clazz) {
        return is(clazz,boolean.class) || is(clazz,Boolean.class);
    }
    
    private static boolean isString(Class<?> clazz) {
        return CharSequence.class.isAssignableFrom(clazz);
    }
    
    private static boolean isChar(Class<?> clazz) {
        return is(clazz,char.class) || is(clazz,Character.class);
    }
   
	public static boolean is(Class<?> clazz,Class<?> type) {
        return null != type && clazz == type;
    }
	
	/**
	 * 是否为Get方法
	 * @param method
	 * @return
	 */
	public static boolean isGetMethod(Method method)
	{
		 if (method.getName().startsWith("get") && method.getParameterTypes().length == 0 && Modifier.isPublic(method.getModifiers()))
		 {
			 return true;
		 }
		 return false;
	}
	
    /**
     * 将字符串首字母小写
     * @param s 字符串
     * @return 首字母小写后的新字符串
     */
    public static String lowerFirst(CharSequence s) {
        if (null == s)
            return null;
        int len = s.length();
        if (len == 0)
            return "";
        char c = s.charAt(0);
        if (Character.isLowerCase(c))
            return s.toString();
        return new StringBuilder(len).append(Character.toLowerCase(c))
                                     .append(s.subSequence(1, len))
                                     .toString();
    }
}
