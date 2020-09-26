
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</style>
<script type="text/javascript" src="${contextPath}/js/boxOver.js"></script>
<script type="text/javascript">
	function loginSystem() {
		var formData = jQuery("#loginForm").serializeArray();
		var url = "${contextPath}/servlet/LoginServlet?task=loginRegUser&date=" + Math.random() + "";
		jQuery.post(url, formData, function(jsonData) {
			if (jsonData.flag == false) {
				window.alert(jsonData.message);
			} else {
				//					window.alert(jsonData.message);
				var url = "${contextPath}/servlet/MainServlet?task=guestMainPage&date=" + Math.random() + "";
				window.top.location.href = url;
			}
		}, "json");
	}
</script>
</head>

<body>

	<div id="">
		<table width=100% bgcolor="#e9e7e7">
			<tr>
				<td width=20%></td>
				<td class="center_content" width=100% height=400px align=center>

					<form action="#" id="loginForm" method="post">
						<div class="title_box">用户登录</div>
						<div class="border_box">
							<p>
								用户名：
								<input name="username" type="text" style="width: 110px" />
							</p>
							<p>
								密&nbsp;&nbsp;&nbsp;&nbsp;码：
								<input name="password" type="password" style="width: 110px" />
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="提交" type="button" value="登录" onclick="loginSystem();" />
								<input name="重置" type="reset" value="重置" />
							</p>
							<p align="center">
								&nbsp;&nbsp;&nbsp;[
								<a href="register.jsp">新用户注册</a>
								] &nbsp;[
								<a href="findPass.jsp">忘记密码</a>
								]
							</p>

						</div>
					</form>

				</td>
			</tr>
		</table>
	</div>

</body>
</html>

