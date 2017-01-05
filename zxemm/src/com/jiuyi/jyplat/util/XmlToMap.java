package com.jiuyi.jyplat.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

public class XmlToMap extends VisitorSupport {
	
	private Map ctx = new HashMap<Object, Object>();
	
	public static void main(String[] args) {
		String xml = "<?xml version='1.0' encoding='UTF-8'?><EBANK><TranCode>770110</TranCode><ChannelId>17</ChannelId><Teller>WEBT</Teller><BODY><AcctNo>101462036145332</AcctNo></BODY></EBANK>";

		Map map = new XmlToMap().toMap(xml);
		System.out.println(map);
	}

	public Map toMap(String xml) {
		try {
			DocumentHelper.parseText(xml).accept(this);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return ctx;
	}

	@Override
	public void visit(Element e) {
		if (e.isTextOnly()) {
			ctx.put(e.getName(), e.getText());
		}
	}

	

}
