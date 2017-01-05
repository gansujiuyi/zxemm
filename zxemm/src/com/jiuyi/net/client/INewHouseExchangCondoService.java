package com.jiuyi.net.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 新房资金监管房管局提供的接口  有待修改
 * @author wsf
 *
 */
@WebService(targetNamespace="http://soap.examples.gsww.com" , name = "INewHouseExchangCondoService")
public interface INewHouseExchangCondoService {
	
	/**
	 * 查询指令接口
	 * @param uxunmsg
	 * @return
	 */
	@WebResult(name = "returnMsg" , targetNamespace = "")
	@WebMethod
	public com.jiuyi.net.message.condo.GetInstractionInfoResultRspMsg getInstractionInfoResult(
			@WebParam(name = "bankCode", targetNamespace = "") String bankCode,
			@WebParam(name = "buiding", targetNamespace = "") String buiding,
			@WebParam(name = "instruction", targetNamespace = "") String instruction,
			@WebParam(name = "payProcess", targetNamespace = "") String payProcess);
	
	/**
	 * 通知房管局操作状态接口
	 * @param uxunmsg
	 * @return
	 */
	@WebResult(name = "returnMsg" , targetNamespace = "")
	@WebMethod
	public com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg setHandleInfoResult(
			@WebParam(name = "instruction" , targetNamespace = "")  String instruction,
			@WebParam(name = "building" , targetNamespace = "")  String building,
			@WebParam(name = "bsinum" , targetNamespace = "")  String bsinum, 
			@WebParam(name = "ifsuccess" , targetNamespace = "")  String ifsuccess, 
			@WebParam(name = "errorinfo" , targetNamespace = "")  String errorinfo,
			@WebParam(name = "regulateAccount" , targetNamespace = "")  String regulateAccount,
			@WebParam(name = "acceptCode" , targetNamespace = "")  String acceptCode,
			@WebParam(name = "bankCode" , targetNamespace = "")String bankCode);
	
	/**
	 * 查询企业信息接口
	 * @param uxunmsg
	 * @return
	 */
//	@WebResult(name = "returnMsg" , targetNamespace = "")
//	@WebMethod
//	public com.jiuyi.net.message.condo.GetEntInfoRspMsg GetEntInfo(
//			@WebParam(name = "building" , targetNamespace = "") String building,
//			@WebParam(name = "instruction" , targetNamespace = "")  String instruction);
	
	@WebResult(name = "returnMsg" , targetNamespace = "")
	@WebMethod
	public com.jiuyi.net.message.condo.GetEntInfoRspMsg GetEntInfo(
			@WebParam(name = "BUILDING" , targetNamespace = "") String building);
	
	/**
	 * 查询对应的购买合同信息
	 * @param bsinum
	 * @return
	 */
	@WebResult(name = "returnMsg" , targetNamespace = "")
	@WebMethod
	public com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg GetBarginInfoResult(
			@WebParam(name = "bsinum" , targetNamespace = "") String bsinum);
}
