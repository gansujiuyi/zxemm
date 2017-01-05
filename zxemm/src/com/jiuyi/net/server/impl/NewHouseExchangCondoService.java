package com.jiuyi.net.server.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.jiuyi.net.message.condo.NewHouseExchangCondoUtil;
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
import com.jiuyi.net.server.INewHouseExchangCondoService;

/**
 * 禁止在此类中直接写方法逻辑体 !!!
 * 请用工具类实现逻辑体 !!!
 * @author wsf
 *
 */
@Service
@WebService(endpointInterface = "com.jiuyi.net.server.INewHouseExchangCondoService")
public class NewHouseExchangCondoService implements
		INewHouseExchangCondoService {

	/**
	 * 新房指令通知接口
	 */
	@Override
	public NoticeInstructionsRspMsg noticeInstructions(
			NoticeInstructionsReqMsg reqMsg) {
		return NewHouseExchangCondoUtil.getInstance().noticeInstructions(reqMsg);
	}

	/**
	 * 房管局查询银行状态接口(未完成)
	 */
	@Override
	public QurBankNoticeRspMsg qurBankNotice(QurBankNoticeReqMsg reqMsg) {
		return NewHouseExchangCondoUtil.getInstance().qurBankNotice(reqMsg);
	}

	/**
	 * 按合同编号查询各户的缴存记录
	 */
	@Override
	public QurRecordByContractNoRspMsg qurRecordByContractNo(
			QurRecordByContractNoReqMsg reqMsg) {
		return NewHouseExchangCondoUtil.getInstance().qurRecordByContractNo(reqMsg);
	}

	/**
	 * 查询整幢楼下各户的缴存记录
	 */
	@Override
	public QurRecordByHouseNoRspMsg qurRecordByHouseNo(
			QurRecordByHouseNoReqMsg reqMsg) {
		return NewHouseExchangCondoUtil.getInstance().qurRecordByHouseNo(reqMsg);
	}

	/**
	 * 查询整幢楼的监管余额
	 */
	@Override
	public QurAmountByBuildingNoRspMsg qurAmountByBuildingNo(
			QurAmountByBuildingNoReqMsg reqMsg) {
		return  NewHouseExchangCondoUtil.getInstance().qurAmountByBuildingNo(reqMsg);
	}

}
