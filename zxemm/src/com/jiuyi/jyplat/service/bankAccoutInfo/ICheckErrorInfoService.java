package com.jiuyi.jyplat.service.bankAccoutInfo;

import com.jiuyi.jyplat.entity.bankAccoutInfo.CheckErrorInfo;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface ICheckErrorInfoService {
	
	
	/**
	 * 分页查询异常统计信息
	 * @param contactInfo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<CheckErrorInfo> CheckErrorInfo(String buildId , String  transactionSeqNum, Query query) throws Exception;
	
	
	
	public void saveCheckInfo();

}
