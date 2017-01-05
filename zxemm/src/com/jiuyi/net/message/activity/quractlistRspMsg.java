package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
@XmlRootElement
public class quractlistRspMsg implements Serializable {

	private static final long serialVersionUID = 5604838874882133641L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private quractlistRsp msgrsp;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public quractlistRsp getMsgrsp() {
		return msgrsp;
	}

	public void setMsgrsp(quractlistRsp msgrsp) {
		this.msgrsp = msgrsp;
	}

}
