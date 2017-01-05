package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class QurRecordByContractNoRspMsg implements Serializable{

	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头
	private QurRecordByContractNoRsp msgrsp;// 报文体
	
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public QurRecordByContractNoRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(QurRecordByContractNoRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
}
