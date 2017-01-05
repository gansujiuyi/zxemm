package com.jiuyi.jyplat.dao.transport;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.transport.PayTransport;
import com.jiuyi.jyplat.entity.transport.PayTransportEnum;
import com.jiuyi.jyplat.entity.transport.SearchTransport;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.web.page.PageFinder;

@Repository
public class PayTransportDao extends HibernateEntityDao<PayTransport> {
	private Logger log = Logger.getLogger(PayTransportDao.class);
	/**
	 * 定时任务查询划款记录
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<PayTransport> queryPayRecord(){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("status", PayTransportEnum.status_begin.toString()));

		@SuppressWarnings("unchecked")
		List<PayTransport> trans = criteria.list();	
		return trans;
	}

	/**
	 * 根据orderId查询出对应支付信息。
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<PayTransport> getByOrderId(String orderId, String payType) throws Exception{
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("orderId", orderId));
		criteria.add(Restrictions.eq("payType", payType));

		@SuppressWarnings("unchecked")
		List<PayTransport> trans = criteria.list();		
		log.info("查询订单【" + orderId + "】对应的流水成功！");
		return trans;
	}

	public PageFinder<PayTransport> qurTransportInfo(SearchTransport search,Integer pageNow, Integer pageSize)  throws Exception{
		Criteria criteria = createCriteria();
		criteria.add(Expression.like("orderId","L%")); 
   	    criteria.list();
		if(StringUtils.isNotBlank(search.getRealName())){
			criteria.add(Restrictions.like("realName", "%"+search.getRealName()+"%"));
		}
		if(StringUtils.isNotBlank(search.getOrderId())){
			criteria.add(Restrictions.eq("orderId", search.getOrderId()));
			
		}
		if(StringUtils.isNotBlank(search.getTranDateS())){
			criteria.add(Restrictions.ge("tranDate", search.getTranDateS()));//>=
		}
		if(StringUtils.isNotBlank(search.getTranDateE())){
			criteria.add(Restrictions.le("tranDate", search.getTranDateE()));
		}
		
		criteria.add(Restrictions.like("orderId", "%MS%"));
		criteria.addOrder(Order.desc("transportId"));// 降序排序
		return this.pagedByCriteria(criteria, pageNow, pageSize);
	}
	
	/**
	 * 根据 以 F 开头模糊查询
	 * @param search
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageFinder<PayTransport> qurTransportInfoeasyShop(SearchTransport search,Integer pageNow, Integer pageSize)  throws Exception{
		Criteria criteria = createCriteria();
		criteria.add(Expression.like("orderId","F%")); 
   	    criteria.list();
		if(StringUtils.isNotBlank(search.getRealName())){
			criteria.add(Restrictions.like("realName", "%"+search.getRealName()+"%"));
		}
		if(StringUtils.isNotBlank(search.getOrderId())){
			criteria.add(Restrictions.eq("orderId", search.getOrderId()));
		}
		//根据柜员进行筛选。
		//if (StringUtils.isNotBlank(search.getOperNo())) {
			//criteria.add(Restrictions.eq("operNo", search.getOperNo()));
		//}
		if(StringUtils.isNotBlank(search.getTranDateS())){
			criteria.add(Restrictions.ge("tranDate", search.getTranDateS()));//>=
		}
		if(StringUtils.isNotBlank(search.getTranDateE())){
			criteria.add(Restrictions.le("tranDate", search.getTranDateE()));
		}
		criteria.addOrder(Order.desc("transportId"));// 降序排序
		return this.pagedByCriteria(criteria, pageNow, pageSize);
	}
	
	/**
	 * 查询在逻辑上的唯一的记录
	 * @param orderId 
	 * @return
	 * @throws Exception
	 */
	public List<PayTransport> getByOnlyRecord(String name, String orderId, String isPrincipal) throws Exception{
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("orderId", orderId));
		criteria.add(Restrictions.eq("realName", name));
		criteria.add(Restrictions.eq("isPrincipal", isPrincipal));

		@SuppressWarnings("unchecked")
		List<PayTransport> trans = criteria.list();		
		log.info("查询订单【" + orderId + "】对应的流水成功！");
		return trans;
	}
	
	public List<PayTransport> getPayTransList(String strDate, String endDate, String type) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.ge("payDate", sdf1.format(sdf.parse(strDate))));
		System.out.println(sdf1.format(new Date(sdf.parse(endDate).getTime() + 1000 * 60 * 60* 24)) + "******" +sdf1.format(sdf.parse(strDate)));
		criteria.add(Restrictions.lt("payDate", sdf1.format(new Date(sdf.parse(endDate).getTime() + 1000 * 60 * 60* 24))));
		criteria.add(Restrictions.eq("status", type));
		criteria.add(Restrictions.eq("isPrincipal", "1"));

		List<PayTransport> trans = criteria.list();		
		return trans;
	}
	
	
	/**
	 * 按时间段查询出账信息
	 * @return
	 * @throws Exception
	 */
	public List<PayTransport> qurPayTransByTime(String beginDate, String endDate) throws Exception{
		if(beginDate == null || "".equals(beginDate) || endDate == null || "".equals(endDate) ){
			throw new Exception("beginDate 或endDate 为空！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date eDate = sdf.parse(endDate);
		long newEndDate = eDate.getTime() + 1000*60*60*24;
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("status", "0002"));
		criteria.add(Restrictions.le("payDate", sdf1.format(newEndDate)));
		criteria.add(Restrictions.gt("payDate", sdf1.format(sdf.parse(beginDate))));
		return criteria.list();
	}
}
