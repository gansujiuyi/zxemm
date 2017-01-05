package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class QurHotOrCommendEHouseReqMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头
	private QurHotOrCommendEHouseReq msgreq;// 报文体

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public QurHotOrCommendEHouseReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(QurHotOrCommendEHouseReq msgreq) {
		this.msgreq = msgreq;
	}

}
