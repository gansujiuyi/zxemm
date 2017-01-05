package com.jiuyi.jyplat.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.uxun.commauth.StringUtil;

public class PageEntity {
	private static Logger log = Logger.getLogger(PageEntity.class);

	private int pageNow = 1;
	private int pageSize = 16;
	private int rowCount = 0;
	private int pageCount = 1;
	private String pagerHtml;

	public String getPagerHtml() {
		return pagerHtml;
	}

	public void setPagerHtml(String pagerHtml) {
		this.pagerHtml = pagerHtml;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setIntRowCount(int intRowCount) {
		this.rowCount = intRowCount;
		this.pageCount = intRowCount % pageSize == 0 ? intRowCount / pageSize : intRowCount / pageSize + 1;
		if (this.pageCount == 0) {
			pageCount = 1;
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 抓取request参数名和参数值
	 * @param request
	 * @param arg   不需要参加转码的参数名称组装串，例如：param1,param2
	 * @return
	 */
	private String getRequestInfo(HttpServletRequest request, String filterName, String... arg) {
		if (request == null)
			return "";
		Enumeration<?> enumeration = request.getParameterNames();
		StringBuffer headInfo = new StringBuffer();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			String[] parameterValues = request.getParameterValues(paramName);
			if (filterName.contains(paramName))
				continue;
			if (paramName.equals("search.minprice") || paramName.equals("search.maxprice")) {
				if (parameterValues[0].contains("￥")) {
					continue;
				}
			}
			for (int i = 0; i < parameterValues.length; i++) {
				String value = parameterValues[i];
				try {
					if (arg == null || arg.length == 0) { //判断是否需要转码
						value = new String(value.getBytes("ISO-8859-1"), "utf-8");
					} else if (!arg[0].contains(paramName)) {
						value = new String(value.getBytes("ISO-8859-1"), "utf-8");
					} else if (arg[0].contains(paramName)) {
						value = URLEncoder.encode(value, "UTF-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				headInfo.append(paramName).append("=").append(value).append("&");
			}
		}
		return headInfo.toString();
	}

	/**
	 * @param baseUrl
	 * @param request
	 * @param arg   不需要参加转码的参数名称组装串，例如：param1,param2
	 * @return
	 */
	public String createPagerHtml(String baseUrl, HttpServletRequest request, String... arg) {
		String paramsStr = getRequestInfo(request, "page.pageNow", arg);
		if (StringUtil.isNotBlank(paramsStr)) {
			paramsStr = paramsStr.substring(0, paramsStr.lastIndexOf("&"));
		}
		baseUrl += "?" + paramsStr;
		String has_more = "...";
		StringBuffer html = new StringBuffer("");
		if (pageCount < 2) {
			return "";
		}
		if (pageNow > 0 && pageNow <= pageCount) {
			html.append(String.format("<a href='%s'>首页</a>", baseUrl + "&page.pageNow=1"));
			if (pageNow > 1) {
				html.append(String.format("<a href='%s'> &lt; 上一页</a>", baseUrl + "&page.pageNow=" + (pageNow - 1)));
			}
			String str_html = "";
			//小于10页时1-10全部显示
			if (pageCount <= 10) {
				int endPage = pageCount;
				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href='%s'>%s</a>", baseUrl + "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}
				if (pageNow != pageCount) {
					html
							.append(String.format("<a href='%s'>下一页 &gt; </a>", baseUrl + "&page.pageNow="
									+ (pageNow + 1)));
				}
			}
			//少于５页的情况
			else if (pageNow - 5 <= 0) {
				int endPage = 0;
				if (pageCount <= 5) {
					endPage = pageCount;
				} else {
					endPage = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);
					endPage = endPage <= 5 ? 5 : endPage;
				}

				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href='%s'>%s</a>", baseUrl + "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}

				if (endPage < pageCount) {
					html.append(has_more); //...
					html
							.append(String.format("<a href='%s'>下一页 &gt; </a>", baseUrl + "&page.pageNow="
									+ (pageNow + 1)));
				}

			}

			else {
				if (pageNow - 5 > 0) {
					for (int page = 1; page <= 2; page++) {
						str_html = String.format("<a href='%s'>%s</a>", baseUrl + "&page.pageNow=" + page, page);
						html.append(str_html);
					}
					html.append(has_more); // ...
				} else {

				}
				int begin = pageNow - 2 <= 0 ? 1 : (pageNow - 2);
				int end = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);

				for (int page = begin; page <= end; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href='%s'>%s</a>", baseUrl + "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}
				if (end < pageCount) {
					//TextUtils.htmlEncode
					html.append(has_more); //...
					html
							.append(String.format("<a href='%s'>下一页 &gt; </a>", baseUrl + "&page.pageNow="
									+ (pageNow + 1)));
				}

			}
			String d = "onkeyup='this.value=this.value.replace(/\\D/g,&apos;&apos;)' onafterpaste='this.value=this.value.replace(/\\D/g,&apos;&apos;)'";
			html.append(String.format("<a href='%s'>末页</a>", baseUrl + "&page.pageNow=" + pageCount));
			html.append("&nbsp&nbsp共" + pageCount + "页");
			html
					.append("&nbsp&nbsp到第&nbsp<input type='text' "
							+ d
							+ " id='toPageTurn' maxlength=5 value='"
							+ pageNow
							+ "' style='width:35px;'>&nbsp页&nbsp<input type='button' onClick='javascript:getPage()' value='确定'>");
			html.append("<script type='text/javascript'>");
			html.append(" var pageCount=" + pageCount + ";");
			html.append(" function getPage(){");
			html.append(" var pageN=$('#toPageTurn').val();");
			html.append(" if(pageN>=pageCount){pageN=pageCount}");
			html.append(" window.location.href=");
			html.append("'" + baseUrl + "&page.pageNow='+pageN;");
			html.append("}");
			html.append("</script>");
		}
		this.pagerHtml = html.toString();
		return html.toString();
	}

	/**
	 * 重载方法，将href属性设置为javascript:void(0);
	 * @param baseUrl
	 * @param request
	 * @param arg   不需要参加转码的参数名称组装串，例如：param1,param2
	 * @return
	 */
	public String createPagerHtml(String baseUrl, HttpServletRequest request, int i) {
		String paramsStr = getRequestInfo(request, "page.pageNow");
		if (StringUtil.isNotBlank(paramsStr)) {
			paramsStr = paramsStr.substring(0, paramsStr.lastIndexOf("&"));
		}
		int step = 5;
		baseUrl += "?" + paramsStr;
		String has_more = "...";
		//String baseUrl="";
		StringBuffer html = new StringBuffer("");
		if (pageCount < 2) {
			return "";
		}
		if (pageNow > 0 && pageNow <= pageCount) {
			if (pageNow > 1) {
				html.append(String.format("<a href = 'javascript:void(0);' name='%s'> &lt; 上一页</span>", baseUrl
						+ "&page.pageNow=" + (pageNow - 1)));
			}
			String str_html = "";
			//小于10页时1-10全部显示
			if (pageCount <= 10) {
				int endPage = pageCount;
				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href= 'javascript:void(0);' name='%s'>%s</a>", baseUrl
								+ "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}
				if (pageNow != pageCount) {
					html.append(String.format("<a href= 'javascript:void(0);' name='%s'>下一页 &gt; </a>", baseUrl
							+ "&page.pageNow=" + (pageNow + 1)));
				}
			}
			//少于５页的情况
			else if (pageNow - 5 <= 0) {
				int endPage = 0;
				if (pageCount <= 5) {
					endPage = pageCount;
				} else {
					endPage = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);
					endPage = endPage <= 5 ? 5 : endPage;
				}

				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href= 'javascript:void(0);' name='%s'>%s</a>", baseUrl
								+ "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}

				if (endPage < pageCount) {
					html.append(has_more); //...
					html.append(String.format("<a href= 'javascript:void(0);' name='%s'>下一页 &gt; </a>", baseUrl
							+ "&page.pageNow=" + (pageNow + 1)));
				}

			}

			else {
				if (pageNow - 5 > 0) {
					for (int page = 1; page <= 2; page++) {
						str_html = String.format("<a href= 'javascript:void(0);' name='%s'>%s</a>", baseUrl
								+ "&page.pageNow=" + page, page);
						html.append(str_html);
					}
					html.append(has_more); // ...
				} else {

				}
				int begin = pageNow - 2 <= 0 ? 1 : (pageNow - 2);
				int end = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);

				for (int page = begin; page <= end; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href= 'javascript:void(0);' name='%s'>%s</a>", baseUrl
								+ "&page.pageNow=" + page, page);
					}
					html.append(str_html);
				}
				if (end < pageCount) {
					//TextUtils.htmlEncode
					html.append(has_more); //...
					html.append(String.format("<a href= 'javascript:void(0);' name='%s'>下一页 &gt; </a>", baseUrl
							+ "&page.pageNow=" + (pageNow + 1)));
				}

			}
		}
		this.pagerHtml = html.toString();
		return html.toString();
	}

	/**
	 * 重载方法，将href属性设置为javascript:void(0);
	 * @param baseUrl
	 * @param request
	 * @param arg   不需要参加转码的参数名称组装串，例如：param1,param2
	 * @return
	 */
	public String createPagerHtml(HttpServletRequest request, String clickStr) {
		if (Utils.isNullData(clickStr)) {
			clickStr = "pageJump";
		}
		String paramsStr = getRequestInfo(request, "page.pageNow");
		if (StringUtil.isNotBlank(paramsStr)) {
			paramsStr = paramsStr.substring(0, paramsStr.lastIndexOf("&"));
		}
		int step = 5;
		String has_more = "...";
		//String baseUrl="";
		StringBuffer html = new StringBuffer("");
		if (pageCount < 2) {
			return "";
		}
		if (pageNow > 0 && pageNow <= pageCount) {
			if (pageNow > 1) {
				html.append(String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:%s\"> &lt; 上一页</a>",
						clickStr + "(" + (pageNow - 1) + ")"));
			}
			String str_html = "";
			//小于10页时1-10全部显示
			if (pageCount <= 10) {
				int endPage = pageCount;
				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class=\"current\">%s</span>", pageNow);
					} else {
						str_html = String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">%s</a>",
								clickStr + "(" + (page) + ")", page);
					}
					html.append(str_html);
				}
				if (pageNow != pageCount) {
					html.append(String.format(
							"<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">下一页 &gt; </a>", clickStr + "("
									+ (pageNow + 1) + ")"));
				}
			}
			//少于５页的情况
			else if (pageNow - 5 <= 0) {
				int endPage = 0;
				if (pageCount <= 5) {
					endPage = pageCount;
				} else {
					endPage = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);
					endPage = endPage <= 5 ? 5 : endPage;
				}

				for (int page = 1; page <= endPage; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">%s</a>",
								clickStr + "(" + (page) + ")", page);
					}
					html.append(str_html);
				}

				if (endPage < pageCount) {
					html.append(has_more); //...
					html.append(String.format(
							"<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">下一页 &gt; </a>", clickStr + "("
									+ (pageNow + 1) + ")"));
				}

			}

			else {
				if (pageNow - 5 > 0) {
					for (int page = 1; page <= 2; page++) {
						str_html = String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">%s</a>",
								clickStr + "(" + (page) + ")", page);
						html.append(str_html);
					}
					html.append(has_more); // ...
				} else {

				}
				int begin = pageNow - 2 <= 0 ? 1 : (pageNow - 2);
				int end = pageNow + 2 >= (pageCount - 1) ? pageCount : (pageNow + 2);

				for (int page = begin; page <= end; page++) {
					if (page == pageNow) {
						str_html = String.format("<span class='current'>%s</span>", pageNow);
					} else {
						str_html = String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">%s</a>",
								clickStr + "(" + (page) + ")", page);
					}
					html.append(str_html);
				}
				if (end < pageCount) {
					html.append(has_more); //...
					html.append(String.format(
							"<a href=\"javascript:void(0);\" onclick=\"javascript:%s\">下一页 &gt; </a>", clickStr + "("
									+ (pageNow + 1) + ")"));
				}

			}
		}
		this.pagerHtml = html.toString();
		return html.toString();
	}

	public static void main(String[] args) {
		PageEntity pager = new PageEntity();
		pager.setPageNow(5);
		pager.setPageCount(5);
		String p_html = pager.createPagerHtml("test.do", null);
		//System.out.println(p_html);
	}

}
