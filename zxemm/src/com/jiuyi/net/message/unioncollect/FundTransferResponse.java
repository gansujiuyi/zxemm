
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fundTransferResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fundTransferResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uxunmsg" type="{http://server.net.uxun.com/}fundTransferRspMsg" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fundTransferResponse", propOrder = {
    "uxunmsg"
})
public class FundTransferResponse {

    protected FundTransferRspMsg uxunmsg;

    /**
     * Gets the value of the uxunmsg property.
     * 
     * @return
     *     possible object is
     *     {@link FundTransferRspMsg }
     *     
     */
    public FundTransferRspMsg getUxunmsg() {
        return uxunmsg;
    }

    /**
     * Sets the value of the uxunmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FundTransferRspMsg }
     *     
     */
    public void setUxunmsg(FundTransferRspMsg value) {
        this.uxunmsg = value;
    }

}
