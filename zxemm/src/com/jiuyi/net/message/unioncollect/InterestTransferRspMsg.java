
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interestTransferRspMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interestTransferRspMsg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://server.net.uxun.com/}interestTransferRsp" minOccurs="0"/>
 *         &lt;element name="head" type="{http://server.net.uxun.com/}unionHead" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interestTransferRspMsg", propOrder = {
    "body",
    "head"
})
public class InterestTransferRspMsg {

    protected InterestTransferRsp body;
    protected UnionHead head;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link InterestTransferRsp }
     *     
     */
    public InterestTransferRsp getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestTransferRsp }
     *     
     */
    public void setBody(InterestTransferRsp value) {
        this.body = value;
    }

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link UnionHead }
     *     
     */
    public UnionHead getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnionHead }
     *     
     */
    public void setHead(UnionHead value) {
        this.head = value;
    }

}
