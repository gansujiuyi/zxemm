package com.jiuyi.net.socketServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import org.jfree.util.Log;

import xiaoyang.server.core.Request;
import xiaoyang.server.core.ServerHandler;
import xiaoyang.server.log.ServerLoggerFactory;

import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.XmlToMap;

public class EzexServerHandler extends ServerHandler {
	Logger logger = ServerLoggerFactory.getLogger(EzexServerHandler.class);

	@Override
	public void process(Request request) {
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Socket requestSocket = request.getRequestSocket();
		InputStream is = null;
		OutputStream out = null;
		try {
			is = requestSocket.getInputStream();
			byte[] buf = new byte[512];
			int len;
			while ((len = is.read(buf)) != -1) {
				String reqXml = new String(buf, 0, len, "utf-8");
				logger.info("接收到的数据："+reqXml);
				XmlToMap xm = new XmlToMap();
				Map map = xm.toMap(reqXml);
				
				Log.info("IdNo:"+map.get("IdNo").toString());
				Log.info("RealName:"+map.get("RealName").toString());
				
				String result = "0";
				if("000001".equals(map.get("TranCode")) && !"".equals(map.get("IdNo").toString()) && !"".equals(map.get("RealName").toString())){
					logger.info("\n======service========IdNo=="+map.get("IdNo").toString());
					logger.info("\n=======service=======RealName=="+map.get("RealName").toString());
					
					DBAction db = new DBAction();
					
					String sql = "SELECT * FROM t_PartiesInfo t where t.idno='"+map.get("IdNo").toString()+"' and t.realname='"+map.get("RealName").toString()+"'";
					try {
						String[] lp = db.executeSearch(sql);
						logger.info("查询数据库的数据："+lp);
						if(lp != null){
							if(lp.length > 0){
								result = "1";
							}else{
								result = "0";
							}
						}else{
							result = "0";
						}
						// 定义发送的字符串
						String sendString = "<?xml version='1.0' encoding='UTF-8'?><EBANK><HEAD><RspCode>000000</RspCode><RspMsg>交易成功</RspMsg></HEAD><BODY><Result>"+result+"</Result></BODY></EBANK>";
						logger.info("要发送的响应报文："+sendString);
						
						out = requestSocket.getOutputStream();
						out.write(sendString.getBytes("utf-8"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (is != null) {
					is.close();
				}

				if (requestSocket != null)
					requestSocket.close();
			} catch (IOException e1) {
				ServerLoggerFactory.throwRuntimeExceptionAndLog(this.logger,
						"resource release exception", e1);
			}
		} finally {
			try {
				if (is != null) {
					is.close();
				}

				if (requestSocket != null)
					requestSocket.close();
			} catch (IOException e) {
				ServerLoggerFactory.throwRuntimeExceptionAndLog(this.logger,
						"resource release exception", e);
			}
		}
	}

}
