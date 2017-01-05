package com.jiuyi.jyplat.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.dao.base.SysEnumDao;
import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service("sysEnumService")
public class SysEnumServiceImpl implements SysEnumService {

	@Resource
	private SysEnumDao sysEnumDao;
	@Resource
	private LogInfoDao logDao;

	Logger log = Logger.getLogger(SysEnumServiceImpl.class);

	/**
	 * 根据枚举表字段查询单条记录
	 * @param sysEnum
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysEnum queryEnumItem(SysEnum sysEnum) throws Exception {
		Criteria criteria = sysEnumDao.createCriteria();
		criteria.setFetchMode("sysEnumItems", FetchMode.JOIN);
		criteria.add(Restrictions.eq("tableName", sysEnum.getTableName()));
		criteria.add(Restrictions.eq("fieldName", sysEnum.getFieldName()));
		if (sysEnum.getStatus() != null && !"".equals(sysEnum.getStatus())) {
			criteria.add(Restrictions.eq("status", sysEnum.getStatus()));
		}
		//		criteria.add( Restrictions.eq("sysEnumItems.status", "1") );

		List<SysEnum> list = criteria.list();
		if (list.size() > 0)
			return (SysEnum) list.get(0);
		return null;
	}

	/**
	 * 删除枚举信息
	 * @param enumId
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delEnum(String enumIds) throws Exception {
		String[] ids = enumIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			this.sysEnumDao.removeById(Integer.parseInt(ids[i].trim()));
			log.info("删除编号为【" + ids[i].trim() + "】的枚举信息成功！");
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUM, OperConstant.ACT_DEL,
					"枚举编号：" + ids[i].trim());
		}
	}

	/**
	 * 分页查询枚举总表中的所有数据
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<SysEnum> queryAllEnum(String enumName, String tableName, String fieldName, Query query)
			throws Exception {
		Criteria criteria = this.sysEnumDao.createCriteria();
		if (enumName != null && !enumName.trim().equals("")) {
			criteria.add(Restrictions.like("enumName", "%" + enumName.trim() + "%"));
		}
		if (Utils.notEmptyString(tableName)) {
			criteria.add(Restrictions.like("tableName", "%" + tableName.trim() + "%"));
		}
		if (Utils.notEmptyString(fieldName)) {
			criteria.add(Restrictions.like("fieldName", "%" + fieldName.trim() + "%"));
		}
		return this.sysEnumDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("enumId"));
	}

	/**
	 * 保存新增枚举信息
	 * @param sysEnum
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveEnum(SysEnum sysEnum) throws Exception {
		this.sysEnumDao.save(sysEnum);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUM, OperConstant.ACT_ADD, "");
	}

	/**
	 * 修改保存枚举信息
	 * @param sysEnum
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEnum(SysEnum sysEnum) throws Exception {
		this.sysEnumDao.update(sysEnum);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUM, OperConstant.ACT_EDIT,
				"枚举编号：" + sysEnum.getEnumId());
	}

	/**
	 * 根据枚举编号查询对应枚举信息
	 * @param enumId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysEnum queryByEnumId(Integer enumId) throws Exception {
		return sysEnumDao.getById(enumId);
	}
}
