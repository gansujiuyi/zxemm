/**
 * <p>Title:             	  Wx_BranchServiceImpl.java
 * <p>Description:       
 * <p>Copyright:         Copyright (C), 2013, UXUNCHINA.
 * <p>Company:          UXUNCHINA
 * <p>Project:			  smsplat
 * @author          	 	  YLiang
 * @version	 		      V1.0
 * @see		      		      com.jiuyi.smsplat.service.wx.impl
 *
 **************************************************************************************************************
 *   Date      *		Time			*      Developers ID      *      Modlog        *         Description         *
 **************************************************************************************************************
 * 2013-12-18	    下午9:24:41  			YLiang                             	   
 */
package com.jiuyi.jyplat.service.wx.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.wx.WxBranchDAO;
import com.jiuyi.jyplat.entity.wx.WxBranch;
import com.jiuyi.jyplat.service.wx.IWx_BranchService;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * @author YLiang
 *
 */
@Service
public class Wx_BranchServiceImpl implements IWx_BranchService {

	@Resource
	private WxBranchDAO wxBranchDao;
	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#save(com.jiuyi.smsplat.entity.wx.WxBranch)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void save(WxBranch wxbranch) {
		// TODO Auto-generated method stub
		wxbranch.setCreateOperno(SessionUtil.getOperator().getOperNo());
		wxbranch.setCreateTime(DateUtils.getCurDateTime());
		if(!Utils.notEmptyString(wxbranch.getStatus()))
			wxbranch.setStatus("0");
		wxBranchDao.save(wxbranch);
	}

	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#update(com.jiuyi.smsplat.entity.wx.WxBranch)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public WxBranch update(WxBranch wxbranch) {
		// TODO Auto-generated method stub
		wxbranch.setUpdateOperno(SessionUtil.getOperator().getOperNo());
		wxbranch.setUpdateTime(DateUtils.getCurDateTime());
		return wxBranchDao.update(wxbranch);
	}

	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#update(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(String ids, String status) throws Exception {
		// TODO Auto-generated method stub
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			if(Utils.isNumeric(id[i]))
				wxBranchDao.update(Integer.parseInt(id[i]), status);
		}
	}

	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#queryAllWxBranch(com.jiuyi.smsplat.entity.wx.WxBranch, java.lang.String, com.jiuyi.smsplat.web.page.Query)
	 */
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public PageFinder<WxBranch> queryAllWxBranch(WxBranch wxbranch,
			String orderby, Query query) throws Exception {
		// TODO Auto-generated method stub
		return wxBranchDao.queryAllWxBranch(wxbranch, orderby, query);
	}

	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#delete(java.lang.String)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			if(Utils.isNumeric(id[i]))
				wxBranchDao.deleteById(Integer.parseInt(id[i]));
		}
	}

	/* (non-Javadoc)
	 * @see com.jiuyi.smsplat.service.wx.IWx_BranchService#findById(com.jiuyi.smsplat.entity.wx.WxBranch)
	 */
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public WxBranch findById(WxBranch wxbranch) {
		// TODO Auto-generated method stub
		return wxBranchDao.findById(wxbranch);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<WxBranch> search(String name) {
		return wxBranchDao.search(name);
	}

	@Override
	public WxBranch getWxBranchById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		WxBranch wxBranch=new WxBranch();
		wxBranch=wxBranchDao.getById(id);
		return wxBranch;
	}

}
