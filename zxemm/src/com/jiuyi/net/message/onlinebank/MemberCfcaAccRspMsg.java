package com.jiuyi.net.message.onlinebank;

import com.jiuyi.net.message.Head;

/**
 * 检查它行卡信息的应答报文
 * @author lix
 * 2014-11-27
 */
public class MemberCfcaAccRspMsg {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private MemberCfcaAccRsp msgrsp;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public MemberCfcaAccRsp getMsgrsp() {
		return msgrsp;
	}

	public void setMsgrsp(MemberCfcaAccRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
}
