package com.jiuyi.jyplat.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.web.util.SysConfig;

/**
 * 权限校验拦截器
 */
public class AuthInterceptor extends AbstractInterceptor {
	private Logger log = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 8491276216537773577L;

	public String intercept(ActionInvocation invocation) throws Exception {

		//获取拦截器内容
		ActionContext context = invocation.getInvocationContext();
		HashMap<String, String> pwMap = new HashMap();
		Iterator entryKeyIterator = context.getParameters().entrySet().iterator();
		List<String> itemList = SysConfig.getInstance().getKeywordList();
		
		
		//安全action方法 下列方法不会被拦截
		List<String> safeUrls = new ArrayList<String>();
		{
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.addHelpLibIndex");
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.addHelpLibLeft");
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.addHelpLibRight");
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.updateHelpLib");
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.saveHelpLib");
			safeUrls.add("com.jiuyi.jyplat.web.action.pointsbusiness.HelpLibAction.queryHelpLibContent");
			
			safeUrls.add("com.jiuyi.jyplat.web.action.guide.ShoppingGuideAction.addShoppingGuideInfo");
			safeUrls.add("com.jiuyi.jyplat.web.action.guide.ShoppingGuideAction.editShoppingGuideInfo");
			safeUrls.add("com.jiuyi.jyplat.web.action.newhouse.ManageNewHouseInfoAction.addNewHouse");
			safeUrls.add("com.jiuyi.jyplat.web.action.newhouse.ManageNewHouseInfoAction.updateNewHouse");
			safeUrls.add("com.jiuyi.jyplat.web.action.newcar.ManageBusinessInfoAction.addBusinessnew");
			safeUrls.add("com.jiuyi.jyplat.web.action.newcar.ManageBusinessInfoAction.updateBusinessnew");
			safeUrls.add("com.jiuyi.jyplat.web.action.newcar.NewFanchier.addFanchierInfo");
			safeUrls.add("com.jiuyi.jyplat.web.action.newcar.NewFanchier.editInfo");
		    safeUrls.add("com.jiuyi.jyplat.web.action.tradeNews.tradeNewsAction.addTradeNewsInfo");
			safeUrls.add("com.jiuyi.jyplat.web.action.tradeNews.tradeNewsAction.editTradeNewsInfo");
			safeUrls.add("com.jiuyi.jyplat.web.action.transport.TransportAction.notify_url");
			safeUrls.add("com.jiuyi.mall.web.action.member.MemberAction.ezexIndex");
			safeUrls.add("com.jiuyi.jyplat.web.action.base.SyscontAction.saveEditSyscont");
			safeUrls.add("com.jiuyi.jyplat.web.action.acegiManage.SysActionAction.saveAction");
			safeUrls.add("com.jiuyi.jyplat.web.action.order.FundChangeIncreaseAciton.fundIncreaseApply");
			safeUrls.add("com.jiuyi.jyplat.web.action.acegiManage.FunctionAction.updateFunction");
		}

		ActionProxy proxy = invocation.getProxy();
		String methodName = proxy.getMethod();
		Object action = proxy.getAction();
		
       
		String classurl = action.getClass().getName();
		String authUrl = classurl + "." + methodName;
		//log.error(authUrl);
		
		//放行安全action方法
//		if (!safeUrls.contains(authUrl)) {
//			while (entryKeyIterator.hasNext()) {
//				Entry<String,String[]> e = (Entry<String,String[]>)entryKeyIterator.next();
//				if( e.getValue() != null ){
//					if(e.getValue() instanceof String[]){
//						String[] value = e.getValue();
//						String key = e.getKey();  
//						for (String string : value) {
//							for(int j=0;j<itemList.size();j++)
//							{
//								if(string.toUpperCase().indexOf(itemList.get(j).toString().toUpperCase()) != -1)
//								{
//									log.info("请勿输入非法或特殊敏感字符["+string+"],请返回重试!");
//									ActionContext.getContext().put("message", "请勿输入非法或特殊敏感字符["+string+"],请返回重试!");
//									return "error";
//								}
//							}
//							// 将解密后的密码重新添加到页面参数MAP中
//							Iterator pwMapIt = pwMap.entrySet().iterator();
//							while (pwMapIt.hasNext()) {
//								Map.Entry ee = (Map.Entry)pwMapIt.next();
//								//context.getParameters().put(e.getKey().toString(), e.getValue().toString());
//								invocation.getStack().setValue(ee.getKey().toString(),ee.getValue().toString());
//							}
//						}
//					}
//				}
//			}
//		}
		
		Operator operator = SessionUtil.getOperator();

		String[] authName = ParseAuthName.parseAuthentication(action.getClass(), methodName, null); //获取方法上权限相关的注解标识

		if (null != operator) {
			try {
				if (authName != null && authName.length > 0) {
					//跳过注解定义需求过滤的请求
					for (String auth : authName) {
						if (Constant.AUTH_PASS.equals(auth)) {
							return invocation.invoke();
						}
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

			/*过滤非法字符*/
			if (!safeUrls.contains(authUrl)) {
			while (entryKeyIterator.hasNext()) {
	            Entry<String,String[]>  entry = (Entry<String,String[]> )entryKeyIterator.next();
	            if( entry.getValue() != null )
	            {
	            	if(entry.getValue() instanceof String[])
	            	{
	            		String[] value = entry.getValue();
	                	for (String i : value) 
	                	{
	                		for(int j=0;j<itemList.size();j++)
	                		{
	                			if(i.toUpperCase().indexOf(itemList.get(j).toString().toUpperCase()) != -1)
	                			{
	                				ActionContext.getContext().put("tip", "请勿输入非法或特殊敏感字符["+itemList.get(j).toString()+"],请返回重试!");
	                				return "noAuth";
	                			}
	                		}
	                	}
	            	}
	            }
			}
		}
			//List<String> authUrls =  (List<String>)context.getSession().get(Constant.AUTH_PRIVILEGE_URL);
			List<String> authUrls = SessionUtil.getActionUrl();

			//处理进行权限校验  控制到方法
			if (authUrls == null || authUrls.contains(authUrl.toLowerCase())
					|| authUrls.contains(classurl.toLowerCase())) {
				return invocation.invoke();
			}
			ActionContext.getContext().put("tip", "您暂时没有此项操作的权限,如有疑问请联系管理员!");
			return "noAuth";
		} else {
			//登录页面，修改密码直接跳过
			if (authUrl.equals(Constant.AUTH_LOGIN_URL) || authUrl.equals(Constant.AUTH_EDIT_PW_URL)
					|| authUrl.equals(Constant.AUTH_DOLOGIN_URL) || authUrl.equals(Constant.AUTH_CHECK_PW_URL)
					|| authUrl.equals(Constant.VALI_CODE_URL) || authUrl.equals(Constant.NOTIFY_URL)) {
				return invocation.invoke();
			}

			ActionContext.getContext().put("tip", "没有登录");
			return Action.LOGIN;
		}

	}

}
