package com.jiuyi.jyplat.dao.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.base.Syscontent;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.util.DataTransfer;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;

@Repository
public class SyscontentDao extends HibernateEntityDao<Syscontent> {

	public void updateSyscont( Syscontent syscont, String actImg)throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append("UPDATE Syscontent").append(" SET Status = :status,ModifyTime = ").append(DataTransfer.toDB(DateUtils.dateToDateString(new Date(), DateUtils.DEFAULT_TIME)));
		hql.append(",ModifyBy = ").append( DataTransfer.toDB(SessionUtil.getOperator().getOperNo()) );
		if( Utils.notEmptyString(syscont.getContname()) )
			hql.append(",contname = :contName");
		if(syscont.getSequence() != null && syscont.getSequence() != 0)
			hql.append(",sequence =:sequence");
		//add guys 20141105 限时抢购标题，系统内容描述
		if( Utils.notEmptyString(syscont.getDescription()) ){
			hql.append(",Description = :description");
		}else{
			if(!"0004".equals(syscont.getStatus())&&!"0005".equals(syscont.getStatus())){
				hql.append(",Description = :description");
			}
		}
			
		if(Utils.notEmptyString(syscont.getConttype()))
			hql.append(",conttype =:contType");
		if( Utils.notEmptyString(syscont.getStarttime()) )
			hql.append(",starttime = :startTime");
		if( Utils.notEmptyString(syscont.getEndtime()) )
			hql.append(",endtime = :endTime");
		if( Utils.notEmptyString(actImg) )
			hql.append(",contimg = :contImg");
		if( Utils.notEmptyString( syscont.getHtmldetail() ) )
			hql.append(",htmldetail = :htmlDetail");
    		
    	if(StringUtils.isNotEmpty(syscont.getViewType()) )
    		hql.append(",viewType = :viewType");
    	
    	hql.append(" WHERE contid = ").append( syscont.getContid() );
    	
    	Query query = createQuery(hql.toString());
		if( Utils.notEmptyString(syscont.getContname()) )
			query.setString("contName", syscont.getContname());
		if( syscont.getSequence() != null && syscont.getSequence() != 0 )
			query.setInteger("sequence", syscont.getSequence());
		//add guys 20141105 限时抢购标题，系统内容描述
		if( Utils.notEmptyString(syscont.getDescription()) ){
			query.setString("description", syscont.getDescription());
		}else{
			if(!"0004".equals(syscont.getStatus())&&!"0005".equals(syscont.getStatus())){
				query.setString("description", syscont.getDescription());
			}
			
		}
			
		if(Utils.notEmptyString(syscont.getConttype()))
			query.setString("contType", syscont.getConttype());
		if( Utils.notEmptyString(syscont.getStarttime()) )
			query.setString("startTime", syscont.getStarttime());
		if( Utils.notEmptyString(syscont.getEndtime()) )
			query.setString("endTime", syscont.getEndtime());
		if( Utils.notEmptyString( syscont.getHtmldetail() ) )
			query.setString("htmlDetail", syscont.getHtmldetail());
		if( Utils.notEmptyString(actImg) )
			query.setString("contImg", actImg);
    		
    	if(StringUtils.isNotEmpty(syscont.getViewType()) )
    		query.setString("viewType", syscont.getViewType());
		// 必输入
		query.setString("status", syscont.getStatus());
		
		query.executeUpdate();
    	
	}
	
	@SuppressWarnings("unchecked")
	public List<Syscontent> querySys(String conttype,String cityid){
		List<Syscontent> syscontents=new ArrayList<Syscontent>();
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("conttype", conttype));
		criteria.add(Restrictions.eq("viewType", cityid));

		syscontents = criteria.list();
		return syscontents;
		
	}
}
