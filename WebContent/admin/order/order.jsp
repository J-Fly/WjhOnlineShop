<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 订单管理</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>

<script type="text/javascript">
	function deliver(orderid) {
		//发起ajax请求，后端趔除，前端要移除对应的行。
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
		<c:import url="/admin/common/left.jsp?type=order"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>订单管理</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/admin/main.jsp" class="actionBtn">返回首页</a>
					订单列表
				</h3>
				<div class="filter">
					<form action="${contextPath}/servlet/OrderServlet?task=searchList" method="post">
						<select name="p_ispay" style="width: 100px;">
							<option value="">是否付款</option>
							<option value="是" ${p_ispay == "是" ? "selected='selected'" : ""}>是</option>
							<option value="否" ${p_ispay == "否" ? "selected='selected'" : ""}>否</option>
						</select>
						&nbsp;&nbsp;用户名：
						<input name="p_username" type="text" class="inpMain" value="${p_username}" size="20" />
						<input name="submit" class="btnGray" type="submit" value="筛选" />
					</form>
				</div>
				<div id="list">
					<form name="action" method="post" action="product.php?rec=action">
						<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
							<tr>
								<th width="40" align="center">编号</th>
								<th width="80" align="center">用户名</th>
								<th width="80" align="center">下单时间</th>
								<th width="80" align="center">是否付款</th>
								<th width="80" align="center">是否发货</th>
								<th width="80" align="center">发货时间</th>
								<th width="80" align="center">发货人</th>
								<th width="80" align="center">操作</th>
							</tr>
							<tbody id="testBody">
								<c:forEach var="orderBean" items="${orderList}">
									<tr id="order${orderBean.orderid}">
										<td align="center">${orderBean.orderid}</td>
										<td align="center">
											<a href="#">${orderBean.ouser}</a>
										</td>
										<td align="center">
											<fmt:formatDate value="${orderBean.odate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td align="center">
											<a href="#">${orderBean.paytype}</a>
										</td>
										<td align="center">
											<c:if test="${orderBean.sendtype=='否'}">${orderBean.sendtype} | <a href="#" style="color: blue;" onclick="deliver(${orderBean.orderid});">发货</a>
											</c:if>
											<c:if test="${orderBean.sendtype=='是'}">${orderBean.sendtype}
											</c:if>
										</td>
										<td align="center">
											<fmt:formatDate value="${orderBean.adate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td align="center">
											<c:if test="${orderBean.auser != 'null'}">${orderBean.auser}</c:if>
										</td>
										<td align="center">
											<a href="${contextPath}/servlet/OrderServlet?task=edit&orderid=${orderBean.orderid}">编辑</a>
											|
											<a href="${contextPath}/servlet/OrderServlet?task=orderDetail&orderid=${orderBean.orderid}">详细</a>
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
