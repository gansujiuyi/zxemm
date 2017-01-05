package com.jiuyi.jyplat.util.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.log4j.Logger;

import com.jiuyi.jyplat.entity.condo.BuildingInfo;
import com.jiuyi.jyplat.service.SpringContextUtil;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountBalService;
import com.jiuyi.jyplat.service.bankAccoutInfo.IAccountFlowsService;
import com.jiuyi.jyplat.service.condo.IBuildingInfoService;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.net.client.zx.webservice.client.BankData;
import com.jiuyi.net.client.zx.webservice.client.IBankService;
import com.jiuyi.net.client.zx.webservice.client.handler.AddHeaderInterceptor;

/**
 * 定时任务 银行流水信息表
 * 
 * @author baizilin
 * 
 */
public class SnyBankAccountInfoUtils {
	private Logger log = Logger.getLogger(SnyBankAccountInfoUtils.class);

	public void run() throws InterruptedException {
		Thread snyBankAccountInfo = new SnyBankAccountInfo();
		try {
			// 刚开始上线，所有数据都是手动操作
			snyBankAccountInfo.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Date getNextDay(Date date , int k) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, k);  
        date = calendar.getTime();  
        return date;  
    }  
	
	 private static void setupTLS(Object port) throws FileNotFoundException, IOException, GeneralSecurityException {
 	 	File file1 = new File(SnyBankAccountInfoUtils.class.getResource("/client.p12").getFile());//keyStore
         File file2 = new File(SnyBankAccountInfoUtils.class.getResource("/client.truststore").getFile());//trustStore
        
         
         HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();
  
         TLSClientParameters tlsCP = new TLSClientParameters();
         String keyPassword = "666666";
         KeyStore keyStore = KeyStore.getInstance("PKCS12");
         keyStore.load(new FileInputStream(file1), keyPassword.toCharArray());
         KeyManager[] myKeyManagers = getKeyManagers(keyStore, keyPassword);
         tlsCP.setKeyManagers(myKeyManagers);
  
         
         KeyStore trustStore = KeyStore.getInstance("JKS");
         trustStore.load(new FileInputStream(file2), keyPassword.toCharArray());
         TrustManager[] myTrustStoreKeyManagers = getTrustManagers(trustStore);
         tlsCP.setTrustManagers(myTrustStoreKeyManagers);
         
         //The following is not recommended and would not be done in a prodcution environment,
         //this is just for illustrative purpose
         tlsCP.setDisableCNCheck(true);
         httpConduit.setTlsClientParameters(tlsCP);

     }

     private static TrustManager[] getTrustManagers(KeyStore trustStore) 
         throws NoSuchAlgorithmException, KeyStoreException {
         String alg = KeyManagerFactory.getDefaultAlgorithm();
         TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
         fac.init(trustStore);
         return fac.getTrustManagers();
     }
     
     private static KeyManager[] getKeyManagers(KeyStore keyStore, String keyPassword) 
         throws GeneralSecurityException, IOException {
         String alg = KeyManagerFactory.getDefaultAlgorithm();
         char[] keyPass = keyPassword != null ? keyPassword.toCharArray() : null;
         KeyManagerFactory fac = KeyManagerFactory.getInstance(alg);
         fac.init(keyStore, keyPass);
         return fac.getKeyManagers();
     }

