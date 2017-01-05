package com.jiuyi.jyplat.dao.acegiManage;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class OperatorDao extends HibernateEntityDao<Operator> {

	Logger log = Logger.getLogger(OperatorDao.class);

	@Resource
	private DBAction dbAction;

	/**
	 * 根据操作员拥有的角色功能ID获取Action
	 * 
	 * @return SysAction[]
	 * @throws Exception
	 */
	public Object[] queryActionByFunId(String functionNo) throws Exception {
		String sql = "SELECT * FROM SysAction WHERE ActionId IN (	"
				+ "SELECT ActionId FROM SysFunctionAction sfa,SysFunction sf 	" + "WHERE sfa.FunctionNo=sf.FunctionNo "
				+ "and sf.FunctionNo IN(" + functionNo + ") and ("
				+ "'1' = replace(ltrim(rtrim(sf.status)),' ','') or '2' = replace(ltrim(rtrim(sf.status)),' ',''))"
				+ ") and '1' = replace(ltrim(rtrim(status)),' ','')";

		return dbAction.executeSearchAll(sql, SysAction.class.getName());

	}

	/**
	 * 停用操作员
	 * 
	 * @param Operator
	 *            .opersNo
	 * @throws Exception
	 */
	public void updateOperStatus(String operNo, String status) throws Exception {
		Query query = createQuery("update Operator  set operStatus = :operStatus,modifiedDate = :modifiedDate where operNo = :operNo");
		query.setString("operNo", operNo);
		query.setString("operStatus", status); // 将操作员的状态置为停用
		query.setString("modifiedDate", DateUtils.dateToDateString(new Date()));
		query.executeUpdate();
	}

	/**
	 * 设置审核状态
	 * 
	 * @param Operator
	 *            .operNo
	 * @throws Exception
	 */
	public void updateOperAuthStatus(String operNo, String authStatus) {
		Query query = createQuery("update Operator  set authStatus = :authStatus where operNo = :operNo");
		query.setString("operNo", operNo);
		query.setString("authStatus", authStatus);
		query.executeUpdate();

	}

	/**
	 * 修改操作员信息
	 * 
	 * @param Operator
	 *            .opersNo
	 * @throws Exception
	 */
	public void updateOper(Operator oper) throws Exception {
		StringBuffer hql = new StringBuffer("update Operator  set modifiedDate=:modifiedDate ");

		if (oper.getOperName() != null)
			hql.append(", operName=:operName ");
		if (oper.getOperMobile() != null)
			hql.append(", operMobile=:operMobile ");
		if (oper.getOperStatus() != null && !oper.getOperStatus().trim().equals(""))
			hql.append(", operStatus=:operStatus ");
		if (oper.getRole() != null)
			hql.append(", role.roleId=:roleId ");
		if (oper.getInst() != null)
			hql.append(", inst.institutionId=:instId ");
		if (oper.getDepartment() != null && !oper.getDepartment().trim().equals(""))
			hql.append(", department=:department ");
		if (oper.getAuthStatus() != null && !oper.getAuthStatus().trim().equals(""))
			hql.append(", authStatus=:authStatus ");
		//操作员级别 add by lzb on 2012/08/07
		if (oper.getOperLvl() != null && !oper.getOperLvl().trim().equals(""))
			hql.append(", operLvl=:operLvl ");
		
		if(oper.getWxBranchId() != null){
			hql.append(", wxBranchId=:wxBranchId ");
		}
		if(StringUtils.isNotBlank(oper.getWxBranchName())){
			hql.append(", wxBranchName=:wxBranchName ");
		}
		
		hql.append(" where operNo=:operNo ");

		Query query = createQuery(hql.toString());

		if (oper.getOperName() != null)
			query.setString("operName", oper.getOperName());
		if (oper.getOperMobile() != null)
			query.setString("operMobile", oper.getOperMobile());
		if (oper.getOperStatus() != null && !oper.getOperStatus().trim().equals(""))
			query.setString("operStatus", oper.getOperStatus());
		if (oper.getRole() != null)
			query.setInteger("roleId", oper.getRole().getRoleId());
		if (oper.getInst() != null)
			query.setInteger("instId", oper.getInst().getInstitutionId());
		if (oper.getDepartment() != null && !oper.getDepartment().trim().equals(""))
			query.setString("department", oper.getDepartment());
		if (oper.getModifiedDate() == null)
			oper.setModifiedDate(DateUtils.dateToDateString(new Date()));
		if (oper.getOperLvl() != null && !oper.getOperLvl().trim().equals(""))
			query.setString("operLvl", oper.getOperLvl());
		
		if(oper.getWxBranchId() != null){
			query.setInteger("wxBranchId", oper.getWxBranchId());
		}
		if(StringUtils.isNotBlank(oper.getWxBranchName())){
			query.setString("wxBranchName", oper.getWxBranchName());
		}

		query.setString("modifiedDate", oper.getModifiedDate());
		query.setString("authStatus", oper.getAuthStatus());
		query.setString("operNo", oper.getOperNo());

		query.executeUpdate();
	}

	/**
	 * 修改操作员密码
	 * @param oper
	 * @throws Exception
	 */
	public void updateOperPwd(Operator oper) throws Exception {
		String hql = "update Operator set password=:password,modifiedDate=:modifiedDate where operNo=:operNo";
		Query query = createQuery(hql);
		query.setString("password", oper.getPassword()); //设置新密码
		query.setString("modifiedDate", DateUtils.dateToDateString(new Date())); //设置修改时间
		query.setString("operNo", oper.getOperNo());
		query.executeUpdate();
	}

	public Integer queryCountInStatus(String authStatus) throws Exception {

		Query query = getSession().createSQLQuery("select count(*) from Operator where authStatus = :authStatus");
		query.setString("authStatus", authStatus);

		// return (Integer)query.list().get(0);
		return Integer.parseInt(query.list().get(0).toString());
	}
}
