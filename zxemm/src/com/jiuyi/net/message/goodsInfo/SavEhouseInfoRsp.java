package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

public class SavEhouseInfoRsp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String retcode;// 响应码。说明：0000为正常，否则为错误
	private String retshow;// 错误提示。说明Retcode为错误的时候出现错误提示
	private String goodsId;
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
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	

}
