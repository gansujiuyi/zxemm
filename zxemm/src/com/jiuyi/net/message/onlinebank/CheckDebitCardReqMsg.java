package com.jiuyi.net.message.onlinebank;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
/**
 * 查询快捷卡信息的请求报文
 * @author lix
 * 2014-11-27
 */
@XmlRootElement
public class CheckDebitCardReqMsg {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private CheckDebitCardReq msgreq;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public CheckDebitCardReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(CheckDebitCardReq msgreq) {
		this.msgreq = msgreq;
	}
	
}
