<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 修改密码</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript">
	function saveData() {
		var formData = jQuery("#changepwdForm").serializeArray();
		var url = "${contextPath}/servlet/UserServlet?task=changePwd&uid="+${userBean.uid}+"&date=" + Math.random() + "";
		var oldpwd=$("#oldpwd").val();
		if (oldpwd==${userBean.password}) {
			var newpwd=$("#newpwd").val();
			var confirmpwd=$("#confirmpwd").val();
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
	 					var logoutURL = "${contextPath}/servlet/LoginServlet?task=logoutAdmin&date=" + Math.random() + "";
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
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=changepwd"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>修改密码</strong>
			</div>
			<div id="manager" class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/admin/main.jsp" class="actionBtn">返回首页</a>
					修改密码
				</h3>
				<form id="changepwdForm" action="#" method="post">
					<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
						<tr>
							<td width="100" align="right">用户名</td>
							<td>
								<input type="text" name="username" size="40" class="inpMain" autocomplete="new-password" value="${userBean.username}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="100" align="right">旧密码</td>
							<td>
								<input type="password" id="oldpwd" name="oldpwd" size="40" class="inpMain" autocomplete="new-password" />
							</td>
						</tr>
						<tr>
							<td align="right">新密码</td>
							<td>
								<input type="password" id="newpwd" name="newpwd" size="40" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">确认密码</td>
							<td>
								<input type="password" id="confirmpwd" name="confirmpwd" size="40" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="button" name="submit" class="btn" value="提交" onclick="saveData();" />
								<input style="margin-left: 180px" name="reset" class="btnGray" type="reset" value="重置" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<div class="clear"></div>
	</div>
</body>
</html>