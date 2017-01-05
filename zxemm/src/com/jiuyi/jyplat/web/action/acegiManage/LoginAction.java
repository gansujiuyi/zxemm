package com.jiuyi.jyplat.web.action.acegiManage;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.entity.system.SysParameter;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.service.base.ISysProcCtrlService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.OperateResult;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;

@ParentPackage("acegi-default")
@Namespace("/login")
public class LoginAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(LoginAction.class);

	@Resource
	private OperatorService operatorService;
	@Resource
	private ISysProcCtrlService sysProcCtrlService;

	private Operator operator; //登陆操作员对象

	private String imagecode, menuHtml, isDefaultPW; //验证码

	private Integer beAuditOper;

	private SysParameter sysParameter;

	private OperateResult operateResult;

	@AuthName
	@Action(value = "/login", results = {
			@Result(name = LOGIN, location = "base/login.jsp"),
			@Result(name = SUCCESS, params = { "actionName", "doLogin", "namespace", "" }, type = "chain") })
	public String login() {
		//先判断用户是否已经登录，如果session中存在用户信息，则说明已经登录，则直接跳转到首页
		Operator operator_1 = SessionUtil.getOperator();
		if (null == operator_1) {
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 
	 * <p>操作员登录</p>
	 * @return
	 * @author guoyuzhuang
	 */
	@AuthName
	@Action(value = "/doLogin", results = { @Result(name = SUCCESS, location = "base/frameIndex.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String doLogin() {
		//先判断用户是否已经登录，如果session中存在用户信息，则说明已经登录，则直接跳转到首页
		Operator operator_1 = SessionUtil.getOperator();
		if (null != operator_1) {
			return SUCCESS;
		}
		try {
			if (null == operator) {
				return LOGIN;
			}
			Operator o = operatorService.getOperator(operator);
			if (o == null) {
				this.addActionMessage("用户名错误！");
				return INPUT;
			}
			if (!o.getOperStatus().trim().equals("1")) { // 操作员状态必须为1可用
				String status = "非正常";
				if (o.getOperStatus().trim().equals("2"))
					status = "待审核";
				else if (o.getOperStatus().trim().equals("3"))
					status = "锁定";
				else
					status = "停用";
				this.addActionMessage("用户状态为" + status + "，请联系系统管理员处理！");
				return INPUT;
			}

			if (!validatePassWord(o)) {
				operatorService.loginFail(o); //	密码登陆错误后续处理
				this.addActionMessage("密码错误！");
				return INPUT;
			}
			operatorService.loginSuccess(o); //	登陆成功
		} catch (Exception e) {
			log.error("系统登陆错误", e);
			this.addActionMessage("系统登陆错误:请联系管理员!" + e.toString());
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>验证短信验证码</p>
	 * <p>用户在登录的时候，异步验证短信验证码信息</p>
	 * @return
	 * @author 郭玉壮
	 */
	@AuthName
	@Action(value = "/valiCode", results = { @Result(name = SUCCESS, type = "json") })
	public String valiCode() {
		boolean result = false;
		try {
			if (!Utils.isNullData(imagecode)) {
				result = this.validateImageCode();
				if (result) {
					operateResult = new OperateResult(true);
				}
			}
		} catch (Exception e) {
			log.info("验证短信码出现错误:" + e.toString(), e);
		}
		if (!result) {
			operateResult = new OperateResult(false, "短信码错误");
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>图片验证码核对</p>
	 * @return
	 * @throws Exception
	 * @author 郭玉壮
	 */
	private boolean validateImageCode() throws Exception {
		try {
			return ActionContext.getContext().getSession().get(Constant.LOGIN_VALIDATE_IMAGE).toString().equals(
					imagecode.toUpperCase()) ? true : false;
		} catch (Exception e) {
			if (ActionContext.getContext().getSession().get(Constant.LOGIN_VALIDATE_IMAGE) == null) {
				throw new Exception("验证码失效,请刷新页面!");
			} else
				throw e;
		}
	}

	/**
	 * 
	 * <p>密码验证码核对</p>
	 * @param o
	 * @return
	 * @author 郭玉壮
	 */
	private boolean validatePassWord(Operator o) {
		return o.getPassword().equals(Utils.md5Encrypt(operator.getPassword()));
	}

	/**
	 * 
	 * <p>注销登录</p>
	 * @return
	 * @author 郭玉壮
	 */
	@AuthName
	@Action(value = "/loginOut", results = { @Result(name = "success", location = "/index.jsp") })
	public String loginOut() {
		SessionUtil.destroyActionUrl();
		SessionUtil.destroyOperator();
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/topFrame", results = { @Result(name = "success", location = "base/frameTop.jsp") })
	public String topFrame() {
		//		operator = SessionUtil.getOperator();
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/leftFrame", results = { @Result(name = "success", location = "base/frameLeft.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String leftFrame() {
		try {
			operator = SessionUtil.getOperator();
			menuHtml = operatorService.getMenuHtml();
		} catch (Exception e) {
			log.error("生成功能菜单错误", e);
			this.addActionMessage("生成功能菜单错误,请联系管理员");
			return INPUT;
		}
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/mainFrame", results = { @Result(name = "success", location = "base/frameWelcome.jsp") })
	public String mainFrame() {
		try {
			Operator operator = SessionUtil.getOperator();
			String defaultPW = operatorService.queryOperDefaultPW();
			if (operator.getPassword().trim().equals(defaultPW.trim()))
				isDefaultPW = "true"; //	提示操作员密码为系统初始默认密码
			if (operator.getRole().getRoleId() == Constant.SYSTEM_ADMIN_ROLE_2)
				// 查询待审核操作员总数
				beAuditOper = operatorService.queryCountInStatus("0");
			sysParameter = sysProcCtrlService.querySysParameter();

		} catch (Exception e) {
			log.error("系统欢迎页错误", e);
			this.addActionMessage("系统欢迎页错误,请联系管理员");
			return INPUT;
		}
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/footFrame", results = { @Result(name = "success", location = "base/frameFoot.jsp") })
	public String footFrame() {
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/switchFrame", results = { @Result(name = "success", location = "base/frameSwitch.jsp") })
	public String switchFrame() {
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/mainTopFrame", results = { @Result(name = "success", location = "base/frameMainTop.jsp") })
	public String mainTopFrame() {
		return SUCCESS;
	}

	public String getImagecode() {
		return imagecode;
	}

	public void setImagecode(String imagecode) {
		this.imagecode = imagecode;
	}

	public String getMenuHtml() {
		return menuHtml;
	}

	public void setMenuHtml(String menuHtml) {
		this.menuHtml = menuHtml;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getIsDefaultPW() {
		return isDefaultPW;
	}

	public void setIsDefaultPW(String isDefaultPW) {
		this.isDefaultPW = isDefaultPW;
	}

	public Integer getBeAuditOper() {
		return beAuditOper;
	}

	public void setBeAuditOper(Integer beAuditOper) {
		this.beAuditOper = beAuditOper;
	}

	public SysParameter getSysParameter() {
		return sysParameter;
	}

	public void setSysParameter(SysParameter sysParameter) {
		this.sysParameter = sysParameter;
	}

	public OperateResult getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(OperateResult operateResult) {
		this.operateResult = operateResult;
	}
}
