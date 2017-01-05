package com.jiuyi.net.message.condo;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
public class QurAmountByBuildingNoRsp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String retcode;// 响应码。说明：0000为正常，否则为错误
	
	private String retshow;// 错误提示。说明Retcode为错误的时候出现错误提示
	
	private String houseNo;//地幢号
	
	private String bankCode;//
	
	private String regulateAmount;//监管余额
	
	private String regulateAccount;//监管账号

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetshow() {
		return retshow;
	}

	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getRegulateAmount() {
		return regulateAmount;
	}

	public void setRegulateAmount(String regulateAmount) {
		this.regulateAmount = regulateAmount;
	}

	public String getRegulateAccount() {
		return regulateAccount;
	}

	public void setRegulateAccount(String regulateAccount) {
		this.regulateAccount = regulateAccount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
		
}
