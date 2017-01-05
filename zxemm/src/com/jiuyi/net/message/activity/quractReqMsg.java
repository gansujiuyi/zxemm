package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
@XmlRootElement
public class quractReqMsg implements Serializable {

	private static final long serialVersionUID = -5651754810426776639L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private quractReq msgreq;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public quractReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(quractReq msgreq) {
		this.msgreq = msgreq;
	}

}
