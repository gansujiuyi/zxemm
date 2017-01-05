package com.jiuyi.jyplat.service.certification.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.certification.CertificationDao;
import com.jiuyi.jyplat.entity.certification.Certification;
import com.jiuyi.jyplat.service.certification.ICertificationService;
import com.jiuyi.jyplat.util.ExcelUtil;
import com.jiuyi.net.client.HttpClientMessageSender;
import com.jiuyi.net.message.certification.BindingEAccountRsp;
import com.jiuyi.net.message.certification.OpenEAccountRsp;
import com.uxun.commauth.StringUtil;

@Service("certificationServiceImpl")
public class CertificationServiceImpl implements ICertificationService {
	@Resource
	private CertificationDao certificationDao;
	
	@Override
	public boolean AutonymCer(String acNo, String bindAcNo, String bindAcName) {
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
		String returnXml = messageSender.returnAutonymXml(acNo, bindAcNo, bindAcName);
		String rspXml = messageSender.sendMessage(returnXml);
		if (rspXml.indexOf("rspXml") <= 0) {
			System.out.println("实名认证结果调用失败");
			return false;
		}
		String resultFlag = rspXml
				.substring(rspXml.indexOf("<CheckResult>") + 13, rspXml.lastIndexOf("</CheckResult>"));
		if ("true".equalsIgnoreCase(resultFlag)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public OpenEAccountRsp DredgeECount(String deptSeq, String userName, String idNo, String mobilePhone) {
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
		String returnXml = messageSender.returnDredgeEcountXml(deptSeq, userName, idNo, mobilePhone);
		String rspXml = messageSender.sendMessage(returnXml);
		if (rspXml.indexOf("UUID") <= 0) {
			System.out.println("登记电子户失败");
			return null;
		}
		String uuid = rspXml.substring(rspXml.indexOf("<UUID>") + 6, rspXml.lastIndexOf("</UUID>"));
		if (uuid != null && StringUtil.isNotEmpty(uuid)) {
			OpenEAccountRsp openECounntRsp = new OpenEAccountRsp();
			openECounntRsp.setUUID(uuid);
			return openECounntRsp;
		} else {
			return null;
		}
	}

	@Override
	public BindingEAccountRsp bindingECount(String idNo, String acNo, String acName, String acNoFlag,
			String fundCardFlag, String trsPassword) {
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
		String returnXml = messageSender.returnBindingXml(idNo, acNo, acName, acNoFlag, fundCardFlag, trsPassword);
		String rspXml = messageSender.sendMessage(returnXml);
		if (rspXml.indexOf("WebAcNo") <= 0) {
			System.out.println("绑定电子账户失败");
			return null;
		}
		String webAcNo = rspXml.substring(rspXml.indexOf("<WebAcNo>") + 9, rspXml.lastIndexOf("</WebAcNo>"));
		if (webAcNo != null && StringUtil.isNotEmpty(webAcNo)) {
			BindingEAccountRsp bindingEAccountRsp = new BindingEAccountRsp();
			bindingEAccountRsp.setWebAcNo(webAcNo);
			return bindingEAccountRsp;
		} else {
			return null;
		}
	}

	
	/**
	 * 
	 * <p>在快速注册时，生成用户密码</p>
	 * @return
	 * @author 孙正斌
	 */
	private String createPwd(){
		char[] arr = {'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t',
				'u','v','w','x','y','0','1','2','3','4','5','6','7','8','9'};
		Random r = new Random();
		StringBuffer bf = new StringBuffer();
		//生成8位密码
		for(int i = 0; i < 8; i++){
			int subscript =  r.nextInt(arr.length);
			bf.append(arr[subscript]);
		}
		if(bf == null){
			return null;
		}else{
			return bf.toString();
		}
	}
	

	@Override
	public Certification validateMemberCertificated(Integer memberNo) {
		List<Certification> certifications = certificationDao.queryCertificatedMember(memberNo);
		if(certifications == null || certifications.size() == 0){
			return null;
		}
		return certifications.get(0);
	}
	
	/**
	 * <p>根据name</p>
	 * @param name 买方姓名
	 * @param phone 买方手机号
	 * @return
	 * @author guys
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Certification validateNameCertificated(String name ,String phone) throws Exception{
		return certificationDao.validateNameCertificated(name, phone);
	}

	@Override
	public void saveCertificatedMember(Certification certification) {
		String id = ExcelUtil.getNextVal("T_CERTIFICATION_SEQUENCE");
		certification.setId(Long.parseLong(id));
		certificationDao.save(certification);
	}

	@Override
	public boolean validateCardNoExsit(Certification certification) throws Exception {
		String cardNo =certification.getAccountNo();
		Certification cert =  certificationDao.validateCardNoExist(cardNo);
		if(cert == null){
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 根据不同的member查询不同的名称
	 * @param buymemberNo
	 * @param sellmember
	 * @return
	 * 
	 * wh
	 */

	public List<Certification> valCertification(Integer buymemberNo,
			Integer sellmember) throws Exception {
		// TODO Auto-generated method stub
		return certificationDao.valCertification(buymemberNo,sellmember);
	}


	@Override
	public List<Certification> queryCertifListByMemberNo(Integer memberNo) {
		List<Certification> list=  null;
		list= certificationDao.findBy("memberNo", memberNo);
		return list;
	}
	
	@Override
	public Certification queryCertificationByCardNo(String CardNo) throws Exception {
		if (CardNo==null) {
			throw new Exception("查询用户信息时CardNo不能为空！");
		}
		Certification certification= certificationDao.validateCardNoExist(CardNo);
		if(certification == null){
			throw new Exception("没有查询到该用户！");
		}
		return certification;
	}

	@Override
	public List<Certification> qurAllCertInfo() throws Exception {
		return certificationDao.loadAll();
	}


}
