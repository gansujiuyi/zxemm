package com.jiuyi.jyplat.service.condo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.net.client.INewHouseExchangCondoService;
import com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg;
import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.net.message.condo.GetInstractionInfoResultRspMsg;
import com.jiuyi.net.message.condo.SetHandleInfoResultRsp;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;
import com.jiuyi.net.utils.WSNewHouseExchangCondoClientUtils;

@Service("newHouseExchangService")
public class NewHouseExchangService implements INewHouseExchangService {

	Logger log = Logger.getLogger(NewHouseExchangService.class);

	/**
	 * 查询对应的购买合同信息 返回信息：地幢号
	 * building_code、合同编号bsinum、预测绘建筑面积area、室号cellnum、项目名称basename
	 * 、房屋坐落houseaddress
	 * 、企业名称rcname，项目编号basecode，企业编号compcode，购买人信息（List（姓名，身份证号码，电话号码）），响应码，错误提示
	 * 
	 * @param bsinum
	 *            合同号
	 * @return
	 */
	@Override
	public GetBarginInfoResultRspMsg getBarginInfo(String bsinum) {
		String isTest = ConfigManager.getString("is_test_environment", "1");
		GetBarginInfoResultRspMsg rspMsg = new GetBarginInfoResultRspMsg();
		if ("1".equals(isTest)) {
			log.info("调用生产接口地址！");
			INewHouseExchangCondoService service = WSNewHouseExchangCondoClientUtils
					.createNewHouseExchangCondoService();
			log.info("开始调用房管局接口！");
			rspMsg = service.GetBarginInfoResult(bsinum);
		} else {
			log.info("调用测试数据！");
			rspMsg.setPsbtolpri("100000");
			rspMsg.setBuildingCode("LCI97002003");
			rspMsg.setBsinum(bsinum);
			rspMsg.setArea("78.41");
			rspMsg.setCellnum("103");
			rspMsg.setRcname("甘肃建安房地产开发有限公司");
			rspMsg.setPbiname("紫雅花园");
			rspMsg.setCompcode("10065");
			rspMsg.setPbicode("2682");
			rspMsg.setCode("0000");
			rspMsg.setMessage("调用成功！");
		}
		return rspMsg;
	}

	/**
	 * 查询企业信息
	 * 
	 * @param buiding
	 *            地幢号
	 * @return
	 */
	@Override
	public GetEntInfoRspMsg getEntInfo(String building) {
		String isTest = ConfigManager.getString("is_test_environment", "1");
		GetEntInfoRspMsg rspMsg = new GetEntInfoRspMsg();
		if ("1".equals(isTest)) {
			log.info("调用生产接口地址！");
			INewHouseExchangCondoService service = WSNewHouseExchangCondoClientUtils.createNewHouseExchangCondoService();
			log.info("开始调用房管局接口！");
			rspMsg = service.GetEntInfo(building);
//			rspMsg = service.GetEntInfo(building ,"1");
		} else {
			log.info("调用测试数据！");
			rspMsg.setBuilding(building);
			rspMsg.setCode("0000");
			rspMsg.setCompanyname("兰州嘉文建设有限公司");
			rspMsg.setConstructarea("100.0");
			rspMsg.setMessage("调用成功");
			rspMsg.setPbicode("TY9090");
			rspMsg.setPbicompcode("IO9990");
			rspMsg.setProjectaddress("兰州城关区102号");
			rspMsg.setProjectname("嘉文花园");
		}
		return rspMsg;
	}

