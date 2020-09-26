<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 修改订单信息</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
	function saveData() {
		var formData = jQuery("#orderForm").serializeArray();
		var modifyURL = "${contextPath}/servlet/OrderServlet?task=modify&orderid=${editOrder.orderid}&date=" + Math.random() + "";
		//设置按钮样式为disabled="disabled",禁用按钮
		$("#saveButton").attr("disabled", "disabled");
		$("#saveButton").toggleClass("btnGray");

		//提交修改请求
		jQuery.post(modifyURL, formData, function(jsonData) {
			if (jsonData.flag == false) {
				window.alert(jsonData.message);
			} else {
				window.alert(jsonData.message);
				//将按钮样式还原
				$("#saveButton").removeAttr("disabled");
				$("#saveButton").removeClass("btnGray");
				$("#saveButton").addClass("btn");
				window.location.reload();
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
				<strong>编辑订单信息</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/OrderServlet?task=list" class="actionBtn add">订单列表</a>
					修改订单&nbsp;&nbsp;&nbsp;&nbsp;
				</h3>
				<form id="orderForm" action="#" method="post" enctype="multipart/form-data">
					<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
						<tr>
							<td width="90" align="right">订单编号</td>
							<td>${editOrder.orderid}</td>
							<td width="90" align="right">下单用户名</td>
							<td>${editOrder.ouser}</td>
						</tr>
						<tr>
							<td align="right">是否付款</td>
							<td>${editOrder.paytype}</td>
							<td align="right">下单时间</td>
							<td>
								<fmt:formatDate value="${editOrder.odate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td align="right">是否发货</td>
							<td>${editOrder.sendtype}</td>
							<td align="right">发货时间</td>
							<td>
								<fmt:formatDate value="${editOrder.adate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td align="right">商品种类数</td>
							<td>${editOrder.mctypesize}</td>
							<td align="right">商品数量</td>
							<td>${editOrder.mcsize}</td>
						</tr>
						<tr>
							<td align="right">订单总价</td>
							<td>${editOrder.totalprice}</td>
							<td align="right">订单备注</td>
							<td>${editOrder.totalprice}</td>
						</tr>
						<tr>
							<td align="right">发货人</td>
							<td>${editOrder.auser}</td>
							<td align="right">收货人</td>
							<td>${editOrder.getname}</td>
						</tr>
						<tr>
							<td align="right">收货地址</td>
							<td>
								<input style="width: 200px;" type="text" id=getaddress name="getaddress" value="${editOrder.getaddress}" size="80" class="inpMain" />
							</td>
							<td align="right">邮编</td>
							<td>
								<input style="width: 200px;" type="text" id=getpostcode name="getpostcode" value="${editOrder.getpostcode}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">收货人电话</td>
							<td>
								<input style="width: 200px;" type="text" id=getphone name="getphone" value="${editOrder.getphone}" size="80" class="inpMain" />
							</td>
							<td align="right">收货人邮箱</td>
							<td>
								<input style="width: 200px;" type="text" id=getemail name="getemail" value="${editOrder.getemail}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3">
								<input style="margin-left: 132px" id="saveButton" name="saveButton" class="btn" type="button" value="保存数据" onclick="saveData();" />
								<input style="margin-left: 132px" name="reset" class="btnGray" type="reset" value="重置输入" />
								<input style="margin-left: 132px" name="cannel" class="btnGray" type="button" value="取消操作" onclick="window.history.back();" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<div class="clear"></div>
	</div>
</body>
</html>
