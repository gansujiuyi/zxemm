package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

import com.jiuyi.net.message.Head;




public class DelHouseRspMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Head msghead;
	private DelHouseRsp msgrsp;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public DelHouseRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(DelHouseRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	

}
