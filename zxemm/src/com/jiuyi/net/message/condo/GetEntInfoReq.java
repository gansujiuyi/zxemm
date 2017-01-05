package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class GetEntInfoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String buiding;//地幢号

	public String getBuiding() {
		return buiding;
	}

	public void setBuiding(String buiding) {
		this.buiding = buiding;
	}
	
	
}
