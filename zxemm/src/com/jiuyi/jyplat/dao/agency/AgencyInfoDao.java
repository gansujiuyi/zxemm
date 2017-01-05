package com.jiuyi.jyplat.dao.agency;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.jiuyi.jyplat.entity.agency.AgencyInfo;

import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.web.page.PageFinder;

@Repository
public class AgencyInfoDao extends HibernateEntityDao<AgencyInfo> {

	public void deleteAgencyCompany(String ids) throws Exception {
		String[] g = ids.split(",");//分割字符串'ids'
		for (int i = 0; i < g.length; i++) {
			String id = g[i];
			String sql = "delete from t_agencyinfo where id='" + id + "'";
			new DBAction().executeUpdate(sql);
		}
	}

	public PageFinder<AgencyInfo> qurAllAgencyCompany(Integer pageNow, Integer pageSize) throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("isbroker", "0"));//查询条件
		//return (PageFinder<AgencyInfo>) criteria.list();
		return this.pagedByCriteria(criteria, pageNow, pageSize);
	}

	public PageFinder<AgencyInfo> qurAllFreezeAgencyCompany(Integer pageNow, Integer pageSize) throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("isbroker", "0"));//查询条件
		criteria.add(Restrictions.eq("status", "1"));//查询条件
		return this.pagedByCriteria(criteria, pageNow, pageSize);
	}

	public PageFinder<AgencyInfo> qurAllUnFreezeAgencyCompany(Integer pageNow, Integer pageSize) throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("isbroker", "0"));//查询条件
		criteria.add(Restrictions.eq("status", "0"));//查询条件
		return this.pagedByCriteria(criteria, pageNow, pageSize);
	}

	public void freezeAgencyCompany(String ids) throws Exception {
		String[] g = ids.split(",");//分割字符串'ids'
		for (int i = 0; i < g.length; i++) {
			String id = g[i];
			String sql = "update t_agencyinfo set status=1 where id='" + id + "'";
			new DBAction().executeUpdate(sql);
		}
	}

	public void unFreezeAgencyCompany(String ids) throws Exception {
		String[] g = ids.split(",");//分割字符串'ids'
		for (int i = 0; i < g.length; i++) {
			String id = g[i];
			String sql = "update t_agencyinfo set status=0 where id='" + id + "'";
			new DBAction().executeUpdate(sql);
		}
	}

	/**
	 * weiyi
	 * <p>TODO</p>
	 * @param account
	 * @return
	 * @throws Exception
	 * @author pengq
	 */
	public Boolean qurAgencyIsHasByLoginaccount(String account) throws Exception {

		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("agencyloginaccount", account));//查询条件
		List<AgencyInfo> list = criteria.list();
		if (list != null && list.size() != 0) {
			return false;
		}
		return true;
	}

	public Boolean qurAgencyByLoginaccount(String account) throws Exception {

		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("agencyloginaccount", account));//查询条件
		List<AgencyInfo> list = criteria.list();
		if (list.size() > 1) {
			return false;
		}
		return true;
	}
	
	public List<AgencyInfo> qurAllAgency() throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("isbroker", "0"));//查询条件
		criteria.add(Restrictions.eq("status", "0"));//查询条件
		return criteria.list();
	}
	
	public List<AgencyInfo> qurAllBroker(String agencyNo) throws Exception {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("isbroker", "1"));//查询条件
		criteria.add(Restrictions.eq("status", "0"));//查询条件
		criteria.add(Restrictions.eq("brokeragencyno", agencyNo));
		return criteria.list();
	}
}
