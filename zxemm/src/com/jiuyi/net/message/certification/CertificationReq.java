package com.jiuyi.net.message.certification;

import java.io.Serializable;

public class CertificationReq implements Serializable{
	
	private static final long serialVersionUID = 2536055361493481328L;

	/**
	 * 账号
	 */
	private String bankId;
	
	/**
	 * 银行卡号
	 */
	private String CardNo;
	
	/**
	 * 用户姓名
	 */
	private String realName;
	
	/**
	 * 证件类型
	 */
	private String idType;
	
	/**
	 * 身份证号码
	 */
	private String idNo;
	
	/**
	 * 用户电话号码
	 */
	private String phoneNo;
	
	/**
	 *会员号
	 */
	private Integer memberNo;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	
}
