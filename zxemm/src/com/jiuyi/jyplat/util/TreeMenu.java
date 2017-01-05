package com.jiuyi.jyplat.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.jiuyi.jyplat.entity.menu.Level1;
import com.jiuyi.jyplat.entity.menu.Level2;
import com.jiuyi.jyplat.entity.menu.Level3;

public class TreeMenu {

	// 生成树形菜单
	public static String getFunctionTree(List<Level1> level1s) {
		StringBuffer rtn = new StringBuffer(); // ����String

		List<Level2> level2s;
		List<Level3> level3s;
		StringBuffer checked;
		StringBuffer unchecked;

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		String serverName = request.getContextPath();

		String imgId; // 功能前的图片的ID
		String trId; // 子功能的图片ID

		rtn.append("<script >\r\n");
		rtn.append("function changed(ii,ss){\r\n");
		rtn.append("  if(document.getElementById(ss).style.display==\"none\"){\r\n");
		rtn.append("    document.getElementById(ss).style.display=\"\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/open.gif\";\r\n");
		rtn.append("  }else\r\n");
		rtn.append("  {\r\n");
		rtn.append("    document.getElementById(ss).style.display=\"none\";\r\n");
		rtn.append("    ii.src=\"" + serverName + "/images/close.gif\";\r\n");
		rtn.append("  }\r\n");
		rtn.append("} \r\n");
		rtn.append("</script>\r\n");
		for (int i = 0; i < level1s.size(); i++) {
			checked = new StringBuffer(); // �����˵�check��click�����еĿ�ѡ��ģ�����
			unchecked = new StringBuffer(); // �����˵�check��click�����еĿ�ȡ��ѡ��ģ�����

			imgId = "imgmenu" + level1s.get(i).getMenu_id();
			trId = "trmenu" + level1s.get(i).getMenu_id();

			/* �����˵�check�� */
			rtn.append("<TR><TD height=21 noWrap class=list_table_tr_title1>&nbsp;<img id=\"");
			rtn.append(imgId);
			rtn.append("\" src=\""
					+ serverName
					+ "/images/close.gif\" width=\"9\" height=\"9\" style=\"cursor:hand\" onClick=\"javascript:changed('");
			rtn.append(imgId);
			rtn.append("','");
			rtn.append(trId);
			rtn.append("')\"><INPUT type=radio value=\"");
			rtn.append(level1s.get(i).getMenu_id());
			rtn.append("\" name=\"checkedFunction");
			rtn.append("\" onclick=\"queryActionByFuncNo()\">");
			rtn.append(level1s.get(i).getMenu_name());
			rtn.append("</TD>\r\n</TR>\r\n");
			rtn.append("<tr valign=\"top\" id=\"");
			rtn.append(trId);
			rtn.append("\" style=\"display:none\">\r\n<td>\r\n<table width=\"100%\" border=\"0\" align=\"left\" cellpadding=\"2\" cellspacing=\"0\" id=\"table1\">\r\n");
			level2s = level1s.get(i).getLevel2();
			for (int j = 0; j < level2s.size(); j++) {
				level3s = level2s.get(j).getLevel3();
				imgId = "imgcontroller" + level2s.get(j).getMenu_id();
				trId = "trcontroller" + level2s.get(j).getMenu_id();

				/* ��ɿ�����check�� */
				rtn.append("<TR>\r\n<TD height=21 noWrap class=list_table_tr_odd1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id=\""); // ///
				rtn.append(imgId);
				rtn.append("\" src=\"" + serverName + "/images/close.gif\" width=\"9\" height=\"9\" ");
				if (level3s != null) {
					rtn.append("onClick=\"javascript:changed('");
					rtn.append(imgId);
					rtn.append("','");
					rtn.append(trId);
					rtn.append("')\"");
				}
				rtn.append(")\"><INPUT type=radio value=\"");
				rtn.append(level2s.get(j).getMenu_id());
				rtn.append("\" name=\"checkedFunction");
				rtn.append("\" onclick=\"queryActionByFuncNo()\">");// onChange='queryActionByFuncNo()'>");
				rtn.append(level2s.get(j).getMenu_name());
				rtn.append("</TD>\r\n</TR>\r\n");

				if (level3s != null) {
					/* ��ɹ���check�� */
					rtn.append("<TR id=\"");
					rtn.append(trId);
					rtn.append("\" style=\"display:none\">\r\n<TD height=21 noWrap class=list_table_tr_odd1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT type=radio value=\""); // ///
					rtn.append(level3s.get(0).getMenu_id());
					rtn.append("\" name=\"checkedFunction");
					rtn.append("\" onClick='queryActionByFuncNo()'>");
					rtn.append(level3s.get(0).getMenu_name());

					for (int k = 1; k < level3s.size(); k++) {
						rtn.append("&nbsp;&nbsp;<INPUT type=radio value=\"");
						rtn.append(level3s.get(k).getMenu_id());
						rtn.append("\" name=\"checkedFunction");
						rtn.append("\" onClick='queryActionByFuncNo()'>");
						rtn.append(level3s.get(k).getMenu_name());
						if (k % 3 == 0) {
							rtn.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						}
					}
					rtn.append("</TD>\r\n</TR>\r\n");
				}
			}
			rtn.append("</table>\r\n</td>\r\n</tr>\r\n");

			//			// 添加js代码
			//			rtn.append("<script>\r\n");
			//			
			//			/* ��ɿ�����click���� */
			//			level2s = level1s.get(i).getLevel2();
			//			for (int i1 = 0; i1 < level2s.size(); i1++) {
			//				// ������checked���
			//				checked.append("document.PageForm.controller");
			//				checked.append(level2s.get(i1).getMenu_id());
			//				checked.append(".checked = true;\r\n");
			//
			//				// ������unchecked���
			//				unchecked.append("document.PageForm.controller");
			//				unchecked.append(level2s.get(i1).getMenu_id());
			//				unchecked.append(".checked = false;\r\n");
			//
			//				// ������ɿ�����check���click����
			//				rtn.append("function controller");
			//				rtn.append(level2s.get(i1).getMenu_id());
			//				rtn.append("click(){");
			//				level3s = level2s.get(i1).getLevel3();
			//				if(level3s!=null){
			//					rtn.append("\r\nif (! window.event.srcElement.checked) { \r\n");
			//				
			//					for (int j1 = 0; j1 < level3s.size(); j1++) {
			//						rtn.append("document.PageForm.function");
			//						rtn.append(level3s.get(j1).getMenu_id());
			//						rtn.append(".checked = false;\r\n");
			//	
			//						unchecked.append("document.PageForm.function");
			//						unchecked.append(level3s.get(j1).getMenu_id());
			//						unchecked.append(".checked = false;\r\n");
			//					}
			//					rtn.append("} else if(window.event.srcElement.checked) {\r\n");
			//	
			//					for (int j1 = 0; j1 < level3s.size(); j1++) {
			//						rtn.append("document.PageForm.function");
			//						rtn.append(level3s.get(j1).getMenu_id());
			//						rtn.append(".checked = true;\r\n");
			//	
			//						checked.append("document.PageForm.function");
			//						checked.append(level3s.get(j1).getMenu_id());
			//						checked.append(".checked = true;\r\n");
			//					}
			//					rtn.append("} \r\n");
			//				}
			//				rtn.append("}\r\n\r\n");
			//			}
			//			// ���������˵�check��click����
			//			rtn.append("function menu");
			//			rtn.append(level1s.get(i).getMenu_id());
			//			rtn.append("click(){\r\nif (! window.event.srcElement.checked) { \r\n");
			//			rtn.append(unchecked.toString());
			//			rtn.append("} else if(window.event.srcElement.checked) { \r\n");
			//			rtn.append(checked.toString());
			//			rtn.append("} \r\n}\r\n");
			//			rtn.append("</script>\r\n");

		}

		return rtn.toString();
	}
}
