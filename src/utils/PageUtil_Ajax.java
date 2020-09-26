package utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class PageUtil_Ajax {
	public static final String Text = "text";
	public static final String BbsText = "bbstext";
	public static final String BbsImage = "bbsimage";
	private int pageSize = 20;
	private int rsCount = 0;
	private int pageCount = 0;
	private int currentPage = 1;
	private HttpServletRequest request = null;

	public PageUtil_Ajax(HttpServletRequest request) {
		this.request = request;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRsCount(int rsCount) {
		this.rsCount = rsCount;
	}

	public int getPageCount() {
		this.pageCount = (this.rsCount - 1) / this.pageSize + 1;
		return this.pageCount;
	}

	public int getCurrentPage() {
		if (this.request.getParameter("currentPage") != null) {
			this.currentPage = Integer.parseInt(this.request.getParameter("currentPage"));
		}
		return this.currentPage;
	}

	public String createPageTool(String flag) {
		StringBuffer str = new StringBuffer();
		String temp = "";
		String url = this.getParamUrl();
		// String url = this.request.getRequestURI() + "?";
		int ProPage = this.currentPage - 1;
		int Nextpage = this.currentPage + 1;
		// 文字的分页
		if (flag.equals(PageUtil_Ajax.Text)) {
			str.append("<form method='post' name='pageform' action=''>");
			str.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			str.append("<tr>");
			str.append("<td width='3%'>&nbsp;</td>");
			str.append("<td height='26'>");
			str.append("共有记录" + this.rsCount + "条&nbsp;&nbsp;&nbsp;");
			str.append("共" + this.pageCount + "页&nbsp;&nbsp;&nbsp;");
			str.append("每页" + this.pageSize + "记录&nbsp;&nbsp;&nbsp;");
			str.append("现在" + this.currentPage + "/" + this.pageCount + "页");
			str.append("</td><td>");
			if (this.currentPage > 1) {
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=1'>首页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + ProPage + "'>上一页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			} else {
				str.append("首页");
				str.append("&nbsp;&nbsp;&nbsp;");
				str.append("上一页");
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			if (this.currentPage < this.pageCount) {
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + Nextpage + "'>下一页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			} else {
				str.append("下一页");
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			if (this.pageCount > 1 && this.currentPage != this.pageCount) {
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + pageCount + "'>尾页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			} else {
				str.append("尾页");
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			str.append("转到");
			str.append("<select name='currentPage' onchange='javascript:ChangePage(this.value);'>");
			for (int j = 1; j <= pageCount; j++) {
				str.append("<option value='" + j + "'");
				if (currentPage == j) {
					str.append("selected");
				}
				str.append(">");
				str.append("" + j + "");
				str.append("</option>");
			}
			str.append("</select>页");
			str.append("</td><td width='3%'>&nbsp;</td></tr></table>");
			str.append("<script language='javascript'>");
			str.append("function ChangePage(testpage){");
			str.append("document.pageform.action='" + url + "&currentPage='+testpage+'';");
			str.append("document.pageform.submit();");
			str.append("}");
			str.append("</script>");
			str.append("</form>");
		} else if (flag.equals(PageUtil_Ajax.BbsText)) {
			/**
			 * 论坛形式的分页[直接以数字方式体现]
			 */
			str.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			str.append("<tr>");
			str.append("<td width='3%'>&nbsp;</td>");
			str.append("<td height='26'>");
			str.append("记录" + this.rsCount + "条&nbsp;&nbsp;");
			str.append("共" + this.pageCount + "页&nbsp;&nbsp;");
			str.append("每页" + this.pageSize + "记录&nbsp;&nbsp;");
			str.append("现在" + this.currentPage + "/" + this.pageCount + "页");
			str.append("</td><td>");
			// 设定是否有首页的链接
			if (this.currentPage > 1) {
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=1'>首页</a>");
				str.append("&nbsp;&nbsp;");
			}
			// 设定是否有上一页的链接
			if (this.currentPage > 1) {
				str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + ProPage + "'>上一页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			// 如果总页数只有10的话
			if (this.pageCount <= 10) {
				for (int i = 1; i <= this.pageCount; i++) {
					if (this.currentPage == i) {
						str.append("<font color=red>[" + i + "]</font>&nbsp;&nbsp;");
					} else {
						str.append("<a href='" + url + "&currentPage=" + i + "'>" + i + "</a>&nbsp;&nbsp;");
					}
				}
			} else {
				// 说明总数有超过10页
				// 制定特环的开始页和结束页

				int endPage = this.currentPage + 4;
				if (endPage > this.pageCount) {
					endPage = this.pageCount;
				}
				int startPage = 0;
				if (this.pageCount >= 8 && this.currentPage >= 8) {
					startPage = this.currentPage - 5;
				} else {
					// 表示从第一页开始算
					startPage = 1;
				}
				System.out.println(startPage);
				System.out.println(endPage);
				for (int i = startPage; i <= endPage; i++) {
					if (this.currentPage == i) {
						str.append("<font color=red>[" + i + "]</font>&nbsp;&nbsp;");
					} else {
						str.append("<a href='" + url + "&currentPage=" + i + "'>" + i + "</a>&nbsp;&nbsp;");
					}
				}
			}
			// 设定是否有下一页的链接
			if (this.currentPage < this.pageCount) {
				//str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + Nextpage + "'>下一页</a>");
				str.append("<a class=\"highlight\" href='javascript:void(0);' onclick='javascript:loadUserData("+Nextpage+");'>下一页</a>");
				str.append("&nbsp;&nbsp;");
			}
			// 设定是否有尾页的链接
			if (this.pageCount > 1 && this.currentPage != this.pageCount) {
				//str.append("<a class=\"highlight\" href='" + url + "&currentPage=" + pageCount + "'>尾页</a>");
				str.append("<a class=\"highlight\" href='javascript:void(0);' onclick='javascript:loadUserData("+pageCount+");'>尾页</a>");
				str.append("&nbsp;&nbsp;");
			}

			str.append("</td><td width='3%'>&nbsp;</td></tr></table>");
		} else if (flag.equals(PageUtil_Ajax.BbsImage)) {
			/**
			 * 论坛形式的分页[以图片的方式体现]
			 */
			// 设定分页显示的CSS
			str.append("<style>");
			//str.append("BODY {FONT-SIZE: 12px;FONT-FAMILY:宋体;WIDTH: 60%; PADDING-LEFT: 25px;}");
			str.append(
					"DIV.meneame {PADDING-RIGHT: 3px; PADDING-LEFT: 3px; FONT-SIZE: 80%; PADDING-BOTTOM: 3px; MARGIN: 3px; COLOR: #ff6500; PADDING-TOP: 3px; TEXT-ALIGN: center}");
			str.append(
					"DIV.meneame A {BORDER-RIGHT: #ff9600 1px solid; PADDING-RIGHT: 7px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #ff9600 1px solid; PADDING-LEFT: 7px; BACKGROUND-IMAGE: url('"
							+ this.request.getContextPath()
							+ "/meneame.jpg'); PADDING-BOTTOM: 5px; BORDER-LEFT: #ff9600 1px solid; COLOR: #ff6500; MARGIN-RIGHT: 3px; PADDING-TOP: 5px; BORDER-BOTTOM: #ff9600 1px solid; TEXT-DECORATION: none}");
			str.append(
					"DIV.meneame A:hover {BORDER-RIGHT: #ff9600 1px solid; BORDER-TOP: #ff9600 1px solid; BACKGROUND-IMAGE: none; BORDER-LEFT: #ff9600 1px solid; COLOR: #ff6500; BORDER-BOTTOM: #ff9600 1px solid; BACKGROUND-COLOR: #ffc794}");
			str.append(
					"DIV.meneame SPAN.current {BORDER-RIGHT: #ff6500 1px solid; PADDING-RIGHT: 7px; BORDER-TOP: #ff6500 1px solid; PADDING-LEFT: 7px; FONT-WEIGHT: bold; PADDING-BOTTOM: 5px; BORDER-LEFT: #ff6500 1px solid; COLOR: #ff6500; MARGIN-RIGHT: 3px; PADDING-TOP: 5px; BORDER-BOTTOM: #ff6500 1px solid; BACKGROUND-COLOR: #ffbe94}");
			str.append(
					"DIV.meneame SPAN.disabled {BORDER-RIGHT: #ffe3c6 1px solid; PADDING-RIGHT: 7px; BORDER-TOP: #ffe3c6 1px solid; PADDING-LEFT: 7px; PADDING-BOTTOM: 5px; BORDER-LEFT: #ffe3c6 1px solid; COLOR: #ffe3c6; MARGIN-RIGHT: 3px; PADDING-TOP: 5px; BORDER-BOTTOM: #ffe3c6 1px solid}");
			str.append("</style>");
			str.append("<div class=\"meneame\">");
			// 判定是否有上一页
			if (this.currentPage > 1) {
				str.append("<a href='" + url + "&currentPage=1' hidefocus=\"true\">首页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
				str.append("<a href='" + url + "&currentPage=" + ProPage + "' hidefocus=\"true\">上一页</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			} else {
				str.append("<span class=\"disabled\">首页</span>");
				str.append("&nbsp;&nbsp;");
				str.append("<span class=\"disabled\">上一页</span>");
				str.append("&nbsp;&nbsp;");
			}
			// 显示中间的图片
			if (this.pageCount <= 10) {
				for (int i = 1; i <= this.pageCount; i++) {
					if (this.currentPage == i) {
						str.append("<span class=\"current\">" + i + "</span>");
					} else {
						str.append("<a href='" + url + "&currentPage=" + i + "' hidefocus=\"true\">" + i + "</a>&nbsp;&nbsp;");
					}
				}
			} else {
				// 说明总数有超过10页
				// 制定特环的开始页和结束页
				int endPage = this.currentPage + 4;
				if (endPage > this.pageCount) {
					endPage = this.pageCount;
				}
				int startPage = 0;
				if (this.pageCount >= 8 && this.currentPage >= 8) {
					startPage = this.currentPage - 5;
				} else {
					// 表示从第一页开始算
					startPage = 1;
				}
				System.out.println(startPage);
				System.out.println(endPage);
				for (int i = startPage; i <= endPage; i++) {
					if (this.currentPage == i) {
						str.append("<span class=\"current\">" + i + "</span>");
					} else {
						str.append("<a href='" + url + "&currentPage=" + i + "' hidefocus=\"true\">" + i + "</a>&nbsp;&nbsp;");
					}
				}
			}

			// 判断下一页和尾页
			if (this.currentPage < this.pageCount) {
				if (this.currentPage < this.pageCount - 10) {
					str.append("...");
					str.append("<a href='" + url + "&currentPage=" + (this.pageCount - 1) + "' hidefocus=\"true\">" + (this.pageCount - 1) + "</a>&nbsp;&nbsp;");
					str.append("<a href='" + url + "&currentPage=" + this.pageCount + "' hidefocus=\"true\">" + this.pageCount + "</a>&nbsp;&nbsp;");
				}

				str.append("<a href='" + url + "&currentPage=" + Nextpage + "' hidefocus=\"true\">下一页</a>");
				str.append("&nbsp;&nbsp;");
			} else {
				str.append("<span class=\"disabled\">下一页</span>");
				str.append("&nbsp;&nbsp;");
			}
			if (this.pageCount > 1 && this.currentPage != this.pageCount) {
				str.append("<a href='" + url + "&currentPage=" + pageCount + "' hidefocus=\"true\">尾页</a>");
				str.append("&nbsp;&nbsp;");
			} else {
				str.append("<span class=\"disabled\">尾页</span>");
				str.append("&nbsp;&nbsp;");
			}
			str.append("</div>");
		}
		return str.toString();
	}

	private String getParamUrl() {
		String result = null;
		String pageUrl = request.getRequestURI();
		Enumeration<String> enumeration = request.getParameterNames();
		String totalParamUrl = "";
		while (enumeration.hasMoreElements()) {
			String param_name = enumeration.nextElement();
			String param_value = request.getParameter(param_name);
			if (param_name != null && !param_name.equals("") && !param_name.equals("currentPage") && !param_value.equals("")) {
				if (totalParamUrl.equals("")) {
					totalParamUrl = param_name + "=" + param_value;
				} else {
					totalParamUrl = totalParamUrl + "&" + param_name + "=" + param_value;
				}
			}
		}
		if (pageUrl.indexOf("?") > -1) {
			result = pageUrl + "&" + totalParamUrl;
		} else {
			result = pageUrl + "?" + totalParamUrl;
		}
		//System.out.println(result);
		return result;
	}
}
