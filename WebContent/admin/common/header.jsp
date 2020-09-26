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
	<div id="dcHead">
		<div id="head">
			<div class="logo">
				<a href="index.html">
					<img src="${contextPath}/images/dclogo.gif" alt="logo">
				</a>
			</div>
			<div class="nav">
				<ul>
					<li class="M">
						<a href="JavaScript:void(0);" class="topAdd">新建</a>
						<div class="drop mTopad">
							<a href="product.php?rec=add">商品</a>
							<a href="article.php?rec=add">文章</a>
							<a href="nav.php?rec=add">自定义导航</a>
							<a href="show.html">首页幻灯</a>
							<a href="page.php?rec=add">单页面</a>
							<a href="manager.php?rec=add">管理员</a>
							<a href="link.html"></a>
						</div>
					</li>
					<li>
						<a href="../index.php" target="_blank">查看站点</a>
					</li>
					<li>
						<a href="index.php?rec=clear_cache">清除缓存</a>
					</li>
					<li>
						<a href="http://www.mycodes.net" target="_blank">帮助</a>
					</li>
					<li class="noRight">
						<a href="module.html">DouPHP+</a>
					</li>
				</ul>
				<ul class="navRight">
					<li class="M noLeft">
						<a href="JavaScript:void(0);">您好，${userBean.realname}</a>
						<div class="drop mUser">
							<a href="manager.php?rec=edit&id=1">编辑我的个人资料</a>
							<a href="manager.php?rec=cloud_account">设置云账户</a>
						</div>
					</li>
					<li class="noRight">
						<a href="#" onclick="logoutAdmin()">退出</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>