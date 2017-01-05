package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
@XmlRootElement
public class quractRspMsg implements Serializable {

	private static final long serialVersionUID = 8478079025930415958L;

	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private quractRsp msgrsp;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public quractRsp getMsgrsp() {
		return msgrsp;
	}

	public void setMsgrsp(quractRsp msgrsp) {
		this.msgrsp = msgrsp;
	}


}
