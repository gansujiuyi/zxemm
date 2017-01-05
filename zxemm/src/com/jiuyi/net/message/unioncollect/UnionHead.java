
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unionHead complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unionHead">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reqsn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tranchannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trandatetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unionHead", propOrder = {
    "authcode",
    "custNo",
    "devno",
    "reqsn",
    "servicename",
    "signData",
    "tranchannel",
    "trandatetime",
    "version"
})
public class UnionHead {

    protected String authcode;
    protected String custNo;
    protected String devno;
    protected String reqsn;
    protected String servicename;
    protected String signData;
    protected String tranchannel;
    protected String trandatetime;
    protected String version;

    /**
     * Gets the value of the authcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthcode() {
        return authcode;
    }

    /**
     * Sets the value of the authcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthcode(String value) {
        this.authcode = value;
    }

    /**
     * Gets the value of the custNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * Sets the value of the custNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustNo(String value) {
        this.custNo = value;
    }

    /**
     * Gets the value of the devno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevno() {
        return devno;
    }

    /**
     * Sets the value of the devno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevno(String value) {
        this.devno = value;
    }

    /**
     * Gets the value of the reqsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqsn() {
        return reqsn;
    }

    /**
     * Sets the value of the reqsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqsn(String value) {
        this.reqsn = value;
    }

    /**
     * Gets the value of the servicename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicename() {
        return servicename;
    }

    /**
     * Sets the value of the servicename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicename(String value) {
        this.servicename = value;
    }

    /**
     * Gets the value of the signData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignData() {
        return signData;
    }

    /**
     * Sets the value of the signData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignData(String value) {
        this.signData = value;
    }

    /**
     * Gets the value of the tranchannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranchannel() {
        return tranchannel;
    }

    /**
     * Sets the value of the tranchannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranchannel(String value) {
        this.tranchannel = value;
    }

    /**
     * Gets the value of the trandatetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrandatetime() {
        return trandatetime;
    }

    /**
     * Sets the value of the trandatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrandatetime(String value) {
        this.trandatetime = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
