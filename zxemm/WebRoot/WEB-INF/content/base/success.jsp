<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + 
		":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><s:text name='domain.title'/></title>
	<link href="<%=path %>/css/basic.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div class="content">
	
	  <div class="rightmain">
			<div class="rightwz">
				<div class="rightwzimg"><img src="<%=path %>/images/index_09.gif" /></div>
				<!-- <div class="rightweizi">&nbsp;&nbsp;您所在的位置:成功页面</div> -->
			</div>
			<div class="rightmain">
              <table width="808" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center" valign="top" class="tablerbtbg"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="20%" align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="18" align="left" valign="middle"><img src="<%=path %>/images/index_34.gif" width="13" height="8" /></td>
                              <td align="left" valign="middle" class="dbtwz">提示</td>
                          </tr>
                        </table></td>
                        <td align="right" valign="middle">&nbsp;</td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td align="center" valign="top" class="tablerbtbgbk">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="searchtb">
                    <tr>
                      <td  align="center" ><table width="400" border="0" cellpadding="0" cellspacing="0" >
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td align="center"><img src="<%=path %>/images/success_t.gif" width="118" height="28" /></td>
                        </tr>
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td align="center"><img src="<%=path %>/images/success_img.gif" width="100" height="100" /></td>
                        </tr>
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                        </table>
                          <table width="750" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                          </table><br>
                       </td>
                    </tr>
                  </table></td>
                </tr>
              </table>
	    </div>
		</div>
  </div>
</body>
</html>
