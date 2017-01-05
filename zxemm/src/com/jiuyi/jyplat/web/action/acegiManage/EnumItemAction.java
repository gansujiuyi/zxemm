package com.jiuyi.jyplat.web.action.acegiManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.system.SysEnumItem;
import com.jiuyi.jyplat.service.base.SysEnumItemService;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;

@Namespace("/acegi")
public class EnumItemAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private SysEnumItemService enumItemService;

	Logger log = Logger.getLogger(EnumItemAction.class);

	private Integer id; //枚举明细编号
	private Integer enumId; // 枚举编号
	private String message; // 记录操作消息
	private String goHref; //跳转路径
	private SysEnumItem sysEnumItem; // 枚举明细对象
	private List<SysEnumItem> sysEnumItems; //枚举明细对象集合

	public List<SysEnumItem> getSysEnumItems() {
		return sysEnumItems;
	}

	public void setSysEnumItems(List<SysEnumItem> sysEnumItems) {
		this.sysEnumItems = sysEnumItems;
	}

	public SysEnumItem getSysEnumItem() {
		return sysEnumItem;
	}

	public void setSysEnumItem(SysEnumItem sysEnumItem) {
		this.sysEnumItem = sysEnumItem;
	}

	public Integer getEnumId() {
		return enumId;
	}

	public void setEnumId(Integer enumId) {
		this.enumId = enumId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 跳转至新增枚举明细信息页面
	 * 
	 * @return
	 */
	@Action(value = "/addEnumItem", results = { @Result(name = "success", location = "base/sysenum/addEnumItem.jsp") })
	public String addEnumItem() {
		return SUCCESS;
	}

	/**
	 * 跳转至修改枚举明细信息页面
	 * 
	 * @return
	 */
	@Action(value = "/queryByEnumItemId", results = {
			@Result(name = "success", location = "base/sysenum/updateEnumItem.jsp"),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryByEnumItemId() {
		try {
			if (id == null) {
				throw new Exception("枚举明细Id为空！");
			}
			sysEnumItem = this.enumItemService.queryByEnumItemId(id);
		} catch (Exception e) {
			message = "查询枚举详细信息失败：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			//			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 根据枚举编号查询所有对应枚举明细记录
	 * 
	 * @return
	 */
	@Action(value = "/queryEnumItemByEnumId", results = {
			@Result(name = "success", type = "json", params = {
					"includeProperties",
					"sysEnumItems\\[\\d+\\]\\.id,sysEnumItems\\[\\d+\\]\\.fieldValue,sysEnumItems\\[\\d+\\]\\.displayValue,sysEnumItems\\[\\d+\\]\\.displayOrder,sysEnumItems\\[\\d+\\]\\.status,enumId" }),
			@Result(name = "input", location = "base/error.jsp") })
	public String queryEnumItemByEnumId() {
		try {
			if (enumId == null) {
				throw new Exception("枚举编号为空！");
			}
			sysEnumItems = this.enumItemService.queryByEnumId(enumId);
		} catch (Exception e) {
			message = "查询关联枚举明细信息失败：" + e.toString();
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
	@Action(value = "/saveEnumItem", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String saveEnumItem() {
		try {
			if (sysEnumItem == null) {
				throw new Exception("枚举明细信息为空！");
			}
			this.enumItemService.saveEnumItem(sysEnumItem);
			message = "保存枚举明细信息成功！";
			goHref = "/acegi/queryAllEnum.do";
			log.info("保存枚举明细信息成功！");
		} catch (Exception e) {
			message = "保存枚举明细信息失败：" + e.toString();
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
	@Action(value = "/updateEnumItem", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String updateEnumItem() {
		try {
			if (sysEnumItem == null) {
				throw new Exception("枚举明细信息为空！");
			}
			this.enumItemService.updateEnumItem(sysEnumItem);
			message = "修改枚举明细信息成功！";
			goHref = "/acegi/queryAllEnum.do";
			log.info("修改枚举明细信息成功！Id:" + sysEnumItem.getId());
		} catch (Exception e) {
			message = "修改枚举明细信息失败：" + e.toString();
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
	@Action(value = "/delEnumItem", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message",
					"${message}" }), @Result(name = "input", location = "base/error.jsp") })
	public String delEnumItem() {
		try {
			if (id == null) {
				throw new Exception("枚举明细Id为空！");
			}
			this.enumItemService.delEnumItem(id); //删除枚举明细记录
			message = "删除枚举明细信息成功！";
			goHref = "/acegi/queryAllEnum.do";
			log.info("删除编号为【" + id + "】的枚举明细信息成功！");
		} catch (Exception e) {
			message = "删除枚举明细信息失败：" + e.toString();
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
}
