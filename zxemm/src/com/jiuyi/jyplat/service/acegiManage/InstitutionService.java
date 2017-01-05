package com.jiuyi.jyplat.service.acegiManage;

import java.util.List;
import java.util.Map;

import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 机构业务
 * @author leiyongjun
 *  May 24, 2011  5:40:47 PM
 */
public interface InstitutionService {

	/**
	 * 根据主键ID查询组织机构
	 * @param instId
	 * @return
	 * @throws Exception
	 */
	public Institution getInstById(Integer instId) throws Exception;

	/**
	 * 分页查询组织机构
	 * @param instName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Institution> queryInstsByName(String instName, Query query) throws Exception;

	/**
	 * 查询所有组织结果
	 * @return List<Institution>
	 * @throws Exception
	 */
	public List<Institution> queryInstsList() throws Exception;

	/**
	 * 根据属性查询所有组织结果
	 * @return List<Institution>
	 * @throws Exception
	 */
	public List<Institution> queryInstsList(Institution inst) throws Exception;

	/**
	 * 新增组织机构
	 * @param Institution
	 * @throws Exception
	 */
	public void insertInsts(Institution inst) throws Exception;

	/**
	 * 修改组织机构
	 * @param Institution
	 * @throws Exception
	 */
	public void updateInsts(Institution inst) throws Exception;

	/**
	 * 删除组织机构
	 * @param Institution
	 * @throws Exception
	 */
	public void deleteByNo(Institution inst) throws Exception;

	/**
	 * 获取机构号
	 * @param oper
	 * @return
	 */
	public List<String> queryInstNosByOper(Operator oper);
}
