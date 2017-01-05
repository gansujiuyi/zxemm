package com.jiuyi.jyplat.dao.acegiManage;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.SysFunctionAction;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class FunctionActionDao extends HibernateEntityDao<SysFunctionAction> {
	@Resource
	private DBAction dbAction;

	/**
	 * 根据功能编号查询对应action
	 * @param functionNo
	 * @return
	 * @throws Exception
	 */
	public Object[] queryActionByFuncNo(String functionNo) throws Exception {
		String sql = "SELECT * FROM sysFunctionAction WHERE  (functionNo = '" + functionNo + "')";
		Object[] obj = dbAction.executeSearchAll(sql, SysFunctionAction.class.getName());
		return obj;
	}

	/**
	 * 根据功能编号删除相应的记录
	 */
	public void deleteByFunction(String functionNo) throws Exception {
		String sql = "delete FROM sysFunctionAction WHERE functionNo = '" + functionNo + "'";
		dbAction.executeDelete(sql);
	}
}
