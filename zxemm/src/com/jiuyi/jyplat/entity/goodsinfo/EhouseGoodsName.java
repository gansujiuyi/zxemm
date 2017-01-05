package com.jiuyi.jyplat.entity.goodsinfo;

import java.io.Serializable;

public class EhouseGoodsName implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodsId;
	private String houseCode;//房源代码
	private String houseCard;//房产证号
	private String houseAddress;//房源地址
	
	//房源类型
	private String houseType;//房源发布类型 1001房东发布，1002经纪人发布
	
	//中介相关
	private String agencyId;//中介公司ID agencyInfo中brokeragencyno字段
	private String subAgencyId;//所属网点ID gencyInfo中subagencyno字段
	private String brokerId;//经纪人ID agencyInfo中id字段
	
	//用户填写内容
	private String province;//省
	private String city;//市
	private String district;//区
	private String street;//路
	private String provinceName;//省名称
	private String cityName;//市
	private String districtName;//区
	private String streetName;//路
	private String neighborhood;//家属区
	
	private Integer buildingId;//小区ID  //废弃
	private String buildingName;//小区名
	
	private Double houseArea;//房屋面积
	private Double perPrice;//单价，在发布商品时根据面积和总价计算后填充
	private Integer num_Bedroom;//几室
	private Integer num_LivingRoom;//几厅
	private Integer num_Washroom;//几卫
	private String roomType;//房屋类型：普通住宅、公寓、别墅、平房。
	private String decorat_state;//装修状态：毛坯、简装、精装。
	private String face;//朝向：东、南、西、北、南北、东西、东南、西南、东北、西北
	private Integer floor;//所在楼层
	private Integer floorAll;//本栋共有楼层
	private String buildYear;//建造时间yyyy
	private String isFiveOld;//房产证是否满五年，用于计算税费
	private String onlyOne;//是否为房东唯一住房，用于计算税费

	private String goodsDesc;//商品图文详情  
	private String tag;//商品标签 “|”分割

	private String houseBluePrint;//户型设计图
	private String buildBluePrint;//小区设计图
	
	private String goodsName;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getHouseCard() {
		return houseCard;
	}

	public void setHouseCard(String houseCard) {
		this.houseCard = houseCard;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getSubAgencyId() {
		return subAgencyId;
	}

	public void setSubAgencyId(String subAgencyId) {
		this.subAgencyId = subAgencyId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
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

	public Integer getNum_Washroom() {
		return num_Washroom;
	}

	public void setNum_Washroom(Integer num_Washroom) {
		this.num_Washroom = num_Washroom;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getDecorat_state() {
		return decorat_state;
	}

	public void setDecorat_state(String decorat_state) {
		this.decorat_state = decorat_state;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
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

	public String getIsFiveOld() {
		return isFiveOld;
	}

	public void setIsFiveOld(String isFiveOld) {
		this.isFiveOld = isFiveOld;
	}

	public String getOnlyOne() {
		return onlyOne;
	}

	public void setOnlyOne(String onlyOne) {
		this.onlyOne = onlyOne;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getHouseBluePrint() {
		return houseBluePrint;
	}

	public void setHouseBluePrint(String houseBluePrint) {
		this.houseBluePrint = houseBluePrint;
	}

	public String getBuildBluePrint() {
		return buildBluePrint;
	}

	public void setBuildBluePrint(String buildBluePrint) {
		this.buildBluePrint = buildBluePrint;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	

}
