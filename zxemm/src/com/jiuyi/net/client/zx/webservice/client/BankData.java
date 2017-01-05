package com.jiuyi.net.client.zx.webservice.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for bankData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="bankData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountBal" type="{http://service.ws.mams.com/}accountBal"/>
 *         &lt;element name="accountFlows" type="{http://service.ws.mams.com/}accountFlows" maxOccurs="unbounded"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bankData", propOrder = { "accountBal", "accountFlows",
		"status", "statusText" })
public class BankData {

	@XmlElement(required = true)
	protected AccountBal accountBal;// 查询账户信息
	@XmlElement(required = true)
	protected List<AccountFlows> accountFlows;// 流水信息列表
	@XmlElement(required = true)
	protected String status;// 查询状态 0000 查询成功 9999 查询失败
	@XmlElement(required = true)
	protected String statusText;// 状态说明

	/**
	 * Gets the value of the accountBal property.
	 * 
	 * @return possible object is {@link AccountBal }
	 * 
	 */
	public AccountBal getAccountBal() {
		return accountBal;
	}

	/**
	 * Sets the value of the accountBal property.
	 * 
	 * @param value
	 *            allowed object is {@link AccountBal }
	 * 
	 */
	public void setAccountBal(AccountBal value) {
		this.accountBal = value;
	}

	/**
	 * Gets the value of the accountFlows property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the accountFlows property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAccountFlows().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AccountFlows }
	 * 
	 * 
	 */
	public List<AccountFlows> getAccountFlows() {
		if (accountFlows == null) {
			accountFlows = new ArrayList<AccountFlows>();
		}
		return this.accountFlows;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

	/**
	 * Gets the value of the statusText property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * Sets the value of the statusText property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatusText(String value) {
		this.statusText = value;
	}

}
