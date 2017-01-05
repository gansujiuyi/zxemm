package com.jiuyi.net.message.onlinebank;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.unioncollect.UnionHead;


/**
 * @author yuran
 * 他行卡绑卡请求对象
 */
@XmlRootElement
public class BankCardBindReqMsg {
	/**
	 * 公共报文头
	 */
	private UnionHead head;
	
	/**
	 * 请求报文体
	 */
	private BankCardBindReq body;


	public UnionHead getHead() {
		return head;
	}

	public void setHead(UnionHead head) {
		this.head = head;
	}

	public BankCardBindReq getBody() {
		return body;
	}

	public void setBody(BankCardBindReq body) {
		this.body = body;
	}


}
