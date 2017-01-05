package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "t_developInfo")
public class DevelopInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	private String DevelopName;
	private String DevelopPassWord;
	private String blockInfoId;//合同编号
	private String buildingId;//地幢号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDevelopName() {
		return DevelopName;
	}
	public void setDevelopName(String developName) {
		DevelopName = developName;
	}
	public String getDevelopPassWord() {
		return DevelopPassWord;
	}
	public void setDevelopPassWord(String developPassWord) {
		DevelopPassWord = developPassWord;
	}
	public String getBlockInfoId() {
		return blockInfoId;
	}
	public void setBlockInfoId(String blockInfoId) {
		this.blockInfoId = blockInfoId;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	

}
