package com.jiuyi.jyplat.service.acegiManage.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.acegiManage.FunctionActionDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.SysFunctionAction;
import com.jiuyi.jyplat.service.acegiManage.FunctionActionService;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.OperConstant;

@Service
public class functionActionServiceImpl implements FunctionActionService {
	@Resource
	private FunctionActionDao funcActionDao;
	@Resource
	private LogInfoDao logDao;

	/**
	 * 添加系统功能与action对应关系
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addFunctionAction(String functionNo, String actionIds) throws Exception {
		if (functionNo == null || functionNo.equals("") || actionIds == null) {
			throw new Exception("未获取到系统功能编号或action数据");
		}
		DBAction dbAction = new DBAction();
		String[] ids = actionIds.split(",");
		String sql = "";
		for (String actionId : ids) {
			sql = "INSERT INTO SysFunctionAction VALUES ('" + functionNo + "', " + Integer.parseInt(actionId) + ")";

			dbAction.executeUpdate(sql);
		}
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCACTION, OperConstant.ACT_ADD, "");
	}

	/**
	 * 根据功能编号查询与其关联的actionId
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String queryActionByFunctionNo(String functionNo) throws Exception {
		Object[] obs = funcActionDao.queryActionByFuncNo(functionNo);
		String actionIds = "";
		if (obs != null) {
			for (Object object : obs) {
				SysFunctionAction functionAction = (SysFunctionAction) object;
				actionIds += functionAction.getActionId() + ",";
			}
		}

		return actionIds;
	}

	/**
	 * 删除系统功能与action关联信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delFunctionAction(String functionNo) throws Exception {
		if (functionNo == null || functionNo.trim().equals(""))
			throw new Exception("未获取到系统功能编号数据!");

		DBAction dbAction = new DBAction();
		String sql = "DELETE FROM SysFunctionAction WHERE functionNo = '" + functionNo + "'";
		dbAction.executeDelete(sql);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCACTION, OperConstant.ACT_DEL, "functionNo:"
				+ functionNo.trim());
	}

}
