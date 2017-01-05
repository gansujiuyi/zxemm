/**
 * <p>Title:             MethodCall.java
 * <p>Description:       参数封装类
 * <p>Copyright:         Copyright (C), 2002-2003, UXUN. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0
 * @see		             com.jiuyi.util.MethodCall
 */

package com.jiuyi.jyplat.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
* 该类实现方法呼叫
*/
public class MethodCall {
	public static Object execute(Object c, String m, ArgumentHolder a) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		Method meth = c.getClass().getMethod(m, a.getArgumentClasses());

		//通过类Method对象的invoke方法动态调用希望调用的方法
		return (meth.invoke(c, a.getArguments()));
	}
}
