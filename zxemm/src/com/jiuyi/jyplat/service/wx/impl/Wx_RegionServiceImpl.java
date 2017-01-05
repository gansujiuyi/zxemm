package com.jiuyi.jyplat.service.wx.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.wx.WxRegionDAO;
import com.jiuyi.jyplat.entity.wx.WxRegion;
import com.jiuyi.jyplat.service.wx.IWx_RegionService;

@Service
public class Wx_RegionServiceImpl implements IWx_RegionService{
	@Resource
	private WxRegionDAO wxRegionDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<WxRegion> qurWxRegionList() throws Exception {
		String orderBy = "id";
		return wxRegionDao.getAll(orderBy, true);
	}

}
