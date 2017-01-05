package com.jiuyi.jyplat.service.base;

import java.util.List;

import com.jiuyi.jyplat.entity.system.SysEnumItem;

public interface SysEnumItemService {
	/**
	 * 查询枚举明细记录详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysEnumItem queryByEnumItemId(Integer id) throws Exception;

	/**
	 * 根据枚举编号查询所有对应的枚举明细信息
	 * @param enumId
	 * @return
	 * @throws Exception
	 */
	public List<SysEnumItem> queryByEnumId(Integer enumId) throws Exception;

	/**
	 * 新增枚举明细记录
	 * @param sysEnumItem
	 * @throws Exception
	 */
	public void saveEnumItem(SysEnumItem sysEnumItem) throws Exception;

	/**
	 * 删除枚举明细记录
	 * @param id
	 * @throws Exception
	 */
	public void delEnumItem(Integer id) throws Exception;

	/**
	 * 修改枚举明细记录
	 * @param sysEnumItem
	 * @throws Exception
	 */
	public void updateEnumItem(SysEnumItem sysEnumItem) throws Exception;
}
