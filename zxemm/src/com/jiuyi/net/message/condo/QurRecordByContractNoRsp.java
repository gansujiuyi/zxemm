package com.jiuyi.net.message.condo;

import java.io.Serializable;
import java.util.List;

import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
public class QurRecordByContractNoRsp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String retcode;// 响应码。说明：0000为正常，否则为错误
	
	private String retshow;// 错误提示。说明Retcode为错误的时候出现错误提示

	private List<RefundPayInfo>  refundPayInfos;//出账信息
	
	private List<CondoPayInfo>   condoPayInfos;//入账信息
	
	private String superviseAmt; //监管金额（已支付金额）

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

	public List<RefundPayInfo> getRefundPayInfos() {
		return refundPayInfos;
	}

	public void setRefundPayInfos(List<RefundPayInfo> refundPayInfos) {
		this.refundPayInfos = refundPayInfos;
	}

	public List<CondoPayInfo> getCondoPayInfos() {
		return condoPayInfos;
	}

	public void setCondoPayInfos(List<CondoPayInfo> condoPayInfos) {
		this.condoPayInfos = condoPayInfos;
	}

	public String getSuperviseAmt() {
		return superviseAmt;
	}

	public void setSuperviseAmt(String superviseAmt) {
		this.superviseAmt = superviseAmt;
	}
}
