package com.jiuyi.jyplat.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jiuyi.net.filter.ParamFilter;
import com.jiuyi.jyplat.web.util.SysConfig;

public class KeyWordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(KeyWordServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<String> temp = SysConfig.queryConfig();
		SysConfig.getInstance().setKeyword(temp);
		ParamFilter.instance().setKeyWord(temp);
		logger.info("更新EMM中的关键字成功!");
	}
}
