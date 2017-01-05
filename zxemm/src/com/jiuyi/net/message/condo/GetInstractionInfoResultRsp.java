package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class GetInstractionInfoResultRsp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String instruction;//指令类型  1：开户操作   2：划款出账操作  3： 购房入账通知  4： 账户变更通知
	
	private String buiding;//地幢号
	
	private String amt;//划款金额
	
	private String modifyAccount;//开发商需要变更的账
	
	private String contactNo;//撤销的购买合同号
	
	private String code;//响应码   0000：请求成功    否则错误
	
	private String message;//消息提示

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getBuiding() {
		return buiding;
	}

	public void setBuiding(String buiding) {
		this.buiding = buiding;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
