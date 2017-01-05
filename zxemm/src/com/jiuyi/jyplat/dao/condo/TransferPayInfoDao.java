package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;

/**
 * 购房划款流水类  Dao层
 * @author wsf
 *
 */
@Repository
public class TransferPayInfoDao extends HibernateEntityDao<TransferPayInfo> {
	
public List<TransferPayInfo> queryTodayTransferInfo(){
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.like("createTime", "%"+DateUtils.curDate()+"%"));

		@SuppressWarnings("unchecked")
		List<TransferPayInfo> condoTransferInfoList = criteria.list();
		return condoTransferInfoList;
		
	}
	
}
