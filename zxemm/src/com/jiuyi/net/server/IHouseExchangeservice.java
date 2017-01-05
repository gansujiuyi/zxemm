package com.jiuyi.net.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.jiuyi.net.message.homeExchange.SendTransferStatusReqMsg;
import com.jiuyi.net.message.homeExchange.SendTransferStatusRspMsg;


/*
* @ WebService is for soap
* @ Path is for the rest top path
*/

@WebService
@Path("/UXUNJFYFRONT")
public interface IHouseExchangeservice {

	/**
	 * 通知资金监管平台过户状态
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/sendTransferStatus")
	public SendTransferStatusRspMsg sendTransferStatus(@WebParam(name = "uxunmsg") SendTransferStatusReqMsg reqMsg);
	
}
