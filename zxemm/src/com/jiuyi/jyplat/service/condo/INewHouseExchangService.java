package com.jiuyi.jyplat.service.condo;

import javax.jws.WebParam;

import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg;
import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.net.message.condo.GetInstractionInfoResultRspMsg;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;

/**
 * 新房资金监管    房管局提供的相关接口   service 层方法
 * @author wsf
 *
 */
public interface INewHouseExchangService {

	/**
	 * 查询指令方法
	 * @param bankCode 银行代码
	 * @param buiding  地幢号
	 * @param instruction 指令类型  1：开户操作   2：划款出账操作  3： 购房入账通知  4： 账户变更通知
	 * @param payProcess 拨付环节
	 * @return
	 */
	public GetInstractionInfoResultRspMsg getInstractionInfo(String bankCode ,String buiding , String instruction ,String payProcess);
	
	/**
	 * 通知房管局操作状态
	 * @param instruction  指令种类   1为开户指令  2 为审批划款指令  3为解除资金监管指令  4为撤销购买合同的指令  5 开发账户变更的指令  6购房入账通知指令  
	 * @param buiding  地幢号
	 * @param bsinum  合同号
	 * @param ifsuccess  1成功；0未成功
	 * @param errorinfo  错误提示
	 * @param regulateAccount  监管账号
	 * @param bankCode 银行编码
	 * @return
	 */
	public SetHandleInfoResultRspMsg setHandleInfo(String instruction , String building , String bsinum , String ifsuccess , String errorinfo , String regulateAccount, String acceptCode , String bankCode);
	
	/**
	 * 查询企业信息
	 * @param buiding  地幢号
	 * @return
	 */
	public GetEntInfoRspMsg getEntInfo(String building);
	
	/**
	 * 查询对应的购买合同信息
	 * @param bsinum  合同号
	 * @return
	 */
	public GetBarginInfoResultRspMsg getBarginInfo(String bsinum);
	/****
	 * 查询指令
	 * @param instruction  指令种类   1为开户指令  2 为审批划款指令  3为解除资金监管指令  4为撤销购买合同的指令  5 开发账户变更的指令  6购房入账通知指令
	 * @param instruction
	 */
	public GetInstractionInfoResultRspMsg getInstractionInfoResult(String bankCode ,  String buiding ,  String instruction ,String payProcess);
}
