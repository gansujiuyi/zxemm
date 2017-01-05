package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SetHandleInfoResultReqMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Head msghead;//报文头
	
	private String instruction;//指令类型  1：开户操作   2：划款出账操作  3： 购房入账通知  4： 账户变更通知
	
	private String building;//地幢号
	
	private String bsinum;//合同号
	
	private String ifsuccess;//1成功；0未成功
	
	private String errorinfo;//错误提示

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBsinum() {
		return bsinum;
	}

	public void setBsinum(String bsinum) {
		this.bsinum = bsinum;
	}

	public String getIfsuccess() {
		return ifsuccess;
	}

	public void setIfsuccess(String ifsuccess) {
		this.ifsuccess = ifsuccess;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}
	
}
