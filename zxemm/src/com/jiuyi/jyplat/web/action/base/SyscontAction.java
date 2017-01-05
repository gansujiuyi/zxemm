package com.jiuyi.jyplat.web.action.base;

import java.io.File;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.base.Syscontent;
import com.jiuyi.jyplat.entity.system.SysEnum;
import com.jiuyi.jyplat.service.base.ISyscontentService;
import com.jiuyi.jyplat.service.base.SysEnumService;
import com.jiuyi.jyplat.service.wx.IWx_BranchService;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Namespace("/sys")
public class SyscontAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(SyscontAction.class);

	@Resource
	private ISyscontentService syscontService;
	@Resource
	private SysEnumService sysEnumService;

	@Resource
	private IWx_BranchService wx_BranchService;

	private PageFinder pageFinder; // 分页查询数据对象
	private Query query; // 分页对象
	private String message, goHref, forSearch; // 记录操作消息
	private Syscontent syscont;
	private SysEnum onChannel, contType, sysStatus;
	private String contid, status;
	private File upload;
	private String uploadFileName;
	private String type; // 记录前台传过来的值

	private Integer zxxgtSize;// 记录已上传的图片数量
	private Integer imgIndex = 0;// 默认从0开始，如果是修改，则取数据库中最大值。


	private String conttype;
	private String sequence;

	/**
	 * 跳转到新增内容页面
	 * 
	 * @return
	 */
	@Action(value = "/addSyscont", results = { @Result(name = "success", location = "base/syscont/addSyscont.jsp") })
	public String addSyscont() {
		try {
			onChannel = this.querySysEnum(onChannel, "syscontent", "onchannel",
					"1");
			contType = this.querySysEnum(contType, "syscontent", "conttype",
					"1");
		} catch (Exception e) {
			message = "跳转新增系统内容页面出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}

		return SUCCESS;
	}

	/**
	 * 跳转到修改系统内容页面
	 * 
	 * @return
	 */
	@Action(value = "/updateSyscont", results = {
			@Result(name = "success", location = "base/syscont/updateSyscont.jsp"),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String updateSyscont() {
		try {
			syscont = syscontService.querySyscontById(contid);
			if (syscont.getContimg() != null) {
				zxxgtSize = 0;
				imgIndex = 1;
			}
			syscont.setImgUrl(ConfigManager.getInstance().getConfigItem(
					"resource_web_path", "defaultVal")
					+ syscont.getContimg());

			/**
			 * 涉及到枚举的，全部注释 onChannel = this.querySysEnum(onChannel,
			 * "syscontent", "onchannel", "1"); contType =
			 * this.querySysEnum(contType, "syscontent", "conttype", "1");
			 */

		} catch (Exception e) {
			message = "跳转修改系统内容页面出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}
		return SUCCESS;
	}

	@Action(value = "/delSyscont", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String delSyscont() {
		try {

			String contid = this.getRequest().getParameter("contid");
			String[] ids = contid.split(",");
			for (int i = 0; i < ids.length; i++) {
				Syscontent syscont = syscontService.querySyscontById(ids[i]);
				syscontService.delSyscont(syscont);
			}
			message = "删除系统内容成功！";
			goHref = "/sys/querySyscont.do";
		} catch (Exception e) {
			message = "删除系统内容出错：" + e.toString();
			log.error(message, e);
			this.addActionMessage(message);
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 功能说明：对发布的系统内容信息展示的启用和停用
	 * 
	 * @return
	 */
	@Action(value = "/authSyscont", results = {
			@Result(name = "success", location = "base/alertMessage.jsp", params = {
					"goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String authSyscont() {
		status = getRequest().getParameter("status");
		try {
			if (!Utils.notEmptyString(contid) || !Utils.notEmptyString(status))
				throw new Exception("无效数据！");

			syscontService.updateSyscontStatus(contid, status);
			if ("0004".equals(status))
				message = "启用成功！";
			else
				message = "停用成功！";
			goHref = "/sys/querySyscont.do";
		} catch (Exception e) {
			if ("0004".equals(status))
				message = "启用系统内容出错：" + e.toString();
			else
				message = "停用系统内容出错：" + e.toString();
			log.error(message);
			this.addActionMessage(message);
			return INPUT;
		}
		return SUCCESS;
	}

	private SysEnum querySysEnum(SysEnum sysEnum, String tableName,
			String fieldName, String status) throws Exception {
		// 商户组下所有商户
		sysEnum = new SysEnum();
		sysEnum.setTableName(tableName);
		sysEnum.setFieldName(fieldName);
		sysEnum.setStatus(status);
		return sysEnumService.queryEnumItem(sysEnum);
	}

	public PageFinder getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder pageFinder) {
		this.pageFinder = pageFinder;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public Syscontent getSyscont() {
		return syscont;
	}

	public void setSyscont(Syscontent syscont) {
		this.syscont = syscont;
	}

	public SysEnum getOnChannel() {
		return onChannel;
	}

	public void setOnChannel(SysEnum onChannel) {
		this.onChannel = onChannel;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public SysEnum getContType() {
		return contType;
	}

	public void setContType(SysEnum contType) {
		this.contType = contType;
	}

	public String getContid() {
		return contid;
	}

	public void setContid(String contid) {
		this.contid = contid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SysEnum getSysStatus() {
		return sysStatus;
	}

	public void setSysStatus(SysEnum sysStatus) {
		this.sysStatus = sysStatus;
	}

	public Integer getZxxgtSize() {
		return zxxgtSize;
	}

	public void setZxxgtSize(Integer zxxgtSize) {
		this.zxxgtSize = zxxgtSize;
	}

	public Integer getImgIndex() {
		return imgIndex;
	}

	public void setImgIndex(Integer imgIndex) {
		this.imgIndex = imgIndex;
	}


	public String getConttype() {
		return conttype;
	}

	public void setConttype(String conttype) {
		this.conttype = conttype;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
