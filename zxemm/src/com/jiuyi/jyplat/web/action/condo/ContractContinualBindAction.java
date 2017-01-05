package com.jiuyi.jyplat.web.action.condo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.authority.AuthName;
import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows;
import com.jiuyi.jyplat.entity.bankAccoutInfo.ContractTranstion;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountFlowsService;
import com.jiuyi.jyplat.service.condo.ICondoPayInfoService;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.util.MathUtils;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;

/***
 * 合同流水绑定
 * 
 * @author baizilin
 * 
 */
@Namespace("contractContinual")
public class ContractContinualBindAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8157339243223781458L;

	Logger log = Logger.getLogger(ContractContinualBindAction.class);

	@Resource
	private IContactInfoService contactInfoService;

	@Resource
	private ICondoPayInfoService condoPayInfoService;

	@Resource
	private IAccountFlowsService accountFlowsService;

	@Resource
	private INewHouseExchangService newHouseExchangService;

	private String contactNo;// 合同号
	private String createDate;// 合同创建时间
	private String createTime;// 支付时间
	private String payCardNo;// 流水号
	private String houseNo;// 地幢号
	private List<ContactInfo> contactInfos;// 合同集合
	private List<CondoPayInfo> payList;// 支付流水集合
	private List<AccountFlows> accountFlows;
	private String sysAccount;// 系统账户
	private String transactionDate;// 交易日期
	private String contractStr;// 已选择的合同
	private String contractTranStr;// 已选择的流水
	private String message;
	private String selectContractNo;//已选择的合同，防止再次查询时出现重复数据，进行数据过滤
	private String selectContractTranst;//已选择的流水，放在再次查询时出现重复数据，进行过滤

	/**
	 * 合同流水绑定页面
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/ContractContinual", results = {
			@Result(name = SUCCESS, location = "condo/contractContinual.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String ContractContinual() {

		return SUCCESS;

	}

	/***
	 * 根据合同号查询合同信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/queryContactInfo", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "contactInfos.*" }) })
	public String queryContactInfo() {
		try {
			List<ContactInfo> infos = contactInfoService.getByContractNo(houseNo,contactNo, createDate);
			contactInfos = new ArrayList<ContactInfo>();
			//判断是否已经存在选中的数据
			if(null!=selectContractNo && selectContractNo.length()>0){
				selectContractNo = selectContractNo.substring( 0 ,selectContractNo.lastIndexOf(","));
				  if(null!=infos && infos.size()>0){
					  for (int i = 0; i < infos.size(); i++) {
						  ContactInfo temp =  infos.get(i);
						  if(!selectContractNo.contains(temp.getContactNo().toString())){
							  contactInfos.add(temp);
						 }
					}
				  }
			}else{
				contactInfos = infos;
			}
		} catch (Exception e) {
			log.equals("根据合同号查询合同信息失败" + e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	/***
	 * 根据支付卡号或者日期查询支付信息
	 * 
	 * @return
	 */
	@AuthName
	@Action(value = "/qyeryTranstInfo", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "accountFlows.*" }) })
	public String qyeryTranstInfo() {
		try {
			List<AccountFlows> flows = accountFlowsService.getAllFlowsByCondition( sysAccount, transactionDate, "0001");
			accountFlows = new ArrayList<AccountFlows>();
			//判断是否已经存在选中的数据
			if(null!=selectContractTranst && selectContractTranst.length()>0){
				selectContractTranst = selectContractTranst.substring( 0 ,selectContractTranst.lastIndexOf(","));
				  if(null!=flows && flows.size()>0){
					  for (int i = 0; i < flows.size(); i++) {
						  AccountFlows temp =  flows.get(i);
						  System.out.println(temp.getId()+"--------------");
						  System.out.println(selectContractTranst+"===================");
						  if(!selectContractTranst.contains(temp.getId())){
							  accountFlows.add(temp);
						 }
					}
				  }
			}else{
				accountFlows = flows;
			}
		} catch (Exception e) {
			log.equals("根据合同号查询合同信息失败" + e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	@AuthName
	@Action(value = "/saveContractTrans", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "message" }) })
	public String saveContractTrans() {
		try {
			if (null != contractStr && !"".equals(contractStr)&& null != contractTranStr && !"".equals(contractTranStr)) {
				/***
				 * 1、1个合同对应1笔流水
				 * 2、1个合同对应多笔流水
				 * 3、多个合同对应多个流水
				 * 4、多个合同对应1笔流水
				 */
				//生成一个随机数将同一批次合同保存在合同流水绑定表（T_CONTRAN）EXT1字段中保证每次付款不会重复
				String randomStr = String.valueOf(UUID.randomUUID());
				BigDecimal contractMoney = new BigDecimal("0.00");
				BigDecimal transtMoney = new BigDecimal("0.00");
				BigDecimal payMoney = new BigDecimal("0.00");
				BigDecimal needPayMoney = new BigDecimal("0.00");
				String[] contr_ = contractStr.split("@");
				
				//根据合同号查询出合同金额,已付金额
				List htlsList = contactInfoService.queryContractMoneyByContractNo(contr_);
				if(null!=htlsList && htlsList.size()>0){
					Object[] obj = (Object[]) htlsList.get(0);
					contractMoney =  new BigDecimal(obj[0]+"");
					payMoney =  new BigDecimal(obj[1]+"");
				}
				//计算流水总和
				String[] contrA_ = contractTranStr.split("@");
				for (int c = 0; c < contrA_.length; c++) {
					String transtNo = contrA_[c];
					transtMoney = transtMoney.add(new BigDecimal(transtNo.split("\\|\\|")[0]));
				}
				//计算需要支付的金额
				needPayMoney = transtMoney.add(payMoney);
				if(contractMoney.subtract(needPayMoney).doubleValue() >= -0.00001 && contractMoney.subtract(needPayMoney).doubleValue() <= 0.00001){
                     //保存合同流水信息
					for (int d = 0; d < contr_.length; d++) {
						//获取合同信息
						BigDecimal isNeedMoney = new BigDecimal("0.00");
						ContactInfo	contactInfo_=contactInfoService.getBuyHouseContactInfo(contr_[d], Constant.BANKCODE);//合同信息
						//获取合同支付流水信息
						List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contr_[d]);
						if(list!=null && list.size()>0){
							for (int k = 0; k < list.size(); k++) {
								CondoPayInfo pay = list.get(k);
								isNeedMoney = isNeedMoney.add(pay.getPayMoney());
							}
						}
						//支付金额减去合同金额
						transtMoney = new BigDecimal(MathUtils.sub(transtMoney.doubleValue(), Double.valueOf(contactInfo_.getContactAmt())));
						isNeedMoney=isNeedMoney.add(transtMoney);//房管局测试需要注释掉
						contactInfo_.setSuperviseAmt(contactInfo_.getContactAmt());
						contactInfo_.setPayStatus("0010");
						contactInfo_.setStatus("3333");
						for (int e = 0; e < contrA_.length;e++) {
							String transtInfo = contrA_[e];
							ContractTranstion contractTranstion = new ContractTranstion();
							CondoPayInfo condoPayInfo=new CondoPayInfo();
							contractTranstion.setContractNo(contr_[d]);
							contractTranstion.setTranstNo(transtInfo.split("\\|\\|")[1]);
							contractTranstion.setExt1(randomStr);
							AccountFlows tmp_ = new AccountFlows();
							tmp_.setTransactionSeqNum(transtInfo.split("\\|\\|")[1]);
							AccountFlows flows = accountFlowsService.getAccountFlowsBySeqNum(tmp_);
							condoPayInfo.setContactNo(contr_[d]);
							condoPayInfo.setDevid("");
							condoPayInfo.setNote(flows.getMemo());
							condoPayInfo.setOppName(flows.getOppositeCustAccountName());
							condoPayInfo.setPayCardNo(flows.getOppositeCustAccount());
							condoPayInfo.setPayDay(DateUtils.getToday());
							condoPayInfo.setPayMoney(new BigDecimal(transtInfo.split("\\|\\|")[0]));
							condoPayInfo.setPayStatus("0010");
							condoPayInfo.setPayTradeNo(flows.getTransactionSeqNum());
							condoPayInfo.setPayTranschannel("");
							condoPayInfo.setCreateTime(DateUtils.getToday());
							condoPayInfo.setPayTime(flows.getTransactionTime());
							condoPayInfo.setPayBankName("中信银行兰州分行");
							if(contactInfoService.saveContractBilding(contactInfo_, contractTranstion, condoPayInfo)){
								//通知房管局操作状态
								SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("6", contactInfo_.getHouseNo(), contactInfo_.getContactNo() , "1", "购房入账成功！" , "", "" , Constant.BANKCODE);
								if("0000".equals(rspMsg.getCode())){
									log.info("通知购房入账通知成功!");
								}
							}
						}
					}
					message = "200";//更新状态成功
				}else if(contractMoney.compareTo(needPayMoney)==1){
					 //保存合同流水信息
					for (int d = 0; d < contr_.length; d++) {
						ContactInfo	contactInfo_=contactInfoService.getBuyHouseContactInfo(contr_[d], Constant.BANKCODE);//合同信息
						contactInfo_.setPayStatus("0011");//部分支付
						for (int e = 0; e < contrA_.length; e++) {
							String transtInfo = contrA_[e];
							//获取合同信息
							BigDecimal isNeedMoney = new BigDecimal("0.00");
							
							//获取合同支付流水信息
							List<CondoPayInfo> list = condoPayInfoService.queryCandoPayInfo(contr_[d]);
							if(list!=null && list.size()>0){
								for (int k = 0; k < list.size(); k++) {
									CondoPayInfo pay = list.get(k);
									isNeedMoney = isNeedMoney.add(pay.getPayMoney());
								}
							}
							isNeedMoney=isNeedMoney.add(new BigDecimal(transtInfo.split("\\|\\|")[0]));//房管局测试需要注释掉
//							contactInfo_.setSuperviseAmt(String.valueOf(isNeedMoney));
							ContractTranstion contractTranstion = new ContractTranstion();
							CondoPayInfo condoPayInfo=new CondoPayInfo();
							contractTranstion.setContractNo(contr_[d]);
							contractTranstion.setTranstNo(transtInfo.split("\\|\\|")[1]);
							contractTranstion.setExt1(randomStr);
							AccountFlows tmp_ = new AccountFlows();
							tmp_.setTransactionSeqNum(transtInfo.split("\\|\\|")[1]);
							AccountFlows flows = accountFlowsService.getAccountFlowsBySeqNum(tmp_);
							condoPayInfo.setContactNo(contr_[d]);
							condoPayInfo.setDevid("");
							condoPayInfo.setNote(flows.getMemo());
							condoPayInfo.setOppName(flows.getOppositeCustAccountName());
							condoPayInfo.setPayCardNo(flows.getOppositeCustAccount());
							condoPayInfo.setPayDay(DateUtils.getToday());
							condoPayInfo.setPayMoney(new BigDecimal(transtInfo.split("\\|\\|")[0]));
							condoPayInfo.setPayStatus("0010");
							condoPayInfo.setPayTradeNo(flows.getTransactionSeqNum());
							condoPayInfo.setPayTranschannel("");
							condoPayInfo.setCreateTime(DateUtils.getToday());
							condoPayInfo.setPayTime(flows.getTransactionTime());
							condoPayInfo.setPayBankName("中信银行兰州分行");
							if(contactInfoService.saveContractBilding(contactInfo_, contractTranstion, condoPayInfo)){
								//通知房管局操作状态
								SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("6", contactInfo_.getHouseNo(), contactInfo_.getContactNo() , "1", "购房入账成功！" , "", "" , Constant.BANKCODE);
								if("0000".equals(rspMsg.getCode())){
									log.info("通知购房入账通知成功!");
								}
							}
						}
					}
					message = "200";//更新状态成功
				}else if(contractMoney.compareTo(needPayMoney)== -1){
					message = "001";//核对金额,支付金额超出合同金额
					log.info("核对金额,支付金额超出合同金额");
					return SUCCESS;
				}else{
					message = "001";//核对金额
					return SUCCESS;
				}
			}else{
				  message = "300";//未选择任何记录
					return SUCCESS;  
			}
		} catch (Exception e) {
			message = "301";// 查询失败
			log.equals("根据合同号查询合同信息失败" + e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	@AuthName
	@Action(value = "/queryContractByExt1", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "message" }) })
	public String queryContractByExt1() {
		try {
			String contractNo_ = contactInfoService.queryContractByExt1(contactNo);
			message =contractNo_;
		} catch (Exception e) {
			message="error";
		}
		return SUCCESS;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public List<CondoPayInfo> getPayList() {
		return payList;
	}

	public void setPayList(List<CondoPayInfo> payList) {
		this.payList = payList;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<AccountFlows> getAccountFlows() {
		return accountFlows;
	}

	public void setAccountFlows(List<AccountFlows> accountFlows) {
		this.accountFlows = accountFlows;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getContractStr() {
		return contractStr;
	}

	public void setContractStr(String contractStr) {
		this.contractStr = contractStr;
	}

	public String getContractTranStr() {
		return contractTranStr;
	}

	public void setContractTranStr(String contractTranStr) {
		this.contractTranStr = contractTranStr;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSelectContractNo() {
		return selectContractNo;
	}

	public void setSelectContractNo(String selectContractNo) {
		this.selectContractNo = selectContractNo;
	}

	public String getSelectContractTranst() {
		return selectContractTranst;
	}

	public void setSelectContractTranst(String selectContractTranst) {
		this.selectContractTranst = selectContractTranst;
	}

}
