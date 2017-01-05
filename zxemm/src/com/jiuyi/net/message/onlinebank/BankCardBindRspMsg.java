package com.jiuyi.net.message.onlinebank;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.unioncollect.UnionHead;


/**
 * @author yuran
 * 他行卡绑卡返回对象
 */
@XmlRootElement
public class BankCardBindRspMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;
	
	/**
	 * 返回报文体
	 */
	private BankCardBindRsp body;

	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public BankCardBindRsp getBody() {
		return body;
	}

	public void setBody(BankCardBindRsp body) {
		this.body = body;
	}
}
