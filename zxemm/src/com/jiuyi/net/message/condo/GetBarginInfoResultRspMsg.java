package com.jiuyi.net.message.condo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetBarginInfoResultRspMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Head msghead;//报文头
/*	private String buildingCode;//地幢号
	private String bsinum;//合同编号
	private String area;//建筑面积
	private String houseaddress;//房屋坐落
	private String cellnum;//室号
	private String unitnum;//单元号
	private String basename;//项目名称
	private String rcname;//企业名称
	private String basecode;//项目编号
	private String psbtolpri;//合同金额
	private String paymentcode;//付款方式
	private String psbpaycont;//首付/贷款
	private String code;//响应码   0000：请求成功    否则错误
	private String message;//消息提示
	private String psbsalecoaddr;*/
	
	//private List<OwnerRecord> ownerRecords;//购买人信息
	//private String compcode;//企业编号
	//private String psbstat;//合同状态
	//private String psbdate;//合同创建时间
	
	private String buildingCode;//监管编号		
	private String bsinum;//合同编号		
	private String area;//建筑面积		
	private String psbsalecoaddr;//	
	private String cellnum;	//室号
	private String pbiname;	//项目名称
	private String rcname;	//企业名称
	private String pbicode;//项目编号	
	private String compcode;//企业编号	
	private String psbstat;	//合同状态
	
	private String psbdate;//合同创建时间
	private String psbtolpri;//合同金额

	private List<OwnerRecord> ownerRecords;//
	
	private String code;//响应码   0000：请求成功    否则错误
	private String message;//消息提示

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
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

	public String getPsbdate() {
		return psbdate;
	}

	public void setPsbdate(String psbdate) {
		this.psbdate = psbdate;
	}

	public String getPsbtolpri() {
		return psbtolpri;
	}

	public void setPsbtolpri(String psbtolpri) {
		this.psbtolpri = psbtolpri;
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
