package com.jiuyi.net.message.onlinebank;

public class CfcaSingleTransferRsp {

	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	/***机构编号**/
	private String InstitutionID;
	/***转账交易流水号**/
	private String TxSN;
	/***状态： 20=正在处理 30=转账成功 40=转账失败**/
	private String Status;
	/***响应代码**/
	private String BankResponseCode;
	/***响应消息**/
	private String BankResponseMessage;
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}
	public String getRetshow() {
		return retshow;
	}
	public void setInstitutionID(String institutionID) {
		InstitutionID = institutionID;
	}
	public String getInstitutionID() {
		return InstitutionID;
	}
	public void setTxSN(String txSN) {
		TxSN = txSN;
	}
	public String getTxSN() {
		return TxSN;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getStatus() {
		return Status;
	}
	public void setBankResponseCode(String bankResponseCode) {
		BankResponseCode = bankResponseCode;
	}
	public String getBankResponseCode() {
		return BankResponseCode;
	}
	public void setBankResponseMessage(String bankResponseMessage) {
		BankResponseMessage = bankResponseMessage;
	}
	public String getBankResponseMessage() {
		return BankResponseMessage;
	}
}
