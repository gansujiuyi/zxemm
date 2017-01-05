package com.jiuyi.jyplat.dao.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.account.AccountManage;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class AccountManageDao extends HibernateEntityDao<AccountManage> {

	public List<AccountManage> qurAccountManage(String InstNo) throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("institutionno", InstNo));//查询条件
		return criteria.list();
	}
}
