package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.BuildingTemp;
import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class BuildingTempDao extends HibernateEntityDao<BuildingTemp> {

	/**
	 */
	public List<BuildingTemp> qurBuildingTemp(){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("status", "0"));

		@SuppressWarnings("unchecked")
		List<BuildingTemp> buildingTemps = criteria.list();
		return buildingTemps;
	}
}
