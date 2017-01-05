package com.jiuyi.jyplat.service.onlineBank;

import java.util.List;

import com.jiuyi.net.message.onlinebank.EbankLogInfo;

/**
 * @author Administrator
 *
 */
public interface IOnlineBankService {
	/**
	 * 对公网关查询转账信息
	 * @param strDate 交易时间
	 * @param oppNo 对方账户
	 * @param oppName 对方姓名
	 * @param amount 转账金额
	 * @param idNo 身份证号
	 * @return
	 * @throws Exception
	 */
	public List<EbankLogInfo> queryOnlineBankAmountService(String acctNo ,String dataTime ,String startTime ,String endTime  )throws Exception;

}
