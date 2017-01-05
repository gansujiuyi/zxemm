package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface IBuildingInfoService {
	
	/**
	 * 查询楼幢信息
	 * @param buildingId
	 * @param query
	 * @param operatorNo
	 * @return
	 * @throws Exception
	 */
	 public PageFinder<BuildingInfo> queryBuildingInfoAll(String buildingId,Query query)throws Exception;
	 
	 
	 /**
	  * 根据地幢号查询楼幢信息
	  * @param buildingId
	  * @return
	  * @throws Exception
	  */
	 public BuildingInfo queryBuildingInfoByBuilidId(String buildingId )throws Exception;
	 
	 /**
	  * 序列产生虚拟账号
	  * @return
	  * @throws Exception
	  */
	 public String genBuildingInfoId()throws Exception;
	 
	 /**
	  * 更新楼幢信息
	  * @throws Exception
	  */
	 public void updateBuildingInfo(BuildingInfo buildingInfo)throws Exception;
	 
	 /**
	  * 保存楼幢信息
	  * @throws Exception
	  */
	 public BuildingInfo saveBuildingIfo(BuildingInfo buildingInfo)throws Exception;
	 
	 /**
	  * 修改楼幢信息、新增新房开发商信息
	  * @param rspMsg
	  * @throws Exception
	  */
	 public void saveBuildingAndBlockInfo(GetEntInfoRspMsg rspMsg) throws Exception;
	 
	 /**
	  * 查询所有楼幢信息
	  * @return
	  * @throws Exception
	  */
	 public List<BuildingInfo> queryAllBuildInfo();

}
