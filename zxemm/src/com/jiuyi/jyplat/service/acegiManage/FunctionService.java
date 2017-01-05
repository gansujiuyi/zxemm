package com.jiuyi.jyplat.service.acegiManage;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.system.SysFunction;

public interface FunctionService {
	/**
	 * 添加系统功能
	 * @param function
	 * @return
	 * @throws Exception
	 */
	public void addFunction(SysFunction function) throws Exception;

	/**
	 * 查询所有系统功能
	 * @return
	 */
	public List<SysFunction> queryAllFunction() throws Exception;

	/**
	 * 根据功能号查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysFunction queryFunctionById(Serializable id) throws Exception;

	/**
	 * 删除系统功能
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void delFunction(String functionNo) throws Exception;

	/**
	 * 修改系统功能
	 * @param sysFunction
	 * @return
	 * @throws Exception
	 */
	public void updateFunction(SysFunction sysFunction) throws Exception;

	/**
	 * 查询对应的子功能
	 * @param functionNo
	 * @return
	 * @throws Exception
	 */
	public List<SysFunction> queryAllChildFunction(String functionNo) throws Exception;

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在
	 * @param 
	 * @return
	 */
	public List<SysFunction> CheckNamesubItem(String checkNamesubItem, String parentNo) throws Exception;

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在——修改时的校验
	 * @return
	 */
	public List<SysFunction> CheckNamesubfunUP(String checkNamesubItem, String parentNo, String functionNo)
			throws Exception;

}
