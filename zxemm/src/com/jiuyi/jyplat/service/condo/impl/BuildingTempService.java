package com.jiuyi.jyplat.service.condo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.dao.condo.BlockInfoDao;
import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.dao.condo.BuildingTempDao;
import com.jiuyi.jyplat.dao.developer.DevelopInfoDao;
import com.jiuyi.jyplat.entity.condo.BlockInfo;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.BuildingTemp;
import com.jiuyi.jyplat.entity.condo.DevelopInfo;
import com.jiuyi.net.message.condo.GetEntInfoRspMsg;

@Service("buildingTempService")
public class BuildingTempService implements IBuildingTempService {

	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private BuildingTempDao buildingTempDao;
	@Resource
	private BlockInfoDao blockInfoDao;
	@Resource
	private BuildingInfoDao buildingInfoDao;

	@Resource
	private DevelopInfoDao developInfoDao;

	@Override
	public BuildingTemp saveBuildingTemp(BuildingTemp buildingTemp) throws Exception {
		// TODO Auto-generated method stub
		//根据buildingid 获取temp信息
		BuildingTemp temp =buildingTempDao.getHibernateTemplate().merge(buildingTemp);
		return temp;
	}

	@Override
	public List<BuildingTemp> getBuildingTemp() throws Exception {
		return buildingTempDao.qurBuildingTemp();

	}

	@Override
	public void updateBildingTemp(BuildingTemp buildingTemp) throws Exception {
		buildingTempDao.update(buildingTemp);
	}

	@Override
	public void saveEntInfo(GetEntInfoRspMsg rspMsg, BuildingTemp buildingTemp,
			String houseNo) throws Exception {
		List<BuildingInfo> bis = buildingInfoDao.qurBuildingInfo(houseNo);
		if (bis != null && bis.size() == 1) {
			BuildingInfo buildingInfo = bis.get(0);
			buildingInfo.setBlockNo(rspMsg.getPbicode());
			buildingInfo.setCompanyId(rspMsg.getPbicompcode());
			buildingInfo.setStatus("1001");// 修改为已监管
			buildingInfoDao.update(buildingInfo);

			BlockInfo bi = new BlockInfo();
			BlockInfo  temp = blockInfoDao.findUniqueBy("blockNo", rspMsg.getPbicode());
			if(null==temp){
				bi.setBlockNo(rspMsg.getPbicode());
				bi.setFundName(buildingTemp.getProjetReguName());
				bi.setFundNo(buildingTemp.getProjectReguNo());
				bi.setProjectName(rspMsg.getProjectname());
				blockInfoDao.save(bi);
			}
			

			buildingTemp.setStatus("1");
			buildingTempDao.update(buildingTemp);

			DevelopInfo de = new DevelopInfo();
			//查询开发商信息
			de = developInfoDao.genDevelopByBuildId(buildingTemp.getBuildingId());
			if(null!= de && null!=de.getId()){
				developInfoDao.update(de);
			}else{
				de.setBlockInfoId(rspMsg.getPbicode());
				de.setBuildingId(houseNo);
				developInfoDao.save(de);
			}
			
		} else {
			log.error("地幢号为：" + houseNo + " 没有开户记录！！！！！！！！！！！！");
		}
	}
	
}