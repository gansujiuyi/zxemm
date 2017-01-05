package com.jiuyi.jyplat.util.timer;

import java.util.List;

import org.apache.log4j.Logger;

import com.jiuyi.net.message.condo.GetEntInfoRspMsg;
import com.jiuyi.jyplat.entity.condo.BuildingTemp;
import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.condo.INewHouseExchangService;
import com.jiuyi.jyplat.service.condo.impl.IBuildingTempService;

/**
 * 定时任务
 * 买卖二手房交易成功，将钱划转给卖方。
 * @author zx
 *
 */
public class SnyCompanyInfoUtils {
	private Logger log = Logger.getLogger(SnyCompanyInfoUtils.class);

	public void run() throws InterruptedException {
		Thread snyCompanyInfo = new SnyCompanyInfo();
		try {
			//刚开始上线，所有数据都是手动操作
			snyCompanyInfo.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class SnyCompanyInfo extends Thread {
		public synchronized void run() {
			log.info("-----------------------------------------开始执行自动同步新房开发商企业信息定时任务-----------------------------------------");
			INewHouseExchangService newHouseExchangService = (INewHouseExchangService) SpringContextUtil.getBean("newHouseExchangService");
			IBuildingTempService buildingTempService = (IBuildingTempService) SpringContextUtil.getBean("buildingTempService");
			try {
				List<BuildingTemp> bts = buildingTempService.getBuildingTemp();
				if(bts != null && bts.size() != 0){
					String houseNo = "";
					for (BuildingTemp buildingTemp : bts) {
						houseNo = buildingTemp.getBuildingId();
						GetEntInfoRspMsg rspMsg = newHouseExchangService.getEntInfo(houseNo);
						if(rspMsg != null && "0000".equals(rspMsg.getCode())){
							if(rspMsg.getPbicode() != null && !"".equals(rspMsg.getPbicode().trim())){
								log.info("-------------------------地幢号为"+ houseNo +"的楼盘获取企业信息成功，企业名称为：" + rspMsg.getCompanyname());
								buildingTempService.saveEntInfo(rspMsg, buildingTemp, houseNo);
							}else{
								log.info("-------------------------地幢号为"+ houseNo +"的楼盘获取企业信息失败，等待房管局项目编号!");
							}
							
						}else{
							log.info("-------------------------地幢号为"+ houseNo +"的楼盘获取企业信息失败");
						}
						log.info("-------------------------------------------------------------同步新房开放商企业信息");
					}
				}
				
			} catch (Exception e) {
				log.info("-------------------------------------------------------------同步新房开放商企业信息出现异常");
				e.printStackTrace();
			}
		}

	}
}
