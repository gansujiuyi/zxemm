package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
public class SysEnum implements Serializable {

	private Integer enumId; // 枚举id，自动增长
	private String tableName; // 对应表名，特例为(Pubilc)
	private String fieldName; // 字段名
	private String enumName; // 枚举名，枚举说明
	private String status; // 是否可用，1是0否
	private String systemFlag; // 是否系统定义，1是0否（1表示不可修改）

	private Set<SysEnumItem> sysEnumItems = new HashSet<SysEnumItem>(0);

	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_SysEnum", allocationSize = 1, initialValue = 1)
	public Integer getEnumId() {
		return enumId;
	}

	public void setEnumId(Integer enumId) {
		this.enumId = enumId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "sysEnum")
	@OrderBy("displayOrder")
	public Set<SysEnumItem> getSysEnumItems() {
		return sysEnumItems;
	}

	public void setSysEnumItems(Set<SysEnumItem> sysEnumItems) {
		this.sysEnumItems = sysEnumItems;
	}

}
