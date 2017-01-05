package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 入账信息实体
 * @author wsf
 *
 */
@Entity(name = "t_inAccountRecord")
public class InAccountRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;  //主键id
	
	private String houseNo;  //地幢号
	
	private String amt;  //入账金额
	
	private String buyerName;  //购买人姓名
	
	private String buyerIdNo;  //购买人身份证号
	
	private String inDate;  //入账日期
	
	private String hosueAddr;  //房屋地址

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerIdNo() {
		return buyerIdNo;
	}

	public void setBuyerIdNo(String buyerIdNo) {
		this.buyerIdNo = buyerIdNo;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getHosueAddr() {
		return hosueAddr;
	}

	public void setHosueAddr(String hosueAddr) {
		this.hosueAddr = hosueAddr;
	}
	
}
