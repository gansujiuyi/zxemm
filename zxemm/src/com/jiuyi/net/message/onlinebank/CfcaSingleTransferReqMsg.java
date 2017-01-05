package com.jiuyi.net.message.onlinebank;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;


/**
 * 机构支付账户单笔转账的请求报文(中金)
 * @author lix
 * 2014-12-23 
 */
@XmlRootElement
public class CfcaSingleTransferReqMsg {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private CfcaSingleTransferReq msgreq;


	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public void setMsgreq(CfcaSingleTransferReq msgreq) {
		this.msgreq = msgreq;
	}

	public CfcaSingleTransferReq getMsgreq() {
		return msgreq;
	}
}
