package com.jiuyi.jyplat.util;

public class ImageUtils {
	
	public static String imgReplacePath(String url,String width,String height){
		if (url ==null || url.equals("") || url.lastIndexOf(".")==-1){
			return "";
		} else {
			if (width.equals("") || height.equals("")){
				return url;
			}
			String suffice = url.substring(url.lastIndexOf("."),url.length());
			return url.substring(0,url.lastIndexOf("."))+"_"+width+"_"+height+suffice; 
		}
	}
}
