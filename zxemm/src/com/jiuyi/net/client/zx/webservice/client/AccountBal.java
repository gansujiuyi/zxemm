package com.jiuyi.net.client.zx.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for accountBal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="accountBal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agreementCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subjectNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountBalance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountBal", propOrder = { "transactionDate", "custId",
		"agreementCode", "primaryAccount", "accountName", "subjectNum",
		"accountBalance" })
public class AccountBal {

	@XmlElement(required = true)
	protected String transactionDate;// 交易日期
	@XmlElement(required = true)
	protected String custId;// 客户ID
	@XmlElement(required = true)
	protected String agreementCode;// 协议编号
	@XmlElement(required = true)
	protected String primaryAccount;// 主账号
	@XmlElement(required = true)
	protected String accountName;// 账号名称
	@XmlElement(required = true)
	protected String subjectNum;// 科目编号
	@XmlElement(required = true)
	protected String accountBalance;// 账户余额

	/**
	 * Gets the value of the transactionDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * Sets the value of the transactionDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionDate(String value) {
		this.transactionDate = value;
	}

	/**
	 * Gets the value of the custId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * Sets the value of the custId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustId(String value) {
		this.custId = value;
	}

	/**
	 * Gets the value of the agreementCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAgreementCode() {
		return agreementCode;
	}

	/**
	 * Sets the value of the agreementCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAgreementCode(String value) {
		this.agreementCode = value;
	}

	/**
	 * Gets the value of the primaryAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrimaryAccount() {
		return primaryAccount;
	}

	/**
	 * Sets the value of the primaryAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPrimaryAccount(String value) {
		this.primaryAccount = value;
	}

	/**
	 * Gets the value of the accountName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the value of the accountName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountName(String value) {
		this.accountName = value;
	}

	/**
	 * Gets the value of the subjectNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubjectNum() {
		return subjectNum;
	}

	/**
	 * Sets the value of the subjectNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubjectNum(String value) {
		this.subjectNum = value;
	}

	/**
	 * Gets the value of the accountBalance property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * Sets the value of the accountBalance property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountBalance(String value) {
		this.accountBalance = value;
	}

}
