package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetEntInfoReqMsg implements Serializable{

	private static final long serialVersionUID = 1L;

	//private Head msghead;//报文头
	
	private String BUILDING;//地幢号

	public String getBUILDING() {
		return BUILDING;
	}

	public void setBUILDING(String bUILDING) {
		BUILDING = bUILDING;
	}

}
