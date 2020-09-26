<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 修改用户信息</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
	window.onload = function choosedate() {
		//得到当前时间
		var date_now = new Date();
		//得到当前年份
		var year = date_now.getFullYear();
		//得到当前月份
		//注：
		//  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
		//  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
		var month = date_now.getMonth() + 1 < 10 ? "0" + (date_now.getMonth() + 1) : (date_now.getMonth() + 1);
		//得到当前日子（多少号）
		var date = date_now.getDate() < 10 ? "0" + date_now.getDate() : date_now.getDate();
		//设置input标签的max属性
		document.getElementById("dcdate").setAttribute("max", year + "-" + month + "-" + date);
	}

	function saveData() {
		//设置按钮样式为disabled="disabled",禁用按钮
		$("#saveButton").attr("disabled", "disabled");
		$("#saveButton").toggleClass("btnGray");

		//提交修改请求
		$("#modifySelfForm").ajaxSubmit({
			success : function(jsonData) {
				if (jsonData.flag == false) {
					window.alert(jsonData.message);
				} else {
					window.alert(jsonData.message);
					window.location.reload();
				}
				//将按钮样式还原
				$("#saveButton").removeAttr("disabled");
				$("#saveButton").removeClass("btnGray");
				$("#saveButton").addClass("btn");
			},
			error : function(jsonData) {
				window.alert("加载失败");
			},
			async : true,//异步
			url : "${contextPath}/servlet/UserServlet?task=modifySysAdmin&uid="+${userBean.uid}+"&self=1"+"&date=" + Math.random() + "",/*设置post提交到的页面*/
			type : "post", /*设置表单以post方法提交*/
			dataType : "json" /*设置返回值类型为文本*/
		});
	}
</script>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=modifySelf"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>修改用户信息</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/admin/main.jsp" class="actionBtn">返回首页</a>
					修改用户&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-size: 14px;">--${userBean.username}</span>
				</h3>
				<form id="modifySelfForm" action="#" method="post" enctype="multipart/form-data">
					<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
						<tr>
							<td width="90" align="right">用户编号</td>
							<td>
								<input style="width: 200px;" type="text" id="uid" name="uid" value="${userBean.uid}" size="80" class="inpMain" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td width="90" align="right">用户名</td>
							<td>
								<input style="width: 200px;" type="text" id="username" name="username" value="${userBean.username}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td width="90" align="right">真实姓名</td>
							<td>
								<input style="width: 200px;" type="text" id="realname" name="realname" value="${userBean.realname}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">性别</td>
							<td>
								<select style="width: 100px;" id="sex" name="sex">
									<option value="男" ${userBean.sex=='男' ? "selected='selected'" : ""}>男</option>
									<option value="女" ${userBean.sex=='女' ? "selected='selected'" : ""}>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">出生日期</td>
							<td>
								<input style="width: 200px; font-size: 14px;" type="date" name="birthday" id="birthday" size="50" value="${userBean.birthday}" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">电话</td>
							<td>
								<input style="width: 200px;" type="text" id="phone" name="phone" value="${userBean.phone}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right" valign="middle">邮箱</td>
							<td>
								<input style="width: 200px;" type="text" id="email" name="email" value="${userBean.email}" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">地址</td>
							<td>
								<textarea id="address" name="address" style="width: 780px; height: 80px;font-size: 14px;" class="textArea">${userBean.address}</textarea>
							</td>
						</tr>
						<tr>
							<td width="90" align="right">邮编</td>
							<td>
								<input style="width: 200px;" type="text" id="postcode" name="postcode" value="${userBean.postcode}" size="80" class="inpMain" />
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td width="90" align="right">状态</td> -->
<!-- 							<td> -->
<!-- 								<input style="margin-left: 25px;" type="radio" name="lock" value="0" >正常 -->
<!-- 								<input style="margin-left: 80px;" type="radio" name="lock" value="1" >冻结 -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<td></td>
							<td>
								<input id="saveButton" name="saveButton" class="btn" type="button" value="保存数据" onclick="saveData();" />
								<input style="margin-left: 132px" name="reset" class="btnGray" type="reset" value="重置输入" />
								<input style="margin-left: 132px" name="cannel" class="btnGray" type="button" value="取消操作" onclick="window.history.back();" />
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
