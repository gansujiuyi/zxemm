package com.jiuyi.net.message.httpClient;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 * xml公用操作类
 * @author zhangb
 * 时间：20130510
 */
public class XMLUtil {
	
	/**
	 * 将xml字符串格式化
	 * @param str  原字符串
	 * @return	   格式化后的xml文件格式的字符串
	 * @throws Exception 
	 * @throws Exception
	 */
    public static String formatXml(String str) throws Exception{
    	StringReader  in=null;
        StringWriter out=null;
	    try{
	        SAXReader reader=new SAXReader();
	        //创建一个串的字符输入流
	        in=new StringReader(str);
	        Document doc=reader.read(in);
	        //创建输出格式
	        OutputFormat formate=OutputFormat.createPrettyPrint();
	        //创建输出
	        out=new StringWriter();
	        //创建输出流
	        XMLWriter writer=new XMLWriter(out,formate);
	        //输出格式化的串到目标中,格式化后的串保存在out中。
	        writer.write(doc);
	    } catch (Exception ex){
	        throw new Exception("对xml字符串进行格式化时产生异常",ex);   
	    } finally{
	    	//关闭流
	    	if(null!=in)in.close();
	    	if(null!=out)out.close();
	    }
	    return out.toString();
    }
    /**
     * 将对象转换为xml字符串格式
     * @param obj  转换对象
     * @return		xml格式字符串
     * @throws Exception
     */
    public static  String objToxml(Object obj) throws Exception{
    	 StringWriter sw = new StringWriter();
         JAXBContext jAXBContext = null;
		try {
			jAXBContext = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = jAXBContext.createMarshaller();
	        marshaller.marshal(obj, sw);
		} catch (JAXBException ex) {
		    throw new Exception("对xml字符串进行格式化时产生异常",ex);  
		}
         return sw.toString();
    }
    /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		 //创建一个串的字符输入流
		StringReader in=new StringReader(strxml);
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document doc =  builder.build(in);
		Element root = (Element) doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			m.put(k, v);
		}
		//关闭流
		in.close();
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}
	
	/**
	 * xml格式字符串去除换行
	 * @param xml
	 * @return
	 */
	public static String xmlRemoveWrap(String xml){
		Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
  	  	Matcher m = p.matcher(xml);
  	  	String result = m.replaceAll("");
  	  	return result;
	}
}

