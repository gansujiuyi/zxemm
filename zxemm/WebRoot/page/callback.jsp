<%@page import="com.google.gson.Gson"%>
<%@page import="com.iec.file.utils.ResponseData"%>
<%@page import="com.iec.file.utils.PropsConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
	
	String code = request.getParameter("code");
	String picurl = "";
	String type = "";
	String filesize = "";
	String filename = "";
	String picCode ="";
	if (code.equals("0")){
		picurl = request.getParameter("picurl");
		type = request.getParameter("type");
		filesize = request.getParameter("filesize");
		filename = request.getParameter("filename");
		picCode = picurl.substring(picurl.lastIndexOf("/")+1,picurl.lastIndexOf("."));
	}
	System.out.println("filename::"+filename);
	StringBuffer strBuf = new StringBuffer();
	strBuf.append("{");
		strBuf.append("\"code\":\""+code+"\",");
		strBuf.append("\"picurl\":\""+picurl+"\",");
		strBuf.append("\"type\":\""+type+"\",");
		strBuf.append("\"filesize\":\""+filesize+"\",");
		strBuf.append("\"picCode\":\""+picCode+"\",");
		strBuf.append("\"filename\":\""+filename+"\"");
	strBuf.append("}");
	System.out.println(strBuf.toString());
	String setUrl = "window.parent.listenerUploadResponse('"+strBuf.toString()+"')";
	out.println("<SCRIPT LANGUAGE='JAVASCRIPT'>");
	out.println(setUrl);
	out.println("</SCRIPT>");
%> 

