package com.jiuyi.net.message.genservicepara;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
/**
 * @author zhangb
 *  生成请求三维易付数据请求类
 */
@XmlRootElement
public class GenGoEpayReqMsg implements Serializable {
	private static final long serialVersionUID = 3528298854736920361L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private GenGoEpayReq msgreq;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public GenGoEpayReq getMsgreq() {
		return msgreq;
	}
	public void setMsgreq(GenGoEpayReq msgreq) {
		this.msgreq = msgreq;
	}
}
