package com.jiuyi.net.message.condo;

import java.io.Serializable;
/**
 * 查询整幢楼下各户的缴存记录
 * @author wsf
 *
 */
public class QurAmountByBuildingNoReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String houseNo; //地幢号
	
	private String bankCode;//银行号
	
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
