package com.jiuyi.net.message.activity;

import java.io.Serializable;

public class quractlistRsp implements Serializable {

	private static final long serialVersionUID = 8172022876769637003L;
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	/** 错误提示。说明：retcode为错误的时候出现错误提示 */
	private String retshow;
	/** 总记录数 */
	private int totalrecs;
	/** 总页数 */
	private int totalpage;
	/** 本次记录数 */
	private int recs;
	/** 商品明细信息列表 */
	private actlist alist;

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

	public int getTotalrecs() {
		return totalrecs;
	}

	public void setTotalrecs(int totalrecs) {
		this.totalrecs = totalrecs;
	}


	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getRecs() {
		return recs;
	}

	public void setRecs(int recs) {
		this.recs = recs;
	}

	public actlist getAlist() {
		return alist;
	}

	public void setAlist(actlist alist) {
		this.alist = alist;
	}

}
