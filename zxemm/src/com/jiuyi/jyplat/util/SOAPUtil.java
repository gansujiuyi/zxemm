package com.jiuyi.jyplat.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * SOAP工具类
 */
public class SOAPUtil {

	/** 空字符串。 */
	public static final String EMPTY_STRING = "";

	/*
	 * 根据发送报文得到响应报文的SOAPBody
	 */
	public static byte[] responseSOAPBody(byte[] bytesBody, String url, String serviceMethod) {
		try {
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = sfc.createConnection();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage sm = mf.createMessage(new MimeHeaders(), new ByteArrayInputStream(bytesBody));//xmlString.getBytes()));
			//System.out.println("发送包:"+xmlString);
			// LogSoapMsg ( sm.getSOAPPart() ) ;

			URL endpoint = new URL(url);

			SOAPMessage response = connection.call(sm, endpoint);

			/* 以下为获取SOAPMSG的对应的值 */
			// QName bodyName = new QName( "urn:cardws",
			// serviceMethod+"Response", "urn" );
			SOAPPart soapPart = response.getSOAPPart();
			return SoapMsgToString(soapPart);

			//SOAPEnvelope se = soapPart.getEnvelope();
			//System.out.println("响应报：");
			// 显示获得的响应数据包
			//LogSoapMsg(soapPart);

			/*
			 * SOAPBody sb = se.getBody(); SOAPHeader sh = se.getHeader();
			 */
			//SOAPBody sb = response.getSOAPBody();

			//return sb;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	/*
	 * 得到包体指定的标签集合
	 */
	public static NodeList getNodeList(SOAPBody body, String name) {
		return body.getElementsByTagName(name);
	}

	/*
	 * 得到包体指定标签子标签集合
	 */
	public static NodeList getNodeList(Node node) {
		return node.getChildNodes();

	}

	/*
	 * 得到nodeList中指定下标标签的值
	 */
	public static String getNodeListItemContext(NodeList nodes, int index) {

		if (nodes.item(index) == null) {
			return "";
		} else {
			return nodes.item(index).getTextContent();
		}

	}

	public static String genMsgItem(String itemNM, String itemValue) {
		StringBuffer msgBuffer = new StringBuffer();

		msgBuffer.append("<");
		msgBuffer.append(itemNM);
		msgBuffer.append(">");
		msgBuffer.append(itemValue);
		msgBuffer.append("</");
		msgBuffer.append(itemNM);
		msgBuffer.append(">\n");

		return msgBuffer.toString();
	}

	//从XMLDATA中获取执行项的值
	public static String parseXmlItemValue(String itemNM, String xmlData) {
		try {
			int iPos = 0;
			int iBgnPos = 0;
			int iEndPos = 0;

			iBgnPos = indexOf(xmlData, "<" + itemNM + ">", iPos);
			if (iBgnPos == -1) {
				//Log.error( "格式错误，没有找到msg 标签" ) ;
				return EMPTY_STRING;
			}

			iEndPos = indexOf(xmlData, "</" + itemNM + ">", iBgnPos + itemNM.length() + 2);
			if (iEndPos == -1) {
				//Log.error( "格式错误，没有找到msg </dev> =结束标签" ) ;
				return EMPTY_STRING;
			}
			return mid(xmlData, iBgnPos + itemNM.length() + 2, iEndPos - (iBgnPos + itemNM.length() + 2)).trim();
		} catch (Exception e) {
			//Log.debug( "解析XML错误：" + e.toString() ) ;
			return EMPTY_STRING;
		}

	}

	//从字节列表中查找字符串位置
	public static int indexOfByte(byte[] str, String searchStr, int startPos) {
		if (str == null)
			return -1;

		byte[] searchBytes = searchStr.getBytes();

		if (str.length < searchBytes.length)
			return -1;

		int j = 0;
		//int iTimes = str.length - startPos ;

		for (int i = startPos; i < str.length; i++) {
			if (str[i] == searchBytes[j]) {
				j = j + 1;
				if (j == searchBytes.length) {

					return i - j + 1; //找到了
				}
			} else {
				j = 0; //不匹配，重新从第一个开始匹配
			}
		}

		return -1;
	}

	//从XMLDATA中获取执行项的值 含标签
	public static byte[] parseXmlItemValueByte(String itemNM, byte[] xmlData) {
		try {
			int iPos = 0;
			int iBgnPos = 0;
			int iEndPos = 0;

			iBgnPos = indexOfByte(xmlData, "<" + itemNM + ">", iPos);
			if (iBgnPos == -1) {
				System.out.println("格式错误，没有找到msg 标签");
				//Log.error( "格式错误，没有找到msg 标签" ) ;
				return null;
			}

			iEndPos = indexOfByte(xmlData, "</" + itemNM + ">", iBgnPos + itemNM.length() + 2);
			if (iEndPos == -1) {
				//Log.error( "格式错误，没有找到msg </dev> =结束标签" ) ;
				return null;
			}
			//int iBgn =  iBgnPos+itemNM.length()+2 ;
			int iLen = iEndPos - iBgnPos + itemNM.length() + 3;
			byte[] retBytes = new byte[iLen];
			System.arraycopy(xmlData, iBgnPos, retBytes, 0, iLen);
			//return mid( xmlData  , iBgnPos+itemNM.length()+2 , iEndPos - ( iBgnPos + itemNM.length()+2 ) ).trim() ;
			return retBytes;
		} catch (Exception e) {
			//Log.debug( "解析XML错误：" + e.toString() ) ;
			return null;
		}

	}

	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}

		if ((len < 0) || (pos > str.length())) {
			return EMPTY_STRING;
		}

		if (pos < 0) {
			pos = 0;
		}

		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		// JDK1.3及以下版本的bug：不能正确处理下面的情况
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}

