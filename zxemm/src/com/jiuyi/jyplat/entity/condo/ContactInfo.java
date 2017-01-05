package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * <P>
 * 购买合同信息
 * </P>
 * 
 * @author sunzb
 */
@Entity(name = "t_contactInfo")
public class ContactInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	private String id;
	/**
	 * 合同编号
	 */
	private String contactNo;
	/**
	 * 合同金额
	 */
	private String contactAmt;

	/**
	 * 监管金额（已支付金额）
	 */
	private String superviseAmt;
	/**
	 * 支付状态 参见OrderEnumDefine中相关定义 0000：未支付 0010：支付成功 2222：退款完成
	 */
	private String payStatus;
	/**
	 * 合同状态 0000 初始状态（待支付） 1111 退款中（合同已撤销） 2222已退款 3333支付完成
	 */
	private String status;
	/**
	 * 地幢号
	 */
	private String houseNo;
	/**
	 * 建筑面积
	 */
	private String houseArea;
	/**
	 * 房屋坐落
	 */
	private String houseAddr;
	/**
	 * 退款银行卡号
	 */
	private String backCardNo;
	/**
	 * 退款银行卡姓名
	 */
	private String backCardName;
	/**
	 * 室号
	 */
	private String roomNo;
	/**
	 * 单元号
	 */
	private String unitNo;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 企业编号
	 */
	private String companyNo;

	/**
	 * 合同创建时间
	 */
	private String createDate;
	/**
	 * 付款方式
	 */
	private String paymentCode;

	/**
	 * 首付/贷款
	 */
	private String payCont;
	/**
	 * 操作员编号
	 */
	private String operNo;

	/***
	 * 银行号
	 */
	private String bankCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactAmt() {
		return contactAmt;
	}

	public void setContactAmt(String contactAmt) {
		this.contactAmt = contactAmt;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public String getBackCardNo() {
		return backCardNo;
	}

	public void setBackCardNo(String backCardNo) {
		this.backCardNo = backCardNo;
	}

	public String getBackCardName() {
		return backCardName;
	}

	public void setBackCardName(String backCardName) {
		this.backCardName = backCardName;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuperviseAmt() {
		return superviseAmt;
	}

	public void setSuperviseAmt(String superviseAmt) {
		this.superviseAmt = superviseAmt;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPayCont() {
		return payCont;
	}

	public void setPayCont(String payCont) {
		this.payCont = payCont;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
