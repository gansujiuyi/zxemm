package com.jiuyi.net.message.onlinebank;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
/**
 * 查询它行卡信息的请求报文
 * @author lix
 * 2014-12-23 
 */
@XmlRootElement
public class MemberCfcaAccReqMsg {

	/** 报文头 */
	private Head msghead;
	
	/** 报文体 */
	private MemberCfcaAccReq msgreq;

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public Head getMsghead() {
		return msghead;
	}

	public void setMsgreq(MemberCfcaAccReq msgreq) {
		this.msgreq = msgreq;
	}

	public MemberCfcaAccReq getMsgreq() {
		return msgreq;
	}
	
	
}
