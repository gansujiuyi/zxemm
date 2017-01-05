package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.system.Role;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.service.acegiManage.RoleService;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/acegi")
public class RoleAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(RoleAction.class);

	private Query query;

	private PageFinder<Role> pageFinder;

	private List<Role> roleList;

	private String roleName, roleId, functionIds, message, goHref;

	private String allFunctionTree;

	private Role role;
	private String forSearch; //值为“true”时代表带条件查询

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	@Resource
	private RoleService roleService;

	@Resource
	private OperatorService operatorService;

	//查询角色
	@Action(value = "/queryRole", results = { @Result(name = "success", location = "role/roleList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryRole() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			pageFinder = roleService.queryRoleByName(roleName, query == null ? new Query() : query);
		} catch (Exception e) {
			log.error("角色查询错误:" + e.getMessage());
			this.addActionMessage("角色查询错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//新增角色
	@Action(value = "/addRole", results = { @Result(name = "success", location = "role/addRole.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String addRole() {
		//跳转到新增role页面
		//		try {
		//			roleList = roleService.queryRoleList();
		//		} catch (Exception e) {
		//			log.error( "角色新增页错误:" + e.getMessage() );
		//			this.addActionMessage("角色新增页错误,请联系管理员!");
		//			return INPUT;
		//		}
		return SUCCESS;
	}

	//修改角色
	@Action(value = "/updateRole", results = { @Result(name = "success", location = "role/updateRole.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String updateRole() {
		try {
			//			roleList = roleService.queryRoleList();//跳转到修改角色页面不用查询有所角色吧？
			role = roleService.getRoleById(role.getRoleId());
		} catch (Exception e) {
			log.error("角色修改页错误:" + e.getMessage());
			this.addActionMessage("角色修改页错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//保存新增角色
	@Action(value = "/saveAddedRole", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveAddedRole() {
		try {
			if (role.getRoleName() != null) {
				//查询角色信息
				List<Role> list = roleService.getRoleByName(role.getRoleName());
				//若查询到角色信息则提示错误 角色名称重复！
				if (list.size() > 0) {
					throw new Exception("角色名称重复！");
				}
			}
			roleService.insertRole(role);
			message = "新增角色成功！";
			goHref = "/acegi/queryRole.do";
		} catch (Exception e) {
			log.error("新增角色错误:" + e.getMessage());
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage()
					: "新增角色错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//保存修改角色
	@Action(value = "/saveUpdatedRole", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveUpdatedRole() {
		try {
			//用于判断是否更改角色名称
			Role role2 = roleService.getRoleById(role.getRoleId());
			if (role.getRoleName() != null) {
				//不相等，即表示改动
				if (!role.getRoleName().trim().equals(role2.getRoleName().trim())) {
					//查询角色信息
					List<Role> list = roleService.getRoleByName(role.getRoleName());
					//若查询到角色信息则提示错误 角色名称重复！
					if (list.size() > 0) {
						throw new Exception("角色名称重复！");
					}
				}
			}
			roleService.updateRole(role);
			message = "修改角色成功！";
			goHref = "/acegi/queryRole.do";
		} catch (Exception e) {
			log.error("保存角色修改信息错误:" + e.getMessage());
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage()
					: "保存角色修改信息错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//删除角色
	@Action(value = "/delRole", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String delRole() {
		try {
			String[] str = roleId.split(",");
			Integer[] id = new Integer[str.length];
			for (int i = 0; i < str.length; i++) {
				id[i] = Integer.parseInt(str[i]);
			}
			if (operatorService.queryByRole(id)) {
				log.error("删除角色失败,该角色(" + roleId + ")在操作员记录中存在引用!");
				this.addActionMessage("删除角色失败:该角色(" + roleId + ")在操作员记录中存在引用!");
				return INPUT;
			}
			// 根据ID删除角色 ,删除多个已逗号间隔,传入ID如下格式 1,2,3
			roleService.delRole(id);
			message = "删除角色成功！";
			goHref = "/acegi/queryRole.do";
		} catch (Exception e) {
			log.error("删除角色错误:" + e.getMessage());
			this.addActionMessage((e.getMessage() != null ? e.getMessage() + "\n" : "") + "删除角色错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//角色-功能关联查询
	@Action(value = "/queryRoleFunction", results = { @Result(name = "success", location = "role/roleFunction.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryRoleFunction() {
		try {
			allFunctionTree = operatorService.getAddRoleHtml();
			roleList = roleService.queryRoleList();
		} catch (Exception e) {
			log.error("角色-功能关联页面错误:" + e.getMessage());
			this.addActionMessage("角色-功能关联页面错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 角色-功能查询显示
	@AuthName
	@Action(value = "/queryRoleShow", results = { @Result(name = "success", location = "role/roleShow.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryRoleShow() {
		try {
			allFunctionTree = operatorService.getAddRoleHtml();
			roleList = roleService.queryRoleList();
		} catch (Exception e) {
			log.error("角色-功能查询显示错误:" + e.getMessage());
			this.addActionMessage("角色-功能查询显示错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//角色-功能关联保存
	@SkipValidation
	@Action(value = "/saveRoleFunction", results = { @Result(name = "success", type = "json"),
			@Result(name = "input", location = "base/error.jsp") })
	public String saveRoleFunction() {
		try {
			roleService.insertRoleFunction(roleId, functionIds);
			message = "saveSuccess";
		} catch (Exception e) {
			log.error("角色-功能关联页面错误:" + e.getMessage());
			this.addActionMessage("角色-功能关联页面错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	//根据角色查询已关联功能
	@AuthName
	@SkipValidation
	@Action(value = "/queryFunByRole", results = { @Result(name = "success", type = "json"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryFunByRole() {
		try {
			functionIds = roleService.queryFunByRole(roleId);
			message = "querySuccess";
		} catch (Exception e) {
			log.error("角色-功能Ajax查询错误:" + e.getMessage());
			this.addActionMessage("角色-功能Ajax查询错误,请联系管理员!");
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

	public PageFinder<Role> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<Role> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAllFunctionTree() {
		return allFunctionTree;
	}

	public void setAllFunctionTree(String allFunctionTree) {
		this.allFunctionTree = allFunctionTree;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

}
