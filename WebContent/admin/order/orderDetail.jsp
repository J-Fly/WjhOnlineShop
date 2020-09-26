<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 订单详情</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>

<script type="text/javascript">
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
				<strong>订单详情</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/OrderServlet?task=list" class="actionBtn add">订单列表</a>
					订单详情
				</h3>
				<div id="list">
					<form name="action" method="post" action="#">
						<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
							<tr>
								<th width="40" align="center">所属订单</th>
								<th width="80" align="center">商品名称</th>
								<th width="80" align="center">商品描述</th>
								<th width="80" align="center">缩略图</th>
								<th width="80" align="center">数量</th>
								<th width="80" align="center">单价</th>
								<th width="80" align="center">总价</th>
							</tr>
							<tbody id="testBody">
								<c:forEach var="orderItemBean" items="${OrderItemList}">
									<tr id="order${orderItemBean.orderid}">
										<td align="center">${orderItemBean.orderid}</td>
										<td align="center">${orderItemBean.itemname}</td>
										<td align="center">${orderItemBean.itemdescription}</td>
										<td align="center">
											<img style="width: 50px; height: 50px;" alt="" src="${contextPath}/upload/${orderItemBean.itemimg}" />
										</td>
										<td align="center">${orderItemBean.count}</td>
										<td align="center">${orderItemBean.price}</td>
										<td align="center">${orderItemBean.totalprice}</td>
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
