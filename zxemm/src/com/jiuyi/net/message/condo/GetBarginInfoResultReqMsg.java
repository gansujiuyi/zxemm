package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetBarginInfoResultReqMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Head msghead;//报文头
	
	private String bsinum;//报文体

	public String getBsinum() {
		return bsinum;
	}

	public void setBsinum(String bsinum) {
		this.bsinum = bsinum;
	}

	
}
