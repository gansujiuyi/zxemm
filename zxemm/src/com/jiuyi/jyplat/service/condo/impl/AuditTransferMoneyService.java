package com.jiuyi.jyplat.service.condo.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.AuditTransferMoneyDao;
import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.service.condo.IAuditTransferMoneyService;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
/**
 * 审核划款实体类  Service层实现类
 * @author wsf
 *
 */
@Service("transferMoneyService")
public class AuditTransferMoneyService implements IAuditTransferMoneyService {

	Logger log = Logger.getLogger(AuditTransferMoneyService.class);
	
	@Resource
	private AuditTransferMoneyDao transferMoneyDao;

	/**
	 * 新增审批划款记录
	 * @param id
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveAuditTransferMoney(String houseNo , String amt) throws Exception {
		AuditTransferMoney auditTransferMoney = new AuditTransferMoney();
		auditTransferMoney.setHouseNo(houseNo);
		auditTransferMoney.setAmt(amt);
		auditTransferMoney.setState("1111");//1111：初始状态
		auditTransferMoney.setTransferMoneyDate(DateUtils.curTime());
		transferMoneyDao.save(auditTransferMoney);
		log.info("新增审批划款记录成功！");
	}

	/**
	 * 分页查询审批划款记录
	 * @param transferMoney
	 * @param query
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public PageFinder<AuditTransferMoney> getPageTransferMoney(AuditTransferMoney transferMoney ,  Query query , String flag)
			throws Exception {
		Criteria criteria = transferMoneyDao.createCriteria();
		if(null !=transferMoney ){
			if(transferMoney.getHouseNo() != null && !"".equals(transferMoney.getHouseNo().trim())){
				criteria.add(Restrictions.eq("houseNo", transferMoney.getHouseNo().trim()));
			}
		}
		return transferMoneyDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("transferMoneyDate"));
	}


	/**
	 * 补录出账信息
	 * @param houseNo
	 * @param outCardNo
	 * @param outBankId
	 * @param outCardName
	 * @param outPhone
	 * @param outIdNo
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void enteringOutCard(String houseNo, String outCardNo,
			String outBankId, String outCardName, String outPhone,
			String outIdNo) throws Exception {
		AuditTransferMoney money = transferMoneyDao.findUniqueBy("houseNo", houseNo);
		money.setOutCardNo(outCardNo);
		money.setOutBankId(outBankId);
		money.setOutCardName(outCardName);
		money.setOutPhone(outPhone);
		money.setOutIdNo(outIdNo);
		money.setState("0000");
		transferMoneyDao.update(money);
		log.info("补录出账信息成功！");
	}

	
	/**
	 * 修改审批划款记录状态
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void updateTransferMoneyState(String id, String state)
			throws Exception {
		AuditTransferMoney money = transferMoneyDao.getById(id);
		money.setState(state);
		transferMoneyDao.update(money);
		log.info("修改审批划款记录状态成功！");
	}
	
	/**
	 * 根据主键Id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public AuditTransferMoney getById(String id) throws Exception {
		return transferMoneyDao.getById(id);
	}
}
