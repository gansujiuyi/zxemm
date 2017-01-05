package com.jiuyi.net.message.certification;

import java.io.Serializable;
import java.util.List;

public class IsCertificatedRsp implements Serializable{
	
	private static final long serialVersionUID = -8238360958004558889L;
	
	private String retcode;
	private String retshow;
	private Integer memberNo;
	private String name;
	private String idType;
	private String idNo;
	private String bankId;
	private String accountNo;
	private List<String> listAccountNo;//add by baizilin  支付时获取当前实名用户的所有卡号
	private String phoneNo;
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
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	 
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public List<String> getListAccountNo() {
		return listAccountNo;
	}
	public void setListAccountNo(List<String> listAccountNo) {
		this.listAccountNo = listAccountNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
