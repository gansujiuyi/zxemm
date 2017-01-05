package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 出账信息实体
 * @author wsf
 *
 */
@Entity(name = "t_outAccountRecord")
public class OutAccountRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;  //主键id
	
	private String houseNo; //地幢号
	
	private String amt;  //划款金额
	
	private String date; //划账日期
	
	private String blockNo; //小区编号
	
	private String enterpriseNo; //企业编号
	
	private String outCardNo;  //出账卡号
	
	private String outCardName;  //出账账户名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public String getOutCardNo() {
		return outCardNo;
	}

	public void setOutCardNo(String outCardNo) {
		this.outCardNo = outCardNo;
	}

	public String getOutCardName() {
		return outCardName;
	}

	public void setOutCardName(String outCardName) {
		this.outCardName = outCardName;
	}
	
}
