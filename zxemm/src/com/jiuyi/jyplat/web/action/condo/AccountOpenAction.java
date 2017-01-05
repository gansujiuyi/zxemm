package com.jiuyi.jyplat.web.action.condo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.jiuyi.jyplat.entity.condo.BlockInfo;
import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.entity.condo.BuildingTemp;
import com.jiuyi.jyplat.entity.condo.DevelopInfo;
import com.jiuyi.jyplat.entity.condo.NoticeInstructionsLog;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.service.condo.IBlockInfoService;
import com.jiuyi.jyplat.service.condo.IBuildingInfoService;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.service.condo.INoticeInstructionsLogService;
import com.jiuyi.jyplat.service.condo.impl.IBuildingTempService;
import com.jiuyi.jyplat.service.developer.IDevelopInfoService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.action.base.BaseActionSupport;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.net.message.condo.GetInstractionInfoResultRspMsg;
import com.jiuyi.net.message.condo.SetHandleInfoResultRspMsg;

@Namespace("/accountOpen")
public class AccountOpenAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(AccountOpenAction.class);
	
	@Resource
	private IBuildingInfoService  buildingInfoService;
	
	@Resource
	private IDevelopInfoService developInfoService;
	
	@Resource
	private IBuildingTempService buildingTempService;
	
	@Resource
	private INoticeInstructionsLogService noticeInstructionsLogService;
	
	@Resource
	private INewHouseExchangService newHouseExchangService;
	
	@Resource
	private IBlockInfoService  blockInfoService;
	
	private PageFinder<BuildingInfo> pageFinder;//分页实体
	
	private String buildingId;//地幢号
	private String buildingValue;
	
	private String message;//返回信息
	private String status;//返回状态
	
	private String blockNo;//小区号
	
	private Query query; // 分页查询对象
	
	private BuildingInfo buildingInfo;//地幢信息
	
	private String buildingValueId;//地幢号
	
	private NoticeInstructionsLog noticeInstructionsLog;//新房指令通知日志表
	private BlockInfo blockInfo;
	
	private String amt,modifyAccount,contactNo,logDate;//撤销监管通知相关字段
	
	private String projectReguNo;//项目监管编号
	private String projetReguName;//项目监管名称
	
	private String operNo;//操作员编号
	
	private DevelopInfo developInfo;//开发商信息
	
	private String str;
	private BuildingTemp buildingTemp;
	
	/**
	 * 开户申请首页
	 * @return
	 */
	@Action(value = "/AccountOpenApplly", results = { 
			@Result(name = SUCCESS, location = "condo/accountOpenApplly.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String AccountOpenApplly(){
	return SUCCESS;
		
	}
	/**
	 * 查询开户信息
	 * @return
	 */
	@Action(value = "/AccountOpenInfo", results = { 
			@Result(name = SUCCESS, location = "condo/accountOpenApplly.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String AccountOpenInfo(){
		Operator operator = SessionUtil.getOperator();//获得柜员对象
		try {
				buildingValue=buildingValueId;
				pageFinder = buildingInfoService.queryBuildingInfoAll(buildingValue,query == null ? new Query() : query);
				if(pageFinder.getList()!=null &&pageFinder.getList().size()>0){
					List<BuildingInfo> buildingInfos=pageFinder.getList();
					List<BuildingInfo> buildings=new ArrayList<BuildingInfo>();
					for(int i=0;i<buildingInfos.size();i++){
						if(buildingInfos.get(i).getStatus().equals(Constant.account_status_wait)&&buildingInfos.get(i).getOperNo().equals(operator.getOperNo())){
							buildings.add(buildingInfos.get(i));
						}else if(buildingInfos.get(i).getStatus().equals(Constant.account_status_noopen)){
							buildings.add(buildingInfos.get(i));
						}else if(buildingInfos.get(i).getStatus().equals(Constant.account_status_open)&&buildingInfos.get(i).getOperNo().equals(operator.getOperNo())){
							buildings.add(buildingInfos.get(i));
						}
					}
					pageFinder.setData(buildings);
				}else{
					//getInstractionInfoResult 如果本地数据库没有数据时，主动查询房产局接口获取数据保证数据的完整性
					GetInstractionInfoResultRspMsg rspMsg = newHouseExchangService.getInstractionInfo(Constant.BANKCODE , buildingValue, "1" , "");
					if(null==rspMsg || !"0000".equals(rspMsg.getCode())){
						log.info("调用房管局查询指令接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
					}else{
						GetEntInfoRspMsg getEntInfoRspMsg = newHouseExchangService.getEntInfo(buildingValue);
						BuildingInfo temBuildingInfo = new BuildingInfo();
						if(null!=getEntInfoRspMsg  && "0000".equals(getEntInfoRspMsg.getCode())){
							temBuildingInfo.setBlockNo(getEntInfoRspMsg.getPbicode());
							temBuildingInfo.setProjetName(getEntInfoRspMsg.getProjectname());
						}else{
							log.info("调用房管局查询企业信息接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
						}
						temBuildingInfo.setBuildingId(rspMsg.getBuiding());
						temBuildingInfo.setOperNo(operator.getOperNo());
						temBuildingInfo.setStatus(Constant.account_status_wait);//账户状态1003等待企业信息（待审核）
						temBuildingInfo.setRegulateStatus(Constant.noregulate_status);//监管状态0001：未监管
						buildingInfoService.saveBuildingIfo(temBuildingInfo);
						pageFinder = buildingInfoService.queryBuildingInfoAll(buildingValue,query == null ? new Query() : query);
					}
				}
		} catch (Exception e) {
			message = "查询地幢信息出错："+e.toString();
			log.error("查询地幢信息出错--AccountOpenApplly--分页异常"+e,e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"查询地幢信息出错失败！");
			return INPUT;
		}
		return SUCCESS;
		
	}
	
	/**
	 * 开户申请信息填写页
	 * @return
	 */
	@Action(value = "/AccountOpenAppllyInfo", results = { 
			@Result(name = SUCCESS, location = "condo/accountOpenAppllyInfo.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String AccountOpenAppllyInfo(){
		try {
			/***
			 * GetEntInfo 1、查询企业信息接口!=null && t_blockinfo！=null? 非第一次开户：
             *2、查询企业信息接口!=null && t_blockinfo==null?  需要我们这边做第一次开户(实户、虚户(t_buildingTemp))
	         *3、查询企业信息接口==null  && t_blockinfo==null? 纯第一次开户，临时户管理(t_buildingTemp)
	         * 2与3的区别：
	         *<3>开户开一半（t_buildingTemp），需要企业信息，生成一个虚户，通知房产局开户完成(setHandleInfoResult，通知开户完成，定时任务跑批：GetEntInfo，补全企业信息)，将t_buildingTemp中的数据添加到t_blockinfo(楼盘<实户>、t_buildingInfo<虚户>)
             *<2>有企业信息，开户时新建t_blockinfo，保存实户信息、更新t_buildingInfo保存虚户信息、不跑定时任务
	         *开户完成的标识：有实户、虚户<t_blockinfo、t_buildingInfo关联使用blockNo,一对多>
			 */
			//调用房产局查询企业信息接口
			GetEntInfoRspMsg rspMsg = newHouseExchangService.getEntInfo(buildingId);
			
			if(null!=rspMsg && "0000".equals(rspMsg.getCode()) && null!=rspMsg.getPbicode() ){
				//获取到的企业信息不为空、项目编号不为空，本地开户非第一次开户
				blockNo = rspMsg.getPbicode();
				blockInfo=blockInfoService.queryBlockInfoByBuilidId(blockNo);
				
				if(null!=blockInfo){
					if(blockInfo.getFundNo()!=null){
						status="1111";
					}
				}else{
					BlockInfo	tmp = new BlockInfo();
					tmp.setBlockNo(blockNo);
					tmp.setProjectName(rspMsg.getProjectname());
					blockInfo = blockInfoService.saveBlockInfo(tmp);
				}
				buildingInfo=buildingInfoService.queryBuildingInfoByBuilidId(buildingId);
				buildingInfo.setCompanyName(rspMsg.getCompanyname());
				buildingInfo.setProjetName(rspMsg.getProjectname());
				buildingInfo.setCoveredArea(rspMsg.getConstructarea());
				buildingInfo.setProjectAdd(rspMsg.getProjectaddress());
				buildingInfo = buildingInfoService.saveBuildingIfo(buildingInfo);
//				
			}else if(null!=rspMsg && "0000".equals(rspMsg.getCode()) && null!=rspMsg.getPbicode()){
				//获取到的企业信息不为空、项目编号为空，房产局存在信息，本系统需要开户(实户、虚户)
				//修改或者新增楼幢信息
				buildingInfoService.saveBuildingAndBlockInfo(rspMsg);
				//根据地幢号查询楼幢信息
				buildingInfo=buildingInfoService.queryBuildingInfoByBuilidId(buildingId);
				//序列产生虚拟账号
				String regulateAccount=buildingInfoService.genBuildingInfoId();
				//更新楼幢信息
				buildingInfo.setRegulateAccount(regulateAccount);
				buildingInfoService.updateBuildingInfo(buildingInfo);
				//保存实户信息
				blockInfo.setProjectName(rspMsg.getProjectname());
				blockInfoService.saveBlockInfo(blockInfo);
			}else if(null!=rspMsg && "0000".equals(rspMsg.getCode()) && null==rspMsg.getPbicode()){
				//获取到的企业信息为空、项目编号也为空，房产局、本系统都属于第一次开户，需要做临时户管理
				//根据地幢号查询楼幢信息
				buildingInfo=buildingInfoService.queryBuildingInfoByBuilidId(buildingId);
				BuildingTemp temp = new BuildingTemp();
				temp.setBuildingId(buildingId);
				temp.setProjectReguNo(projectReguNo);
				temp.setProjetReguName(projetReguName);
				temp.setStatus("0");
				buildingTemp = buildingTempService.saveBuildingTemp(temp);
			}else{
				log.info("调用房管局接口出现错误："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				throw new Exception("房产局接口调用失败");
			}
			
		} catch (Exception e) {
			message = "根据地幢号查询地幢信息出错："+e.toString();
			log.error("地幢号查询地幢信息出错--AccountOpenAppllyInfo--"+e,e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"地幢号查询地幢信息出错失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	/**
	 * 保存开户申请信息
	 */
	@Action(value = "/accountOpenAppllysave", results = { 
			@Result(name = SUCCESS, location = "condo/accountOpenAppllysuccess.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String accountOpenAppllysave(){
		Operator operator = SessionUtil.getOperator();//获得柜员对象
		operNo=operator.getOperNo();
		try {
			//查询企业信息(根据地幢号)
			GetEntInfoRspMsg rspMsg1 = newHouseExchangService.getEntInfo(buildingInfo.getBuildingId());
			if(null==rspMsg1.getPbicode()  ||  "".equals(rspMsg1.getPbicode())){
				//保存虚拟账户信息
				BuildingTemp buildingTemp=new BuildingTemp();
				buildingTemp.setBuildingId(buildingInfo.getBuildingId());
				buildingTemp.setProjectReguNo(projectReguNo.trim());
				buildingTemp.setProjetReguName(projetReguName.trim());
				buildingTemp.setStatus("0");
				//保存虚拟账户信息
				buildingTempService.saveBuildingTemp(buildingTemp);
				developInfo=new DevelopInfo();
				str=developInfoService.genDevelopName();
				developInfo.setDevelopName(str);
				developInfo.setDevelopPassWord("123456");
				developInfo.setBuildingId(buildingInfo.getBuildingId());
				//保存开发商信息
				developInfoService.saveDevelopInfo(developInfo);
				
			}
			     
			     //修改监管状态
			    buildingInfo.setOperNo(operator.getOperNo());
				buildingInfo.setStatus(Constant.account_status_wait);
				buildingInfo.setRegulateStatus(Constant.regulate_status);
				buildingInfo.setBlockNo(rspMsg1.getPbicode());
				//更新楼幢信息
				buildingInfoService.updateBuildingInfo(buildingInfo);
				//通知房管局开户成功
				SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("1", buildingInfo.getBuildingId(), "" , "1", "开户成功！" , buildingInfo.getRegulateAccount(), "", Constant.BANKCODE);
				if("0000".equals(rspMsg.getCode())){
					log.info("--------------------------通知房管局开户成功!");
				}else{
					log.info("--------------------------通知房管局开户失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				}
		} catch (Exception e) {
			message = "更新保存地幢小区信息出错："+e.toString();
			log.error("更新保存地幢小区信息出错--accountOpenAppllysave--"+e,e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"更新保存地幢小区信息出错失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到撤销监管信息页面
	 */
	@Action(value = "/AccountOpenBackout", results = { 
			@Result(name = SUCCESS, location = "condo/accountOpenBackout.jsp"),
			@Result(name = INPUT, location = "base/error.jsp") })
	public String AccountOpenBackout(){
		List<NoticeInstructionsLog> noticeInstructionsLogs=new ArrayList<NoticeInstructionsLog>();
		
		try {
			buildingInfo=buildingInfoService.queryBuildingInfoByBuilidId(buildingId);
			if(buildingInfo!=null&&buildingInfo.getBuildingId()!=null){
				
				noticeInstructionsLogs=noticeInstructionsLogService.queNoticeLogByHouseNo(buildingInfo.getBuildingId(),"3");
				if(noticeInstructionsLogs!=null&&noticeInstructionsLogs.size()>0){					
					noticeInstructionsLog=noticeInstructionsLogs.get(0);
				}else{
					noticeInstructionsLog=null;
					message="房产局暂时没有通知消息";
					log.info("房产局暂时没有通知消息:noticeInstructionsLogs="+noticeInstructionsLogs);
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "查询撤销监管信息出错："+e.toString();
			log.error("查询撤销监管信息出错--AccountOpenBackout--"+e,e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"查询撤销监管信息出错失败！");
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * 主动查询撤销监管信息
	 * @return
	 */
	@Action(value = "/checkQuery", results = { 
			@Result(name = SUCCESS, type = "json", params = {"includeProperties", "status,message,amt,modifyAccount,contactNo,buildingId,logDate" })
		})
	public String checkQuery(){
		try {
			if(buildingId!=null){
				//查询指令方法
				GetInstractionInfoResultRspMsg rspMsg =  newHouseExchangService.getInstractionInfo(Constant.BANKCODE , buildingId, "3" , "");
				if("0000".equals(rspMsg.getCode())){
					amt = rspMsg.getAmt();
					modifyAccount = rspMsg.getModifyAccount();
					contactNo = rspMsg.getContactNo();
					buildingId = rspMsg.getBuiding();
					logDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					//存入通知日志表
					NoticeInstructionsLog log = new NoticeInstructionsLog();
					log.setAmt(amt);
					log.setContactNo(contactNo);
					log.setHouseNo(buildingId);
					log.setInstructionsVariety(rspMsg.getInstruction());
					log.setLogDate(logDate);
					log.setModifyAccount(modifyAccount);
					noticeInstructionsLogService.saveNoticeInstructionsLog(log);
					status="0000";
					message="同步查询撤销监管信息成功";
					return SUCCESS;
				}else{
					status="0002";
					message="同步查询撤销监管信息出错了";
					log.info("查询房管局撤销监管信息失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
					return SUCCESS;
				}
			}else{
				status="0001";
				message="地幢号不能为空";
				return SUCCESS;
			}
		} catch (Exception e) {
			status="0003";
			message="同步查询撤销监管信息出错了";
			log.error("同步撤销监管信息出错了："+e.getMessage(),e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"同步撤销监管信息出错了！");
			return SUCCESS;
		}
	}
	
	/**
	 * 撤销监管信息修改状态
	 * @return
	 */
	@Action(value = "/updateAccountOpenBackout", results = { 
			@Result(name = SUCCESS, type = "json", params = {"includeProperties", "status,message" })
		})
	public String updateAccountOpenBackout(){
		Operator operator = SessionUtil.getOperator();//获得柜员对象
		try {
			if(buildingId!=null){
				buildingInfo=buildingInfoService.queryBuildingInfoByBuilidId(buildingId);
				buildingInfo.setOperNo(operator.getOperNo());
				buildingInfo.setRegulateStatus(Constant.retulate_status_backout);
				buildingInfoService.updateBuildingInfo(buildingInfo);
				status="0000";
				//通知房管局撤销成功
				SetHandleInfoResultRspMsg rspMsg =  newHouseExchangService.setHandleInfo("3", buildingInfo.getBuildingId(), "" , "1", "解除资金监管成功！" , "", "" , Constant.BANKCODE);
				if("0000".equals(rspMsg.getCode())){
					log.info("通知房管局解除资金监管成功!");
				}else{
					log.info("通知房管局解除资金监管失败："+rspMsg.getCode()+"\t"+rspMsg.getMessage());
				}
			}else{
				status="0001";
				message="地幢号不能为空";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("撤销监管信息修改状态出错--updateAccountOpenBackout--"+e,e);
			this.addActionMessage(Utils.notEmptyString(e.getLocalizedMessage()) ? e.getLocalizedMessage():"撤销监管信息修改状态出错失败！");
			return SUCCESS;
		}
		return SUCCESS;
		
	}
	


	public IBuildingInfoService getBuildingInfoService() {
		return buildingInfoService;
	}

	public void setBuildingInfoService(IBuildingInfoService buildingInfoService) {
		this.buildingInfoService = buildingInfoService;
	}

	public PageFinder<BuildingInfo> getPageFinder() {
		return pageFinder;
	}

	public void setPageFinder(PageFinder<BuildingInfo> pageFinder) {
		this.pageFinder = pageFinder;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getBuildingValue() {
		return buildingValue;
	}

	public void setBuildingValue(String buildingValue) {
		this.buildingValue = buildingValue;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public BuildingInfo getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}



	public NoticeInstructionsLog getNoticeInstructionsLog() {
		return noticeInstructionsLog;
	}

	public void setNoticeInstructionsLog(NoticeInstructionsLog noticeInstructionsLog) {
		this.noticeInstructionsLog = noticeInstructionsLog;
	}

	public BlockInfo getBlockInfo() {
		return blockInfo;
	}

	public void setBlockInfo(BlockInfo blockInfo) {
		this.blockInfo = blockInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getBuildingValueId() {
		return buildingValueId;
	}
	public void setBuildingValueId(String buildingValueId) {
		this.buildingValueId = buildingValueId;
	}
	public String getProjectReguNo() {
		return projectReguNo;
	}
	public void setProjectReguNo(String projectReguNo) {
		this.projectReguNo = projectReguNo;
	}
	public String getProjetReguName() {
		return projetReguName;
	}
	public void setProjetReguName(String projetReguName) {
		this.projetReguName = projetReguName;
	}
	public String getOperNo() {
		return operNo;
	}
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}
	public DevelopInfo getDevelopInfo() {
		return developInfo;
	}
	public void setDevelopInfo(DevelopInfo developInfo) {
		this.developInfo = developInfo;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public BuildingTemp getBuildingTemp() {
		return buildingTemp;
	}
	public void setBuildingTemp(BuildingTemp buildingTemp) {
		this.buildingTemp = buildingTemp;
	}
	
	
	
}
