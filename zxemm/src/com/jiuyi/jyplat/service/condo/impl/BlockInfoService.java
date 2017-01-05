package com.jiuyi.jyplat.service.condo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.BlockInfoDao;
import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.entity.condo.BlockInfo;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.service.condo.IBlockInfoService;

@Service("blockInfoService")
public class BlockInfoService implements IBlockInfoService {
	Logger log = Logger.getLogger(BlockInfoService.class);
	
	@Resource
	private BlockInfoDao blockInfoDao;
	
	@Resource
	private BuildingInfoDao buildingInfoDao;
	
	@Resource
	private ContactInfoDao contactInfoDao;

	@Override
	public BlockInfo queryBlockInfoByBuilidId(String blockNo)
			throws Exception {
		// TODO Auto-generated method stub
		
		BlockInfo blockInfo=new BlockInfo();
		 if(blockNo!=null){
			 blockInfo=blockInfoDao.findUniqueBy("blockNo", blockNo);
		 }
		return blockInfo;
	}

	@Override
	public BlockInfo saveBlockInfo(BlockInfo blockInfo) throws Exception {
		// TODO Auto-generated method stub]
		BlockInfo tmp=blockInfoDao.findUniqueBy("blockNo", blockInfo.getBlockNo());
		if(null!=tmp){
			return blockInfoDao.update(blockInfo);
		}else{
			return blockInfoDao.save(blockInfo);
		}
	}

	/**
	  * 根据合同编号查询 小区信息
	  * @param contactNo
	  * @return
	  * @throws Exception
	  */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public BlockInfo getBycontactNo(String contactNo) throws Exception {
		log.info("*********************************合同编号为："+contactNo);
		ContactInfo contactInfo = contactInfoDao.findUniqueBy("contactNo", contactNo);
		log.info("*********************************查询结果为："+contactInfo);
		if(null!=contactInfo && !"".equals(contactInfo.getHouseNo()) && null != contactInfo.getHouseNo()){
			BuildingInfo buildingInfo = buildingInfoDao.findUniqueBy("buildingId", contactInfo.getHouseNo().trim());
			if(null!=buildingInfo && !"".equals(buildingInfo.getBlockNo()) && null != buildingInfo.getBlockNo()){
				BlockInfo blockInfo = blockInfoDao.findUniqueBy("blockNo", buildingInfo.getBlockNo().trim());
				return blockInfo;
			}
		}
		return null;
	}

	@Override
	public List<BlockInfo> queryAllInfo() {
		Criteria criteria = blockInfoDao.createCriteria();
		return criteria.list();
	}
}
