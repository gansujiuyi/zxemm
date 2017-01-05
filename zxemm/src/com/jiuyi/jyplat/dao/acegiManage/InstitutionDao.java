package com.jiuyi.jyplat.dao.acegiManage;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class InstitutionDao extends HibernateEntityDao<Institution> {

	Logger log = Logger.getLogger(InstitutionDao.class);

	/**
	 * 删除组织机构
	 * @param Institution.institutionId
	 * @param Institution.parentInstitutionId
	 * @throws Exception
	 */
	public void deleteByNo(Institution inst) throws Exception {
		Query query = createQuery("DELETE Institution where institutionId = :institutionId OR parentInstitutionId = :parentInstitutionId");
		query.setInteger("institutionId", inst.getInstitutionId());
		query.setInteger("parentInstitutionId", inst.getInstitutionId()); // 将操作员的状态置为停用
		query.executeUpdate();
	}

}
