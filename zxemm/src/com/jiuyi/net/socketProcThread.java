/**
 * <p>Title:             SocketClient.java
 * <p>Description:       短连接处理函数
 * <p>Copyright:         Copyright (C), 2010 UXUNCHINA.
 * <p>Company:           UXUNCHINA
 * @author          	 zenglj
 * @version	 		     V1.1
 * @see		      		 
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 2007-07-06	          zenglj                             	         v1.0 
 */
package com.jiuyi.net;

import java.io.*;
import java.net.*;

import javax.xml.soap.SOAPBody;

import org.apache.log4j.Logger;

import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.SOAPUtil;

public class socketProcThread implements Runnable {
	Logger Log = Logger.getLogger(socketProcThread.class);

	private OutputStream out = null;
	private InputStream in = null;
	private Socket sck = null;
	private String WebserviceURL;

	public socketProcThread(Socket socket, String proxyWebservice) {
		sck = socket;
		WebserviceURL = proxyWebservice;
		new Thread(this).start();
	}

	/*
	 * 包文格式定义：
	 * 6位包长+包体 ;包长后补空格
	 * 包体以XML提现，本程序是webservice的代理，把webservice协议承载转换为socket.
	 */
	public void DoTran() throws IOException

	{
		try {
			out = sck.getOutputStream();
			in = sck.getInputStream();

			//包长
			short RecvLen = 0;

			byte[] bRecvLen = new byte[6];
			readBytes(bRecvLen);
			String strRecvLen = new String(bRecvLen);
			RecvLen = Short.parseShort(strRecvLen.trim());

			if (RecvLen < 40) {
				Log.debug("包长度太小，包头都取不全 Len=" + RecvLen);
				throw new Exception("包长度太小，包头都取不全 Len=" + RecvLen);
			}

			Log.debug("接收报文包长度=" + String.valueOf(RecvLen));

			byte[] bSeq = new byte[RecvLen]; //包体
			int iLen = readBytes(bSeq);
			if (iLen != RecvLen) {
				Log.debug("数据包没有收全，标识大小=" + RecvLen + "实际接收长度=" + iLen);
				throw new Exception("数据包没有收全 ");
			}

			String recvData = new String(bSeq);
			Log.debug("接收的包文体：" + recvData);

			//查找报文编码格式
			String xmlencoding = "UTF-8"; //缺省为UTF-8
			//业务处理
			//String strSend = "系统错误，请联系系统管理员!!" ;
			//String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" ;
			String xmlHead = "";

			//查找字符 ?> 
			int iHeadPos = SOAPUtil.indexOf(recvData, "?>", 0);
			if (iHeadPos > 0) {
				xmlHead = recvData.substring(0, iHeadPos + 2);
				Log.debug("xmlHead=：" + xmlHead);
				//取编码格式
				int iencodepos = SOAPUtil.indexOf(xmlHead, "encoding", 0);
				if (iencodepos > 0) {
					//Log.debug("iencodepos=" + iencodepos + " iHeadPos=" + iHeadPos ) ;
					xmlencoding = xmlHead.substring(iencodepos + 8, iHeadPos);
					xmlencoding = xmlencoding.replace("=", "");
					xmlencoding = xmlencoding.replace("\"", "");
					xmlencoding = xmlencoding.replace(" ", "");
					xmlencoding = xmlencoding.trim();
					Log.debug("xmlencoding=：" + xmlencoding);
				}

				//如果编码格式不是UTF-8,则转换为UTF-8的字节串
				if (!"UTF-8".equalsIgnoreCase(xmlencoding)) {
					String strTemp = new String(bSeq, xmlencoding);
					bSeq = strTemp.getBytes("UTF-8");
				}
			} else {
				Log.debug("no send xmlHead, no encoding ,using utf-8");
			}

			try {
				//代理处理
				String serviceNM = SOAPUtil.parseXmlItemValue("servicename", recvData);
				if (serviceNM == null || serviceNM.length() == 0) {
					Log.error("非法包文，无效的服务名，系统级错误");
					throw new Exception("非法包文，无效的服务名");
				}

				//为了避免编码问题，按BYTE通道转发数据，具体编码由发起方和WEBSERVICE方定义。WEBSERVICE目前采用UTF-8格式
				byte[] bSeqBody = new byte[bSeq.length - xmlHead.getBytes().length];
				System.arraycopy(bSeq, xmlHead.getBytes().length, bSeqBody, 0, bSeqBody.length);

				byte[] strMsg = SOAPUtil.responseSOAPBody(genSoapMsg(bSeqBody, serviceNM), WebserviceURL, serviceNM);
				Log.debug("返回的webservice包文：" + new String(strMsg));

				//byte [] sendBytes = SOAPUtil.genMsgItem( "uxunmsg", SOAPUtil.parseXmlItemValue( "uxunmsg" , strMsg  ) ) ;
				bSeq = SOAPUtil.parseXmlItemValueByte("uxunmsg", strMsg);
				//Log.debug( "截取消息包文：" + new String (bSeq ) ) ;
				//判断是否是兰州银行，如果是兰州银行的电话银行渠道，则在数据返回的时候需要处理   
				//20121113 只有柜面不需要转换
				if ("lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE)
						&& (!"001".equalsIgnoreCase(SOAPUtil.parseXmlItemValue("tranchannel", recvData))))
				//if ( "lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE) )
				{

					bSeq = SOAPUtil.LZBankConvert(bSeq);
				}
			} catch (Exception e) {
				Log.error("系统级错误" + e.toString());
			}

			//StringBuffer sendBuf = new StringBuffer () ;
			//String strSLen = FixLenStr ( String.valueOf( strSend.getBytes().length ) , 6 ) ;
			//sendBuf.append( strSLen ) ;
			//sendBuf.append(  strSend ) ;

			//bSeq = sendBuf.toString().getBytes() ;
			//Log.debug( "返回成功 sendbuf=" + sendBuf.toString() ) ;
			//如果编码格式不是UTF-8,则需要把UTF-8的字节串转换为目标字符串
			if (!"UTF-8".equalsIgnoreCase(xmlencoding)) {
				String strTemp = new String(bSeq, "UTF-8");
				bSeq = strTemp.getBytes(xmlencoding);
			}

			//如果没有送编码格式，返回编码格式UTF-8的声明
			if (xmlHead.length() == 0) {
				xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			}

			String strSLen;
			byte[] sendBytes;
			//写交易内容
			//if ( "lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE))
			//兰州银行电话银行返回数据转换
			//if ( "lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE) && "006".equalsIgnoreCase(SOAPUtil.parseXmlItemValue("tranchannel",  recvData )))
			if ("lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE)
					&& (!"001".equalsIgnoreCase(SOAPUtil.parseXmlItemValue("tranchannel", recvData)))) {
				//加4位长度
				strSLen = FixLenStr(String.valueOf(bSeq.length + xmlHead.getBytes().length + 4), 6);
				sendBytes = new byte[6 + bSeq.length + xmlHead.getBytes().length + 4];

				String strCallLen = SOAPUtil.FixLenStr(String.valueOf(bSeq.length + xmlHead.getBytes().length), 4, "0");

				System.arraycopy(strSLen.getBytes(), 0, sendBytes, 0, 6);
				System.arraycopy(strCallLen.getBytes(), 0, sendBytes, 6, 4);
				System.arraycopy(xmlHead.getBytes(), 0, sendBytes, 10, xmlHead.getBytes().length);
				System.arraycopy(bSeq, 0, sendBytes, 4 + 6 + xmlHead.getBytes().length, bSeq.length);
			} else if ("lz".equalsIgnoreCase(Constant.SYS_BANK_TYPE)
					&& ("001".equalsIgnoreCase(SOAPUtil.parseXmlItemValue("tranchannel", recvData)))) {
				//兰州柜面的返回不包含长度
				sendBytes = new byte[bSeq.length + xmlHead.getBytes().length];
				System.arraycopy(xmlHead.getBytes(), 0, sendBytes, 0, xmlHead.getBytes().length);
				System.arraycopy(bSeq, 0, sendBytes, xmlHead.getBytes().length, bSeq.length);
			} else {
				//通用
				strSLen = FixLenStr(String.valueOf(bSeq.length + xmlHead.getBytes().length), 6);
				sendBytes = new byte[6 + bSeq.length + xmlHead.getBytes().length];

				System.arraycopy(strSLen.getBytes(), 0, sendBytes, 0, 6);
				System.arraycopy(xmlHead.getBytes(), 0, sendBytes, 6, xmlHead.getBytes().length);
				System.arraycopy(bSeq, 0, sendBytes, 6 + xmlHead.getBytes().length, bSeq.length);
			}

			//增加打印日志，把返回数据打印，方便调试 20120907  zlj
			Log.debug(new String(sendBytes, xmlencoding));

			writeBytes(sendBytes);
			out.flush();

			//关闭释放
			out.close();
			in.close();
			sck.close();
		} catch (Exception e) {
			Log.debug("系统异常" + e.toString());
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (sck != null)
				sck.close();

		}
	}

