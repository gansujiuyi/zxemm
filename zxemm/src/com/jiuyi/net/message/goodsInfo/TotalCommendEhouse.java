package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;
import java.util.List;


public class TotalCommendEhouse implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CommendEHouse> ehouses;//房源列表
	
	/** 总记录数 */
	private Integer totalrecs;

	public List<CommendEHouse> getEhouses() {
		return ehouses;
	}

	public void setEhouses(List<CommendEHouse> ehouses) {
		this.ehouses = ehouses;
	}

	public Integer getTotalrecs() {
		return totalrecs;
	}

	public void setTotalrecs(Integer totalrecs) {
		this.totalrecs = totalrecs;
	}
	
	

}
