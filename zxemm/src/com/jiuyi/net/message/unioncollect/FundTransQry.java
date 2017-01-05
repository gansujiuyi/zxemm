
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fundTransQry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fundTransQry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uxunmsg" type="{http://server.net.uxun.com/}fundTransQryReqMsg" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fundTransQry", propOrder = {
    "uxunmsg"
})
public class FundTransQry {

    protected FundTransQryReqMsg uxunmsg;

    /**
     * Gets the value of the uxunmsg property.
     * 
     * @return
     *     possible object is
     *     {@link FundTransQryReqMsg }
     *     
     */
    public FundTransQryReqMsg getUxunmsg() {
        return uxunmsg;
    }

    /**
     * Sets the value of the uxunmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FundTransQryReqMsg }
     *     
     */
    public void setUxunmsg(FundTransQryReqMsg value) {
        this.uxunmsg = value;
    }

}
