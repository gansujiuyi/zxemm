package com.jiuyi.jyplat.web.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.jiuyi.jyplat.entity.keyword.KeyWord;
import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.keyword.IKeyWordService;


public class SysConfig
{
	private static SysConfig instance = new SysConfig();
	
	private static DataMap itemMap = new DataMap();
	
	private static List<String> keywordList = null;
	
	private static Logger logger = Logger.getLogger(SysConfig.class);
	
	
	static
	{
		loadConfig();
	}
	
	
	
	private SysConfig()
	{
	}
	
	
	public static SysConfig getInstance()
	{
		return instance;
	}
	
	public  DataMap getItemMap()
	{
		return itemMap;
	}
	
	public  List<String> getKeywordList()
	{
		return keywordList;
	}

	
	public  void setKeyword(List<String> keyword)
	{
		keywordList = keyword;
	}

	public static List<String> queryConfig()
	{
		List<String> data = new ArrayList<String>();
		try
		{
			IKeyWordService service = (IKeyWordService) SpringContextUtil.getBean("keyWordService");
			List<KeyWord> configList = service.queryAllKeyWord();
			if(null != configList && configList.size() >0)
			{
				for(KeyWord k : configList)
				{
					data.add(k.getValue());
				}
			}
			return data;
		}
		catch (Exception e)
		{
			logger.error("查询关键字发生异常!",e);
		}
		return null;
	}
	
	/**
	 * 从数据库中读入配置
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean loadConfig()
	{
		try
		{
			keywordList = new ArrayList<String>();
			IKeyWordService service = (IKeyWordService) SpringContextUtil.getBean("keyWordService");
			List<KeyWord> configList = service.queryAllKeyWord();
			if(null != configList && configList.size() >0)
			{
				for(KeyWord k : configList)
				{
					keywordList.add(k.getValue());
				}
			}
		}
		catch (Exception e)
		{
			logger.error("读取配置内容到内存中失败",e);
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 设定配置信息
	 *
	 * @param name  键值名称
	 * @param value 具体的值
	 */
	public static void set(String name, String value)
	{
		itemMap.set(name, value);
	}
	
	
	
	/**
	 * 设定配置信息
	 *
	 * @param name  键值名称
	 * @param value 具体的值
	 */
	public static void set(String name, int value)
	{
		itemMap.set(name, value);
	}
	
	
	
	/**
	 * 获得字串配置信息
	 *
	 * @param name 键值名称
	 * @return 若不存在，则返回空字串
	 */
	public static String getString(String name)
	{
		return itemMap.getString(name);
	}
	
	/**
	 * 返回整型配置值
	 *
	 * @param name 键值名称
	 * @return 若不存在，或转换失败，则返回0
	 */
	public static int getInt(String name)
	{
		return itemMap.getInt(name);
	}
}
