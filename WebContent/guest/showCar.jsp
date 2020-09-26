<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

.gw_num {
	border: 1px solid #dbdbdb;
	width: 110px;
	line-height: 26px;
	overflow: hidden;
}

.gw_num em {
	display: block;
	height: 26px;
	width: 26px;
	float: left;
	color: #7A7979;
	border-right: 1px solid #dbdbdb;
	text-align: center;
	cursor: pointer;
}

.gw_num .num {
	display: block;
	float: left;
	text-align: center;
	width: 52px;
	font-style: normal;
	font-size: 14px;
	line-height: 24px;
	border: 0;
}

.gw_num em.add {
	float: left;
	border-right: 0;
	border-left: 1px solid #dbdbdb;
}
</style>
<script type="text/javascript" src="${contextPath}/js/boxOver.js"></script>
<script language=JavaScript>
	function date() {
		var today = new Date();
		var strDate = (today.getYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate());

		return strDate;
	}

	$(document).ready(function() {
		//加的效果
		$(".add").click(function() {
			var n = $(this).prev().val();
			var num = parseInt(n) + 1;
			if (num == 0) {
				return;
			}
			$(this).prev().val(num);
			var itemid=$(this).prev().attr("name");
			var newCount=$(this).prev().val();
			updateURL="${contextPath}/servlet/CartServlet?task=update&date="+Math.random()+"";
			var paramData={
			"itemid":		itemid,
			"newCount":		newCount
			};
			jQuery.post(updateURL,paramData,function(jsonData){
				var flag=jsonData.flag;
				if (flag==true) {
					var cartBean=jsonData.data;
					var totalCount=cartBean.totalCount;
					var totalPrice_format=cartBean.totalPrice_format;			
					window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
					window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
					//页面直接刷新
					window.location.reload();
				}else {
					window.alert(jsonData.message);
				}
			},"json");
		});
		//减的效果
		$(".jian").click(function() {
			var n = $(this).next().val();
			var num = parseInt(n) - 1;
			if (num == 0) {
				return;
			}
			$(this).next().val(num);
			var itemid=$(this).next().attr("name");
			var newCount=$(this).next().val();
			updateURL="${contextPath}/servlet/CartServlet?task=update&date="+Math.random()+"";
			var paramData={
			"itemid":		itemid,
			"newCount":		newCount
			};
			jQuery.post(updateURL,paramData,function(jsonData){
				var flag=jsonData.flag;
				if (flag==true) {
					var cartBean=jsonData.data;
					var totalCount=cartBean.totalCount;
					var totalPrice_format=cartBean.totalPrice_format;			
					window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
					window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
					//页面直接刷新
					window.location.reload();
				}else {
					window.alert(jsonData.message);
				}
			},"json");
		});
	});
	
	
	
	function clearCart() {
		var confirmFlag=window.alert("是否清空购物车中所有的商品？");
		clearURL="${contextPath}/servlet/CartServlet?task=clear&date="+Math.random()+"";
		jQuery.get(clearURL,function(jsonData){
			var flag=jsonData.flag;
			if (flag==true) {
				window.parent.document.getElementById("p_totalCount").innerHTML="商品数量：0 件"; 
				window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额：0.00 ￥";
				window.alert(jsonData.message);
				//页面直接刷新
				window.location.reload();
			}else {
				window.alert(jsonData.message);
			}
		},"json");
	}
	
	function deleteCart(id,name) {
		var confirmFlag=window.confirm("是否移除商品"+name+"？");
		deleteURL="${contextPath}/servlet/CartServlet?task=delete&itemid="+id+"&date="+Math.random()+"";
		if (confirmFlag==true) {
			jQuery.get(deleteURL,function(jsonData){
				var flag=jsonData.flag;
				if (flag==true) {
					var cartBean=jsonData.data;
					var totalCount=cartBean.totalCount;
					var totalPrice_format=cartBean.totalPrice_format;			
					window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
					window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
//	 				window.alert("成功移除商品"+name);
					//页面直接刷新
					window.location.reload();
				}else {
					window.alert(jsonData.message);
				}
			},"json");
		}
		
	}
	
	function updateCount(thisObj,eventObj) {
		var keyCode=eventObj.keyCode;
		if (keyCode==13) {
			 var newCount=thisObj.value;
			 var itemid=thisObj.name;
// 			 window.alert("新的数量："+newCount+"  更新的id："+itemid);
			 updateURL="${contextPath}/servlet/CartServlet?task=update&date="+Math.random()+"";
			 var paramData={
				"itemid":		itemid,
				"newCount":		newCount
			 };
			 jQuery.post(updateURL,paramData,function(jsonData){
					var flag=jsonData.flag;
					if (flag==true) {
						var cartBean=jsonData.data;
						var totalCount=cartBean.totalCount;
						var totalPrice_format=cartBean.totalPrice_format;			
						window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
						window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
						//页面直接刷新
						window.location.reload();
					}else {
						window.alert(jsonData.message);
					}
				},"json");
 		}
	}
	
	
	function saveData() {
		 updateURL="${contextPath}/servlet/OrderServlet?task=newOrder&date="+Math.random()+"";
		 if (${cartBean.itemList.size()}==0) {
			 window.alert("购物车里没有商品，不可提交");
		 }else {
			 var msg=document.getElementById("orderTemp").value;
			 var paramData={
				"msg":		msg
			 };
			 jQuery.post(updateURL,paramData,function(jsonData){
					var flag=jsonData.flag;
					if (flag==true) {
						var cartBean=jsonData.data;
						var totalCount=cartBean.totalCount;
						var totalPrice_format=cartBean.totalPrice_format;			
						window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
						window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
						var url = "${contextPath}/servlet/OrderServlet?task=getOrderList&uid=${regUserBean.username}&date=" + Math.random() + "";
						var frmright=window.top.frames["frmright"];
						frmright.window.location.href = url;
					}else {
						window.alert(jsonData.message);
					}
				},"json");
		 }
 		}
