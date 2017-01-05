package com.jiuyi.net.message.ecode;

import java.math.BigDecimal;

public class eSmsReq {
	
	/** 手机号码 */
	private String phoneNo;
	/** 短信内容 */
	private String noteContent;
	private String smsType;
	
	private String name;
	
	private String giftOrderId;
	private String carDisplace;//排量
	
	private String carType;//基本车型 1.6L
	
	private String phone;
	private String districtName;
	private String streetName;
	
	private String businessPhone;
	
	private String idNo;//身份证号
	private BigDecimal loanprice;//贷款金额；
	
	private String userPhone;//用户电话号码
	
	private String carName;
	
	private String buildingName;//楼盘名称
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGiftOrderId() {
		return giftOrderId;
	}
	public void setGiftOrderId(String giftOrderId) {
		this.giftOrderId = giftOrderId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCarDisplace() {
		return carDisplace;
	}
	public void setCarDisplace(String carDisplace) {
		this.carDisplace = carDisplace;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public BigDecimal getLoanprice() {
		return loanprice;
	}
	public void setLoanprice(BigDecimal loanprice) {
		this.loanprice = loanprice;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	
	
	
	
	

}
