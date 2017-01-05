package com.jiuyi.jyplat.authority;

/**
 * 业务异常
 * @author Administrator
 *
 */
public class BussException extends Exception {
	
	private String message;

	public BussException() {
		super();
	}

	public BussException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
