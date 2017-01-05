
package com.jiuyi.net.client.zx.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mams.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AccountFlows_QNAME = new QName("http://service.ws.mams.com/", "AccountFlows");
    private final static QName _QurAccountInfo_QNAME = new QName("http://service.ws.mams.com/", "qurAccountInfo");
    private final static QName _QurOrderInfo_QNAME = new QName("http://service.ws.mams.com/", "qurOrderInfo");
    private final static QName _QurOrderInfoResponse_QNAME = new QName("http://service.ws.mams.com/", "qurOrderInfoResponse");
    private final static QName _AccountBal_QNAME = new QName("http://service.ws.mams.com/", "AccountBal");
    private final static QName _QurAccountInfoResponse_QNAME = new QName("http://service.ws.mams.com/", "qurAccountInfoResponse");
    private final static QName _BankData_QNAME = new QName("http://service.ws.mams.com/", "BankData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mams.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccountFlows }
     * 
     */
    public AccountFlows createAccountFlows() {
        return new AccountFlows();
    }

    /**
     * Create an instance of {@link AccountBal }
     * 
     */
    public AccountBal createAccountBal() {
        return new AccountBal();
    }

    /**
     * Create an instance of {@link QurAccountInfoResponse }
     * 
     */
    public QurAccountInfoResponse createQurAccountInfoResponse() {
        return new QurAccountInfoResponse();
    }

    /**
     * Create an instance of {@link QurOrderInfo }
     * 
     */
    public QurOrderInfo createQurOrderInfo() {
        return new QurOrderInfo();
    }

    /**
     * Create an instance of {@link BankData }
     * 
     */
    public BankData createBankData() {
        return new BankData();
    }

    /**
     * Create an instance of {@link QurOrderInfoResponse }
     * 
     */
    public QurOrderInfoResponse createQurOrderInfoResponse() {
        return new QurOrderInfoResponse();
    }

    /**
     * Create an instance of {@link QurAccountInfo }
     * 
     */
    public QurAccountInfo createQurAccountInfo() {
        return new QurAccountInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountFlows }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "AccountFlows")
    public JAXBElement<AccountFlows> createAccountFlows(AccountFlows value) {
        return new JAXBElement<AccountFlows>(_AccountFlows_QNAME, AccountFlows.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QurAccountInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "qurAccountInfo")
    public JAXBElement<QurAccountInfo> createQurAccountInfo(QurAccountInfo value) {
        return new JAXBElement<QurAccountInfo>(_QurAccountInfo_QNAME, QurAccountInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QurOrderInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "qurOrderInfo")
    public JAXBElement<QurOrderInfo> createQurOrderInfo(QurOrderInfo value) {
        return new JAXBElement<QurOrderInfo>(_QurOrderInfo_QNAME, QurOrderInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QurOrderInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "qurOrderInfoResponse")
    public JAXBElement<QurOrderInfoResponse> createQurOrderInfoResponse(QurOrderInfoResponse value) {
        return new JAXBElement<QurOrderInfoResponse>(_QurOrderInfoResponse_QNAME, QurOrderInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountBal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "AccountBal")
    public JAXBElement<AccountBal> createAccountBal(AccountBal value) {
        return new JAXBElement<AccountBal>(_AccountBal_QNAME, AccountBal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QurAccountInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "qurAccountInfoResponse")
    public JAXBElement<QurAccountInfoResponse> createQurAccountInfoResponse(QurAccountInfoResponse value) {
        return new JAXBElement<QurAccountInfoResponse>(_QurAccountInfoResponse_QNAME, QurAccountInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.mams.com/", name = "BankData")
    public JAXBElement<BankData> createBankData(BankData value) {
        return new JAXBElement<BankData>(_BankData_QNAME, BankData.class, null, value);
    }

}
