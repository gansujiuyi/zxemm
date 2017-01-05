package com.jiuyi.jyplat.dao.certification;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.jiuyi.jyplat.entity.certification.Certification;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class CertificationDao extends HibernateEntityDao<Certification> {
	
	/**
	 * 
	 * <p>根据memberNo来查询通过实名认证的用户信息</p>
	 * @return
	 * @author sunzb
	 */
	public List<Certification> queryCertificatedMember(Integer memberNo){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("memberNo", memberNo));
		List<Certification> businessAreas = criteria.list();
		return businessAreas;
	}
	
	/**
	 * <p>根据name</p>
	 * @param name 买方姓名
	 * @param phone 买方手机号
	 * @return
	 * @author guys
	 */
	public Certification validateNameCertificated(String name ,String phone){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("phone", phone));
		List<Certification> certifications = criteria.list();
		Certification c = null;
		if(certifications.size()==0){
				return c;
		}else{
			c = certifications.get(0);
			return c;
		}
		
	}
	
	/**
	 * <p>根据name</p>
	 * @param name 买方姓名
	 * @param phone 买方手机号
	 * @return
	 * @author guys
	 */
	public Certification validateCardNoExist(String cardNo){
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("accountNo", cardNo));
		List<Certification> certifications = criteria.list();
		Certification c = null;
		if(certifications == null || certifications.size()==0){
				return null;
		}else{
			c = certifications.get(0);
			return c;
		}
		
	}
	

	/**
	 * 根据不同的member查询不同的名称
	 * @param buymemberNo
	 * @param sellmember
	 * @return
	 * 
	 * wh
	 */
	public List<Certification> valCertification(Integer buymemberNo,Integer sellmember){
		
		
		List<Certification> listcertification = new ArrayList<Certification>();
		StringBuffer hql= new StringBuffer();
		hql.append("select *　from t_certification t  where t.memberno=? or t.memberno=?");
	   Query query = (Query) createSQLQuery(hql.toString());
	   query.setParameter(0, buymemberNo);
	   query.setParameter(1, sellmember);
	   listcertification = query.list();
	   return listcertification;
	

	  
	}
}
