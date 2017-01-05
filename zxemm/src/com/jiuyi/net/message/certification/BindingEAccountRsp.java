package com.jiuyi.net.message.certification;

public class BindingEAccountRsp {
	
	/**
	 * 电子账号
	 */
	private String webAcNo;
	
	/**
	 * 机构名称
	 */
	private String deptName;
	
	/**
	 * 机构号
	 */
	private String deptId;

	public String getWebAcNo() {
		return webAcNo;
	}

	public void setWebAcNo(String webAcNo) {
		this.webAcNo = webAcNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
