package com.jiuyi.jyplat.web.action.condo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.service.condo.IAuditTransferMoneyService;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.service.condo.ITransferPayInfoService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;
import com.jiuyi.net.message.onlinebank.EbankLogInfo;
import com.jiuyi.net.message.onlinebank.PosTranReq;
/**
 * 新房   审批划款   相关操作Action
 * @author wsf
 *
 */
@Namespace("/auditTransferMoney")
public class AuditTransferMoneyAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(AuditTransferMoneyAction.class);
	
	@Resource
	private IAuditTransferMoneyService transferMoneyService;
	@Resource
	private ITransferPayInfoService transferPayInfoService;
	@Resource
	private INewHouseExchangService newHouseExchangService;
	
	private PageFinder<AuditTransferMoney> pageFinder;	//审核划款分页查询对象
	private List<AuditTransferMoney> auditTransferMoneys;
	private AuditTransferMoney auditTransferMoney;
	private Query	query;	//分页查询对象
	private AuditTransferMoney transferMoney;
	private List<TransferPayInfo>  payList;	//支付流水集合
	private String message;
	private String goHref;
	private String forSearch;
	
	private BigDecimal paiedMoney;//已支付的金额
	//调用接口查询 网银转账
	private String strDate;//付款日期
	private String oppNo;//卡号
	private String oppName;//户名
	private String orderId;
	private String transferId;
	private List<EbankLogInfo> eBankLogInfos;

	//调用接口查询 pos转账
	private String mid; //商户号
	private String devid;//商户设备号
	private String cardNo;//卡号
	private String devStan;//流水号
	private String tranAmt;//金额
	private String tranDate;//付款

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
	private PosTranReq req;
	/**
	 * 分页查询等待划款审批划款记录
	 * @return
	 */
	@Action(value = "/getPageTransferMoney" , results = {
			@Result(name = SUCCESS , location = "condo/transferMoneyList.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String getPageTransferMoney(){
		try {
			if (forSearch != null && forSearch.trim().equals("true"))
				query = new Query();
				pageFinder = transferMoneyService.getPageTransferMoney(transferMoney, query == null? new Query() : query , "0");
				auditTransferMoneys=pageFinder.getList();
				if(auditTransferMoneys!=null){
					auditTransferMoney=auditTransferMoneys.get(0);
				}else{
					auditTransferMoney=null;
				}
		} catch (Exception e) {
			log.error("分页查询等待划款审批划款记录出现错误："+e.getMessage(), e);
			this.addActionMessage("分页查询等待划款审批划款记录出现错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至补录信息页面
	 * @return
	 */
	@Action(value = "/getByHouseNo" , results = {
			@Result(name = SUCCESS , location = "condo/enteringMessage.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String getByHouseNo(){
		try {
			
		} catch (Exception e) {
			log.error("跳转至补录信息页面出现错误："+e.getMessage(), e);
			this.addActionMessage("跳转至补录信息页面出现错误！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 实名认证、保存补录信息
	 * @return
	 */
	@Action(value = "/enteringOutMessage" , results = {
			@Result(name = SUCCESS , type = "json" , params={"includeProperties","message"})
	})
	public String enteringOutMessage(){
		try {
			if(transferMoney.getOutBankId().equals("447")){//本行卡信息验证
			/*	CheckDebitCardRspMsg checkRspMsg= WSEpayUtil.checkDebitCard(transferMoney.getOutCardNo().trim(), transferMoney.getOutIdNo().trim(), "01",
            			null, transferMoney.getOutCardName().trim(), transferMoney.getOutPhone().trim());*/
				String retCode ="0000";//checkRspMsg.getMsgrsp().getRetcode();
//				String retShow = checkRspMsg.getMsgrsp().getRetshow();
				if("0000".equals(retCode)){//实名认证成功
					message = "补录信息完成！";
					log.info("补录信息完成！");
					transferMoneyService.enteringOutCard(transferMoney.getHouseNo().trim(), transferMoney.getOutCardNo().trim(), transferMoney.getOutBankId().trim(), transferMoney.getOutCardName().trim(), transferMoney.getOutPhone().trim(), transferMoney.getOutIdNo().trim());
				}else{
					message = "实名认证不通过！";
				}
			}else{//他行卡信息验证
			/*	MemberCfcaAccRspMsg memberCfcaAccRspMsg=WSEpayUtil.checkMembercfcaCard(transferMoney.getOutBankId().trim(), transferMoney.getOutCardNo().trim(), "0", null, transferMoney.getOutIdNo().trim(), 
						"01", null, transferMoney.getOutCardName().trim(),transferMoney.getOutPhone().trim(), null);*/
				String retCode ="0000" ;//memberCfcaAccRspMsg.getMsgrsp().getRetcode();
//				String retShow = memberCfcaAccRspMsg.getMsgrsp().getRetshow();
				if("0000".equals(retCode)){//实名认证成功
					message = "补录信息完成！";
					log.info("补录信息完成！");
					transferMoneyService.enteringOutCard(transferMoney.getHouseNo().trim(), transferMoney.getOutCardNo().trim(), transferMoney.getOutBankId().trim(), transferMoney.getOutCardName().trim(), transferMoney.getOutPhone().trim(), transferMoney.getOutIdNo().trim());
				}else{
					message = "实名认证不通过！";
				}
			}
		} catch (Exception e) {
			log.error("补录出账信息出现错误："+e.getMessage(), e);
			message = "实名认证不通过！";
		}
		return SUCCESS;
	}
	
	/**
	 * 跳至录入流水页面
	 * @return
	 */
	@Action(value = "/transferPayInfo" , results = {
			@Result(name = SUCCESS , location = "condo/transferPayInfo.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
	public String transferPayInfo(){
		try {
			transferMoney = transferMoneyService.getById(transferMoney.getId());
			//payList = transferPayInfoService.getByTransNo(transferMoney.getId());
		} catch (Exception e) {
			log.error("跳至录入流水页面，出现错误："+e.getMessage(), e);
			this.addActionMessage("跳至录入流水页面出现错误，请联系管理员");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询已支付金额
	 * @return
	 */
	@Action(value = "/queryPayMoney", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
	"message,paiedMoney" }) })
     public String queryPayMoney() {
		if (StringUtils.isBlank(transferId)) {
			message = "400";//传入的审批划款编号不能为空
			return SUCCESS;
		}
		
		paiedMoney = new BigDecimal("0.00");
		try {
			List<TransferPayInfo> list = transferPayInfoService.getByTransNo(transferId);
			for (TransferPayInfo transferPayInfo : list) {
				paiedMoney = paiedMoney.add(transferPayInfo.getPayMoney());
			}
			message = "000";
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询已支付金额出错："+e.getMessage());
		}
		return SUCCESS;
	}
	
	
	/**
	 * 调用接口查询  pos转账
	 * <p>TODO</p>
	 * @return
	 */
	@Action(value = "/tmPosWay", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
			"message,req.*" }) })
	public String tmPosWay() {
		if (StringUtils.isBlank(mid) || StringUtils.isBlank(devid) || StringUtils.isBlank(cardNo) || StringUtils.isBlank(devStan) ||  StringUtils.isBlank(tranAmt) || StringUtils.isBlank(tranDate)) {
			 message = "401";//数据不完整
			 return SUCCESS;
		}

		 req = new PosTranReq();
		req.setMid(mid.trim());
		req.setDevid(devid.trim());
		req.setCardNo(cardNo.trim());
		req.setDevStan(devStan.trim());
		req.setTranAmt(tranAmt.trim());
		req.setTranDate(tranDate.trim());

		try {
				List<TransferPayInfo> list = transferPayInfoService.getByTransNo(transferId);
				if(list != null){
					for (TransferPayInfo PayInfo : list) {
						if(PayInfo.getPayTradeNo().trim().equals(req.getDevStan().trim())){
							message = "100";//查询 成功  流水已使用
							return SUCCESS;
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
	 * 提交处理
	 * <p>TODO</p>
	 * @return
	 */
	@Action(value = "/tmPosCommit", results = { @Result(name = SUCCESS, type = "json", params = { "includeProperties",
	"message,difMoney" }) })
	public String tmPosCommit() {
		if (StringUtils.isBlank(transferId)) {
			message = "400";//传入的审批划款编号不能为空
			return SUCCESS;
		}
		TransferPayInfo transferPayInfo=new TransferPayInfo();

		try {
			//根据transferId判断TransferPayInfo是否有记录 有则计算总金额
			BigDecimal isMoney =new BigDecimal("0.00");
			
			List<TransferPayInfo> list = transferPayInfoService.getByTransNo(transferId);
			for (TransferPayInfo transferPay : list) {
				isMoney = isMoney.add(transferPay.getPayMoney());
			}
			isMoney=isMoney.add(payMoney);
			transferMoney=transferMoneyService.getById(transferId);
			BigDecimal oldMoney = new BigDecimal("0.00");
			if(transferMoney != null && transferMoney.getAmt() != null){
				oldMoney = new BigDecimal(transferMoney.getAmt());
			}
			if (oldMoney.subtract(isMoney).doubleValue() >= -0.00001 && oldMoney.subtract(isMoney).doubleValue() <= 0.00001) {//判断金额
				//通知房管局划款成功
				SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("2", transferMoney.getHouseNo(), "" , "1", "划款成功！" , "", transferMoney.getAcceptcode() , Constant.BANKCODE);
				if("0000".equals(rspMsg.getCode())){
					log.info("通知房管局划款成功!");
					transferPayInfo.setAuditTransferMoneyNo(transferId);
					transferPayInfo.setDevid(devid);
					transferPayInfo.setNote(note);
					transferPayInfo.setOppName(oppName);
					transferPayInfo.setPayCardNo(payCardNo);
					transferPayInfo.setPayDay(payDay);
					transferPayInfo.setPayMoney(payMoney);
					transferPayInfo.setPayStatus("0010");
					transferPayInfo.setPayTradeNo(payTradeNo);
					transferPayInfo.setPayTranchannel(channel);
					transferPayInfo.setCreateTime(createTime);
					transferPayInfo.setPayTime(payDay);
					transferPayInfo.setPayBankName("中信银行兰州分行");
					if(transferPayInfoService.saveTransferPayInfo(transferPayInfo)){
						//修改审批划款记录划款状态
						transferMoneyService.updateTransferMoneyState(transferId, "0010");
						message = "200";//更新状态成功
					}else{
						message = "300";//未选择任何记录
					}
					log.info("---------------保存划款信息成功!");
				}else{
					log.info("通知房管局划款失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				}
				
			} else if(oldMoney.compareTo(isMoney)>1){
				transferPayInfo.setAuditTransferMoneyNo(transferId);
				transferPayInfo.setDevid(devid);
				transferPayInfo.setNote(note);
				transferPayInfo.setOppName(oppName);
				transferPayInfo.setPayCardNo(payCardNo);
				transferPayInfo.setPayDay(payDay);
				transferPayInfo.setPayMoney(payMoney);
				transferPayInfo.setPayStatus("0010");
				transferPayInfo.setPayTradeNo(payTradeNo);
				transferPayInfo.setPayTranchannel(channel);
				transferPayInfo.setCreateTime(createTime);
				transferPayInfo.setPayTime(payDay);
				transferPayInfo.setPayBankName("中信银行兰州分行");
				if(transferPayInfoService.saveTransferPayInfo(transferPayInfo)){
					difMoney=oldMoney.subtract(isMoney);
					message = "002";//流失提交成功，核对总金额
				}else{
					message = "300";//未选择任何记录
				}
			}else{
				message = "001";//核对金额
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			message = "500";//查询失败
			log.info("添加审核划款支付金额出错："+e.getMessage());
			return SUCCESS;
		}
	}
	
	/***
	 * 添加退款流水信息
	 */
	@Action(value = "/transferPayInfo" , results = {
			@Result(name = SUCCESS , location = "condo/transferPayInfoList.jsp"),
			@Result(name = INPUT , location = "base/error.jsp")
	})
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
	
	public PageFinder<AuditTransferMoney> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<AuditTransferMoney> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
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

	public AuditTransferMoney getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(AuditTransferMoney transferMoney) {
		this.transferMoney = transferMoney;
	}

	public List<TransferPayInfo> getPayList() {
		return payList;
	}

	public void setPayList(List<TransferPayInfo> payList) {
		this.payList = payList;
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
	
	public List<EbankLogInfo> geteBankLogInfos() {
		return eBankLogInfos;
	}

	public void seteBankLogInfos(List<EbankLogInfo> eBankLogInfos) {
		this.eBankLogInfos = eBankLogInfos;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPaiedMoney() {
		return paiedMoney;
	}

	public void setPaiedMoney(BigDecimal paiedMoney) {
		this.paiedMoney = paiedMoney;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public List<AuditTransferMoney> getAuditTransferMoneys() {
		return auditTransferMoneys;
	}

	public void setAuditTransferMoneys(List<AuditTransferMoney> auditTransferMoneys) {
		this.auditTransferMoneys = auditTransferMoneys;
	}

	public AuditTransferMoney getAuditTransferMoney() {
		return auditTransferMoney;
	}

	public void setAuditTransferMoney(AuditTransferMoney auditTransferMoney) {
		this.auditTransferMoney = auditTransferMoney;
	}

	public PosTranReq getReq() {
		return req;
	}

	public void setReq(PosTranReq req) {
		this.req = req;
	}
	
	

}
