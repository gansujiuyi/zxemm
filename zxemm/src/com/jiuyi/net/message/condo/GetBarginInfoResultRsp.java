package com.jiuyi.net.message.condo;

import java.io.Serializable;
import java.util.List;

public class GetBarginInfoResultRsp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String building_code;//地幢号
	
	private String bsinum;//合同编号
	
	private String area;//建筑面积
	
	private String psbsalecoaddr;//房屋坐落
	
	private String cellnum;//室号
	
	private String pbiname;//项目名称
	
	private String rcname;//企业名称
	
	private String pbicode;//项目编号
	
	private String compcode;//企业编号
	
	private String psbstat;//合同状态
	
	private List<OwnerRecord> ownerRecords;//购买人信息
	
	private String code;//响应码   0000：请求成功    否则错误
	
	private String message;//消息提示

	public String getBuilding_code() {
		return building_code;
	}

	public void setBuilding_code(String buildingCode) {
		building_code = buildingCode;
	}

	public String getBsinum() {
		return bsinum;
	}

	public void setBsinum(String bsinum) {
		this.bsinum = bsinum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPsbsalecoaddr() {
		return psbsalecoaddr;
	}

	public void setPsbsalecoaddr(String psbsalecoaddr) {
		this.psbsalecoaddr = psbsalecoaddr;
	}

	public String getCellnum() {
		return cellnum;
	}

	public void setCellnum(String cellnum) {
		this.cellnum = cellnum;
	}

	public String getPbiname() {
		return pbiname;
	}

	public void setPbiname(String pbiname) {
		this.pbiname = pbiname;
	}

	public String getRcname() {
		return rcname;
	}

	public void setRcname(String rcname) {
		this.rcname = rcname;
	}

	public String getPbicode() {
		return pbicode;
	}

	public void setPbicode(String pbicode) {
		this.pbicode = pbicode;
	}

	public String getCompcode() {
		return compcode;
	}

	public void setCompcode(String compcode) {
		this.compcode = compcode;
	}

	public String getPsbstat() {
		return psbstat;
	}

	public void setPsbstat(String psbstat) {
		this.psbstat = psbstat;
	}

	public List<OwnerRecord> getOwnerRecords() {
		return ownerRecords;
	}

	public void setOwnerRecords(List<OwnerRecord> ownerRecords) {
		this.ownerRecords = ownerRecords;
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
