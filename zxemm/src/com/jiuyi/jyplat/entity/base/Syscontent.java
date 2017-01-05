package com.jiuyi.jyplat.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Syscontent entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSCONTENT")
public class Syscontent implements java.io.Serializable {
	private static final long serialVersionUID = 896752979864438229L;
	
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_SYSCONTENT",initialValue=1,allocationSize=1)
	private Integer contid;
	private String contname;		//内容名称
	private String description;	//内容描述
	private String starttime;		//开始时间
	private String endtime;		//结束时间
	private String status;			//状态 0003 待审核,0004 审核通过,0005 审核失败
	@Lob  
    @Column(name = "htmldetail", columnDefinition = "CLOB") 
	private String htmldetail;		//详细的HTML内容
	private String createby;		//活动创建者
	private String createtime;		//创建时间
	private String modifyby;		//活动编辑者
	private String modifytime;		//编辑时间
	private String conttype;		//内容类型 1001:首页轮播广告，1002：首页中间广告，1003：首页底部广告，1004：首页右侧广告1,1005：首页右侧广告2
	                                //2001:新房列表页广告1，2002：新房列表页广告2
	                                //3001:新房详情页广告1,3002：新房详情页广告2
	                                //4001:二手房列表页广告1,4002：二手房列表页广告2
	                                //金融超市轮播图：5001
	private String contimg;		//内容图片路径
	
	@Transient
	private String imgUrl;	//图片的真实访问地址
	
	private Integer sequence;		//首页轮播广告中用于标记图片展示的顺序。
	
	private String viewType; //CITYID
	
	
	
	// Property accessors
	public Integer getContid() {
		return this.contid;
	}

	public void setContid(Integer contid) {
		this.contid = contid;
	}

	public String getContname() {
		return this.contname;
	}

	public void setContname(String contname) {
		this.contname = contname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHtmldetail() {
		return this.htmldetail;
	}

	public void setHtmldetail(String htmldetail) {
		this.htmldetail = htmldetail;
	}

	public String getCreateby() {
		return this.createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getModifyby() {
		return this.modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	public String getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getConttype() {
		return this.conttype;
	}

	public void setConttype(String conttype) {
		this.conttype = conttype;
	}


	public String getContimg() {
		return this.contimg;
	}

	public void setContimg(String contimg) {
		this.contimg = contimg;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
}