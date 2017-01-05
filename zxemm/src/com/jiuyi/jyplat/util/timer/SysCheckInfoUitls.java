package com.jiuyi.jyplat.util.timer;

import org.apache.log4j.Logger;

import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.bankAccoutInfo.ICheckErrorInfoService;

public class SysCheckInfoUitls {

	private Logger log = Logger.getLogger(SysCheckInfoUitls.class);

	public void run() throws InterruptedException {
		Thread snycheckInfo = new SnyCheckInfo();
		try {
			// 刚开始上线，所有数据都是手动操作
			snycheckInfo.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class SnyCheckInfo extends Thread {
		public synchronized void run() {
			log.info("======================开始执行错误信息检查任务===============");
			ICheckErrorInfoService buildingTempService = (ICheckErrorInfoService) SpringContextUtil.getBean("checkErrorInfoService");
			try {
				buildingTempService.saveCheckInfo();
			} catch (Exception e) {
				 log.error("======================开始执行错误信息检查任务出错"+e.getMessage());
			}
		}
	}
}