	private class SnyBankAccountInfo extends Thread {
		public synchronized void run() { 
			String acctNo = "";// 7461110182600100497
			String dataTime = "";// 2016-01-14
			String startTime = "";//
			String endTime = "";
			log.info("-----------------------------------------开始执行自动同步银行流水信息定时任务-----------------------------------------");
			IAccountFlowsService accountFlowsService = (IAccountFlowsService) SpringContextUtil.getBean("accountFlowsService");
			IAccountBalService bankDataService = (IAccountBalService) SpringContextUtil.getBean("accountBalService");
			IBuildingInfoService buildingInfoService =  (IBuildingInfoService) SpringContextUtil.getBean("buildingInfoService");
			List<BuildingInfo> buildingInfos = buildingInfoService.queryAllBuildInfo();
			if(null !=buildingInfos && buildingInfos.size()>0){
				for (int i = 0; i < buildingInfos.size(); i++) {
					BuildingInfo temp = buildingInfos.get(i);
					acctNo = temp.getRegulateAccount();//资金监管账号
					int k=0;
					for (int j = 0; j < 3; j++) {
						dataTime =DateUtils.dateToDateString(getNextDay(new Date(), k),"yyyy-MM-dd");
						startTime =DateUtils.dateToDateString(getNextDay(new Date(), k),"yyyy-MM-dd");
						endTime =DateUtils.dateToDateString(getNextDay(new Date(), k),"yyyy-MM-dd");
						k--;
						try {
							JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
					        factory.setServiceClass(IBankService.class);
					        String endPoint = "https://22.168.1.100:8443/AdvanceSaleHouseWebserviceDemo/services/bankServer";
					        factory.setAddress(endPoint);
					        factory.getInInterceptors().add(new LoggingInInterceptor());
					        factory.getOutInterceptors().add(new LoggingOutInterceptor());
					        String id = "admin";
							String pwd = "123456";
							byte[] idData = id.getBytes();
							byte[] pwdData = pwd.getBytes();
							byte[] publicKey = Base64.decodeBase64(com.jiuyi.net.client.zx.webservice.client.RsaUtils.publicKeyBase64);
							byte[] encodedId = com.jiuyi.net.client.zx.webservice.client.RsaUtils.encryptByPublicKey(idData, publicKey);
							byte[] encodedPwd = com.jiuyi.net.client.zx.webservice.client.RsaUtils.encryptByPublicKey(pwdData, publicKey);
							factory.getOutInterceptors().add(new AddHeaderInterceptor(Base64.encodeBase64String(encodedId), Base64.encodeBase64String(encodedPwd)));
							IBankService service = (IBankService) factory.create();
							setupTLS(service);
//							 dataTime = "2016-09-16";//2016-01-14
//							 startTime = "2016-01-01"; 
//							 endTime = "2016-12-31";	
							if(null ==acctNo){
								break;
							}
							BankData data = service.qurAccountInfo(acctNo, dataTime, startTime, endTime);
							if (data != null) {
								if ("0000".equals(data.getStatus())) {
									// 查询数据成功
									com.jiuyi.net.client.zx.webservice.client.AccountBal retAccountBal = data.getAccountBal();
									com.jiuyi.jyplat.entity.bankAccoutInfo.AccountBal saveAccountBal = new com.jiuyi.jyplat.entity.bankAccoutInfo.AccountBal();
									if(null!=retAccountBal && null !=retAccountBal.getCustId()){
										saveAccountBal.setCustId(retAccountBal.getCustId());
										saveAccountBal.setPrimaryAccount(retAccountBal.getPrimaryAccount());
										saveAccountBal.setSubjectNum(retAccountBal.getSubjectNum());
										saveAccountBal.setTransactionDate(retAccountBal.getTransactionDate());
										saveAccountBal.setAccountBalance(retAccountBal.getAccountBalance());
										saveAccountBal.setAccountName(retAccountBal.getAccountName());
										saveAccountBal.setAgreementCode(retAccountBal.getAgreementCode());
										//保存主账户信息
										bankDataService.saveAccountBal(saveAccountBal);
									}
									//保存明细信息
									List<com.jiuyi.net.client.zx.webservice.client.AccountFlows> aflist = data.getAccountFlows();
									if(null!=aflist && aflist.size()>0){
										List<com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows> flows = new ArrayList<com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows>();
										for (com.jiuyi.net.client.zx.webservice.client.AccountFlows aflFlows : aflist) {
											com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows tempFlows = new com.jiuyi.jyplat.entity.bankAccoutInfo.AccountFlows();
											tempFlows.setAccountBalance(aflFlows.getAccountBalance());
											tempFlows.setCashTransitionType(aflFlows.getCashTransitionType());
											tempFlows.setCustAccount(aflFlows.getCustAccount());
											tempFlows.setCustId(aflFlows.getCustId());
											tempFlows.setCustName(aflFlows.getCustName());
											tempFlows.setDebitCreditType(aflFlows.getDebitCreditType());
											tempFlows.setMemo(aflFlows.getMemo());
											tempFlows.setOpenAccountDate(aflFlows.getOpenAccountDate());
											tempFlows.setOpenAccountOrgId(aflFlows.getOpenAccountOrgId());
											tempFlows.setOppositeBankName(aflFlows.getOppositeBankName());
											tempFlows.setOppositeCustAccount(aflFlows.getOppositeCustAccount());
											tempFlows.setOppositeCustAccountName(aflFlows.getOppositeCustAccountName());
											tempFlows.setSysAccount(aflFlows.getSysAccount());
											tempFlows.setTransactionAmount(aflFlows.getTransactionAmount());
											tempFlows.setTransactionCode(aflFlows.getTransactionCode());
											tempFlows.setTransactionDate(aflFlows.getTransactionDate());
											tempFlows.setTransactionSeqNum(aflFlows.getTransactionSeqNum());
											tempFlows.setTransactionTime(aflFlows.getTransactionTime());
											tempFlows.setStatus("0");
											flows.add(tempFlows);
										}
										//保存明细
										accountFlowsService.saveAccountFlows(flows);
									}
									
								} else if ("9999".equals(data.getStatus())) {
									log.info("-------------------------------------------------------------调用核心接口查询银行信息出现异常"
											+ data.getStatusText());
								}
							}
						} catch (Exception e) {
							log.info("-------------------------------------------------------------同步银行流水信息出现异常"+e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}

	}
}
