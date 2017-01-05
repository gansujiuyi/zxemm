package com.jiuyi.jyplat.dao.condo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class BuildingInfoDao extends HibernateEntityDao<BuildingInfo> {

	/**
	 * 生成订单ID。 当前时间8位+数据库中的4位序列
	 * 
	 * @return
	 * @throws Exception
	 */
	public String genBuildingInfoId() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		Query query = createSQLQuery("select s_genBuildingInfo.nextval from dual");
		BigDecimal id = (BigDecimal) query.uniqueResult();
		String buildingInfoId = date + new DecimalFormat("000").format(id);
		return buildingInfoId;
	}

	public List<BuildingInfo> qurBuildingInfo(String houseNo) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("buildingId", houseNo));

		@SuppressWarnings("unchecked")
		List<BuildingInfo> buildingInfos = criteria.list();
		return buildingInfos;
	}

	public List<BuildingInfo> qurBuildingInfoByBlockNo(String blockNo) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("blockNo", blockNo));

		@SuppressWarnings("unchecked")
		List<BuildingInfo> buildingInfos = criteria.list();
		return buildingInfos;
	}
}