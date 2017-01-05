package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author YR
 * 代收请求
 */
@XmlRootElement
public class ThirdCardCollectReqMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;
	
	/**
	 * 请求报文体
	 */
	private ThirdCardCollectReq body;

	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public ThirdCardCollectReq getBody() {
		return body;
	}

	public void setBody(ThirdCardCollectReq body) {
		this.body = body;
	}
	
}
