package com.jiuyi.jyplat.service.acegiManage;

public interface FunctionActionService {
	/**
	 * 根据功能编号查询对应action
	 * 
	 * @param functionNo
	 * @return
	 * @throws Exception
	 */
	public String queryActionByFunctionNo(String functionNo) throws Exception;

	/**
	 * 添加系统功能与action关联信息
	 * 
	 * @param functionNo
	 * @param actionId
	 * @throws Exception
	 */
	public void addFunctionAction(String functionNo, String actionIds) throws Exception;

	/**
	 * 删除系统功能与action的关联信息
	 * 
	 * @param functionNo
	 * @throws Exception
	 */
	public void delFunctionAction(String functionNo) throws Exception;
}
