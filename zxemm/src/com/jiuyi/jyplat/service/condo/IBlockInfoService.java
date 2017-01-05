package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.BlockInfo;

/***
 * 新房开发商信息Services
 * @author baizilin
 *
 */
public interface IBlockInfoService {
	
	
	/**
	 * 根据小区id查询小区信息
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	 public BlockInfo queryBlockInfoByBuilidId(String blockNo )throws Exception;
	 
	 
	 /**
	  * 保存小区信息
	  * @param blockInfo
	  * @throws Exception
	  */
	 public BlockInfo saveBlockInfo(BlockInfo blockInfo)throws Exception;

	 /**
	  * 根据合同编号查询 小区信息
	  * @param contactNo
	  * @return
	  * @throws Exception
	  */
	 public BlockInfo getBycontactNo(String contactNo)throws Exception;
	 
	 /***
	  * 查询所有的资金监管编号
	  */
	 public List<BlockInfo> queryAllInfo();
}
