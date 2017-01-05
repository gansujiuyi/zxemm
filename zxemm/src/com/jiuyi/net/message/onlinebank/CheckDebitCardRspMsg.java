package com.jiuyi.net.message.onlinebank;

import java.io.Serializable;

import com.jiuyi.net.message.Head;
/**
 * 检查快捷卡信息的应答报文
 * @author lix
 * 2014-11-27
 */
@SuppressWarnings("serial")
public class CheckDebitCardRspMsg implements Serializable {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private CheckDebitCardRsp msgrsp;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public CheckDebitCardRsp getMsgrsp() {
		return msgrsp;
	}

	public void setMsgrsp(CheckDebitCardRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
}
