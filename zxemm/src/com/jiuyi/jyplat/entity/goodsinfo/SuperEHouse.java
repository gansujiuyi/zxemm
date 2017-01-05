package com.jiuyi.jyplat.entity.goodsinfo;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.Id;


/**
 * 用于保存精品推荐的信息。
 * @author wushitao
 */
@Entity(name = "t_superehouse")
public class SuperEHouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String goodsId;//商品id
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}	
}
