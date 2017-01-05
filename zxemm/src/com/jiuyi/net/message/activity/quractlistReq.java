package com.jiuyi.net.message.activity;

import java.io.Serializable;

public class quractlistReq implements Serializable {

	private static final long serialVersionUID = 6927269643154716775L;
	/** 排序方式。00：按照开始时间； */
	private String ordertype;
	/** 活动类型 */
	private String acttype; //1 启动项 2 首页轮播。。。
//	/** 开始时间 */
//	private String bgntime;
//	/** 结束时间 */
//	private String endtime;
	/** 查询页码 。说明：每页返回数据记录数由平台配置，缺省配置为10条 */
	private String indexpage;
	/** 每页记录数。分页查询时，每页显示的记录数 */
	private String pagesize;
	
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getActtype() {
		return acttype;
	}
	public void setActtype(String acttype) {
		this.acttype = acttype;
	}
	public String getIndexpage() {
		return indexpage;
	}
	public void setIndexpage(String indexpage) {
		this.indexpage = indexpage;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
}
