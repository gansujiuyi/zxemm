package com.jiuyi.jyplat.web.action.keyword;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.jiuyi.net.filter.ParamFilter;
import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.keyword.KeyWord;
import com.jiuyi.jyplat.service.keyword.IKeyWordService;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.jyplat.web.util.HttpClientUtil;
import com.jiuyi.jyplat.web.util.SysConfig;

@Namespace("/keyword")
public class KeyWordAction extends BaseActionSupport{

	private static final long serialVersionUID = -1310794330792268793L;

	@Resource
	private IKeyWordService keyWordService;
	
	// 日志文件对象
	Logger log = Logger.getLogger(KeyWordAction.class);
	
	private PageFinder pageFinder; // 分页查询数据对象
	
	private Query query; // 分页对象
	
	private String message;
	
	private String goHref;
	
	private String forSearch;
	
	private KeyWord keyword;
	
	private Integer id;
	
	private String ids;
	
	/**
	 * 查询所有承运商信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryAllKeyWord", results = { @Result(name = "success", location = "keyword/keyWordList.jsp"), @Result(name = "INPUT", location = "base/error.jsp") })
	public String queryAllKeyWord()
	{
		try
		{
			if (forSearch != null && forSearch.trim().equals("true"))
			{
				query = new Query(); // 如果是条件查询则从第一页数据开始
			}
			pageFinder = keyWordService.queryKeyWord(keyword, query == null ? new Query() : query);
		}
		catch (Exception e)
		{
			message = "查询关键字信息出错：" + e.toString();
			log.error("查询关键字信息出现异常", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "查询关键字信息失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 保存关键字信息
	 * 
	 * @return
	 */
	@AuthName(Constant.AUTH_PASS)
	@Action(value = "/saveKeyWord", results = { 
			@Result(name = "add", location = "keyword/addKeyWord.jsp"),
			@Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String saveKeyWord()
	{
		try
		{
			if (keyword == null)
			{
				return "add";
			}
			//判断key是否有重复
			if (StringUtils.isNotEmpty(keyword.getKey()))
			{
				KeyWord kw = keyWordService.queryKeyWord(keyword.getKey());
				if (null != kw)
				{
					throw new Exception("关键字" + kw.getKey() + "已经存在");
				}
			}
			
			keyWordService.saveKeyWord(keyword);
			message = "保存关键字信息成功！请点击更新内存启用该关键字";
			goHref = "/keyword/queryAllKeyWord.do";
		}
		catch (Exception e)
		{
			message = "保存关键字信息失败：" + e.toString();
			log.error("保存关键字信息出现异常", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "保存承运商信息失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Action(value = "/queryKeyWordById", results = { @Result(name = "success", location = "keyword/updateKeyWord.jsp"), @Result(name = "INPUT", location = "base/error.jsp") })
	public String queryKeyWordById()
	{
		try
		{
			keyword = keyWordService.queryKeyWordById(id);
		}
		catch (Exception e)
		{
			message = "跳转到修改关键字页面失败：" + e.toString();
			log.error("跳转到修改关键字页面发生异常", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "跳转修改关键字页面页面失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改关键字
	 * 
	 * @return
	 */
	@AuthName(Constant.AUTH_PASS)
	@Action(value = "/updateKeyWord", results = { @Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String updateKeyWord()
	{
		try
		{
			if (keyword == null)
			{
				throw new Exception("关键字信息为空，修改失败。");
			}
			//判断key是否有重复
			if (StringUtils.isNotEmpty(keyword.getKey()))
			{
				KeyWord kw = keyWordService.queryKeyWord(keyword.getKey());
				if (null != kw && kw.getId().intValue() != keyword.getId().intValue())
				{
					throw new Exception("关键字" + kw.getKey() + "已经存在");
				}
			}
			
			keyWordService.updateKeyWord(keyword);
			message = "修改关键字信息成功！请点击更新内存启用该关键字";
			goHref = "/keyword/queryAllKeyWord.do";
		}
		catch (Exception e)
		{
			message = "修改关键字信息失败：" + e.toString();
			log.error("修改关键字信息出现异常", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "修改关键字信息失败！");
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除关键字
	 * 
	 * @return
	 */
	@AuthName(Constant.AUTH_PASS)
	@Action(value = "/delKeyWord", results = { @Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String delKeyWord()
	{
		try
		{
			if (ids == null)
			{
				throw new Exception("关键字ID信息为空，删除失败。");
			}
			keyWordService.delKeyWord(ids);
			
			message = "删除关键字信息成功！";
			goHref = "/keyword/queryAllKeyWord.do";
		}
		catch (Exception e)
		{
			message = "删除关键字信息失败：" + e.toString();
			log.error("删除关键字信息出现异常", e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "删除关键字信息失败！");
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 更新到内存
	 * 
	 * @return
	 */
	@AuthName(Constant.AUTH_PASS)
	@Action(value = "/reloadRAM", results = { @Result(name = "success", location = "base/alertMessage.jsp", params = { "goHref", "${goHref}", "message", "${message}" }),
			@Result(name = "INPUT", location = "base/error.jsp") })
	public String reloadRAM()
	{
		try
		{
		    /*通知ehouse更新关键字 */
			String [] paths = ConfigManager.getInstance().getConfigItem("keyword_path", "").toString().split(",");
			String url;
			for(String path : paths)
			{
				url = path+"keyWordServlet";
				HttpClientUtil.sendSimplePostRequest(url,null);
				log.info("通知【"+url+"】更新内存中的关键字数据成功！");
			}
			message = "将关键字数据更新到内存成功！";
			goHref = "/keyword/queryAllKeyWord.do";
		}
		catch (Exception e)
		{
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage() : "更新关键字信息到内存失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	public PageFinder getPageFinder()
	{
		return pageFinder;
	}
	
	public void setPageFinder(PageFinder pageFinder)
	{
		this.pageFinder = pageFinder;
	}
	
	public Query getQuery()
	{
		return query;
	}
	
	public void setQuery(Query query)
	{
		this.query = query;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getGoHref()
	{
		return goHref;
	}
	
	public void setGoHref(String goHref)
	{
		this.goHref = goHref;
	}
	
	public String getForSearch()
	{
		return forSearch;
	}
	
	public void setForSearch(String forSearch)
	{
		this.forSearch = forSearch;
	}
	
	public KeyWord getKeyword()
	{
		return keyword;
	}
	
	public void setKeyword(KeyWord keyword)
	{
		this.keyword = keyword;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getIds()
	{
		return ids;
	}
	
	public void setIds(String ids)
	{
		this.ids = ids;
	}
}
