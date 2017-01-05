package com.jiuyi.jyplat.service.base;

import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface SysEnumService {
	/**
	 * 根据枚举表字段查询单条记录
	 * @param sysEnum
	 * @return
	 * @throws Exception
	 */
	public SysEnum queryEnumItem(SysEnum sysEnum) throws Exception;

	/**
	 * 根据枚举编号查询对应枚举信息
	 * @param enumId
	 * @return
	 * @throws Exception
	 */
	public SysEnum queryByEnumId(Integer enumId) throws Exception;

	/**
	 * 查询枚举总表中的所有数据
	 * @return
	 * @throws Exception
	 */
	public PageFinder<SysEnum> queryAllEnum(String enumName, String tableName, String fieldName, Query query)
			throws Exception;

	/**
	 * 保存新增枚举信息
	 * @param sysEnum
	 * @throws Exception
	 */
	public void saveEnum(SysEnum sysEnum) throws Exception;

	/**
	 * 删除枚举信息
	 * @param enumId
	 * @throws Exception
	 */
	public void delEnum(String enumIds) throws Exception;

	/**
	 * 修改保存枚举信息
	 * @param sysEnum
	 * @throws Exception
	 */
	public void updateEnum(SysEnum sysEnum) throws Exception;
}
