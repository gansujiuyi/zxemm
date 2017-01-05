
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fundTransQryRsp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fundTransQryRsp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retshow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalpage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalrecs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transList" type="{http://server.net.uxun.com/}transList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fundTransQryRsp", propOrder = {
    "retcode",
    "retshow",
    "totalpage",
    "totalrecs",
    "transList"
})
public class FundTransQryRsp {

    protected String retcode;
    protected String retshow;
    protected String totalpage;
    protected String totalrecs;
    protected TransList transList;

    /**
     * Gets the value of the retcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetcode() {
        return retcode;
    }

    /**
     * Sets the value of the retcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetcode(String value) {
        this.retcode = value;
    }

    /**
     * Gets the value of the retshow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetshow() {
        return retshow;
    }

    /**
     * Sets the value of the retshow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetshow(String value) {
        this.retshow = value;
    }

    /**
     * Gets the value of the totalpage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalpage() {
        return totalpage;
    }

    /**
     * Sets the value of the totalpage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalpage(String value) {
        this.totalpage = value;
    }

    /**
     * Gets the value of the totalrecs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalrecs() {
        return totalrecs;
    }

    /**
     * Sets the value of the totalrecs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalrecs(String value) {
        this.totalrecs = value;
    }

    /**
     * Gets the value of the transList property.
     * 
     * @return
     *     possible object is
     *     {@link TransList }
     *     
     */
    public TransList getTransList() {
        return transList;
    }

    /**
     * Sets the value of the transList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransList }
     *     
     */
    public void setTransList(TransList value) {
        this.transList = value;
    }

}
