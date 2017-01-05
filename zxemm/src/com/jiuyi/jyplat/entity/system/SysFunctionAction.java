package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

//@Entity
//@Table(name="sysFunctionAction")
public class SysFunctionAction implements Serializable {
	private String functionNo; // 功能编号
	private Integer actionId; // action编号

	// @Id

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
}
