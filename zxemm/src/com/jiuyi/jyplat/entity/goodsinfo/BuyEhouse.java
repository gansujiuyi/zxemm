package com.jiuyi.jyplat.entity.goodsinfo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_BuyEhouse")
public class BuyEhouse {
	
	@Id
	private String id;
	
	
	private String goodsId;//商品编号
	
	//房源信息
	private String sstr1;//房源编码
	private String sstr2;//产权证号
	private String sstr3;//房屋面积
	private String houseName;//房屋名称
	private Double goodsPrice;//商品价格
	private String lstr8;//房屋所处的位置
	
	private String createTime;//订单生成时间
	
	private Integer num_Bedroom;//几室
	private Integer num_LivingRoom;//几厅
	private Integer num_Washroom;//几卫
	private String decorat_state;//装修状态：毛坯、简装、精装。
	private Integer floor;//所在楼层
	private Integer floorAll;//本栋共有楼层
	private String buildYear;//建造时间yyyy
	
	//买方信息
	private String buyName;	//买房姓名
	private String buyPhone;//买房手机号
	private Integer buyerNo;//买方代理人。
	private String buyerMessage;//买方留言


	//卖方信息
	private String sellName;//产权人姓名
	private Integer sellerNo;//产权人编号
	private String orderStatus;//订单状态

	

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getSstr1() {
		return sstr1;
	}
	public void setSstr1(String sstr1) {
		this.sstr1 = sstr1;
	}
	public String getSstr2() {
		return sstr2;
	}
	public void setSstr2(String sstr2) {
		this.sstr2 = sstr2;
	}
	public String getSstr3() {
		return sstr3;
	}
	public void setSstr3(String sstr3) {
		this.sstr3 = sstr3;
	}
	
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getLstr8() {
		return lstr8;
	}
	public void setLstr8(String lstr8) {
		this.lstr8 = lstr8;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	public String getBuyPhone() {
		return buyPhone;
	}
	public void setBuyPhone(String buyPhone) {
		this.buyPhone = buyPhone;
	}
	
	public Integer getBuyerNo() {
		return buyerNo;
	}
	public void setBuyerNo(Integer buyerNo) {
		this.buyerNo = buyerNo;
	}
	
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public Integer getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(Integer sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	

}
