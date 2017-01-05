package com.jiuyi.jyplat.service.developer;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.DevelopInfo;

public interface IDevelopInfoService {
	
	
	/**
	 * 保存开发商信息
	 * @param developInfo
	 * @throws Exception
	 */
	public void saveDevelopInfo(DevelopInfo developInfo)throws Exception;
	
	/**
	 * 生成开发商名称
	 * @return
	 * @throws Exception
	 */
	public String genDevelopName()throws Exception;

	/**
	 * 根据用户名和 密码 查询实体
	 * @param developname
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public List<DevelopInfo> getDevelopInfo(String developname,String password) throws Exception;
}
