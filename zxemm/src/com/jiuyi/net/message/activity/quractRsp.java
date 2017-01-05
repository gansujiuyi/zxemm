package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class quractRsp implements Serializable {

	private static final long serialVersionUID = 8177646880721217602L;
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	/** 错误提示。说明retcode为错误的时候，出现错误提示 */
	private String retshow;
	private String actid;
	private String actname;
	private String actdesc;
	private String acttype;
	private String actimg;
	private String onchannel;
	private String starttime;
	private String endtime;
	private String smscontent;
	private String adname;
	private String addesc;
	private String adcontent;

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetshow() {
		return retshow;
	}

	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getActname() {
		return actname;
	}

	public void setActname(String actname) {
		this.actname = actname;
	}

	public String getActdesc() {
		return actdesc;
	}

	public void setActdesc(String actdesc) {
		this.actdesc = actdesc;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSmscontent() {
		return smscontent;
	}

	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getAddesc() {
		return addesc;
	}

	public void setAddesc(String addesc) {
		this.addesc = addesc;
	}

	public String getAdcontent() {
		return adcontent;
	}

	public void setAdcontent(String adcontent) {
		this.adcontent = adcontent;
	}

	public String getActtype() {
		return acttype;
	}

	public void setActtype(String acttype) {
		this.acttype = acttype;
	}

	public String getOnchannel() {
		return onchannel;
	}

	public void setOnchannel(String onchannel) {
		this.onchannel = onchannel;
	}

	public String getActimg() {
		return actimg;
	}

	public void setActimg(String actimg) {
		this.actimg = actimg;
	}

}
