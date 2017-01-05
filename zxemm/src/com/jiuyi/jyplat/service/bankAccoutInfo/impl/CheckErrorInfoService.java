package com.jiuyi.jyplat.service.bankAccoutInfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.dao.bankAccountInfo.CheckErrorInfoDao;
import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows;
import com.jiuyi.jyplat.entity.bankAccoutInfo.CheckErrorInfo;
import com.jiuyi.jyplat.entity.condo.BlockInfo;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountFlowsService;
import com.jiuyi.jyplat.service.bankAccoutInfo.ICheckErrorInfoService;
import com.jiuyi.jyplat.service.condo.IBlockInfoService;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service("checkErrorInfoService")
public class CheckErrorInfoService implements ICheckErrorInfoService {

	Logger log = Logger.getLogger(CheckErrorInfoService.class);

	@Resource
	private CheckErrorInfoDao checkErrorInfoDao;
	@Resource
	private BuildingInfoDao buildingInfoDao;
	@Resource
	private IBlockInfoService blockInfoService;
	@Resource
	private IContactInfoService contactInfoService;
	@Resource
	private IAccountFlowsService accountFlowsService;
	

	@Override
	public PageFinder<CheckErrorInfo> CheckErrorInfo(String buildId,
			String transactionSeqNum, Query query) throws Exception {
		Criteria criteria = checkErrorInfoDao.createCriteria();
		if (null != buildId && !"".equals(buildId)) {
			criteria.add(Restrictions.eq("buildId", buildId.trim()));
		}
		if (null != transactionSeqNum && !"".equals(transactionSeqNum)) {
			criteria.add(Restrictions.eq("transactionSeqNum",
					transactionSeqNum.trim()));
		}
		return checkErrorInfoDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("id"));
	}


	@Override
	public void saveCheckInfo() {
		
		try {
			DBAction dbAction = new DBAction();
			//获取当前实户信息
			List<BlockInfo> blockInfos = blockInfoService.queryAllInfo();
			if (null != blockInfos && blockInfos.size() > 0) {
				for (int i = 0; i < blockInfos.size(); i++) {
					BlockInfo tmp = blockInfos.get(i);
					//根据blockNO 查询所有的虚户
					List<BuildingInfo> infos = buildingInfoDao.qurBuildingInfoByBlockNo(tmp.getBlockNo());
					if (null != infos && infos.size() > 0) {
						//查询所有合同信息
						for (int j = 0; j < infos.size(); j++) {
							BuildingInfo buildingInfo = infos.get(j);
							List<ContactInfo> contactInfos = contactInfoService.getByHouseNo(buildingInfo.getBuildingId(),null);
							buildingInfo.getRegulateAccount();//获取资金监管账号
							for (int k = 0; k < contactInfos.size(); k++) {
						    	ContactInfo tmpCon = contactInfos.get(k);
						    	//根据合同号查询流水信息，并根据流水号判断当前的资金监管账号和已绑定流水的资金监管账号是否相同
						    	String sql="select transtno from t_contran where contractno ='"+tmpCon.getContactNo()+"'";
						    	String[][] ret = dbAction.executeSearchAll(sql);
						    	for (int l = 0; l < ret.length; l++) {
									//根据流水号查询监管账户
						    		AccountFlows tmp_ = new AccountFlows();
						    		tmp_.setTransactionSeqNum(ret[l][0]);
						    		AccountFlows accountFlows =accountFlowsService.getAccountFlowsBySeqNum(tmp_);
						    		if(null!=accountFlows){
						    			if(!accountFlows.getSysAccount().equals(buildingInfo.getRegulateAccount())){
						    				//保存错误信息 
						    				CheckErrorInfo errorInfo = new CheckErrorInfo();
						    				errorInfo.setBuildId(buildingInfo.getBuildingId());
						    				errorInfo.setTransactionSeqNum(ret[l][0]);
						    				errorInfo.setTransactionAmount(accountFlows.getTransactionAmount());
						    				errorInfo.setContractNo(tmpCon.getContactNo());
						    				errorInfo.setSysAccount(accountFlows.getSysAccount());
						    				errorInfo.setCreatetime(DateUtils.getCurDate());
						    				errorInfo.setMsg("当前流水对应的监管账户错误");
						    				saveCheckInfo(errorInfo);
						    			}
						    		}
								}
							}
						  
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("定时任务执行错误!");
		}
		
	}
	
	public void saveCheckInfo(CheckErrorInfo checkErrorInfo){
		try {
			DBAction dbAction = new DBAction();
			String couSql = "select count(1) from t_checkerrinfo a where a.buildId='"
					+ checkErrorInfo.getBuildId()
					+ "' and a. transactionSeqNum='"
					+ checkErrorInfo.getTransactionSeqNum() 
					+ "' and a.contractNo='"
					+checkErrorInfo.getContractNo()
					+"' and a.createtime='"
					+DateUtils.getCurDate()+"'";
			String[] ret = dbAction.executeSearch(couSql);
			if(null!=ret && ret.length>0){
				if(Integer.valueOf(ret[0])==0){
					checkErrorInfoDao.save(checkErrorInfo);
				}
			}
		} catch (Exception e) {
			 log.error("查询错误");
			 
		}
	}
}
