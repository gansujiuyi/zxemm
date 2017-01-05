package com.jiuyi.jyplat.service.acegiManage;

/**
 * 角色 功能关联关系
 * @author leiyongjun
 *  May 25, 2011  4:52:15 PM
 */
public interface RoleFunctionService {

	/**
	 * 删除角色功能关联关系。
	 * @param functionNo
	 * @throws Exception
	 */
	public void delRelation(String functionNo) throws Exception;

}
