package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.CondoBuyer;


/**
 * 购房人信息  Service 层  接口
 * @author wsf
 *
 */
public interface ICondoBuyerService {

	/**
	 * 根据地幢号查询购买人信息
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<CondoBuyer> getByHouseNo(String houseNo)throws Exception;
}
