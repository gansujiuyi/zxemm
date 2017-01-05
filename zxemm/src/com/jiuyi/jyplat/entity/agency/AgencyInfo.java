package com.jiuyi.jyplat.entity.agency;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Citycode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_AGENCYINFO")
public class AgencyInfo  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID主键
	 */
	@Id
     private String id;
     /**
      * 中介名称
      */
     private String agencyname;
     /**
      * 经营地址
      */
     private String agencyaddress;
     /**
      * 成立时间
      */
     private String buildtime;
     
     /**
      *注册资金 
      */
     private String registermoney;
     /**
      * 法人
      */
     private String legalperson;
     /**
      * 流动资金
      */
     private String workingfund;
     /**
      * 办公电话
      */
     private String officephone;
     /**
      * 手机号码
      */
     private String cellphone;
     /**
      * 备案号码
      */
     private String recordnumber;
     /**
      * 从业人数
      */
     private String employeenNumber;
     /**
      * 已培训从业人数
      */
     private String trainedEmployeeNumber;
     /**
      * 中介登陆账号
      */
     private String agencyloginaccount;
     /**
      * 中介登陆密码
      */
     private String agencyloginpwd;
     /**
      * 资金监管账户户名
      */
     private String fundaccountname;
     /**资金监管账户卡号
      * 
      */
     private String fundaccountno;
     
     /**
      * 佣金户名
      */
     private String commissionaccountname;
     /**
      * 佣金卡号
      */
     private String commissionaccountno;
     
     private String onfundaccountname;//资金监管账号开户名称
     private String oncommissionaccountname;//佣金账户名称
     
     /**
      * 经济人所属网点号
      */
     private String subagencyno;
     /**
      * 经济人所属网点名字
      */
     private String subagencyname;
     
     /**
      * 是否是经纪人表示：0，是中介，1，是经纪人
      */
     private String isbroker;
     
     /**
      * 中介状态 :0 正常
      *        1 冻结
      */
     private String status;
     
     /**
      * 经纪人所属中介编号
      */
     private String brokeragencyno;
     
     /**
      * LOGO图片
      */
    private String logo;     
  
	/**
     * 证照名称
     */
    private String  zname;    
    
	/**
     * 证照图片
     */
    private String zpicture;
    
    private String infoQQ;//咨询qq;
 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgencyname() {
		return agencyname;
	}
	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}
	public String getAgencyaddress() {
		return agencyaddress;
	}
	public void setAgencyaddress(String agencyaddress) {
		this.agencyaddress = agencyaddress;
	}
	public String getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}
	public String getRegistermoney() {
		return registermoney;
	}
	public void setRegistermoney(String registermoney) {
		this.registermoney = registermoney;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getWorkingfund() {
		return workingfund;
	}
	public void setWorkingfund(String workingfund) {
		this.workingfund = workingfund;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public String getSubagencyname() {
		return subagencyname;
	}
	public void setSubagencyname(String subagencyname) {
		this.subagencyname = subagencyname;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getRecordnumber() {
		return recordnumber;
	}
	public void setRecordnumber(String recordnumber) {
		this.recordnumber = recordnumber;
	}
	public String getEmployeenNumber() {
		return employeenNumber;
	}
	public void setEmployeenNumber(String employeenNumber) {
		this.employeenNumber = employeenNumber;
	}
	public String getTrainedEmployeeNumber() {
		return trainedEmployeeNumber;
	}
	public void setTrainedEmployeeNumber(String trainedEmployeeNumber) {
		this.trainedEmployeeNumber = trainedEmployeeNumber;
	}
	public String getAgencyloginaccount() {
		return agencyloginaccount;
	}
	public void setAgencyloginaccount(String agencyloginaccount) {
		this.agencyloginaccount = agencyloginaccount;
	}
	public String getAgencyloginpwd() {
		return agencyloginpwd;
	}
	public void setAgencyloginpwd(String agencyloginpwd) {
		this.agencyloginpwd = agencyloginpwd;
	}
	public String getFundaccountname() {
		return fundaccountname;
	}
	public void setFundaccountname(String fundaccountname) {
		this.fundaccountname = fundaccountname;
	}
	public String getFundaccountno() {
		return fundaccountno;
	}
	public void setFundaccountno(String fundaccountno) {
		this.fundaccountno = fundaccountno;
	}
	public String getCommissionaccountname() {
		return commissionaccountname;
	}
	public void setCommissionaccountname(String commissionaccountname) {
		this.commissionaccountname = commissionaccountname;
	}
	public String getCommissionaccountno() {
		return commissionaccountno;
	}
	public void setCommissionaccountno(String commissionaccountno) {
		this.commissionaccountno = commissionaccountno;
	}
	public String getSubagencyno() {
		return subagencyno;
	}
	public void setSubagencyno(String subagencyno) {
		this.subagencyno = subagencyno;
	}
	public String getIsbroker() {
		return isbroker;
	}
	public void setIsbroker(String isbroker) {
		this.isbroker = isbroker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBrokeragencyno() {
		return brokeragencyno;
	}
	public void setBrokeragencyno(String brokeragencyno) {
		this.brokeragencyno = brokeragencyno;
	}
	public String getOnfundaccountname() {
		return onfundaccountname;
	}
	public void setOnfundaccountname(String onfundaccountname) {
		this.onfundaccountname = onfundaccountname;
	}
	public String getOncommissionaccountname() {
		return oncommissionaccountname;
	}
	public void setOncommissionaccountname(String oncommissionaccountname) {
		this.oncommissionaccountname = oncommissionaccountname;
	}
	
	
	  public String getInfoQQ() {
		return infoQQ;
	}
	public void setInfoQQ(String infoQQ) {
		this.infoQQ = infoQQ;
	}
	public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public String getZname() {
			return zname;
		}
		public void setZname(String zname) {
			this.zname = zname;
		}
	    
		public String getZpicture() {
			return zpicture;
		}
		public void setZpicture(String zpicture) {
			this.zpicture = zpicture;
		}
}