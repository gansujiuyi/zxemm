package com.jiuyi.net.message.condo;

import java.io.Serializable;
/**
 * 房管局查询银行状态接口
 * @author wsf
 *
 */
public class QurBankNoticeReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String noticeVariety;//指令类型  1：划款出账信息同步   2：购房资金登记入账通知
	
	private String houseNo;//出账经销商地撞号  

	private String buyerName;//入账资金人姓名   购房资金登记入账通知接口使用
	
	private String buyerIdNo;//入账资金人身份证号
	
	private double amt;//入账金额
	
	private String houseAddr;//房屋位置

	public String getNoticeVariety() {
		return noticeVariety;
	}

	public void setNoticeVariety(String noticeVariety) {
		this.noticeVariety = noticeVariety;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerIdNo() {
		return buyerIdNo;
	}

	public void setBuyerIdNo(String buyerIdNo) {
		this.buyerIdNo = buyerIdNo;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	
}
