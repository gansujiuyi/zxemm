package com.jiuyi.jyplat.service.bankAccoutInfo;

import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountBal;

 
public interface IAccountBalService  {
	
	/**
	 * 保存数据
	 * @param accountBal
	 */
	public void saveAccountBal(AccountBal accountBal);
  
	/**
	 * 根据客户ID和交易日期查询是否存在
	 */
	 
	public boolean checkDataByCustIdAndTranDate(String custId , String transactionDate);

}
