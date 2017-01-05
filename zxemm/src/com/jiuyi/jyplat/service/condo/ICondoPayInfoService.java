package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.CondoPayInfo;

public interface ICondoPayInfoService {
	
	/**
	 * 根据合同号查询付款信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public List<CondoPayInfo> queryCandoPayInfo(String contactNo)throws Exception;
	
	
	/**
	 * 根据合同号查询付款信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public List<CondoPayInfo> queryCandoPayInfoList(CondoPayInfo condoPayInfo)throws Exception;
	
	/**
	 * 支付成功保存支付信息
	 * @param condoPayInfo
	 * @return
	 * @throws Exception
	 */
	public Boolean savecondoPayInfoService(CondoPayInfo condoPayInfo)throws Exception;

	/**
	 * 根据地幢号查询所有的入账流水记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<CondoPayInfo> getByHouseNo(String houseNo)throws Exception;
	
	/**
	 * 新房资金监管总账对账查询
	 * @throws Exception
	 */
	public void queryAccount()throws Exception;
}
