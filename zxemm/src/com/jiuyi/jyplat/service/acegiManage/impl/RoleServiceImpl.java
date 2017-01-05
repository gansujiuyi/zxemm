package com.jiuyi.jyplat.service.acegiManage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.acegiManage.RoleDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.Role;
import com.jiuyi.jyplat.entity.system.RoleFunction;
import com.jiuyi.jyplat.service.acegiManage.RoleService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service
public class RoleServiceImpl implements RoleService {

	Logger log = Logger.getLogger(RoleServiceImpl.class);

	@Resource
	private RoleDao roleDao;

	@Resource
	private LogInfoDao logDao;

	/**
	 * 根据角色编号查询角色信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Role getRoleById(Integer roleId) throws Exception {
		return roleDao.getById(roleId);
	}

	/**
	 * 根据角色名称查询角色信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Role> getRoleByName(String roleName) throws Exception {
		Criteria criteria = roleDao.createCriteria();
		if (roleName != null && !"".equals(roleName)) {
			criteria.add(Restrictions.eq("roleName", roleName.trim()));
		}
		return criteria.list();
	}

	/**
	 * 分页查询所有角色信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<Role> queryRoleByName(String roleName, Query query) throws Exception {
		Criteria criteria = roleDao.createCriteria();
		if (null != roleName && !"".equalsIgnoreCase(roleName.trim())) {
			criteria.add(Restrictions.like("roleName", "%" + roleName.trim() + "%"));
		}
		//		criteria.addOrder(Order.desc("roleitutionId"));
		return roleDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("roleId"));
	}

	/**
	 * 查询所有角色信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Role> queryRoleList() throws Exception {
		return roleDao.createCriteria().list();
	}

	/**
	 * 保存新增角色信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertRole(Role role) throws Exception {
		roleDao.save(role);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ROLE, OperConstant.ACT_ADD, "");
	}

	/**
	 * 修改角色信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateRole(Role role) throws Exception {
		roleDao.update(role);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ROLE, OperConstant.ACT_EDIT,
				"角色编号：" + role.getRoleId());
	}

	/**
	 * 删除角色信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delRole(Integer[] roleIds) throws Exception {
		if (roleIds == null || roleIds.length < 1)
			throw new Exception("数据格式错误,删除角色失败");
		for (Integer id : roleIds) {
			//判断该角色是否为超级管理员角色
			if (id == Constant.SYSTEM_ADMIN_ROLE_1 || id == Constant.SYSTEM_ADMIN_ROLE_2) {
				throw new Exception("角色为超级管理员，无法删除！");
			}
			roleDao.removeById(id);
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ROLE, OperConstant.ACT_DEL, "角色编号：" + id);
		}
	}

	/**
	 * 保存角色与功能关联记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertRoleFunction(String roleId, String functionIds) throws Exception {
		if (roleId == null || roleId.trim().equals("") || functionIds == null || functionIds.trim().equals(""))
			throw new Exception("数据格式错误,角色权限关联错误!");
		delRoleFunction(roleId); // 插入前先清空以前的关联记录		
		String fids[] = functionIds.split(",");
		for (String string : fids) {
			roleDao.insertRoleFunction(roleId, string);
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ROLEFUN, OperConstant.ACT_ADD, "角色编号："
					+ roleId + ",功能编号" + string);
		}
	}

	/**
	 * 删除角色与功能关联记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delRoleFunction(String roleId) throws Exception {
		if (roleId == null || roleId.trim().equals(""))
			throw new Exception("数据格式错误,角色权限关联错误!");

		roleDao.delRoleFunction(roleId);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ROLEFUN, OperConstant.ACT_DEL, "角色编号：" + roleId);
	}

	/**
	 * 查询角色关联的功能
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String queryFunByRole(String roleId) throws Exception {
		if (roleId == null || roleId.trim().equals(""))
			throw new Exception("数据格式错误,查询角色权限关联错误!");

		String funIds = "";
		Object[] rf = roleDao.queryFunByRoleId(roleId.trim());
		if (rf == null)
			return "";
		for (Object obj : rf) {
			RoleFunction roleFunction = (RoleFunction) obj;
			funIds += roleFunction.getFunctionNo().trim() + ",";
		}
		if (funIds.trim().length() > 1)
			funIds = funIds.substring(0, funIds.length() - 1);
		return funIds;
	}

	/**
	 * 根据所属平台查询所有角色信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Role> queryRoleByPlat(String belongPlat) throws Exception {
		Criteria c = roleDao.createCriteria();
		if (belongPlat != null && !"".equals(belongPlat)) {
			c.add(Restrictions.eq("belongplat", belongPlat));
		}
		return c.list();
	}

}
