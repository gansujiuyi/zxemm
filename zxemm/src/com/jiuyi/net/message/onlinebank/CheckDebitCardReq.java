package com.jiuyi.net.message.onlinebank;

import java.io.Serializable;
/**
 * 查询快捷卡信息的请求报文体
 * @author lix
 * 2014-11-27
 */
@SuppressWarnings("serial")
public class CheckDebitCardReq implements Serializable{

	private String name;//银行卡用户名
	private String idtype;  //证件类型
	private String idno;	//证件号码
	private String accountno;	//银行卡号
	private String phone;		//手机号
	private Integer memberno; //用户会员号
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
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getMemberno() {
		return memberno;
	}
	public void setMemberno(Integer memberno) {
		this.memberno = memberno;
	}
	
}
