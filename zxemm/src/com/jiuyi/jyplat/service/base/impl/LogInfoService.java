package com.jiuyi.jyplat.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.LogInfo;
import com.jiuyi.jyplat.service.base.ILogInfoService;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service
public class LogInfoService implements ILogInfoService {
	@Resource
	private LogInfoDao logDao;

	/**
	 * 查询操作日志
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<LogInfo> queryAllLog(LogInfo logInfo, Query query) throws Exception {
		return logDao.queryAllLog(logInfo, query);
	}

	/**
	 * 新增操作日志
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveLog(LogInfo logInfo) throws Exception {
		logDao.save(logInfo);
	}

}
