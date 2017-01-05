package com.jiuyi.net.message.homeExchange;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class SendTransferStatusReqMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private Head head;// 报文头
	private SendTransferStatusReq sendTransferStatusReq;// 报文体
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public SendTransferStatusReq getSendTransferStatusReq() {
		return sendTransferStatusReq;
	}
	public void setSendTransferStatusReq(SendTransferStatusReq sendTransferStatusReq) {
		this.sendTransferStatusReq = sendTransferStatusReq;
	}
	
}
