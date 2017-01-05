package com.jiuyi.net.message.ecode;

import java.io.Serializable;

public class eSmsRsp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	

}
