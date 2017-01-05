
package com.jiuyi.net.message.unioncollect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interestTransferRsp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interestTransferRsp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retshow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interestTransferRsp", propOrder = {
    "retcode",
    "retshow"
})
public class InterestTransferRsp {

    protected String retcode;
    protected String retshow;

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

}
