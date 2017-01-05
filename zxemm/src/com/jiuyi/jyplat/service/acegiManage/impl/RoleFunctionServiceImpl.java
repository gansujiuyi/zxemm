package com.jiuyi.jyplat.service.acegiManage.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.service.acegiManage.RoleFunctionService;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.OperConstant;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

	Logger log = Logger.getLogger(RoleFunctionServiceImpl.class);

	@Resource
	private LogInfoDao logDao;

	@Override
	public void delRelation(String functionNo) throws Exception {
		if (functionNo == null || functionNo.trim().equals(""))
			throw new Exception("未获取到系统功能编号数据!");

		DBAction dbAction = new DBAction();
		String sql = "delete from ROLEFUNCTION rl where rl.functionno = '" + functionNo + "'";
		dbAction.executeDelete(sql);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCACTION, OperConstant.ACT_DEL, "functionNo:"
				+ functionNo.trim());
	}

}
