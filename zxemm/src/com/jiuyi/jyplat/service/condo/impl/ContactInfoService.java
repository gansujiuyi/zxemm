package com.jiuyi.jyplat.service.condo.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.bankAccountInfo.ContractTranstionDao;
import com.jiuyi.jyplat.dao.condo.CondoBuyerDao;
import com.jiuyi.jyplat.dao.condo.CondoPayInfoDao;
import com.jiuyi.jyplat.dao.condo.ContactInfoDao;
import com.jiuyi.jyplat.dao.condo.RefundPayInfoDao;
import com.jiuyi.jyplat.entity.bankAccoutInfo.ContractTranstion;
import com.jiuyi.jyplat.entity.condo.CondoBuyer;
import com.jiuyi.jyplat.entity.condo.CondoPayInfo;
import com.jiuyi.jyplat.entity.condo.ContactInfo;
import com.jiuyi.jyplat.entity.condo.RefundPayInfo;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.service.condo.IContactInfoService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.net.message.condo.GetBarginInfoResultRspMsg;
import com.jiuyi.net.message.condo.OwnerRecord;

/**
 * 购买合同信息    Service层 实现类
 * @author wsf
 *
 */
@Service("contactInfoService")
public class ContactInfoService implements IContactInfoService {

	Logger log = Logger.getLogger(ContactInfoService.class);
	
	@Resource
	private ContactInfoDao contactInfoDao;

	@Resource
	private CondoBuyerDao condoBuyerDao;
	
	@Resource
	private CondoPayInfoDao condoPayInfoDao;
	
