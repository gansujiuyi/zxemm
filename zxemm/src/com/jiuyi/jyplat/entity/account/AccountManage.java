package com.jiuyi.jyplat.entity.account;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "t_accountmanage")
public class AccountManage implements Serializable {
private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	private String ezexaccno;//资金监管账号
	private String ezexaccname;//资金监管账号名称
	private String ezexinterestacctno;//自己监管利息账号
	private String ezexinterestacctname;//自己监管利息账号名称
	private String institutionno;//操作员编号
	private String institutionname;//操作员名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEzexaccno() {
		return ezexaccno;
	}
	public void setEzexaccno(String ezexaccno) {
		this.ezexaccno = ezexaccno;
	}
	public String getEzexaccname() {
		return ezexaccname;
	}
	public void setEzexaccname(String ezexaccname) {
		this.ezexaccname = ezexaccname;
	}
	public String getEzexinterestacctno() {
		return ezexinterestacctno;
	}
	public void setEzexinterestacctno(String ezexinterestacctno) {
		this.ezexinterestacctno = ezexinterestacctno;
	}
	public String getEzexinterestacctname() {
		return ezexinterestacctname;
	}
	public void setEzexinterestacctname(String ezexinterestacctname) {
		this.ezexinterestacctname = ezexinterestacctname;
	}
	public String getInstitutionname() {
		return institutionname;
	}
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}
	public String getInstitutionno() {
		return institutionno;
	}
	public void setInstitutionno(String institutionno) {
		this.institutionno = institutionno;
	}

}
