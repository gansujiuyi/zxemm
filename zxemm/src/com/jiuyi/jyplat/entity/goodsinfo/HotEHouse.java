package com.jiuyi.jyplat.entity.goodsinfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Id;


/**
 * 用于保存热门房源的信息。
 * @author sunzb
 */
@Entity(name = "t_hotehouse")
public class HotEHouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String goodsId;//商品id
	private Date savetime;//添加热门房源时间
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Date getSavetime() {
		return savetime;
	}
	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}	
	
}
