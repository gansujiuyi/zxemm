package com.jiuyi.jyplat.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.SysEnumItem;
import com.jiuyi.jyplat.util.HibernateEntityDao;
import com.jiuyi.jyplat.util.Utils;

@Repository
public class SysEnumItemDao extends HibernateEntityDao<SysEnumItem> {
	public void delEnum(Integer id, Integer enumId) throws Exception {
		String hql = "DELETE SysEnumItem a where a.id = :id and a.sysEnum.enumId = :enumId";
		Query query = this.createQuery(hql);
		query.setInteger("id", id);
		query.setInteger("enumId", enumId);
		query.executeUpdate();
	}

	public List<Object[]> queryEnumItem(String tableName, String fieldName, String fieldValue) throws Exception {
		StringBuffer hql = new StringBuffer(
				"SELECT SEI.id,SEI.fieldValue,SEI.displayValue,SEI.displayOrder,SEI.status,SEI.enumId ");
		hql.append(" FROM SYSENUM SE,SYSENUMITEM SEI");
		hql.append(" WHERE SE.ENUMID = SEI.ENUMID");
		hql.append(" AND SE.TABLENAME = :tableName ");
		hql.append(" AND FIELDNAME = :fieldName ");
		if (Utils.notEmptyString(fieldValue))
			hql.append(" AND SEI.FIELDVALUE = :fieldValue");
		Query query = this.getSession().createSQLQuery(hql.toString());
		query.setString("tableName", tableName);
		query.setString("fieldName", fieldName);
		if (Utils.notEmptyString(fieldValue))
			;
		query.setString("fieldValue", fieldValue);
		return query.list();
	}
}
