package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetInstractionInfoResultRspMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Head msghead;	//报文头
	
	private String instruction;//指令种类   1为开户指令  2 为审批划款指令  3为解除资金监管指令  4为撤销购买合同的指令  5 开发账户变更的指令  6购房入账通知指令  
	
	private String buiding;//地幢号
	
	private String amt;//划款金额
	
	private String modifyAccount;//开发商需要变更的账
	
	private String contactNo;//撤销的购买合同号
	
	private String bankCode;
	
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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
