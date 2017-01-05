package com.jiuyi.net.message.certification;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class CertificationRspMsg {
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private CertificationRsp msgrsp;
	
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public CertificationRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(CertificationRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
}
