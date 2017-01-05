/**
 * @{#} eCodeRspMsg Create on 2013年10月05日 
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

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;

@XmlRootElement
public class eCodeRspMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5746205964444122903L;
	
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private eCodeRsp msgrsp;

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public eCodeRsp getMsgrsp() {
		return msgrsp;
	}

	public void setMsgrsp(eCodeRsp msgrsp) {
		this.msgrsp = msgrsp;
	}

}
