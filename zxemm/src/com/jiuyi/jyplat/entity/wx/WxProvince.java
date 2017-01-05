package com.jiuyi.jyplat.entity.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 网点省份相关实体
 * <P>TODO</P>
 * @author pengq
 */
@Entity
@Table(name="wx_province")
public class WxProvince implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "WX_PROVINCE_SEQ", allocationSize = 1, initialValue = 1)
	private Integer id;//自增id
	private String proName;//省名
	
	
	public WxProvince() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WxProvince(Integer id, String proName) {
		super();
		this.id = id;
		this.proName = proName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
	

}
