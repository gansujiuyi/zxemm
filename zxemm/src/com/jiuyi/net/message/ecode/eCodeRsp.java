/**
 * @{#} eCodeRsp.java Create on 2013年10月05日 
 * 发送手机验证码--应答类的消息体
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class eCodeRsp implements Serializable {

	private static final long serialVersionUID = -8754511449354397960L;

	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	
	private String phoneNo;
	
	private String cardNo;
	
	private String phoneCode;
	

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetshow() {
		return retshow;
	}

	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	
	
}
