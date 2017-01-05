package com.jiuyi.net.client.zx.webservice.client.handler;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.DOMException;

import com.jiuyi.net.client.zx.webservice.client.header.User;


public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage>
{
	private String name;
	private String pass;
	public AddHeaderInterceptor(String name, String pass)
	{
		super(Phase.PREPARE_SEND);
//		super(Phase.WRITE);
		this.name = name;
		this.pass = pass;
	}

	//处理消息
	public void handleMessage(SoapMessage message)
	{
		
		try 
		{
			System.out.println("****************************进入拦截器*********************************************");
			List<Header> headers = message.getHeaders();
			QName qname = new QName("http://service.ws.mams.com", "citicbank", "ns");
			User user = new User(name, pass);
			DataBinding dataBinding = new JAXBDataBinding(User.class);
			SoapHeader authHeader = new SoapHeader(qname , user, dataBinding);
			headers.add(authHeader);
			System.out.println("****************************离开拦截器*********************************************");
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
