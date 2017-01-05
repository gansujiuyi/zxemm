package com.jiuyi.jyplat.authority;

import java.lang.reflect.Method;

public class ParseAuthName {

	public static String[] parseAuthentication(Class<?> clazz, String methodName, Class<?>... parameterTypes)
			throws NoSuchMethodException {

		//根据方法名，取得方法，如果有则返回
		Method method = clazz.getMethod(methodName, parameterTypes);

		if (null != method) {
			AuthName authName = method.getAnnotation(AuthName.class);

			if (null != authName) {
				return authName.value();
			}

		}
		return null;

	}

}
