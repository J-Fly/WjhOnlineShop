<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 系统用户管理</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript">
	function deleteUser(uid) {
		var testBody = $("#testBody");
		var confirmMsg = "确定删除该用户？";
		var confirmFlag = window.confirm(confirmMsg);
		if (confirmFlag == true) {
			//发起ajax请求，后端趔除，前端要移除对应的行。
			var delURL = "${contextPath}/servlet/UserServlet?task=delete&uid=" + uid + "&date=" + Math.random() + "";
			jQuery.get(delURL, function(jsonData) {
				var flag = jsonData.flag;
				var message = jsonData.message;
				if (flag == true) {
					//删除商品
					var trNode = $("#user" + uid);
					if (trNode != null) {
						//转换为dom对象才能使用removeChild方法
						testBody[0].removeChild(trNode[0]);
						window.location.reload();
					}
				} else {
					window.alert(message);
				}
			}, "json");
		} else {
			
		}
	}
</script>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=adminuser"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>系统用户列表</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/admin/user/addsysadmin.jsp" class="actionBtn add">添加系统用户</a>
					系统用户列表
				</h3>
				<div class="filter">
					<form action="${contextPath}/servlet/UserServlet?task=sysList" method="post">
						&nbsp;&nbsp;用户名：
						<input name="p_adminusername" type="text" class="inpMain" value="${p_adminusername}" size="20" />
						&nbsp;&nbsp;真实姓名：
						<input name="p_adminrealname" type="text" class="inpMain" value="${p_adminrealname}" size="20" />
						<input name="submit" class="btnGray" type="submit" value="筛选" />
					</form>
				</div>
				<div id="list">
					<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
						<tr>
							<th width="10" align="center">
								<input name='chkall' type='checkbox' id='chkall' onclick='selectcheckbox(this.form)' value='check'>
							</th>
							<th width="15" align="center">编号</th>
							<th width="60" align="center">用户名</th>
							<th width="30" align="center">真实姓名</th>
							<th width="10" align="center">性别</th>
							<th width="30" align="center">出生日期</th>
							<th width="50" align="center">电话</th>
							<th width="50" align="center">邮箱</th>
							<th width="100" align="center">地址</th>
							<th width="20" align="center">邮编</th>
							<th width="20" align="center">状态</th>
							<th width="60" align="center">操作</th>
						</tr>
						<tbody id="testBody">
							<c:forEach var="userBean" items="${userList}">
								<tr id="user${userBean.uid}">
									<td align="center">
										<input type="checkbox" name="checkbox[]" value="" />
									</td>
									<td align="center">${userBean.uid}</td>
									<td align="center">
										<a href="#">${userBean.username}</a>
									</td>
									<td align="center">${userBean.realname}</td>
									<td align="center">
										<a href="#">${userBean.sex}</a>
									</td>
									<td align="center">
										<a href="#">${userBean.birthday}</a>
									</td>
									<td align="center">${userBean.phone}</td>
									<td align="center">${userBean.email}</td>
									<td align="center">${userBean.address}</td>
									<td align="center">${userBean.postcode}</td>
									<td align="center">${userBean.islock==1?"冻结":"正常"}</td>
									<td align="center">
										<a href="${contextPath}/servlet/UserServlet?task=edit&uid=${userBean.uid}">编辑</a>
										|
										<a href="#" onclick="deleteUser(${userBean.uid});">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="clear"></div>
				<div class="pager">${pageTool}</div>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<!-- dcFooter 结束 -->
		<div class="clear"></div>
	</div>
</body>
</html>