	// implement the typed write() and read() functions myself since they didn't work properly
	private void writeBytes(byte value[]) throws Exception {
		out.write(value);
	}

	public void writeShort(short value) throws Exception {
		byte sent[] = new byte[2];

		sent[0] = (byte) ((value >> 8) & 0xff);
		sent[1] = (byte) (value & 0xff);
		out.write(sent);
	}

	private void writeInt(int value) throws Exception {
		byte sent[] = new byte[4];

		sent[0] = (byte) ((value >> 24) & 0xff);
		sent[1] = (byte) ((value >> 16) & 0xff);
		sent[2] = (byte) ((value >> 8) & 0xff);
		sent[3] = (byte) (value & 0xff);
		out.write(sent);
	}

	public void writeString(int value) throws Exception {
		byte sent[] = new byte[2];

		sent[0] = (byte) ((value >> 24) & 0xff);
		sent[1] = (byte) ((value >> 16) & 0xff);
		out.write(sent);
	}

	private int readBytes(byte[] value) throws Exception {
		//必须读到所有数据，否则循环
		int iRecvedLen = 0;
		int iRecv = 0;
		for (int i = 0; i < 10; i++) {
			iRecv = in.read(value, iRecvedLen, value.length - iRecvedLen);
			if (iRecv >= 0)
				iRecvedLen = iRecvedLen + iRecv;

			if (iRecvedLen >= value.length)
				break;
		}
		return iRecvedLen;
	}

