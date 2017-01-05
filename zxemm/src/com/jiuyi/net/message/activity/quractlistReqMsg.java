package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
@XmlRootElement
public class quractlistReqMsg implements Serializable {

	private static final long serialVersionUID = -9177928134783677638L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private quractlistReq msgreq;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public quractlistReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(quractlistReq msgreq) {
		this.msgreq = msgreq;
	}

}
