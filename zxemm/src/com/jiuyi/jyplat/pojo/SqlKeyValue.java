package com.jiuyi.jyplat.pojo;

public class SqlKeyValue {
	public SqlKeyValue(String key, Class classValue) {
		this.key = key;
		this.classValue = classValue;
	}

	public SqlKeyValue() {

	}

	private String key;
	private Class classValue;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Class getClassValue() {
		return classValue;
	}

	public void setClassValue(Class classValue) {
		this.classValue = classValue;
	}
}
