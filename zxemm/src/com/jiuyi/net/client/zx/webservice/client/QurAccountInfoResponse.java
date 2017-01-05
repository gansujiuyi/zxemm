
package com.jiuyi.net.client.zx.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for qurAccountInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qurAccountInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Data" type="{http://service.ws.mams.com/}bankData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qurAccountInfoResponse", propOrder = {
    "data"
})
public class QurAccountInfoResponse {

    @XmlElement(name = "Data")
    protected BankData data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link BankData }
     *     
     */
    public BankData getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankData }
     *     
     */
    public void setData(BankData value) {
        this.data = value;
    }

}
