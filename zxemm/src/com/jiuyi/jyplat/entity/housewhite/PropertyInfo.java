package com.jiuyi.jyplat.entity.housewhite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 产权人信息实体
 * @author hp
 *
 */

@Entity(name = "T_PROPERTY")
public class PropertyInfo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 private String partid; //主键

	private String houseid;//外键房屋id
	 private String realname;//产权人姓名
	 private String idno;//产权人身份证号
	 private String phoneno;// 电话号码
	 private String isSeller;//是否是产权人是：0010 否：0020
	 public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public PropertyInfo(String partid, String houseid, String realname,
			String idno, String phoneno, String str2, String str3) {
		super();
		this.partid = partid;
		this.houseid = houseid;
		this.realname = realname;
		this.idno = idno;
		this.phoneno = phoneno;
		this.isSeller = isSeller;
		this.str3 = str3;
	}

	 private String str3;//保留字段3
		
	 public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getHouseid() {
		return houseid;
	}
	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getIsSeller() {
		return isSeller;
	}
	public void setIsSeller(String isSeller) {
		this.isSeller = isSeller;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public PropertyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
