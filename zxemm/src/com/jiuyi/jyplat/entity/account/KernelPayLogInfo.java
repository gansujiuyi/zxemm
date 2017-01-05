package com.jiuyi.jyplat.entity.account;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "t_outaccount")
public class KernelPayLogInfo implements Serializable {
private static final long serialVersionUID = 1L;

@Id
@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	private String traStr;//获取到的流水信息。
	private String traChannel;//渠道
	private String acountNo;//账号
	private String traTime;//交易／结转日期
	private String loanSign;//借贷标识 D - 借方C - 贷方
	private String traType;//交易类型 0-正常1-蓝字2-红字
	private String oppNo;//对方帐号
	private String traMoney;//交易金额
	private String balance;//当前余额
	public String getTraStr() {
		return traStr;
	}
	public void setTraStr(String traStr) {
		this.traStr = traStr;
	}
	public String getTraChannel() {
		return traChannel;
	}
	public void setTraChannel(String traChannel) {
		this.traChannel = traChannel;
	}
	public String getAcountNo() {
		return acountNo;
	}
	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}
	public String getTraTime() {
		return traTime;
	}
	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}
	public String getLoanSign() {
		return loanSign;
	}
	public void setLoanSign(String loanSign) {
		this.loanSign = loanSign;
	}
	public String getTraType() {
		return traType;
	}
	public void setTraType(String traType) {
		this.traType = traType;
	}
	public String getOppNo() {
		return oppNo;
	}
	public void setOppNo(String oppNo) {
		this.oppNo = oppNo;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
