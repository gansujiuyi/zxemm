/**
 * <p>Title:             	  Wx_BranchAction.java
 * <p>Description:       
 * <p>Copyright:         Copyright (C), 2013, UXUNCHINA.
 * <p>Company:          UXUNCHINA
 * <p>Project:			  smsplat
 * @author          	 	  YLiang
 * @version	 		      V1.0
 * @see		      		      com.jiuyi.smsplat.web.action.wx.wxBranch
 *
 **************************************************************************************************************
 *   Date      *		Time			*      Developers ID      *      Modlog        *         Description         *
 **************************************************************************************************************
 * 2013-12-19	    下午9:03:56  			YLiang                             	   
 */
package com.jiuyi.jyplat.web.action.wxbranch;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.entity.wx.WxBranch;
import com.jiuyi.jyplat.entity.wx.WxCity;
import com.jiuyi.jyplat.entity.wx.WxRegion;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.service.wx.IWx_BranchService;
import com.jiuyi.jyplat.service.wx.IWx_CityService;
import com.jiuyi.jyplat.service.wx.IWx_RegionService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;


/**
 * @author YLiang
 *
 */
@Namespace("/wxBranch")
public class Wx_BranchAction extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7566909899878259522L;

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private SysEnumService sysEnumService;
	@Resource
	private IWx_BranchService wx_BranchService;
	@Resource 
	private IWx_CityService wx_cityService;
	@Resource
	private IWx_RegionService wx_RegionService;
	
	private Query query;
	private PageFinder pageFinder;
	private String orderby;
	private String forSearch,goHref,message;
	private WxBranch wxbranch;
	private List<WxBranch> branchs;//机构
	private List<WxCity> wxCitys;//城市
	private List<WxRegion> wxRegions;//区
	private String arrList;//将wxRegions转换成String
	
	private SysEnum locationCitysysEnum;
	private SysEnum typesysEnum;
	private SysEnum statussysEnum;
	private SysEnum atmtypesysEnum;
	
	private String  wxbranchids;
	
	private String step;
	private String status;
	
	
	/**
	 * 查询网点/ATM信息列表
	 * @return
	 */
	@Action(value = "/queryAllWXBranchList", 
			results = {
			@Result(name = "success", location = "wx/wxbranch/wxBranchList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllWXBranchList() {
		try {
			locationCitysysEnum = querySysEnum("WX_BRANCH","LocationCity");
			typesysEnum = querySysEnum("WX_BRANCH","Type");
			statussysEnum = querySysEnum("WX_BRANCH","Status");
			atmtypesysEnum = querySysEnum("WX_BRANCH","ATMType");
			if( forSearch != null && forSearch.trim().equals("true") )
				query = new Query();
			pageFinder = wx_BranchService.queryAllWxBranch(wxbranch, orderby, query == null? new Query() : query);
			wxCitys = wx_cityService.qurWxCityList();//城市list
			wxRegions = wx_RegionService.qurWxRegionList();//区list
			arrList = "";
			for (int i = 0; i < wxRegions.size(); i++) {
				arrList += wxRegions.get(i).getCityId() + "|" + wxRegions.get(i).getRegionName() + "|" +  wxRegions.get(i).getId();
				if(i != wxRegions.size() - 1){
					arrList += ",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error( "查询网点/ATM信息列表错误:" + e.getMessage() );
			this.addActionMessage("查询网点/ATM信息列表错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 增加网点/ATM信息
	 * @return
	 */
	@Action(value = "/addWXBranch", 
			results = {
			@Result(name = "success", location = "wx/wxbranch/addwxBranch.jsp"),
			@Result(name = "input", location = "base/alertMessage.jsp", params={"goHref","${goHref}","message","${message}"}),
			@Result(name = "error", location = "base/error.jsp") })
	public String addWXBranch() {
		
		try {
			//所在市区级联信息 ----- start
			wxCitys = wx_cityService.qurWxCityList();//城市list
			wxRegions = wx_RegionService.qurWxRegionList();//区list
			arrList = "";
			for (int i = 0; i < wxRegions.size(); i++) {
				arrList += wxRegions.get(i).getCityId() + "|" + wxRegions.get(i).getRegionName() + "|" +  wxRegions.get(i).getId();
				if(i != wxRegions.size() - 1){
					arrList += ",";
				}
			}
			//所在市区级联信息  ------end
			
			if("0".equals(step)){
				locationCitysysEnum = querySysEnum("WX_BRANCH","LocationCity");
				typesysEnum = querySysEnum("WX_BRANCH","Type");
				statussysEnum = querySysEnum("WX_BRANCH","Status");
				atmtypesysEnum = querySysEnum("WX_BRANCH","ATMType");
				return SUCCESS;
			}else{
				wx_BranchService.save(wxbranch);
				goHref = "/wxBranch/queryAllWXBranchList.do";
				message = "增加网点/ATM信息成功！";
				return INPUT;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error( "增加网点/ATM信息错误:" + e.getMessage() );
			this.addActionMessage("增加网点/ATM信息错误,请联系管理员!");
			return ERROR;
		}
	}
	
	/**
	 * 编辑网点/ATM信息
	 * @return
	 */
	@Action(value = "/modifyWXBranch", 
			results = {
			@Result(name = "success", location = "wx/wxbranch/modifywxBranch.jsp"),
			@Result(name = "input", location = "base/alertMessage.jsp", params={"goHref","${goHref}","message","${message}"}),
			@Result(name = "error", location = "base/error.jsp") })
	public String modifyWXBranch() {
		try {
			//所在市区级联信息 ----- start
			wxCitys = wx_cityService.qurWxCityList();//城市list
			wxRegions = wx_RegionService.qurWxRegionList();//区list
			arrList = "";
			for (int i = 0; i < wxRegions.size(); i++) {
				arrList += wxRegions.get(i).getCityId() + "|" + wxRegions.get(i).getRegionName() + "|" +  wxRegions.get(i).getId();
				if(i != wxRegions.size() - 1){
					arrList += ",";
				}
			}
			//所在市区级联信息  ------end
			
			if("0".equals(step)){
				wxbranch = wx_BranchService.findById(wxbranch);
				locationCitysysEnum = querySysEnum("WX_BRANCH","LocationCity");
				typesysEnum = querySysEnum("WX_BRANCH","Type");
				statussysEnum = querySysEnum("WX_BRANCH","Status");
				atmtypesysEnum = querySysEnum("WX_BRANCH","ATMType");
				return SUCCESS;
			}else{
				wx_BranchService.update(wxbranch);
				goHref = "/wxBranch/queryAllWXBranchList.do";
				message = "编辑网点/ATM信息成功！";
				return INPUT;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error( "编辑网点/ATM信息错误:" + e.getMessage() );
			this.addActionMessage("编辑网点/ATM信息错误,请联系管理员!");
			return ERROR;
		}
	}
	
	/**
	 * 删除网点/ATM信息
	 * @return
	 */
	@Action(value = "/delWXBranch", 
			results = {
			@Result(name = "success", location = "base/alertMessage.jsp",params={"goHref","${goHref}","message","${message}"}),
			@Result(name = "input", location = "base/error.jsp") })
	public String delWXBranch() {
		try {
			wx_BranchService.delete(wxbranchids);
			message = "删除网点/ATM信息成功！";
			goHref = "/wxBranch/queryAllWXBranchList.do";
		} catch (Exception e) {
			log.error( "删除网点/ATM信息错误:" + e.getMessage() );
			this.addActionMessage("删除网点/ATM信息错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Action(value = "/searchBranch", results = { 
			@Result(name = SUCCESS, type = "json", params = { "includeProperties","status,tip,branchs.*" })
	})
	public String searchBranch(){
		String name = getRequest().getParameter("name");
		if(StringUtils.isNotBlank(name)){
			try{
				name = name;//new String(name.getBytes("ISO8859-1"), "UTF-8");
			}catch(Exception e){
				log.error("将【" + name + "】转码成utf-8失败。", e);
			}
		}
		branchs = wx_BranchService.search(name);
		return SUCCESS;
	}
	
	/**
	 * 修改网点/ATM信息状态
	 * @return
	 */
	@Action(value = "/updateWXBranch", 
			results = {
			@Result(name = "success", location = "base/alertMessage.jsp",params={"goHref","${goHref}","message","${message}"}),
			@Result(name = "input", location = "base/error.jsp") })
	public String updateWXBranch() {
		try {
			wx_BranchService.update(wxbranchids, status);
			message = "修改网点/ATM信息状态成功！";
			goHref = "/wxBranch/queryAllWXBranchList.do";
		} catch (Exception e) {
			log.error( "修改网点/ATM信息状态错误:" + e.getMessage() );
			this.addActionMessage("修改网点/ATM信息状态错误,请联系管理员!");
			return INPUT;
		}
		return SUCCESS;
	}
	
	private SysEnum querySysEnum(String tableName,String fieldName) throws Exception{
		SysEnum sysEnum = new SysEnum();
		sysEnum.setTableName(tableName);
		sysEnum.setFieldName(fieldName);
		sysEnum.setStatus("1");
		return sysEnumService. queryEnumItem(sysEnum);
	}

	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public PageFinder getPageFinder() {
		return pageFinder;
	}
	public void setPageFinder(PageFinder pageFinder) {
		this.pageFinder = pageFinder;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getForSearch() {
		return forSearch;
	}
	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}
	public String getGoHref() {
		return goHref;
	}
	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public WxBranch getWxbranch() {
		return wxbranch;
	}
	public void setWxbranch(WxBranch wxbranch) {
		this.wxbranch = wxbranch;
	}
	public SysEnum getLocationCitysysEnum() {
		return locationCitysysEnum;
	}
	public void setLocationCitysysEnum(SysEnum locationCitysysEnum) {
		this.locationCitysysEnum = locationCitysysEnum;
	}
	public SysEnum getTypesysEnum() {
		return typesysEnum;
	}
	public void setTypesysEnum(SysEnum typesysEnum) {
		this.typesysEnum = typesysEnum;
	}
	public SysEnum getStatussysEnum() {
		return statussysEnum;
	}
	public void setStatussysEnum(SysEnum statussysEnum) {
		this.statussysEnum = statussysEnum;
	}
	public SysEnum getAtmtypesysEnum() {
		return atmtypesysEnum;
	}
	public void setAtmtypesysEnum(SysEnum atmtypesysEnum) {
		this.atmtypesysEnum = atmtypesysEnum;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getWxbranchids() {
		return wxbranchids;
	}
	public void setWxbranchids(String wxbranchids) {
		this.wxbranchids = wxbranchids;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<WxBranch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<WxBranch> branchs) {
		this.branchs = branchs;
	}
	public List<WxCity> getWxCitys() {
		return wxCitys;
	}
	public void setWxCitys(List<WxCity> wxCitys) {
		this.wxCitys = wxCitys;
	}
	public List<WxRegion> getWxRegions() {
		return wxRegions;
	}
	public void setWxRegions(List<WxRegion> wxRegions) {
		this.wxRegions = wxRegions;
	}


	public String getArrList() {
		return arrList;
	}


	public void setArrList(String arrList) {
		this.arrList = arrList;
	}
	
}
