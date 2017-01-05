package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class QurRecordByHouseNoRspMsg implements Serializable{

	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头
	private QurRecordByHouseNoRsp msgrsp;// 报文体
	
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public QurRecordByHouseNoRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(QurRecordByHouseNoRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
	
}