</script>
</head>
<body>
	<div id="#cart_main_container">
		<table cellpadding="0" cellspacing="0" align="center" width=100% height="400px" bgcolor="#e9e7e7">
			<tr>
				<!-- 				<td width=15%></td> -->
				<%-- 				<c:if test="${!empty sessionScope.user.username}"> --%>

				<td class="cart_center_content" width=100% height=300px>
					<div class="cart_center_content">
						<div class="center_title_bar">我的购物车</div>
						<div class="cart_prod_box_big">
							<div class="cart_top_prod_box_big"></div>
							<div class="cart_center_prod_box_big">
								<table class="cratTable">
									<%
										SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										String tm = matter.format(new Date());
										Object s = session.getAttribute("list");
										SimpleDateFormat mat = new SimpleDateFormat("yyyyMMdd");
										String ornum = mat.format(new Date());
										int ra = (int) (1000 + Math.random() * 9000);
									%>

									<c:if test="${cartBean.itemList.size() == 0}">
										<p style="padding-bottom: 10px; background-color: rgb(247, 243, 243); font-size: 16px; color: red;">购物车里面没有商品！</p>
									</c:if>
									<c:if test="${cartBean.itemList.size() != 0}">
										<tr>
											<td width="120">商品名称</td>
											<td width="124">商品类别</td>
											<td width="60">商品数量</td>
											<td width="60">商品价格</td>
											<td width="119">操作</td>
										</tr>
										<c:forEach var="itemBean" items="${cartBean.itemList}">
											<tr>
												<td width="147">${itemBean.name}</td>
												<td width="144">${itemBean.maxname}/${itemBean.minname}</td>
												<td style="text-align: center;" width="124">
													<div class="gw_num" style="margin-left: 12px;">
														<em class="jian">-</em>
														<input type="text" id="singleCount${itemBean.id}" name="${itemBean.id}" value="${itemBean.singleCount}" class="num" onkeyup="updateCount(this,event);" />
														<em class="add">+</em>
													</div>
												</td>
												<td width="119">${itemBean.singlePrice}￥</td>
												<td width="119">
													<a href="javascript:void(0);" onclick="deleteCart(${itemBean.id},'${itemBean.name}');">移除</a>
												</td>
											</tr>
										</c:forEach>
										<tr>
											<td width="147" colspan="2">合计：</td>
											<td width="144">${cartBean.totalCount}</td>
											<td width="119" colspan="2">${cartBean.totalPrice_format}￥</td>
										</tr>
									</c:if>
								</table>

								<table style="padding-top: 20px;">
									<tr>
										<td>
											<form action="#" method="post" name="myform">
												<table>
													<tr>
														<td></td>
														<td>
															买家备注：
															<input type="text" id="orderTemp" value="无" />
														</td>
														<td>
															<input type="button" value="提交订单" onclick="saveData();" />
														</td>
													</tr>
												</table>
											</form>
										</td>
										<td width=140>
											&nbsp;
											<input type="button" value="清空购物车" onclick="clearCart();" />
										</td>
										<td>
											<form action="#">
												<input type="button" value="继续购物" onclick="window.top.location.href='${contextPath}/servlet/MainServlet?task=guestMainPage'"/>
											</form>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div></div>
					</div>
				</td>
				<%-- 				</c:if> --%>
				<%-- 				<c:if test="${empty sessionScope.user.username}"> --%>
				<!-- 					<td class="center_content" width=100% height=400px align=center> -->
				<!-- 						<table bgcolor="#e9e7e7"> -->
				<!-- 							<tr> -->
				<!-- 								<td> -->
				<!-- 									<h3>对不起，还未登录，请登录</h3> -->
				<!-- 									<h3> -->
				<!-- 										正在跳转到登录页面，若没有跳转请 -->
				<!-- 										<a href=login.jsp>点击这里！</a> -->
				<!-- 									</h3> -->
				<%
					/*response.setHeader("refresh", "3;url=login.jsp");*/
				%>
				<!-- 								</td> -->
				<!-- 							</tr> -->
				<!-- 						</table> -->
				<!-- 					</td> -->

				<%-- 				</c:if> --%>
			</tr>
		</table>
	</div>

</body>
</html>