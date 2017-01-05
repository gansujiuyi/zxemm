package com.jiuyi.net.message.customers;

import javax.persistence.Entity;

public class Customers {
	private Integer memberNo; //会员号
	private Integer customersId;	//客户编号
	private String customersName;	//客户姓名
	private String phoneNo;	//手机号码
	private String email;	//邮箱
	private String customersType;	//客户类别 0 个人 1 企业 
	private String createTime;	//创建时间
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getCustomersId() {
		return customersId;
	}
	public void setCustomersId(Integer customersId) {
		this.customersId = customersId;
	}
	public String getCustomersName() {
		return customersName;
	}
	public void setCustomersName(String customersName) {
		this.customersName = customersName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomersType() {
		return customersType;
	}
	public void setCustomersType(String customersType) {
		this.customersType = customersType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
