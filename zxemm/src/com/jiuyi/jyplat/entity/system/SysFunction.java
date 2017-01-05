package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class SysFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	private String functionNo;// 功能编号
	private String functionName;// 功能名称
	private String parentFunctionNo;// 父功能号，空表示一级功能
	private String url;// 请求路径
	private String description; // 说明
	private String status;// 是否可用，1是 0否
	private Integer orderBy; //排序序号
	private Set<Role> roles = new HashSet<Role>(0); // 关联角色

	@Id
	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentFunctionNo() {
		return parentFunctionNo;
	}

	public void setParentFunctionNo(String parentFunctionNo) {
		this.parentFunctionNo = parentFunctionNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysFunctions")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
}
