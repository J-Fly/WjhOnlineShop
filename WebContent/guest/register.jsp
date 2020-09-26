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
		var realname = document.getElementById("realname").value;
		if (realname == null || realname == '') {
			alert("真实姓名不能为空！");
			return false;
		} else if (realname.length > 4) {
			alert("真实姓名长度不能大于4！");
			return false;
		}
		var postcode = document.getElementById("postcode").value;
		var postcodeReg = /^\d{6}$/;
		if (postcode == null || postcode == '') {
			alert("邮编不能为空！");
			return false;
		} else if (!postcodeReg.test(postcode)) {
			alert("邮编只能为6位数字！");
			return false;
		}
		var address = document.getElementById("address").value;
		if (address == null || address == '') {
			alert("地址不能为空！");
			return false;
		}
		var email = document.getElementById("email").value;
		var emailReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
		if (email == null || email == '') {
			alert("邮箱不能为空！");
			return false;
		} else if (!emailReg.test(email)) {
			alert("邮箱格式错误！");
			return false;
		}
		var phone = document.getElementById("phone").value;
		var phoneReg = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
		if (phone == null || phone == '') {
			alert("电话不能为空！");
			return false;
		} else if (!phoneReg.test(phone)) {
			alert("电话格式错误！");
			return false;
		}
		//提交注册请求
		$("#regForm").ajaxSubmit({
			success : function(jsonData) {
				if (jsonData.flag == false) {
					window.alert(jsonData.message);
				} else {
					window.alert(jsonData.message);
					var index = "${contextPath}/servlet/MainServlet?task=guestMainPage";
					window.top.location.href = index;
				}
			},
			error : function(jsonData) {
				window.alert("加载失败");
			},
			async : true,//异步
			url : "${contextPath}/servlet/RegServlet?task=register&date=" + Math.random() + "",
			type : "post", /*设置表单以post方法提交*/
			dataType : "json" /*设置返回值类型为文本*/
		});
	}
</script>
</head>
<body>
	<div id="#cart_main_container">
		<table border="0" cellpadding="0" cellspacing="0" align="center" width=100% height="400px" bgcolor="#e9e7e7">
			<tr>
				<td width=20%></td>

				<td class="center_content" width=100% height=500px align="center">
					<form name="regForm" method="post" action="#" id="regForm" enctype="multipart/form-data">

						<table width=500 border="0" align="center" bgcolor="#e9e7e7">
							<tr>
								<td colspan=3 bgcolor="#c0c0c0" align="center">
									<h3>用户注册</h3>
								</td>

							</tr>
							<tr>
								<td width=60 height=30>用户名:</td>
								<td width=100>
									<input id="username" type="text" name="username" size="20" onclick="fun(msg)" onblur="show(msg)" autocomplete="new-password" />
								</td>
								<td width=220>
									<div id=msg style="color: #ff0000; display: none;">用户名长度为8至15位</div>
									<a href="javascript:;" onclick="checkUserName();">检测用户名是否存在</a>
									<span id="isExist" style="color: green; margin-left: 15px;"></span>
								</td>

							</tr>
							<tr>
								<td height=30>密码:</td>
								<td width=100>
									<input id="password" type="password" name="password" size="20" onclick="fun(msg1)" onblur="show(msg1)" autocomplete="new-password" />
								</td>
								<td width=180>
									<div id=msg1 style="color: #ff0000; display: none;">密码长度为6至16位</div>
								</td>
							</tr>
							<tr>
								<td height=30>真实姓名:</td>
								<td width=100>
									<input id="realname" type="text" name="realname" size="20" onclick="fun(msg3)" onblur="show(msg3)" />
								</td>
								<td width=180>
									<div id=msg3 style="color: #ff0000; display: none;">输入您的真实姓名</div>
								</td>
							</tr>
							<tr>
								<td height=30>性别:</td>
								<td width=100>
									<input type="radio" name="sex" value="男" ${regNewUserBean.sex=='男' ? "checked='checked'" : ""} />
									男
									<input type="radio" name="sex" value="女" ${regNewUserBean.sex=='女' ? "checked='checked'" : ""} />
									女
								</td>
								<td></td>
							</tr>
							<tr>
								<td height=30>出生日期:</td>
								<td width=100>
									<input onclick="fun(msg12)" onblur="show(msg12)" style="width: 158px; font-size: 14px;" type="date" name="birthday" id="birthday" size="50" value="${regUserBean.birthday}" />
								</td>
								<td width=180>
									<div id=msg12 style="color: #ff0000; display: none;">选择您的出生日期</div>
								</td>
							</tr>
							<tr>
								<td height=30>用户电话:</td>
								<td width=100>
									<input id="phone" type="text" name="phone" size="20" onclick="fun(msg4)" onblur="show(msg4)" />
								</td>
								<td width=180>
									<div id=msg4 style="color: #ff0000; display: none;">输入您联系电话</div>
								</td>
							</tr>
							<tr>
								<td height=30>邮编:</td>
								<td width=100>
									<input id="postcode" type="text" name="postcode" size="20" onclick="fun(msg7)" onblur="show(msg7)" />
								</td>
								<td width=180>
									<div id=msg7 style="color: #ff0000; display: none;">输入您的邮编，请认真填写</div>
								</td>
							</tr>
							<tr>
								<td height=30>收货地址:</td>
								<td width=100>
									<input id="address" type="text" name="address" size="20" onclick="fun(msg5)" onblur="show(msg5)" />
								</td>
								<td width=180>
									<div id=msg5 style="color: #ff0000; display: none;">输入您的收货地址，请认真填写</div>
								</td>
							</tr>
							<tr>
								<td height=30>电子邮件:</td>
								<td width=100>
									<input id="email" type="text" name="email" size="20" onclick="fun(msg6)" onblur="show(msg6)" />
								</td>
								<td width=180>
									<div id=msg6 style="color: #ff0000; display: none;">填写如111111@163.com的地址</div>
								</td>
							</tr>
							<tr>
								<td>
									<input type="hidden" name="user.money" value="1000" />
								</td>
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