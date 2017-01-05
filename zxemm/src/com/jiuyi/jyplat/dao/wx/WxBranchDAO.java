package com.jiuyi.jyplat.dao.wx;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.wx.WxBranch;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.DataTransfer;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;

/**
 * A data access object (DAO) providing persistence and search support for
 * WxBranch entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jiuyi.jyplat.entity.WxBranch
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WxBranchDAO extends HibernateEntityDao<WxBranch> {
	private static final Logger log = LoggerFactory
			.getLogger(WxBranchDAO.class);

	@Resource
	private DBAction dbAction;
	
	public void update(Integer id, String status) throws Exception{
		dbAction.executeUpdate("update wx_branch set status="+DataTransfer.toDB(status+"")+",updateTime="+DataTransfer.toDB(DateUtils.getCurDateTime())+",updateOperno="+DataTransfer.toDB(Utils.trim(SessionUtil.getOperator().getOperNo()))+" where id = "+id+"");
	}
	// property constants

	public void delete(WxBranch persistentInstance) {
		log.debug("deleting WxBranch instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WxBranch findById(WxBranch id) {
		log.debug("getting WxBranch instance with id: " + id);
		try {
			WxBranch instance = (WxBranch) getSession().get(
					"com.jiuyi.jyplat.entity.wx.WxBranch", id.getId());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WxBranch instance) {
		log.debug("finding WxBranch instance by example");
		try {
			List results = getSession()
					.createCriteria("com.jiuyi.jyplat.entity.wx.WxBranch")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding WxBranch instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WxBranch as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all WxBranch instances");
		try {
			String queryString = "from WxBranch";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WxBranch merge(WxBranch detachedInstance) {
		log.debug("merging WxBranch instance");
		try {
			WxBranch result = (WxBranch) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WxBranch instance) {
		log.debug("attaching dirty WxBranch instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WxBranch instance) {
		log.debug("attaching clean WxBranch instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public PageFinder<WxBranch> queryAllWxBranch(WxBranch wxbranch, String orderby ,com.jiuyi.jyplat.web.page.Query query){
		Criteria criteria = this.createCriteria();
		if(wxbranch != null){
			if(Utils.notEmptyString(wxbranch.getName())){
				criteria.add(Restrictions.like("name", "%"+wxbranch.getName().trim()+"%"));
			}
			if(Utils.notEmptyString(wxbranch.getCityName())){
				criteria.add(Restrictions.like("cityName", "%"+wxbranch.getCityName().trim()+"%"));
			}
			if(Utils.notEmptyString(wxbranch.getRegionName())){
				criteria.add(Restrictions.like("regionName", "%"+wxbranch.getRegionName().trim()+"%"));
			}
			if(Utils.notEmptyString(wxbranch.getAddress())){
				criteria.add(Restrictions.like("address", "%"+wxbranch.getAddress().trim()+"%"));
			}
			if(Utils.notEmptyString(wxbranch.getStatus())){
				criteria.add(Restrictions.eq("status", wxbranch.getStatus().trim()));
			}
			if(Utils.notEmptyString(wxbranch.getType())){
				criteria.add(Restrictions.eq("type", wxbranch.getType().trim()));
			}
			if(Utils.notEmptyString(wxbranch.getLocationCity())){
				String[] city = wxbranch.getLocationCity().split(",");
				criteria.add(Restrictions.in("locationCity",city));
			}
		}
		
		if("0".equalsIgnoreCase(orderby)){
			return this.pagedByCriteria(criteria, query.getPage(), query.getPageSize(),Order.desc("createTime")); 
		}else if("1".equalsIgnoreCase(orderby)){
			return this.pagedByCriteria(criteria, query.getPage(), query.getPageSize(),Order.desc("updateTime"));
		}else{
			return this.pagedByCriteria(criteria, query.getPage(), query.getPageSize(),Order.desc("id"));
		}
	}
	
	/**
	 * 搜索小区列表信息。
	 * @param name
	 * @return
	 */
	public List<WxBranch> search(String name) {
		Criteria criteria = this.createCriteria();
		if (Utils.notEmptyString(name)) {
			criteria.add(Restrictions.like("name", "%" + name.trim() + "%"));
		}
		return findByPage(criteria, 0, 40);
	}

	/**
	 * @param parseInt
	 */
	public void deleteById(int parseInt) {
		// TODO Auto-generated method stub
		String hql = "DELETE WxBranch where id = :id";
		org.hibernate.Query query = this.createQuery(hql);
		query.setInteger("id", parseInt);
		query.executeUpdate();
	}
}