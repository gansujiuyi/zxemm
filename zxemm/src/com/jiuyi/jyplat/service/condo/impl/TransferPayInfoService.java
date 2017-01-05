package com.jiuyi.jyplat.service.condo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.AuditTransferMoneyDao;
import com.jiuyi.jyplat.dao.condo.TransferPayInfoDao;
import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.entity.condo.TransferPayInfo;
import com.jiuyi.jyplat.service.condo.ITransferPayInfoService;

/**
 *  购房划款流水类  Service层实现类
 * @author wsf
 *
 */
@Service("transferPayInfoService")
public class TransferPayInfoService implements ITransferPayInfoService {
	
	Logger log = Logger.getLogger(TransferPayInfoService.class);
	
	@Resource
	private TransferPayInfoDao transferPayInfoDao;
	@Resource
	private AuditTransferMoneyDao transferMoneyDao;
	/**
	 * 根据订单编号查询订单流水集合
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<TransferPayInfo> getByTransNo(String auditTransferMoneyNo)
			throws Exception {
		return transferPayInfoDao.findBy("auditTransferMoneyNo", auditTransferMoneyNo);
	}
	
	/**
	 * 新增审批划款流水
	 * @param transferPayInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public boolean saveTransferPayInfo(TransferPayInfo transferPayInfo)
			throws Exception {
		if(transferPayInfo != null){
			transferPayInfoDao.save(transferPayInfo);
			return true;
		}
		return false;
	}

	/**
	 * 根据地幢号查询所有的出账流水记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true ,propagation = Propagation.SUPPORTS)
	public List<TransferPayInfo> getByHouseNo(String houseNo) throws Exception {
		/*List<TransferPayInfo> list = new ArrayList<TransferPayInfo>();
		String sql = "select t.*  from  t_auditTransferMoney a , t_transferPayInfo t where a.id = t.audittransfermoneyno and a.houseno = '"+houseNo.trim()+"'";
		Query query = transferPayInfoDao.createSQLQuery(sql);
		list = query.list();
		return list;*/
		List<TransferPayInfo> list = new ArrayList<TransferPayInfo>();
		List<AuditTransferMoney> transList = transferMoneyDao.findBy("houseNo", houseNo.trim());
		for (AuditTransferMoney trans : transList) {
		     List<TransferPayInfo>  payList = transferPayInfoDao.findBy("auditTransferMoneyNo", trans.getId().trim());
		     for (TransferPayInfo transferPayInfo : payList) {
		    	 list.add(transferPayInfo);
			}
		}
		return list;
	}
}
