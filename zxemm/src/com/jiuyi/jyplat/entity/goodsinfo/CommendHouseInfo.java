package com.jiuyi.jyplat.entity.goodsinfo;

/**
 * 用于封装推荐房源查询到的两张表的所有的在页面显示的数据
 * 
 * @author Administrator
 * 
 */
public class CommendHouseInfo {
	private String goodsId;

	private String goodsName;// 商品名称
	private String goodsImg;// 商品图片

	private String tag;// 商品标签 “|”分割

	// 小区信息
	private Integer countyId;// 地理区域
	private String countyName;// 地理区域名
	//private String buildingName;// 小区名
	private String streetName;//街道
	private String villageaddress;// 小区地址
	// private

	private Integer num_Bedroom;// 几室
	private Integer num_LivingRoom;// 几厅
	private Integer num_Washroom;// 几卫
	private String houseArea;// 房屋面积
	private String decorat_state;// 装修状态：毛坯、简装、精装。
	private Integer floor;// 所在楼层
	private Integer floorAll;// 本栋共有楼层
	private String buildYear;// 建造时间yyyy
	private String modifTime;// 修改时间
	private String createTime;// 创建时间
	
	

	private Double price;// 价格 总价
	private Double perPrice;// 单价

	private String buildingName;// 楼盘名称
	private String districtName;// 区
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getVillageaddress() {
		return villageaddress;
	}

	public void setVillageaddress(String villageaddress) {
		this.villageaddress = villageaddress;
	}

	public Integer getNum_Bedroom() {
		return num_Bedroom;
	}

	public void setNum_Bedroom(Integer numBedroom) {
		num_Bedroom = numBedroom;
	}

	public Integer getNum_LivingRoom() {
		return num_LivingRoom;
	}

	public void setNum_LivingRoom(Integer numLivingRoom) {
		num_LivingRoom = numLivingRoom;
	}

	public Integer getNum_Washroom() {
		return num_Washroom;
	}

	public void setNum_Washroom(Integer numWashroom) {
		num_Washroom = numWashroom;
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

	public void setDecorat_state(String decoratState) {
		decorat_state = decoratState;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	

}
