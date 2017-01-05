package com.jiuyi.net.message.goodsInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jiuyi.net.message.Head;
import com.jiuyi.net.message.MsgRetCode;
import com.jiuyi.net.utils.FilterParam;
import com.jiuyi.jyplat.util.DBAction;

public class DelEhouseUtils {

	private Logger log = Logger.getLogger(DelEhouseUtils.class);

	private DelEhouseUtils() {
	}

	private static DelEhouseUtils utils;

	public static DelEhouseUtils getInstance() {
		if (utils == null) {
			utils = new DelEhouseUtils();
		}
		return utils;
	}

	/**
	 * 删除个人中心二手房。
	 * 
	 * @param reqMsg
	 * @return
	 */

	public DelHouseRspMsg delEHouse(DelHouseReqMsg reqMsg) {

		/* 定义应答类的消息体、应答类、报文头 */
		DelHouseRspMsg rspMsg = new DelHouseRspMsg();
		rspMsg.setMsghead(reqMsg.getMsghead());
		DelHouseRsp rsp = new DelHouseRsp();

		// 获取查询内容，并进行防注入处理。
		DelHouseReq req = FilterParam.filterObj(reqMsg.getMsgreq());
		Head head = reqMsg.getMsghead();
		Integer flag = -1;

		try {

			// sql拼接规则：
			// 如果要添加列，请按照查询店铺名的方式进行left join
			// 如果要对行进行筛选，请按照查询商品名的方式进行筛选。
			//goodsstatus=1005从个人中心删除发布房源
			if (StringUtils.isNotBlank(req.getDelIds())) {
				String[] delId = req.getDelIds().split(",");
				StringBuffer sb = new StringBuffer();
				sb.append("update t_goodsinfo g set g.goodsstatus= '1005' ");
				sb.append(" where g.custid= '"+ head.getMemberNo()+ "' and goodsid in (" );
				for (int i = 0; i < delId.length; i++) {
					    sb.append("'"+ delId[i]+"' ,");
				}
				String sb1=sb.substring(0,sb.length()-1);
				sb1=sb1+")";
				
				log.info("删除个人中心二手房sql：" + sb1);
				DBAction db = new DBAction();
				 flag = db.executeUpdate(sb1.toString());
				
			} 
			rsp.setFlag(flag);
			rsp.setRetcode(MsgRetCode.Trans_Success_Code);

		} catch (Exception e) {
			log.error("删除个人中心二手房时出现错误" + e.toString(), e);

			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			rspMsg.setMsgrsp(rsp);
			return rspMsg;
		}

		log.debug("searcheHouse:===组装应答报文完毕，返回应答报文===");
		rspMsg.setMsgrsp(rsp);
		return rspMsg;

	}

}
