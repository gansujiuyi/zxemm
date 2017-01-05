package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ThirdCardCollectRspMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;
	
	/**
	 * 返回报文体
	 */
	private ThirdCardCollectRsp body;

	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public ThirdCardCollectRsp getBody() {
		return body;
	}

	public void setBody(ThirdCardCollectRsp body) {
		this.body = body;
	}
	
}
