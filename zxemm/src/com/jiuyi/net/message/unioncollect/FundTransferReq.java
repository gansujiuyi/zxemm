package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for fundTransferReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fundTransferReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inBankAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inBankPhoneNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outBankAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outBankPhoneNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outTradeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fundTransferReq", propOrder = { "accountType", "amt", "currencyType", "cvn", "inBankAcc",
		"inBankCode", "inBankName", "inBankPhoneNo", "memberNo", "outBankAcc", "outBankCode", "outBankName",
		"outBankPhoneNo", "outTradeNo", "remark", "tradeType", "validDate" })
public class FundTransferReq {

	protected String memberNo;

	protected String outBankAcc;

	protected String outBankName;

	protected String outBankCode;

	protected String inBankAcc;

	protected String inBankName;

	protected String inBankCode;

	protected String outTradeNo;

	protected String amt;

	protected String currencyType;

	protected String tradeType;

	protected String remark;

	protected String outBankPhoneNo;

	protected String accountType;

	protected String inBankPhoneNo;

	protected String validDate;

	protected String cvn;

	/**
	 * Gets the value of the accountType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the value of the accountType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAccountType(String value) {
		this.accountType = value;
	}

	/**
	 * Gets the value of the amt property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * Sets the value of the amt property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAmt(String value) {
		this.amt = value;
	}

	/**
	 * Gets the value of the currencyType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * Sets the value of the currencyType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCurrencyType(String value) {
		this.currencyType = value;
	}

	/**
	 * Gets the value of the cvn property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCvn() {
		return cvn;
	}

	/**
	 * Sets the value of the cvn property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCvn(String value) {
		this.cvn = value;
	}

	/**
	 * Gets the value of the inBankAcc property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getInBankAcc() {
		return inBankAcc;
	}

	/**
	 * Sets the value of the inBankAcc property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setInBankAcc(String value) {
		this.inBankAcc = value;
	}

	/**
	 * Gets the value of the inBankCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getInBankCode() {
		return inBankCode;
	}

	/**
	 * Sets the value of the inBankCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setInBankCode(String value) {
		this.inBankCode = value;
	}

	/**
	 * Gets the value of the inBankName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getInBankName() {
		return inBankName;
	}

	/**
	 * Sets the value of the inBankName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setInBankName(String value) {
		this.inBankName = value;
	}

	/**
	 * Gets the value of the inBankPhoneNo property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getInBankPhoneNo() {
		return inBankPhoneNo;
	}

	/**
	 * Sets the value of the inBankPhoneNo property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setInBankPhoneNo(String value) {
		this.inBankPhoneNo = value;
	}

	/**
	 * Gets the value of the memberNo property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the value of the memberNo property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMemberNo(String value) {
		this.memberNo = value;
	}

	/**
	 * Gets the value of the outBankAcc property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOutBankAcc() {
		return outBankAcc;
	}

	/**
	 * Sets the value of the outBankAcc property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOutBankAcc(String value) {
		this.outBankAcc = value;
	}

	/**
	 * Gets the value of the outBankCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOutBankCode() {
		return outBankCode;
	}

	/**
	 * Sets the value of the outBankCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOutBankCode(String value) {
		this.outBankCode = value;
	}

	/**
	 * Gets the value of the outBankName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOutBankName() {
		return outBankName;
	}

	/**
	 * Sets the value of the outBankName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOutBankName(String value) {
		this.outBankName = value;
	}

	/**
	 * Gets the value of the outBankPhoneNo property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOutBankPhoneNo() {
		return outBankPhoneNo;
	}

	/**
	 * Sets the value of the outBankPhoneNo property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOutBankPhoneNo(String value) {
		this.outBankPhoneNo = value;
	}

	/**
	 * Gets the value of the outTradeNo property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * Sets the value of the outTradeNo property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the tradeType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * Sets the value of the tradeType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTradeType(String value) {
		this.tradeType = value;
	}

	/**
	 * Gets the value of the validDate property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * Sets the value of the validDate property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setValidDate(String value) {
		this.validDate = value;
	}

}
