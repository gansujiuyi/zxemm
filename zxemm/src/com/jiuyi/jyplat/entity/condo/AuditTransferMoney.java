package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 审核划款实体类
 * @author wsf
 *
 */
@Entity(name="t_auditTransferMoney")
public class AuditTransferMoney implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;	//主键id
	
	private String houseNo; //地幢号
	
	private String amt;  //划款金额
	
	private String blockNo; //小区编号
	
	private String enterpriseNo; //企业编号
	
	private String transferMoneyDate; //划账日期
	
	private String state; //划款状态   1111：初始状态    0000：未支付     0010：支付成功
	
	private String outCardNo;  //出账卡号
	
	private String outBankId;  //出账银行编码
	
	private String outCardName;  //出账账户名称
	
	private String outPhone;//出账人电话
	
	private String outIdNo;//出账人身份证编号
	private String acceptcode;//流水号
	
	

	public String getAcceptcode() {
		return acceptcode;
	}

	public void setAcceptcode(String acceptcode) {
		this.acceptcode = acceptcode;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTransferMoneyDate() {
		return transferMoneyDate;
	}

	public void setTransferMoneyDate(String transferMoneyDate) {
		this.transferMoneyDate = transferMoneyDate;
	}

	public String getOutPhone() {
		return outPhone;
	}

	public void setOutPhone(String outPhone) {
		this.outPhone = outPhone;
	}

	public String getOutIdNo() {
		return outIdNo;
	}

	public void setOutIdNo(String outIdNo) {
		this.outIdNo = outIdNo;
	}

	public String getOutBankId() {
		return outBankId;
	}

	public void setOutBankId(String outBankId) {
		this.outBankId = outBankId;
	}
	
}
