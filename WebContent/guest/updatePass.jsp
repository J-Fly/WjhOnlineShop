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
<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
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
<script>
	function fun(t) {
		t.style.display = '';
	}
	function show(t) {
		t.style.display = 'none';
	}
</script>
<script type="text/javascript" src="${contextPath}/js/boxOver.js"></script>
<script language=JavaScript>
	function checkUserName() {
		var username = $("#username").val();
		var URL = "${contextPath}/servlet/RegServlet?task=check&username=" + username + "&date=" + Math.random() + "";

		jQuery.get(URL, function(jsonData) {
			var flag = jsonData.flag;
			if (flag == true) {
				$("#isExist").css("color", "green");
				$("#isExist").text("用户名可以使用");
			} else {
				$("#isExist").css("color", "red");
				$("#isExist").text("用户名已存在，请更换");
			}
		}, "json");

	}

	function saveData() {
		var formData = jQuery("#myform").serializeArray();
		var url = "${contextPath}/servlet/RegServlet?task=updatePass&uid="+${regUserBean.uid}+"&date=" + Math.random() + "";
		var oldpwd=$("#oldpassword").val();
		if (oldpwd==${regUserBean.password }) {
			var newpwd=$("#newpassword").val();
			var confirmpwd=$("#passwordconfirm").val();
			if (newpwd.length==0) {
				window.alert("新密码不能为空，请重新输入");
			}else if (newpwd==oldpwd) {
				window.alert("新密码不能与旧密码相同，请重新输入");
			}else if(newpwd!=confirmpwd) {
				window.alert("确认密码与新密码不相同，请重新输入");
			}else {
	 			jQuery.post(url, formData, function(jsonData) {
	 				if (jsonData.flag == false) {
	 					window.alert(jsonData.message);
	 				} else {
	 					window.alert(jsonData.message); 
	 					var logoutURL = "${contextPath}/servlet/LoginServlet?task=logoutRegUser&date=" + Math.random() + "";
	 					window.top.location.href = logoutURL;
	 				}
	 			}, "json");
			}
		}else{
			window.alert("旧密码错误，请重新输入");
		}
	}
</script>
</head>
<body>
	<div id="main_container" style="height: 384px; width: 580px; background-color: #E9E7E7; float: left;">
		<table border="0" bgcolor="#e9e7e7" align="center" width=100%>
			<tr>
				<td class="cart_center_content" width=100% align="center" style="height: 380px;">
					<form name="myform" method="post" action="#" id="myform">
						<table style="padding-left:30px; padding-right: 20px;" bgcolor="#e9e7e7" width=100% align=center>
							<tr>
								<td colspan=3 bgcolor="#c0c0c0" align="center">
									<h3>修改密码</h3>
								</td>
							</tr>
							<tr>
								<td height=30>旧密码:</td>
								<td width=100>
									<input id="oldpassword" type="password" name="oldpassword" size="20" onclick="fun(msg1)" onblur="show(msg1)" autocomplete="new-password" />
								</td>
								<td width=180>
									<div id=msg1 style="color: #ff0000; display: none;">密码长度为8至15位</div>
								</td>
							</tr>
							<tr>
								<td height=30>密码:</td>
								<td width=100>
									<input id="newpassword" type="password" name="newpassword" size="20" onclick="fun(msg1)" onblur="show(msg1)" autocomplete="new-password" />
								</td>
								<td width=180>
									<div id=msg1 style="color: #ff0000; display: none;">密码长度为8至15位</div>
								</td>
							</tr>
							<tr>
								<td height=30>确认密码:</td>
								<td width=100>
									<input id="passwordconfirm" type="password" name="passwordconfirm" size="20" onclick="fun(msg2)" onblur="show(msg2)" autocomplete="new-password" />
								</td>
								<td width=180>
									<div id=msg2 style="color: #ff0000; display: none;">两次输入的密码必须相同</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="button" name="submit1" value="提交" onclick="saveData();" />
									<input type="reset" name="Submit2" value="重置" />
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>