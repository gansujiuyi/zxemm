package com.jiuyi.jyplat.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.dao.base.SysEnumDao;
import com.jiuyi.jyplat.dao.base.SysEnumItemDao;
import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.entity.system.SysEnumItem;
import com.jiuyi.jyplat.service.base.SysEnumItemService;
import com.jiuyi.jyplat.util.OperConstant;

@Service
public class SysEnumItemServiceImpl implements SysEnumItemService {
	@Resource
	private SysEnumItemDao enumItemDao;
	@Resource
	private SysEnumDao sysEnumDao;
	@Resource
	private LogInfoDao logDao;

	Logger log = Logger.getLogger(SysEnumItemServiceImpl.class);

	/**
	 * 删除枚举明细记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delEnumItem(Integer id) throws Exception {
		SysEnumItem si = null;
		SysEnum sysenum = null;
		Integer fv = null;
		try {
			si = this.enumItemDao.getById(id);
			sysenum = sysEnumDao.getById(si.getSysEnum().getEnumId());
			fv = Integer.parseInt(si.getFieldValue());
		} catch (Exception e) {
			log.error("删除枚举值前判断出错" + e.toString());
		}
		if (sysenum != null && si != null && fv != null && sysenum.getTableName().trim().equals("custInfo")
				&& sysenum.getFieldName().trim().equals("custGroup") && si.getFieldValue() != null && fv >= 100000) {
			throw new Exception("系统重要枚举参数,禁止删除!");
		}
		this.enumItemDao.removeById(id);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUMITEM, OperConstant.ACT_DEL, "枚举编号：" + id);
	}

	/**
	 * 根据枚举编号查询所有对应的枚举明细信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SysEnumItem> queryByEnumId(Integer enumId) throws Exception {
		return this.enumItemDao.findBy("sysEnum.enumId", enumId, "displayOrder", true);
	}

	/**
	 * 查询枚举明细记录详细信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SysEnumItem queryByEnumItemId(Integer id) throws Exception {
		return this.enumItemDao.getById(id);
	}

	/**
	 * 新增枚举明细记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveEnumItem(SysEnumItem sysEnumItem) throws Exception {
		this.enumItemDao.save(sysEnumItem);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUMITEM, OperConstant.ACT_ADD, "");
	}

	/**
	 * 修改枚举明细记录
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEnumItem(SysEnumItem sysEnumItem) throws Exception {
		this.enumItemDao.update(sysEnumItem);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_ENUMITEM, OperConstant.ACT_EDIT, "枚举编号："
				+ sysEnumItem.getId());
	}
}
