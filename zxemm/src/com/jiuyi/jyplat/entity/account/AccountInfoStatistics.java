package com.jiuyi.jyplat.entity.account;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


public class AccountInfoStatistics implements Serializable {
private static final long serialVersionUID = 1L;

	private String operNo;
	private String OperName;
	private String orderNum;
	private String orderPrice;
	private String inAccountNum;
	private String iAccountPrice;
	private String outAccountNum;
	private String outAccountPrice;
	public String getOperNo() {
		return operNo;
	}
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}
	public String getOperName() {
		return OperName;
	}
	public void setOperName(String operName) {
		OperName = operName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getInAccountNum() {
		return inAccountNum;
	}

	public String getOutAccountNum() {
		return outAccountNum;
	}
	public void setOutAccountNum(String outAccountNum) {
		this.outAccountNum = outAccountNum;
	}
	public String getOutAccountPrice() {
		return outAccountPrice;
	}
	public void setOutAccountPrice(String outAccountPrice) {
		this.outAccountPrice = outAccountPrice;
	}
	public String getiAccountPrice() {
		return iAccountPrice;
	}
	public void setiAccountPrice(String iAccountPrice) {
		this.iAccountPrice = iAccountPrice;
	}
	public void setInAccountNum(String inAccountNum) {
		this.inAccountNum = inAccountNum;
	}
	
	
}
