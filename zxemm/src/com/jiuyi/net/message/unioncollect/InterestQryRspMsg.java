package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InterestQryRspMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;
	
	/**
	 * 返回报文体
	 */
	private InterestQryRsp body;

	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public InterestQryRsp getBody() {
		return body;
	}

	public void setBody(InterestQryRsp body) {
		this.body = body;
	}

}
