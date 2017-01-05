package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class qurGoodsInfoReqMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头
	private qurGoodsInfoReq msgreq;// 报文体

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public qurGoodsInfoReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(qurGoodsInfoReq msgreq) {
		this.msgreq = msgreq;
	}

}
