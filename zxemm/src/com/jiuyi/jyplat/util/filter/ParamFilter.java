package com.jiuyi.jyplat.util.filter;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.jiuyi.net.filter.ExitException;
import com.jiuyi.net.filter.Mirror;
import com.jiuyi.jyplat.web.util.SysConfig;

import static com.jiuyi.net.filter.Mirror.*;

@SuppressWarnings("unchecked")
public class ParamFilter
{
	private static final Logger logger = Logger.getLogger(ParamFilter.class);
	
	private static ParamFilter instance = null;
	
	private static List<String> keyword = null;
	
	private ParamFilter() 
	{
		
	}
	
	static
	{
		instance = new ParamFilter();
		keyword = SysConfig.getInstance().getKeywordList();
	}
	
	public static ParamFilter instance()
	{
		return instance;
	}
	
	/**
	 * 提供一个手工设置关键词的入口
	 * @param keywordList
	 * @return
	 */
	public ParamFilter setKeyWord(List<String> keywordList)
    {
		if(null != keywordList)
		{
			keyword = keywordList;
		}
    	return this;
    }
	
    
	/**
	 * 是否含有敏感字
	 * @param value
	 * @return
	 */
	public String hasKeyWord(String value)
	{
		for(String s : keyword)
		{
			if(value.toUpperCase().indexOf(s.toUpperCase()) >= 0)
			{
				return s;
			}
		}
		return null;
	}
	
	/**
	 * 过滤所有的参数
	 * @param fieldName 属性名 仅用于跟踪显示
	 * @param obj 检测对象
	 * @return 
	 * <ul>
	 * <li>如果抛出了ExitException类型的异常,则表示检测到了敏感字,哪个字段含有哪个敏感字在message中描述</li>
	 * <li>如果抛出了其他类型的异常,则表示在检测是否有敏感字的过程中发生了异常</li>
	 * </ul>
	 */
	public void filter(String fieldName,Object obj) throws Exception
	{
		if(null != obj && !"".equals(obj))
		{
			Class<?> clazz = obj.getClass();
			logger.info(" 开始检查【"+fieldName+"】字段的值【"+obj.toString()+"】......");
			/*简单类型*/
			if(isSimple(clazz))
			{
				String result = hasKeyWord(obj.toString());
				if(null != result)
				{
					throw new ExitException("参数中【%s】字段的值【%s】为敏感字",new Object[]{fieldName,result});
				}
			}
			
			/*集合类型*/
			else if(isCollection(clazz))
			{
				Collection<?> c = (Collection<?>)obj;
				for(Iterator<?> iter =c.iterator();iter.hasNext();)
				{
					filter(fieldName,iter.next());
				}
			}
			
			/*Array类型*/
			else if(isArray(clazz))
			{
		        for (int i = 0; i < Array.getLength(obj); i++) {
		        	filter(fieldName,Array.get(obj, i));
		        }
			}
			
			/*Map类型*/
			else if(isMap(clazz))
			{
				Map<?,?> map = (Map<?,?>)obj;
				for(Iterator<?> iter = map.keySet().iterator(); iter.hasNext();)
				{
					filter(fieldName,map.get(iter.next()));
				}
			}
			
			/*POJO*/
			else
			{
				/*调用所有的get方法得到所有的字段值*/
				for(Method method : clazz.getDeclaredMethods())
				{
					if(Mirror.isGetMethod(method))
					{
						method.setAccessible(true);
						filter(lowerFirst(method.getName().substring(3)),method.invoke(obj, new Object[]{}));
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 过滤所有的参数
	 * @param paramsList 参数对象
	 * @return 
	 * <ul>
	 * <li>如果返回值为null,则表示没有过滤到敏感字</li>
	 * <li>如果返回值为字符串,则表示有过滤到敏感字</li>
	 * <li>如果抛出了异常,则表示在检测是否有敏感字的过程中发生了异常</li>
	 * </ul>
	 */
	public  String filter(Object obj) throws Exception
	{
		try
		{
			filter(null,obj);
		}
		catch (RuntimeException e)
		{
			if(e instanceof ExitException)
			{
				return e.getMessage();
			}
			throw new Exception("调用Get方法发生异常!",e);
		}
		return null;
	}
}
