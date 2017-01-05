package com.jiuyi.jyplat.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.jiuyi.jyplat.pojo.SqlKeyValue;
import com.jiuyi.jyplat.web.page.PageFinder;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * @see HibernateDaoSupport
 * @see GenericsUtils
 */

@SuppressWarnings("unchecked")
public abstract class HibernateEntityDao<T> extends HibernateDaoSupport {

	/**
	 * DAO�?��理的Entity类型.
	 */
	protected Class<T> entityClass;

	/**
	 * 本类�?��现的sessionFacotry，以区分父类的sessionFacotry
	 */
	@Resource
	private SessionFactory mySessionFacotry;

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	public HibernateEntityDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果�?
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	@Resource
	public void setMySessionFacotry(SessionFactory sessionFacotry) {
		this.mySessionFacotry = sessionFacotry;
	}

	/**
	 * 使用注释之后的PO对象�?��将父类HibernateDaoSupport的构造函数重�?
	 */
	@PostConstruct
	public void injectSessionFactory() {
		super.setSessionFactory(mySessionFacotry);
	}

	/**
	 * 保存对象.
	 */
	public T save(T o) {
		//		// 根据对象的注解判断此对象是否�?��建立索引
		//		if (o.getClass().getAnnotation(Indexed.class) != null) {
		//			// 使用封装全文搜索引擎后的session进行数据库操�?
		//			FullTextSession fullTextSession = getFullTextSession();
		//			fullTextSession.saveOrUpdate(o);
		//		} else {
		getHibernateTemplate().save(o);
		//		}
		return o;
	}

	/**
	 * 修改对象.
	 */
	public T update(T o) {
		getHibernateTemplate().update(o);
		return o;
	}

	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
	 */
	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 获取全部对象
	 */
	public List<T> loadAll() {
		getHibernateTemplate().setCacheQueries(true);
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 获取全部对象,带排序字段与升降序参�?
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> getAll(String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
	}

	/**
	 * 删除对象.
	 */
	public T remove(T o) {
		//		// 根据对象的注解判断此对象是否�?��建立索引
		//		if (o.getClass().getAnnotation(Indexed.class) != null) {
		//			// 使用封装全文搜索引擎后的session进行数据库操�?
		//			FullTextSession fullTextSession = getFullTextSession();
		//			fullTextSession.delete(o);
		//		} else {
		getHibernateTemplate().delete(o);
		//		}
		return o;
	}

	/**
	 * 根据ID移除对象.
	 */
	public T removeById(Serializable id) {
		return remove(getById(id));
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 取得Entity的Criteria. 可变的Restrictions条件列表,�?
	 * {@link #createQuery(String,Object...)}
	 * 
	 * @param criterions
	 * @return
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 根据属�?名和属�?值查询对�?
	 * 
	 * @return 符合条件的对象列�?
	 * @see HibernateGenericDao#findBy(Class,String,Object)
	 */
	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		List<T> a = null;
		try {
			a = createCriteria(Restrictions.eq(propertyName, value)).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 根据属�?名和属�?值查询对�?带排序参�?
	 * 
	 * @return 符合条件的对象列�?
	 */
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * 根据属�?名和属�?值查询对�?带排序参�?
	 * 
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @param limit
	 * @return
	 */
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc, int limit) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(limit).list();
	}

	/**
	 * 不带属�?查询对象,带排序参数和�?��返回�?
	 * 
	 * @return 符合条件的对象列�?
	 */
	public List<T> findBy(String orderBy, boolean isAsc, int limit) {
		Assert.hasText(orderBy);
		return createCriteria(orderBy, isAsc).setMaxResults(limit).setCacheable(true).list();
	}

	/**
	 * 根据属�?名和属�?值查询单个对�?
	 * 
	 * @return 符合条件的唯�?���?or null
	 */
	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 判断对象某些属�?的�?在数据库中唯�?
	 * 
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属�?列表,以�?号分�?�?name,loginid,password"
	 * @see HibernateGenericDao#isUnique(Class,Object,String)
	 */
	public boolean isUnique(Object entity, String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = createCriteria().setProjection(Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			// 循环加入唯一�?
			for (String name : nameList) {
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));
			}
			// 以下代码为了如果是update的情�?排除entity自身.
			String idName = getIdName();
			// 取得entity的主键�?
			Serializable id = getId(entity);

			// 如果id!=null,说明对象已存�?该操作为update,加入排除自身的判�?
			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (Integer) criteria.uniqueResult() == 0;
	}

	/**
	 * 消除�?Hibernate Session 的关�?
	 * 
	 * @param entity
	 */
	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	public void evict(String collection, Serializable id) {
		getSessionFactory().evictCollection(collection, id);
	}

	/**
	 * 取得Entity的Criteria对象，带排序字段与升降序字段.
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数,不推荐使�?
	 * 
	 * @param values
	 *            可变参数,见{@link #createQuery(String,Object...)}
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}

	/**
	 * 根据外置命名查询
	 * 
	 * @param queryName
	 * @param values
	 *            参数值列�?
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findByNameQuery(String queryName, Object... values) {
		return findByNameQuery(true, queryName, values);
	}

	@SuppressWarnings("rawtypes")
	public List findByNameQuery(boolean isCache, String queryName, Object... values) {
		Assert.hasText(queryName);
		getHibernateTemplate().setCacheQueries(isCache);
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public void setCacheQueries(boolean isCache) {
		getHibernateTemplate().setCacheQueries(isCache);
	}

	/**
	 * 根据外置命名查询
	 * 
	 * @param queryName
	 * @param limit
	 *            记录�?��返回�?
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findByNameQuery(int limit, String queryName, Object... values) {
		return findByNameQuery(limit, true, queryName, values);
	}

	@SuppressWarnings("rawtypes")
	public List findByNameQuery(int limit, boolean isCache, String queryName, Object... values) {
		Query queryObject = getSession().getNamedQuery(queryName).setMaxResults(limit).setCacheable(isCache);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}

	/**
	 * 创建Query对象.
	 * 对于�?��first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设�?
	 * 留意可以连续设置,如下�?
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下�?
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 */
	public Query createQuery(String hql, Object... values) {
		//Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if (null != values && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Query createSQLQuery(String sql, Map<String, String> map) {
		Query query = getSession().createSQLQuery(sql);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	public Query createSQLQuery(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query;
	}

	/**
	 * 按游离hibernate标准查询器进行分页查�?
	 * 
	 * @param pagination
	 * @return
	 */
	public PageFinder<T> pagedByDetachedCriteria(DetachedCriteria detachedCriteria, int pageNo, int pageSize) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
		return pagedByCriteria(criteria, pageNo, pageSize);
	}

	/**
	 * 按hibernate标准查询器进行分页查�?
	 * 当数据存在一对多时，切立即加载的情况下，如果用此分页则查出来的分页数据会不完整！
	 * @param pagination
	 * @return
	 */
	@Deprecated
	public PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize) {
		Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		if (totalRows.intValue() < 1) {
			return new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
		} else {
			PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
			List<T> list = criteria.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			finder.setData(list);
			return finder;
		}
	}

