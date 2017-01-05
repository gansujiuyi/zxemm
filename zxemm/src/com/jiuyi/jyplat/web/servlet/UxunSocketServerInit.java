/**
 * <p>Title:             UxunSocketServerInit.java
 * <p>Description:       通信网关系统初始化
 * <p>Copyright:         Copyright (C), 2011  uxunchina .
 * <p>Company:           uxunchina
 * @author               zenglj
 * @version	             v1.0
 * @see		             com.jiuyi.jyplat.web.servlet.UxunSocketServerInit
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 05/25/2011	          zenglj	                             	         v1.0 
 */

package com.jiuyi.jyplat.web.servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.jiuyi.net.socketProcThread;
import com.jiuyi.jyplat.util.Constant;

/**
 * 实现内部接口短连接服务
 */
public class UxunSocketServerInit extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	Logger Log = Logger.getLogger(UxunSocketServerInit.class);
	public static String SEARCHCUSTID = "";
	private int serverPort = 8890; // 侦听端口
	private ServerSocket server; // 服务器
	private String proxyWebservice = "http://127.0.0.1:8080/" + Constant.SYS_SERVER_NAME + "/ESB/UXUNMSG";

	public void run() {
		Log.debug("开始启动侦听服务....");
		try {
			server = new ServerSocket(this.serverPort);

			while (true) {
				Socket clientsocket = server.accept();
				// 开一个线程进行具体处理并返回应答给客户端
				new socketProcThread(clientsocket, proxyWebservice);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.info("通信服务器启动成功，侦听端口：" + this.serverPort);

	}

	/* 系统参数初始化 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		/* 从web.xml文件中读取系统配置相关信息 */

		String portno = config.getInitParameter("portno");
		String maxconn = config.getInitParameter("maxconn");
		SEARCHCUSTID = config.getInitParameter("SEARCHCUSTID");
		this.proxyWebservice = config.getInitParameter("proxywebservice");

		this.serverPort = Integer.parseInt(portno.trim());
		Integer.parseInt(maxconn.trim());

		try {
			// 开始线程 外网无需配置
			new Thread(this).start();

		} catch (Exception e) {
			Log.warn(e.toString());
		}
	}
}
