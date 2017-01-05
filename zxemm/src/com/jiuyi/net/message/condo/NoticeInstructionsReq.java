package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class NoticeInstructionsReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String instructionsVariety;// 指令种类 1为开户指令 2 为审批划款指令 3为解除资金监管指令
										// 4为撤销购买合同的指令 5 开发账户变更的指令 6购房入账通知指令

	private String houseNo; // 地幢号

	private String amt; // 划款金额

	private String contactNo;// 撤销的购买合同号

	private String modifyAccount;// 开发商需要变更的账户

	private String acceptcode;// 流水号

	private String bankCode;// 银行号，区分通知类型

	public String getInstructionsVariety() {
		return instructionsVariety;
	}

	public void setInstructionsVariety(String instructionsVariety) {
		this.instructionsVariety = instructionsVariety;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getAcceptcode() {
		return acceptcode;
	}

	public void setAcceptcode(String acceptcode) {
		this.acceptcode = acceptcode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
}
