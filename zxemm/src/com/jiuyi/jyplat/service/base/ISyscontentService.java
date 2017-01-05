package com.jiuyi.jyplat.service.base;

import java.io.File;
import java.util.List;

import com.jiuyi.jyplat.entity.base.Syscontent;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;




public interface ISyscontentService {
	
	public PageFinder<Syscontent> querySyscont(Syscontent syscont,String cityid, String type, Query query) throws Exception;
	
	public Syscontent querySyscontById(String syscontId) throws Exception;
	
	public void updateSyscont(Syscontent syscont,File file,String fileName) throws Exception;
	
	public void updateSyscontStatus(String contid, String status) throws Exception;
	
	public void saveSyscont(Syscontent syscont,File file,String fileName) throws Exception;
	
	public void delSyscont(Syscontent syscont) throws Exception;
	
	public List<Syscontent> querySys(String conttype,String cityid) throws Exception;
	
}
