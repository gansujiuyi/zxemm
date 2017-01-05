package com.jiuyi.jyplat.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class ObjectValueCopy {

	/**
	 * 将source中与target中相同的的且不等于null的属性深复制到target中 如果属性中有自定义的对象，必须序列化，否则会执行浅拷贝
	 * @param source
	 * @param target
	 */
	public static void copyObject(Object source, Object target) {
		if (target == null) {
			target = source;
			return;
		}
		Class sourceClass = source.getClass();
		Class targetClass = target.getClass();
		Field[] sFields = sourceClass.getDeclaredFields();
		for (Field sourceField : sFields) {
			if ("serialVersionUID".equals(sourceField.getName())) {
				continue;
			}
			Field targetField = null;
			try {
				targetField = targetClass.getDeclaredField(sourceField
						.getName());
			} catch (Exception e) {
				continue;
			}
			if (targetField != null) {
				sourceField.setAccessible(true);
				targetField.setAccessible(true);
				if (sourceField.getType().equals(targetField.getType())) {
					Object value = null;
					try {
						value = sourceField.get(source);
						if (value == null) {
							continue;
						}
						copyValue(value, target, targetField);
					} catch (Exception e) {
					}
				}
			}
		}
	}

	private static void copyValue(Object value, Object target, Field targetField)
			throws Exception {
		Class valueClass = value.getClass();
		if (valueClass.isPrimitive()) {
			targetField.set(target, value);
		} else {
			copyObject(value, target, targetField);
		}
	}

	private static void copyObject(Object value, Object target,Field targetField) throws IllegalArgumentException,IllegalAccessException {
		try {
			Object obj = deepCopy(value);
			targetField.set(target, obj);
		} catch (Exception e) {
			targetField.set(target, value);
		}
	}

	/**
	 * 对象的深克隆
	 * @param value 需要克隆的对象
	 * @return 返回克隆的新对象
	 * @throws Exception  当克隆的对象没有序列化的时候就会抛出异常
	 */
	private static Object deepCopy(Object value) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		oos = new ObjectOutputStream(bos);
		oos.writeObject(value);
		bis = new ByteArrayInputStream(bos.toByteArray());
		ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}
