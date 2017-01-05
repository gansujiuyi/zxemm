package com.jiuyi.jyplat.authority;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.SessionUtil;

/**
 * 报表权限拦截器
 */
public class ReportInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 8491276216537773577L;

	public String intercept(ActionInvocation invocation) throws Exception {
		//获取拦截器内容
		ActionContext context = invocation.getInvocationContext();

		ActionProxy proxy = invocation.getProxy();
		String methodName = proxy.getMethod();
		Object action = proxy.getAction();

		String classurl = action.getClass().getName();
		String authUrl = classurl + "." + methodName;

		Map paramMap = (Map) context.get("com.opensymphony.xwork2.ActionContext.parameters");
		String page = "", reportNM = "";
		Object Pages = paramMap.get("Page");
		Object ReportNMs = paramMap.get("ReportNM");
		Object OutType = paramMap.get("OutType");
		if (ReportNMs != null && OutType != null && !OutType.toString().trim().equals(""))
			return invocation.invoke();
		if (ReportNMs != null && ReportNMs instanceof String[]) {
			page = ((String[]) Pages)[0];
			reportNM = ((String[]) ReportNMs)[0];
		} else if (ReportNMs != null && ReportNMs instanceof String) {
			page = Pages.toString();
			reportNM = ReportNMs.toString();
		}

		String allUrl = authUrl + "?Page=" + page + "&ReportNM=" + reportNM;

		Operator operator = SessionUtil.getOperator();

		if (null != operator) {
			List<String> authUrls = SessionUtil.getActionUrl();

			//处理进行权限校验  控制到方法
			if (authUrls == null || authUrls.contains(allUrl.toLowerCase())) {
				return invocation.invoke();
			}
			ActionContext.getContext().put("tip", "您暂时没有此项操作的权限,如有疑问请联系管理员!");
			return "noAuth";
		} else {
			//登录页面，修改密码直接跳过
			if (authUrl.equals(Constant.AUTH_LOGIN_URL) || authUrl.equals(Constant.AUTH_EDIT_PW_URL)
					|| authUrl.equals(Constant.AUTH_CHECK_PW_URL)) {
				return invocation.invoke();
			}

			ActionContext.getContext().put("tip", "没有登录");
			return Action.LOGIN;
		}
	}

}
