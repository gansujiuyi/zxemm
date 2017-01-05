package com.jiuyi.jyplat.entity.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 区
 * <P>TODO</P>
 * @author pengq
 */
@Entity
@Table(name="wx_region")
public class WxRegion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "WX_REGION_SEQ", allocationSize = 1, initialValue = 1)
	private Integer id;//
	private String regionName;//区名
	private Integer cityId;//市id
	
	public WxRegion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WxRegion(Integer id, String regionName, Integer cityId) {
		super();
		this.id = id;
		this.regionName = regionName;
		this.cityId = cityId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	

}
