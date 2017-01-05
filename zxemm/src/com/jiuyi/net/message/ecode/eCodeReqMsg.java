/**
 * @{#} eCodeReqMsg.java Create on 2013年10月05日 
 * 发送手机验证码--请求类的消息体
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

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class eCodeReqMsg implements Serializable {

	private static final long serialVersionUID = 3528298854736920361L;

	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private eCodeReq msgreq;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public eCodeReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(eCodeReq msgreq) {
		this.msgreq = msgreq;
	}

}
