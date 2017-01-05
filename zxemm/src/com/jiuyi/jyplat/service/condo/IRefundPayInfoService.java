package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.RefundPayInfo;

/**
 *  购房退款流水类 Service层接口
 * @author wsf
 *
 */
public interface IRefundPayInfoService {
	
	/**
	 * 根据合同编号查询退款记录
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public List<RefundPayInfo> getByContactNo(String contactNo)throws Exception;
	
	/**
	 *保存退款流水
	 * @param refundPayInfo
	 * @return
	 * @throws Exception
	 */
	public Boolean saveRefundPayInfo(RefundPayInfo refundPayInfo)throws Exception;
	
	
	/**
	 * 根据地幢号查询退款信息
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<RefundPayInfo> getByHouseNo(String houseNo)throws Exception;
}
