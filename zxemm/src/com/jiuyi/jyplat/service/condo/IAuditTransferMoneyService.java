package com.jiuyi.jyplat.service.condo;

import com.jiuyi.jyplat.entity.condo.AuditTransferMoney;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 审核划款实体类  Service层接口
 * @author wsf
 *
 */
public interface IAuditTransferMoneyService {

	/**
	 * 新增审批划款记录
	 * @param id
	 * @throws Exception
	 */
	public void saveAuditTransferMoney(String houseNo , String amt)throws Exception;
	
	/**
	 * 分页查询审批划款记录
	 * @param transferMoney
	 * @param query
	 * @param flag 
	 * @return
	 * @throws Exception
	 */
	public PageFinder<AuditTransferMoney> getPageTransferMoney(AuditTransferMoney transferMoney ,  Query query , String flag)throws Exception;
	
	/**
	 * 补录出账信息
	 * @param houseNo
	 * @param outCardNo
	 * @param outBankId
	 * @param outCardName
	 * @param outPhone
	 * @param outIdNo
	 * @throws Exception
	 */
	public void enteringOutCard(String houseNo , String outCardNo , String outBankId , String outCardName ,String outPhone ,String outIdNo)throws Exception;
	
	/**
	 * 修改审批划款记录状态
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	public void updateTransferMoneyState(String id , String state) throws Exception;
	
	/**
	 * 根据主键Id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AuditTransferMoney getById(String id)throws Exception;
}
