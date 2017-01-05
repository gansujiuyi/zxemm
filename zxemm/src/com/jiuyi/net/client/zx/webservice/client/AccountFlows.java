
package com.jiuyi.net.client.zx.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountFlows complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountFlows">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sysAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openAccountDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debitCreditType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cashTransitionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountBalance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionSeqNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oppositeCustAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oppositeCustAccountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oppositeBankName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openAccountOrgId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountFlows", propOrder = {
    "custId",
    "custName",
    "sysAccount",
    "custAccount",
    "openAccountDate",
    "transactionCode",
    "debitCreditType",
    "cashTransitionType",
    "transactionDate",
    "transactionTime",
    "transactionAmount",
    "accountBalance",
    "transactionSeqNum",
    "oppositeCustAccount",
    "oppositeCustAccountName",
    "oppositeBankName",
    "openAccountOrgId",
    "memo"
})
public class AccountFlows {

	@XmlElement(required = true)
    protected String custId;//客户ID
    @XmlElement(required = true)
    protected String custName;//客户名称
    @XmlElement(required = true)
    protected String sysAccount;//系统账户
    @XmlElement(required = true)
    protected String custAccount;//客户账户
    @XmlElement(required = true)
    protected String openAccountDate;//开户日期
    @XmlElement(required = true)
    protected String transactionCode;//交易码
    @XmlElement(required = true)
    protected String debitCreditType;//借贷类型  0001--借  0002---贷
    @XmlElement(required = true)
    protected String cashTransitionType;//现转类型
    @XmlElement(required = true)
    protected String transactionDate;//交易日期
    @XmlElement(required = true)
    protected String transactionTime;//交易日期
    @XmlElement(required = true)
    protected String transactionAmount;//交易金额
    @XmlElement(required = true)
    protected String accountBalance;//账户余额
    @XmlElement(required = true)
    protected String transactionSeqNum;//交易流水号
    @XmlElement(required = true)
    protected String oppositeCustAccount;//交易对手账户
    @XmlElement(required = true)
    protected String oppositeCustAccountName;//交易对手账户名称
    @XmlElement(required = true)
    protected String oppositeBankName;//交易对手账户开户行名称
    @XmlElement(required = true)
    protected String openAccountOrgId;//交易对手账户机构名称
    @XmlElement(required = true)
    protected String memo;//摘要

    /**
     * Gets the value of the custId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustId() {
        return custId;
    }

    /**
     * Sets the value of the custId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustId(String value) {
        this.custId = value;
    }

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * Gets the value of the sysAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysAccount() {
        return sysAccount;
    }

    /**
     * Sets the value of the sysAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysAccount(String value) {
        this.sysAccount = value;
    }

    /**
     * Gets the value of the custAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAccount() {
        return custAccount;
    }

    /**
     * Sets the value of the custAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAccount(String value) {
        this.custAccount = value;
    }

    /**
     * Gets the value of the openAccountDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenAccountDate() {
        return openAccountDate;
    }

    /**
     * Sets the value of the openAccountDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenAccountDate(String value) {
        this.openAccountDate = value;
    }

    /**
     * Gets the value of the transactionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionCode() {
        return transactionCode;
    }

    /**
     * Sets the value of the transactionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionCode(String value) {
        this.transactionCode = value;
    }

    /**
     * Gets the value of the debitCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCreditType() {
        return debitCreditType;
    }

    /**
     * Sets the value of the debitCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCreditType(String value) {
        this.debitCreditType = value;
    }

    /**
     * Gets the value of the cashTransitionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCashTransitionType() {
        return cashTransitionType;
    }

    /**
     * Sets the value of the cashTransitionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashTransitionType(String value) {
        this.cashTransitionType = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(String value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the transactionTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTime() {
        return transactionTime;
    }

    /**
     * Sets the value of the transactionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTime(String value) {
        this.transactionTime = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionAmount(String value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the accountBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the value of the accountBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountBalance(String value) {
        this.accountBalance = value;
    }

    /**
     * Gets the value of the transactionSeqNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSeqNum() {
        return transactionSeqNum;
    }

    /**
     * Sets the value of the transactionSeqNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSeqNum(String value) {
        this.transactionSeqNum = value;
    }

    /**
     * Gets the value of the oppositeCustAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOppositeCustAccount() {
        return oppositeCustAccount;
    }

    /**
     * Sets the value of the oppositeCustAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOppositeCustAccount(String value) {
        this.oppositeCustAccount = value;
    }

    /**
     * Gets the value of the oppositeCustAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOppositeCustAccountName() {
        return oppositeCustAccountName;
    }

    /**
     * Sets the value of the oppositeCustAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOppositeCustAccountName(String value) {
        this.oppositeCustAccountName = value;
    }

    /**
     * Gets the value of the oppositeBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOppositeBankName() {
        return oppositeBankName;
    }

    /**
     * Sets the value of the oppositeBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOppositeBankName(String value) {
        this.oppositeBankName = value;
    }

    /**
     * Gets the value of the openAccountOrgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenAccountOrgId() {
        return openAccountOrgId;
    }

    /**
     * Sets the value of the openAccountOrgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenAccountOrgId(String value) {
        this.openAccountOrgId = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

}
