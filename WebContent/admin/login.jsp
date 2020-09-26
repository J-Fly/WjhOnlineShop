<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7," />
<link rel="stylesheet" type="text/css" href="${contextPath}/css/login/style.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/css/login/skin_/login.css" />
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<title>商城_用户登录</title>

</head>
<body>
	<div id="container">
		<div id="bd">
			<div id="main">
				<div class="login-box">
					<div id="logo"></div>
					<h1></h1>
					<form id="loginForm" autocomplete="off">
						<div class="input username" id="username">
							<label for="userName">用户名</label>
							<input type="text" id="userName" name="userName" />
							<span id="usernameCheck" style="padding-left: 275px; color: red;"></span>
						</div>
						<div class="input psw" id="psw">
							<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
							<span></span>
							<input type="password" id="password" autocomplete="new-password" name="password" />
							<span id="pswCheck" style="padding-left: 272px; color: red;"></span>
						</div>
						<div class="input validate" id="validate">
							<label for="valiDate">验证码</label>
							<input type="text" id="valiDate" />
							<div id="randomCode" class="value" style="cursor: pointer;" onclick="refeshRandom();"></div>
						</div>
					</form>
					<div id="btn" class="loginButton">
						<input type="button" class="button" value="登录" onclick="loginSystem()" />
					</div>
					<div id="randomCodeCheck">
						<span id="randomCodeCheckSpan" style="color: red"></span>
					</div>
				</div>
			</div>
			<div id="ft">CopyRight&nbsp;2020&nbsp;&nbsp;版权所有&nbsp;&nbsp;版权所有 © wjh</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
		var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
	});

	jQuery(function() {
		refeshRandom();
	})

	function refeshRandom() {
		//获取验证码
		var url = "${contextPath}/servlet/LoginServlet?task=getRandomCode&date=" + Math.random() + "";
		jQuery.get(url, function(randomCode) {
			$("#randomCode").html(randomCode);
		}, "text");
	}

	function loginSystem() {
		// 		var pattern_chin =eval("/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/");
		// 		$("#usernameCheck").html("用户名不能为空");
		// 		if ($("#userName").val()=="") {
		// 			$("#usernameCheck").html("用户名不能为空");
		// 		}else if (!pattern_chin.test($("#userName").val())) {
		// 			$("#usernameCheck").html("用户名不合法");
		// 		}else {
		// 			$("#usernameCheck").html("");
		// 		}
		if ($("#randomCode").html().toLowerCase() == $("#valiDate").val().toLowerCase()) {
			$("#randomCodeCheckSpan").html("");
			var formData = jQuery("#loginForm").serializeArray();
			var url = "${contextPath}/servlet/LoginServlet?task=loginAdmin&date=" + Math.random() + "";
			jQuery.post(url, formData, function(jsonData) {
				if (jsonData.flag == false) {
					window.alert(jsonData.message);
				} else {
					var url = "${contextPath}/servlet/MainServlet?task=mainPage&date=" + Math.random() + "";
					window.top.location.href = url;
				}
			}, "json");
		} else {
			$("#randomCodeCheckSpan").html("验证码错误");
		}
	}
</script>
</html>
