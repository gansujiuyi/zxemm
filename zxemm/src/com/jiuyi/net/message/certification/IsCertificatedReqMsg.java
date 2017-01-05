package com.jiuyi.net.message.certification;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class IsCertificatedReqMsg {

	private static final long serialVersionUID = -6763570263026595772L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private IsCertificatedReq msgreq;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public IsCertificatedReq getMsgreq() {
		return msgreq;
	}
	public void setMsgreq(IsCertificatedReq msgreq) {
		this.msgreq = msgreq;
	}

	
}
