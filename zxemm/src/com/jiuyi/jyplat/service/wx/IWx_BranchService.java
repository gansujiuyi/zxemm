/**
 * <p>Title:             	  IWx_BranchService.java
 * <p>Description:       
 * <p>Copyright:         Copyright (C), 2013, UXUNCHINA.
 * <p>Company:          UXUNCHINA
 * <p>Project:			  smsplat
 * @author          	 	  YLiang
 * @version	 		      V1.0
 * @see		      		      com.jiuyi.smsplat.service.wx
 *
 **************************************************************************************************************
 *   Date      *		Time			*      Developers ID      *      Modlog        *         Description         *
 **************************************************************************************************************
 * 2013-12-9	    下午11:26:45  			YLiang                             	   
 */
package com.jiuyi.jyplat.service.wx;

import java.util.List;

import com.jiuyi.jyplat.entity.wx.WxBranch;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;


/**
 * @author YLiang
 *
 */
public interface IWx_BranchService {

	/**保存网点信息*/
	public void save(WxBranch wxbranch);
	/**修改网点信息*/
	public WxBranch update(WxBranch wxbranch);
	/**修改网点信息状态*/
	public void update(String ids, String status) throws Exception;
	/**分页查询网点信息*/
	public PageFinder<WxBranch> queryAllWxBranch(WxBranch wxbranch, String orderby ,Query query) throws Exception;
	/**删除网点信息*/
	public void  delete(String ids) throws Exception;
	/**根据ID查找网点信息*/
	public WxBranch findById(WxBranch wxbranch);
	//根据姓名搜索
	public List<WxBranch> search(String name);
	
	///**根据ID查找网点信息*/
	public WxBranch getWxBranchById(Integer integer) throws Exception;
}
