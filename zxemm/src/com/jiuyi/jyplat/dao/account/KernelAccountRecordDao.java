package com.jiuyi.jyplat.dao.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.account.KernelAccountRecord;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class KernelAccountRecordDao extends HibernateEntityDao<KernelAccountRecord> {

	/**
	 * 根据时间查询时间段内的入账记录
	 * @param orderInfos
	 * @return
	 * @throws Exception
	 */
	public List<KernelAccountRecord> qurKernelRecords(String beginDate, String endDate, String flag) throws Exception{
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.le("traDate", endDate));
		criteria.add(Restrictions.ge("traDate", beginDate));
		if("C".equals(flag)){
			criteria.add(Restrictions.eq("loanSign", "C"));
		}else if("D".equals(flag)){
			criteria.add(Restrictions.eq("loanSign", "D"));
		}else{
			throw new Exception("查询核心账记录异常，出入账标示有误！");
		}
		return criteria.list();
	}
}
