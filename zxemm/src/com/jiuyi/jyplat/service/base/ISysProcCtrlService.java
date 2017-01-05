package com.jiuyi.jyplat.service.base;

import java.util.List;

import com.jiuyi.jyplat.entity.system.SysParameter;
import com.jiuyi.jyplat.entity.system.SysProcCtrl;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface ISysProcCtrlService {
	/**
	 * 查询所有有效的批处理任务（批处理监控用）
	 * @return
	 * @throws Exception
	 */
	public List<SysProcCtrl> querySysProcCtrl() throws Exception;

	/**
	 * 查询单条记录
	 * @param SysProcCtrl
	 * @return SysProcCtrl
	 * @throws Exception
	 */
	public List<SysProcCtrl> querySysProcCtrl(SysProcCtrl sysProcCtrl) throws Exception;

	/**
	 * 分页查询
	 * @param sysProcCtrl
	 * @param query
	 * @return PageFinder<SysProcCtrl>
	 * @throws Exception
	 */
	public PageFinder<SysProcCtrl> queryAll4Page(SysProcCtrl sysProcCtrl, Query query) throws Exception;

	/**
	 * 保存新增任务流程
	 * @param SysProcCtrl
	 * @throws Exception
	 */
	public void saveSysProcCtrl(SysProcCtrl SysProcCtrl) throws Exception;

	/**
	 * 删除任务流程
	 * @param enumId
	 * @throws Exception
	 */
	public void delSysProcCtrl(String taskId) throws Exception;

	/**
	 * 修改保存任务流程
	 * @param SysProcCtrl
	 * @throws Exception
	 */
	public void updateSysProcCtrl(SysProcCtrl SysProcCtrl) throws Exception;

	/**
	 * 系统参数查询
	 * @return SysParameter
	 * @throws Exception
	 */
	public SysParameter querySysParameter() throws Exception;

	/**
	 * 修改系统参数-系统处理账务日期,执行状态
	 * @return SysParameter
	 * @throws Exception
	 */
	public void updateSysParameter(SysParameter sysParameter) throws Exception;

}
