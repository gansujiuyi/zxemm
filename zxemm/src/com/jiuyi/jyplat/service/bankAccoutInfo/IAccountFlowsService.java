package com.jiuyi.jyplat.service.bankAccoutInfo;

import java.util.List;

import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows;
import com.jiuyi.jyplat.entity.bankAccoutInfo.ContractTranstion;

public interface IAccountFlowsService {

	public void saveAccountFlows(List<AccountFlows> flows);

	public boolean checkAccountFlowByCustIdAndTranDate(String custId,
			String transactionDate, String transactionTime);
	
	/**
	 * 
	 * @param sysaccount 系统客户号
	 * @param transactiondate 交易日期
	 * @param debitcredittype 交易类型  0001 --借  0002 贷
	 * @return
	 */
	public List<AccountFlows> getAllFlowsByCondition(String sysaccount , String transactiondate , String debitcredittype);
	
	
	 /***
	  * 保存合同和流水绑定信息
	  */
	public void saveContrTranst(ContractTranstion contractTranstion);
	
	/***
	 * 更新流水状态
	 */
	public void updateAccountFlowById(String transactionSeqNum);
	
	/***
	 * 根据流水号查询支付信息
	 */
	public AccountFlows getAccountFlowsBySeqNum(AccountFlows accountFlows);

}


