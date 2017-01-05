package com.jiuyi.jyplat.web.action.acegiManage;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.entity.system.SysEnumItem;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/acegi")
public class EnumAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private SysEnumService enumService;

	Logger log = Logger.getLogger(EnumAction.class);

	private SysEnum sysEnum; // 枚举总表对象
	private Integer enumId; // 枚举编号
	private PageFinder<SysEnum> pageFinder; // 分页数据对象
	private Query query; // 分页查询对象
	private String enumName, tableName, fieldName; // 枚举名称
	private String message; // 记录操作消息
	private String goHref; //跳转路径
	private String enumIds;// 页面请求的多个枚举编号组成的字符串
	private SysEnumItem sysEnumItem; // 枚举明细对象
	private String forSearch; //值为“true”时代表带条件查询

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public SysEnumItem getSysEnumItem() {
		return sysEnumItem;
	}

	public void setSysEnumItem(SysEnumItem sysEnumItem) {
		this.sysEnumItem = sysEnumItem;
	}

	public String getEnumIds() {
		return enumIds;
	}

	public void setEnumIds(String enumIds) {
		this.enumIds = enumIds;
	}

	public SysEnum getSysEnum() {
		return sysEnum;
	}

	public void setSysEnum(SysEnum sysEnum) {
		this.sysEnum = sysEnum;
	}

	public Integer getEnumId() {
		return enumId;
	}

	public void setEnumId(Integer enumId) {
		this.enumId = enumId;
	}

	public PageFinder<SysEnum> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<SysEnum> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 跳转至新增枚举信息页面
	 * 
	 * @return
	 */
	@Action(value = "/addEnum", results = { @Result(name = "success", location = "base/sysenum/addEnum.jsp") })
	public String addEnum() {
		return SUCCESS;
	}

	/**
	 * 跳转至修改枚举信息页面
	 * 
	 * @return
	 */
	@Action(value = "/queryByEnumId", results = { @Result(name = "success", location = "base/sysenum/updateEnum.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryByEnumId() {
		try {
			if (enumId == null) {
				throw new Exception("枚举Id为空！");
			}
			sysEnum = this.enumService.queryByEnumId(enumId);
		} catch (Exception e) {
			message = "查询枚举详细信息出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 分页查询枚举信息（模糊查询）
	 * 
	 * @return
	 */
	@Action(value = "/queryAllEnum", results = { @Result(name = "success", location = "base/sysenum/enumList.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryAllEnum() {
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();

			pageFinder = this.enumService.queryAllEnum(enumName, tableName, fieldName, query == null ? new Query()
					: query);
		} catch (Exception e) {
			message = "枚举信息查询出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存新增枚举信息
	 * 
	 * @return
	 */
	@Action(value = "/saveEnum", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveEnum() {
		try {
			if (sysEnum == null) {
				throw new Exception("枚举信息为空！");
			}

			//查询枚举信息
			SysEnum newSysEnum = this.enumService.queryEnumItem(sysEnum);
			//若查询到枚举信息则提示错误 新增枚举失败：对应表明和字段名都重复！
			if (newSysEnum != null) {
				throw new Exception("对应表明和字段名都重复！");
			}
			this.enumService.saveEnum(sysEnum);
			message = "保存枚举信息成功！";
			goHref = "/acegi/queryAllEnum.do";
			log.info("保存枚举信息成功！");
		} catch (Exception e) {
			message = "保存枚举信息失败：" + e.getMessage();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 修改枚举信息
	 * 
	 * @return
	 */
	@Action(value = "/updateEnum", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String updateEnum() {
		try {
			if (sysEnum == null) {
				throw new Exception("枚举信息为空！");
			}
			this.enumService.updateEnum(sysEnum);
			message = "修改枚举信息成功！";
			goHref = "/acegi/queryAllEnum.do";
			log.info("修改枚举信息成功！enumId:" + sysEnum.getEnumId());
		} catch (Exception e) {
			message = "修改枚举信息出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除枚举信息
	 * 
	 * @return
	 */
	@Action(value = "/delEnum", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String delEnum() {
		try {
			if (enumIds == null || enumIds.trim().equals("")) {
				throw new Exception("枚举编号为空！");
			}
			this.enumService.delEnum(enumIds);
			message = "删除枚举信息成功！";
			goHref = "/acegi/queryAllEnum.do";
		} catch (Exception e) {
			message = "删除枚举信息出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
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
}
