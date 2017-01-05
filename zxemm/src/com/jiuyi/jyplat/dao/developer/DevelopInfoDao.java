package com.jiuyi.jyplat.dao.developer;

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
import com.jiuyi.jyplat.entity.condo.DevelopInfo;
import com.jiuyi.jyplat.util.HibernateEntityDao;


@Repository("developInfoDao")
public class DevelopInfoDao extends HibernateEntityDao<DevelopInfo> {
	
	public String genDevelopName(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());

		Query query = createSQLQuery("select S_DEVELOP.nextval from dual");
		BigDecimal id = (BigDecimal) query.uniqueResult();
		String str = date + new DecimalFormat("00").format(id); // 增加房产局要求字符前缀
		
		return str;
	}
	
	public DevelopInfo genDevelopByBuildId(String buildingId){
		DevelopInfo def=new DevelopInfo();
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("buildingId", buildingId));

		@SuppressWarnings("unchecked")
		List<DevelopInfo> developInfos = criteria.list();
		if(developInfos!=null && developInfos.size()>0){
			def=developInfos.get(0);
		}
		return def;
	}

}
