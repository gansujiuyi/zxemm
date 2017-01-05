package com.jiuyi.net.message.unioncollect;

public class ThirdCardCollectRsp {
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	
	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 金额
	 */
	private String amt;

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
	
}
