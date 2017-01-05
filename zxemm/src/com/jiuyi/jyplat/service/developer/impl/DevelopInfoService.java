package com.jiuyi.jyplat.service.developer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.dao.developer.DevelopInfoDao;
import com.jiuyi.jyplat.entity.condo.DevelopInfo;
import com.jiuyi.jyplat.service.developer.IDevelopInfoService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.MacUtils;

@Service("developInfoService")
public class DevelopInfoService implements IDevelopInfoService{

	@Resource
	private DevelopInfoDao developInfoDao;
	
	@Override
	public void saveDevelopInfo(DevelopInfo developInfo) throws Exception {
		// TODO Auto-generated method stub
	  String pwd=MacUtils.encrypt(Constant.KEY_PASSWORD,MacUtils.md5Encode("123456"));
	  developInfo.setDevelopPassWord(pwd);
	developInfoDao.save(developInfo);
	}

	@Override
	public String genDevelopName() throws Exception {
		// TODO Auto-generated method stub
		return developInfoDao.genDevelopName();
	}
	
	@Override
	public List<DevelopInfo> getDevelopInfo(String developname, String password) throws Exception {
		
		return developInfoDao.find("from t_developInfo where developname=? and developpassword=?", developname,password);
	}

}
