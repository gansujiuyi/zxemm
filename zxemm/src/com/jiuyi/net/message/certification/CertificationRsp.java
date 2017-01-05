package com.jiuyi.net.message.certification;

import java.io.Serializable;

public class CertificationRsp implements Serializable{
	
	private static final long serialVersionUID = -8238360958004558889L;
	
	private String retcode;
	private String retshow;
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetshow() {
		return retshow;
	}
	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}
}
