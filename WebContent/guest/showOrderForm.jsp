<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/guest/style.css" />
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>

<style>
.main_iframe {
	Z-INDEX: 1;
	VISIBILITY: inherit;
	WIDTH: 100%;
	HEIGHT: 92%
}

.cratTable {
	border-collapse: collapse;
	margin: 0 auto;
	text-align: center;
}

.cratTable td, .cratTable th {
	border: 1px solid #cad9ea;
	color: #666;
	height: 30px;
}

.cratTable tr:nth-child(odd) {
	background: #fff;
}

.cratTable tr:nth-child(even) {
	background: #F5FAFA;
}
</style>

<script type="text/javascript">
	function payOrder(orderid) {
		payURL="${contextPath}/servlet/OrderServlet?task=payOrder&orderid="+orderid+"&date="+Math.random()+"";
		jQuery.post(payURL,function(jsonData){
				var flag=jsonData.flag;
				if (flag==true) {
					window.alert(jsonData.message);
					var frmright=window.top.frames["frmright"];
					frmright.window.location.reload();
				}else {
					window.alert(jsonData.message);
				}
			},"json");
	}
</script>
</head>
<body>

	<div id="#cart_main_container">
		<table border="0" bgcolor="#e9e7e7" align="center" width=100%>
			<tr>
				<td class="cart_center_content" width=100% height=400px>
					<%-- 					<c:if test="${empty regUserBean}"> --%>
					<!-- 						<table bgcolor="#e9e7e7" width=100% align=center> -->
					<!-- 							<tr> -->
					<!-- 								<td align=center> -->
					<!-- 									<h3>对不起，还未登录，请登录</h3> -->
					<!-- 									<h3> -->
					<!-- 										正在跳转到登录页面，若没有跳转请 -->
					<%-- 										<a href="${contextPath}/servlet/MainServlet?task=guestMainPage">点击这里！</a> --%>
					<!-- 									</h3> -->
					<!-- 								</td> -->
					<!-- 							</tr> -->
					<!-- 						</table> -->
					<%-- 						<% --%>
					<!--  							response.setHeader("refresh", "3;url=login.jsp"); -->
					<%-- 						%> --%>
					<%-- 					</c:if> --%>
					<c:if test="${!empty regUserBean}">
						<div class="cart_center_content">
							<div class="center_title_bar">我的订单</div>
							<div class="cart_prod_box_big">
								<div class="cart_top_prod_box_big"></div>
								<div class="cart_center_prod_box_big">
									<table class="cratTable">
										<c:if test="${empty orderList}">
											<td colspan="5">没有您的订单信息！</td>
										</c:if>
										<c:if test="${!empty orderList}">
											<tr>
												<td width="100">订单号</td>
												<td width="154">付款时间</td>
												<td width="154">发货时间</td>
												<td width="80">订单总金额</td>
												<td width="60">付款</td>
												<td width="60">发货</td>
												<td width="60">操作</td>
											</tr>
											<c:forEach var="orderBean" items="${orderList}">
												<tr>
													<td width="100">${orderBean.orderid}</td>
													<td width="154">
														<fmt:formatDate value="${orderBean.odate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td width="154">
														<fmt:formatDate value="${orderBean.adate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td width="80">${orderBean.totalprice}</td>
													<td width="60">${orderBean.paytype}</td>
													<td width="60">${orderBean.sendtype}</td>
													<td width="60">
														<c:if test="${orderBean.paytype=='否'}">
															<input type="button" value="付款" onclick="payOrder(${orderBean.orderid});" />
														</c:if>
														<c:if test="${orderBean.paytype=='是'}">
															已付款
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</table>
								</div>
								<!-- 								<div class="cart_bottom_prod_box_big"></div> -->
								<div class="pager">${pageTool}</div>
							</div>
						</div>
					</c:if>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>
