package com.jiuyi.jyplat.web.servlet;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.Systemparas;

public class SystemInit extends HttpServlet implements Runnable {
	Logger Log = Logger.getLogger(SystemInit.class);

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ignore) {
		}
	}

	public void run() {
		Log.debug("启动监控线程程序，进行业务参数更新操作");

	}


	/* 系统参数初始化 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		readConfig(config);

		// 获取spring上下文信息 为servlet和外部集成构件使用
		ServletContext application = this.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(application);
		SpringContextUtil.setApplicationContext(wac);
		// 系统参数
		Constant.FJS_URL = config.getInitParameter("houseexchangewebservice");
		Constant.EZEX_ONLINEBANKADDRS=ConfigManager.getString("ezex_onlinebankaddrs", "").toString();//E住E行调用epaypalt地址
		Constant.BANKCODE = config.getInitParameter("bankCode");
		new Thread(this).start();
	}

	/* 动态读取SERVLET定义的所有参数 */
	private void readConfig(ServletConfig config) {
		//把所有的参数保存下来
		String strServletName = config.getServletName();
		for (Enumeration e = config.getInitParameterNames(); e.hasMoreElements();) {
			String strName = (String) e.nextElement();
			String strValue = config.getInitParameter(strName);
			//参数记录下来
			Systemparas.addUxunPara(strServletName, strName, strValue);
		}

	}
}
