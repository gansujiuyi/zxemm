package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "operator")
public class Operator implements Serializable, Cloneable {

	private static final long serialVersionUID = -8169887333395411034L;
	private String operNo;// 操作员编号
	private String operName;// 操作员姓名
	private String password;// 登录密码
	private String operMobile;// 手机号码
	private String createdDate;// 创建日期
	private String modifiedDate;// 最后修改日期
	private String authDate;// 审核日期
	private String operStatus;// 操作员状态，1可用2待审核3锁定4停用
	private String authStatus;// 是否通过审核，1是0否
	private Role role;// 关联角色
	private Institution inst;// 关联角色
	private String department;// 所属部门
	private String departmentName;// 所属部门
	private String lastLoginIp;// 最后登录IP
	private String lastLoginTime;// 最后登录时间
	private Integer loginTimes;// 总登录次数
	private Integer errorLoginTimes;// 累计错误登录次数
	
	private Integer wxBranchId;//网点ID
	private String wxBranchName;//网点名称

	private String operLvl; //操作员级别 1-行员级;2-机构级;3-管理员级;4-全辖级 	add by lzb on 2012/08/06

	public Operator clone() {
		Operator o = null;
		try {
			o = (Operator) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return o;
	}

	@Id
	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperMobile() {
		return operMobile;
	}

	public void setOperMobile(String operMobile) {
		this.operMobile = operMobile;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getAuthDate() {
		return authDate;
	}

	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getErrorLoginTimes() {
		return errorLoginTimes;
	}

	public void setErrorLoginTimes(Integer errorLoginTimes) {
		this.errorLoginTimes = errorLoginTimes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institutionId")
	public Institution getInst() {
		return inst;
	}

	public void setInst(Institution inst) {
		this.inst = inst;
	}

	@Transient
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getOperLvl() {
		return operLvl;
	}

	public void setOperLvl(String operLvl) {
		this.operLvl = operLvl;
	}

	public Integer getWxBranchId() {
		return wxBranchId;
	}

	public void setWxBranchId(Integer wxBranchId) {
		this.wxBranchId = wxBranchId;
	}

	public String getWxBranchName() {
		return wxBranchName;
	}

	public void setWxBranchName(String wxBranchName) {
		this.wxBranchName = wxBranchName;
	}

}
