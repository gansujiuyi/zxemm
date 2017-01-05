package com.jiuyi.jyplat.service.acegiManage.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.net.message.Head;
import com.jiuyi.net.message.MsgRetCode;
import com.jiuyi.jyplat.dao.acegiManage.FunctionDao;
import com.jiuyi.jyplat.dao.acegiManage.OperatorDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.dao.base.SysEnumItemDao;
import com.jiuyi.jyplat.dao.base.SysParameterDao;
import com.jiuyi.jyplat.entity.menu.Level1;
import com.jiuyi.jyplat.entity.menu.Level2;
import com.jiuyi.jyplat.entity.menu.Level3;
import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.entity.system.SysFunction;
import com.jiuyi.jyplat.service.acegiManage.OperatorService;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DataTransfer;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.SortList;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {

	Logger log = Logger.getLogger(OperatorServiceImpl.class);

	@Resource
	private OperatorDao operatorDao;

	@Resource
	private FunctionDao functionDao;

	@Resource
	private SysEnumItemDao sysEnumItemDao;

	@Resource
	private SysParameterDao sysParameterDao;

	@Resource
	private LogInfoDao logDao;

	/**
	 * 根据操作员编号查询操作员信息
	 */
	@Override
	public Operator getOperator(Operator o) {
		try {
			Criteria criteria = operatorDao.createCriteria();

			criteria.setFetchMode("role", FetchMode.JOIN);
			criteria.setFetchMode("role.sysFunctions", FetchMode.JOIN);
			criteria.setFetchMode("inst", FetchMode.JOIN);

			criteria.add(Restrictions.eq("operNo", o.getOperNo().trim()));
			//			criteria.add(Restrictions.eq("role.sysFunctions.status", "1"));
			List<Operator> list = criteria.list();
			if (list.size() > 0) {
				Operator operator = (Operator) list.get(0);
				return operator;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public void loginSuccess(Operator o) throws Exception {

		// 部门名称
		List<Object[]> sei = sysEnumItemDao.queryEnumItem("operator", "department", o.getDepartment().trim());
		if (sei != null && sei.size() > 0)
			o.setDepartmentName(sei.get(0)[2].toString());

		StringBuffer functionNo = new StringBuffer("");
		for (SysFunction sysFunction : o.getRole().getSysFunctions()) {
			if (sysFunction != null && sysFunction.getStatus() != null
					&& (sysFunction.getStatus().trim().equals("1") || sysFunction.getStatus().trim().equals("2"))) {
				functionNo.append(DataTransfer.toDB(sysFunction.getFunctionNo().trim()) + ",");
			}
		}
		if (functionNo.length() > 0) {
			String fNos = functionNo.toString().substring(0, functionNo.length() - 1);
			//根据Function查询所有对应Action
			Object[] objs = operatorDao.queryActionByFunId(fNos);

			if (objs != null) {
				List<String> authUrls = new ArrayList<String>();
				for (Object obj : objs) {
					SysAction sa = (SysAction) obj;
					authUrls.add(sa.getActionUrl().toLowerCase());
				}
				// 拥有功能权限存入session
				SessionUtil.setActionUrl(authUrls);
			}
		}
		// 操作员存入session
		SessionUtil.setOperator(o);
		ActionContext.getContext().getSession().put("currentIp", this.getClientAddress());

		Operator op = o.clone();
		//登录成功后将错误登录次数置零，并将当前的登录时间、Ip、总登录次数写入数据库
		op.setErrorLoginTimes(0); //	错误登陆次数
		op.setLastLoginTime(DateUtils.dateToDateString(new Date())); //	最后登陆时间
		op.setLastLoginIp(this.getClientAddress()); //	最后登陆IP
		op.setLoginTimes(op.getLoginTimes() == null ? 1 : op.getLoginTimes() + 1); //	总登陆次数加一
		operatorDao.update(op);

		log.debug("用户:" + o.getOperName() + ",登陆成功,登陆IP:" + o.getLastLoginIp());

	}

	@Override
	public void loginFail(Operator o) {

		if (null==o.getErrorLoginTimes() || o.getErrorLoginTimes() == 0) {// 尝试登录5次锁定用户
			o.setErrorLoginTimes(1);
		} else {
			if (Utils.daysOfTwo(DateUtils.getDate(o.getLastLoginTime(), DateUtils.DEFAULT_TIME), new Date()) >= 1) {//是否当天登陆
				o.setErrorLoginTimes(1);
			} else {
				o.setErrorLoginTimes(o.getErrorLoginTimes() + 1);
				if (o.getErrorLoginTimes() >= 5) {
					o.setOperStatus("4"); // 1可用2待审核3锁定4停用
				}
			}
		}
		o.setLastLoginTime(DateUtils.dateToDateString(new Date())); //	最后登陆时间
		o.setLastLoginIp(this.getClientAddress()); //	最后登陆IP

		operatorDao.update(o);

		log.debug("用户:" + o.getOperName() + ",登陆密码错误次数:" + o.getErrorLoginTimes() + ",登陆IP:" + o.getLastLoginIp());
	}

	// 获取客户端IP地址
	//	private final String getClientAddress() {
	//
	//		 HttpServletRequest request = (HttpServletRequest) ActionContext
	//				.getContext().get(ServletActionContext.HTTP_REQUEST);
	//
	//		String ip = request.getHeader("x-forwarded-for");
	//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	//			ip = request.getHeader("Proxy-Client-IP");
	//		}
	//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	//			ip = request.getHeader("WL-Proxy-Client-IP");
	//		}
	//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	//			ip = request.getRemoteAddr();
	//		}
	//		return ip; 
	//
	//	}
	private final String getClientAddress() {

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null)
			ip = request.getRemoteAddr();
		return ip.trim().equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;

	}

	@Override
	public List<Level1> getAllMenuList() throws Exception {
		Operator operator = SessionUtil.getOperator();
		Set<SysFunction> sysFunctions = operator.getRole().getSysFunctions();
		List<SysFunction> list = new ArrayList<SysFunction>();
		for (SysFunction sysFunction : sysFunctions) {
			String status = sysFunction.getStatus(); // 可用但隐藏的菜单不会被显示
			if (status != null && status.trim().equals("1"))
				list.add(sysFunction);
		}
		SortList<SysFunction> sortList = new SortList<SysFunction>();
		sortList.Sort(list, "getOrderBy", "desc");
		return this.getAllMenuList_c(list);
	}

	@Override
	public List<Level1> getAllMenuList(List<SysFunction> sysFunctions) throws Exception {
		return this.getAllMenuList_c(sysFunctions);
	}

	/**
	 * 将取出的三级菜单数据装入到Data Bean中
	 *@return Level1[]  一级菜单数据Bean集合
	 */
	private List<Level1> getAllMenuList_c(Collection<SysFunction> sysFunctions) throws Exception {

		List<Level1> level_1_List = new ArrayList<Level1>(); //所有的第一级菜单

		List<Level2> level_2_List; //所有的第二级菜单

		List<Level3> level_3_List; //所有的第三级菜单

		List<Level3> level_2_or_3 = new ArrayList<Level3>(); //所有的第二,三级菜单(临时)

		Level1 singleLevel_1;
		Level3 singleLevel_2_or_3;

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();
		String MENU_LEVEL_1_IMG = serverName + "/images/MENU15.gif";
		String MENU_LEVEL_2_IMG = "";
		String MENU_LEVEL_3_IMG = serverName + "/images/index_9.gif";
		//第一次循环,加载第一级到level_1_List, 把第二级和第三级一起加载到level_2_or_3
		for (SysFunction sysFunction : sysFunctions) {
			// 父级为空则为第一级
			if (sysFunction.getParentFunctionNo() == null || sysFunction.getParentFunctionNo().trim().equals("")) {
				singleLevel_1 = new Level1();

				singleLevel_1.setImg_src(MENU_LEVEL_1_IMG);
				singleLevel_1.setMenu_id(sysFunction.getFunctionNo().trim());
				singleLevel_1.setMenu_name(sysFunction.getFunctionName().trim());

				level_1_List.add(singleLevel_1);
			} else {// 父级不为空, 统统认为是第二级或者第三级
				singleLevel_2_or_3 = new Level3();

				singleLevel_2_or_3.setMenu_id(sysFunction.getFunctionNo().trim());
				singleLevel_2_or_3.setMenu_name(sysFunction.getFunctionName().trim());
				singleLevel_2_or_3.setServlet_class(sysFunction.getUrl() == null ? "" : sysFunction.getUrl().trim());
				singleLevel_2_or_3.setParent_id(sysFunction.getParentFunctionNo().trim());

				level_2_or_3.add(singleLevel_2_or_3);
			}
		}

		Level2 leve2;
		Level3 leve3;

		// 第二次循环,根据第一级的Menu_id取第二级和第三级一起的level_2_or_3中寻找子级
		for (Level1 level1 : level_1_List) {
			level_2_List = new ArrayList<Level2>();
			// 第三次循环, 已拿到某一个第一级的ID, 下面去循环取出第二级
			for (Level3 level3 : level_2_or_3) {
				// 此处判断匹配则为第二级
				if (level3.getParent_id().trim().equalsIgnoreCase(level1.getMenu_id().trim())) {
					leve2 = new Level2();

					leve2.setImg_src(MENU_LEVEL_2_IMG);
					leve2.setMenu_id(level3.getMenu_id());
					leve2.setMenu_name(level3.getMenu_name());

					level_3_List = new ArrayList<Level3>();
					// 第三次循环, 已拿到某一个第二级的ID, 下面去循环取出第三级
					for (Level3 level33 : level_2_or_3) {
						// 此处判断匹配则为第三级
						if (level33.getParent_id().trim().equalsIgnoreCase(leve2.getMenu_id().trim())) {
							leve3 = new Level3();

							leve3.setImg_src(MENU_LEVEL_3_IMG);
							leve3.setMenu_id(level33.getMenu_id());
							leve3.setMenu_name(level33.getMenu_name());
							leve3.setServlet_class(level33.getServlet_class());

							level_3_List.add(leve3);
						}
					}
					if (level_3_List.size() > 0)
						leve2.setLevel3(level_3_List);
					level_2_List.add(leve2);
				}
			}
			level1.setLevel2(level_2_List);
		}

		return level_1_List;
	}

	@Override
	public String getMenuHtml() throws Exception {

		List<Level1> menuCollection = this.getAllMenuList();

		StringBuffer rtn = new StringBuffer(); //返回String

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();

		List<Level2> controllerCollection;
		List<Level3> functionCollection;
		String imgId; //功能前的图片的ID
		String trId; //子功能的图片ID

		rtn.append("<script language=JavaScript>\r\n");
		rtn.append("function changed(ii,ss){\r\n");
		rtn.append("  if(ss.style.display==\"none\"){\r\n");
		rtn.append("    ss.style.display=\"\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/menu_close.gif\";\r\n");
		//		rtn.append("    ii.src=\"/uxun"+serverName+"/s/icon_formdefine.gif\";\r\n");		
		rtn.append("  }else\r\n");
		rtn.append("  {\r\n");
		rtn.append("    ss.style.display=\"none\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/menu_open.gif\";\r\n");
		//		rtn.append("    ii.src=\""+serverName+"/images/icon_formdefine.gif\";\r\n");
		rtn.append("  }\r\n");
		rtn.append("}\r\n");

		rtn.append("function changedNoFlag(ii,ss){\r\n");
		//-- 当打开或关闭一个一级菜单时,首先关闭处于打开状态的一级菜单
		StringBuffer tempRtn = new StringBuffer();
		for (int i = 0; i < menuCollection.size(); i++) {
			imgId = "imgmenu" + menuCollection.get(i).getMenu_id();
			trId = "trmenu" + menuCollection.get(i).getMenu_id();
			tempRtn.append(trId);
			tempRtn.append(".style.display=\"none\";\r\n");
			tempRtn.append(imgId);
			tempRtn.append(".background=\"" + serverName + "/images/iconbg_down.gif\";\r\n");
		}
		//--

		rtn.append("  if(ss.style.display==\"none\"){\r\n");
		rtn.append(tempRtn);
		rtn.append("    ss.style.display=\"\";\r\n");
		rtn.append("    ii.background=\"" + serverName + "/images/iconbg.gif\";\r\n");
		rtn.append("  }else\r\n");
		rtn.append("  {\r\n");
		rtn.append(tempRtn);
		rtn.append("    ss.style.display=\"none\";\r\n");
		rtn.append("    ii.background=\"" + serverName + "/images/iconbg_down.gif\";\r\n");
		rtn.append("  }\r\n");
		rtn.append("}\r\n");
		rtn.append("</script>\r\n");

		for (int i = 0; i < menuCollection.size(); i++) {
			imgId = "imgmenu" + menuCollection.get(i).getMenu_id();
			trId = "trmenu" + menuCollection.get(i).getMenu_id();

			/* 生成主菜单check框 */
			rtn.append("<TR><TD id="
					+ imgId
					+ " style=\"cursor:pointer; background-repeat: no-repeat;\" align=\"center\" height=21 noWrap background=\""
					+ serverName + "/images/iconbg_down.gif\" width=\"100%\" onclick=\"changedNoFlag(");
			rtn.append(imgId);
			rtn.append(",");
			rtn.append(trId);
			rtn.append(");\">");
			//			rtn.append("&nbsp;<img id=\"");
			//			rtn.append(imgId);
			//			rtn.append("\" align=absMiddle border=0 height=18 width=18 src=\"");
			//			rtn.append(menuCollection.get(i).getImg_src());
			//			rtn.append("\" >");	

			//	        rtn.append("<a href=\"#\" class=\"list_tilte\" onClick=\"changedNoFlag(");
			//			rtn.append(imgId);
			//			rtn.append(",");
			//			rtn.append(trId);
			//			rtn.append(");return false\">");								
			rtn.append("<b>" + menuCollection.get(i).getMenu_name() + "</b>");
			rtn.append("</TD>\r\n</TR>\r\n");

			rtn.append("<tr valign=\"top\" id=\"");
			rtn.append(trId);
			rtn.append("\" style=\"display:none\">\r\n<td>\r\n<table width=\"100%\" border=\"0\" align=\"left\" cellpadding=\"2\" cellspacing=\"0\" id=\"table1\">\r\n");

			controllerCollection = menuCollection.get(i).getLevel2();
			for (int j = 0; j < controllerCollection.size(); j++) {
				imgId = "imgcontroller" + controllerCollection.get(j).getMenu_id();
				trId = "trcontroller" + controllerCollection.get(j).getMenu_id();

				/* 生成控制器check框 */
				rtn.append("<TR>\r\n<TD height=21 noWrap class=list_table_tr_odd1 >");

				rtn.append("&nbsp;");
				rtn.append("&nbsp;<img id=\"");
				rtn.append(imgId);
				//				rtn.append("\" src=\""+serverName+"/images/icon_formdefine.gif\" width=\"16\" height=\"16\" align=absMiddle border=0>&nbsp;");
				rtn.append("\" src=\"" + serverName
						+ "/images/menu_open.gif\" width=\"16\" height=\"16\" align=absMiddle border=0 ");
				rtn.append("onClick=\"changed(");
				rtn.append(imgId);
				rtn.append(",");
				rtn.append(trId);
				rtn.append(")\">&nbsp;");
				functionCollection = controllerCollection.get(j).getLevel3();

				rtn.append("<a style=\"cursor: pointer;\" onClick=\"");
				if (functionCollection != null) {
					rtn.append(" changed(");
					rtn.append(imgId);
					rtn.append(",");
					rtn.append(trId);
					rtn.append(");");
				}
				rtn.append("return false\">");

				rtn.append(controllerCollection.get(j).getMenu_name());
				rtn.append("</a></TD>\r\n</TR>\r\n");

				if (functionCollection != null) {
					/* 生成功能check框 */
					rtn.append("<TR id=\"");
					rtn.append(trId);
					rtn.append("\" style=\"display:none\">\r\n<TD height=21 noWrap class=list_table_tr_odd1>");
					rtn.append("<Table border=0 width=100%>");
					for (int k = 0; k < functionCollection.size(); k++) {
						rtn.append("<tr><td width=1%>&nbsp;</td><td noWrap>&nbsp;&nbsp;");

						rtn.append("<IMG align=absMiddle border=0 height=10 src=\"");
						rtn.append(functionCollection.get(k).getImg_src());
						rtn.append("\" width=10>");

						rtn.append("<A href=\"");
						String sc = functionCollection.get(k).getServlet_class();
						rtn.append(sc.indexOf(serverName + "/") == 0 ? sc : (serverName + "/" + sc));
						//强制对此链接进行弹窗处理。
						if(sc.contains("showOrderEhouse")){
							//rtn.append("\" target=\"_blank\">");
							rtn.append("\" target=\"mainFrame\">");
						}else{
							rtn.append("\" target=\"mainFrame\">");
						}
						rtn.append(functionCollection.get(k).getMenu_name());
						rtn.append("</a></td></tr>");
					}
					rtn.append("</Table>");
					rtn.append("</TD>\r\n</TR>\r\n");
				}
			}

			rtn.append("</table>\r\n</td>\r\n</tr>\r\n");/////						
		}

		return rtn.toString();
	}

	@Override
	public String getAddRoleHtml() throws Exception {
		List<SysFunction> lf = functionDao.createCriteria().addOrder(Order.desc("orderBy")).list();
		List<Level1> allMenu = this.getAllMenuList(lf);

		StringBuffer rtn = new StringBuffer(); //返回String

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();

		List<Level2> controllerCollection;
		List<Level3> functionCollection;
		StringBuffer checked;
		StringBuffer unchecked;

		String imgId; //功能前的图片的ID
		String trId; //子功能的图片ID

		rtn.append("<script language=JavaScript>\r\n");
		rtn.append("function changed(ii,ss){\r\n");
		rtn.append("  if(document.getElementById(ss).style.display==\"none\"){\r\n");
		rtn.append("    document.getElementById(ss).style.display=\"\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/open.gif\";\r\n");
		rtn.append("  }else\r\n");
		rtn.append("  {\r\n");
		rtn.append("    document.getElementById(ss).style.display=\"none\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/close.gif\";\r\n");
		rtn.append("  }\r\n");
		rtn.append("}\r\n");
		rtn.append("function putParentOn(level,eventObj){\r\n");
		//		rtn.append("alert(eventObj.attributes[\"parentId\"].value);");
		rtn.append("	var	parentMenu = eval('document.PageForm.'+eventObj.attributes[\"parentId\"].value);//将上级菜单勾选上\r\n");
		rtn.append("	if(eventObj.checked){\r\n");
		rtn.append("		parentMenu.checked = true;\r\n");
		rtn.append("		if(level > 1){//如果还有上级菜单,继续勾选\r\n");
		rtn.append("			var pparentMenu = eval('document.PageForm.'+parentMenu.attributes[\"parentId\"].value);\r\n");
		rtn.append("			pparentMenu.checked = true;\r\n");
		rtn.append("		}\r\n");
		rtn.append("	}else{\r\n");
		rtn.append("		var temp1 = $(\"input[parentId='\" + eventObj.attributes[\"parentId\"].value +\"']\");\r\n");
		rtn.append("		var none = true;\r\n");
		rtn.append("		for(var tl=0;tl<temp1.length;tl++){\r\n");
		rtn.append("			if(temp1[tl].id){\r\n");
		rtn.append("				if(temp1[tl].checked){\r\n");
		rtn.append("					none = false;\r\n");
		rtn.append("					break;\r\n");
		rtn.append("				}\r\n");
		rtn.append("			}\r\n");
		rtn.append("		}\r\n");
		rtn.append("		if(none){\r\n");
		rtn.append("			parentMenu.checked = false;\r\n");
		rtn.append("		}\r\n");
		rtn.append("		if(parentMenu.attributes[\"parentId\"]){\r\n");
		//		rtn.append("alert(parentMenu.checked);");
		rtn.append("			var ptemp1 = $(\"input[parentId='\" + parentMenu.attributes[\"parentId\"].value +\"']\");\r\n");
		rtn.append("			var pnone = true;\r\n");
		rtn.append("			for(var ptl=0;ptl<ptemp1.length;ptl++){\r\n");
		rtn.append("				if(ptemp1[ptl].id){\r\n");
		rtn.append("					if(ptemp1[ptl].checked){\r\n");
		rtn.append("						pnone = false;\r\n");
		rtn.append("						break;\r\n");
		rtn.append("					}\r\n");
		rtn.append("				}\r\n");
		rtn.append("			}\r\n");
		rtn.append("			if(pnone){\r\n");
		rtn.append("				var ppMenu = eval('document.PageForm.'+parentMenu.attributes[\"parentId\"].value);\r\n");
		rtn.append("				ppMenu.checked = false;\r\n");
		rtn.append("			}\r\n");
		rtn.append("		}\r\n");
		rtn.append("	}\r\n");
		rtn.append("}\r\n");
		rtn.append("</script>\r\n");

		for (int i = 0; i < allMenu.size(); i++) {
			checked = new StringBuffer(); //存放主菜单check框click函数中的框选中模块代码
			unchecked = new StringBuffer(); //存放主菜单check框click函数中的框取消选中模块代码

			imgId = "imgmenu" + allMenu.get(i).getMenu_id();
			trId = "trmenu" + allMenu.get(i).getMenu_id();

			/* 生成主菜单check框 */
			rtn.append("<TR><TD height=21 noWrap class=list_table_tr_title1>&nbsp;<img id=\"");
			rtn.append(imgId);
			rtn.append("\" src=\"" + serverName
					+ "/images/close.gif\" width=\"9\" height=\"9\" style=\"cursor:pointer\" onClick=\"changed('");
			rtn.append(imgId);
			rtn.append("','");
			rtn.append(trId);
			rtn.append("')\"><INPUT type=checkbox value=\"");
			rtn.append(allMenu.get(i).getMenu_id());
			rtn.append("\" name=\"menu");
			rtn.append(allMenu.get(i).getMenu_id());
			rtn.append("\" onclick=\"menu");
			rtn.append(allMenu.get(i).getMenu_id());
			rtn.append("click()\">");
			rtn.append(allMenu.get(i).getMenu_name());
			rtn.append("</TD>\r\n</TR>\r\n");

			rtn.append("<tr valign=\"top\" id=\"");
			rtn.append(trId);
			rtn.append("\" style=\"display:none\">\r\n<td>\r\n<table width=\"100%\" border=\"0\" align=\"left\" cellpadding=\"2\" cellspacing=\"0\" id=\"table1\">\r\n");

			controllerCollection = allMenu.get(i).getLevel2();
			for (int j = 0; j < controllerCollection.size(); j++) {
				functionCollection = controllerCollection.get(j).getLevel3();
				imgId = "imgcontroller" + controllerCollection.get(j).getMenu_id();
				trId = "trcontroller" + controllerCollection.get(j).getMenu_id();

				/* 生成控制器check框 */
				rtn.append("<TR>\r\n<TD height=21 noWrap class=list_table_tr_odd1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id=\""); /////
				rtn.append(imgId);
				rtn.append("\" src=\"" + serverName + "/images/close.gif\" width=\"9\" height=\"9\"");

				if (functionCollection != null) {
					rtn.append(" onClick=\"changed('");
					rtn.append(imgId);
					rtn.append("','");
					rtn.append(trId);
					rtn.append("')\"");
				}

				rtn.append("><INPUT type=checkbox value=\"");
				rtn.append(controllerCollection.get(j).getMenu_id());
				rtn.append("\" id=\"controller");
				rtn.append(controllerCollection.get(j).getMenu_id());
				rtn.append("\" name=\"controller");
				rtn.append(controllerCollection.get(j).getMenu_id());
				rtn.append("\" onclick=\"controller");
				rtn.append(controllerCollection.get(j).getMenu_id());
				rtn.append("click()\" parentId=\"menu" + allMenu.get(i).getMenu_id() + "\">");
				rtn.append(controllerCollection.get(j).getMenu_name());
				rtn.append("</TD>\r\n</TR>\r\n");

				if (functionCollection != null) {
					/* 生成功能check框 */
					rtn.append("<TR id=\"");
					rtn.append(trId);
					rtn.append("\" style=\"display:none\">\r\n<TD height=21 noWrap class=list_table_tr_odd1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT type=checkbox value=\""); /////
					rtn.append(functionCollection.get(0).getMenu_id());
					rtn.append("\" id=\"function");
					rtn.append(functionCollection.get(0).getMenu_id());
					rtn.append("\" name=\"function\" parentId=\"controller");
					rtn.append(controllerCollection.get(j).getMenu_id());
					rtn.append("\" onclick=\"putParentOn(2,this)\">");
					rtn.append(functionCollection.get(0).getMenu_name());

					for (int k = 1; k < functionCollection.size(); k++) {
						rtn.append("&nbsp;&nbsp;<INPUT type=checkbox value=\"");
						rtn.append(functionCollection.get(k).getMenu_id());
						rtn.append("\" id=\"function");
						rtn.append(functionCollection.get(k).getMenu_id());
						rtn.append("\" name=\"function\" parentId=\"controller");
						rtn.append(controllerCollection.get(j).getMenu_id());
						rtn.append("\" onclick=\"putParentOn(2,this)\">");
						rtn.append(functionCollection.get(k).getMenu_name());
					}

					rtn.append("</TD>\r\n</TR>\r\n");
				}
			}

			rtn.append("</table>\r\n</td>\r\n</tr>\r\n");/////

			/* 生成check框script脚本语句 */
			rtn.append("<script language=javascript>\r\n");

			/* 生成控制器click函数 */
			controllerCollection = allMenu.get(i).getLevel2();
			for (int i1 = 0; i1 < controllerCollection.size(); i1++) {
				//控制器checked语句
				checked.append("document.PageForm.controller");
				checked.append(controllerCollection.get(i1).getMenu_id());
				checked.append(".checked  = true;\r\n");

				//控制器unchecked语句
				unchecked.append("document.PageForm.controller");
				unchecked.append(controllerCollection.get(i1).getMenu_id());
				unchecked.append(".checked  = false;\r\n");

				//以下生成控制器check框的click函数
				rtn.append("function controller");
				rtn.append(controllerCollection.get(i1).getMenu_id());
				rtn.append("click(){");
				functionCollection = controllerCollection.get(i1).getLevel3();
				if (functionCollection != null) {
					rtn.append("\r\nif (! window.event.srcElement.checked) { \r\n");

					for (int j1 = 0; j1 < functionCollection.size(); j1++) {
						rtn.append("document.PageForm.function");
						rtn.append(functionCollection.get(j1).getMenu_id());
						rtn.append(".checked  = false;\r\n");

						unchecked.append("document.PageForm.function");
						unchecked.append(functionCollection.get(j1).getMenu_id());
						unchecked.append(".checked  = false;\r\n");
					}

					rtn.append("} else if(window.event.srcElement.checked) { \r\n");
					for (int j1 = 0; j1 < functionCollection.size(); j1++) {
						rtn.append("document.PageForm.function");
						rtn.append(functionCollection.get(j1).getMenu_id());
						rtn.append(".checked  = true;\r\n");

						checked.append("document.PageForm.function");
						checked.append(functionCollection.get(j1).getMenu_id());
						checked.append(".checked  = true;\r\n");
					}
					rtn.append("} \r\n");
					rtn.append("	putParentOn(1,window.event.srcElement); \r\n");
				}
				rtn.append("}\r\n\r\n");

			}

			//以下生成主菜单check框click函数
			rtn.append("function menu");
			rtn.append(allMenu.get(i).getMenu_id());
			rtn.append("click(){\r\nif (! window.event.srcElement.checked) { \r\n");
			rtn.append(unchecked.toString());
			rtn.append("} else if(window.event.srcElement.checked) { \r\n");
			rtn.append(checked.toString());
			rtn.append("} \r\n}\r\n");

			rtn.append("</script>\r\n");
		}

		return rtn.toString();
	}

	/**
	 * 根据操作员编号查询操作员信息
	 */
	@Override
	public Operator getOperatorByNo(String operNo) throws Exception {
		return operatorDao.getById(operNo);
	}

	@Override
	public boolean queryByRole(Integer[] roleId) throws Exception {
		boolean isExist = false;
		if (roleId == null || roleId.length < 1)
			throw new Exception("数据格式错误,查询角色下操作员错误!");
		Criteria criteria = operatorDao.createCriteria();
		//		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.add(Restrictions.in("role.roleId", roleId));
		List list = criteria.list();
		if (list.size() > 0)
			isExist = true;
		return isExist;
	}

	/**
	 * 分页查询所有操作员信息
	 */
	@Override
	public PageFinder<Operator> queryOperatorByName(String operName, Query query) throws Exception {
		Criteria criteria = operatorDao.createCriteria();
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("inst", FetchMode.JOIN);
		if (null != operName && !"".equalsIgnoreCase(operName.trim())) {
			criteria.add(Restrictions.like("operName", "%" + operName.trim() + "%"));
		}

		return operatorDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("createdDate"));
	}

	/**
	 * 按条件分页查询操作员
	 */
	@Override
	public PageFinder<Operator> queryOperator(String operName, String operStatus, String department,
			String institutionId, Query query) throws Exception {
		Criteria criteria = operatorDao.createCriteria();
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("inst", FetchMode.JOIN);
		if (null != operName && !"".equalsIgnoreCase(operName.trim())) {
			criteria.add(Restrictions.like("operName", "%" + operName.trim() + "%"));
		}
		if (null != operStatus && !"".equalsIgnoreCase(operStatus.trim())) {
			criteria.add(Restrictions.eq("operStatus", operStatus.trim()));
		}
		if (null != department && !"".equalsIgnoreCase(department.trim())) {
			criteria.add(Restrictions.eq("department", department.trim()));
		}
		if (null != institutionId && !"".equalsIgnoreCase(institutionId.trim())) {
			Institution inst = new Institution();
			inst.setInstitutionId(Integer.valueOf(institutionId.trim()));
			criteria.add(Restrictions.eq("inst", inst));
		}
		return operatorDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.asc("operStatus"));
	}

	/**
	 * 查询所有操作员信息
	 */
	@Override
	public List<Operator> queryOperatorList() throws Exception {
		return operatorDao.createCriteria().list();
	}

	/**
	 * 根据属性查询所有操作员
	 */
	@Override
	public List<Operator> queryAllOperator(Operator oper) throws Exception {
		Criteria criteria = operatorDao.createCriteria();
		if (oper.getOperNo() != null && !"".equals(oper.getOperNo())) {
			criteria.add(Restrictions.eq("operNo", oper.getOperNo().trim()));
		}
		return criteria.list();
	}

	/**
	 * 保存新增操作员信息
	 */
	@Override
	public void insertOperator(Operator operator) throws Exception {
		operator.setCreatedDate(DateUtils.dateToDateString(new Date()));
		operator.setPassword(this.queryOperDefaultPW());
		operator.setAuthStatus("0"); // 默认状态为未通过审核
		operator.setOperStatus("2"); // 默认状态为待审核
		operatorDao.save(operator);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_OPER, OperConstant.ACT_ADD, "");
	}

	/**
	 * 获取操作员初始密码
	 */
	@Override
	public String queryOperDefaultPW() throws Exception {
		return sysParameterDao.queryOperDefaultPW();
	}

	/**
	 * 修改操作员信息
	 */
	@Override
	public void updateOperator(Operator operator) throws Exception {
		operatorDao.updateOper(operator);
		log.info("操作员信息修改成功:" + operator.getOperName());
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_OPER, OperConstant.ACT_EDIT,
				"操作员编号：" + operator.getOperNo());
	}

	/**
	 * 停用操作员
	 */
	@Override
	public void delOperator(Operator operator) throws Exception {
		String status = operator.getOperStatus();
		if (operator == null || operator.getOperNo().trim().equals(""))
			throw new Exception("数据格式错误," + (status.trim().equals("4") ? "停用" : "启用") + "操作员失败");

		String[] ids = operator.getOperNo().trim().split(",");
		for (String string : ids) {
			if (status.trim().equals("4")) {
				Operator oper = operatorDao.getById(string);
				if (oper.getRole().getRoleId() == Constant.SYSTEM_ADMIN_ROLE_1
						|| oper.getRole().getRoleId() == Constant.SYSTEM_ADMIN_ROLE_2) {
					log.error("系统管理员角色:" + oper.getOperName() + ",不能停用!");
					throw new Exception("系统管理员:" + oper.getOperName() + ",不能停用!");
				}
				operatorDao.updateOperStatus(oper.getOperNo(), status);
			} else {
				operatorDao.updateOperStatus(string, "2"); // 需由审核管理员,审核通过后才可更改为启用状态

				operatorDao.updateOperAuthStatus(string, "0");//在将操作员状态设置为待审核状态的同时，将审核状态改为否
			}
			//生成操作员日志
			logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_OPER, OperConstant.ACT_DEL, (status.trim()
					.equals("4") ? "停用" : "启用") + "操作员编号：" + string);
		}
	}

	/**
	 * 查询所有待审核的操作员
	 */
	@Override
	public PageFinder<Operator> queryByOperStatus(String operName, String authStatus, String operStatus,
			String department, String institutionId, Query query) throws Exception {
		Criteria criteria = operatorDao.createCriteria();
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("inst", FetchMode.JOIN);
		if (operName != null && !operName.trim().equals("")) {
			criteria.add(Restrictions.like("operName", "%" + operName.trim() + "%"));
		}
		if (null != operStatus && !"".equalsIgnoreCase(operStatus.trim())) {
			criteria.add(Restrictions.eq("operStatus", operStatus.trim()));
		}
		if (null != department && !"".equalsIgnoreCase(department.trim())) {
			criteria.add(Restrictions.eq("department", department.trim()));
		}
		if (null != institutionId && !"".equalsIgnoreCase(institutionId.trim())) {
			Institution inst = new Institution();
			inst.setInstitutionId(Integer.valueOf(institutionId.trim()));
			criteria.add(Restrictions.eq("inst", inst));
		}
		criteria.add(Restrictions.eq("authStatus", authStatus));
		return operatorDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("createdDate"));
	}

	/**
	 * 查询所有某些登录号在某些状态的操作员总数
	 */
	@Override
	public Integer queryCountInStatus(String authStatus) throws Exception {
		return operatorDao.queryCountInStatus(authStatus);
	}

	/**
	 * 审核操作员
	 */
	@Override
	public void auditOperator(Operator oper) throws Exception {
		operatorDao.update(oper);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_OPER, OperConstant.ACT_EDIT,
				"操作员编号：" + oper.getOperNo());
	}

	/**
	 * 修改操作员密码
	 */
	@Override
	public void updateOperPwd(Operator oper) throws Exception {
		operatorDao.updateOperPwd(oper);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_OPER, OperConstant.ACT_EDIT,
				"修改密码，操作员编号：" + oper.getOperNo());
	}

}
