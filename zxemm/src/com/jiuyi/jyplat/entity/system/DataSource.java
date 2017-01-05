package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class DataSource implements Serializable {

	private static final long serialVersionUID = 949364798902862260L;

	@Id
	//	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_DataSource", allocationSize = 1, initialValue = 1)
	private Integer sourceId; // ID，自增长
	private String sourceName; // 交易日期
	private String dbURI;
	private String dbUser;
	private String dbPW;
	private String dbSID;
	private String dbType;
	private String tableName;

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDbURI() {
		return dbURI;
	}

	public void setDbURI(String dbURI) {
		this.dbURI = dbURI;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPW() {
		return dbPW;
	}

	public void setDbPW(String dbPW) {
		this.dbPW = dbPW;
	}

	public String getDbSID() {
		return dbSID;
	}

	public void setDbSID(String dbSID) {
		this.dbSID = dbSID;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
