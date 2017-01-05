package com.jiuyi.jyplat.entity.wx;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * AbstractWxBranchId entity provides the base persistence definition of the
 * WxBranchId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="wx_branch")
public class WxBranch implements Serializable {

	// Fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "wx_branch_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	private String locationCity;
	private String name;
	private String address;
	private String mobilePhone;
	private String atmtype;
	private String type;
	@Column(name = "location_X")
	private String locationX;
	@Column(name = "location_Y")
	private String locationY;
	private String updateTime;
	private String updateOperno;
	private String createTime;
	private String createOperno;
	private String status;
	private String orgno;//�����
	private String regionName;//区名
	private String regionId;//区id
	private String cityName;
	// Constructors

	/** default constructor */
	public WxBranch() {
	}

	/** minimal constructor */
	public WxBranch(Integer id) {
		this.id = id;
	}

	

	// Property accessors

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationCity() {
		return this.locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAtmtype() {
		return this.atmtype;
	}

	public void setAtmtype(String atmtype) {
		this.atmtype = atmtype;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocationX() {
		return this.locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return this.locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOperno() {
		return this.updateOperno;
	}

	public void setUpdateOperno(String updateOperno) {
		this.updateOperno = updateOperno;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateOperno() {
		return this.createOperno;
	}

	public void setCreateOperno(String createOperno) {
		this.createOperno = createOperno;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgno() {
		return orgno;
	}

	public void setOrgno(String orgno) {
		this.orgno = orgno;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxBranch))
			return false;
		WxBranch castOther = (WxBranch) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getLocationCity() == castOther.getLocationCity()) || (this
						.getLocationCity() != null
						&& castOther.getLocationCity() != null && this
						.getLocationCity().equals(castOther.getLocationCity())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getMobilePhone() == castOther.getMobilePhone()) || (this
						.getMobilePhone() != null
						&& castOther.getMobilePhone() != null && this
						.getMobilePhone().equals(castOther.getMobilePhone())))
				&& ((this.getAtmtype() == castOther.getAtmtype()) || (this
						.getAtmtype() != null && castOther.getAtmtype() != null && this
						.getAtmtype().equals(castOther.getAtmtype())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getLocationX() == castOther.getLocationX()) || (this
						.getLocationX() != null
						&& castOther.getLocationX() != null && this
						.getLocationX().equals(castOther.getLocationX())))
				&& ((this.getLocationY() == castOther.getLocationY()) || (this
						.getLocationY() != null
						&& castOther.getLocationY() != null && this
						.getLocationY().equals(castOther.getLocationY())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())))
				&& ((this.getUpdateOperno() == castOther.getUpdateOperno()) || (this
						.getUpdateOperno() != null
						&& castOther.getUpdateOperno() != null && this
						.getUpdateOperno().equals(castOther.getUpdateOperno())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getCreateOperno() == castOther.getCreateOperno()) || (this
						.getCreateOperno() != null
						&& castOther.getCreateOperno() != null && this
						.getCreateOperno().equals(castOther.getCreateOperno())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getOrgno() == castOther.getOrgno()) || (this
				.getOrgno() != null && castOther.getOrgno() != null && this
				.getOrgno().equals(castOther.getOrgno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getLocationCity() == null ? 0 : this.getLocationCity()
						.hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37
				* result
				+ (getMobilePhone() == null ? 0 : this.getMobilePhone()
						.hashCode());
		result = 37 * result
				+ (getAtmtype() == null ? 0 : this.getAtmtype().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getLocationX() == null ? 0 : this.getLocationX().hashCode());
		result = 37 * result
				+ (getLocationY() == null ? 0 : this.getLocationY().hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		result = 37
				* result
				+ (getUpdateOperno() == null ? 0 : this.getUpdateOperno()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37
				* result
				+ (getCreateOperno() == null ? 0 : this.getCreateOperno()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}