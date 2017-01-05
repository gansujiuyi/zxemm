package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;

public class qurGoodsInfoReq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String goodsId; // 参数1

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}
