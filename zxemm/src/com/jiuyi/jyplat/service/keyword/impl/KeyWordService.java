package com.jiuyi.jyplat.service.keyword.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.dao.keyword.KeyWordDao;
import com.jiuyi.jyplat.entity.keyword.KeyWord;
import com.jiuyi.jyplat.service.keyword.IKeyWordService;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.ObjectValueCopy;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service
public class KeyWordService implements IKeyWordService{
	@Resource
	private KeyWordDao kwDao;
	
	@Resource
	private LogInfoDao logDao;
	
	//日志文件对象
	Logger log = Logger.getLogger(KeyWordService.class);
	
	/**
	 * 分页查询所有的关键字
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<KeyWord> queryKeyWord(KeyWord obj, Query query) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from keyword where 1=1 ");
		Map params = new HashMap();
		if (obj != null)
		{
			if (null != obj.getId())
			{
				sb.append(" and id = ").append(obj.getId());
			}
			if (StringUtils.isNotEmpty(obj.getKey().trim()))
			{
				sb.append(" and key like '%" + obj.getKey().trim() + "%'");
			}
			if (StringUtils.isNotEmpty(obj.getValue().trim()))
			{
				sb.append(" and value like '%" + obj.getValue().trim() + "%'");
			}
		}
		sb.append(" order by id desc ");
		return kwDao.pagedBySQL(sb.toString(), "1", query.getPage(), query.getPageSize(), params);
	}
	
	/**
	 * 删除关键字
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delKeyWord(String ids) throws Exception
	{
		if (StringUtils.isEmpty(ids))
		{
			throw new Exception("关键字Id为空！");
		}
		String[] idArry = ids.trim().split(",");
		KeyWord kw = null;
		for (String id : idArry)
		{
			kw = kwDao.getById(Integer.parseInt(id));
			if (null != kw)
			{
				kwDao.remove(kw);
				log.info("关键字删除成功！Id：" + id.trim());
				logDao.addLog(OperConstant.ACT_DEL, OperConstant.ACT_DEL, OperConstant.ACT_DEL, "关键字Id：" + id.trim());
			}
		}
	}
	
	/**
	 * 查询关键字
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public KeyWord queryKeyWordById(Integer id) throws Exception
	{
		if (id == null)
		{
			throw new Exception("Id为空！");
		}
		KeyWord kw = null;
		Criteria criteria = kwDao.createCriteria();
		criteria.add(Restrictions.eq("id", id));
		List list = criteria.list();
		if (null != list && list.size() > 0)
		{
			kw = (KeyWord) list.get(0);
		}
		return kw;
	}
	
	/**
	 * 查询关键字
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<KeyWord> queryKeyWord(KeyWord obj) throws Exception
	{
		Criteria criteria = kwDao.createCriteria();
		if (null != obj && null != obj.getId())
		{
			criteria.add(Restrictions.eq("id", obj.getId()));
		}
		if (null != obj && StringUtils.isNotEmpty(obj.getKey()))
		{
			criteria.add(Restrictions.eq("key", obj.getKey()));
		}
		if (null != obj && StringUtils.isNotEmpty(obj.getValue()))
		{
			criteria.add(Restrictions.eq("value", obj.getValue()));
		}
		List<KeyWord> list = criteria.list();
		if (null != list && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	/**
	 * 查询所有的关键字
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<KeyWord> queryAllKeyWord() throws Exception
	{
		Criteria criteria = kwDao.createCriteria();
		List<KeyWord> list = criteria.list();
		return list;
	}
	
	/**
	 * 按key查询关键字
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public KeyWord queryKeyWord(String key) throws Exception
	{
		List datas = kwDao.findBy("key", key);
		if(null != datas && datas.size() > 0)
		{
			return (KeyWord)datas.get(0);
		}
		return null;
	}
	
	
	/**
	 * 新增关键字
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveKeyWord(KeyWord obj) throws Exception
	{
		if (obj == null)
		{
			throw new Exception("关键字数据为空！");
		}
		obj.setCreatetime(DateUtils.getCurDateTime());
		kwDao.save(obj);
		log.info("新增关键字成功！");
		logDao.addLog(OperConstant.ACT_ADD, OperConstant.ACT_ADD, OperConstant.ACT_ADD, "");
	}
	
	/**
	 * 修改关键字
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateKeyWord(KeyWord obj) throws Exception
	{
		KeyWord kwObj = queryKeyWordById(obj.getId());
		ObjectValueCopy.copyObject(obj, kwObj);
		kwDao.update(kwObj);
		log.info("修改关键字成功！");
		logDao.addLog(OperConstant.ACT_EDIT, OperConstant.ACT_EDIT, OperConstant.ACT_EDIT, "关键字Id：" + obj.getId());
	}
}
