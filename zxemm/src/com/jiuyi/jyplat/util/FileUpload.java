package com.jiuyi.jyplat.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.util.IsSynchronousImgUtil;

/**
 * 说明：单个文件上传Action类
 * @author Frank
 *
 */
//@Namespace("/points")
public class FileUpload extends BaseActionSupport {
	private File file; //上传的文件    
	private String fileName; //文件名称    
	private String fileContentType; //文件类型   

	//	@Action(value = "/uploadFile", results = {
	//			@Result(name = "success", location = "pointsManage/trans/batchadd.jsp"),
	//			@Result(name = "input", location = "base/error.jsp") })
	public String uploadFile() throws Exception {

		String realpath = ServletActionContext.getServletContext().getRealPath("excel/upload");

		System.out.println("realpath: " + realpath);
		if (file != null) {
			File savefile = new File(new File(realpath), fileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(file, savefile);
			//add guys 20150205 同步shell脚本物理路径
			IsSynchronousImgUtil.isSynchronousImg();
			ActionContext.getContext().put("message", "文件上传成功");
		}
		return "success";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File getFile() {
		return file;
	}

}
