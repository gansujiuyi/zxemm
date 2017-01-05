package com.jiuyi.jyplat.service.wx.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.wx.WxCityDAO;
import com.jiuyi.jyplat.entity.wx.WxCity;
import com.jiuyi.jyplat.service.wx.IWx_CityService;

@Service
public class Wx_CityServiceImpl implements IWx_CityService{
	
	@Resource
	private WxCityDAO wxCityDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<WxCity> qurWxCityList() throws Exception {
		String orderBy = "id";
		return wxCityDao.getAll(orderBy, true);
	}

}