	@Resource
	private RefundPayInfoDao refundPayInfoDao;
	@Resource
	private ContractTranstionDao contractTranstionDao;
	
	
	/**
	 * 分页查询合同信息
	 * @param contactInfo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public PageFinder<ContactInfo> getPageContactInfo(ContactInfo contactInfo,
			Query query) throws Exception {
		Criteria criteria = contactInfoDao.createCriteria();
		Operator operator = SessionUtil.getOperator();
		if(contactInfo != null){
			if(contactInfo.getContactNo() != null && !"".equals(contactInfo.getContactNo().trim())){
				criteria.add(Restrictions.eq("contactNo", contactInfo.getContactNo().trim()));
				criteria.add(Restrictions.or(Restrictions.eq("operNo", operator.getOperNo().trim()), Restrictions.isNull("operNo")));
			}
		}else{
			criteria.add(Restrictions.eq("operNo", operator.getOperNo().trim()));
		}
		return contactInfoDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("createDate"));
	}

	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public ContactInfo getBuyHouseContactInfo(String contactNo ,  String bankCode)
			throws Exception {
		// TODO Auto-generated method stub
		ContactInfo contactInfo=new ContactInfo();
		 if(contactNo!=null){
			 Criteria criteria = contactInfoDao.createCriteria();
			 criteria.add(Restrictions.eq("contactNo", contactNo));
			 criteria.add(Restrictions.eq("bankCode", bankCode));
			 contactInfo = (ContactInfo) criteria.uniqueResult();
		 }
		return contactInfo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void updateContactInfo(ContactInfo contactInfo) throws Exception {
		// TODO Auto-generated method stub
		if(contactInfo!=null){
			 Operator operator = SessionUtil.getOperator();
			 contactInfo.setOperNo(operator.getOperNo());
			 contactInfoDao.update(contactInfo);
			 log.info("合同信息更新成功");
		 }
		
	}

	/**
	 * 新增合同信息及购买人信息
	 * @param rsp
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void saveContactInfoAndCondoBuyer(GetBarginInfoResultRspMsg rsp)
			throws Exception {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setContactNo(rsp.getBsinum());
		contactInfo.setContactAmt(rsp.getPsbtolpri());
		contactInfo.setSuperviseAmt("0.0");
		contactInfo.setPayStatus("0000");
//		contactInfo.setStatus("0000");//0000  初始状态
		contactInfo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		contactInfo.setHouseNo(rsp.getBuildingCode());
		contactInfo.setHouseArea(rsp.getArea());
		//contactInfo.setHouseAddr(rsp.getHouseaddress());
		contactInfo.setRoomNo(rsp.getCellnum());
		contactInfo.setProjectName(rsp.getRcname());
		contactInfo.setProjectNo(rsp.getPbicode());
		contactInfo.setCompanyName(rsp.getRcname());
		contactInfo.setPayCont(rsp.getPsbtolpri());
		contactInfo.setBankCode(Constant.BANKCODE);//银行号
		contactInfo.setCompanyNo(rsp.getCompcode());
		if(null!=rsp.getPsbdate() && !"".equals(rsp.getPsbdate())){
			contactInfo.setStatus(rsp.getPsbdate());//合同状态
		}else{
			contactInfo.setStatus("0000");//0000  初始状态
		}
		List<OwnerRecord> list = rsp.getOwnerRecords();
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				CondoBuyer buyer = new CondoBuyer();
				OwnerRecord ownerRecord = list.get(i);
				buyer.setBuyerName(ownerRecord.getName());
				buyer.setIdNo("");//响应信息里无该字段
				buyer.setPhoneNo(ownerRecord.getPhone());
				buyer.setCardNo(ownerRecord.getCardNo());
				buyer.setContactId(rsp.getBsinum());
				condoBuyerDao.save(buyer);
			}
		}
		ContactInfo temp = contactInfoDao.findUniqueBy("contactNo", contactInfo.getContactNo());
		if(null==temp){
			contactInfoDao.save(contactInfo);
		}else{
			temp.setStatus(rsp.getPsbdate());
			contactInfoDao.update(temp);
		}
		log.info("新增合同信息成功！");
	}

	/**
	 * 根据合同编号查询合同相关信息
	 * @param contactNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public Map<String, Object> getByContactNo(String contactNo)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ContactInfo contactInfo = contactInfoDao.findUniqueBy("contactNo", contactNo);
		List<CondoBuyer> buyerList = condoBuyerDao.findBy("contactId", contactNo);
		List<CondoPayInfo> payList = condoPayInfoDao.findBy("contactNo", contactNo);
		List<RefundPayInfo> refundList = refundPayInfoDao.findBy("contactNo", contactNo);
		map.put("contactInfo", contactInfo);
		map.put("buyerList", buyerList);
		map.put("payList", payList);
		map.put("refundList", refundList);
		return map;
	}

	/**
	 * 根据地幢号查询合同集合
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<ContactInfo> getByHouseNo(String houseNo , String contractNo) throws Exception {
		 Criteria criteria = contactInfoDao.createCriteria();
		 criteria.add(Restrictions.eq("houseNo", houseNo.trim()));
		 if(null!=contractNo && !"".equals(contractNo)){
			 criteria.add(Restrictions.eq("contactNo", contractNo.trim()));
		 }
		return criteria.list();
	}
	
	@Override
	public List<ContactInfo> getByContractNo(String houseNo,String ContractNo ,String createDate)
			throws Exception {
		 Criteria criteria = contactInfoDao.createCriteria();
		 if(null!=ContractNo && !"".equals(ContractNo)){
//			 criteria.add(Restrictions.or(Restrictions.eq("contactNo", ContractNo.trim()), Restrictions.like("createDate", createDate.trim()+"%")));
			 criteria.add(Restrictions.eq("status", "0000"));//未支付的合同
			 criteria.add(Restrictions.eq("houseNo", houseNo.trim()));//未支付的合同
			 criteria.add(Restrictions.eq("bankCode", Constant.BANKCODE));//当前银行的合同
			 if(null!=createDate &&  !"".equals(createDate)){
				 criteria.add(Restrictions.like("createDate", createDate.trim()+"%"));
			 }
			 return criteria.list();
		 }
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public boolean saveContractBilding(ContactInfo contactInfo,
			ContractTranstion contractTranstion, CondoPayInfo condoPayInfo) {
		try {
			DBAction db = new DBAction();
			String sql="update T_ACCOUNTFLOWS set status='1' where transactionSeqNum='"+condoPayInfo.getPayTradeNo()+"'";
			db.executeUpdate(sql);
			condoPayInfoDao.save(condoPayInfo);
			contactInfoDao.update(contactInfo);
			contractTranstionDao.save(contractTranstion);
			return true;
		} catch (Exception e) {
			 return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String queryContractByExt1(String contractNo) {
		StringBuffer sbf= new StringBuffer();
		sbf.append(" select d.contractno from t_contran d");
		sbf.append(" where d.ext1 in( ");
		sbf.append(" select x.ext1 from  ");
		sbf.append(" t_contran x where x.contractno = '"+contractNo+"')");
		org.hibernate.Query q = contactInfoDao.createSQLQuery(sbf.toString());
		if(q.list().size()>0){
			return  listToString(q.list());
		}
		return null;
	}
	
	public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	@Override
	public List queryContractMoneyByContractNo(String[] contr_) {
		String contractNo = "";
		 for (int i = 0; i < contr_.length; i++) {
			 contractNo+="'"+contr_[i]+"'"+",";
		}
		 contractNo = contractNo.substring(0, contractNo.lastIndexOf(","));
		 StringBuffer sbf = new StringBuffer();
		 sbf.append(" select nvl(sum( distinct a.contactamt),0) as htje ,  nvl(sum( distinct b.paymoney),0) as yfje");
		 sbf.append(" from t_contactinfo a left join t_condopayinfo b on a.contactno = b.contactno ");
		 sbf.append(" where a.contactno in ("+contractNo+") ");
		 org.hibernate.Query q = contactInfoDao.createSQLQuery(sbf.toString());
		return q.list();
	}
}
