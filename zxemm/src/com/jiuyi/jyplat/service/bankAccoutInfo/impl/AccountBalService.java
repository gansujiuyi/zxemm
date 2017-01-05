package com.jiuyi.jyplat.service.bankAccoutInfo.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.bankAccountInfo.AccountBalDao;
import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountBal;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountBalService;
import com.jiuyi.jyplat.util.DBAction;

@Service("accountBalService")
public class AccountBalService implements IAccountBalService {

	Logger log = Logger.getLogger(AccountBalService.class);

	@Resource
	private AccountBalDao accountBalDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveAccountBal(AccountBal accountBal) {
		if(checkDataByCustIdAndTranDate(accountBal.getCustId(), accountBal.getTransactionDate())){
			accountBalDao.save(accountBal);
		}
		
	}

	@Override
	public boolean checkDataByCustIdAndTranDate(String custId,
			String transactionDate) {
         String sql ="select count(1) from T_ACCOUNTBAL t where t.transactionDate='"+transactionDate+"' and t.custId='"+custId+"'";
         DBAction dbAction = new DBAction();
         try {
			String[] ret = dbAction.executeSearch(sql);
			if(null!=ret && ret.length>0){
				if(Integer.valueOf(ret[0])>=1){
					return false;
				}else{
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			log.info("查询数据库出错");
		}
         return false;
	}
}
