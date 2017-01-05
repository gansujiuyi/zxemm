/**
 * <p>Title:             ExecCmdLog.java
 * <p>Description:       执行操作系统命令的日志处理方法
 * <p>Copyright:         Copyright (C), 2011.
 * <p>Company:           www.uxunchina.com
 * @author               zenglj
 * @version	             v1.0 
 * @see		             task.BaseTask
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/23/2012	          zenglj                            	         v1.0 
 * 12/10/2013			  cyq
 */

package com.jiuyi.jyplat.util.imgSynchronous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
	对命令执行的日志进行归类处理
*/
public class ExecCmdLog extends Thread
{
	private InputStream is;  
	private String type;  
	private Logger log = Logger.getLogger(this.getClass());
	
	public ExecCmdLog(InputStream is, String type) 
	{  
    	this.is = is  ;
    	this.type = type ;
   	}  
	
	
	//启动线程来处理命令的日志输出，并归并到batch.log
	public void run() {

		InputStreamReader isr = null;  
		BufferedReader br = null;  
		log.debug(type);
		try {  
	            isr = new InputStreamReader( is );  
	            br = new BufferedReader(isr);  
	            String line=null;  
	            while ( (line = br.readLine()) != null) {  
	            	if ( "STDERR".equalsIgnoreCase( type )){
	            		log.error(line);
	            	}else{
	            		log.debug(line);
	            	}
	            }
    		}catch (IOException ioe){
    			log.error("处理脚本报错--"+ioe.toString());
			}finally{  
				if ( isr != null )
					try {
						isr.close() ;
					} catch (IOException e) {
						log.error(e.toString());
					}
				if ( br != null )
					try {
						br.close() ;
					} catch (IOException e) {
						log.error(e.toString());
					}
			}  
	}
	

}