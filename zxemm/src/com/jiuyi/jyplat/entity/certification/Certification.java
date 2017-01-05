package com.jiuyi.jyplat.entity.certification;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * <P>实名认证过的用户信息</P>
 * @author sunzb
 */
@Entity(name = "t_certification")
public class Certification  implements Serializable{

	private static final long serialVersionUID = -3159955315739881469L;
	
	@Id
	private Long id;
	/**
	 * 用户编号
	 */
	private Integer memberNo;
	/**
	 * 用户姓名
	 */
	private String	name;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 银行编码
	 */
	private String bankId;
	/**
	 * 银行卡号
	 */
	private String accountNo;
	
	/**
	 * 电话号码
	 */
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
