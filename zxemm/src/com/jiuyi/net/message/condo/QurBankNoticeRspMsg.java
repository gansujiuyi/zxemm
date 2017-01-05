package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class QurBankNoticeRspMsg implements Serializable{

	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头
	private QurBankNoticeRsp msgrsp;// 报文体
	
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public QurBankNoticeRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(QurBankNoticeRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
}
