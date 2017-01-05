package com.jiuyi.jyplat.web.action.condo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows;
import com.jiuyi.jyplat.entity.condo.BlockInfo;
import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountFlowsService;
import com.jiuyi.jyplat.service.condo.IBlockInfoService;
import com.jiuyi.jyplat.service.condo.ICondoPayInfoService;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.service.condo.IRefundPayInfoService;
import com.jiuyi.jyplat.service.onlineBank.IOnlineBankService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg;
import com.jiuyi.net.message.condo.GetInstractionInfoResultRspMsg;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;
import com.jiuyi.net.message.onlinebank.EbankLogInfo;
import com.jiuyi.net.message.onlinebank.PosTranReq;
/**
 * 购置新房 Action
 * @author wsf
 *
 */
@Namespace("/buyerNewHouse")
public class BuyerNewHouseAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(BuyerNewHouseAction.class);
	@Resource
	private IContactInfoService contactInfoService;
	
	@Resource
	private ICondoPayInfoService condoPayInfoService;
	
	@Resource
	private IRefundPayInfoService  refundPayInfoService;
	
	@Resource
	private INewHouseExchangService newHouseExchangService;
	
	@Resource
	private IOnlineBankService onlineBankService;
	
	@Resource
	private IBlockInfoService  blockInfoService;
	@Resource
	private IAccountFlowsService accountFlowsService;
	
	private PageFinder<ContactInfo>  pageFinder;	//分页对象
	private Query query;	//分页对象
	private ContactInfo contactInfo; //合同信息对象
	private String message;
	private String goHref;
	private String forSearch;
	private String contactNo;//合同号
	private BigDecimal paiedMoney;//已支付的金额
	private String state;//流水查询类型     refund ： 退款     condo 付款
	private List<CondoBuyer> buyerList;//购买人集合
	private List<CondoPayInfo> payList;//支付流水集合
	private List<RefundPayInfo> refundList;//退款流水集合
	private String fundNo;//项目资金监管编号
	
	//调用接口查询 网银转账
	private String strDate;//付款日期
	private String oppNo;//卡号
	private String oppName;//户名
	
	private List<EbankLogInfo> eBankLogInfos;
	//支付流水信息list
	private List<CondoPayInfo>  condoPayInfos;

	//调用接口查询 pos转账
	private String mid; //商户号
	private String devid;//商户设备号
	private String cardNo;//卡号
	private String devStan;//流水号
	private String tranAmt;//金额
	private String tranDate;//付款
	
	private PosTranReq req;

	//添加
	private String payTradeNo;//付款流水号
	private String payCardNo;//支付用银行卡号
	private BigDecimal payMoney;//支付金额
	private BigDecimal difMoney;//差额
	private String payDay;//支付时间
	private String note;
	private String payStatus = "0002";//交易状态
	private String channel = "001";//渠道
	private String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	private String tmpPayMoney;
	
	/**
	 * 将str转为Double。
	 * 
	 * @param str
	 * @return
	 */
	private Double paramToDouble(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			log.error("输入的“" + str + "”不能转换为Integer数值！");
			return null;
		}
	}
	
	/**
	 * 分页查询购买合同信息
	 * @return
	 */
	@Action(value = "/getPageContactInfo" , results = {
			@Result(name = SUCCESS , location = "condo/contactInfoList.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String getPageContactInfo(){
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
			{
				if("".equals(contactInfo.getContactNo().trim())||null==contactInfo.getContactNo().trim()){
					pageFinder=null;
					return SUCCESS;
				}
				String ContactNo = contactInfo.getContactNo().trim();
				//判断该合同是否已存在
				contactInfo = contactInfoService.getBuyHouseContactInfo(ContactNo, Constant.BANKCODE.trim());
				if(null == contactInfo){
//					//调用房管局查询合同信息接口
					GetBarginInfoResultRspMsg rspMsg = newHouseExchangService.getBarginInfo(ContactNo);
					if("0000".equals(rspMsg.getCode())){
						if(null!= rspMsg.getBsinum()  &&  !"".equals(rspMsg.getBsinum())){
							contactInfoService.saveContactInfoAndCondoBuyer(rspMsg);
							query = new Query(); // 如果是条件查询则从第一页数据开始
							contactInfo = new ContactInfo();
							contactInfo.setContactNo(ContactNo);
							pageFinder = contactInfoService.getPageContactInfo(contactInfo, query == null ? new Query() : query);
						}
						
					}else{
						log.info("调用房管局接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
					}
					/***
					 * 1、t_contactInfo==null && getInstractionInfoResult==null  没有合同信息，给提示
						2、t_contactInfo==null && getInstractionInfoResult !=null 同步信息到数据库<t_contactInfo>
						3、t_contactInfo！=null && getInstractionInfoResult ==null 提示异常数据，需要人工维护
						4、t_contactInfo！=null && getInstractionInfoResult ！=null 直接返回需要缴款的信息
					 */
				}else{
					GetBarginInfoResultRspMsg rspMsg = newHouseExchangService.getBarginInfo(ContactNo);
					if("0000".equals(rspMsg.getCode())){
						query = new Query(); // 如果是条件查询则从第一页数据开始
						pageFinder = contactInfoService.getPageContactInfo(contactInfo, query == null ? new Query() : query);
					}else if(!"0000".equals(rspMsg.getCode())){
						log.info("根据合同号查询到的缴款信息存在,调用房产局获取的数据异常"+rspMsg.getCode()+"\t"+rspMsg.getMessage());
						log.info("调用房管局接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
					}else{
						log.info("调用房管局接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
					}
				}
			}
		} catch (Exception e) {
			log.error("分页查询购买合同信息"+e.getMessage(), e);
			this.addActionMessage("分页查询购买合同信息");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至补录购房合同页面
	 * @return
	 */
	@Action(value = "/getBuyHouseContactInfo" , results = {
			@Result(name = SUCCESS , location = "condo/getBuyHouseContactInfo.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String getBuyHouseContactInfo(){
		try {
			
			if(null!=contactNo){
//				contactNo= new String(BuyerNewHouseAction.getRequest().getParameter("contactNo").getBytes("ISO8859-1"),"UTF-8");
				contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo,Constant.BANKCODE);
				BlockInfo blockInfo = blockInfoService.getBycontactNo(contactNo);
				if(blockInfo != null){
					fundNo = blockInfo.getFundNo();
				}
			}
		} catch (Exception e) {
			log.error("跳转至补录信息页面出现错误："+e.getMessage(), e);
			this.addActionMessage("跳转至补录信息页面出现错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新补录购房合同页面
	 * @return
	 */
	@Action(value = "/updateContactInfo" , results = {
			@Result(name = SUCCESS , location = "base/alertMessage.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String updateContactInfo(){
		try {
			if(null!=contactInfo){
				contactInfoService.updateContactInfo(contactInfo);
				message = "合同信息补录成功，等待补充支付流水！";
				goHref = "/buyerNewHouse/getPageContactInfo.do";
			}
			
		} catch (Exception e) {
			log.error("更新补录购房合同页面："+e.getMessage(), e);
			this.addActionMessage("更新补录购房合同页面发送错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到补充支付流水
	 * @return
	 */
	@Action(value = "/condoPayInfo" , results = {
			@Result(name = SUCCESS , location = "condo/condoPayInfo.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String condoPayInfo(){
		try {
			if(null!=contactNo){
//				contactNo= new String(BuyerNewHouseAction.getRequest().getParameter("contactNo").getBytes("ISO8859-1"),"UTF-8");
				contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);
			}
			
		} catch (Exception e) {
			log.error("跳转到补充支付流水页面："+e.getMessage(), e);
			this.addActionMessage("跳转到补充支付流水页面发送错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 调用接口查询  pos转账candoPosWay
	 * <p>TODO</p>
	 * @return
	 *//*
	@Action(value = "/cdPosWay", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
			"message,req.* ,condoPayInfos.*" }) })
	public String cdPosWay() {
		if (StringUtils.isBlank(payTradeNo) || StringUtils.isBlank(cardNo)  || StringUtils.isBlank(payDay)) {
			 message = "401";//数据不完整
			return SUCCESS;
		}
		try {
			CondoPayInfo  temp = new CondoPayInfo();
			temp.setPayCardNo(cardNo.trim());
			temp.setPayDay(payDay.trim());
			temp.setPayTradeNo(payTradeNo.trim());
			temp.setContactNo(contactNo.trim());
			//查询支付流水信息
			 condoPayInfos =  condoPayInfoService.queryCandoPayInfoList(temp);
			
			if("refund".equals(state)){
					List<RefundPayInfo> list = refundPayInfoService.getByContactNo(contactNo);
					
					if(list != null){
						for (RefundPayInfo PayInfo : list) {
							if(PayInfo.getPayTradeNo().trim().equals(req.getDevStan().trim())){
								message = "100";//查询 成功  流水已使用
								return SUCCESS;
							}
						}
					}
				}else if("condo".equals(state)){
					List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contactNo);
					for (int i = 0; i < condoPayInfos.size(); i++) {
						condoPayInfos.get(i).setNote(condoPayInfos.get(i).getNote()==null ? "暂无":condoPayInfos.get(i).getNote());
						if(null!=list){
							for (int j = 0; j < list.size(); j++) {
								if(condoPayInfos.get(i).getPayTradeNo().trim().equals(list.get(j).getPayTradeNo().trim())){
									condoPayInfos.remove(i);
								}
							}
						}
					}
				}
				message = "200";//查询 成功
				return SUCCESS;
		} catch (Exception e) {
			log.info("流水查询出错："+e.getMessage());
			message = "300";//查询失败
			return SUCCESS;
		}
	}*/
	
	
	/**
	 * 调用接口查询  pos转账candoPosWay
	 * <p>TODO</p>
	 * @return
	 */
	@Action(value = "/cdPosWay", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
			"message,req.*" }) })
	public String cdPosWay() {
		if (StringUtils.isBlank(mid) || StringUtils.isBlank(devid) || StringUtils.isBlank(cardNo) || StringUtils.isBlank(devStan) ||  StringUtils.isBlank(tranAmt) || StringUtils.isBlank(tranDate)) {
			 message = "404";//数据不完整
			return SUCCESS;
		}
		AccountFlows temp = new AccountFlows();
		temp.setTransactionSeqNum(devStan.trim());
		temp.setTransactionCode("0002");
		temp.setTransactionDate(tranDate);
		AccountFlows accountFlows = accountFlowsService.getAccountFlowsBySeqNum(temp);
        if(null!=accountFlows){
        	req = new PosTranReq();
    		req.setMid(mid.trim());
    		req.setDevid(devid.trim());
    		req.setCardNo(accountFlows.getOppositeCustAccount());
    		req.setDevStan(devStan.trim());
    		req.setTranAmt(accountFlows.getTransactionAmount());
    		req.setTranDate(accountFlows.getTransactionDate());
        }else{
        	message = "404";//查询失败
        	return SUCCESS;
        }
		try {
//			result="00";
				if("refund".equals(state)){
					List<RefundPayInfo> list = refundPayInfoService.getByContactNo(contactNo);
					
					if(list != null){
						for (RefundPayInfo PayInfo : list) {
							if(PayInfo.getPayTradeNo().trim().equals(req.getDevStan().trim())){
								message = "100";//查询 成功  流水已使用
								return SUCCESS;
							}
						}
					}
				}else if("condo".equals(state)){
					List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contactNo);
					if(list != null){
						for (CondoPayInfo PayInfo : list) {
							if(PayInfo.getPayTradeNo().trim().equals(req.getDevStan().trim())){
								message = "100";//查询 成功  流水已使用
								return SUCCESS;
							}
						}
					}
				}
				message = "200";//查询 成功
				return SUCCESS;
		} catch (Exception e) {
			log.info("pos流水查询出错："+e.getMessage());
			message = "300";//查询失败
			return SUCCESS;
		}
	}
	
	
	
	
	/**
	 * 添加流水提交处理
	 * <p>TODO</p>
	 * @return
	 */
	@Action(value = "/cadoPosCommit", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
			"message,difMoney" }) })
	public String cadoPosCommit() {
		if (StringUtils.isBlank(contactNo)) {
			message = "400";//传入的合同号不能为空
			return SUCCESS;
		}
		
		CondoPayInfo condoPayInfo=new CondoPayInfo();

		try {
			//根据contactNo判断payInfo是否有记录 有则计算总金额
			BigDecimal isMoney = new BigDecimal("0.00");
			List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contactNo);
			if(list!=null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					CondoPayInfo pay = list.get(i);
					isMoney = isMoney.add(pay.getPayMoney());
				}
			}
			isMoney=isMoney.add(payMoney);//房管局测试需要注释掉
			contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);//合同信息
			BigDecimal oldMoney =new BigDecimal("0.00");
			if(contactInfo != null && contactInfo.getContactAmt() != null){
				oldMoney = new BigDecimal(contactInfo.getContactAmt());
			}
			if (oldMoney.subtract(isMoney).doubleValue() >= -0.00001 && oldMoney.subtract(isMoney).doubleValue() <= 0.00001) {//判断金额
				condoPayInfo.setContactNo(contactNo);
				condoPayInfo.setDevid(devid);
				condoPayInfo.setNote(note);
				condoPayInfo.setOppName(oppName);
				condoPayInfo.setPayCardNo(payCardNo);
				condoPayInfo.setPayDay(payDay);
				condoPayInfo.setPayMoney(payMoney);
				condoPayInfo.setPayStatus("0010");
				condoPayInfo.setPayTradeNo(payTradeNo);
				condoPayInfo.setPayTranschannel(channel);
				condoPayInfo.setCreateTime(createTime);
				condoPayInfo.setPayTime(payDay);
				condoPayInfo.setPayBankName("中信银行兰州分行");
				//这个  if else 房管局测试 需要注释掉   下面注释放开
				//通知房管局操作状态
				SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("6", contactInfo.getHouseNo(), contactInfo.getContactNo() , "1", "购房入账成功！" , "", "", Constant.BANKCODE);
				if("0000".equals(rspMsg.getCode())){
					log.info("通知购房入账通知成功!");
					if(condoPayInfoService.savecondoPayInfoService(condoPayInfo)){
						contactInfo.setPayStatus("0010");
						contactInfo.setStatus("3333");
						contactInfo.setSuperviseAmt(String.valueOf(isMoney));
						contactInfoService.updateContactInfo(contactInfo);
						accountFlowsService.updateAccountFlowById(payTradeNo);
						message = "200";//更新状态成功
						//通知房管局购房入账成功
						log.info("购房入账支付完成");
					}else{
						message = "300";//未选择任何记录
					}
				}else{
					log.info("通知购房入账通知失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				}
				
				/*contactInfo.setPayStatus("0010");
				contactInfo.setStatus("3333");
				contactInfo.setSuperviseAmt(String.valueOf(isMoney));
				contactInfoService.updateContactInfo(contactInfo);
				message = "200";//更新状态成功
				//通知房管局购房入账成功
				SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("6", contactInfo.getHouseNo(), contactInfo.getContactNo() , "1", "购房入账成功！" , "");
				if("0000".equals(rspMsg.getCode())){
					log.info("通知购房入账通知成功!");
				}else{
					log.info("通知购房入账通知失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				}*/
			} else if(oldMoney.compareTo(isMoney)==1){
				condoPayInfo.setContactNo(contactNo);
				condoPayInfo.setDevid(devid);
				condoPayInfo.setNote(note);
				condoPayInfo.setOppName(oppName);
				condoPayInfo.setPayCardNo(payCardNo);
				condoPayInfo.setPayDay(payDay);
				condoPayInfo.setPayMoney(payMoney);
				condoPayInfo.setPayStatus("0010");
				condoPayInfo.setPayTradeNo(payTradeNo);
				condoPayInfo.setPayTranschannel(channel);
				condoPayInfo.setCreateTime(createTime);
				condoPayInfo.setPayTime(payDay);
				condoPayInfo.setPayBankName("中信银行兰州分行");
				if(condoPayInfoService.savecondoPayInfoService(condoPayInfo)){
					difMoney=oldMoney.subtract(isMoney);
					message = "200";//核对金额
					contactInfo.setSuperviseAmt(String.valueOf(isMoney));
					contactInfoService.updateContactInfo(contactInfo);
				}else{
					message = "300";//未选择任何记录
				}
			}else{
				message = "001";//核对金额
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			message = "301";//查询失败
			log.info("添加支付金额出错："+e.getMessage());
			return SUCCESS;
		}
	}
	
	/**
	 * 查询已支付金额
	 * @return
	 */
	@Action(value = "/qurPaiedMoney", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
	"message,paiedMoney" }) })
     public String qurPaiedMoney() {
		if (StringUtils.isBlank(contactNo)) {
			message = "400";//传入的合同号不能为空
			return SUCCESS;
		}
		
		paiedMoney = new BigDecimal("0.00");
		try {
			List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contactNo);
			if(list!=null && list.size()>0){
				for(int  i=0;i<list.size();i++){
					paiedMoney =paiedMoney.add(list.get(i).getPayMoney());
				}
			}
			BigDecimal oldMoney =new BigDecimal("0.00");
			contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);//合同信息
			if(contactInfo.getSuperviseAmt()!=null&& !"".equals(contactInfo.getSuperviseAmt())){
				 oldMoney = new BigDecimal(contactInfo.getSuperviseAmt().trim());
			}
			if(paiedMoney.subtract(oldMoney).doubleValue() >= -0.00001 && paiedMoney.subtract(oldMoney).doubleValue() <= 0.00001){
				message = "200";
				return SUCCESS;
			}else{
				message = "300";
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("查询已支付金额出错："+e.getMessage());
			message = "002";//传入的合同号不能为空
			return SUCCESS;
		}
	}

	/**
	 * 跳转到退款补充流水页面
	 * @return
	 */
	@Action(value = "/refundPayInfo" , results = {
			@Result(name = SUCCESS , location = "condo/refundPayInfo.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String refundPayInfo(){
		try {
			if(null!=contactNo){
//				contactNo= new String(BuyerNewHouseAction.getRequest().getParameter("contactNo").getBytes("ISO8859-1"),"UTF-8");
				contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);
				if(null != contactInfo){
					//合同信息不为空,并且支付状态为0010.合同状态3333，主动查询房产局数据
					if(contactInfo.getStatus().equals("3333") &&  contactInfo.getPayStatus().equals("0010")){
						GetInstractionInfoResultRspMsg resultRspMsg = newHouseExchangService.getInstractionInfoResult(Constant.BANKCODE , contactInfo.getHouseNo(), "4" , "");
						if(null!=resultRspMsg && "0000".equals(resultRspMsg.getCode())){
							contactInfo =  contactInfoService.getBuyHouseContactInfo(resultRspMsg.getContactNo(), Constant.BANKCODE);
						}else{
							log.error("调用接口查询房产局信息错误："+resultRspMsg.getMessage());
						}
					}
				}else{
					//合同信息为空,主动查询房产局数据
					GetInstractionInfoResultRspMsg resultRspMsg = newHouseExchangService.getInstractionInfoResult(Constant.BANKCODE , contactInfo.getHouseNo(), "4" ,"");
//					contactInfoService.saveContactInfoAndCondoBuyer(resultRspMsg);
					if(null!=resultRspMsg && "0000".equals(resultRspMsg.getCode())){
						contactInfo =  contactInfoService.getBuyHouseContactInfo(resultRspMsg.getContactNo(), Constant.BANKCODE);
					}else{
						log.error("调用接口查询房产局信息错误："+resultRspMsg.getMessage());
					}
				}
			}
			
		} catch (Exception e) {
			log.error("跳转到补充支付流水页面："+e.getMessage(), e);
			this.addActionMessage("跳转到补充支付流水页面发送错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 退款查询余额
	 * @return
	 */
	@Action(value = "/qurRefundMoney", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
	"message,paiedMoney" }) })
     public String qurRefundMoney() {
		if (StringUtils.isBlank(contactNo)) {
			message = "400";//传入的合同号不能为空
			return SUCCESS;
		}
		paiedMoney = new BigDecimal("0.00");
		try {
			/*List<RefundPayInfo> list = refundPayInfoService.getByContactNo(contactNo);
			if(list!=null && list.size()>0){
				for(int  i=0;i<list.size();i++){
					paiedMoney +=list.get(i).getPayMoney();
				}
			}*/
			contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);//合同信息
			paiedMoney = new BigDecimal(contactInfo.getSuperviseAmt());
			message = "200";
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("查询已支付金额出错："+e.getMessage());
			message = "002";//传入的合同号不能为空
			return SUCCESS;
		}
	}

	/**
	 * 退款添加流水提交处理
	 * <p>TODO</p>
	 * @return
	 */
	@Action(value = "/refundPosCommit", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
			"message,difMoney" }) })
	public String refundPosCommit() {
		if (StringUtils.isBlank(contactNo)) {
			message = "400";//传入的合同号不能为空
			return SUCCESS;
		}
		
		RefundPayInfo refundPayInfo = new RefundPayInfo();

		try {
			//根据contactNo判断RefundPayInfo是否有记录 有则计算总金额   测试放开
			BigDecimal isMoney = new BigDecimal("0.00");
			payMoney = new BigDecimal("0.00");
			//测试需放开
			/*List<RefundPayInfo> list = refundPayInfoService.getByContactNo(contactNo);
			if(list!=null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					RefundPayInfo pay = list.get(i);
					isMoney += pay.getPayMoney() ;
				}
			}*/
			for (int i = 0; i < tmpPayMoney.split("#").length; i++) {
				payMoney = payMoney.add(new BigDecimal(tmpPayMoney.split("#")[i]));
			}
			isMoney=isMoney.add(payMoney);//房管局测试需要注释掉
			contactInfo=contactInfoService.getBuyHouseContactInfo(contactNo, Constant.BANKCODE);//合同信息
			BigDecimal oldMoney = new BigDecimal("0.00");
			if(contactInfo != null && contactInfo.getSuperviseAmt() != null){
				oldMoney = new BigDecimal(contactInfo.getSuperviseAmt());
			}
			
			if (oldMoney.subtract(isMoney).doubleValue() >= -0.00001 && oldMoney.subtract(isMoney).doubleValue() <= 0.00001) {//判断金额
				for (int i = 0; i < tmpPayMoney.split("#").length; i++) {
					
					refundPayInfo.setContactNo(contactNo);
					refundPayInfo.setDevid(devid.split("#").length>0 ? devid.split("#")[i] :"");
					refundPayInfo.setNote(note.split("#").length>0 ? note.split("#")[i] : "");
					refundPayInfo.setOppName(oppName);
					refundPayInfo.setPayCardNo(payCardNo.split("#")[i]);
					refundPayInfo.setPayDay(payDay.split("#")[i]);
					refundPayInfo.setPayMoney(new BigDecimal(tmpPayMoney.split("#")[i]));
					refundPayInfo.setPayStatus("0010");
					refundPayInfo.setPayTradeNo(payTradeNo.split("#")[i]);
					refundPayInfo.setPayTranschannel(channel);
					refundPayInfo.setCreateTime(createTime);
					refundPayInfo.setPayTime(payDay.split("#")[i]);
					refundPayInfo.setPayBankName("中信银行兰州分行");
					// if  else  房管局测试需要注释掉  下面注释需要放开
					if(refundPayInfoService.saveRefundPayInfo(refundPayInfo)){
						//通知房管局撤销购买合同成功
						SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("4", contactInfo.getHouseNo(), contactInfo.getContactNo() , "1", "撤销购买合同成功！" , "", "",Constant.BANKCODE);
						if("0000".equals(rspMsg.getCode())){
							log.info("通知房管局撤销购买合同成功!");
						}else{
							log.info("通知房管局撤销购买合同失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
						}
						contactInfo.setStatus("2222");
						contactInfo.setPayStatus("2222");
						contactInfo.setSuperviseAmt(String.valueOf(new BigDecimal(contactInfo.getSuperviseAmt().trim()).subtract(isMoney)));
						contactInfoService.updateContactInfo(contactInfo);
						
					}else{
						//message = "300";//未选择任何记录
					}
				}
				message = "200";//更新状态成功
			} else if(oldMoney.compareTo(isMoney)>1){
				for (int i = 0; i < tmpPayMoney.split("#").length; i++) {
					refundPayInfo.setContactNo(contactNo);
					refundPayInfo.setDevid(devid.split("#")[i]);
					refundPayInfo.setNote(note.split("#").length>0 ? note.split("#")[i] : "");
					refundPayInfo.setOppName(oppName);
					refundPayInfo.setPayCardNo(payCardNo.split("#")[i]);
					refundPayInfo.setPayDay(payDay.split("#")[i]);
					refundPayInfo.setPayMoney(new BigDecimal(tmpPayMoney.split("#")[i]));
					refundPayInfo.setPayStatus("0010");
					refundPayInfo.setPayTradeNo(payTradeNo.split("#")[i]);
					refundPayInfo.setPayTranschannel(channel);
					refundPayInfo.setCreateTime(createTime);
					refundPayInfo.setPayTime(payDay.split("#")[i]);
					refundPayInfo.setPayBankName("中信银行兰州分行");
					if(refundPayInfoService.saveRefundPayInfo(refundPayInfo)){
						difMoney=oldMoney.subtract(isMoney);
						
						contactInfo.setSuperviseAmt(String.valueOf(new BigDecimal(contactInfo.getSuperviseAmt().trim()).subtract(isMoney)));
						contactInfoService.updateContactInfo(contactInfo);
					}
				}
				message = "500";//提交退款流水成功，请核对金额
			}else{
				message = "001";//核对金额
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			message = "600";//查询失败
			log.info("添加退款支付流水出错："+e.getMessage());
			return SUCCESS;
		}
	}
	
	/**
	 * 查看合同信息详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "/searchContactInfoById" , results = {
			@Result(name = SUCCESS , location = "condo/contactInfoDetails.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")})
	public String searchContactInfoById(){
		try {
			Map<String, Object> map = contactInfoService.getByContactNo(contactNo);
			contactInfo = (ContactInfo) map.get("contactInfo");
			buyerList = (List<CondoBuyer>) map.get("buyerList");
			payList = (List<CondoPayInfo>) map.get("payList");
			refundList = (List<RefundPayInfo>) map.get("refundList");
		} catch (Exception e) {
			log.error("查看合同信息详情"+e.getMessage(), e);
			this.addActionMessage("查看合同信息详情出现错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	public PageFinder<ContactInfo> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<ContactInfo> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGoHref() {
		return goHref;
	}

	public void setGoHref(String goHref) {
		this.goHref = goHref;
	}

	public String getForSearch() {
		return forSearch;
	}

	public void setForSearch(String forSearch) {
		this.forSearch = forSearch;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getOppNo() {
		return oppNo;
	}

	public void setOppNo(String oppNo) {
		this.oppNo = oppNo;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDevStan() {
		return devStan;
	}

	public void setDevStan(String devStan) {
		this.devStan = devStan;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getPayTradeNo() {
		return payTradeNo;
	}

	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getDifMoney() {
		return difMoney;
	}

	public void setDifMoney(BigDecimal difMoney) {
		this.difMoney = difMoney;
	}

	public String getPayDay() {
		return payDay;
	}

	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public List<EbankLogInfo> geteBankLogInfos() {
		return eBankLogInfos;
	}

	public void seteBankLogInfos(List<EbankLogInfo> eBankLogInfos) {
		this.eBankLogInfos = eBankLogInfos;
	}

	public BigDecimal getPaiedMoney() {
		return paiedMoney;
	}

	public void setPaiedMoney(BigDecimal paiedMoney) {
		this.paiedMoney = paiedMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<CondoBuyer> getBuyerList() {
		return buyerList;
	}

	public void setBuyerList(List<CondoBuyer> buyerList) {
		this.buyerList = buyerList;
	}

	public List<CondoPayInfo> getPayList() {
		return payList;
	}

	public void setPayList(List<CondoPayInfo> payList) {
		this.payList = payList;
	}

	public List<RefundPayInfo> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<RefundPayInfo> refundList) {
		this.refundList = refundList;
	}

	public String getFundNo() {
		return fundNo;
	}

	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}

	public PosTranReq getReq() {
		return req;
	}

	public void setReq(PosTranReq req) {
		this.req = req;
	}

	public List<CondoPayInfo> getCondoPayInfos() {
		return condoPayInfos;
	}

	public void setCondoPayInfos(List<CondoPayInfo> condoPayInfos) {
		this.condoPayInfos = condoPayInfos;
	}

	public String getTmpPayMoney() {
		return tmpPayMoney;
	}

	public void setTmpPayMoney(String tmpPayMoney) {
		this.tmpPayMoney = tmpPayMoney;
	}
	
	
}
