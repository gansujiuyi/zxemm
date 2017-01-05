package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

import com.jiuyi.net.message.Head;




public class DelHouseReqMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Head msghead;
	private DelHouseReq msgreq;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public DelHouseReq getMsgreq() {
		return msgreq;
	}
	public void setMsgreq(DelHouseReq msgreq) {
		this.msgreq = msgreq;
	}
	

}
