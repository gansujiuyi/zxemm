package com.jiuyi.net.message.onlinebank;
/**
 * 查询它行卡信息的请求报文体
 * @author lix
 * 2014-12-23 
 */
public class MemberCfcaAccReq {

	private Integer memberno;    //发起请求的会员号
	private String name;//银行卡用户名
	private String idtype;  //证件类型    开户证件类型 01=身份证
	private String idno;	//证件号码
	private String bankid;  //银行id 参考《银行编码表》
	private String cardtype; //银行卡类型  0=个人借记 1=个人贷记
	private String bankno;	//银行卡号
	private String phone;		//手机号
	private String validdate;   //信用卡有效期，格式YYMM(绑定信用卡该项必填）
	private String cvn2;        //信用卡背面的末3位数字 (绑定信用卡该项必填)
	public Integer getMemberno() {
		return memberno;
	}
	public void setMemberno(Integer memberno) {
		this.memberno = memberno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getValiddate() {
		return validdate;
	}
	public void setValiddate(String validdate) {
		this.validdate = validdate;
	}
	public String getCvn2() {
		return cvn2;
	}
	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}
	
	
}
