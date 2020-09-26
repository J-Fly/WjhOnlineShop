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
<script type="text/javascript" src="${contextPath}/js/boxOver.js"></script>
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
		});
		//减的效果
		$(".jian").click(function() {
			var n = $(this).next().val();
			var num = parseInt(n) - 1;
			if (num == 0) {
				return;
			}
			$(this).next().val(num);
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
	
</script>

</head>
<body>

	<div id="main_container">
		<div class="top_bar"></div>
		<div id="header">

			<div id="divstr">
				<br />
				<h2>欢迎来到网上商城</h2>
				<h3>
					<script language=JavaScript>
						var today = new Date();
						var strDate = (today.getYear() + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日");
						/*var hh = today.getHours();
						 if(hh<10) hh = '0' + hh;
						 var mm = today.getMinutes();
						 if(mm<10) mm = '0' + mm;
						 var ss = today.getSeconds();
						 if(ss<10) ss = '0' + ss;
						 */
						var n_day = today.getDay();
						var ww;

						switch (n_day) {
						case 0: {
							ww = "星期日";
						}
							break;
						case 1: {
							ww = "星期一";
						}
							break;
						case 2: {
							ww = "星期二";
						}
							break;
						case 3: {
							ww = "星期三";
						}
							break;
						case 4: {
							ww = "星期四";
						}
							break;
						case 5: {
							ww = "星期五";
						}
							break;
						case 6: {
							ww = "星期六";
						}
							break;
						case 7: {
							ww = "星期日";
						}
							break;
						}
						strDate = "今天是:" + ww + "</font>";
						document.write(strDate);
					</script>
				</h3>
			</div>
			<!-- end of oferte_content-->
		</div>
		<script language=JavaScript>
			function date() {
				var today = new Date();
				var strDate = (today.getYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate());

				return strDate;
			}
		</script>
		<div id="main_content">
			<div id="menu_tab">
				<div class="left_menu_corner"></div>
				<ul class="menu">
					<li>
						<a href="${contextPath}/servlet/MainServlet?task=guestMainPage" class="nav1">首页</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="${contextPath}/servlet/MainServlet?task=guestMainPage" class="nav2">在线购物</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="${contextPath}/servlet/CartServlet?task=list_newpage" class="nav5">我的购物车</a>
					</li>
					<li class="divider"></li>
					<c:if test="${empty regUserBean}">
						<li class="divider"></li>
						<li>
							<a href="${contextPath}/guest/login.jsp" class="nav3">用户登录</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="register.jsp" class="nav6">用户注册 </a>
						</li>
					</c:if>
					<li class="divider"></li>
				</ul>

				<div class="right_menu_corner"></div>
			</div>
			<!-- end of menu tab -->

		</div>


		<div class="crumb_navigation">
			导航:
			<span class="current">
				<a href="${contextPath}/servlet/MainServlet?task=guestMainPage">首页</a>
				&gt;&gt; 购物车
			</span>

		</div>
		<table border="0" cellpadding="0" cellspacing="0" align="center" width=100% height="400px" bgcolor="#e9e7e7">
			<tr>
				<td width=16%></td>
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
										<p style="width: 543px; padding-bottom: 10px; background-color: rgb(247, 243, 243); font-size: 16px; color: red;">购物车里面没有商品！</p>
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
														<input type="text" id="singleCount" name="${itemBean.id}" value="${itemBean.singleCount}" class="num" onkeyup="updateCount(this,event);" />
														<em class="add">+</em>
													</div>
												</td>
												<td width="119">${itemBean.singlePrice}</td>
												<td width="119">
													<a href="javascript:void(0);" onclick="deleteCart(${itemBean.id},'${itemBean.name}');">移除</a>
												</td>
											</tr>
										</c:forEach>
										<tr>
											<td width="147" colspan="2">合计：</td>
											<td width="144">${cartBean.totalCount}</td>
											<td width="119" colspan="2">${cartBean.totalPrice_format}</td>
										</tr>
									</c:if>
								</table>
								<table style="padding-top: 20px; width: 100%;">
									<tr>
										<td>
											<form action="#" method="post" name="myform">
												<table>
													<tr>
														<td></td>
														<td>
															买家备注：
															<input type="text" name="orderTemp" value="无" />
														</td>
														<td>
															<input type="submit" value="提交订单" />
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
												<input type="button" value="继续购物" />
											</form>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div></div>
					</div>
				</td>

				<td class="right_content" style="padding-left: 50px;">
					<table>
						<tr>
							<td class="shopping_cart">
								<table>
									<tr>
										<td class="cart_title">购物车</td>
										<td style="text-align: left;">
											<p id="p_totalCount" style="padding-top: 10px;">
												<c:if test="${sessionScope.cartBean != null}">商品数量：${sessionScope.cartBean.totalCount} 件</c:if>
												<c:if test="${sessionScope.cartBean == null}">商品数量：0 件</c:if>
											</p>
										</td>
									</tr>
									<tr>
										<td class="cart_icon">
											<a href="${contextPath}/servlet/CartServlet?task=list" title="" target="frmright">
												<img src="${contextPath}/css/guest/images/shoppingcart.png" alt="" title="" width="48" height="48" border="0" />
											</a>
										</td>
										<td style="text-align: left;">
											<p id="p_totalPrice" style="padding-top: 10px;">
												<c:if test="${sessionScope.cartBean != null}">商品金额：${sessionScope.cartBean.totalPrice_format} ￥</c:if>
												<c:if test="${sessionScope.cartBean == null}">商品金额：0.00 ￥</c:if>
											</p>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${empty regUserBean}">
									<form action="#" id="loginForm" method="post">
										<div class="title_box">用户登录</div>
										<div class="border_box">
											<p>
												用户名：
												<input id="username" name="username" type="text" style="width: 110px" />
											</p>
											<p>
												密&nbsp;&nbsp;&nbsp;&nbsp;码：
												<input id="password" name="password" type="password" style="width: 110px" />
											</p>
											<p>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input name="提交" type="submit" value="登录" onclick="loginSystem();" />
												<input name="重置" type="reset" value="重置" />
											</p>
											<p align="center">
												&nbsp;&nbsp;&nbsp;[
												<a href="${contextPath}/guest/register.jsp">新用户注册</a>
												] &nbsp;[
												<a href="findPass.jsp">忘记密码</a>
												]
											</p>

										</div>
									</form>
								</c:if>
								<c:if test="${!empty regUserBean}">
									<div class="title_box">用户信息</div>
									<div class="border_box">
										<br />
										<p>欢迎登陆：${regUserBean.username}</p>
										<br />
										<p>
											[
											<a href="OrderFormAction!findOrderFormByUserName.action?orderForm.username=${sessionScope.user.username}">我的订单</a>
											]&nbsp;&nbsp; [
											<a href="${contextPath}/guest/updatePass.jsp">修改密码</a>
											]
										</p>
										<p>
											[
											<a href="${contextPath}/guest/userInfo.jsp">个人信息</a>
											]&nbsp;&nbsp; [
											<a href="${contextPath}/servlet/LoginServlet?task=logoutRegUser" onclick="return confirm('确定要退出登录吗?')">退出系统</a>
											]
										</p>
									</div>
								</c:if>

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


		<div class="footer" align="center">
			<br />
			&copy;&nbsp;&nbsp;|&nbsp;XXXX&nbsp;|&nbsp;版权所有&nbsp;|&nbsp;网上商城系统
			<br />
			<a href="admin/login.jsp">后台管理</a>
		</div>

	</div>

</body>
</html>