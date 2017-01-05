package com.jiuyi.net.message.ecode;

import com.jiuyi.net.message.Head;

public class eSmsReqMsg {
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private eSmsReq msgreq;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public eSmsReq getMsgreq() {
		return msgreq;
	}
	public void setMsgreq(eSmsReq msgreq) {
		this.msgreq = msgreq;
	}
	

	

}
