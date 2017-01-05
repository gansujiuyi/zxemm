package com.jiuyi.jyplat.util;

public class OperateResult {

	private boolean success;

	private String message;

	private Object object;

	public OperateResult() {
		super();
	}

	public OperateResult(boolean success) {
		super();
		this.success = success;
	}

	public OperateResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "success : " + success + ", message : " + message + ", object : " + object;
	}
}
