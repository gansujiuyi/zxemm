package com.jiuyi.net.message.unioncollect;

public class ThirdCardCollectReq {
	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 代收卡号
	 */
	private String bankCard;
	
	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 交易金额
	 */
	private String amt;
	
	/**
	 * 币种,现金支付币种,取值：1（人民币）, 默认值是1，暂只支持1
	 */
	private String currencyType;
	
	/**
	 * 备注
	 */
	private String remark;

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

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
