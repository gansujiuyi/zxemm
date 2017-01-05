package com.jiuyi.net.message.homeExchange;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class SendTransferStatusRspMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private Head head;// 报文头
	private SendTransferStatusRsp sendTransferStatusRsp;// 报文体
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public SendTransferStatusRsp getSendTransferStatusRsp() {
		return sendTransferStatusRsp;
	}
	public void setSendTransferStatusRsp(SendTransferStatusRsp sendTransferStatusRsp) {
		this.sendTransferStatusRsp = sendTransferStatusRsp;
	}
}
