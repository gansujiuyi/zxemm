package com.jiuyi.jyplat.service.condo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jiuyi.jyplat.dao.condo.CondoBuyerDao;
import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.service.condo.ICondoBuyerService;

/**
 *  购房人信息  Service 层 实现类
 * @author wsf
 *
 */
@Service("condoBuyerService")
public class CondoBuyerService implements ICondoBuyerService {

	Logger log = Logger.getLogger(CondoBuyerService.class);
	
	@Resource
	private CondoBuyerDao condoBuyerDao;
	@Resource
	private ContactInfoDao contactInfoDao;

	/**
	 * 根据地幢号查询购买人信息
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CondoBuyer> getByHouseNo(String houseNo) throws Exception {
		List<CondoBuyer> buyerList = new ArrayList<CondoBuyer>();
		List<ContactInfo> contactList = contactInfoDao.findBy("houseNo", houseNo);
		for (ContactInfo contactInfo : contactList) {
			List<CondoBuyer> list = condoBuyerDao.findBy("contactId", contactInfo.getContactNo());
			for (CondoBuyer condoBuyer : list) {
				buyerList.add(condoBuyer);
			}
		}
		return buyerList;
	}
}
