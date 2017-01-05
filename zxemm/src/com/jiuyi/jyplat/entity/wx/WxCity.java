package com.jiuyi.jyplat.entity.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 网点城市信息
 * <P>TODO</P>
 * @author pengq
 */
@Entity
@Table(name="wx_city")
public class WxCity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "WX_CITY_SEQ", allocationSize = 1, initialValue = 1)
	private Integer id;//自增id
	private String cityName;//市名
	private Integer provinceId;//关联的省id
	private String zipcode;//邮编
	
	
	public WxCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public WxCity(Integer id, String cityName, Integer provinceId) {
		super();
		this.id = id;
		this.cityName = cityName;
		this.provinceId = provinceId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	

}
