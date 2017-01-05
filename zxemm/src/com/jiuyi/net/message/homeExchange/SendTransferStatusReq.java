package com.jiuyi.net.message.homeExchange;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.homeExchange.CustInfo;

public class SendTransferStatusReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资金监管流水号
	 */
	private String orderId;
	
	/**
	 * 业务宗号
	 */
	private String workNo;
	
	/**
	 * 买方列表
	 */
	private List<CustInfo> sellerInfo;
	
	/**
	 * 卖方列表
	 */
	private List<CustInfo> buyerInfo;
	
	/**
	 * 处理结果
	 */
	private String retcode;
	
	/**
	 * 处理结果说明
	 */
	private String retshow;
	
	/**
	 * 预留字段1
	 */
	private String str1;
	
	/**
	 * 预留字段2                                     
	 */
	private String str2;
	
	/**
	 * 预留字段3
	 */
	private String str3;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public List<CustInfo> getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(List<CustInfo> sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public List<CustInfo> getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(List<CustInfo> buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetshow() {
		return retshow;
	}

	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}
	
	
}
