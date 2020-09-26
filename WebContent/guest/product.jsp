<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品信息</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/guest/style.css" />
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	function addToCart(itemid) {
		var addURL="${contextPath}/servlet/CartServlet?task=add&itemid="+itemid+"&date="+Math.random()+"";
		jQuery.get(addURL,function(jsonData){
			var flag=jsonData.flag;
			if (flag==true) {
				var cartBean=jsonData.data;
				var totalCount=cartBean.totalCount;
				var totalPrice_format=cartBean.totalPrice_format;			
// 				self.top.location.reload();
// 				window.top.location.reload();
// 				console.log(window.parent.document.getElementById("p_totalCount"));
				window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
				window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
				window.alert(jsonData.message);
			}else {
				window.alert(jsonData.message);
			}
		},"json");
	}
	
	function showDetail(itemid) {
		var URL="${contextPath}/servlet/ItemServlet?task=showDetail&itemid="+itemid+"&date="+Math.random()+"";
		jQuery.get(URL,function(jsonData){
			var flag=jsonData.flag;
			if (flag==true) {
				window.location.href = "${contextPath}/guest/showdetails.jsp";
			}else {
				window.alert(jsonData.message);
			}
		},"json");
	}
</script>
</head>

<body>
	<div class="center_content">
		<div class="center_title_bar">商品列表</div>
		<c:forEach var="itemBean" items="${itemList}">
			<div class="prod_box">
				<div class="top_prod_box"></div>
				<div class="center_prod_box">
					<div class="product_title">
						<a href="javascript:void(0);" target="_top">${itemBean.name}</a>
					</div>
					<div class="product_img">
						<a href="javascript:void(0);" target="_top">
							<img style="width: 170px; height: 154px;" src="${contextPath}/upload/${itemBean.filepath}" alt="${itemBean.filename}" title="" border="0" />
						</a>
					</div>
					<div class="prod_price">
						<span class="price">${itemBean.price}￥</span>
					</div>
				</div>
				<div class="bottom_prod_box"></div>
				<div class="prod_details_tab">
					<a href="javascript:void(0);" title="header=[添加到购物车] body=[&nbsp;] fade=[on]" target="_top" onclick="addToCart(${itemBean.id});">
						<img src="${contextPath}/css/guest/images/cart.gif" alt="添加到购物车" title="添加到购物车" border="0" class="left_bt" />
					</a>
					<a href="javascript:void(0);" onclick="showDetail(${itemBean.id});" class="prod_details" target="_top">详细</a>
				</div>
			</div>
		</c:forEach>
		<div style="clear: both;">
			<c:if test="${empty itemList==false}">${pageTool}</c:if>
		</div>
	</div>
</body>
</html>