	/**
	 * 查询指令方法
	 * @param bankCode 银行号
	 * 
	 * @param buiding
	 *            地幢号(合同号)
	 * @param instruction
	 *            指令类型 1：开户操作 2：划款出账操作 3：撤销监管  4:解除合同(退款) 5： 账户变更通知
	 * 
	 * @param payProcess 拨付环节
	 * @return
	 */
	@Override
	public GetInstractionInfoResultRspMsg getInstractionInfo(String bankCode , String buiding, String instruction ,String payProcess) {
		String isTest = ConfigManager.getString("is_test_environment", "1");
		GetInstractionInfoResultRspMsg rspMsg = new GetInstractionInfoResultRspMsg();
		if ("1".equals(isTest)) {
			log.info("调用生产接口地址！");
			INewHouseExchangCondoService service = WSNewHouseExchangCondoClientUtils
					.createNewHouseExchangCondoService();
			log.info("开始调用房管局接口！");
			rspMsg = service.getInstractionInfoResult(bankCode , buiding, instruction ,payProcess);
		} else {
			log.info("调用测试数据！");
			rspMsg.setAmt("10000.00");
			rspMsg.setBuiding(buiding);
			rspMsg.setCode("0000");
			rspMsg.setContactNo("HT12345");
			rspMsg.setInstruction(instruction);
			rspMsg.setMessage("调用成功！");
			rspMsg.setModifyAccount("622110890890123123");
		}
		return rspMsg;
	}

	/**
	 * 通知房管局操作状态
	 * 
	 * @param instruction
	 *            指令种类 1为开户指令 2 为审批划款指令 3为解除资金监管指令 4为撤销购买合同的指令 5 开发账户变更的指令
	 *            6购房入账通知指令
	 * @param buiding
	 *            地幢号
	 * @param bsinum
	 *            合同号
	 * @param ifsuccess
	 *            1成功；0未成功
	 * @param errorinfo
	 *            错误提示
	 * @param regulateAccount
	 *            监管账号
	 * @return
	 */
	@Override
	public SetHandleInfoResultRspMsg setHandleInfo(String instruction,
			String building, String bsinum, String ifsuccess, String errorinfo,
			String regulateAccount, String acceptCode , String bankCode) {
		String isTest = ConfigManager.getString("is_test_environment", "1");
		SetHandleInfoResultRspMsg rspMsg = new SetHandleInfoResultRspMsg();
		if ("1".equals(isTest)) {
			log.info("调用生产接口地址！");
			INewHouseExchangCondoService service = WSNewHouseExchangCondoClientUtils
					.createNewHouseExchangCondoService();
			log.info("开始调用房管局接口！");
			//bankCode 银行号 0016fa58942d4f5ea4f64fa7cb06e63a
			rspMsg = service.setHandleInfoResult(instruction, building, bsinum,
					ifsuccess, errorinfo, regulateAccount, acceptCode ,bankCode);
		} else {
			log.info("调用测试数据！");
			SetHandleInfoResultRsp rsp = new SetHandleInfoResultRsp();
			rsp.setCode("0000");
			rsp.setMessage("调用成功");
			rspMsg.setCode("0000");
			rspMsg.setMessage("调用成功");
		}
		return rspMsg;
	}

	@Override
	public GetInstractionInfoResultRspMsg getInstractionInfoResult(String bankCode ,  String buiding ,  String instruction ,String payProcess) {
		String isTest = ConfigManager.getString("is_test_environment", "1");
		GetInstractionInfoResultRspMsg rspMsg  = new GetInstractionInfoResultRspMsg();
		if ("1".equals(isTest)) {
			log.info("开始调用生产接口地址！");
			INewHouseExchangCondoService service = WSNewHouseExchangCondoClientUtils.createNewHouseExchangCondoService();
			log.info("开始调用房管局开始调用查询指令接口！");
			rspMsg = service.getInstractionInfoResult(bankCode , buiding ,instruction ,payProcess);
		} else {
			log.info("调用测试数据！");
			rspMsg.setCode("0000");
			rspMsg.setMessage("调用成功");
			rspMsg.setAmt("100");
			rspMsg.setContactNo("2007预06880");
			rspMsg.setInstruction(instruction);
			rspMsg.setModifyAccount((int)(Math.random()*(9999-1000+1))+1000+"111");
			rspMsg.setCode("0000");
			rspMsg.setMessage("调用成功");
		}
		return rspMsg;
	}
}
