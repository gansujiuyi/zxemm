package com.jiuyi.jyplat.dao.acegiManage;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.Role;
import com.jiuyi.jyplat.entity.system.RoleFunction;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class RoleDao extends HibernateEntityDao<Role> {

	@Resource
	private DBAction dbAction;

	/**
	 * 通过角色查询Function
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Object[] queryFunByRoleId(String roleId) throws Exception {

		String sql = "SELECT * FROM RoleFunction WHERE  (RoleId = '" + roleId + "')";
		Object[] obj = dbAction.executeSearchAll(sql, RoleFunction.class.getName());
		return obj;
	}

	/**
	 * 通过Function查询Action
	 * @param funIds
	 * @return
	 * @throws Exception
	 */
	public Object[] queryActionsByFunId(String funIds) throws Exception {

		String sql = "SELECT * FROM SysAction WHERE ActionId IN ( " + funIds + " )";

		return dbAction.executeSearchAll(sql, SysAction.class.getName());
	}

	public void insertRoleFunction(String roleId, String functionNo) {
		Query query = getSession().createSQLQuery(
				"INSERT INTO RoleFunction VALUES ('" + roleId + "','" + functionNo + "')");
		query.executeUpdate();
	}

	public void delRoleFunction(String roleId) {
		Query query = getSession().createSQLQuery("DELETE FROM RoleFunction WHERE  (RoleId = " + roleId + ")");
		query.executeUpdate();
	}
}
