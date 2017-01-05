package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class GetBarginInfoResultReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bsinum;//合同号

	public String getBsinum() {
		return bsinum;
	}

	public void setBsinum(String bsinum) {
		this.bsinum = bsinum;
	}
	
}
