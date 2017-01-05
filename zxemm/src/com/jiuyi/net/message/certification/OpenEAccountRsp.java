package com.jiuyi.net.message.certification;

public class OpenEAccountRsp{
	
	/**
	 * 用户开通电子户后，返回的唯一标示
	 */
	private String UUID;
	
	/**
	 * 返回是否开通电子户的状态
	 */
	private String eAState;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String geteAState() {
		return eAState;
	}

	public void seteAState(String eAState) {
		this.eAState = eAState;
	}
	
	
	
}
