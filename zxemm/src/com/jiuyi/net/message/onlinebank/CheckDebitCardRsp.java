package com.jiuyi.net.message.onlinebank;

import java.io.Serializable;

/**
 * 检查快捷卡信息的应答报文体
 * @author lix
 * 2014-11-27
 */
@SuppressWarnings("serial")
public class CheckDebitCardRsp implements Serializable{

	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;

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
