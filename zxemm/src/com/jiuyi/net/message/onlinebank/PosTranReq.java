package com.jiuyi.net.message.onlinebank;


/**
 * POS刷卡交易
 * @author Administrator
 *
 */
public class PosTranReq {
	private String Mid;    //商户号  通过配置文件获取，请勿传入。(2015-04-20修改，商户号要动态传入)
	private String Devid;  //终端编号
	private String CardNo; //卡号
	private String DevStan; //流水号
	private String TranDate; //交易日期
	private String TranAmt;  //交易金额
	
	public String getMid() {
		return Mid;
	}
	public void setMid(String mid) {
		Mid = mid;
	}
	public String getDevid() {
		return Devid;
	}
	public void setDevid(String devid) {
		Devid = devid;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getDevStan() {
		return DevStan;
	}
	public void setDevStan(String devStan) {
		DevStan = devStan;
	}
	public String getTranDate() {
		return TranDate;
	}
	public void setTranDate(String tranDate) {
		TranDate = tranDate;
	}
	public String getTranAmt() {
		return TranAmt;
	}
	public void setTranAmt(String tranAmt) {
		TranAmt = tranAmt;
	}

}
