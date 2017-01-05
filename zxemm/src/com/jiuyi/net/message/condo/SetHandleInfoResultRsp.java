package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class SetHandleInfoResultRsp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;//响应码   0000：请求成功    否则错误
	
	private String message;//消息提示

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
