package com.jiuyi.jyplat.entity.goodsScan;

import java.io.Serializable;

/**
 * 浏览记录的相关信息
 * <P>TODO</P>
 * @author pengq
 */
public class FrontGoodsScan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String buildingid;
	private String buildingName;//小区名
	private String houseArea;//房屋面积
	private Integer num_Bedroom;//几室
	private Integer num_LivingRoom;//几厅
	private String decorat_state;//装修状态：毛坯、简装、精装。
	private Integer floor;//所在楼层
	private Integer floorAll;//本栋共有楼层
	private String buildYear;//建造时间yyyy
	private Integer memberNo;
	private String phoneNo;//联系电话
	private String goodsId;
	private String lastTime;//最后浏览时间
	private String goodsName;//商品名称
	private Double price;//价格 总价
	private String cityName;//地理区域名
	private String villageaddress;//小区地址
	
	public String getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
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
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
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
	
	
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getVillageaddress() {
		return villageaddress;
	}
	public void setVillageaddress(String villageaddress) {
		this.villageaddress = villageaddress;
	}
	
	
	
	
}
