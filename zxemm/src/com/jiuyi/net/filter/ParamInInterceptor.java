package com.jiuyi.net.filter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jiuyi.jyplat.web.util.SysConfig;


/**
 * <p>参数拦截器</p>
 * 适用于过滤SOAP协议发送过来的xml格式数据，如果是REST协议的json格式数据可以采用Invoker进行过滤
 * @author 刘明
 * @time 2014-11-03
 */
public class ParamInInterceptor extends AbstractPhaseInterceptor<Message>
{
	private static Logger logger = Logger.getLogger(ParamInInterceptor.class);

	private static List<String> keyWordList =null;
	
	public ParamInInterceptor(String phase)
	{
		super(phase);
	}
	
	public ParamInInterceptor()
	{
		super(Phase.RECEIVE);
	}
	
	

	

	
	
	private static String removeFirstChar(String value)
	{
		if(value.indexOf("/") != -1)
		{
			return value.substring(1, value.length());
		}
		return value;
	}
	

	/**
	 * 获取XML中的各个部分
	 * @param xmlStr XML字符串
	 * @param flagElement 正文根节点名称
	 * @return xml的各个部分
	 */
	@SuppressWarnings("unused")
	private static Map<String,String> splitXML(String xmlStr,String flagElement)
	{
		if(StringUtils.isEmpty(xmlStr))
		{
			return null;
		}
		String prefix = xmlStr.substring(0,xmlStr.indexOf("<"+flagElement+">"));
		String suffix = xmlStr.substring(xmlStr.indexOf("</"+flagElement+">")+("</"+flagElement+">").length());
		String mainContent = xmlStr.substring(xmlStr.indexOf("<"+flagElement+">"), xmlStr.indexOf("</"+flagElement+">")+("</"+flagElement+">").length());
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("prefix", prefix);
		data.put("suffix", suffix);
		data.put("mainContent", mainContent);
		return data;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void eachElement(Element element) throws ExitException
	{
		if(null !=element)
		{
			if(null != element.elements() && element.elements().size() > 0)
			{
				for (Iterator iter = element.elements().iterator(); iter.hasNext();)
	            {
	                eachElement((Element) iter.next());
	            }
			}
			else
			{
				if(StringUtils.isNotEmpty(ParamFilter.instance().setKeyWord(keyWordList).hasKeyWord(element.getText())))
				{
					throw new ExitException("参数中【%s】字段的值【%s】含有敏感字",new Object[]{element.getName(),element.getTextTrim()});
				}
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void filter(String xmlStr) throws Exception
	{
		if(StringUtils.isNotBlank(xmlStr))
		{
			String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + xmlStr;
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			if (null != document)
			{
				eachElement(document.getRootElement());
			}
		}
	}
	

	
	
	
	public static void main(String[] args) throws Exception
	{
		String param = "<soapenv:Envelope xmlns:soapenv";
		param += "<soapenv:Header >";
		param += "</soapenv:Header >";
		param += " <soapenv:Body><ser:sendCode>";
		param += "<uxunmsg><msghead><authcode>343443</authcode><version>V1.0</version></msghead><msgreq><noteContent></noteContent><phoneCode></phoneCode><phoneNo>13728905504</phoneNo><smsType>0002</smsType><typeName>1</typeName>";
		param += "</msgreq></uxunmsg></ser:sendCode> </soapenv:Body></soapenv:Envelope>";
		
		String prefix = param.substring(0,param.indexOf("<uxunmsg>"));
		String suffix = param.substring(param.indexOf("</uxunmsg>")+"</uxunmsg>".length());
		String mainContent = param.substring(param.indexOf("<uxunmsg>"), param.indexOf("</uxunmsg>")+"</uxunmsg>".length());
		logger.info("\n请求头部:"+prefix + "\n" + "正文:" + mainContent + "\n"+ "请求尾部:" + suffix);
		filter(mainContent);
	}

	@Override
	public void handleMessage(Message arg0) throws Fault {
		// TODO Auto-generated method stub
		
	}
}
