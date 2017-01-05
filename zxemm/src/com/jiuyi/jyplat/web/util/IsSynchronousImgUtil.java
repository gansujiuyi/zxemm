package com.jiuyi.jyplat.web.util;

import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.imgSynchronous.RsyncUtil;



/**
 * 图片是否同步，公用实体类
 * @author Administrator
 *
 */
public class IsSynchronousImgUtil {
	
	public static void isSynchronousImg(){
		//add guys 20140924 同步shell脚本物理路径
		String isSynchronousImg=ConfigManager.getInstance().getConfigItem("isSynchronousImg","0").toString();
		if("1".equals(isSynchronousImg)){
			String shellPath=ConfigManager.getInstance().getConfigItem("shellPath","/home/ezexapp/rsyncd.sh").toString();			
			RsyncUtil.rsyncToService(shellPath);
		}
	}
}
