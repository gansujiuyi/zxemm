package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;
/**
 * 购房退款流水类 Dao层
 * @author wsf
 *
 */
@Repository
public class RefundPayInfoDao extends HibernateEntityDao<RefundPayInfo> {
	
public List<RefundPayInfo> queryTodayRefundInfo(){
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.like("createTime", "%"+DateUtils.curDate()+"%"));

		@SuppressWarnings("unchecked")
		List<RefundPayInfo> condoRefundInfoList = criteria.list();
		return condoRefundInfoList;
		
	}

}
