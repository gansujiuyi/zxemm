package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

public class DelHouseReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String delIds;//房源id
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	
	

}
