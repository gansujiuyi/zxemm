package com.jiuyi.net.message.condo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import com.jiuyi.net.message.Head;
@XmlRootElement
public class QurRecordByHouseNoReqMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Head msghead;// 报文头

	private QurRecordByHouseNoReq msgreq;// 报文体

	public Head getMsghead() {
		return msghead;
	}

	public void setMsghead(Head msghead) {
		this.msghead = msghead;
	}

	public QurRecordByHouseNoReq getMsgreq() {
		return msgreq;
	}

	public void setMsgreq(QurRecordByHouseNoReq msgreq) {
		this.msgreq = msgreq;
	}

}
