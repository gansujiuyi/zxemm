package com.jiuyi.jyplat.service.condo.impl;

import java.util.List;

import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.jyplat.entity.condo.BuildingTemp;


public interface IBuildingTempService {
	
	/**
	 * 保存虚拟账户信息
	 * @param buildingTemp
	 * @throws Exception
	 */
	public BuildingTemp saveBuildingTemp(BuildingTemp buildingTemp)throws Exception;
	
	/**
	 * 查询所有没有查询过企业信息的数据
	 * @throws Exception
	 */
	public List<BuildingTemp> getBuildingTemp()throws Exception;
	
	/**
	 * 更新企业信息的数据
	 * @throws Exception
	 */
	public void updateBildingTemp(BuildingTemp buildingTemp)throws Exception;
	
	/**
	 * 根据地幢号保存信息
	 * @param rspMsg
	 * @param houseNo
	 * @throws Exception
	 */
	public void saveEntInfo(GetEntInfoRspMsg rspMsg,BuildingTemp buildingTemp, String houseNo)throws Exception;
	

}
