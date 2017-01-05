package com.jiuyi.net.message.condo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jiuyi.net.message.MsgRetCode;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.condo.IBuildingInfoService;
import com.jiuyi.jyplat.service.condo.ICondoBuyerService;
import com.jiuyi.jyplat.service.condo.ICondoPayInfoService;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.service.condo.INoticeInstructionsLogService;
import com.jiuyi.jyplat.service.condo.IRefundPayInfoService;
import com.jiuyi.jyplat.service.condo.ITransferPayInfoService;

/**
 * 新房资金监管相关接口辅助类
 * 
 * @author wsf
 * 
 */
public class NewHouseExchangCondoUtil {

	private Logger log = Logger.getLogger(NewHouseExchangCondoUtil.class);

	private static NewHouseExchangCondoUtil instance;

	public static NewHouseExchangCondoUtil getInstance() {
		if (instance == null) {
			instance = new NewHouseExchangCondoUtil();
		}
		return instance;
	}

	/**
	 * 新房指令通知接口
	 */
	public NoticeInstructionsRspMsg noticeInstructions(
			NoticeInstructionsReqMsg reqMsg) {
		NoticeInstructionsRspMsg rspMsg = new NoticeInstructionsRspMsg();
		NoticeInstructionsRsp rsp = new NoticeInstructionsRsp();
		rspMsg.setMsghead(reqMsg.getMsghead());
		rspMsg.setMsgrsp(rsp);

		NoticeInstructionsReq req = reqMsg.getMsgreq();

		String instructionsVariety = req.getInstructionsVariety();
		String houseNo = req.getHouseNo();
		String amt = req.getAmt();
		String contactNo = req.getContactNo();
		String modifyAccount = req.getModifyAccount();
		String acceptcode= req.getAcceptcode();
		String bankCode = req.getBankCode();

		if (StringUtils.isBlank(instructionsVariety)) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("instructionsVariety不能为空！");
			return rspMsg;
		}
		if (StringUtils.isBlank(houseNo)) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("houseNo不能为空！");
			return rspMsg;
		}
		try {
			// 业务逻辑
			INoticeInstructionsLogService instructionsLogService = (INoticeInstructionsLogService) SpringContextUtil
					.getBean("instructionsLogService");
			NoticeInstructionsLog instructionsLog = new NoticeInstructionsLog();
			instructionsLog.setHouseNo(houseNo);
			instructionsLog.setInstructionsVariety(instructionsVariety);
			instructionsLog.setAmt(amt);
			instructionsLog.setContactNo(contactNo);
			instructionsLog.setModifyAccount(modifyAccount);
			instructionsLog.setAcceptcode(acceptcode);
			instructionsLog.setLogDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			instructionsLog.setBankCode(bankCode);
			instructionsLogService.saveNoticeInstructionsLog(instructionsLog);
			rsp.setRetcode(MsgRetCode.Trans_Success_Code);
			rsp.setRetshow("操作成功！");
			return rspMsg;
		} catch (Exception e) {
			log.error("新房指令通知时出现错误:" + e.getMessage(), e);
			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			return rspMsg;
		}
	}

	/**
	 * 房管局查询银行状态接口
	 */
	public QurBankNoticeRspMsg qurBankNotice(QurBankNoticeReqMsg reqMsg) {
		QurBankNoticeRspMsg rspMsg = new QurBankNoticeRspMsg();
		QurBankNoticeRsp rsp = new QurBankNoticeRsp();
		rspMsg.setMsghead(reqMsg.getMsghead());
		rspMsg.setMsgrsp(rsp);

		QurBankNoticeReq req = reqMsg.getMsgreq();

		String noticeVariety = req.getNoticeVariety();
		String houseNo = req.getHouseNo();
		String buyerName = req.getBuyerName();
		String buyerIdNo = req.getBuyerIdNo();
		double amt = req.getAmt();
		String houseAddr = req.getHouseAddr();

		if (StringUtils.isBlank(noticeVariety)) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("noticeVariety不能为空！");
			return rspMsg;
		}
		if (StringUtils.isBlank(houseNo)) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("houseNo不能为空！");
			return rspMsg;
		}
		try {
			// 业务逻辑

			rsp.setStatus("待定");
			rsp.setRetcode(MsgRetCode.Trans_Success_Code);
			rsp.setRetshow("操作成功！");
			return rspMsg;
		} catch (Exception e) {
			log.error("房管局查询银行状态时出现错误:" + e.getMessage(), e);
			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			return rspMsg;
		}
	}

	/**
	 * 按合同编号查询各户的缴存记录
	 */
	public QurRecordByContractNoRspMsg qurRecordByContractNo(QurRecordByContractNoReqMsg reqMsg) {
		QurRecordByContractNoRspMsg rspMsg = new QurRecordByContractNoRspMsg();
		QurRecordByContractNoRsp rsp = new QurRecordByContractNoRsp();
		rspMsg.setMsghead(reqMsg.getMsghead());
		rspMsg.setMsgrsp(rsp);

		QurRecordByContractNoReq req = reqMsg.getMsgreq();

		String contractNo = req.getContractNo();
		String bankCode= req.getBankCode();

		if (StringUtils.isBlank(contractNo) && StringUtils.isBlank(bankCode)) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("contractNo不能为空！");
			return rspMsg;
		}
		try {
			// 业务逻辑
			// 查询合同信息
			IContactInfoService contactService = (IContactInfoService) SpringContextUtil.getBean("contactInfoService");
			ContactInfo contactInfo = contactService.getBuyHouseContactInfo(contractNo.trim() ,bankCode.trim());
			if(contactInfo==null){
				rsp.setRetcode(MsgRetCode.Database_Query_Code);
				rsp.setRetshow("合同信息为空");
				return rspMsg;
			}else{
				// 查询该合同下的所有入账流水
				ICondoPayInfoService condoInfoService = (ICondoPayInfoService) SpringContextUtil.getBean("condoPayInfoService");
				List<CondoPayInfo> condoPayInfos = condoInfoService.queryCandoPayInfo(contractNo.trim());
				// 查询该合同下的所有出账流水
				IRefundPayInfoService refundInfoService = (IRefundPayInfoService) SpringContextUtil.getBean("refundPayInfoService");
				List<RefundPayInfo> refundPayInfos = refundInfoService.getByContactNo(contractNo.trim());
				rsp.setCondoPayInfos(condoPayInfos);
				rsp.setRefundPayInfos(refundPayInfos);
				rsp.setSuperviseAmt(contactInfo.getSuperviseAmt());
				rsp.setRetcode(MsgRetCode.Trans_Success_Code);
				rsp.setRetshow("操作成功！");
				return rspMsg;
			}

		} catch (Exception e) {
			log.error("按合同编号查询各户的缴存记录时出现错误:" + e.getMessage(), e);
			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			return rspMsg;
		}
	}

	/**
	 * 查询整幢楼下各户的缴存记录
	 */
	public QurRecordByHouseNoRspMsg qurRecordByHouseNo(
			QurRecordByHouseNoReqMsg reqMsg) {
		QurRecordByHouseNoRspMsg rspMsg = new QurRecordByHouseNoRspMsg();
		QurRecordByHouseNoRsp rsp = new QurRecordByHouseNoRsp();
		rspMsg.setMsghead(reqMsg.getMsghead());
		rspMsg.setMsgrsp(rsp);

		QurRecordByHouseNoReq req = reqMsg.getMsgreq();

		String houseNo = req.getHouseNo();
		String bankCode = req.getBankCode();

		if (StringUtils.isBlank(houseNo.trim()) && StringUtils.isBlank(bankCode.trim())) {
			log.error("请求参数不完整！");
			rsp.setRetcode(MsgRetCode.Requset_Format_Code);
			rsp.setRetshow("houseNo不能为空！");
			return rspMsg;
		}
		try {
			// 业务逻辑
			ICondoPayInfoService condoPayInfoService = (ICondoPayInfoService) SpringContextUtil.getBean("condoPayInfoService");
			ITransferPayInfoService transferPayInfoService = (ITransferPayInfoService) SpringContextUtil.getBean("transferPayInfoService");
			IContactInfoService contactService = (IContactInfoService) SpringContextUtil.getBean("contactInfoService");
			ICondoBuyerService buyerService = (ICondoBuyerService) SpringContextUtil.getBean("condoBuyerService");
			IBuildingInfoService buildingService = (IBuildingInfoService) SpringContextUtil.getBean("buildingInfoService");

			List<CondoPayInfo> condoList = condoPayInfoService.getByHouseNo(houseNo.trim());
			List<TransferPayInfo> transferList = transferPayInfoService.getByHouseNo(houseNo.trim());
			List<ContactInfo> contactInfos = contactService.getByHouseNo(houseNo.trim() ,null);
			List<CondoBuyer> condoBuyers = buyerService.getByHouseNo(houseNo.trim());
			BuildingInfo buildingInfo = buildingService.queryBuildingInfoByBuilidId(houseNo.trim());

			rsp.setHouseNo(houseNo.trim());
			rsp.setCondoPayInfos(condoList);
			rsp.setTransferPayInfos(transferList);
			rsp.setCondoBuyers(condoBuyers);
			rsp.setContactInfos(contactInfos);
			rsp.setRegulateAccName(buildingInfo.getRegulateAccName());
			rsp.setRegulateAccount(buildingInfo.getRegulateAccount());
			rsp.setRetcode(MsgRetCode.Trans_Success_Code);
			rsp.setRetshow("操作成功！");
			return rspMsg;
		} catch (Exception e) {
			log.error("查询整幢楼下各户的缴存记录时出现错误:" + e.getMessage(), e);
			rsp.setRetcode(MsgRetCode.Database_Query_Code);
			rsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			return rspMsg;
		}
	}

	/**
	 * 查询整幢楼下各户的缴存记录
	 */
	public QurAmountByBuildingNoRspMsg qurAmountByBuildingNo(
		QurAmountByBuildingNoReqMsg reqMsg) {
		QurAmountByBuildingNoRspMsg rspMsg = new QurAmountByBuildingNoRspMsg();
		QurAmountByBuildingNoRsp msgrsp = new QurAmountByBuildingNoRsp();
		rspMsg.setMsghead(reqMsg.getMsghead());
		rspMsg.setMsgrsp(msgrsp);

		QurAmountByBuildingNoReq req = reqMsg.getMsgreq();

		String houseNo = req.getHouseNo();

		if (StringUtils.isBlank(houseNo.trim())) {
			log.error("请求参数不完整！");
			msgrsp.setRetcode(MsgRetCode.Requset_Format_Code);
			msgrsp.setRetshow("houseNo不能为空！");
			return rspMsg;
		}
		try {
			BigDecimal payMoney=new BigDecimal(Double.toString(0.0));
			BigDecimal tranferMoney=new BigDecimal(Double.toString(0.0));
			BigDecimal refundMoney=new BigDecimal(Double.toString(0.0));
			String accountNo="";
			ICondoPayInfoService condoPayInfoService = (ICondoPayInfoService) SpringContextUtil.getBean("condoPayInfoService");
			ITransferPayInfoService transferPayInfoService = (ITransferPayInfoService) SpringContextUtil.getBean("transferPayInfoService");
			IRefundPayInfoService refundPayInfoService=(IRefundPayInfoService) SpringContextUtil.getBean("refundPayInfoService");
			IBuildingInfoService buildingService = (IBuildingInfoService) SpringContextUtil.getBean("buildingInfoService");
			log.info("根据地幢号查询缴款信息：houseNo="+houseNo);
			List<CondoPayInfo> condoList = condoPayInfoService.getByHouseNo(houseNo.trim());
			if(condoList!=null && condoList.size()>0){
				for(int i=0;i<condoList.size();i++){
					if(condoList.get(i).getPayStatus().equals("0010")){
						BigDecimal	payMoney1=condoList.get(i).getPayMoney();
						payMoney=payMoney.add(payMoney1);
					}
				}
			}
			log.info("缴款信息查询完毕，根据地幢号查询划款信息！");
			List<TransferPayInfo> transferList = transferPayInfoService.getByHouseNo(houseNo.trim());
 
			if(transferList!=null && transferList.size()>0){
				for(int i=0;i<transferList.size();i++){
					if(transferList.get(i).getPayStatus().equals("0010")){
						BigDecimal	tranferMoney1=transferList.get(i).getPayMoney();
						tranferMoney=tranferMoney.add(tranferMoney1);
					}
				}
			}
			log.info("划款信息查询完毕，根据地幢号查询退款信息");
			List<RefundPayInfo> refundPayList=refundPayInfoService.getByHouseNo(houseNo.trim());
			if(refundPayList!=null && refundPayList.size()>0){
				for(int i=0;i<refundPayList.size();i++){
					if(refundPayList.get(i).getPayStatus().equals("0010")){
						BigDecimal	refundMoney1= refundPayList.get(i).getPayMoney();
						refundMoney=refundMoney.add(refundMoney1);
					}
				}
			}
			log.info("退款信息查询完毕,根据地幢号查询监管账号信息！");
			
			BuildingInfo buildingInfo = buildingService.queryBuildingInfoByBuilidId(houseNo.trim());
			if(buildingInfo!=null && buildingInfo.getRegulateAccount()!=null){
				accountNo=buildingInfo.getRegulateAccount();
			}
			msgrsp.setRegulateAccount(accountNo);
			msgrsp.setRetshow("交易成功");
			msgrsp.setRegulateAmount(payMoney.subtract(tranferMoney).subtract(refundMoney)+"");
			msgrsp.setRetcode(MsgRetCode.Trans_Success_Code);
			
			return rspMsg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查询整幢楼下各户的缴存记录时出现错误:" + e.getMessage(), e);
			msgrsp.setRetcode(MsgRetCode.Database_Query_Code);
			msgrsp.setRetshow(MsgRetCode.Database_Query_Show + e.getMessage());
			return rspMsg;
		}
	}
}
