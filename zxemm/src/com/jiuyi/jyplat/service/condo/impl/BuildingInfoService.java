package com.jiuyi.jyplat.service.condo.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.BlockInfoDao;
import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.service.condo.IBuildingInfoService;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.net.message.condo.GetEntInfoRspMsg;

@Service("buildingInfoService")
public class BuildingInfoService implements IBuildingInfoService {
	Logger log = Logger.getLogger(BuildingInfoService.class);

	@Resource
	private BuildingInfoDao buildingInfoDao;

	@Resource
	private BlockInfoDao blockInfoDao;

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<BuildingInfo> queryBuildingInfoAll(String buildingId,
			Query query) throws Exception {
		// TODO Auto-generated method stub

		Criteria criteria = buildingInfoDao.createCriteria();

		// 订单状态
		if (!"".equals(buildingId) && buildingId != null) {
			criteria.add(Restrictions.eq("buildingId", buildingId));
		}
		return buildingInfoDao.pagedByCriteria(criteria, query.getPage(),
				query.getPageSize());
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BuildingInfo queryBuildingInfoByBuilidId(String buildingId)
			throws Exception {
		// TODO Auto-generated method stub
		BuildingInfo buildingInfo = new BuildingInfo();
		if (buildingId != null) {
			buildingInfo = buildingInfoDao.findUniqueBy("buildingId",
					buildingId);
		}

		return buildingInfo;
	}

	@Override
	public String genBuildingInfoId() throws Exception {
		// TODO Auto-generated method stub
		String regulateAccount = "";
		regulateAccount = buildingInfoDao.genBuildingInfoId();
		return regulateAccount;
	}

	@Override
	public void updateBuildingInfo(BuildingInfo buildingInfo) throws Exception {
		// TODO Auto-generated method stub
		buildingInfoDao.update(buildingInfo);

	}

	@Override
	public BuildingInfo saveBuildingIfo(BuildingInfo buildingInfo) throws Exception {
		// TODO Auto-generated method stub
		BuildingInfo tmp = buildingInfoDao.findUniqueBy("buildingId",buildingInfo.getBuildingId());
		if(null==tmp){
			return buildingInfoDao.save(buildingInfo);
		}else{
			buildingInfo.setId(tmp.getId());
			return buildingInfoDao.getHibernateTemplate().merge(buildingInfo);
		}
	}

	/**
	 * 修改楼幢信息、新增新房开发商信息
	 * 
	 * @param rspMsg
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBuildingAndBlockInfo(GetEntInfoRspMsg rspMsg)
			throws Exception {
		BuildingInfo buildingInfo = buildingInfoDao.findUniqueBy("buildingId",
				rspMsg.getBuilding());
		if (null == buildingInfo) {
			buildingInfo = new BuildingInfo();
		}
		buildingInfo.setProjetName(rspMsg.getProjectname());
		buildingInfo.setCompanyName(rspMsg.getCompanyname());
		buildingInfo.setProjectAdd(rspMsg.getProjectaddress());
		buildingInfo.setCoveredArea(rspMsg.getConstructarea());
		buildingInfo.setCompanyId(rspMsg.getPbicompcode());
		buildingInfo.setBuildingId(rspMsg.getBuilding());
		buildingInfo.setStr2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime()));
		if (null == buildingInfo.getId() || "".equals(buildingInfo.getId())) {
			buildingInfoDao.save(buildingInfo);
		} else {
			buildingInfoDao.update(buildingInfo);
		}
	}

	@Override
	public List<BuildingInfo> queryAllBuildInfo(){
		Criteria criteria = buildingInfoDao.createCriteria();
		return criteria.list();
	}

}