	private short readShort() throws Exception {
		byte received[] = new byte[2];
		in.read(received);
		return (short) ((received[0] << 8) | (received[1] & 0xff));
	}

	private int readInt() throws Exception {
		byte received[] = new byte[4];
		in.read(received);
		return (int) ((received[0] << 24) | (received[1] << 16) | (received[2] << 8) | (received[3] & 0xff));
	}

	private String FixLenStr(String str, int len) {
		if (str == null) {
			str = "";
		}

		//为了避免中文问题，需要取BYTE的长度
		if (str.getBytes().length >= len)
			return new String(str.getBytes(), 0, len);

		StringBuffer retStr = new StringBuffer();
		retStr.append(str);

		for (int i = 0; i < (len - str.getBytes().length); i++)
			retStr.append(" ");

		return retStr.toString();
	}

	//构建soap的头尾
	private byte[] genSoapMsg(byte[] recvData, String serviceNM) {
		String xmlFmtHead = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://server.net.uxun.com/\">"
				+ "\n<soapenv:Header/>" + "\n  <soapenv:Body>" + "\n     <soap:" + serviceNM + ">\n		";

		String xmlFmtEnd = "\n     </soap:" + serviceNM + ">" + "\n   </soapenv:Body>" + "\n</soapenv:Envelope>";

		int iHeadLen = xmlFmtHead.getBytes().length;
		int iEndLen = xmlFmtEnd.getBytes().length;
		int iLen = iHeadLen + recvData.length + iEndLen;

		byte[] soapMsg = new byte[iLen];
		//System.arraycopy(byte[]   A,int   i,byte[]   B,int   j,int   length); 
		//意思是从数组A第i个（即A[i]处，含A[i]）开始copy长度为length个的byte数据到数组B从第j个开始（即B[j]处，含B[j]）覆盖
		System.arraycopy(xmlFmtHead.getBytes(), 0, soapMsg, 0, iHeadLen);
		System.arraycopy(recvData, 0, soapMsg, iHeadLen, recvData.length);
		System.arraycopy(xmlFmtEnd.getBytes(), 0, soapMsg, iHeadLen + recvData.length, iEndLen);

		return soapMsg;
	}

	public void run() {
		// TODO 自动生成方法存根
		try {
			DoTran();
		} catch (Exception e) {
			Log.debug("通信关闭异常:" + e.toString());
		}
	}

}
