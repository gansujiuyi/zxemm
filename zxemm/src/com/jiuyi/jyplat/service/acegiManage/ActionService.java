package com.jiuyi.jyplat.service.acegiManage;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface ActionService {
	/**
	 * 添加action动作
	 * 
	 * @param sysAction
	 * @return
	 */
	public void addAction(SysAction sysAction) throws Exception;

	/**
	 * 修改action动作
	 * 
	 * @param sysAction
	 * @return
	 */
	public void updateAction(SysAction sysAction) throws Exception;

	/**
	 * 根据actionId查询
	 * 
	 * @param actionId
	 * @return
	 */
	public SysAction queryActionById(Serializable actionId) throws Exception;

	/**
	 * 查询所有的action
	 * 
	 * @return
	 */
	public List<SysAction> queryAllAction() throws Exception;

	/**
	 * 批量删除action
	 * 
	 * @param actionId
	 * @return
	 */
	public void delAction(String actionIds) throws Exception;

	/**
	 * 根据actionName模糊查询（分页）
	 * @param actionName
	 * @param query
	 * @return
	 */
	public PageFinder<SysAction> queryByActionName(SysAction sysAction, Query query) throws Exception;

	/**
	 * 根据actionName查询 Action
	 * @param actionName
	 * @return
	 */
	public List<SysAction> queryByActionName(String checkNameAction) throws Exception;

	/**
	 * 根据actionName查询 Action__修改时的校验
	 * @param actionName
	 * @return
	 */
	public List<SysAction> CkNameForUpdate(String checkNameAction, String checkIdAction) throws Exception;
}
