package com.jiuyi.net.message.condo;

import java.io.Serializable;
/**
 * 按合同编号查询各户的缴存记录
 * @author wsf
 *
 */
public class QurRecordByContractNoReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String contractNo;// 合同编号
	
	private String bankCode;// 银行号，区分通知类型
	
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
}
