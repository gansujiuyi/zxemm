package com.jiuyi.jyplat.dao.base;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.entity.system.LogInfo;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DataTransfer;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Repository
public class LogInfoDao extends HibernateEntityDao<LogInfo> {
	/**
	 * 添加操作日志
	 * @param operTypeNo
	 * @param operActionNo
	 */
	public void addLog(String platform, String module, String operAction, String description) throws Exception {
		LogInfo logInfo = new LogInfo();
		logInfo.setOperTime(DateUtils.dateToDateString(new Date(), DateUtils.DEFAULT_TIME));
		logInfo.setPlatform(platform);
		logInfo.setModule(module);
		logInfo.setOperAction(operAction);
		logInfo.setDescription(description);
		String currentIp = "", operNo = Constant.SYS_DEFAULT_OPERATOR;
		if (ActionContext.getContext() != null && ActionContext.getContext().getSession() != null
				&& ActionContext.getContext().getSession().get("currentIp") != null) {
			currentIp = ActionContext.getContext().getSession().get("currentIp").toString();
			operNo = SessionUtil.getOperator().getOperNo();
		}
		logInfo.setLoginIp(currentIp);
		logInfo.setOperNo(operNo);
		this.save(logInfo);
	}

	/**
	 * 查询操作日志
	 * @param logInfo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<LogInfo> queryAllLog(LogInfo logInfo, Query query) throws Exception {
		Criteria criteria = this.createCriteria();
		if (logInfo != null) {
			//查询日志生成时间大于开始时间的所有日志
			if (logInfo.getStartDate() != null && !logInfo.getStartDate().trim().equals("")) {
				criteria.add(Restrictions.ge("operTime", logInfo.getStartDate().trim() + " 00:00:00"));
			}
			//查询日志生成时间小于终止时间的所有日志
			if (logInfo.getEndDate() != null && !logInfo.getEndDate().equals("")) {
				criteria.add(Restrictions.le("operTime", logInfo.getEndDate().trim() + " 23:59:59"));
			}
			//添加按功能平台查询日志的查询条件
			if (logInfo.getPlatform() != null && !logInfo.getPlatform().trim().equals("")) {
				criteria.add(Restrictions.eq("platform", logInfo.getPlatform().trim()));
			}
			//添加按功能模块查询日志的查询条件
			if (logInfo.getModule() != null && !logInfo.getModule().trim().equals("")) {
				criteria.add(Restrictions.eq("module", logInfo.getModule().trim()));
			}
			//添加按操作动作查询日志的查询条件
			if (logInfo.getOperAction() != null && !logInfo.getOperAction().trim().equals("")) {
				criteria.add(Restrictions.eq("operAction", logInfo.getOperAction().trim()));
			}
			//添加按操作员编号查询日志的查询条件
			if (logInfo.getOperNo() != null && !logInfo.getOperNo().trim().equals("")) {
				criteria.add(Restrictions.eq("operNo", logInfo.getOperNo()));
			}
		}
		return this.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("logId"));
	}
}
