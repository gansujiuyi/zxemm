package com.jiuyi.net.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jiuyi.net.message.Head;
import com.jiuyi.net.message.MsgRetCode;
import com.jiuyi.net.message.activity.actlist;
import com.jiuyi.net.message.activity.quractReq;
import com.jiuyi.net.message.activity.quractReqMsg;
import com.jiuyi.net.message.activity.quractRsp;
import com.jiuyi.net.message.activity.quractRspMsg;
import com.jiuyi.net.message.activity.quractlistReq;
import com.jiuyi.net.message.activity.quractlistReqMsg;
import com.jiuyi.net.message.activity.quractlistRsp;
import com.jiuyi.net.message.activity.quractlistRspMsg;
import com.jiuyi.jyplat.entity.base.Syscontent;
import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.base.ISyscontentService;
import com.jiuyi.jyplat.service.base.impl.SyscontentService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.PageToObj;

public class ActivityUtils {

	Logger log = Logger.getLogger(ActivityUtils.class);

	private static ActivityUtils activityUtils;

	public static ActivityUtils getInstance() {
		if (activityUtils == null)
			activityUtils = new ActivityUtils();

		return activityUtils;
	}
	
	
	private String trimString(Object arg) {
		return null == arg ? "" : arg.toString().trim();
	}
	
	private boolean isNullData(String... args) {
		for (int i = 0; i < args.length; i++) {
			if (null == args[i] || args[i].trim().equals(""))
				return true;
		}
		return false;
	}
	
