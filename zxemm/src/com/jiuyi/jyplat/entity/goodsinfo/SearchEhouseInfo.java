package com.jiuyi.jyplat.entity.goodsinfo;

import java.io.Serializable;

public class SearchEhouseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String retcode;


	private String goodsstatus;//商品本身状态
	
	private boolean isPreview;//查询个人中心发布的房源

	private String goodsName;// 二手房的名称
	private String districtId;//区县
	private Integer businessId;//区域ID
	private String street;//路id
	private String countyName;//区域名
	private String villageId;// 小区ID
	private Integer num_Bedroom;// 几室
	private Double minArea;// 房屋面积
	private Double maxArea;// 房屋面积
	private Double minPrice;// 价格区间 最小值
	private Double maxPrice;// 价格区间 最大值
	private String minYear;//建造时间yyyy
	private String maxYear;//建造时间yyyy
	private String decorat_state;//装修状态：毛坯、简装、精装。
	private Integer minFloor;//在XX层之下
	private Integer maxFloor;//在XX层之上
	private String modifTime;//修改时间
	private String createTime;//创建时间
	private String orderType;//排序方式。
	private Integer memberNo;
	private String brokerId;//经纪人ID agencyInfo中id字段
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getGoodsstatus() {
		return goodsstatus;
	}
	public void setGoodsstatus(String goodsstatus) {
		this.goodsstatus = goodsstatus;
	}
	public boolean isPreview() {
		return isPreview;
	}
	public void setPreview(boolean isPreview) {
		this.isPreview = isPreview;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public Integer getNum_Bedroom() {
		return num_Bedroom;
	}
	public void setNum_Bedroom(Integer num_Bedroom) {
		this.num_Bedroom = num_Bedroom;
	}
	public Double getMinArea() {
		return minArea;
	}
	public void setMinArea(Double minArea) {
		this.minArea = minArea;
	}
	public Double getMaxArea() {
		return maxArea;
	}
	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinYear() {
		return minYear;
	}
	public void setMinYear(String minYear) {
		this.minYear = minYear;
	}
	public String getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(String maxYear) {
		this.maxYear = maxYear;
	}
	public String getDecorat_state() {
		return decorat_state;
	}
	public void setDecorat_state(String decorat_state) {
		this.decorat_state = decorat_state;
	}
	public Integer getMinFloor() {
		return minFloor;
	}
	public void setMinFloor(Integer minFloor) {
		this.minFloor = minFloor;
	}
	public Integer getMaxFloor() {
		return maxFloor;
	}
	public void setMaxFloor(Integer maxFloor) {
		this.maxFloor = maxFloor;
	}
	public String getModifTime() {
		return modifTime;
	}
	public void setModifTime(String modifTime) {
		this.modifTime = modifTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	
	

}
