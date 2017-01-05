package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class QurBankNoticeRsp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String retcode;// 响应码。说明：0000为正常，否则为错误
	
	private String retshow;// 错误提示。说明Retcode为错误的时候出现错误提示
	
	private String status;//合同状态  0000 初始状态（待支付）     1111 退款中（合同已撤销）   2222已退款    3333支付完成

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
