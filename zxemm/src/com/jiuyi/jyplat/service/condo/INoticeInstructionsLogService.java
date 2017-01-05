package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;

/**
 *  新房指令通知日志表  Service 层接口
 * @author wsf
 *
 */
public interface INoticeInstructionsLogService {

	/**
	 * 新增 NoticeInstructionsLog 新房指令通知日志及其他指令对应的记录
	 * @param instructionsLog
	 * @throws Exception
	 */
	public void saveNoticeInstructionsLog(NoticeInstructionsLog instructionsLog)throws Exception;
	
	/**
	 * 根据地幢号指令种类查询房管局通知信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public List<NoticeInstructionsLog> queNoticeLogByHouseNo(String houseNo,String instructionsVariety)throws Exception;
}
