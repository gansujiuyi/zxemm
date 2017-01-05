package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

import com.jiuyi.net.message.Head;

public class QurHotEHouseReqMsg implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private Head msghead;// 报文头
	private QurHotEHouseReq msgreq;// 报文体
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public QurHotEHouseReq getMsgreq() {
		return msgreq;
	}
	public void setMsgreq(QurHotEHouseReq msgreq) {
		this.msgreq = msgreq;
	}
	
	

}
