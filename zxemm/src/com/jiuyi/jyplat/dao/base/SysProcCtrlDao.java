package com.jiuyi.jyplat.dao.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.SysProcCtrl;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.Utils;

@Repository
public class SysProcCtrlDao extends HibernateEntityDao<SysProcCtrl> {

	public List<SysProcCtrl> querySysProcCtrl(SysProcCtrl sysProcCtrl) throws Exception {
		Criteria criteria = super.createCriteria();

		criteria.addOrder(Order.desc("procID"));
		if (Utils.notEmptyString(sysProcCtrl.getProcID()))
			criteria.add(Restrictions.eq("procID", sysProcCtrl.getProcID()));// 主键
		if (Utils.notEmptyString(sysProcCtrl.getTaskDec()))
			criteria.add(Restrictions.eq("taskDec", sysProcCtrl.getTaskDec()));// 任务说明

		return criteria.list();
	}

	public void updateSysProcCtrl(SysProcCtrl sysProcCtrl) {
		String hql = "UPDATE SysProcCtrl SET procID = procID ";

		if (Utils.notEmptyString(sysProcCtrl.getTaskProcState())) {
			hql += ",taskProcState = :taskProcState";
		}
		hql += " where procID = :procID ";

		Query query = this.createQuery(hql);

		if (Utils.notEmptyString(sysProcCtrl.getTaskProcState())) {
			query.setString("taskProcState", sysProcCtrl.getTaskProcState());
		}
		query.setString("procID", sysProcCtrl.getProcID());

		query.executeUpdate();
	}
}
