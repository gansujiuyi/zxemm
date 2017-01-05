package com.jiuyi.jyplat.service.condo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.dao.condo.RefundPayInfoDao;
import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.service.condo.IRefundPayInfoService;
/**
 * 购房退款流水类 Service层实现类
 * @author wsf
 *
 */
@Service("refundPayInfoService")
public class RefundPayInfoService implements IRefundPayInfoService {
	
	Logger log = Logger.getLogger(RefundPayInfoService.class);
	
	@Resource
	private RefundPayInfoDao refundPayInfoDao;
	@Resource
	private ContactInfoDao contactInfoDao;

	/**
	 * 根据合同编号查询退款记录
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<RefundPayInfo> getByContactNo(String contactNo)
			throws Exception {
		List<RefundPayInfo> list = new ArrayList<RefundPayInfo>();
		list = refundPayInfoDao.findBy("contactNo", contactNo.trim());
		if(list != null && list.size() > 0){
			return list; 
		}
		return null;
	}

	/**
	 *保存退款流水
	 * @param refundPayInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public Boolean saveRefundPayInfo(RefundPayInfo refundPayInfo)
			throws Exception {
		if(refundPayInfo != null){
			refundPayInfoDao.save(refundPayInfo);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true ,propagation = Propagation.SUPPORTS)
	public List<RefundPayInfo> getByHouseNo(String houseNo) throws Exception {
		// TODO Auto-generated method stub
		List<RefundPayInfo> list = new ArrayList<RefundPayInfo>();
		List<ContactInfo> contactList = contactInfoDao.findBy("houseNo", houseNo.trim());
		for (ContactInfo trans : contactList) {
		     List<RefundPayInfo>  payList = refundPayInfoDao.findBy("contactNo", trans.getContactNo().trim());
		     for (RefundPayInfo refundPayInfo : payList) {
		    	 list.add(refundPayInfo);
			}
		}
		return list;
	}
}
