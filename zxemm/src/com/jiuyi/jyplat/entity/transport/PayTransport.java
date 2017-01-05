package com.jiuyi.jyplat.entity.transport;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 划账流水表
 * 
 * @author Administrator
 * 
 */
@Entity(name = "t_PayTransport")
public class PayTransport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String transportId;

	private String payDate;// 添加时间
	private String payType;// 打款的类型 打款给卖方(1001)、退款给买方(1000)。
	private String tranDate;// 转账时间
	private String status;// 状态 待处理(0000) 支付中(0001) 支付成功(0002) 支付失败(0003) 支付异常(4)

	private String orderId;// 关联的订单ID
	private String orderType;// 关联的订单类型

	// 新添加字段（出金账号）
	private String sourceRealName;// 姓名
	private String sourceCardNo;// 卡号
	private String sourceIdNo;// 身份证
	private String sourcePhoneNo;// 手机号
	// 入金账号
	private String realName;// 姓名
	private String cardNo;// 卡号
	private String idNo;// 身份证
	private String phoneNo;// 手机号
	private Double money;// 金额
	private Double payFee;// 交易手续费

	// 新添加字段
	private String isPrincipal;// 0为利息，1为本金
	private String isCommission; // 0为房款，1为佣金

	// 与中金对接需要的内容
	private String TxSN;// 转账交易流水号（保证唯一不能重复）
	private String BankID;// 银行编号
	private String Remark; // 备注



	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getPayFee() {
		return payFee;
	}

	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}

	/**
	 * 打款的类型 打款给卖方、退款给买方。
	 * 
	 * @return
	 */
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTxSN() {
		return TxSN;
	}

	public void setTxSN(String txSN) {
		TxSN = txSN;
	}

	public String getBankID() {
		return BankID;
	}

	public void setBankID(String bankID) {
		BankID = bankID;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getSourceRealName() {
		return sourceRealName;
	}

	public void setSourceRealName(String sourceRealName) {
		this.sourceRealName = sourceRealName;
	}

	public String getSourceCardNo() {
		return sourceCardNo;
	}

	public void setSourceCardNo(String sourceCardNo) {
		this.sourceCardNo = sourceCardNo;
	}

	public String getSourceIdNo() {
		return sourceIdNo;
	}

	public void setSourceIdNo(String sourceIdNo) {
		this.sourceIdNo = sourceIdNo;
	}

	public String getSourcePhoneNo() {
		return sourcePhoneNo;
	}

	public void setSourcePhoneNo(String sourcePhoneNo) {
		this.sourcePhoneNo = sourcePhoneNo;
	}

	public String getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public String getIsCommission() {
		return isCommission;
	}

	public void setIsCommission(String isCommission) {
		this.isCommission = isCommission;
	}

}
