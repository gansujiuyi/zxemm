package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InterestQryReqMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;

	/**
	 * 请求报文体
	 */
	private  InterestQryReq body;

	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public InterestQryReq getBody() {
		return body;
	}

	public void setBody(InterestQryReq body) {
		this.body = body;
	}
	
}
