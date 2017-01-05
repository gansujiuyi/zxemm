package com.jiuyi.net.message.unioncollect;

public class InterestQryRsp {
	
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	
	/**
	 * 利息金额
	 */
	private String interestAmt;

	public String getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}

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

}