	public quractRspMsg quract(quractReqMsg reqMsg) {
		/* 定义应答类的消息体、应答类、报文头 */
		quractRspMsg rspMsg = new quractRspMsg();
		quractRsp rsp = new quractRsp();
		Head msghead = new Head();

		/* 应答类的报文头和请求类的报文头保持一致 */
		msghead = reqMsg.getMsghead();
		rspMsg.setMsghead(msghead);

		/* 定义局部变量，从请求类的消息中公告id编号*/
		quractReq req = reqMsg.getMsgreq();
		String actid = trimString(req.getActid());//公告id编号

		/* 判断请求报文中的head和request中的必输项是否为空，如果有为空的数据，则直接返回错误信息“请求报文数据错误” */
		Boolean errorReq = isNullData(msghead.getAuthcode(),msghead.getReqsn(), msghead.getServicename(), msghead
				.getTranchannel(), msghead.getTrandatetime(), msghead.getVersion(), actid);
		if (errorReq) {
			log.error("quract:请求报文数据不完整，请检查===");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow(MsgRetCode.Requset_Format_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}
		Syscontent content = null;
		try {
			ISyscontentService marketingService = (SyscontentService) SpringContextUtil.getBean("syscontService");
			content = marketingService.querySyscontById(actid);
			if(content == null )
				throw new Exception(MsgRetCode.ERROR_TRANS_MANUAL_002);
		} catch (Exception e) {
			log.error("quract:查询单个活动出错，返回应答报文===",e);
			rsp.setRetcode(MsgRetCode.Trans_Error_Code);
			rsp.setRetshow(MsgRetCode.Trans_Error_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}
		
		/* =====所有数据准备完毕，开始组装应答报文===== */
		rsp.setRetcode(MsgRetCode.Trans_Success_Code);
		rsp.setRetshow(MsgRetCode.Trans_Success_Show);
		rsp.setActid(actid);
//		rsp.setActdesc(content.getDescription());
//		rsp.setActimg(content.getActImg());
		rsp.setActname(content.getContname());
//		rsp.setActtype(marketing.getActType());
//		if( marketing.getActivityAD() != null ){
//			rsp.setAdcontent(marketing.getActivityAD().getContent());
//			rsp.setAddesc(marketing.getActivityAD().getDescription());
//			rsp.setAdname(marketing.getActivityAD().getAdName());
//		}
//		rsp.setEndtime(marketing.getEndTime());
//		rsp.setOnchannel(marketing.getOnChannel());
		rsp.setSmscontent(content.getHtmldetail());
//		rsp.setStarttime(marketing.getStartTime());
		/* =============组装报文完毕============= */

		log.debug("quract:===组装应答报文完毕，返回应答报文===");
		rspMsg.setMsgrsp(rsp);
		return rspMsg;
	}

	public quractlistRspMsg quractlist(quractlistReqMsg reqMsg){
		/* 定义应答类的消息体、应答类、报文头 */
		quractlistRspMsg rspMsg = new quractlistRspMsg();
		quractlistRsp rsp = new quractlistRsp();
		Head msghead = new Head();

		/* 应答类的报文头和请求类的报文头保持一致 */
		msghead = reqMsg.getMsghead();
		rspMsg.setMsghead(msghead);

		/* 定义局部变量，从请求类的消息中获取凭证类型、凭证号码 */
		quractlistReq req = reqMsg.getMsgreq();

		int indexpage = 0;// 查询页码
		try {
			indexpage = Integer.parseInt("".equals(trimString(req.getIndexpage())) ? "1" : req.getIndexpage().trim());// 如果传进来的页码为空，则默认查第1页
		} catch (NumberFormatException e) {
			log.error("quractlist:查询页码格式不正确，请检查===");
			rsp.setRetcode(MsgRetCode.Index_Format_Code);
			rsp.setRetshow(MsgRetCode.Index_Format_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}

		int pageSize = 10;// 每页默认记录数
		try {
			pageSize = trimString(req.getPagesize()).equals("") ? MsgRetCode.Page_Size: Integer.parseInt(req.getPagesize().trim());// 如果传进来的页码为空，则默认记录数
		} catch (NumberFormatException e) {
			log.error("quractlist:每页记录数大小不正确，请检查===");
			rsp.setRetcode(MsgRetCode.PageSize_Format_Code);
			rsp.setRetshow(MsgRetCode.PageSize_Format_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}
		String acttype = trimString(req.getActtype());
		String ordertype = trimString(req.getOrdertype());
		/* 判断请求报文中的head和request中的必输项是否为空，如果有为空的数据，则直接返回错误信息“请求报文数据错误” */
		Boolean errorReq = isNullData(msghead.getAuthcode(),msghead.getReqsn(), msghead.getServicename(), msghead
			.getTranchannel(), msghead.getTrandatetime(), msghead.getVersion(),acttype);//,onchannel
		if (errorReq) {
			log.error("quractlist:请求报文数据不完整，请检查===");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow(MsgRetCode.Requset_Format_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}

		int totalRec = 0;// 总记录数
		int totalPage = 0;// 总页数

		/* 定义局部对象：活动列表 */
		actlist alist = new actlist();
		List<Syscontent> acts = new ArrayList<Syscontent>();
		
		/* 定义分页查询实现类的实例 */
		PageToObj pageToObj = null;

		// ====(1).根据商品类型，分页查询兑换商品信息====
		try {
			pageToObj = getActList("", ordertype, acttype, "", "", "", indexpage, pageSize,"");
			
			Object[] data = pageToObj.getResult();
			if (data != null && data.length != 0) {
				totalRec = pageToObj.getAvailableCount();
				// 总页数 = ( 总记录数 - 1 ) / 每页记录数 + 1
				totalPage = (pageToObj.getAvailableCount() - 1) / pageSize + 1;
				for (int i = 0; i < data.length && data[i] != null; i++) {
					acts.add((Syscontent) data[i]);
				}
			}
		} catch (NullPointerException e) {
			log.info("quractlist:活动列表为空===");
		} catch (Exception e) {
			log.error("quractlist:查询活动列表时出错，返回应答报文===" + e.toString());
			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show);
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}
		
		/* =====所有数据准备完毕，开始组装应答报文===== */
		rsp.setRetcode(MsgRetCode.Trans_Success_Code);
		rsp.setRetshow(MsgRetCode.Trans_Success_Show);
		rsp.setTotalrecs(totalRec);
		rsp.setTotalpage(totalPage);

		rsp.setRecs(acts.size());
		alist.setAinfo(acts);
		rsp.setAlist(alist);

		/* =============组装报文完毕============= */
		log.debug("quractlist:===组装应答报文完毕，返回应答报文===");
		rspMsg.setMsgrsp(rsp);
		return rspMsg;
	}
	
	private PageToObj getActList(String custid, String ordertype, String acttype,
			String onchannel, String bgntime, String endtime,int indexpage, int pageSize,String shopId)
			throws NullPointerException, Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("select act.* ");
		sql.append(" from syscontent act where act.status = 0004 ");//公告为审核可用状态
		sql.append(" AND ACT.CONTTYPE = '").append(acttype).append("' ");//内容类型
		//sql.append(" AND ACT.ONCHANNEL = '").append(onchannel).append("' ");//展示渠道
		
		if (bgntime != null && !bgntime.isEmpty()){//开始时间
			if(Constant.SYS_DB_TYPE.trim().equalsIgnoreCase("oracle")){
				sql.append(" AND SUBSTR(ACT.STARTTIME,0,8) >= '").append(bgntime).append("'");
			}else{
				sql.append(" AND SUBSTR(ACT.STARTTIME,1,8) >= '").append(bgntime).append("'");
			}
		}

		if (endtime != null && !endtime.isEmpty()){//结束时间
			if(Constant.SYS_DB_TYPE.trim().equalsIgnoreCase("oracle")){
				sql.append(" AND SUBSTR(ACT.ENDTIME,0,8) >= '").append(endtime).append("'");
			}else{
				sql.append(" AND SUBSTR(ACT.ENDTIME,1,8) >= '").append(endtime).append("'");
			}
		}
		//如果id不为null和“” ，查询条件就加上店铺id
		if(null!=shopId&&!shopId.isEmpty()){
			sql.append(" AND  ACT.SHOPID ='").append(shopId).append("'");
		}
		
		sql.append(" ORDER BY act.SEQUENCE ");
		
		log.debug("分页查询活动列表信息===" + sql.toString());
		//通过分页查询类进行查询，默认为每页10条记录，该参数暂时固定
		PageToObj pageToObj = new PageToObj(sql.toString(), pageSize, indexpage,"com.jiuyi.jyplat.entity.base.Syscontent");
		return pageToObj;	
	
	}
}
