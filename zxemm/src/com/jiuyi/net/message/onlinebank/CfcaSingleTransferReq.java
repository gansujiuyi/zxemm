package com.jiuyi.net.message.onlinebank;

public class CfcaSingleTransferReq {

	private String memberno;               //会员号
	/**收款人信息**/
	private Integer AccountType;            //账户类型： 11=个人账户 12=企业账户
	private String BankID;                 //银行编号
	private String BankAccountName;        //银行账户名称
	private String BankAccountNumber;      //银行账户号码
	private String PhoneNumber;            //手机号
	private Long Amount;                 //转账金额，单位：分
	private String Remark;                 //备注
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	public String getMemberno() {
		return memberno;
	}
	public Integer getAccountType() {
		return AccountType;
	}
	public void setAccountType(Integer accountType) {
		AccountType = accountType;
	}
	public String getBankID() {
		return BankID;
	}
	public void setBankID(String bankID) {
		BankID = bankID;
	}
	public String getBankAccountName() {
		return BankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		BankAccountName = bankAccountName;
	}
	public String getBankAccountNumber() {
		return BankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		BankAccountNumber = bankAccountNumber;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public Long getAmount() {
		return Amount;
	}
	public void setAmount(Long amount) {
		Amount = amount;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
}
