package com.jiuyi.jyplat.entity.goodsinfo;

import java.io.Serializable;
import java.util.List;

public class TotalEhouse implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private List<EHouse> ehouses;//房源列表
	
	/** 总记录数 */
	private Integer totalrecs;

	public List<EHouse> getEhouses() {
		return ehouses;
	}

	public void setEhouses(List<EHouse> ehouses) {
		this.ehouses = ehouses;
	}

	public Integer getTotalrecs() {
		return totalrecs;
	}

	public void setTotalrecs(Integer totalrecs) {
		this.totalrecs = totalrecs;
	}
	
	
	
	

}
