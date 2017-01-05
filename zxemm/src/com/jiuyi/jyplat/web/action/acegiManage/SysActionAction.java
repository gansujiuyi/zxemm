package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.service.acegiManage.ActionService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/sysAction")
public class SysActionAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private ActionService actionService;

	//生成日志对象
	Logger log = Logger.getLogger(SysActionAction.class);

	private int actionId; //action编号
	private SysAction sysAction; //action对象
	private List<SysAction> sysActions; //action对象集合
	private String actionIds; //多个action编号，以逗号分隔
	private Query query; //分页查询对象
	private PageFinder<SysAction> pageFinder; //分页数据
	private String actionName; //action名称
	private String message; //记录操作消息
	private String goHref; //跳转路径
	private String forSearch; //值为“true”时代表带条件查询

	private String checkNameAction; //Action 名称
	private String checkIdAction; //Action Id

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public PageFinder<SysAction> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<SysAction> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public String getActionIds() {
		return actionIds;
	}

	public void setActionIds(String actionIds) {
		this.actionIds = actionIds;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public SysAction getSysAction() {
		return sysAction;
	}

	public void setSysAction(SysAction sysAction) {
		this.sysAction = sysAction;
	}

	public List<SysAction> getSysActions() {
		return sysActions;
	}

	public void setSysActions(List<SysAction> sysActions) {
		this.sysActions = sysActions;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

	public String getCheckNameAction() {
		return checkNameAction;
	}

	public void setCheckNameAction(String checkNameAction) {
		this.checkNameAction = checkNameAction;
	}

	public String getCheckIdAction() {
		return checkIdAction;
	}

	public void setCheckIdAction(String checkIdAction) {
		this.checkIdAction = checkIdAction;
	}

	/**
	 * 跳转到添加action页面
	 * @return
	 */
	@Action(value = "/addAction", results = { @Result(name = "success", location = "action/addAction.jsp") })
	public String addAction() {
		return SUCCESS;
	}

	/**
	 * 保存新增action
	 * @return
	 */
	@Action(value = "/saveAction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveAction() {
		try {
			if (sysAction == null) {
				message = "action对象数据为空，保存失败！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			actionService.addAction(sysAction);
			message = "保存action成功！";
			goHref = "/sysAction/queryAllAction.do";
			log.info("保存action成功！action名称：" + sysAction.getActionName());
		} catch (Exception e) {
			message = "保存action信息失败：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 分页查询所有action（根据actionName模糊查询）
	 * 
	 * @return
	 */
	@Action(value = "/queryAllAction", results = { @Result(name = "success", location = "action/findAllAction.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllAction() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			pageFinder = actionService.queryByActionName(sysAction, query == null ? new Query() : query);
		} catch (Exception e) {
			message = "查询action信息操作出错：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 根据actionId查询对应信息
	 * 
	 * @return
	 */
	@Action(value = "/queryActionById", results = { @Result(name = "success", location = "action/updateAction.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryActionById() {
		try {
			if (actionIds == null || actionIds.trim().equals("")) {
				message = "未获取到action编号，查询action信息失败！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			//获取action编号，转换格式
			actionId = Integer.parseInt(actionIds.split(",")[0].trim());

			sysAction = actionService.queryActionById(actionId);
		} catch (Exception e) {
			message = "根据action编号查询action信息出错：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除Action信息
	 * 
	 * @return
	 */
	@Action(value = "/delAction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String delAction() {
		if (actionIds == null || actionIds.trim().equals("")) {
			message = "未获取到action编号，删除action信息失败！";
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}
		try {
			actionService.delAction(actionIds);
			message = "删除action成功！";
			goHref = "/sysAction/queryAllAction.do";
		} catch (Exception e) {
			message = "删除action操作失败：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 修改Action信息
	 * @return
	 */
	@Action(value = "/updateAction", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String updateAction() {
		try {
			if (sysAction == null) {
				message = "action对象数据为空，修改保存失败！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			actionService.updateAction(sysAction);
			message = "修改action成功！";
			goHref = "/sysAction/queryAllAction.do";
			log.info("修改保存action信息成功！actionId：" + sysAction.getActionId());
		} catch (Exception e) {
			message = "修改保存action信息失败：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 检查Action名称信息是否存在
	 * @return
	 */
	@Action(value = "/CheckNameAction", results = {
			@Result(name = "success", type = "json", params = { "includeProperties", "message" }),
			@Result(name = "input", location = "action/addAction.jsp") })
	public String CheckNameAction() {
		try {
			List<SysAction> sysAction = actionService.queryByActionName(checkNameAction);
			if (null == sysAction || sysAction.size() == 0) {
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
	 * 检查Action名称信息是否存在__修改时的校验
	 * @return
	 */
	@Action(value = "/CkNameForUpdate", results = {
			@Result(name = "success", type = "json", params = { "includeProperties", "message" }),
			@Result(name = "input", location = "action/addAction.jsp") })
	public String CkNameForUpdate() {
		try {
			List<SysAction> sysAction = actionService.CkNameForUpdate(checkNameAction, checkIdAction);
			if (null == sysAction || sysAction.size() == 0) {
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
