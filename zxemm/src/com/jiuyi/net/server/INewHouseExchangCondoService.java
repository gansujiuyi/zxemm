package com.jiuyi.net.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.jiuyi.net.message.condo.NoticeInstructionsReqMsg;
import com.jiuyi.net.message.condo.NoticeInstructionsRspMsg;
import com.jiuyi.net.message.condo.QurAmountByBuildingNoReqMsg;
import com.jiuyi.net.message.condo.QurAmountByBuildingNoRspMsg;
import com.jiuyi.net.message.condo.QurBankNoticeReqMsg;
import com.jiuyi.net.message.condo.QurBankNoticeRspMsg;
import com.jiuyi.net.message.condo.QurRecordByContractNoReqMsg;
import com.jiuyi.net.message.condo.QurRecordByContractNoRspMsg;
import com.jiuyi.net.message.condo.QurRecordByHouseNoReqMsg;
import com.jiuyi.net.message.condo.QurRecordByHouseNoRspMsg;

/**
 * 新房资金监管相关接口
 * @author wsf
 *
 */
@WebService(targetNamespace = "http://server.net.uxun.com/", name = "NewHouseExchangCondoService")
@Path("/UXUNNEWHOUSE")
public interface INewHouseExchangCondoService {

	
	/**
	 * 新房指令通知接口
	 * @param reqMsg
	 * @return
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/noticeInstructions") 
	public NoticeInstructionsRspMsg noticeInstructions(@WebParam(name = "uxunmsg") NoticeInstructionsReqMsg reqMsg);
	
	/**
	 * 房管局查询银行状态接口
	 * @param reqMsg
	 * @return
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/qurBankNotice")
	public QurBankNoticeRspMsg qurBankNotice(@WebParam(name = "uxunmsg") QurBankNoticeReqMsg reqMsg);
	
	/**
	 * 查询整幢楼下各户的缴存记录
	 * @param reqMsg
	 * @return
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/qurRecordByHouseNo")
	public QurRecordByHouseNoRspMsg qurRecordByHouseNo(@WebParam(name = "uxunmsg") QurRecordByHouseNoReqMsg reqMsg);
	
	/**
	 * 按合同编号查询各户的缴存记录
	 * @param reqMsg
	 * @return
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/qurRecordByContractNo")
	public QurRecordByContractNoRspMsg qurRecordByContractNo(@WebParam(name = "uxunmsg") QurRecordByContractNoReqMsg reqMsg);
	
	/**
	 * 根据地幢号查询监管余额
	 * @param reqMsg
	 * @return
	 */
	@WebResult(name = "uxunmsg")
	@POST
	@Path("/qurAmountByBuildingNo")
	public QurAmountByBuildingNoRspMsg qurAmountByBuildingNo(@WebParam(name = "uxunmsg") QurAmountByBuildingNoReqMsg reqMsg);
}
