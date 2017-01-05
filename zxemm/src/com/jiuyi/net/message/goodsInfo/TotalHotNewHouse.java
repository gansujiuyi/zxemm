package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.net.message.goodsInfo.HotNewEhouse;

public class TotalHotNewHouse implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private List<HotNewEhouse> NewEhouses;//房源列表
	
	/** 总记录数 */
	private Integer totalrecs;

	public List<HotNewEhouse> getNewEhouses() {
		return NewEhouses;
	}

	public void setNewEhouses(List<HotNewEhouse> newEhouses) {
		NewEhouses = newEhouses;
	}

	public Integer getTotalrecs() {
		return totalrecs;
	}

	public void setTotalrecs(Integer totalrecs) {
		this.totalrecs = totalrecs;
	}
	
	

}
