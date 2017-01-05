package com.jiuyi.jyplat.service.acegiManage;

import java.util.List;

import com.jiuyi.jyplat.entity.menu.Level1;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.entity.system.SysFunction;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface OperatorService {

	public Operator getOperator(Operator o);

	//	public void updateOperator(Operator operator);

	/**
	 * 操作员登陆成功后系统处理
	 * @param Operator
	 * @return 
	 */
	public void loginSuccess(Operator o) throws Exception;

	/**
	 * 操作员登陆失败后系统处理
	 * @param Operator
	 * @return 
	 */
	public void loginFail(Operator o);

	/**
	 * 获取操作员拥有的功能菜单
	 */
	public String getMenuHtml() throws Exception;

	/**
	 * 取登陆操作员所拥有的功能菜单
	 * @return List<Level1>
	 * @throws Exception
	 */
	public List<Level1> getAllMenuList() throws Exception;

	/**
	 * 根据传入的List<SysFunction>取功能菜单
	 * @param List<SysFunction>
	 * @return List<Level1>
	 * @throws Exception
	 */
	public List<Level1> getAllMenuList(List<SysFunction> sysFunctions) throws Exception;

	/**
	 * 根据主键ID查询操作员
	 * @param RoleId
	 * @return
	 * @throws Exception
	 */
	public Operator getOperatorByNo(String operNo) throws Exception;

	/**
	 * 分页查询操作员
	 * @param OperName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Operator> queryOperatorByName(String operName, Query query) throws Exception;

	/**
	 * 按条件分页查询操作员
	 * @param operName
	 * @param operStatus
	 * @param department
	 * @param institutionId
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Operator> queryOperator(String operName, String operStatus, String department,
			String institutionId, Query query) throws Exception;

	/**
	 * 查询所有组织结果
	 * @return List<Oper>
	 * @throws Exception
	 */
	public List<Operator> queryOperatorList() throws Exception;

	/**
	 * 根据属性查询所有操作员
	 * @param Oper
	 * @return List<Oper>
	 * @throws Exception
	 */
	public List<Operator> queryAllOperator(Operator oper) throws Exception;

	/**
	 * 新增操作员
	 * @param Oper
	 * @throws Exception
	 */
	public void insertOperator(Operator oper) throws Exception;

	/**
	 * 修改操作员
	 * @param Oper
	 * @throws Exception
	 */
	public void updateOperator(Operator oper) throws Exception;

	/**
	 * 删除操作员
	 * @param Oper
	 * @throws Exception
	 */
	public void delOperator(Operator oper) throws Exception;

	public String getAddRoleHtml() throws Exception;

	/**
	 * 查询某个角色是否被操作员引用
	 * @param roleId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean queryByRole(Integer[] roleId) throws Exception;

	/**
	 * 查询所有待审核的操作员
	 * @param authStatus
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Operator> queryByOperStatus(String operName, String authStatus, String operStatus,
			String department, String institutionId, Query query) throws Exception;

	/**
	 * 审核操作员
	 * @param oper
	 * @throws Exception
	 */
	public void auditOperator(Operator oper) throws Exception;

	/**
	 * 查询系统中默认的系统操作员初始密码
	 * @return String
	 * @throws Exception
	 */
	public String queryOperDefaultPW() throws Exception;

	/**
	 * 查询所有某些登录号在某些状态的操作员总数(count)
	 * @return Integer
	 * @throws Exception
	 */
	public Integer queryCountInStatus(String authStatus) throws Exception;

	/**
	 * 修改操作员密码
	 * @param oper
	 * @throws Exception
	 */
	public void updateOperPwd(Operator oper) throws Exception;

}
