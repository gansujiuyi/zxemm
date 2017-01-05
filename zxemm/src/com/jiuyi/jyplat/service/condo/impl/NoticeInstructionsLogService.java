package com.jiuyi.jyplat.service.condo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.AuditTransferMoneyDao;
import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.dao.condo.NoticeInstructionsLogDao;
import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.service.condo.INoticeInstructionsLogService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DateUtils;

/**
 *  新房指令通知日志表  Service 层实现类
 * @author wsf
 *
 */
@Service("instructionsLogService")
public class NoticeInstructionsLogService implements
		INoticeInstructionsLogService {

	Logger log = Logger.getLogger(NoticeInstructionsLogService.class);
	
	@Resource
	private NoticeInstructionsLogDao instructionsLogDao;
	@Resource
	private AuditTransferMoneyDao transferMoneyDao;
	@Resource
	private BuildingInfoDao	buildingInfoDao;
	@Resource
	private ContactInfoDao  contactInfoDao;
	@Resource
	private INewHouseExchangService newHouseExchangService;
	
	/**
	 * 新增 NoticeInstructionsLog 新房指令通知日志
	 * @param instructionsLog
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveNoticeInstructionsLog(NoticeInstructionsLog instructionsLog)
			throws Exception {
		instructionsLogDao.save(instructionsLog);
		log.info("新增 NoticeInstructionsLog 新房指令通知日志成功！");
		//指令种类   1为开户指令  2 为审批划款指令  3为解除资金监管指令  4为撤销购买合同的指令  5 开发账户变更的指令  6购房入账通知指令  
		if("1".equals(instructionsLog.getInstructionsVariety())){
			//判断商品房楼幢信息存不存在
			BuildingInfo buildingInfo = buildingInfoDao.findUniqueBy("buildingId", instructionsLog.getHouseNo().trim());
			if(buildingInfo == null){
				buildingInfo = new BuildingInfo();
				buildingInfo.setBuildingId(instructionsLog.getHouseNo().trim());
				buildingInfo.setStatus(Constant.account_status_noopen);//账户状态  1002：未开启
				buildingInfo.setRegulateStatus(Constant.noregulate_status);//监管状态  0001：未监管
				buildingInfoDao.save(buildingInfo);
				log.info("新增开户商品房楼幢信息成功！");
			}
		}else if("2".equals(instructionsLog.getInstructionsVariety())){
			List<AuditTransferMoney> auditTransferMoney = transferMoneyDao.qurAuditTransferMoney(instructionsLog.getAcceptcode(), 
					instructionsLog.getHouseNo());
			if(auditTransferMoney == null || auditTransferMoney.size() == 0){
				AuditTransferMoney money = new AuditTransferMoney();
				money = new AuditTransferMoney();
				money.setHouseNo(instructionsLog.getHouseNo());
				money.setAmt(instructionsLog.getAmt());
				money.setState("1111");//1111：初始状态
				money.setAcceptcode(instructionsLog.getAcceptcode());
				money.setTransferMoneyDate(DateUtils.curTime());
				transferMoneyDao.save(money);
				log.info("新增审批划款记录成功！");
			}
		}else if("3".equals(instructionsLog.getInstructionsVariety())){
			
			log.info("通知解除资金监管成功！");
		}else if("4".equals(instructionsLog.getInstructionsVariety())){
			ContactInfo contactInfo = contactInfoDao.findUniqueBy("contactNo", instructionsLog.getContactNo());
			if(null!=contactInfo){
				contactInfo.setStatus("4444");//退款中
				contactInfo.setPayStatus("0010");
				contactInfoDao.update(contactInfo);
				log.info("撤销购买合同成功！");
			}else{
				log.error("未查询到合同信息");
			}
			
		}
		
	}

	@Override
	public List<NoticeInstructionsLog> queNoticeLogByHouseNo(String houseNo,
			String instructionsVariety) throws Exception {
		// TODO Auto-generated method stub
		return  instructionsLogDao.queNoticeLogByHouseNo(houseNo,instructionsVariety);
	}
}
