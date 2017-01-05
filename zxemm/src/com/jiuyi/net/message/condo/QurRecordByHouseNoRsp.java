package com.jiuyi.net.message.condo;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
public class QurRecordByHouseNoRsp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String retcode;// 响应码。说明：0000为正常，否则为错误
	
	private String retshow;// 错误提示。说明Retcode为错误的时候出现错误提示
	
	private String houseNo;//地幢号
	
	private List<CondoPayInfo>  condoPayInfos;//入账流水信息
	
	private List<TransferPayInfo>   transferPayInfos;//出账流水信息
	
	private List<ContactInfo> contactInfos;//合同信息
	
	private List<CondoBuyer> condoBuyers;//购房人信息
	
	private String regulateAccount;//监管账号
	
	private String regulateAccName;//监管账号名称

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

	public List<CondoPayInfo> getCondoPayInfos() {
		return condoPayInfos;
	}

	public void setCondoPayInfos(List<CondoPayInfo> condoPayInfos) {
		this.condoPayInfos = condoPayInfos;
	}

	public List<TransferPayInfo> getTransferPayInfos() {
		return transferPayInfos;
	}

	public void setTransferPayInfos(List<TransferPayInfo> transferPayInfos) {
		this.transferPayInfos = transferPayInfos;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public List<CondoBuyer> getCondoBuyers() {
		return condoBuyers;
	}

	public void setCondoBuyers(List<CondoBuyer> condoBuyers) {
		this.condoBuyers = condoBuyers;
	}

	public String getRegulateAccount() {
		return regulateAccount;
	}

	public void setRegulateAccount(String regulateAccount) {
		this.regulateAccount = regulateAccount;
	}

	public String getRegulateAccName() {
		return regulateAccName;
	}

	public void setRegulateAccName(String regulateAccName) {
		this.regulateAccName = regulateAccName;
	}
	
}
