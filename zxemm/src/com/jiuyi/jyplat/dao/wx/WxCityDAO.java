package com.jiuyi.jyplat.dao.wx;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.wx.WxCity;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class WxCityDAO extends HibernateEntityDao<WxCity>{
	private static final Logger log = LoggerFactory
			.getLogger(WxCityDAO.class);
	
	
}
