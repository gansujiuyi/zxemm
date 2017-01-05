package com.jiuyi.net.message;

import java.io.Serializable;

public class Head implements Serializable {
	private static final long serialVersionUID = 1L;

	private String version;// 版本号
	private String tranchannel;// 渠道类型
	private String trandatetime;// 前端时间
	private String authcode;// 认证码
	private Integer memberNo;//会员ID
	private String reqsn;// 请求流水号
	private String servicename;// 后台服务方法
	private String devno;// 设备号

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTranchannel() {
		return tranchannel;
	}

	public void setTranchannel(String tranchannel) {
		this.tranchannel = tranchannel;
	}

	public String getTrandatetime() {
		// 这里提高下兼容性，如果trandatetime 为空，则赋值本系统时间、如果不是
		// yyyymmdd hh:mm:ss 时间格式，则做格式化
		return trandatetime;
	}

	public void setTrandatetime(String trandatetime) {
		this.trandatetime = trandatetime;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getReqsn() {
		return reqsn;
	}

	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getDevno() {
		return devno;
	}

	public void setDevno(String devno) {
		this.devno = devno;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

}
