package com.jiuyi.jyplat.web.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import xiaoyang.server.core.Server;
import xiaoyang.server.core.ServerFactory;

public class KernSocketServerInit extends HttpServlet implements Runnable {
	Logger log = Logger.getLogger(KernSocketServerInit.class);

	@Override
	public void run() {
//		log.debug("==================开始给核心提供侦听服务....");
//		List<Server> servers = ServerFactory.createServer("config.xml");
//		for (Server server : servers) {
//			server.start();
//		}
	}

	/* 系统参数初始化 */
	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		try {
//			// 开始线程 外网无需配置
//			new Thread(this).start();
//
//		} catch (Exception e) {
//			log.warn(e.toString());
//		}

	}
}
