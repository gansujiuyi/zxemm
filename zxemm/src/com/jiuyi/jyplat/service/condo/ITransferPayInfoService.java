package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.TransferPayInfo;

/**
 * 购房划款流水类  Service层接口
 * @author wsf
 *
 */
public interface ITransferPayInfoService {

	/**
	 * 根据订单编号查询订单流水集合
	 * @param auditTransferMoneyNo
	 * @return
	 * @throws Exception
	 */
	public List<TransferPayInfo> getByTransNo(String auditTransferMoneyNo)throws Exception;
	
	/**
	 * 新增审批划款流水
	 * @param transferPayInfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveTransferPayInfo(TransferPayInfo transferPayInfo)throws Exception;
	
	/**
	 * 根据地幢号查询所有的出账流水记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<TransferPayInfo> getByHouseNo(String houseNo)throws Exception; 
}
