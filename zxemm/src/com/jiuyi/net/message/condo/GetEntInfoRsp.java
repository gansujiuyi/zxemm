package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElementWrapper;

public class GetEntInfoRsp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;//响应码   0000：请求成功    否则错误
	
	private String building;//地幢号
	
	private String projectname;//项目名称
	
	private String projectaddress;//项目地址
	
	private String companyname;//企业名称
	
	private String constructarea;//建筑面积
	
	private String pbicompcode;//项目编号
	
	private String pbicode;//企业编码
	
	
	private String message;//消息提示

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectaddress() {
		return projectaddress;
	}

	public void setProjectaddress(String projectaddress) {
		this.projectaddress = projectaddress;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getConstructarea() {
		return constructarea;
	}

	public void setConstructarea(String constructarea) {
		this.constructarea = constructarea;
	}

	public String getPbicompcode() {
		return pbicompcode;
	}

	public void setPbicompcode(String pbicompcode) {
		this.pbicompcode = pbicompcode;
	}

	public String getPbicode() {
		return pbicode;
	}

	public void setPbicode(String pbicode) {
		this.pbicode = pbicode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
