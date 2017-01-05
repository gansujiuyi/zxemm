package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;
/**
 * 购房支付流水表  Dao层
 * @author wsf
 *
 */
@Repository
public class CondoPayInfoDao extends HibernateEntityDao<CondoPayInfo> {
	
	
	
	public List<CondoPayInfo> queryTodayPayInfo(){
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.like("createTime", "%"+DateUtils.curDate()+"%"));

		@SuppressWarnings("unchecked")
		List<CondoPayInfo> condoPayInfoList = criteria.list();
		return condoPayInfoList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CondoPayInfo> queryCandoPayInfoList(CondoPayInfo condoPayInfo){
		Criteria criteria = createCriteria();
		if (!"".equals(condoPayInfo.getContactNo()) && condoPayInfo.getContactNo() != null) {
			criteria.add(Restrictions.eq("contactNo", condoPayInfo.getContactNo().trim()));
		}
		if(!"".equals(condoPayInfo.getPayCardNo()) && condoPayInfo.getPayCardNo() != null){
			criteria.add(Restrictions.eq("payCardNo", condoPayInfo.getPayCardNo().trim()));
		}
		if(!"".equals(condoPayInfo.getPayTradeNo()) && condoPayInfo.getPayTradeNo() != null){
			criteria.add(Restrictions.eq("payTradeNo", condoPayInfo.getPayTradeNo().trim()));
		}
		if(!"".equals(condoPayInfo.getPayDay()) && condoPayInfo.getPayDay() != null){
			criteria.add(Restrictions.eq("payDay", condoPayInfo.getPayDay().trim()));
		}
		return criteria.list();
	}
}
