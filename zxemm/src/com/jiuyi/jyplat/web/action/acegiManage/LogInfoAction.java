package com.jiuyi.jyplat.web.action.acegiManage;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.LogInfo;
import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.service.base.ILogInfoService;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/acegi")
public class LogInfoAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private ILogInfoService logInfoService;
	@Resource
	private SysEnumService sysEnumService;

	private Query query;
	private LogInfo logInfo;
	private PageFinder<LogInfo> pageFinder;
	private SysEnum platforms; //关联功能平台枚举
	private SysEnum modules; //关联功能模块枚举
	private SysEnum operActions; //关联操作动作枚举
	private SysEnum sysEnum;
	private String forSearch; //值为“true”时代表带条件查询

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public SysEnum getSysEnum() {
		return sysEnum;
	}

	public void setSysEnum(SysEnum sysEnum) {
		this.sysEnum = sysEnum;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public PageFinder<LogInfo> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<LogInfo> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public SysEnum getPlatforms() {
		return platforms;
	}

	public void setPlatforms(SysEnum platforms) {
		this.platforms = platforms;
	}

	public SysEnum getModules() {
		return modules;
	}

	public void setModules(SysEnum modules) {
		this.modules = modules;
	}

	public SysEnum getOperActions() {
		return operActions;
	}

	public void setOperActions(SysEnum operActions) {
		this.operActions = operActions;
	}

	/**
	 * 查询所有操作日志
	 * @return
	 */
	@Action(value = "/queryAllLog", results = { @Result(name = "success", location = "oper/logInfoList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllLog() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();

			platforms = this.querySysEnum("logInfo", "platform");//查询关联功能平台枚举
			modules = this.querySysEnum("logInfo", "module");//查询关联功能模块枚举
			operActions = this.querySysEnum("logInfo", "operAction");//查询关联操作动作枚举
			pageFinder = this.logInfoService.queryAllLog(logInfo, query == null ? new Query() : query);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 查询我的操作日志
	 * @return
	 */
	@Action(value = "/queryMyLog", results = { @Result(name = "success", location = "oper/myLogList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryMyLog() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();

			platforms = this.querySysEnum("logInfo", "platform");//查询关联功能平台枚举
			modules = this.querySysEnum("logInfo", "module");//查询关联功能模块枚举
			operActions = this.querySysEnum("logInfo", "operAction");//查询关联操作动作枚举
			//如果没有设置查询条件，则生成一个新的LogInfo对象并设置当前操作员编号
			if (logInfo == null) {
				logInfo = new LogInfo();
			}
			logInfo.setOperNo(SessionUtil.getOperator().getOperNo());
			pageFinder = this.logInfoService.queryAllLog(logInfo, query == null ? new Query() : query);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	//查询关联枚举信息
	private SysEnum querySysEnum(String tableName, String fieldName) throws Exception {
		sysEnum = new SysEnum();
		sysEnum.setTableName(tableName);
		sysEnum.setFieldName(fieldName);
		sysEnum.setStatus("1");
		return sysEnumService.queryEnumItem(sysEnum);
	}
}
