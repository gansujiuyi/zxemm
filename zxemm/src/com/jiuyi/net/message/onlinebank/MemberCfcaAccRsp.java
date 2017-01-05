package com.jiuyi.net.message.onlinebank;
/**
 * 检查它行卡信息的应答报文体
 * @author lix
 * 2014-11-27
 */
public class MemberCfcaAccRsp {

	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	/** 交易状态 10=绑定处理中 20=绑定失败 30=绑定成功*/
	private String status;
	/** 发卡机构代码（银联返回，不是Request中的BankID）*/
	private String issinscode;
	/** 支付卡类型（银联返回，不是Request中的CardType） 00=未知      01=借记账户    02=贷记账户    03=准贷记账户    04=借贷合一账户    05=预付费账户     06=半开放预付费账户*/
	private String paycardtype;
	/** 银行处理时间*/
	private String banktxtime;

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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setIssinscode(String issinscode) {
		this.issinscode = issinscode;
	}

	public String getIssinscode() {
		return issinscode;
	}

	public void setPaycardtype(String paycardtype) {
		this.paycardtype = paycardtype;
	}

	public String getPaycardtype() {
		return paycardtype;
	}

	public void setBanktxtime(String banktxtime) {
		this.banktxtime = banktxtime;
	}

	public String getBanktxtime() {
		return banktxtime;
	}
	
}
