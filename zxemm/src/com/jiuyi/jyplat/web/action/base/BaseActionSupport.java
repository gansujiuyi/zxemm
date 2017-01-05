package com.jiuyi.jyplat.web.action.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("acegi-default")
@Results(value = { @Result(name = Action.LOGIN, location = "/index.jsp") })
public class BaseActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected String msg = "服务器繁忙，请稍后再试";//出错时的提示信息。

	/**
	 * 获得HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}

	/**
	 *获得HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	}

	/**
	 * 获得Session
	 * @return
	 */
	public static Map getSession() {
		return ActionContext.getContext().getSession();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
