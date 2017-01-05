package com.jiuyi.net.filter;


import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import com.jiuyi.jyplat.util.ConfigManager;

/**
 * <p>IP拦截器</p>
 * 无效IP，不能调用接口,SOAP协议，REST协议都有效
 * @author 刘明
 * @time 2014-11-05
 */
public class IPInterceptor extends AbstractPhaseInterceptor<Message>
{
	private static Logger logger = Logger.getLogger(IPInterceptor.class);

	public IPInterceptor(String phase)
	{
		super(phase);
	}
	
	public IPInterceptor()
	{
		super(Phase.RECEIVE);
	}
	
	
	@Override
	public void handleMessage(Message message) throws Fault
	{
		try
		{  
			String url = message.get("org.apache.cxf.request.url").toString(); //对REST协议有效,SOAP协议只能得到一部分
			/*校验IP*/
			HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
			if(!checkIpAddress(request))
			{
				logger.error("地址【"+url+"】,没有权限访问");
				throw new Fault(new Exception("没有权限访问"));
			}
		}
		catch (Exception e)
		{
			throw new Fault(e);
		}
	}
	
	/**
	 * 校验IP
	 * @param request
	 * @return
	 */
	public static boolean checkIpAddress(HttpServletRequest request)
	{
		String clientip = request.getHeader("x-forwarded-for");
		String legalip = ConfigManager.getString("legal_ip", "127.0.0.1");
		if(clientip==null)
		{
			clientip =  request.getRemoteAddr();
		}
		clientip = clientip.trim().equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : clientip;
		logger.info("开始比较IP【"+clientip+"】--【"+legalip+"】是否匹配........");
		for(String s : legalip.split(","))
		{
			if(clientip.equals(s))
			{
				return true;
			}
		}
		return false;
	}
}
