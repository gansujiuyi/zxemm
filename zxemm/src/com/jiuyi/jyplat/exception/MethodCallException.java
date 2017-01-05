/**
 * <p>Title:             MethodCallException.java
 * <p>Description:       方法调用例外
 * <p>Copyright:         Copyright (C), 2002-2003, UXUN Tech. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0
 * @see		             com.jiuyi.util.exception.MethodCallException
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 05/14/2003	          xuf	                             	         v1.0 
 */

package com.jiuyi.jyplat.exception;

public class MethodCallException extends Exception {
	public MethodCallException() {
		super();
	}

	public MethodCallException(Exception e) {
		super(e);
	}

	public MethodCallException(String desc) {
		super(desc);
	}
}