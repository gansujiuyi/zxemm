package com.jiuyi.jyplat.service.acegiManage;

import java.util.List;

import com.jiuyi.jyplat.entity.system.Role;
import com.jiuyi.jyplat.entity.system.RoleFunction;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 角色业务
 * @author leiyongjun
 *  May 25, 2011  4:52:15 PM
 */
public interface RoleService {

	/**
	 * 根据主键ID查询角色
	 * @param RoleId
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(Integer roleId) throws Exception;

	/**
	 * 根据角色名称查询角色
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleByName(String roleName) throws Exception;

	/**
	 * 分页查询角色
	 * @param RoleName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Role> queryRoleByName(String roleName, Query query) throws Exception;

	/**
	 * 查询所有组织结果
	 * @return List<Role>
	 * @throws Exception
	 */
	public List<Role> queryRoleList() throws Exception;

	/**
	 * 新增角色
	 * @param Role
	 * @throws Exception
	 */
	public void insertRole(Role role) throws Exception;

	/**
	 * 修改角色
	 * @param Role
	 * @throws Exception
	 */
	public void updateRole(Role role) throws Exception;

	/**
	 * 删除角色
	 * @param Role
	 * @throws Exception
	 */
	public void delRole(Integer[] roleIds) throws Exception;

	/**
	 * 新增角色功能权限关联
	 * @param Role
	 * @throws Exception
	 */
	public void insertRoleFunction(String roleId, String functionIds) throws Exception;

	/**
	 * 删除角色功能权限关联
	 * @param Role
	 * @throws Exception
	 */
	public void delRoleFunction(String roleId) throws Exception;

	/**
	 * 根据角色ID查询FunctionId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public String queryFunByRole(String roleId) throws Exception;

	/**
	 * 根据所属平台查询所有角色信息
	 */
	public List<Role> queryRoleByPlat(String belongPlat) throws Exception;
}
