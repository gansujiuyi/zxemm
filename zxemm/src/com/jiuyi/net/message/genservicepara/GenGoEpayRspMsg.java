package com.jiuyi.net.message.genservicepara;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiuyi.net.message.Head;
/**
 * @author zhangb
 *  生成请求三维易付数据响应类
 */
@XmlRootElement
public class GenGoEpayRspMsg implements Serializable {
	private static final long serialVersionUID = -5746205964444122903L;
	/** 报文头 */
	private Head msghead;
	/** 报文体 */
	private GenGoEpayRsp msgrsp;
	public Head getMsghead() {
		return msghead;
	}
	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}
	public GenGoEpayRsp getMsgrsp() {
		return msgrsp;
	}
	public void setMsgrsp(GenGoEpayRsp msgrsp) {
		this.msgrsp = msgrsp;
	}
}
