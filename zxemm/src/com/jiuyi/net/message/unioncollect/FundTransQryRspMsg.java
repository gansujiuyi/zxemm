
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fundTransQryRspMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fundTransQryRspMsg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://server.net.uxun.com/}fundTransQryRsp" minOccurs="0"/>
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
@XmlType(name = "fundTransQryRspMsg", propOrder = {
    "body",
    "head"
})
public class FundTransQryRspMsg {

    protected FundTransQryRsp body;
    protected UnionHead head;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link FundTransQryRsp }
     *     
     */
    public FundTransQryRsp getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link FundTransQryRsp }
     *     
     */
    public void setBody(FundTransQryRsp value) {
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
