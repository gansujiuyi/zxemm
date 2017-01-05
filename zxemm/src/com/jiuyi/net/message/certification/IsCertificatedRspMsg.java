package com.jiuyi.net.message.certification;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class IsCertificatedRspMsg {
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private IsCertificatedRsp msgrsp;
	
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public IsCertificatedRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(IsCertificatedRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
}
