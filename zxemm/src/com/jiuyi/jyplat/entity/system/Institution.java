package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 组织机构
 * @author leiyongjun
 *  May 24, 2011  5:43:00 PM
 */
@Entity
public class Institution implements Serializable {

	@Transient
	private static final long serialVersionUID = -6415660493773523704L;
	private Integer institutionId;// 机构Id
	private String institutionNo;// 机构编号
	private String institutionName;// 机构名称
	private Integer parentInstitutionId;// 父机构Id
	private String description;// 说明
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Operator> operators = new HashSet<Operator>(0);

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_Institution", allocationSize = 1, initialValue = 1)
	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getInstitutionNo() {
		return institutionNo;
	}

	public void setInstitutionNo(String institutionNo) {
		this.institutionNo = institutionNo;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getParentInstitutionId() {
		return parentInstitutionId;
	}

	public void setParentInstitutionId(Integer parentInstitutionId) {
		this.parentInstitutionId = parentInstitutionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inst")
	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

}
