package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.menu.Level1;
import com.jiuyi.jyplat.entity.system.SysFunction;
import com.jiuyi.jyplat.service.acegiManage.FunctionService;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.util.TreeMenu;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;

@Namespace("/sysFunction")
public class FunctionAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private FunctionService functionService;
	@Resource
	private OperatorService operatorService;

	Logger log = Logger.getLogger(FunctionAction.class);

	private PageFinder<SysFunction> pageFinder; //分页数据
	private SysFunction sysFunction; //系统功能对象
	private String functionNo; //功能编号
	private List<SysFunction> sysFunctions; //系统功能对象集合
	private List<Level1> level1s; //菜单对象
	private String functionMenu; //系统功能树形展示代码
	private String message; //记录操作消息
	private String goHref; //跳转路径
	private String requestAction; //请求动作

	private String parentNo; //父类功能编号
	private String checkNamesubItem; //功能菜单名称

	public String getRequestAction() {
		return requestAction;
	}

	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFunctionMenu() {
		return functionMenu;
	}

	public void setFunctionMenu(String functionMenu) {
		this.functionMenu = functionMenu;
	}

	public SysFunction getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
	}

	public List<SysFunction> getSysFunctions() {
		return sysFunctions;
	}

	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public PageFinder<SysFunction> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<SysFunction> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public List<Level1> getLevel1s() {
		return level1s;
	}

	public void setLevel1s(List<Level1> level1s) {
		this.level1s = level1s;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

	public String getCheckNamesubItem() {
		return checkNamesubItem;
	}

	public void setCheckNamesubItem(String checkNamesubItem) {
		this.checkNamesubItem = checkNamesubItem;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	/**
	 * 跳转到增加系统功能页面
	 * 
	 * @return
	 */
	@Action(value = "/addFunction", results = { @Result(name = "success", location = "function/addFunction.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addFunction() {
		try {
			if (functionNo == null) {
				throw new Exception("系统功能编号为空！");
			}
			functionNo = functionNo.split(",")[0];
			//查询欲添加的功能的父功能信息
			sysFunction = this.functionService.queryFunctionById(functionNo);
		} catch (Exception e) {
			message = "查询父功能信息失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 添加保存系统功能
	 * 
	 * @return
	 */
	@Action(value = "/saveFunction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveFunction() {
		try {
			if (sysFunction == null) {
				throw new Exception("系统功能数据为空！");
			}
			functionService.addFunction(sysFunction);
			message = "保存系统功能成功！";
			goHref = "/sysFunction/queryAllFunction.do";
			log.info("保存系统功能成功！");
		} catch (Exception e) {
			message = "保存系统功能失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 查询所有功能
	 * 
	 * @return
	 */
	@Action(value = "/queryAllFunction", results = {
			@Result(name = "success", location = "function/findAllFunction.jsp"),
			@Result(name = "forAjax", type = "json", params = { "includeProperties",
					"sysFunctions\\[\\d+\\]\\.functionNo" }), @Result(name = "input", location = "base/error.jsp") })
	public String queryAllFunction() {
		try {
			sysFunctions = functionService.queryAllFunction();
			//请求页面是通过ajax查询所有系统功能时执行
			if (requestAction != null && requestAction.trim().equals("queryByAjax")) {
				return "forAjax";
			}
			if (sysFunctions == null) {
				throw new Exception("未查询到系统功能数据！");
			}
			//按照系统功能层级关系封装菜单数据
			level1s = this.operatorService.getAllMenuList(sysFunctions);
			//将系统功能集合转换为以树形结构展示的代码
			this.functionMenu = TreeMenu.getFunctionTree(level1s);
		} catch (Exception e) {
			message = "查询系统功能失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 根据FunctionNo查询对应功能
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryFunctionById", results = { @Result(name = "success", type = "json"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryFunctionById() {
		try {
			if (functionNo == null || functionNo.trim().equals("")) {
				message = "查询系统功能失败：系统功能编号为空！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			sysFunction = functionService.queryFunctionById(functionNo.trim());
			message = "querySuccess";
			if (sysFunction == null) {
				throw new Exception("未查询到系统功能！");
			}
			sysFunction.setRoles(null);
		} catch (Exception e) {
			message = "查询功能编号为" + functionNo.trim() + "的系统功能信息失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 根据functionNo删除对应功能
	 * 
	 * @return
	 */
	@Action(value = "/delFunction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String delFunction() {
		if (functionNo == null || functionNo.trim().equals("")) {
			message = "删除系统功能失败：未获取到系统功能编号！";
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}
		try {
			functionService.delFunction(functionNo);
			message = "删除系统功能成功！";
			goHref = "/sysFunction/queryAllFunction.do";
		} catch (Exception e) {
			message = "删除系统功能失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 修改系统功能
	 * @return
	 */
	@Action(value = "/updateFunction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String updateFunction() {
		try {
			if (sysFunction == null) {
				message = "更新保存系统功能失败：没有获取到需要保存的系统功能数据！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			functionService.updateFunction(sysFunction);
			message = "修改系统功能成功！";
			goHref = "/sysFunction/queryAllFunction.do";
			log.info("更新保存系统功能成功！functionNo:" + sysFunction.getFunctionNo());
		} catch (Exception e) {
			message = "更新保存系统功能失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 查询对应子功能
	 * @return
	 */
	@Action(value = "/queryAllChildFunction", results = {
			@Result(name = "success", type = "json", params = { "includeProperties",
					"sysFunctions\\[\\d+\\]\\.functionNo,sysFunctions\\[\\d+\\]\\.functionName,message" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllChildFunction() {
		try {
			if (functionNo == null || functionNo.trim().equals("")) {
				message = "功能编号为空,查询操作失败！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			sysFunctions = this.functionService.queryAllChildFunction(functionNo);
			message = "hasChildFunction";
		} catch (Exception e) {
			message = "查询功能编号为【" + functionNo + "】对应子功能信息失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在
	 * 
	 * @return
	 */
	@Action(value = "/CheckNamesubItem", results = {
			@Result(name = "success", type = "json", params = { "includeProperties", "message" }),
			@Result(name = "input", location = "function/addFunction.jsp") })
	public String CheckNamesubItem() {
		try {
			List<SysFunction> sysFunction = functionService.CheckNamesubItem(checkNamesubItem, parentNo);
			if (null == sysFunction || sysFunction.size() == 0) {
				message = "noexists";
			} else {
				message = "exists";
			}
		} catch (Exception e) {
			log.error("根据Action名称查询action信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 检查同一父类菜单下菜单名称信息是否存在——修改时的校验
	 * 
	 * @return
	 */
	@Action(value = "/CheckNamesubfunUP", results = {
			@Result(name = "success", type = "json", params = { "includeProperties", "message" }),
			@Result(name = "input", location = "function/findAllFunction.jsp") })
	public String CheckNamesubfunUP() {
		try {
			List<SysFunction> sysFunction = functionService.CheckNamesubfunUP(checkNamesubItem, parentNo, functionNo);
			if (null == sysFunction || sysFunction.size() == 0) {
				message = "noexists";
			} else {
				message = "exists";
			}
		} catch (Exception e) {
			log.error("根据Action名称查询action信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}
}
