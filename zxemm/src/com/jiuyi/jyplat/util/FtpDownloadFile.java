package com.jiuyi.jyplat.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.cxf.binding.corba.wsdl.Array;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.jiuyi.net.message.onlinebank.EbankLogInfo;
import com.jiuyi.jyplat.entity.account.KernelPayLogInfo;



public class FtpDownloadFile {

	Logger Log = Logger.getLogger( FtpDownloadFile.class );
	FTPClient ftpClient = new FTPClient();
	String localPath=ConfigManager.getString("ftp_local_path", "0");   //下载后保存到本地的路径 
	
	/**
	 * 出账文件下载解析
	 * @param fileName
	 * @return
	 */
	public Map<String, Double> downFileOutAccount(String fileName) {  
		
		String ip=ConfigManager.getString("ftp_HostName", null);  // FTP服务器ip
		String username=ConfigManager.getString("ftp_User_Name", null);//username FTP登录账号 
		String password=ConfigManager.getString("ftp_Passwd", null); //password FTP登录密码 
		String remotePath=ConfigManager.getString("ftp_File_Path", null);   //FTP服务器上的相对路径 
		
		Map<String, Double> map  = new HashMap<String, Double>();
        FileOutputStream fos = null;
        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);
            String remoteFileName = remotePath+fileName;
            // 创建文件夹
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            fos = new FileOutputStream(localPath+fileName);
            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
            //判断文件是否正常
	          File myFile=new File(localPath+fileName);
	  	      if(myFile.length()==0){ 
	  	    	  Log.info("从ftp服务器上下载文件为空");
	  	          return map;
	  	      }
            Log.info("从ftp服务器上下载文件"+fileName+"成功");
            try {
				map = readFileCount(localPath+fileName, "1");
			} catch (Exception e) {
				Log.info("读取文件异常："+e.getMessage());
				e.printStackTrace();
			}
        } catch (IOException e) {
            Log.info("FTP下载出错！"+e.toString());
           
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                Log.info("关闭FTP连接发生异常！"+ e.toString());
            }
        }
        
		return map;
    } 
	
	/**
	 * 出账文件下载解析(返回出账数据列表)
	 * @param fileName
	 * @return
	 */
	public List<KernelPayLogInfo> snyFileOutAccount(String fileName) {  
		
		String ip=ConfigManager.getString("ftp_HostName", null);  // FTP服务器ip
		String username=ConfigManager.getString("ftp_User_Name", null);//username FTP登录账号 
		String password=ConfigManager.getString("ftp_Passwd", null); //password FTP登录密码 
		String remotePath=ConfigManager.getString("ftp_File_Path", null);   //FTP服务器上的相对路径 
		
		List<KernelPayLogInfo> kernelPayLogInfos = new ArrayList<KernelPayLogInfo>();
        FileOutputStream fos = null;
        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);
            String remoteFileName = remotePath+fileName;
            // 创建文件夹
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            fos = new FileOutputStream(localPath+fileName);
            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
            //判断文件是否正常
	          File myFile=new File(localPath+fileName);
	  	      if(myFile.length()==0){ 
	  	    	  Log.debug("从ftp服务器上下载文件为空");
	  	          return kernelPayLogInfos;
	  	      }
            Log.info("从ftp服务器上下载文件"+fileName+"成功");
            try {
            	kernelPayLogInfos = readFileOutAccount(localPath+fileName);
			} catch (Exception e) {
				Log.info("读取文件异常："+e.getMessage());
				e.printStackTrace();
			}
        } catch (IOException e) {
            Log.error("FTP下载出错！"+e.toString());
           
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                Log.error("关闭FTP连接发生异常！"+ e.toString());
            }
        }
        
		return kernelPayLogInfos;
    } 
	
	/**
	 * 入账文件下载解析
	 * @param fileName
	 * @return
	 */
	public Map<String, Double> downFileInAccount(String fileName) {  
		List<EbankLogInfo> list= new ArrayList<EbankLogInfo>();
		
		String ip=ConfigManager.getString("ftp_HostName", null);  // FTP服务器ip
		String username=ConfigManager.getString("ftp_User_Name", null);//username FTP登录账号 
		String password=ConfigManager.getString("ftp_Passwd", null); //password FTP登录密码 
		String remotePath=ConfigManager.getString("ftp_File_Path", null);   //FTP服务器上的相对路径 
		
		Map<String, Double> map  = new HashMap<String, Double>();
		
        FileOutputStream fos = null;
        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);
            String remoteFileName = remotePath+fileName;
            // 创建文件夹
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            fos = new FileOutputStream(localPath+fileName);
            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
            //判断文件是否正常
	          File myFile=new File(localPath+fileName);
	  	      if(myFile.length()==0){ 
	  	    	  Log.debug("从ftp服务器上下载文件为空");
	  	          return map;
	  	      }
            Log.debug("从ftp服务器上下载文件"+fileName+"成功");
            try {
            	map = readFileCount(localPath+fileName, "0");
			} catch (Exception e) {
				Log.debug("读取文件异常："+e.getMessage());
				e.printStackTrace();
			}
        } catch (IOException e) {
            Log.debug("FTP下载出错！"+e.toString());
           
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                Log.debug("关闭FTP连接发生异常！"+ e.toString());
            }
        }
        
		return map;
    } 
	
	/** 
	 * Description: 从FTP服务器下载信用卡账单数据文件
	 * @param fileName 要下载的文件名 
	 * @param wqz ftp交易参数对象
	 * @return boolean
	 */  
	public String downCriditCardFile(String fileName) {  
		String ip="98.10.1.159";      			   // FTP服务器ip
		String username="ftpthd";       //username FTP登录账号 
		String password="thdftp";   //password FTP登录密码 
		String remotePath="/lzdep/ftpthd/thdfile/";    //FTP服务器上的相对路径 
//		String localPath="C:/filepath/";    //下载后保存到本地的路径 
		
		
        FileOutputStream fos = null;
        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);
            Log.info("ftp地址："+ip);
            Log.info("ftp用户名："+username);
            Log.info("ftp密码："+password);
            Log.info("ftp远程路径："+remotePath);
            String remoteFileName = remotePath+fileName;
            
            // 创建文件夹
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            fos = new FileOutputStream(localPath+fileName);
            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
            
            //判断文件是否正常
          File myFile=new File(localPath+fileName);
  	      if(myFile.length()==0){ 
  	    	  Log.debug("从ftp服务器上下载文件为空");
  	          return null;
  	      }
            Log.debug("从ftp服务器上下载文件"+fileName+"成功");
        } catch (IOException e) {
            Log.debug("FTP下载出错！"+e.toString());
           
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                Log.debug("关闭FTP连接发生异常！"+ e.toString());
            }
        }
        
		return localPath+fileName;
    } 
	
	//md5加密文件名字,32位
	 public String MD5ForFileName(String inStr) {   
		  MessageDigest md5 = null;   
		  try {   
		   md5 = MessageDigest.getInstance("MD5");   
		  } catch (Exception e) {   
			  Log.debug( ""+e.toString());   
			  return "";   
		  }  
		  
		  char[] charArray = inStr.toCharArray();   
		  byte[] byteArray = new byte[charArray.length];   
		  
		  for (int i = 0; i < charArray.length; i++){   
			  byteArray[i] = (byte) charArray[i];
		  }
		  
		  byte[] md5Bytes = md5.digest(byteArray);   
		  StringBuffer hexValue = new StringBuffer();   
		  
		  for (int i = 0; i < md5Bytes.length; i++) {   
		   int val = ((int) md5Bytes[i]) & 0xff;   
		   if (val < 16){   
			   hexValue.append("0");
		   }
		   hexValue.append(Integer.toHexString(val));
		   
		  }   
		  
		  return hexValue.toString();   
	 }  
	 
	 /**
		 * 解析文件。
		 * @param path文件路径
		 * @return
		 * @throws Exception
		 */
		public List<KernelPayLogInfo>  readFileOutAccount(String fileName) throws Exception{
			List<KernelPayLogInfo> loginfos = new ArrayList<KernelPayLogInfo>();
			  String str = "";
			  String result = "";
		      File myFile=new File(fileName);
		      List<KernelPayLogInfo> list= new ArrayList<KernelPayLogInfo>();
		      //判断文件是存在
		      if(!myFile.exists())
		      { 
		          System.err.println("Can't Find " + fileName);
		      }
		      try 
		      {
//		          BufferedReader in = new BufferedReader(new FileReader(myFile),"GB2312");
		    	  BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile),"GBK"));
		          while ((str = in.readLine()) != null) 
		          {
		        	  result = str;
		        	  String[] dgwymx =  result.split("\\|");
		        	  KernelPayLogInfo kernelPayLogInfo = new KernelPayLogInfo();
		        	  kernelPayLogInfo.setTraStr(dgwymx[0].trim());//交易日期
		        	  kernelPayLogInfo.setTraChannel(dgwymx[1].trim());//交易时间
		        	  kernelPayLogInfo.setAcountNo(dgwymx[2].trim());//记账日期
		        	  kernelPayLogInfo.setTraTime(dgwymx[3].trim());//交易渠道
		        	  kernelPayLogInfo.setLoanSign(dgwymx[4].trim());//借贷标识 D - 借方C - 贷方
		        	  kernelPayLogInfo.setTraType(dgwymx[5].trim());//交易类型 0-正常1-蓝字2-红字
		        	  kernelPayLogInfo.setOppNo(dgwymx[6].trim());//对方帐号
		        	  kernelPayLogInfo.setTraMoney(dgwymx[7].trim());//交易金额
		        	  kernelPayLogInfo.setBalance(dgwymx[8].trim());//当前余额
		        	  list.add(kernelPayLogInfo);
		          }
		          Log.info("读取数据为："+result);
		          in.close();
		      } 
		      catch (IOException e) 
		      {
		          Log.debug("从ftp服务器上下载文件有误"+e.toString());
		      }
		      return list;  
		}

	 
	  /**
	   * 读取文件并将内容转化为字符串(入账信息)
	   * @param fileName
	   * @return
	   */
	  public List<EbankLogInfo> readFileInAccount(String fileName){
		  String str = "";
		  String result = "";
	      File myFile=new File(fileName);
	      EbankLogInfo ebankLogInfo = null;
	      List<EbankLogInfo> list= new ArrayList<EbankLogInfo>();
	      //判断文件是存在
	      if(!myFile.exists())
	      { 
	          System.err.println("Can't Find " + fileName);
	      }
	      try 
	      {
//	          BufferedReader in = new BufferedReader(new FileReader(myFile),"GB2312");
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile),"GBK"));
	          while ((str = in.readLine()) != null) 
	          {
	        	  result = str;
	        	  String[] dgwymx =  result.split("\\|");
	        	    ebankLogInfo = new EbankLogInfo();
	        	    ebankLogInfo.setTraDate(dgwymx[0].trim());//交易日期
					ebankLogInfo.setTraTime(dgwymx[1].trim());//交易时间
					ebankLogInfo.setSignTime(dgwymx[2].trim());//记账日期
					ebankLogInfo.setTraChannel(dgwymx[3].trim());//交易渠道
					ebankLogInfo.setMoneyType(dgwymx[4].trim());//币种
					ebankLogInfo.setCashSign(dgwymx[5].trim());//钞汇标志
					ebankLogInfo.setCashTraType(dgwymx[6].trim());//现转标志 1：现金 2：转帐
					ebankLogInfo.setLoanSign(dgwymx[7].trim());//借贷标志
					ebankLogInfo.setTraMoney(dgwymx[8].trim());//交易金额
					ebankLogInfo.setBalance(dgwymx[9].trim());//余额
					ebankLogInfo.setIdType(dgwymx[10].trim());//凭证种类
					ebankLogInfo.setIdNo(dgwymx[11].trim());//凭证号码
					ebankLogInfo.setNote(dgwymx[12].trim());//摘要
					ebankLogInfo.setCardNo(dgwymx[13].trim());//账号
					ebankLogInfo.setOppNo(dgwymx[14].trim());//对手账号
					ebankLogInfo.setPerTraNo(dgwymx[15].trim());//原交易流水号
					ebankLogInfo.setPerTraSubNo(dgwymx[16].trim());//子交易流水号
					ebankLogInfo.setOppName(dgwymx[17].trim());//对手户名
					list.add(ebankLogInfo);
	          }
	          Log.info("读取数据为："+result);
	          in.close();
	      } 
	      catch (IOException e) 
	      {
	          Log.debug("从ftp服务器上下载文件有误"+e.toString());
	      }
		return list;  
	}
	  
	  /**
	   * 读取文件并将计算入账数据
	   * @param fileName
	   * @param flag 入账\出账标志 0:入账  1：出账
	   * @return
	   */
	  public Map<String, Double> readFileCount(String fileName, String flag){
		  Double totalMoney = 0.0;
		  Map<String, Double> map = new HashMap<String, Double>();
		  int tradeCount = 0;
		  String str = "";
		  String result = "";
	      File myFile=new File(fileName);
	      //判断文件是存在
	      if(!myFile.exists())
	      { 
	          System.err.println("Can't Find " + fileName);
	      }
	      try 
	      {
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile),"GBK"));
	          while ((str = in.readLine()) != null) 
	          {
	        	  result = str;
	        	  Log.debug(str);
	        	  String[] dgwymx =  result.split("\\|");
	        	 if("0".equals(flag) && "C".equals(dgwymx[6].trim())){
	        		 if(dgwymx[7].trim() != null && !"".equals(dgwymx[7].trim())){
		        		  totalMoney += Double.parseDouble(dgwymx[7].trim());//交易金额
		        		  tradeCount ++ ;
		        	  }
	        	 }else if("1".equals(flag) && "D".equals(dgwymx[4].trim())){
	        		 if(dgwymx[7].trim() != null && !"".equals(dgwymx[7].trim())){
	        			 totalMoney += Double.parseDouble(dgwymx[7].trim());//交易金额
		        		  tradeCount ++ ;
		        	  }
	        	 }
	          }
	          map.put("totalMoney", totalMoney);
	          map.put("tradeCount",tradeCount + 0.0);
	          in.close();
	      } 
	      catch (IOException e) 
	      {
	          Log.debug("从ftp服务器上下载文件有误"+e.toString());
	      }
		return map;  
	}
	  
	public static void main(String[] args) {
//		Wx_qzinfo wqz = new Wx_qzinfo();
//		wqz.setIp("98.10.1.159");
//		wqz.setPort("baspftp");
//		wqz.setIjy_num("baspftp");
//		wqz.setChannelid("/home/baspftp/thdfile/");
//		String str = new FtpDownload_DG().downCriditCardFile("FILE01010570106600");
//		if(str!=null){
//			String[] str2 = str.split("\\|");
//			System.out.println(str2.length);
//		}
	}
}
