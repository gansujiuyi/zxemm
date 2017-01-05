package com.jiuyi.net.message.onlinebank;

public class BankCardBindRsp {
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	
	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 绑定卡号
	 */
	private String bankCard;

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

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
}
