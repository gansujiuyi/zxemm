package com.jiuyi.net.utils;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.jiuyi.net.client.INewHouseExchangCondoService;
import com.jiuyi.net.message.Head;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DateUtils;
/**
 * 新房资金监管   房管局提供服务   工具类
 * @author wsf
 *
 */
public class WSNewHouseExchangCondoClientUtils {

	private static Logger log = Logger.getLogger(WSNewHouseExchangCondoClientUtils.class);
	
	public static INewHouseExchangCondoService service = null;
	
	private WSNewHouseExchangCondoClientUtils(){
		
	}
	
	/**
	 * 获得报文头
	 * @param serviceName
	 * @return
	 */
	public static Head createHead(String serviceName){
		Head head = new Head();
		head.setDevno("112");
		head.setAuthcode(Constant.AuthCode);
		head.setReqsn(DateUtils.getNowDateTime2() + "002");
		head.setServicename(serviceName);
		head.setTranchannel(Constant.TranChannel);
		head.setTrandatetime(DateUtils.getCurDateTime());
		head.setVersion(Constant.Version);
		return head;
	}
	
	/**
	 * 获得NewHouseExchangCondoService对象
	 * @return
	 */
	public static INewHouseExchangCondoService createNewHouseExchangCondoService(){
		try {
			if(service == null){
				String newHouseSuperviseUrl = ConfigManager.getString("newHouseSuperviseUrl", "http://22.168.2.124:8080/lzrs-ws/soap/noticeSoapService");
				JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
				factory.setServiceClass(INewHouseExchangCondoService.class);
				factory.setAddress(newHouseSuperviseUrl);
				service = (INewHouseExchangCondoService) factory.create();
				return service;
			}
			return service;
		} catch (Exception e) {
			log.error("创建NewHouseExchangCondoService连接失败："+e.getMessage(), e);
			return null;
		}
	}
	
}
