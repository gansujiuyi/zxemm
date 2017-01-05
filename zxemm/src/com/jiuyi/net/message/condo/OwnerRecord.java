/**
 * Copyright @2016 中国电信甘肃万维公司 All rights reserved.
 * 中国电信甘肃万维公司 专有/保密源代码,未经许可禁止任何人通过任何* 渠道使用、修改源代码.
 * 日期 2016-3-22 下午03:38:44
 */
package com.jiuyi.net.message.condo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @company:    中国电信甘肃万维公司 
 *
 * @project:    lzrs-ws
 * @class:      com.gsww.ws.soap.response.InAccountRecord
 * @version:    V1.0
 * @author:     魏   荣
 * @date:       2016-3-22 下午03:38:44	
 * @description
 * <p>
 * 		购买人信息实体
 * </p>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OwnerRecord",
		propOrder = { "name", "cardNo", "phone" })
public class OwnerRecord{

	private String name;		
	private String cardNo;		
	private String phone;
	
	public OwnerRecord() {
		super();
	}

	public OwnerRecord(String name, String cardNo, String phone) {
		super();
		this.name = name;
		this.cardNo = cardNo;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}
