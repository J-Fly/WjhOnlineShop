<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心</title>
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript">
	function logoutAdmin() {
		var confirmFlag = window.confirm("确定退出系统？");
		if (confirmFlag == true) {
			var logoutURL = "${contextPath}/servlet/LoginServlet?task=logoutAdmin&date=" + Math.random() + "";
			window.top.location.href = logoutURL;
		}
	}
</script>
</head>
<body>
	<div id="dcLeft">
		<div id="menu">
			<ul class="top">
				<li ${param.type=="index"? "class='cur'":""}>
					<a href="${contextPath}/admin/main.jsp">
						<i class="home"></i>
						<em>管理首页</em>
					</a>
				</li>
			</ul>
			<!-- 			<ul> -->
			<!-- 				<li><a href="nav.html"><i class="nav"></i><em>自定义导航栏</em></a></li> -->
			<!-- 				<li><a href="show.html"><i class="show"></i><em>首页幻灯广告</em></a></li> -->
			<!-- 				<li><a href="page.html"><i class="page"></i><em>单页面管理</em></a></li> -->
			<!-- 			</ul> -->
			<ul>
				<li ${param.type=="add_product"? "class='cur'":""}>
					<a href="${contextPath}/servlet/ItemServlet?task=addItem">
						<i class="productCat"></i>
						<em>商品添加</em>
					</a>
				</li>
				<li ${param.type=="product"? "class='cur'":""}>
					<a href="${contextPath}/servlet/ItemServlet?task=list">
						<i class="product"></i>
						<em>商品管理</em>
					</a>
				</li>
			</ul>
			<ul>
				<li ${param.type=="add_category"? "class='cur'":""}>
					<a href="${contextPath}/servlet/CategoryServlet?task=addCategory">
						<i class="articleCat"></i>
						<em>类别添加</em>
					</a>
				</li>
				<li ${param.type=="category"? "class='cur'":""}>
					<a href="${contextPath}/servlet/CategoryServlet?task=list">
						<i class="article"></i>
						<em>类别管理</em>
					</a>
				</li>
			</ul>
			<ul>
				<li ${param.type=="order"? "class='cur'":""}>
					<a href="${contextPath}/servlet/OrderServlet?task=list">
						<i class="theme"></i>
						<em>订单管理</em>
					</a>
				</li>
				<li ${param.type=="order_count"? "class='cur'":""}>
					<a href="${contextPath}/servlet/OrderServlet?task=statistics">
						<i class="page"></i>
						<em>订单统计</em>
					</a>
				</li>
			</ul>
			<ul>
				<c:if test="${userBean.level==1}">
					<li ${param.type=="adminuser"? "class='cur'":""}>
						<a href="${contextPath}/servlet/UserServlet?task=sysList">
							<i class="system"></i>
							<em>系统用户管理</em>
						</a>
					</li>
				</c:if>
				<li ${param.type=="reguser"? "class='cur'":""}>
					<a href="${contextPath}/servlet/UserServlet?task=regList">
						<i class="manager"></i>
						<em>注册用户管理</em>
					</a>
				</li>
				<li ${param.type=="modifySelf"? "class='cur'":""}>
					<a href="${contextPath}/servlet/UserServlet?task=editSelf">
						<i class="mobile"></i>
						<em>修改信息</em>
					</a>
				</li>
				<li ${param.type=="changepwd"? "class='cur'":""}>
					<a href="${contextPath}/admin/user/changepwd.jsp">
						<i class="backup"></i>
						<em>修改密码</em>
					</a>
				</li>
			</ul>
			<ul class="bot">
				<li ${param.type=="quit"? "class='cur'":""}>
					<a href="#" onclick="logoutAdmin()">
						<i class="managerLog"></i>
						<em>退出系统</em>
					</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>