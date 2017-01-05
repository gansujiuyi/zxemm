package com.jiuyi.jyplat.dao.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.SysParameter;
import com.jiuyi.jyplat.entity.system.SysProcCtrl;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.Utils;

@Repository
public class SysParameterDao extends HibernateEntityDao<SysParameter> {

	/**
	 * 获取操作员初始密码
	 */
	public String queryOperDefaultPW() throws Exception {
		String defaultPw = "";
		List sp = this.createCriteria().list();
		if (sp != null && sp.size() > 0)
			defaultPw = ((SysParameter) sp.get(0)).getOperInitialPassword();
		return defaultPw;
	}

	/**
	 * 获取操作员初始密码
	 */
	public String queryWorkDate() throws Exception {
		String defaultPw = "";
		List sp = this.createCriteria().list();
		if (sp != null && sp.size() > 0)
			defaultPw = ((SysParameter) sp.get(0)).getSysWorkDate();
		return defaultPw;
	}

	public void upateSysParameter(SysParameter sysParameter) throws Exception {
		String hql = "UPDATE SysParameter SET OPERINITIALPASSWORD = OPERINITIALPASSWORD";
		if (Utils.notEmptyString(sysParameter.getSysWorkDate()))
			hql += ",sysWorkDate = :sysWorkDate";
		if (Utils.notEmptyString(sysParameter.getSysState()))
			hql += ",sysState = :sysState";

		Query query = this.createQuery(hql);

		query.setString("sysWorkDate", sysParameter.getSysWorkDate());
		query.setString("sysState", sysParameter.getSysState());

		query.executeUpdate();
	}
}
