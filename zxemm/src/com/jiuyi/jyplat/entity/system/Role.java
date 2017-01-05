package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer roleId;// 角色编号
	private String roleName;// 角色名称
	private String description;// 说明
	private String belongplat; //角色所属平台
	private Set<Operator> operators = new HashSet<Operator>(0);
	private Set<SysFunction> sysFunctions = new HashSet<SysFunction>(0);

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_Role", allocationSize = 1, initialValue = 1)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolefunction", joinColumns = { @JoinColumn(name = "roleid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "functionNo", nullable = false, updatable = false) })
	public Set<SysFunction> getSysFunctions() {
		return sysFunctions;
	}

	public void setSysFunctions(Set<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public String getBelongplat() {
		return belongplat;
	}

	public void setBelongplat(String belongplat) {
		this.belongplat = belongplat;
	}
}
