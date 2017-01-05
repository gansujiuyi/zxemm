package com.jiuyi.net.message.activity;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.base.Syscontent;

public class actlist implements Serializable {

	private static final long serialVersionUID = -6824543588601706452L;
	/** 活动明细信息列表 */
	private List<Syscontent> ainfo;

	public List<Syscontent> getAinfo() {
		return ainfo;
	}

	public void setAinfo(List<Syscontent> ainfo) {
		this.ainfo = ainfo;
	}


}
