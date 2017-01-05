package com.jiuyi.jyplat.web.action.acegiManage;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.service.acegiManage.InstitutionService;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@ParentPackage("acegi-default")
@Namespace("/acegi")
public class InstitutionAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(InstitutionAction.class);

	private Query query;

	private PageFinder<Institution> pageFinder;

	private List<Institution> instList;

	private String instName, isAddOEditOper;

	private Institution inst;

	private String message;

	private String forSearch; //值为“true”时代表带条件查询

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	@Resource
	private InstitutionService institutionService;

	//查询组织机构
	@Action(value = "/queryInst", results = { @Result(name = "success", location = "inst/instList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryInst() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();

			instList = institutionService.queryInstsList();
			pageFinder = institutionService.queryInstsByName(instName, query == null ? new Query() : query);
		} catch (Exception e) {
			log.error("组织机构查询错误:" + e.getMessage());
			this.addActionMessage("组织机构查询错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//新增组织机构
	@Action(value = "/addInstIndex", results = { @Result(name = "success", location = "inst/addInstIndex.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addInstIndex() {
		return SUCCESS;
	}

	//新增组织机构
	@Action(value = "/addInstLeft", results = { @Result(name = "success", location = "inst/addInstLeft.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addInstLeft() {
		try {
			instList = institutionService.queryInstsList();
		} catch (Exception e) {
			log.error("组织机构新增页错误:" + e.getMessage());
			this.addActionMessage("组织机构新增页错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//新增组织机构
	@Action(value = "/addInstRight", results = { @Result(name = "success", location = "inst/addInstRight.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addInstRight() {
		try {
			if (inst != null)
				inst.setInstitutionName(java.net.URLDecoder.decode(inst.getInstitutionName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("组织机构新增页错误:" + e.getMessage());
			this.addActionMessage("组织机构新增页错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//修改组织机构
	@Action(value = "/updateInst", results = { @Result(name = "success", location = "inst/updateInst.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String updateInst() {
		try {
			instList = institutionService.queryInstsList();
			inst = institutionService.getInstById(inst.getInstitutionId());
		} catch (Exception e) {
			log.error("组织机构修改页错误:" + e.getMessage());
			this.addActionMessage("组织机构修改页错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//保存新增组织机构
	@Action(value = "/saveAddedInst", results = {
			@Result(name = "success", type = "redirectAction", params = { "isAddOEditOper", "true", "message", "save" }, location = "queryInst"),
			@Result(name = "input", location = "base/error.jsp") })
	public String saveAddedInst() {
		try {
			//判断组织机构名称是否重复
			if (inst.getInstitutionName() != null) {
				Institution instTemp = new Institution();
				instTemp.setInstitutionName(inst.getInstitutionName());
				//查询组织机构信息
				List<Institution> list = institutionService.queryInstsList(instTemp);
				//若查询到组织机构信息则提示错误 组织机构名称重复！
				if (list.size() > 0) {
					throw new Exception("组织机构名称重复！");
				}
			}
			institutionService.insertInsts(inst);
		} catch (Exception e) {
			log.error("新增组织机构错误:" + e.getMessage());
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage()
					: "新增组织机构错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//保存修改组织机构
	@Action(value = "/saveUpdatedInst", results = {
			@Result(name = "success", type = "redirectAction", params = { "isAddOEditOper", "true", "message", "update" }, location = "queryInst"),
			@Result(name = "input", location = "base/error.jsp") })
	public String saveUpdatedInst() {
		try {
			//用于判断是否更改组织机构名称
			Institution inst2 = institutionService.getInstById(inst.getInstitutionId());
			//判断组织机构名称是否重复
			if (inst.getInstitutionName() != null) {
				if (!inst.getInstitutionName().trim().equals(inst2.getInstitutionName().trim())) {
					Institution instTemp = new Institution();
					instTemp.setInstitutionName(inst.getInstitutionName());
					//查询组织机构信息
					List<Institution> list = institutionService.queryInstsList(instTemp);
					//若查询到组织机构信息则提示错误 组织机构名称重复！
					if (list.size() > 0) {
						throw new Exception("组织机构名称重复！");
					}
				}
			}
			institutionService.updateInsts(inst);
		} catch (Exception e) {
			log.error("保存组织机构修改信息错误:" + e.getMessage());
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage()
					: "保存组织机构修改信息错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//删除组织机构
	@Action(value = "/delInst", results = {
			@Result(name = "success", type = "redirectAction", params = { "isAddOEditOper", "true", "message", "del" }, location = "queryInst"),
			@Result(name = "input", location = "base/error.jsp") })
	public String delInst() {
		try {
			institutionService.deleteByNo(inst);
		} catch (Exception e) {
			log.error("删除组织机构错误:" + e.getMessage());
			this.addActionMessage("删除机构错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public PageFinder<Institution> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<Institution> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public List<Institution> getInstList() {
		return instList;
	}

	public void setInstList(List<Institution> instList) {
		this.instList = instList;
	}

	public Institution getInst() {
		return inst;
	}

	public void setInst(Institution inst) {
		this.inst = inst;
	}

	public String getIsAddOEditOper() {
		return isAddOEditOper;
	}

	public void setIsAddOEditOper(String isAddOEditOper) {
		this.isAddOEditOper = isAddOEditOper;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
