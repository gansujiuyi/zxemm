package com.jiuyi.net.message.ecode;

import java.io.Serializable;

import com.jiuyi.net.message.Head;

public class eSmsRspMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private eSmsRsp msgrsp;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public eSmsRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(eSmsRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
	

}
