package com.jiuyi.jyplat.entity.goodsCollect;

import java.io.Serializable;

/**
 * 我的收藏信息
 * <P>TODO</P>
 * @author pengq
 */
public class FrontGoodsCollect implements Serializable{
	private static final long serialVersionUID = 1L;

	private String goodsId;
	private String goodsName;//商品名称
	private String goodsImg;//商品图片

	private String countyName;//地理区域名
	private String buildingName;//小区名
	private String villageaddress;//小区地址
	private Integer num_Washroom;//几卫
	private String houseArea;//房屋面积
	private String decorat_state;//装修状态：毛坯、简装、精装。
	private Integer floor;//所在楼层
	private Integer floorAll;//本栋共有楼层
	private String buildYear;//建造时间yyyy
	private Double price;//价格 总价
	private Double perPrice;//单价
	private Integer num_Bedroom;//几室
	private Integer num_LivingRoom;//几厅
	private String phoneNo;
	
	public FrontGoodsCollect() {
		super();
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getVillageaddress() {
		return villageaddress;
	}
	public void setVillageaddress(String villageaddress) {
		this.villageaddress = villageaddress;
	}
	public Integer getNum_Washroom() {
		return num_Washroom;
	}
	public void setNum_Washroom(Integer num_Washroom) {
		this.num_Washroom = num_Washroom;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public String getDecorat_state() {
		return decorat_state;
	}
	public void setDecorat_state(String decorat_state) {
		this.decorat_state = decorat_state;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getFloorAll() {
		return floorAll;
	}
	public void setFloorAll(Integer floorAll) {
		this.floorAll = floorAll;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPerPrice() {
		return perPrice;
	}
	public void setPerPrice(Double perPrice) {
		this.perPrice = perPrice;
	}
	public Integer getNum_Bedroom() {
		return num_Bedroom;
	}
	public void setNum_Bedroom(Integer num_Bedroom) {
		this.num_Bedroom = num_Bedroom;
	}
	public Integer getNum_LivingRoom() {
		return num_LivingRoom;
	}
	public void setNum_LivingRoom(Integer num_LivingRoom) {
		this.num_LivingRoom = num_LivingRoom;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	private Integer memberNo;//会员id
	
}