		return str.indexOf(searchStr, startPos);
	}

	//把SOAP消息转换为XML串
	public static byte[] SoapMsgToString(SOAPPart soapPart) {
		try {
			/* 以下代码为输出SOAP消息的XML数据 */
			Source source = soapPart.getContent();

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(bos);
			//transformer.transform(new DOMSource(root), result );
			transformer.transform(source, result);
			return bos.toByteArray();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//针对兰州银行的报文要求，对返回结果进行转换
	//只取 <msgrsp> 里的数据 ,然后取消掉<retcode>  <retshow>
	//加入<CallCenter> ,msgrsp去掉后的数据加上<BODY>，然后对<retcode>和<retshow>加上<HEAD> 
	public static byte[] LZBankConvert(byte[] data) {
		//只要msgrsp数据  数据是带 msgrsp 标签的
		byte[] msgrsp = parseXmlItemValueByte("msgrsp", data);
		int iretcodebgn = indexOfByte(msgrsp, "<retcode>", 0);
		int iretcodeend = indexOfByte(msgrsp, "</retcode>", iretcodebgn) + 10;
		int iretshowbgn = indexOfByte(msgrsp, "<retshow>", 0);
		int iretshowend = indexOfByte(msgrsp, "</retshow>", iretshowbgn) + 10;

		//计算新的长度
		//int ilen = msgrsp.length + "<CallCenter>".length() + "</CallCenter>".length() + "<BODY>".length() +  "</BODY>".length() + "<HEAD>".length() + "</HEAD>".length() ;
		String EC = "<CallCenter>";
		String ECE = "</CallCenter>";
		String EB = "<BODY>";
		String EBE = "</BODY>";
		String EH = "<HEAD>";
		String EHE = "</HEAD>";
		String MSGRSP = "<msgrsp>";
		String MSGRSPE = "</msgrsp>";

		byte[] retcodes = new byte[iretcodeend - iretcodebgn];
		System.arraycopy(msgrsp, iretcodebgn, retcodes, 0, iretcodeend - iretcodebgn);
		String retCode = new String(retcodes);
		//7零换为6个0 
		if (retCode.length() == (19 + 4)) {
			retCode = retCode.replace("0000", "000000");
		} else if (retCode.length() == (19 + 7)) {
			retCode = retCode.replace("0000000", "000000");
		}

		int ilen = msgrsp.length + EC.length() + ECE.length() + EB.length() + EBE.length() + EH.length() + EHE.length()
				- MSGRSP.length() - MSGRSPE.length() - (iretcodeend - iretcodebgn) + retCode.getBytes().length;
		int ipos = 0;

		byte[] newdata = new byte[ilen];

		//加4位长度
		//String strLen = FixLenStr ( String.valueOf( ilen ) , 4, "0" ) ;
		//System.arraycopy ( strLen.getBytes() , 0 , newdata , ipos , 4 ) ;
		//ipos = ipos + 4 ;

		//内容主体
		System.arraycopy(EC.getBytes(), 0, newdata, ipos, EC.length());
		ipos = ipos + EC.length();
		//head段
		System.arraycopy(EH.getBytes(), 0, newdata, ipos, EH.length());
		ipos = ipos + EH.length();
		//System.arraycopy ( msgrsp  , iretcodebgn  , newdata , ipos , iretcodeend- iretcodebgn ) ;
		//ipos = ipos + (iretcodeend- iretcodebgn) ;
		System.arraycopy(retCode.getBytes(), 0, newdata, ipos, retCode.getBytes().length);
		ipos = ipos + retCode.getBytes().length;

		System.arraycopy(msgrsp, iretshowbgn, newdata, ipos, iretshowend - iretshowbgn);
		ipos = ipos + (iretshowend - iretshowbgn);
		System.arraycopy(EHE.getBytes(), 0, newdata, ipos, EHE.length());
		ipos = ipos + EHE.length();

		//body端
		System.arraycopy(EB.getBytes(), 0, newdata, ipos, EB.length());
		ipos = ipos + EB.length();
		//要比较retcode 和retshow节点的顺序 ，先把BODY内容搞定
		if (iretcodebgn < iretshowbgn) {
			//位置前的 iretcodebgn在前
			System.arraycopy(msgrsp, 0 + MSGRSP.length(), newdata, ipos, iretcodebgn - MSGRSP.length());
			ipos = ipos + iretcodebgn - MSGRSP.length();
			//两个节点中间的
			if (iretshowbgn > iretcodeend) {
				System.arraycopy(msgrsp, iretcodeend, newdata, ipos, iretshowbgn - iretcodeend);
				ipos = ipos + (iretshowbgn - iretcodeend);
			}
			//节点后的部分
			if (msgrsp.length > iretshowend) {
				System.arraycopy(msgrsp, iretshowend, newdata, ipos, (msgrsp.length - iretshowend - MSGRSPE.length()));
				ipos = ipos + (msgrsp.length - iretshowend - MSGRSPE.length());
			}
		} else {
			//位置前的 iretshowbgn在前
			System.arraycopy(msgrsp, 0 + MSGRSP.length(), newdata, ipos, iretshowbgn - MSGRSP.length());
			ipos = ipos + iretshowbgn - MSGRSP.length();
			//两个节点中间的
			if (iretcodebgn > iretshowend) {
				System.arraycopy(msgrsp, iretshowend, newdata, ipos, iretcodebgn - iretshowend);
				ipos = ipos + (iretcodebgn - iretshowend);
			}
			//节点后的部分
			if (msgrsp.length > iretcodeend) {
				System.arraycopy(msgrsp, iretcodeend, newdata, ipos, (msgrsp.length - iretcodeend - MSGRSPE.length()));
				ipos = ipos + (msgrsp.length - iretcodeend - MSGRSPE.length());
			}
		}

		System.arraycopy(EBE.getBytes(), 0, newdata, ipos, EBE.length());
		ipos = ipos + EBE.length();
		System.arraycopy(ECE.getBytes(), 0, newdata, ipos, ECE.length());
		ipos = ipos + ECE.length();

		//处理所有<a/>的节点为<a></a>的方式
		int iIndex = 0;
		int iEnd = 0;
		do {
			iIndex = indexOfByte(newdata, "/>", iIndex);
			if (iIndex < 0)
				break;

			iEnd = iIndex + 2;
			//如果找到了，则需要处理找到开始位置
			for (; iIndex >= 0; iIndex--) {
				if (newdata[iIndex] == '<')
					break;
			}

			ilen = (iEnd - iIndex) - 1; //增加的长度
			byte[] rpdata = new byte[newdata.length + ilen];

			System.arraycopy(newdata, 0, rpdata, 0, iEnd - 2);
			ipos = iEnd - 2;
			System.arraycopy(newdata, iEnd - 1, rpdata, ipos, 1);
			ipos = ipos + 1;
			System.arraycopy(newdata, iIndex, rpdata, ipos, 1);
			ipos = ipos + 1;
			System.arraycopy(newdata, iEnd - 2, rpdata, ipos, 1);
			ipos = ipos + 1;
			System.arraycopy(newdata, iIndex + 1, rpdata, ipos, ilen - 2);
			ipos = ipos + ilen - 2;
			//System.arraycopy ( newdata , iEnd-1 , rpdata , ipos   , 1  ) ;
			//ipos = ipos + 1 ;
			System.arraycopy(newdata, iEnd - 1, rpdata, ipos, newdata.length - iEnd + 1);

			newdata = rpdata;

		} while (true);

		return newdata;

	}

	/**
	 * 返回固定长度字符串，长度不够补字符
	 * @param str 字符串
	 * @param len 长度
	 * @param s 以什么字符在前面补位
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String FixLenStr(String str, int len, String s) {
		if (str == null) {
			str = "";
		}

		// 为了避免中文问题，需要取BYTE的长度
		if (str.getBytes().length >= len)
			return new String(str.getBytes(), 0, len);

		StringBuffer retStr = new StringBuffer();

		for (int i = 0; i < (len - str.getBytes().length); i++) {
			retStr.append(s);
		}
		retStr.append(str);
		return retStr.toString();
	}

	/*
	 * 打印报文
	 */
	public static void LogSoapMsg(SOAPPart soapPart) {
		try {
			/* 以下代码为输出SOAP消息的XML数据 */
			Source source = soapPart.getContent();

			Node root = null;
			if (source instanceof DOMSource) {
				root = ((DOMSource) source).getNode();
			} else if (source instanceof SAXSource) {
				InputSource inSource = ((SAXSource) source).getInputSource();
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = null;

				db = dbf.newDocumentBuilder();

				Document doc = db.parse(inSource);
				root = (Node) doc.getDocumentElement();
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(root), new StreamResult(System.out));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
