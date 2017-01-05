package com.jiuyi.net.message.certification;

import java.io.Serializable;

public class IsCertificatedReq implements Serializable{
	
	private static final long serialVersionUID = 2536055361493481328L;
	
	/**
	 * 用户电话号码
	 */
	private String phoneNo;
	
	/**
	 * 用户真实姓名
	 */
	private String name;
	
	/**
	 * 银行卡号吗
	 */
	private String cardNo;
	
	/**
	 * 证件类型，01身份证
	 */
	private String idType;
	
	/**
	 * 证件号码
	 */
	private String idNo;
	
	/**
	 * 银行ID
	 */
	private String bankId;
	
	/**
	 * 查询类型：1:是否进行实名认证， 2：用户信息银行卡、电话、姓名验证， 3：卖方查询买方信息是否存在。
	 */
	private String queryType;
	
	/***
	 * 会员号
	 */
	
	private Integer memberNo;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	
}
