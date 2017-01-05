package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

public class DelHouseRsp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String retcode;
	private String retshow;
	private Integer flag;
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetshow() {
		return retshow;
	}
	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
	

}
