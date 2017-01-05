package com.jiuyi.jyplat.entity.condo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * <P>新房开发商信息</P>
 * @author sunzb
 */
@Entity(name = "t_blockinfo")
public class BlockInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	private String id;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 小区编号
	 */
	private String blockNo;
	/**
	 * 项目资金监管编号
	 */
	private String fundNo;
	/**
	 * 项目资金监管名称
	 */
	private String fundName;
	/**
	 * 被用字段
	 */
	private String str1;
	/**
	 *  被用字段
	 */
	private String str2;
	
	/**
	 * 被用字段
	 */
	private String str3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public String getFundNo() {
		return fundNo;
	}

	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

}
