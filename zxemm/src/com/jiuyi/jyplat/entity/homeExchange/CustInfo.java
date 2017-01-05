package com.jiuyi.jyplat.entity.homeExchange;

import java.io.Serializable;

public class CustInfo implements Serializable{
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 身份证号
	 */
	private String idNo;
	
	/**
	 * 电话号码
	 */
	private String phoneNo;
	
	/**
	 * 保留字段1
	 */
	private String str1;
	
	/**
	 * 保留字段2
	 */
	private String str2;
	
	/**
	 * 保留字段3
	 */
	private String str3;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	
}

