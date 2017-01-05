package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品房楼幢信息
 * @author yhm
 *
 */

@Entity(name = "t_buildingInfo")
public class BuildingInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	private String buildingId;//地幢号
	private String projetName;//项目名称
	private String companyName;//企业名称
	private String projectAdd;//项目地址
	private String coveredArea;//建筑面积
	private String companyId;//企业号
	private String blockNo;//小区编号  对应  GetEntInfoRsp类 basecode项目编号  
	private String regulateAccount;//监管账号
	private String regulateAccName;//监管账号名称
	private String status;//账户状态1001:已开启，1002：未开启 1003:等待企业信息（待审核）
	private String regulateStatus;//监管状态0001：未监管，0002：已监管，0003：撤销监管
	private String str2;//备用   开盘时间
	private String str3;//备用
	private String operNo;//操作员编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getProjetName() {
		return projetName;
	}
	public void setProjetName(String projetName) {
		this.projetName = projetName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProjectAdd() {
		return projectAdd;
	}
	public void setProjectAdd(String projectAdd) {
		this.projectAdd = projectAdd;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}
	public String getRegulateAccount() {
		return regulateAccount;
	}
	public void setRegulateAccount(String regulateAccount) {
		this.regulateAccount = regulateAccount;
	}
	public String getRegulateAccName() {
		return regulateAccName;
	}
	public void setRegulateAccName(String regulateAccName) {
		this.regulateAccName = regulateAccName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegulateStatus() {
		return regulateStatus;
	}
	public void setRegulateStatus(String regulateStatus) {
		this.regulateStatus = regulateStatus;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	public String getOperNo() {
		return operNo;
	}
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}
	
	

}
