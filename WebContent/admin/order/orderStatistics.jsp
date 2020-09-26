<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 订单统计</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>

<script type="text/javascript">
	function deliver(orderid) {
		var deliverURL = "${contextPath}/servlet/OrderServlet?task=deliver&orderid=" + orderid + "&date=" + Math.random() + "";
		jQuery.get(deliverURL, function(jsonData) {
			var flag = jsonData.flag;
			var message = jsonData.message;
			if (flag == true) {
				window.alert(message);
				window.location.reload();
			} else {
				window.alert(message);
			}
		}, "json");
	}
</script>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=order_count"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>订单统计</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/admin/main.jsp" class="actionBtn">返回首页</a>
					订单统计
				</h3>
				<div class="filter">
					<form action="${contextPath}/servlet/OrderServlet?task=statistics" method="post">
						&nbsp;订单日期：<input style="width: 150px; font-size: 14px;" type="date" name=p_odate id="p_odate" size="50" value="${p_odate}" class="inpMain" />
						&nbsp;&nbsp;用户名：
						<input name="p_username" type="text" class="inpMain" value="${p_username}" size="20" />
						<input name="submit" class="btnGray" type="submit" value="筛选" />
					</form>
				</div>
				<div id="list">
					<form name="action" method="post" action="product.php?rec=action">
						<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
							<tr>
								<th width="40" align="center">用户名</th>
								<th width="80" align="center">真实姓名</th>
								<th width="80" align="center">日期</th>
								<th width="80" align="center">已付款订单</th>
								<th width="80" align="center">未付款订单</th>
								<th width="80" align="center">已发货订单</th>
								<th width="80" align="center">未发货订单</th>
								<th width="80" align="center">操作</th>
							</tr>
							<tbody id="testBody">
								<c:forEach var="orderBean" items="${user_Orders}">
									<tr>
										<td align="center">
											<a href="#">${orderBean.ouser}</a>
										</td>
										<td align="center">${orderBean.getname}</td>
										<td align="center">${orderBean.odate}</td>
										<td align="center">
											<a href="#">${orderBean.pay_count}</a>
										</td>
										<td align="center">
											<a href="#">${orderBean.nopay_count}</a>
										</td>
										<td align="center">
											<a href="#">${orderBean.send_count}</a>
										</td>
										<td align="center">
											<a href="#">${orderBean.nosend_count}</a>
										</td>
										<td align="center">
											<a style="color: blue;" href="${contextPath}/servlet/OrderServlet?task=currentDayOrders&ouser=${orderBean.ouser}&odate=${orderBean.odate}">查看当日所有订单</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
				<div class="clear"></div>
				<div class="pager">${pageTool}</div>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<div class="clear"></div>
	</div>
</body>
</html>