	/**
	 * 按hibernate标准查询器进行分页查�?
	 * 当数据存在一对多时，切立即加载的情况下，如果用此分页则查出来的分页数据会不完整！
	 * @param pagination
	 * @return
	 */
	@Deprecated
	public PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize, Order order) {
		Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		criteria.addOrder(order);
		if (totalRows.intValue() < 1) {
			return new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
		} else {
			PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
			List<T> list = criteria.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			finder.setData(list);
			return finder;
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<T> findByPage(Criteria criteria, int offset, int pageSize) {

		return criteria.setFirstResult(offset).setMaxResults(pageSize)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * 使用搜索引擎进行分页查询
	 * 
	 * @param criteria
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//	public PageFinder<T> pagedBySearcher(org.apache.lucene.search.Query luceneQuery, int pageNo, int pageSize) {
	//		FullTextSession s = getFullTextSession();
	//		FullTextQuery query = s.createFullTextQuery(luceneQuery, entityClass);
	//		int totalRows = query.getResultSize();
	//		if (totalRows < 1) {
	//			return new PageFinder(pageNo, pageSize, totalRows);
	//		} else {
	//			PageFinder finder = new PageFinder(pageNo, pageSize, totalRows);
	//			query.setMaxResults(pageSize);
	//			query.setFirstResult(finder.getStartOfPage());
	//			List<T> list = query.list();
	//			finder.setData(list);
	//			return finder;
	//		}
	//	}

	/**
	 * 分页实体,按分页对象类型调用相应的分页实现
	 * 
	 * @param pagination
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageFinder pagedEntity(int toPage, int pageSize) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		return pagedByCriteria(criteria, toPage, pageSize);
	}

	/**
	 * 按HQL方式进行分页查询
	 * 
	 * @param toPage
	 *            跳转页号
	 * @param pageSize
	 *            每页数量
	 * @param hql
	 *            查询语句
	 * @param values
	 *            参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageFinder pagedByHQL(String hql, int toPage, int pageSize, Object... values) {
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		Long totalCount = (Long) countlist.get(0);
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount.intValue());
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount.intValue());
			Query query = createQuery(hql, values);
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;
		}

	}

	@SuppressWarnings("rawtypes")
	public PageFinder pagedBySQL(String hql, String countField, int toPage, int pageSize, Map<String, String> params) {
		String countQueryString = "";

		if (countField != null && !countField.trim().equals(""))
			countQueryString = " select count (" + countField + ") " + removeSelect(removeOrders(hql));
		else
			countQueryString = " select count (*) " + removeSelect(removeOrders(hql));

		List countlist = createSQLQuery(countQueryString, params).list();
		//Integer totalCount = (Integer) countlist.get(0);
		Integer totalCount = Integer.parseInt(countlist.get(0).toString());
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount);
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount);
			Query query = createSQLQuery(hql, params);
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;
		}

	}

	public PageFinder pagedByGroupSQL(String hql, String countField, int toPage, int pageSize,
			Map<String, String> params) {
		String countQueryString = "SELECT COUNT(*) FROM (" + hql + ")";
		List countlist = createSQLQuery(countQueryString, params).list();
		//Integer totalCount = (Integer) countlist.get(0);
		Integer totalCount = Integer.parseInt(countlist.get(0).toString());
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount);
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount);
			Query query = createSQLQuery(hql, params);
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;
		}

	}

	@SuppressWarnings("rawtypes")
	public PageFinder pagedBySQLFS(String hql, String countField, int toPage, int pageSize, Map<String, String> params) {
		String countQueryString = "";

		if (countField != null && !countField.trim().equals(""))
			countQueryString = " select count (" + countField + ") from (" + hql + ")";
		else
			countQueryString = " select count (" + countField + ") from (" + hql + ")";

		List countlist = createSQLQuery(countQueryString, params).list();
		//Integer totalCount = (Integer) countlist.get(0);
		Integer totalCount = Integer.parseInt(countlist.get(0).toString());
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount);
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount);
			Query query = createSQLQuery(hql, params);
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;
		}

	}

	@SuppressWarnings("rawtypes")
	public PageFinder pagedBySQL(String hql, String countField, String table, int toPage, int pageSize,
			Map<String, String> params) {
		String countQueryString = "";

		if (countField != null && !countField.trim().equals(""))
			countQueryString = " select count (" + countField + ")  from " + table;
		else
			countQueryString = " select count (*) from " + table;
		List countlist = createSQLQuery(countQueryString, params).list();
		//Integer totalCount = (Integer) countlist.get(0);
		Integer totalCount = Integer.parseInt(countlist.get(0).toString());
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount);
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount);
			Query query = createSQLQuery(hql, params);
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;

		}
	}

	@SuppressWarnings("rawtypes")
	public PageFinder pagedBySQL(String hql, String countField, int toPage, int pageSize, Map<String, String> params,
			List<SqlKeyValue> classList) {
		String countQueryString = "";

		if (countField != null && !countField.trim().equals(""))
			countQueryString = " select count (" + countField + ") " + removeSelect(removeOrders(hql));
		else
			countQueryString = " select count (*) " + removeSelect(removeOrders(hql));

		List countlist = createSQLQuery(countQueryString, params).list();
		//Integer totalCount = (Integer) countlist.get(0);
		Integer totalCount = Integer.parseInt(countlist.get(0).toString());
		if (totalCount.intValue() < 1) {
			return new PageFinder(toPage, pageSize, totalCount);
		} else {
			PageFinder finder = new PageFinder(toPage, pageSize, totalCount);
			SQLQuery query = (SQLQuery) createSQLQuery(hql, params);
			for (SqlKeyValue c : classList) {
				query.addEntity(c.getKey(), c.getClassValue());
			}
			List list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize()).list();
			finder.setData(list);
			return finder;
		}

	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
	 * 
	 * @param pageNo
	 *            页号,�?�?��.
	 * @return 含�?记录数和当前页数据的Page对象.
	 */
	public PageFinder<T> pagedFinder(int pageNo, int pageSize, Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		return pagedByCriteria(criteria, pageNo, pageSize);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参�?排序参数创建默认�?code>Criteria</code>.
	 * 
	 * @param pageNo
	 *            页号,�?�?��.
	 * @return 含�?记录数和当前页数据的Page对象.
	 */
	@SuppressWarnings("rawtypes")
	public PageFinder pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(orderBy, isAsc, criterions);
		return pagedByCriteria(criteria, pageNo, pageSize);
	}

	/**
	 * 取得对象的主键�?,辅助函数.
	 */
	public Serializable getId(Object entity) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		return (Serializable) PropertyUtils.getProperty(entity, getIdName());
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, entityClass.getSimpleName() + " has no identifier property define.");
		return idName;
	}

	/**
	 * 去除hql的select 子句，未考虑union的情�?用于pagedQuery.
	 * 
	 * @param hql
	 * @return
	 */
	protected final static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @param hql
	 * @return
	 */
	protected final static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	protected final static String removeGroup(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 获得全文搜索引擎的查询会�?
	 * 
	 * @return
	 */
	//	protected FullTextSession getFullTextSession() {
	//		return Search.getFullTextSession(getSession());
	//	}

	/***
	 * 获得总记录条�?
	 */
	public int getRowCount(Criteria criteria) {
		Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return totalRows;
	}
}
