package com.jiuyi.jyplat.util;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.entity.system.Operator;

public class SessionUtil {

	/**
	 * 从Session中获取登陆操作员信息
	 */
	public static Operator getOperator() {
		return (Operator) ActionContext.getContext().getSession().get(Constant.LOGIN_SYSTEM_USER);
	}
	
	/**
	 * 从Session中获取登陆操作员所属机构信息
	 */
	public static Institution getInstitution() {
		return (Institution) ActionContext.getContext().getSession().get(Constant.LOGIN_SYSTEM_USER_INSTITUTION);
	}

	/**
	 * 把登陆操作员信息存入Session中
	 */
	public static void setOperator(Operator o) {
		ActionContext.getContext().getSession().put(Constant.LOGIN_SYSTEM_USER, o);
		ActionContext.getContext().getSession().put(Constant.LOGIN_SYSTEM_USER_INSTITUTION, o.getInst());
	}
	

	/**
	 * 销毁Session中登陆操作员信息
	 */
	public static void destroyOperator() {
		ActionContext.getContext().getSession().remove(Constant.LOGIN_SYSTEM_USER);
		ActionContext.getContext().getSession().remove(Constant.LOGIN_SYSTEM_USER_INSTITUTION);
	}

	/**
	 * 把登陆操作员拥有功能权限的URL存入Session中
	 */
	public static void setActionUrl(List<String> urls) {
		ActionContext.getContext().getSession().put(Constant.AUTH_LOGIN_URL, urls);
	}

	/**
	 * 从Session中获取登陆操作员拥有功能权限的URL信息
	 */
	public static List<String> getActionUrl() {
		return (List<String>) ActionContext.getContext().getSession().get(Constant.AUTH_LOGIN_URL);
	}

	/**
	 * 销毁Session中登陆操作员拥有功能权限的URL
	 */
	public static void destroyActionUrl() {
		ActionContext.getContext().getSession().remove(Constant.AUTH_LOGIN_URL);
	}

}
