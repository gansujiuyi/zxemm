package com.jiuyi.jyplat.web.action.capitalManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.service.condo.IBuildingInfoService;
import com.jiuyi.jyplat.service.condo.ICondoPayInfoService;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.service.condo.IRefundPayInfoService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 对账异常信息
 */
@Namespace("/capitalManage")
public class CapitalManageAction extends BaseActionSupport {
	private static final long serialVersionUID = -6313741202180248508L;
	Logger log = Logger.getLogger(CapitalManageAction.class);

	// -------------
	private Query query; // 分页查询对象

	private PageFinder<BuildingInfo> pageFinder; // 审核划款分页查询对象
	@Resource
	private IBuildingInfoService buildingInfoService;
	@Resource
	private IContactInfoService contactInfoService;
	@Resource
	private IRefundPayInfoService refundPayInfoService;
	@Resource
	private ICondoPayInfoService condoPayInfoService;
	private BuildingInfo buildingInfo;
	private String buildingId;// 楼幢号
	private List<ContactInfo> contactInfos;
	private List<RefundPayInfo> refundPayInfos;
	private List<CondoPayInfo> condoPayInfos;
    private String contactNo;
	private String forSearch;
	

	/**
	 * 信息查询 信息报表查询----分页
	 * 
	 * @return
	 */
	@Action(value = "/queryBofficeCapitalInfoAll", results = {
			@Result(name = SUCCESS, location = "accountCompare/accountCompareexp.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String queryBofficeCapitalInfoAll() {
		try {
			if (forSearch != null && forSearch.trim().equals("true")) {
				query = new Query();
			}
			pageFinder = buildingInfoService.queryBuildingInfoAll(buildingId,
					query == null ? new Query() : query);
		} catch (Exception e) {
			log.error("分页查询对账信息记录出现错误：" + e.getMessage(), e);
			this.addActionMessage("分页查询对账信息记录出现错误！");
			return INPUT;
		}

		return SUCCESS;
	}
	
	
	/**
	 * 根据楼幢号查询合同信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryContractByBuild", results = {
			@Result(name = SUCCESS, location = "condo/contactInfo.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String queryContractByBuild() {
		try {
			contactInfos = contactInfoService.getByHouseNo(buildingId , contactNo);
		} catch (Exception e) {
			log.error("根据楼幢号查询合同信息出现错误：" + e.getMessage(), e);
			this.addActionMessage("根据楼幢号查询合同信息出现错误！");
			return INPUT;
		}

		return SUCCESS;
	}
	
	/**
	 * 根据楼幢号查询合同支付信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryContractPayInfo", results = {
			@Result(name = SUCCESS, location = "condo/contactPayInfo.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String queryContractPayInfo() {
		try {
			condoPayInfos = condoPayInfoService.queryCandoPayInfo(contactNo);
		} catch (Exception e) {
			log.error("根据楼幢号查询合同信息出现错误：" + e.getMessage(), e);
			this.addActionMessage("根据楼幢号查询合同信息出现错误！");
			return INPUT;
		}

		return SUCCESS;
	}
	
	/**
	 * 根据楼幢号查询合同退款信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryContactRefPayInfo", results = {
			@Result(name = SUCCESS, location = "condo/contactRefPayInfo.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String queryContactRefPayInfo() {
		try {
			refundPayInfos = refundPayInfoService.getByContactNo(contactNo);
		} catch (Exception e) {
			log.error("根据楼幢号查询合同信息出现错误：" + e.getMessage(), e);
			this.addActionMessage("根据楼幢号查询合同信息出现错误！");
			return INPUT;
		}

		return SUCCESS;
	}
	
	

	public List<CondoPayInfo> getCondoPayInfos() {
		return condoPayInfos;
	}


	public void setCondoPayInfos(List<CondoPayInfo> condoPayInfos) {
		this.condoPayInfos = condoPayInfos;
	}

	public List<RefundPayInfo> getRefundPayInfos() {
		return refundPayInfos;
	}


	public void setRefundPayInfos(List<RefundPayInfo> refundPayInfos) {
		this.refundPayInfos = refundPayInfos;
	}


	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public PageFinder<BuildingInfo> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<BuildingInfo> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public BuildingInfo getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
