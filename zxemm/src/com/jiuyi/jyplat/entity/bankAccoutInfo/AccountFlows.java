package com.jiuyi.jyplat.entity.bankAccoutInfo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "t_accountflows")
public class AccountFlows implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905499416991754572L;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	private String id;
	private String custId;// 客户ID

	private String custName;// 客户名称

	private String sysAccount;// 系统账户

	private String custAccount;// 客户账户

	private String openAccountDate;// 开户日期

	private String transactionCode;// 交易码

	private String debitCreditType;// 借贷类型 0001--借 0002---贷

	private String cashTransitionType;// 现转类型

	private String transactionDate;// 交易日期

	private String transactionTime;// 交易日期

	private String transactionAmount;// 交易金额

	private String accountBalance;// 账户余额

	private String transactionSeqNum;// 交易流水号

	private String oppositeCustAccount;// 交易对手账户

	private String oppositeCustAccountName;// 交易对手账户名称

	private String oppositeBankName;// 交易对手账户开户行名称

	private String openAccountOrgId;// 交易对手账户机构名称

	private String memo;// 摘要

	private String status;// 流水状态

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getOpenAccountDate() {
		return openAccountDate;
	}

	public void setOpenAccountDate(String openAccountDate) {
		this.openAccountDate = openAccountDate;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getDebitCreditType() {
		return debitCreditType;
	}

	public void setDebitCreditType(String debitCreditType) {
		this.debitCreditType = debitCreditType;
	}

	public String getCashTransitionType() {
		return cashTransitionType;
	}

	public void setCashTransitionType(String cashTransitionType) {
		this.cashTransitionType = cashTransitionType;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getTransactionSeqNum() {
		return transactionSeqNum;
	}

	public void setTransactionSeqNum(String transactionSeqNum) {
		this.transactionSeqNum = transactionSeqNum;
	}

	public String getOppositeCustAccount() {
		return oppositeCustAccount;
	}

	public void setOppositeCustAccount(String oppositeCustAccount) {
		this.oppositeCustAccount = oppositeCustAccount;
	}

	public String getOppositeCustAccountName() {
		return oppositeCustAccountName;
	}

	public void setOppositeCustAccountName(String oppositeCustAccountName) {
		this.oppositeCustAccountName = oppositeCustAccountName;
	}

	public String getOppositeBankName() {
		return oppositeBankName;
	}

	public void setOppositeBankName(String oppositeBankName) {
		this.oppositeBankName = oppositeBankName;
	}

	public String getOpenAccountOrgId() {
		return openAccountOrgId;
	}

	public void setOpenAccountOrgId(String openAccountOrgId) {
		this.openAccountOrgId = openAccountOrgId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
