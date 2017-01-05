package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.entity.system.Role;
import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.service.acegiManage.InstitutionService;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.service.acegiManage.RoleService;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/operator")
public class OperatorReAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private OperatorService operatorService;
	@Resource
	private InstitutionService instService;
	@Resource
	private SysEnumService sysEnumService;
	Logger log = Logger.getLogger(OperatorAction.class);

	private Query query;

	private PageFinder<Operator> pageFinder;

	private String operName;
	private String operStatus;//操作员状态
	private String department;//操作员部门
	private String institutionId;//操作员机构

	private SysEnum sysEnum;
	private List<Institution> insts;
	private String forSearch; //值为“true”时代表带条件查询



	//查询操作员
	@Action(value = "/queryOperList", results = { @Result(name = SUCCESS, location = "reoper/reOperList.jsp"),
			@Result(name = ERROR, location = "base/error.jsp") })
	public String queryOperList() {
		try {
			log.info("传入的信息为：" + operName + "," + operStatus + "," + department + ","  + institutionId);
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			log.info("开始查询部门信息！！");
			sysEnum = this.querySysEnum("operator", "department");//查询所有部门
			log.info("部门信息查询完毕！！"+sysEnum.getEnumId());
			log.info("开始查询机构信息！！");
			insts = instService.queryInstsList();
			log.info("机构信息查询完毕"+insts.get(0).getInstitutionName());
			//pageFinder = operatorService.queryOperatorByName(operName, query == null? new Query() : query);
			if(query==null){
				query=new Query();
			}
			pageFinder = operatorService.queryOperator(operName, operStatus, department, institutionId,query);
			log.info("操作员的记录共有：" + pageFinder.getRowCount());
			return SUCCESS;
		} catch (Exception e) {
			log.error("操作员查询错误", e);
			this.addActionMessage("操作员查询错误,请联系管理员!");
			return ERROR;
		}
	}

	//查询待审核操作员
	@Action(value = "/queryOperByOperStatus", results = { @Result(name = SUCCESS, location = "reoper/reAuthOperList.jsp"),
			@Result(name = ERROR, location = "base/error.jsp") })
	public String queryOperByOperStatus() {
		try {
			log.info("传入的信息为：" + operName + "," + operStatus + "," + department + ","  + institutionId);
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			sysEnum = this.querySysEnum("operator", "department");//查询所有部门
			insts = instService.queryInstsList();
			//分页查询审核状态为0（即未通过审核）的所有操作员信息
			pageFinder = operatorService.queryByOperStatus(operName, "0", operStatus, department, institutionId,
					query == null ? new Query() : query);
			log.info("操作员的记录共有：" + pageFinder.getRowCount());
			return SUCCESS;
		} catch (Exception e) {
			log.error("操作员查询错误", e);
			this.addActionMessage("操作员查询错误,请联系管理员!");
			return ERROR;
		}
	}

	//查询关联枚举信息
	private SysEnum querySysEnum(String tableName, String fieldName) throws Exception {
		sysEnum = new SysEnum();
		sysEnum.setTableName(tableName);
		sysEnum.setFieldName(fieldName);
		sysEnum.setStatus("1");
		return sysEnumService.queryEnumItem(sysEnum);
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public PageFinder<Operator> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<Operator> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public SysEnum getSysEnum() {
		return sysEnum;
	}

	public void setSysEnum(SysEnum sysEnum) {
		this.sysEnum = sysEnum;
	}

	public List<Institution> getInsts() {
		return insts;
	}

	public void setInsts(List<Institution> insts) {
		this.insts = insts;
	}

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}



}
