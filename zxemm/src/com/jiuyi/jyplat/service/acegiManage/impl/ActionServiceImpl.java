package com.jiuyi.jyplat.service.acegiManage.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.acegiManage.ActionDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.service.acegiManage.ActionService;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service
public class ActionServiceImpl implements ActionService {
	@Resource
	private ActionDao actionDao;
	@Resource
	private LogInfoDao logDao;
	//生成日志对象
	Logger log = Logger.getLogger(ActionServiceImpl.class);

	/**
	 * 添加action
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addAction(SysAction sysAction) throws Exception {
		actionDao.save(sysAction);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ACTION, OperConstant.ACT_ADD, "");
	}

	/**
	 * 删除action
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delAction(String actionIds) throws Exception {
		String[] ids = actionIds.split(",");//将jsp页面的actionIds字符串转换为数组
		for (int i = 0; i < ids.length; i++) {
			actionDao.removeById(Integer.parseInt(ids[i].trim()));
			log.info("成功删除编号为" + ids[i].trim() + "的action信息！");
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ACTION, OperConstant.ACT_DEL, "actionId:"
					+ ids[i].trim());
		}
	}

	/**
	 * 查询所有action
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysAction> queryAllAction() throws Exception {
		return actionDao.createCriteria().list();
	}

	/**
	 * 根据actionId查询action
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysAction queryActionById(Serializable actionId) throws Exception {
		return actionDao.getById(actionId);
	}

	/**
	 * 修改action信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAction(SysAction sysAction) throws Exception {
		actionDao.update(sysAction);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ACTION, OperConstant.ACT_EDIT, "actionId:"
				+ sysAction.getActionId());
	}

	/**
	 * 模糊查询action（分页）
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<SysAction> queryByActionName(SysAction sysAction, Query query) throws Exception {
		Criteria criteria = actionDao.createCriteria();
		//actionName不为空则添加action名称模糊查询条件，否则查询所有的action信息
		if (sysAction != null) {
			if (Utils.notEmptyString(sysAction.getActionName())) {
				criteria.add(Restrictions.like("actionName", "%" + sysAction.getActionName().trim() + "%"));
			}
			if (Utils.notEmptyString(sysAction.getDescription())) {
				criteria.add(Restrictions.like("description", "%" + sysAction.getDescription().trim() + "%"));
			}
		}
		return actionDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("actionId"));
	}

	/**
	 * 根据actionName查询 Action
	 * @param actionName
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysAction> queryByActionName(String checkNameAction) {
		return actionDao.findBy("actionName", checkNameAction);
	}

	/**
	 * 根据actionName查询 Action__修改时的校验
	 * 校验除了当前Action Id以外的Action
	 * @param actionName
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysAction> CkNameForUpdate(String checkNameAction, String checkIdAction) {
		String sql = "select s from SysAction s where s.actionName='" + checkNameAction + "' and s.actionId<>"
				+ checkIdAction;
		return actionDao.find(sql, null);
	}

}
