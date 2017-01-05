package com.jiuyi.jyplat.service.keyword;

import java.util.List;
import com.jiuyi.jyplat.entity.keyword.KeyWord;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

public interface IKeyWordService {

	/**
	 * 查询所有的关键字
	 */
	public List<KeyWord> queryAllKeyWord() throws Exception;
	
	/**
	 * 按key查询关键字
	 */
	public KeyWord queryKeyWord(String key) throws Exception;
	
	/**
	 * 分页查询所有的关键字
	 */
	public PageFinder<KeyWord> queryKeyWord(KeyWord obj, Query query) throws Exception;
	
	/**
	 * 删除关键字
	 */
	void delKeyWord(String ids) throws Exception;
	
	/**
	 * 查询关键字
	 */
	KeyWord queryKeyWordById(Integer id) throws Exception;
	
	/**
	 * 查询关键字
	 */
	List<KeyWord> queryKeyWord(KeyWord obj) throws Exception;
	
	/**
	 * 新增关键字
	 */
	void saveKeyWord(KeyWord obj) throws Exception;
	
	/**
	 * 修改关键字
	 */
	void updateKeyWord(KeyWord obj) throws Exception;
}
