package com.jiuyi.net.message.goodsInfo;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.goodsinfo.EHouse;

public class QurHotOrCommendEHouseReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int pageSize;//每页的条数
	
	private int pageNow;//当前页数
	
	private String cityId;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	
	
}
