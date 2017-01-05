/**
 * @{#} eCodeReq.java Create on 2013年10月05日 
 * 发送手机验证码--请求类
 * @author HuangWj
 * @version 1.0
 * Copyright @ 2009 - 2011  
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 2013-10-05	          HuangWj                             	         v1.0 
 */
package com.jiuyi.net.message.ecode;

import java.io.Serializable;

public class eCodeReq implements Serializable {

	private static final long serialVersionUID = 1399338355867661142L;

	/** 手机号码 */
	private String phoneNo;

	/** 用户页面输入的手机验证码 */
	private String phoneCode;

	/** 短信内容 */
	private String noteContent;

	/**
	 * 1:发送
	 * 0:校验
	 */
	private String typeName;
	
	
	private String smsType;
	

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	public String getSmsType()
	{
		return smsType;
	}

	
	public void setSmsType(String smsType)
	{
		this.smsType = smsType;
	}
}
