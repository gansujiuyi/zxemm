package com.jiuyi.jyplat.util.imgSynchronous;

import java.io.IOException;


import org.apache.log4j.Logger;

/**
 * @desc 执行bat或sh脚本的工具类
 * @author cyq
 * @date 2013.12.10
 */
public class RsyncUtil {
	
	private static Logger log = Logger.getLogger(RsyncUtil.class);
	
	//将图文信息同步至服务器
	public static void rsyncToService(String rsyncBatPath){
		log.debug( "同步shell脚本路径为：" + rsyncBatPath ) ;
		try {
			Process process = Runtime.getRuntime().exec( rsyncBatPath ) ;
			
			//起两个线程来对命令输出日志进行处理，避免日志多了阻塞
			ExecCmdLog cmderrlog = new ExecCmdLog ( process.getErrorStream(), "STDERR") ;
			cmderrlog.start() ;
			
			ExecCmdLog cmdoutlog = new ExecCmdLog ( process.getInputStream(), "STDOUT") ;
			cmdoutlog.start() ;
			
			int exitCode = process.waitFor() ;
			log.debug( "exec cmd exitcode=：" + exitCode ) ;
			
			if (exitCode != 0)
			{
				log.debug( "执行操作系统命令失败，命令返回值不等于0，为"+ exitCode ) ;
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("调用同步错误1："+e.getMessage());
		} catch (InterruptedException e) {
			log.debug("调用同步错误2："+e.getMessage());
			e.printStackTrace();
		}
		 /*try {
	            Process process = Runtime.getRuntime().exec(rsyncBatPath);
	            InputStreamReader ir = new InputStreamReader(process.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            String line;
	            while((line = input.readLine()) != null)
	            	log.debug("返回结果"+line);
	            input.close();
	            ir.close();
	            log.debug("同步图像成功");
	        } catch (IOException e) {
	        	log.debug("同步图像异常"+e.getMessage());
	            e.printStackTrace();
	        }*/
	    }

}
