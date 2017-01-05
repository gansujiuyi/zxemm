package com.jiuyi.jyplat.entity.bankAccoutInfo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "t_contran")
public class ContractTranstion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2343070035010295565L;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	private String id;
	private String contractNo;// 合同号
	private String transtNo;// 交易流水号
	private String ext1;// 扩展字段1

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTranstNo() {
		return transtNo;
	}

	public void setTranstNo(String transtNo) {
		this.transtNo = transtNo;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

}
