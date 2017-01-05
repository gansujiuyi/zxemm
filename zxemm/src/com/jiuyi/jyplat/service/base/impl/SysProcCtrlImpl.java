package com.jiuyi.jyplat.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.base.SysParameterDao;
import com.jiuyi.jyplat.dao.base.SysProcCtrlDao;
import com.jiuyi.jyplat.entity.system.SysParameter;
import com.jiuyi.jyplat.entity.system.SysProcCtrl;
import com.jiuyi.jyplat.service.base.ISysProcCtrlService;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service
public class SysProcCtrlImpl implements ISysProcCtrlService {

	@Resource
	private SysProcCtrlDao sysProcCtrlDao;
	@Resource
	private SysParameterDao sysParameterDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysProcCtrl> querySysProcCtrl(SysProcCtrl sysProcCtrl) throws Exception {
		return sysProcCtrlDao.querySysProcCtrl(sysProcCtrl);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<SysProcCtrl> queryAll4Page(SysProcCtrl sysProcCtrl, Query query) throws Exception {
		Criteria criteria = this.sysProcCtrlDao.createCriteria();
		if (sysProcCtrl != null) {
			if (Utils.notEmptyString(sysProcCtrl.getProcID())) {
				criteria.add(Restrictions.eq("procID", sysProcCtrl.getProcID()));
			}
			if (Utils.notEmptyString(sysProcCtrl.getTaskDec())) {
				criteria.add(Restrictions.like("taskDec", sysProcCtrl.getTaskDec(), MatchMode.ANYWHERE));
			}
		}
		return this.sysProcCtrlDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(),
				Order.asc("taskSeqNo"));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delSysProcCtrl(String taskId) throws Exception {
		String[] ids = taskId.split(",");
		for (int i = 0; i < ids.length; i++) {
			this.sysProcCtrlDao.removeById(ids[i].trim());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveSysProcCtrl(SysProcCtrl sysProcCtrl) throws Exception {
		this.sysProcCtrlDao.save(sysProcCtrl);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSysProcCtrl(SysProcCtrl sysProcCtrl) throws Exception {
		this.sysProcCtrlDao.update(sysProcCtrl);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysParameter querySysParameter() throws Exception {
		List sp = sysParameterDao.createCriteria().list();
		if (sp != null && sp.size() > 0)
			return (SysParameter) sp.get(0);
		else
			return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSysParameter(SysParameter sysParameter) throws Exception {
		sysParameterDao.upateSysParameter(sysParameter);
	}

	/**
	 * 查询所有有效的批处理任务（批处理监控用）
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysProcCtrl> querySysProcCtrl() throws Exception {
		Criteria criteria = this.sysProcCtrlDao.createCriteria();
		//设置批处理任务执行状态为1有效的查询条件
		criteria.add(Restrictions.eq("recState", "1"));
		//设置排序顺序
		criteria.addOrder(Order.asc("taskSeqNo"));
		return criteria.list();
	}
}
