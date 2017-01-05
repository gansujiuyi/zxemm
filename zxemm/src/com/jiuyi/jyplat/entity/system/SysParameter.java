package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SysParameter implements Serializable {

	@Id
	private String operInitialPassword; // 操作员初始密码，密文（MD5）
	private String sysWorkDate;//	Char(8)				当前系统业务日期
	private String lastWorkDate;//	Char(8)				上个工作日
	private String lastMonthDate;//		Char(8)				上月
	private String lastQuartDate;//	Char(8)				上季
	private String lastYearDate;//	Char(8)				上年
	private String dataDownDate;//	Char(8)				数据下载日期
	private String sysState;//Char(1)				系统状态
	private String sysVer;//Char(10)				系统版本号

	public String getOperInitialPassword() {
		return operInitialPassword;
	}

	public void setOperInitialPassword(String operInitialPassword) {
		this.operInitialPassword = operInitialPassword;
	}

	public String getSysWorkDate() {
		return sysWorkDate;
	}

	public void setSysWorkDate(String sysWorkDate) {
		this.sysWorkDate = sysWorkDate;
	}

	public String getLastWorkDate() {
		return lastWorkDate;
	}

	public void setLastWorkDate(String lastWorkDate) {
		this.lastWorkDate = lastWorkDate;
	}

	public String getLastMonthDate() {
		return lastMonthDate;
	}

	public void setLastMonthDate(String lastMonthDate) {
		this.lastMonthDate = lastMonthDate;
	}

	public String getLastQuartDate() {
		return lastQuartDate;
	}

	public void setLastQuartDate(String lastQuartDate) {
		this.lastQuartDate = lastQuartDate;
	}

	public String getLastYearDate() {
		return lastYearDate;
	}

	public void setLastYearDate(String lastYearDate) {
		this.lastYearDate = lastYearDate;
	}

	public String getDataDownDate() {
		return dataDownDate;
	}

	public void setDataDownDate(String dataDownDate) {
		this.dataDownDate = dataDownDate;
	}

	public String getSysState() {
		return sysState;
	}

	public void setSysState(String sysState) {
		this.sysState = sysState;
	}

	public String getSysVer() {
		return sysVer;
	}

	public void setSysVer(String sysVer) {
		this.sysVer = sysVer;
	}
}
