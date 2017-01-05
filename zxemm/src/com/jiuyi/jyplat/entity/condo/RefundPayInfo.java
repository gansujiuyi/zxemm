package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 购房退款流水表
 * 一个商品会有多个支付成功的流水。
 * 取完成支付的那几个的总和。
 * @author wsf
 *
 */
@Entity(name = "t_refundPayInfo")
public class RefundPayInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;

	private String contactNo;//订单编号
	private String payStatus;//交易状态  参见OrderEnumDefine中相关定义    0000：未支付    0001：支付中   0010：支付成功    0020 ：支付失败

	private BigDecimal payMoney;//支付金额

	private String devid;//商户设备号
	
	private String createTime;//流水生成时间
	private String payTime;//支付时间
	private String payDay;//付款日期 yyyy-MM-dd
	private String refundTime;//退款时间

	//线下pose
	private String payCardNo;//支付用银行卡号
	private String payTradeNo;//付款流水号
	
	private String payBankName;//刷卡行（缴存银行）
	private String payBankInst;//银行卡网点 

	private String transaction_id;//兰银易付支付流水号	
	private String payTranschannel;//支付渠道
	private String oppName;//户名
	
	private String note;//备注
	

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

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayDay() {
		return payDay;
	}

	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getPayTradeNo() {
		return payTradeNo;
	}

	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public String getPayBankInst() {
		return payBankInst;
	}

	public void setPayBankInst(String payBankInst) {
		this.payBankInst = payBankInst;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getPayTranschannel() {
		return payTranschannel;
	}

	public void setPayTranschannel(String payTranschannel) {
		this.payTranschannel = payTranschannel;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
