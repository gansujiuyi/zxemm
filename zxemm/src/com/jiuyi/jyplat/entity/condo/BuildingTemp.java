package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "t_buildingTemp")
public class BuildingTemp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	private String buildingId;//地幢号
	private String projectReguNo;//项目监管编号
	private String projetReguName;//项目监管名称
	private String status;//是否获取企业信息0：未获取，1：已获取
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getProjectReguNo() {
		return projectReguNo;
	}
	public void setProjectReguNo(String projectReguNo) {
		this.projectReguNo = projectReguNo;
	}
	public String getProjetReguName() {
		return projetReguName;
	}
	public void setProjetReguName(String projetReguName) {
		this.projetReguName = projetReguName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
