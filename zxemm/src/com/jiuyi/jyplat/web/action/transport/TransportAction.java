package com.jiuyi.jyplat.web.action.transport;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.bankAccoutInfo.CheckErrorInfo;
import com.jiuyi.jyplat.service.bankAccoutInfo.ICheckErrorInfoService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * @author heq
 */
@Namespace("/transport")
public class TransportAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(TransportAction.class);

	private String forSearch;
	private Query query; // 分页查询对象
	private PageFinder<CheckErrorInfo> pageFinder; // 审核划款分页查询对象

	private String buildId;// 楼幢号
	private String transactionSeqNum;// 流水号
	private List<CheckErrorInfo> checkErrorInfos;
	@Resource
	private ICheckErrorInfoService checkErrorInfoService;

	@Action(value = "/qurBofficePayTransport", results = {
			@Result(name = SUCCESS, location = "accountCompare/accountCompare.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String qurBofficePayTransport() {
		try {
			if (forSearch != null && forSearch.trim().equals("true")) {
				query = new Query();
			}
			 pageFinder =checkErrorInfoService.CheckErrorInfo(buildId, transactionSeqNum, query == null ? new Query() : query);
		} catch (Exception e) {
			log.error("分页查询对账信息记录出现错误：" + e.getMessage(), e);
			this.addActionMessage("分页查询对账信息记录出现错误！");
			return INPUT;
		}

		return SUCCESS;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public PageFinder<CheckErrorInfo> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<CheckErrorInfo> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public String getBuildId() {
		return buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public String getTransactionSeqNum() {
		return transactionSeqNum;
	}

	public void setTransactionSeqNum(String transactionSeqNum) {
		this.transactionSeqNum = transactionSeqNum;
	}

	public List<CheckErrorInfo> getCheckErrorInfos() {
		return checkErrorInfos;
	}

	public void setCheckErrorInfos(List<CheckErrorInfo> checkErrorInfos) {
		this.checkErrorInfos = checkErrorInfos;
	}

}
