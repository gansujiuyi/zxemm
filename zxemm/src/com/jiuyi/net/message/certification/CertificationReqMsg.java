package com.jiuyi.net.message.certification;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class CertificationReqMsg {

	private static final long serialVersionUID = -6763570263026595772L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private CertificationReq msgreq;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public CertificationReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(CertificationReq msgreq) {
		this.msgreq = msgreq;
	}
}
