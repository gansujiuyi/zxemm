package com.jiuyi.jyplat.dao.condo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;
import com.jiuyi.jyplat.util.HibernateEntityDao;
/**
 *  新房指令通知日志表 Dao层
 * @author wsf
 *
 */
@Repository("instructionsLogDao")
public class NoticeInstructionsLogDao extends HibernateEntityDao<NoticeInstructionsLog> {
	
	
	
	
	/**
	 * 根据地幢号指令种类查询房管局通知信息
	 * @param goodsId
	 * @return
	 */
	public List<NoticeInstructionsLog> queNoticeLogByHouseNo(String houseNo,String instructionsVariety){
		if (StringUtils.isBlank(houseNo)) {
			return null;
		}
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("houseNo", houseNo));
		criteria.add(Restrictions.eq("instructionsVariety", instructionsVariety));

		@SuppressWarnings("unchecked")
		List<NoticeInstructionsLog> noticeInstructionsLog = criteria.list();
		return noticeInstructionsLog;
	}

}
