package com.jiuyi.jyplat.service.wx;

import java.util.List;

import com.jiuyi.jyplat.entity.wx.WxCity;

public interface IWx_CityService {
	
	/**
	 * 查询城市list
	 */
	public List<WxCity> qurWxCityList() throws Exception;

}
