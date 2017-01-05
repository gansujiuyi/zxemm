package com.jiuyi.net.message.activity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class actinfo implements Serializable {

	private static final long serialVersionUID = 7232056200409015892L;
	private String actid;
	private String actname;
	private String actdesc;
	private String acttype;
	private String actimg;
	private String onchannel;
	private String starttime;
	private String endtime;
	private String adname;
	private String custid;
	private String custname;
	private String custlogoimg;
	@XmlAttribute(required=false)
	private String sourceurl;
	
	
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
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getActimg() {
		return actimg;
	}
	public void setActimg(String actimg) {
		this.actimg = actimg;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustlogoimg() {
		return custlogoimg;
	}
	public void setCustlogoimg(String custlogoimg) {
		this.custlogoimg = custlogoimg;
	}
	public String getSourceurl() {
		return sourceurl;
	}
	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

}
