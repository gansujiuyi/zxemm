package com.jiuyi.jyplat.service.condo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.BuildingInfoDao;
import com.jiuyi.jyplat.dao.condo.CondoPayInfoDao;
import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.dao.condo.RefundPayInfoDao;
import com.jiuyi.jyplat.dao.condo.TransferPayInfoDao;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.service.condo.ICondoPayInfoService;

/**
 * 购房支付流水表  Service 层 实现类
 * @author wsf
 *
 */
@Service("condoPayInfoService")
public class CondoPayInfoService implements ICondoPayInfoService {

	Logger log = Logger.getLogger(CondoPayInfoService.class);
	
	@Resource
	private CondoPayInfoDao condoPayInfoDao;
	@Resource
	private ContactInfoDao contactInfoDao;
	
	@Resource
	private RefundPayInfoDao refundPayInfDao;
	
	@Resource
	private TransferPayInfoDao transferPayInfoDao;
	
	@Resource
	private BuildingInfoDao  buildingInfoDao;

	@Override
	public List<CondoPayInfo> queryCandoPayInfo(String contactNo)
			throws Exception {
		// TODO Auto-generated method stub
		List<CondoPayInfo> condoPayInfo=new ArrayList<CondoPayInfo>();
		condoPayInfo=condoPayInfoDao.findBy("contactNo", contactNo);
		if(condoPayInfo!=null && condoPayInfo.size()>0){
			return condoPayInfo;
		}
		return null;
	}
	
	

	@Override
	public List<CondoPayInfo> queryCandoPayInfoList(CondoPayInfo condoPayInfo)
			throws Exception {
		return condoPayInfoDao.queryCandoPayInfoList(condoPayInfo);
	}



	@Override
	public Boolean savecondoPayInfoService(CondoPayInfo condoPayInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		if(condoPayInfo!=null){
			condoPayInfoDao.save(condoPayInfo);
			return true;
		}
		return false;
	}

	/**
	 * 根据地幢号查询所有的入账流水记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<CondoPayInfo> getByHouseNo(String houseNo) throws Exception {
		/*List<CondoPayInfo> list = new ArrayList<CondoPayInfo>();
		String sql = "select p.*  from t_contactInfo c , t_condoPayInfo p where c.contactno = p.contactno and c.houseno = '"+houseNo.trim()+"'";
		Query query = condoPayInfoDao.createSQLQuery(sql);
		list = query.list();
		return list;*/
		List<CondoPayInfo> list = new ArrayList<CondoPayInfo>();
		List<ContactInfo> contactList = contactInfoDao.findBy("houseNo", houseNo.trim());
		for (ContactInfo contactInfo : contactList) {
			List<CondoPayInfo> payList = condoPayInfoDao.findBy("contactNo", contactInfo.getContactNo().trim());
			for (CondoPayInfo condoPayInfo : payList) {
				list.add(condoPayInfo);
			}
		}
		return list;
	}

	@Override
	public void queryAccount() throws Exception {
		// TODO Auto-generated method stub
		List<ContactInfo> contactInfoList=contactInfoDao.loadAll();
		if(contactInfoList!=null && contactInfoList.size()>0){
			for(int j=0;j<contactInfoList.size();j++){
				List<BuildingInfo> buildingInfoList=buildingInfoDao.qurBuildingInfoByBlockNo(contactInfoList.get(j).getBackCardNo());
				if(buildingInfoList!=null && buildingInfoList.size()>0){
					for(int k=0;k<buildingInfoList.size();k++){
						BigDecimal payMoney=new BigDecimal(Double.toString(0.0));
						BigDecimal tranferMoney=new BigDecimal(Double.toString(0.0));
						BigDecimal refundMoney=new BigDecimal(Double.toString(0.0));
				/*		List<CondoPayInfo> condoPayInfoList=condoPayInfoDao.queryTodayPayInfo(buildingInfoList.get(k).getBuildingId());
						if(condoPayInfoList!=null && condoPayInfoList.size()>0){
							for(int i=0;i<condoPayInfoList.size();i++){
								if(condoPayInfoList.get(i).getPayStatus().equals("0010")){
									BigDecimal	payMoney1=new BigDecimal(Double.toString(condoPayInfoList.get(i).getPayMoney()));
									payMoney=payMoney.add(payMoney1);
								}
							}
						}
						List<RefundPayInfo> refundPayList=refundPayInfDao.queryTodayRefundInfo(buildingInfoList.get(k).getBuildingId());
						if(refundPayList!=null && refundPayList.size()>0){
							for(int i=0;i<refundPayList.size();i++){
								if(refundPayList.get(i).getPayStatus().equals("0010")){
									BigDecimal	refundMoney1=new BigDecimal(Double.toString(refundPayList.get(i).getPayMoney()));
									refundMoney=refundMoney.add(refundMoney1);
								}
							}
						}
						
						List<TransferPayInfo> transferList=transferPayInfoDao.queryTodayTransferInfo(buildingInfoList.get(k).getBuildingId());
						if(transferList!=null && transferList.size()>0){
							for(int i=0;i<transferList.size();i++){
								if(transferList.get(i).getPayStatus().equals("0010")){
									BigDecimal	tranferMoney1=new BigDecimal(Double.toString(transferList.get(i).getPayMoney()));
									tranferMoney=tranferMoney.add(tranferMoney1);
								}
							}
						}	*/
					}
				}
			}
		}
	}
}
