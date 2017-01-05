package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

public class RoleFunction implements Serializable {

	private String roleId;

	private String functionNo;

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
