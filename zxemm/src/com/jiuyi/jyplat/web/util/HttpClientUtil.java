package com.jiuyi.jyplat.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/** 
 * HTTP 工具类. 
 *  
 */
public class HttpClientUtil {

	/** 
	 * 默认编码为 utf-8
	 */
	private static final String HTTP_CONTENT_CHARSET = "UTF-8";
	
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	public static final Integer MAX_TIME_OUT = 15000;
	
	public static final Integer MAX_IDLE_TIME_OUT = 60000;
	
	public static final Integer MAX_CONN = 100;
	
	public static HttpClient httpClient = null;
	
	static
	{
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.closeIdleConnections(MAX_IDLE_TIME_OUT);
		connectionManager.getParams().setParameter("http.connection-manager.max-total", MAX_CONN);
		httpClient = new HttpClient(connectionManager);
//		httpClient.getParams().setParameter("http.socket.timeout", MAX_TIME_OUT);
		httpClient.getParams().setParameter("http.connection.timeout", MAX_TIME_OUT);
		httpClient.getParams().setParameter("http.connection-manager.timeout", MAX_TIME_OUT.longValue());
		httpClient.getParams().setContentCharset(HTTP_CONTENT_CHARSET);
	}
	
	/** 
	 * 发送POST请求 
	 *  
	 * @param url 
	 * @param param 
	 * @return HTTP响应 
	 */
	public static String sendSimplePostRequest(String url, Map<String, Object> param)
	{
		PostMethod post = new PostMethod(url);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HTTP_CONTENT_CHARSET);
		if (param != null)
		{
			for (Entry<String, Object> entry : param.entrySet())
			{
				
				// 排除掉空值  
				if (entry.getValue() != null)
				{
					NameValuePair pair = new NameValuePair(entry.getKey(), entry.getValue().toString());
					post.addParameter(pair);
				}
			}
		}
		try
		{
			post.addRequestHeader(new Header("Connection", "close"));
			httpClient.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK)
			{
				return getString(post.getResponseBodyAsStream());
			}
			else
			{
				post.abort();//马上断开连接  
				logger.error("连接"+url+"发生异常 --> " + post.getStatusCode(), null);
			}
		}
		catch (Exception e)
		{
			logger.error("连接"+url+"发生异常", e);
		}
		finally
		{
			post.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 发送POST请求
	 * @param url 请求路径
	 * @param queryString 请求参数
	 * @param obj 参数对象
	 */
	public static Object sendObjectPostRequest(String url, String queryString, Serializable obj)
	{
		if(StringUtils.isEmpty(url))
		{
			logger.error("请求的路径为空");
			return null;
		}
		
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type", "application/octet-stream");  
		if(StringUtils.isNotEmpty(queryString))
		{
			logger.info("请求的参数为空");
			post.setQueryString(queryString);  
		}
		
		java.io.ByteArrayOutputStream bOut = new java.io.ByteArrayOutputStream(1024);  
		java.io.ByteArrayInputStream bInput = null;  
		java.io.ObjectOutputStream out = null;  
		Serializable returnObj = null;  
		try
		{
			 out = new java.io.ObjectOutputStream(bOut);  
			 out.writeObject(obj);  
			 out.flush();  
			 out.close();  
			 out = null;
			 bInput = new java.io.ByteArrayInputStream(bOut.toByteArray());  
			 RequestEntity re = new InputStreamRequestEntity(bInput);  
			 post.setRequestEntity(re);  
			 httpClient.executeMethod(post);  
			 java.io.InputStream in = post.getResponseBodyAsStream();  
			 java.io.ObjectInputStream oInput = new java.io.ObjectInputStream(in);  
			 returnObj = (Serializable) oInput.readObject();  
			 oInput.close();  
			 oInput = null;
			 return returnObj;
		}
		catch (Exception e)
		{
			logger.error("发生了异常!");
		}
		finally
		{
			try
			{
				if(out != null)
				{
					out.close();
					out = null;
				}
				if(bInput != null)
				{
					bInput.close();
					bInput = null;
				}
			}
			catch (Exception e)
			{
				logger.error("关闭连接发生异常");
			}
			//释放连接   
			post.releaseConnection();  
			return returnObj;
		}
	}
	
	public static String getString(InputStream inputStream) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer string = new StringBuffer();
		String str = "";
		while((str = reader.readLine()) != null)
		{
			string.append(str);
		}
		return string.toString();
	}
}
