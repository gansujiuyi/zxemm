package com.jiuyi.jyplat.service.condo;
import java.util.List;
import java.util.Map;

import com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg;
import com.jiuyi.jyplat.entity.bankAccoutInfo.ContractTranstion;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 购买合同信息    Service层 接口
 * @author wsf
 *
 */
public interface IContactInfoService {

	/**
	 * 分页查询合同信息
	 * @param contactInfo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageFinder<ContactInfo> getPageContactInfo(ContactInfo contactInfo , Query query) throws Exception;
	
	/**
	 * 根据合同号查询合同信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public ContactInfo getBuyHouseContactInfo(String contactNo ,String bankCode)throws Exception;
	
	/**
	 * 更新合同信息
	 * @param contactInfo
	 * @throws Exception
	 */
	public void updateContactInfo(ContactInfo contactInfo)throws Exception;
	
	/**
	 * 新增合同信息及购买人信息
	 * @param rsp
	 * @throws Exception
	 */
	public void saveContactInfoAndCondoBuyer(GetBarginInfoResultRspMsg rsp)throws Exception;
	
	/**
	 * 根据合同编号查询合同相关信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getByContactNo(String contactNo)throws Exception;
	
	/**
	 * 根据地幢号查询合同集合
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<ContactInfo> getByHouseNo(String houseNo , String contractNo)throws Exception;
	
	
	/****
	 * 根据合同号查询合同
	 */
	public List<ContactInfo> getByContractNo(String houseNo ,String ContractNo ,String createDate)throws Exception;
	
	

	/***
	 * 多个合同流水绑定
	 */
	public boolean saveContractBilding(ContactInfo contactInfo ,ContractTranstion contractTranstion,CondoPayInfo condoPayInfo);
	
	/**
	 * 根据合同编号查询出同一批次支付的合同号
	 */
	public String queryContractByExt1(String contractNo);
	
	
	/***
	 * 根据合同号查询出已付金额、合同总金额
	 */
	public List queryContractMoneyByContractNo(String[] contr_);
}
