package com.jiuyi.jyplat.service.bankAccoutInfo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.bankAccountInfo.AccountFlowsDao;
import com.jiuyi.jyplat.dao.bankAccountInfo.ContractTranstionDao;
import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows;
import com.jiuyi.jyplat.entity.bankAccoutInfo.ContractTranstion;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountFlowsService;
import com.jiuyi.jyplat.util.DBAction;

@Service("accountFlowsService")
public class AccountFlowsService implements IAccountFlowsService {

	Logger log = Logger.getLogger(AccountFlowsService.class);

	@Resource
	private AccountFlowsDao accountFlowsDao;
	@Resource
	private ContractTranstionDao contractTranstionDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveAccountFlows(List<AccountFlows> flows) {
		if (null != flows && flows.size() > 0) {
			for (AccountFlows accountFlows : flows) {
				// 先判断是否已经存在数据
				if (checkAccountFlowByCustIdAndTranDate(
						accountFlows.getCustId(),
						accountFlows.getTransactionDate(),
						accountFlows.getTransactionTime())) {
					accountFlowsDao.save(accountFlows);
				}
			}
		}
	}

	@Override
	public boolean checkAccountFlowByCustIdAndTranDate(String custId,
			String transactionDate, String transactionTime) {
		String sql = "select count(1) from T_ACCOUNTFLOWS t where t.custId='"
				+ custId + "' and t.transactionDate='" + transactionDate
				+ "' and t.transactionTime='" + transactionTime + "'";
		DBAction dbAction = new DBAction();
		try {
			String[] ret = dbAction.executeSearch(sql);
			if (null != ret && ret.length > 0) {
				if (Integer.valueOf(ret[0]) >= 1) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			log.info("查询数据库失败!");
		}
		return false;
	}

	@Override
	public List<AccountFlows> getAllFlowsByCondition(String sysaccount,
			String transactiondate, String debitcredittype) {
		List<AccountFlows> flows =new ArrayList<AccountFlows>();
		Criteria criteria = accountFlowsDao.createCriteria();
		if(null!=sysaccount && !"".equals(sysaccount)){
			criteria.add(Restrictions.eq("sysAccount", sysaccount));
		}
		if(null!=transactiondate && !"".equals(transactiondate)){
			criteria.add(Restrictions.eq("transactionDate", transactiondate));
		}
		if(null!=debitcredittype && !"".equals(debitcredittype)){
			criteria.add(Restrictions.eq("debitCreditType", debitcredittype));
		}
		criteria.add(Restrictions.ne("status","1"));
		flows = criteria.list();
		return flows;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveContrTranst(ContractTranstion contractTranstion) {
		try {
			//判断流水和合同是否已经存在
			DBAction dbAction = new DBAction();
			String sqlCount = "select count(1) from t_contran a where a.contractno ='"
					+ contractTranstion.getContractNo()
					+ "' and a.transtno ='"
					+ contractTranstion.getTranstNo() + "'";
			String[] ret=dbAction.executeSearch(sqlCount);
			if(null!=ret ){
				 if(Integer.valueOf(ret[0])==0){
					 contractTranstionDao.save(contractTranstion);
					 String sql = "update T_ACCOUNTFLOWS t set t.status='1' where t.TRANSACTIONSEQNUM='"
							 + contractTranstion.getTranstNo() + "'";
					 accountFlowsDao.createSQLQuery(sql).executeUpdate();
				 }
			}
		} catch (Exception e) {
		}
		
	}

	@Override
	public void updateAccountFlowById(String transactionSeqNum) {
		String sql="update T_ACCOUNTFLOWS set t.status='1' where t.TRANSACTIONSEQNUM='"+transactionSeqNum+"'";
		 accountFlowsDao.createSQLQuery(sql).executeUpdate();
	}

	@Override
	public AccountFlows getAccountFlowsBySeqNum(AccountFlows accountFlows) {
		Criteria criteria = accountFlowsDao.createCriteria();
		criteria.add(Restrictions.eq("transactionSeqNum", accountFlows.getTransactionSeqNum()));
		if(null!=accountFlows){
//			if( !"".equals(accountFlows.getDebitCreditType())){
//				criteria.add(Restrictions.eq("debitCreditType", accountFlows.getDebitCreditType()));
//				if(!"".equals(accountFlows.getTransactionDate())){
//					criteria.add(Restrictions.eq("transactionDate", accountFlows.getTransactionDate()));
//				}
//			}
			
		}
		List<AccountFlows> list = criteria.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
}