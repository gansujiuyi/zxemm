/**
 * @{#} voUxunECodeAuth.java Create on 2013年10月05日 
 * 手机动态口令认证码实体类
 * @author zenglj
 * @version 1.0
 * Copyright @ 2009 - 2013  
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 2013-10-5	         zenglj                             	         v1.0 
 */
package com.jiuyi.bcms.data;

//
public class voUxunECodeAuth {

	private String USER_NO;// VARCHAR2(16) N 用户编号（手机号码）
	private String CURR_PWD;// VARCHAR2(60) Y 当前动态口令（已加密）
	private String STATE;// VARCHAR2(1) Y 用户状态 0 正常 1 锁定
	private int ERR_TIMES;// NUMBER(6) Y 0 口令错误次数（5次）
	private String GEN_TIME;// VARCHAR2(20) Y 口令生成时间
	private String CHK_TIME;// VARCHAR2(20) Y 最近口令验证时间
	private String CRT_TIME;// VARCHAR2(20) Y 用户建立时间
	private String THR_USER;// VARCHAR2(20) Y 业务用户号

	public voUxunECodeAuth() {
	}

	public String getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(String user_no) {
		USER_NO = user_no;
	}

	public String getCURR_PWD() {
		return CURR_PWD;
	}

	public void setCURR_PWD(String curr_pwd) {
		CURR_PWD = curr_pwd;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String state) {
		STATE = state;
	}

	public int getERR_TIMES() {
		return ERR_TIMES;
	}

	public void setERR_TIMES(int err_times) {
		ERR_TIMES = err_times;
	}

	public String getGEN_TIME() {
		return GEN_TIME;
	}

	public void setGEN_TIME(String gen_time) {
		GEN_TIME = gen_time;
	}

	public String getCHK_TIME() {
		return CHK_TIME;
	}

	public void setCHK_TIME(String chk_time) {
		CHK_TIME = chk_time;
	}

	public String getCRT_TIME() {
		return CRT_TIME;
	}

	public void setCRT_TIME(String crt_time) {
		CRT_TIME = crt_time;
	}

	public String getTHR_USER() {
		return THR_USER;
	}

	public void setTHR_USER(String thr_user) {
		THR_USER = thr_user;
	}

}