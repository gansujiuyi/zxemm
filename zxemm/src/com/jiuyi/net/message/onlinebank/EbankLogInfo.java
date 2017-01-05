package com.jiuyi.net.message.onlinebank;

import java.io.Serializable;

public class EbankLogInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String traStr;//获取到的流水信息。
	
	private String traDate;//交易日期
	private String traTime;//交易时间
	private String signTime;//记账日期
	private String traChannel;//交易渠道
	private String moneyType;//币种
	private String cashSign;//钞汇标志
	private String cashTraType;//现转标志 1：现金 2：转帐
	private String loanSign;//借贷标志
	private String traMoney;//交易金额
	private String balance;//余额
	private String idType;//凭证种类
	private String idNo;//凭证号码
	private String note;//摘要
	private String cardNo;//账号
	private String oppNo;//对手账号
	private String perTraNo;//原交易流水号
	private String perTraSubNo;//子交易流水号
	private String oppName;//对手户名

	public String getTraStr() {
		return traStr;
	}

	public void setTraStr(String traStr) {
		this.traStr = traStr;
	}

	public String getTraDate() {
		return traDate;
	}

	public void setTraDate(String traDate) {
		this.traDate = traDate;
	}

	public String getTraTime() {
		return traTime;
	}

	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getTraChannel() {
		return traChannel;
	}

	public void setTraChannel(String traChannel) {
		this.traChannel = traChannel;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getCashSign() {
		return cashSign;
	}

	public void setCashSign(String cashSign) {
		this.cashSign = cashSign;
	}

	public String getCashTraType() {
		return cashTraType;
	}

	public void setCashTraType(String cashTraType) {
		this.cashTraType = cashTraType;
	}

	public String getLoanSign() {
		return loanSign;
	}

	public void setLoanSign(String loanSign) {
		this.loanSign = loanSign;
	}

	public String getTraMoney() {
		return traMoney;
	}

	public void setTraMoney(String traMoney) {
		this.traMoney = traMoney;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOppNo() {
		return oppNo;
	}

	public void setOppNo(String oppNo) {
		this.oppNo = oppNo;
	}

	public String getPerTraNo() {
		return perTraNo;
	}

	public void setPerTraNo(String perTraNo) {
		this.perTraNo = perTraNo;
	}

	public String getPerTraSubNo() {
		return perTraSubNo;
	}

	public void setPerTraSubNo(String perTraSubNo) {
		this.perTraSubNo = perTraSubNo;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

}
