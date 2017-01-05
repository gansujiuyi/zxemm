package com.jiuyi.net.client.zx.webservice.client;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.9
 * Fri Sep 16 14:15:01 CST 2016
 * Generated source version: 2.2.9
 * 
 */

@WebService(targetNamespace = "http://service.ws.mams.com/", name = "IBankService")
@XmlSeeAlso({ObjectFactory.class})
public interface IBankService {

    @WebResult(name = "Data", targetNamespace = "")
    @RequestWrapper(localName = "qurAccountInfo", targetNamespace = "http://service.ws.mams.com/", className = "com.jiuyi.net.client.zx.webservice.client.QurAccountInfo")
    @WebMethod
    @ResponseWrapper(localName = "qurAccountInfoResponse", targetNamespace = "http://service.ws.mams.com/", className = "com.jiuyi.net.client.zx.webservice.client.QurAccountInfoResponse")
    public BankData qurAccountInfo(
        @WebParam(name = "AccountNo", targetNamespace = "")
        java.lang.String accountNo,
        @WebParam(name = "QueryDate", targetNamespace = "")
        java.lang.String queryDate,
        @WebParam(name = "StartDate", targetNamespace = "")
        java.lang.String startDate,
        @WebParam(name = "EndDate", targetNamespace = "")
        java.lang.String endDate
    );

    @WebResult(name = "uxunmsg", targetNamespace = "")
    @RequestWrapper(localName = "qurOrderInfo", targetNamespace = "http://service.ws.mams.com/", className = "com.jiuyi.net.client.zx.webservice.client.QurOrderInfo")
    @WebMethod
    @ResponseWrapper(localName = "qurOrderInfoResponse", targetNamespace = "http://service.ws.mams.com/", className = "com.jiuyi.net.client.zx.webservice.client.QurOrderInfoResponse")
    public java.lang.String qurOrderInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}