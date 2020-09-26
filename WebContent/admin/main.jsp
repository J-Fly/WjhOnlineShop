<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心</title>
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<style>
div.item img {
	width: 1200px;
	height: 200px;
}

div#carousel-example-generic {
	width: 100%;
	height: 590px;
	margin: 0;
}
</style>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?task=index"></c:import>
		<div id="dcMain">
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					<li data-target="#carousel-example-generic" data-slide-to="3"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox" style="height: 600px;">
					<div class="item active">
						<img style="width: 1200px; height: 590px;" src="${contextPath}/upload/bar1.jpg">
					</div>
					<div class="item">
						<img style="width: 1200px; height: 590px;" src="${contextPath}/upload/bar2.jpg">
					</div>
					<div class="item">
						<img style="width: 1200px; height: 590px;" src="${contextPath}/upload/bar3.jpg">
					</div>

					<div class="item">
						<img style="width: 1200px; height: 590px;" src="${contextPath}/upload/bar4.jpg">
					</div>

				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

				</a>
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>

				</a>

			</div>
			<div class="clear"></div>
			<c:import url="/admin/common/footer.jsp"></c:import>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>