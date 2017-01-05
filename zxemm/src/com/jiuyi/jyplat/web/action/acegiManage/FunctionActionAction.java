package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.menu.Level1;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.entity.system.SysFunction;
import com.jiuyi.jyplat.service.acegiManage.ActionService;
import com.jiuyi.jyplat.service.acegiManage.FunctionActionService;
import com.jiuyi.jyplat.service.acegiManage.FunctionService;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.util.TreeMenu;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;

@Namespace("/sysFunctionAction")
public class FunctionActionAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private FunctionService functionService;
	@Resource
	private ActionService actionService;
	@Resource
	private OperatorService operatorService;
	@Resource
	private FunctionActionService funcActionService;

	Logger log = Logger.getLogger(FunctionActionAction.class);

	private String functionMenu;
	private String functionNo, actionIds, message;
	private List<SysFunction> sysFunctions;
	private List<SysAction> sysActions;
	private List<Level1> level1s;

	//	private List<FunctionAction> functionActions;
	//	public List<FunctionAction> getFunctionActions() {
	//		return functionActions;
	//	}
	//
	//	public void setFunctionActions(List<FunctionAction> functionActions) {
	//		this.functionActions = functionActions;
	//	}

	public String getFunctionMenu() {
		return functionMenu;
	}

	public void setFunctionMenu(String functionMenu) {
		this.functionMenu = functionMenu;
	}

	public List<Level1> getLevel1s() {
		return level1s;
	}

	public void setLevel1s(List<Level1> level1s) {
		this.level1s = level1s;
	}

	public List<SysFunction> getSysFunctions() {
		return sysFunctions;
	}

	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public List<SysAction> getSysActions() {
		return sysActions;
	}

	public void setSysActions(List<SysAction> sysActions) {
		this.sysActions = sysActions;
	}

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getActionIds() {
		return actionIds;
	}

	public void setActionIds(String actionIds) {
		this.actionIds = actionIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 查询所有的function和action
	 * @return
	 */
	@Action(value = "/queryAllFunctionAction", results = {
			@Result(name = "success", location = "functionaction/functionAction.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllFunctionAction() {
		try {
			//查询所有的系统功能
			sysFunctions = this.functionService.queryAllFunction();
			if (sysFunctions == null) {
				message = "无系统功能记录！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			//获取系统功能菜单集合
			List<Level1> level1s = this.operatorService.getAllMenuList(sysFunctions);
			//将系统功能集合转换为以树形结构展示的代码
			this.functionMenu = TreeMenu.getFunctionTree(level1s);
			//获取所有的action
			this.sysActions = this.actionService.queryAllAction();
		} catch (Exception e) {
			message = "查询系统功能及action失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//				e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 更新保存系统功能与action的关联信息
	 * @return
	 */
	@Action(value = "/addFunctionAction", results = { @Result(name = "success", type = "json"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addFunctionAction() {
		try {
			funcActionService.delFunctionAction(functionNo);
			funcActionService.addFunctionAction(functionNo, actionIds);
			message = "saveSuccess";
			log.info("修改保存系统功能与action的关联关系成功！functionNo:" + functionNo);
		} catch (Exception e) {
			message = "修改系统功能与action的关联关系失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//				e.printStackTrace();
			return INPUT;
		}

		return SUCCESS;
	}

	/**
	 * 查询某个系统功能对应actionId
	 * @return
	 */
	@Action(value = "/queryActionByFuncNo", results = { @Result(name = "success", type = "json"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryActionByFuncNo() {
		try {
			if (functionNo == null || functionNo.trim().equals("")) {
				message = "未获取到系统功能编号数据！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			actionIds = funcActionService.queryActionByFunctionNo(functionNo.trim());
			message = "querySuccess";
		} catch (Exception e) {
			message = "查询系统功能与action关联关系失败！" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//					e.printStackTrace();
			return INPUT;
		}

		return SUCCESS;
	}
}
