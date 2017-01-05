package com.jiuyi.jyplat.service.base;

import java.util.Date;

import com.jiuyi.jyplat.entity.system.LogInfo;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface ILogInfoService {
	/**
	 * 分页查询所有日志信息
	 * @param logInfo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<LogInfo> queryAllLog(LogInfo logInfo, Query query) throws Exception;

	/**
	 * 保存日志信息
	 * @param logInfo
	 * @throws Exception
	 */
	public void saveLog(LogInfo logInfo) throws Exception;
}
