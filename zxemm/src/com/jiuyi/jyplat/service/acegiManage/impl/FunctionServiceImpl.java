package com.jiuyi.jyplat.service.acegiManage.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.dao.acegiManage.FunctionDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.SysFunction;
import com.jiuyi.jyplat.service.acegiManage.FunctionActionService;
import com.jiuyi.jyplat.service.acegiManage.FunctionService;
import com.jiuyi.jyplat.service.acegiManage.RoleFunctionService;
import com.jiuyi.jyplat.util.OperConstant;

@Service
public class FunctionServiceImpl implements FunctionService {
	@Resource
	private FunctionDao functionDao;
	@Resource
	private LogInfoDao logDao;
	@Resource
	private FunctionActionService funcActionService;
	@Resource
	private RoleFunctionService roleFunctionService;

	Logger log = Logger.getLogger(FunctionServiceImpl.class);

	/**
	 * 添加系统功能
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addFunction(SysFunction function) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();
		String urls = function.getUrl();
		if (urls.indexOf(serverName + "/") == 0) {
			function.setUrl(urls.replace(serverName + "/", ""));
		}
		functionDao.save(function);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCTION, OperConstant.ACT_ADD, "");
	}

	/**
	 * 根据functionNo删除对应系统功能
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delFunction(String functionNo) throws Exception {
		String[] functionNos = functionNo.split(",");
		for (String str : functionNos) {
			//先删除功能角色关联关系
			roleFunctionService.delRelation(str.trim());
			//先删除功能与action对应关系
			funcActionService.delFunctionAction(str.trim());
			//删除系统功能
			functionDao.removeById(str.trim());
			log.info("删除系统功能成功！functionNo:" + str.trim());
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCTION, OperConstant.ACT_DEL, "functionNo:"
					+ str.trim());
		}
	}

	/**
	 * 查询所有系统功能
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysFunction> queryAllFunction() throws Exception {
		Criteria criteria = functionDao.createCriteria();
		criteria.addOrder(Order.desc("orderBy"));//按序号倒序排列
		return criteria.list();
	}

	/**
	 * 根据functionNo查询对应功能
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysFunction queryFunctionById(Serializable id) throws Exception {

		return functionDao.getById(id);
	}

	/**
	 * 修改系统功能
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFunction(SysFunction sysFunction) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();
		String urls = sysFunction.getUrl();
		if (urls.indexOf(serverName + "/") == 0) {
			sysFunction.setUrl(urls.replace(serverName + "/", ""));
		}
		functionDao.update(sysFunction);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_FUNCTION, OperConstant.ACT_EDIT, "functionNo:"
				+ sysFunction.getFunctionNo());
	}

	/**
	 * 查询对应的子功能
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysFunction> queryAllChildFunction(String functionNo) throws Exception {
		return functionDao.findBy("parentFunctionNo", functionNo.trim());
	}

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysFunction> CheckNamesubItem(String checkNamesubItem, String parentNo) {
		String sql = "select s from SysFunction s where s.functionName='" + checkNamesubItem + "'";
		if (null != parentNo && !"".equals(parentNo.trim())) {
			sql = sql + " and s.parentFunctionNo='" + parentNo + "'";
		} else {
			sql = sql + " and (s.parentFunctionNo='" + parentNo + "' or s.parentFunctionNo is null)";
		}
		return functionDao.find(sql, null);
	}

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在——修改时的校验
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysFunction> CheckNamesubfunUP(String checkNamesubItem, String parentNo, String functionNo) {
		String sql = "select s from SysFunction s where s.functionName='" + checkNamesubItem + "' and s.functionNo <>'"
				+ functionNo + "'";
		if (null != parentNo && !"".equals(parentNo.trim())) {
			sql = sql + " and s.parentFunctionNo='" + parentNo + "'";
		} else {
			sql = sql + " and (s.parentFunctionNo='" + parentNo + "' or s.parentFunctionNo is null)";
		}

		return functionDao.find(sql, null);
	}
}
