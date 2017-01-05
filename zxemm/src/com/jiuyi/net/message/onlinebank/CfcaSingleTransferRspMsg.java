package com.jiuyi.net.message.onlinebank;

import com.jiuyi.net.message.Head;


public class CfcaSingleTransferRspMsg {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private CfcaSingleTransferRsp msgrsp;

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public Head getMsghead() {
		return msghead;
	}

	public void setMsgrsp(CfcaSingleTransferRsp msgrsp) {
		this.msgrsp = msgrsp;
	}

	public CfcaSingleTransferRsp getMsgrsp() {
		return msgrsp;
	}
}
