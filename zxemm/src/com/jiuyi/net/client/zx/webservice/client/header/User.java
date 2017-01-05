/**
 * 
 */
package com.jiuyi.net.client.zx.webservice.client.header;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 * 头信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "passwd"})
public class User implements Serializable{

	private static final long serialVersionUID = 8547468309173126920L;
	
	@XmlElement(name="name",required=true)
	private String name;
	@XmlElement(name="passwd",required=true)
	private String passwd;
	
	public User() {
		super();
	}

	public User(String name, String passwd) {
		super();
		this.name = name;
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", passwd=" + passwd + "]";
	}
	
	
	
	
}
