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

@Namespace("/acegi")
public class OperatorAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private OperatorService operatorService;
	@Resource
	private RoleService roleService;
	@Resource
	private InstitutionService instService;
	@Resource
	private SysEnumService sysEnumService;
	Logger log = Logger.getLogger(OperatorAction.class);

	private Query query;

	private PageFinder<Operator> pageFinder;

	private String operName;
	private String operStatus;// 操作员状态
	private String department;// 操作员部门
	private String institutionId;// 操作员机构
	private Operator oper;

	private SysEnum sysEnum;

	private String operNo;

	private String newPassword;

	private String oldPassword;

	private String message;
	private String goHref;
	private List<Institution> insts;
	private List<Role> roles;
	private String authStatus;
	private String operatorNos;
	private String forSearch; // 值为“true”时代表带条件查询

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public String getOperatorNos() {
		return operatorNos;
	}

	public void setOperatorNos(String operatorNos) {
		this.operatorNos = operatorNos;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
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

	public Operator getOper() {
		return oper;
	}

	public void setOper(Operator oper) {
		this.oper = oper;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	// 查询操作员
	@Action(value = "/queryOper", results = { @Result(name = SUCCESS, location = "oper/operList.jsp") })
	public String queryOper() {
		try {
			log.info("传入的信息为：" + operName + "," + operStatus + "," + department
					+ "," + institutionId);
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			log.info("开始查询部门信息！！");
			sysEnum = this.querySysEnum("operator", "department");// 查询所有部门
			log.info("部门信息查询完毕！！" + sysEnum.getEnumId());
			log.info("开始查询机构信息！！");
			insts = instService.queryInstsList();
			log.info("机构信息查询完毕" + insts.get(0).getInstitutionName());
			// pageFinder = operatorService.queryOperatorByName(operName, query
			// == null? new Query() : query);
			if (query == null) {
				query = new Query();
			}
			pageFinder = operatorService.queryOperator(operName, operStatus,
					department, institutionId, query);
			log.info("----------------------------操作员的记录共有："
					+ pageFinder.getRowCount());
		} catch (Exception e) {
			log.error("操作员查询错误", e);
			this.addActionMessage("操作员查询错误,请联系管理员!");
			return INPUT;
		}
		log.info("*********************返回成功页面*********************");
		return SUCCESS;
	}

	// 查询待审核操作员
	@Action(value = "/queryByOperStatus", results = {
			@Result(name = SUCCESS, location = "oper/authOperList.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String queryByOperStatus() {
		try {
			log.info("传入的信息为：" + operName + "," + operStatus + "," + department
					+ "," + institutionId);
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
			sysEnum = this.querySysEnum("operator", "department");// 查询所有部门
			insts = instService.queryInstsList();
			// 分页查询审核状态为0（即未通过审核）的所有操作员信息
			pageFinder = operatorService.queryByOperStatus(operName, "0",
					operStatus, department, institutionId,
					query == null ? new Query() : query);
			log.info("操作员的记录共有：" + pageFinder.getRowCount());
		} catch (Exception e) {
			log.error("操作员查询错误", e);
			this.addActionMessage("操作员查询错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 操作员审核
	@Action(value = "/auditOperator", results = {
			@Result(name = SUCCESS, type = "redirectAction", location = "queryByOperStatus", params = { "message",
					"auditPass" }), @Result(name = "input", location = "base/error.jsp") })
	public String auditOperator() {
		try {
			// 批量审核操作员
			String[] operNos = operatorNos.split(",");
			for (int i = 0; i < operNos.length; i++) {
				oper = operatorService.getOperatorByNo(operNos[i].trim());
				oper.setAuthStatus("1");// 设置审核状态 1通过、0未通过
				oper.setAuthDate(DateUtils.dateToDateString(new Date()));// 修改最后审核时间
				if (oper.getOperStatus().trim().equals("2")) {
					// 若审核通过时将操作员状态为【待审核】修改为【可用】
					oper.setOperStatus("1");
				}
				operatorService.auditOperator(oper);
			}
			message = "OK";
		} catch (Exception e) {
			log.error("审核操作员错误", e);
			this.addActionMessage("审核操作员错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 新增操作员
	@Action(value = "/addOper", results = {
			@Result(name = SUCCESS, location = "oper/addOper.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String addOper() {
		try {
			sysEnum = this.querySysEnum("operator", "department");// 查询所有部门
			insts = instService.queryInstsList();
			roles = roleService.queryRoleList();
		} catch (Exception e) {
			log.error("查询操作员部门错误", e);
			this.addActionMessage("查询操作员部门错误,请联系管理员");
			return INPUT;
		}
		return SUCCESS;
	}

	// 修改操作员
	@Action(value = "/updateOper", results = {
			@Result(name = SUCCESS, location = "oper/updateOper.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String updateOper() {
		try {
			sysEnum = this.querySysEnum("operator", "department");// 查询所有部门
			insts = instService.queryInstsList();
			roles = roleService.queryRoleList();
			oper = operatorService.getOperatorByNo(oper.getOperNo());
		} catch (Exception e) {
			log.error("操作员修改页错误", e);
			this.addActionMessage("操作员修改页错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 保存新增操作员
	@Action(value = "/saveAddedOper", results = {
			@Result(name = SUCCESS, location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String saveAddedOper() {
		try {
			// 判断操作员登录名是否重复
			if (oper.getOperNo() != null) {
				Operator operTemp = new Operator();
				operTemp.setOperNo(oper.getOperNo());
				// 查询操作员信息
				List<Operator> list = operatorService
						.queryAllOperator(operTemp);
				// 若查询到操作员信息则提示错误 登录名重复！
				if (list.size() > 0) {
					throw new Exception("登录名重复！");
				}
			}
			operatorService.insertOperator(oper);
			message = "新增操作员成功！";
			goHref = "/acegi/queryOper.do";
		} catch (Exception e) {
			log.error("新增操作员错误", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e
					.getLocalizedMessage() : "新增操作员错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 保存修改操作员
	@Action(value = "/saveUpdatedOper", results = {
			@Result(name = SUCCESS, location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String saveUpdatedOper() {
		try {
			if (oper == null)
				throw new Exception("数据无效");
			// 操作员状态可在页面手动修改
			oper.setOperStatus("2");// 将操作员状态修改为待审核
			oper.setAuthStatus("0");// 将审核状态修改为未通过
			oper.setModifiedDate(DateUtils.dateToDateString(new Date()));// 设置修改时间
			operatorService.updateOperator(oper);
			message = "修改操作员成功！";
			goHref = "/acegi/queryOper.do";
		} catch (Exception e) {
			log.error("保存操作员修改信息错误", e);
			this.addActionMessage("保存操作员修改信息错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 停用/启用操作员
	@Action(value = "/delOper", results = {
			@Result(name = SUCCESS, location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String delOper() {
		String status = oper.getOperStatus();
		try {
			operatorService.delOperator(oper);
			message = (status.trim().equals("4") ? "停用" : "启用") + "操作员成功！";
			goHref = "/acegi/queryOper.do";
		} catch (Exception e) {
			log.error((status.trim().equals("4") ? "停用" : "启用") + "操作员错误", e);
			this.addActionMessage((e.getMessage() != null ? e.getMessage()
					+ "\n" : "")
					+ (status.trim().equals("4") ? "停用" : "启用")
					+ "操作员错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 密码重置
	@Action(value = "/resetPassword", results = {
			@Result(name = SUCCESS, location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String resetPassword() {
		try {
			if (operatorNos == null || operatorNos.trim().equals("")) {
				message = "未获取到操作员编号！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			String[] operNos = operatorNos.split(",");
			for (int i = 0; i < operNos.length; i++) {
				oper = operatorService.getOperatorByNo(operNos[i].trim());
				oper.setPassword(operatorService.queryOperDefaultPW());// 将密码修改为默认密码(13579)
				oper.setModifiedDate(DateUtils.dateToDateString(new Date()));// 修改最后修改时间
				oper.setErrorLoginTimes(0);// 将错误登录次数置零
				operatorService.auditOperator(oper);
				log.info("编号为【" + oper.getOperNo() + "】的操作员重置密码成功！");
			}
			message = "操作员密码重置成功！";
			goHref = "/acegi/queryOper.do";
		} catch (Exception e) {
			log.error("操作员重置密码错误！", e);
			this.addActionMessage("操作员重置密码错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}

	// 跳转至修改操作员密码页面
	@Action(value = "/modifyPassword", results = {
			@Result(name = SUCCESS, location = "oper/modifyPassword.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String modifyPassword() {
		return SUCCESS;
	}

	// 检查输入的原密码是否正确
	@Action(value = "/checkOldPassword", results = {
			@Result(name = SUCCESS, type = "json", params = {
					"includeProperties", "message" }),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String checkOldPassword() {
		try {
			// 将页面输入的原密码以MD5算法加密
			String oldPwd = Utils.md5Encrypt(oldPassword.trim());

			// 检验操作员编号输入是否合法
			if (operNo == null || operNo.trim().equals("")) {
				message = "操作员编号输入有误，请重新核对！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}

			// 根据操作员编号查询操作员信息
			oper = operatorService.getOperatorByNo(operNo.trim());
			if (oper == null) {
				message = "没有操作员编号为" + operNo.trim() + "的相关数据！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			// 校验页面输入的旧密码是否与取得数据库中记录的原密码吻合
			String password = oper.getPassword().trim();
			if (password.equals(oldPwd)) {
				message = "truePwd";
				log.info("原密码校验成功！");
			} else {
				message = "falsePwd";
			}
		} catch (Exception e) {
			message = "原密码校验失败：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}
		return SUCCESS;
	}

	// 修改保存操作员密码
	@Action(value = "/savePassword", results = {
			@Result(name = SUCCESS, location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String savePassword() {
		try {
			// 1、检验操作员编号输入是否合法
			if (operNo == null || operNo.trim().equals("")) {
				message = "操作员编号输入有误，请重新核对！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}
			// 2、根据操作员编号查询操作员信息
			oper = operatorService.getOperatorByNo(operNo.trim());
			if (oper == null) {
				message = "没有编号为" + operNo.trim() + "的操作员信息，请检查操作员编号是否输入正确！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}

			// 3、校验页面输入的旧密码是否与取得数据库中记录的原密码吻合

			// 将页面输入的原密码以MD5算法加密
			String oldPwd = Utils.md5Encrypt(oldPassword.trim());
			// 取出数据库中的原操作员密码
			String password = oper.getPassword().trim();
			if (!password.equals(oldPwd)) {
				message = "修改密码失败：原密码输入错误！";
				log.error(message);
				this.addActionMessage(message);
				return INPUT;
			}

			// 4、将修改后的密码以MD5算法加密后设置新密码
			String pwd = Utils.md5Encrypt(newPassword);
			oper.setPassword(pwd); // 设置新密码
			operatorService.updateOperPwd(oper); // 调用修改操作员密码方法
			log.info("修改密码成功！操作员编号：" + oper.getOperNo().trim());
			message = "修改密码成功！";
			goHref = "/login/mainFrame.do";
		} catch (Exception e) {
			message = "修改操作员密码出错：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 查询关联枚举信息
	private SysEnum querySysEnum(String tableName, String fieldName)
			throws Exception {
		sysEnum = new SysEnum();
		sysEnum.setTableName(tableName);
		sysEnum.setFieldName(fieldName);
		sysEnum.setStatus("1");
		return sysEnumService.queryEnumItem(sysEnum);
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

}
