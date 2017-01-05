package com.jiuyi.jyplat.entity.goodsCollect;

import java.io.Serializable;
import java.util.List;

public class FrontGoodsCollectList implements Serializable{
	
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private List<FrontGoodsCollect> collectList;
	private Integer totalRec;
	public List<FrontGoodsCollect> getCollectList() {
		return collectList;
	}
	public void setCollectList(List<FrontGoodsCollect> collectList) {
		this.collectList = collectList;
	}
	public Integer getTotalRec() {
		return totalRec;
	}
	public void setTotalRec(Integer totalRec) {
		this.totalRec = totalRec;
	}
	

}
