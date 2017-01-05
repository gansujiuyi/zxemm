package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.entity.condo.BuildingTemp;
import com.jiuyi.jyplat.util.HibernateEntityDao;
/**
 * 审核划款实体类Dao层
 * @author wsf
 *
 */
@Repository("transferMoneyDao")
public class AuditTransferMoneyDao extends HibernateEntityDao<AuditTransferMoney> {
	
	public List<AuditTransferMoney> qurAuditTransferMoney(String acceptcode, String houseNo){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("houseNo", houseNo));
		criteria.add(Restrictions.eq("acceptcode", acceptcode));

		@SuppressWarnings("unchecked")
		List<AuditTransferMoney> auditTransferMoneys = criteria.list();
		return auditTransferMoneys;
	}
}
