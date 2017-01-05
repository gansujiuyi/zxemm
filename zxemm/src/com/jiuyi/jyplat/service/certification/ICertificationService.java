package com.jiuyi.jyplat.service.certification;

import java.util.List;

import com.jiuyi.jyplat.entity.certification.Certification;
import com.jiuyi.net.message.certification.BindingEAccountRsp;
import com.jiuyi.net.message.certification.OpenEAccountRsp;

/**
 * 
 * <P>实名认证，绑定电子账号</P>
 * @author 孙正斌
 */
public interface ICertificationService {
	
	/**
	 * 
	 * <p>开电子户（登记电子户）</p>
	 * @param deptSeq 机构号(用户名)
	 * @param userName 用户名
	 * @param idNo 身份证号码
	 * @param mobilePhone 手机号码
	 * @return
	 * @author 孙正斌
	 */
	public OpenEAccountRsp DredgeECount(String deptSeq, String userName, String idNo, String mobilePhone);
	
	/**
	 * <p>绑定，开通电子账户</p>
	 * @param idNo 身份证号
	 * @param acNo 绑定的银行卡号
	 * @param acName 绑定的银行卡的用户姓名
	 * @param acNoFlag 银行卡标识 0:本行卡 1:他行卡
	 * @param fundCardFlag 若是本行卡，是否作为购买“宝”的卡 0-否    1-是,在本系统中默认为0。
	 * @param trsPassword 电子账户密码
	 * @return
	 * @author 孙正斌
	 */
	public BindingEAccountRsp bindingECount(String idNo, String acNo, String acName, String acNoFlag, String fundCardFlag,
			String trsPassword);
	
	/**
	 * 
	 * <p>实名认证结果查询</p>
	 * @param acNo 账号
	 * @param bindAcNo 绑定的银行卡号
	 * @param bindAcName 绑定银行卡的用户姓名
	 * @return
	 * @author 孙正斌
	 */
	public boolean AutonymCer(String acNo, String bindAcNo, String bindAcName);
	
	
	/**
	 * 
	 * <p>根据memberNo</p>
	 * @param memberNo
	 * @return
	 * @author sunzb
	 */
	public Certification validateMemberCertificated(Integer memberNo);
	/**
	 * 
	 * <p>根据name</p>
	 * @param name 买方姓名
	 * @param phone 买方手机号
	 * @return
	 * @author guys
	 */
	public Certification validateNameCertificated(String name ,String phone)throws Exception;
	
	/**
	 * 
	 * <p>保存用户实名认证成功的用户信息 </p>
	 * @param certification
	 * @author sunzb
	 */
	public void saveCertificatedMember(Certification certification) ;
	
	/**
	 * 
	 * <p>验证实名认证的银行卡号是否已经进行过实名认证</p>
	 * @param certification
	 * @author sunzb
	 */
	public boolean validateCardNoExsit(Certification certification) throws Exception;

	

	
	
	public List <Certification> valCertification(Integer buymemberNo,Integer sellmember) throws Exception;

	
	/****
	 * 根据会员ID查询当前拥有的所有实名卡号
	 * @auth baizilin
	 */
	public List<Certification> queryCertifListByMemberNo(Integer memberNo);
	
	/**
	 * 查询所有实名认证信息
	 */
	public List<Certification> qurAllCertInfo() throws Exception;
	
	
	/**
	 * <p><b>根据卡号查询会员信息</b>TODO
	 * <p>2015-11-19 下午5:04:37
	 * @author yhm
	 * @param CardNo
	 * @return
	 * @throws Exception
	 */
	public Certification queryCertificationByCardNo(String CardNo) throws Exception;

}
