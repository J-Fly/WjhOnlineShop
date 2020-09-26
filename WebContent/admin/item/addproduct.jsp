<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 添加商品</title>
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
		document.getElementById("birthday").setAttribute("max", year + "-" + month + "-" + date);
	}

	function saveData() {
		//设置按钮样式为disabled="disabled"
		$("#saveButton").attr("disabled", "disabled");
		$("#saveButton").toggleClass("btnGray");

		$("#itemForm").ajaxSubmit({
			success : function(jsonData) {
				if (jsonData.flag == false) {
					window.alert(jsonData.message);
				} else {
					var confirmFlag = window.confirm(jsonData.message);
					if (confirmFlag == true) {
						window.location.reload();
					} else {
						window.location.href = "${contextPath}/servlet/ItemServlet?task=list";
					}
				}
				$("#saveButton").removeAttr("disabled");
				$("#saveButton").removeClass("btnGray");
				$("#saveButton").addClass("btn");
			},
			error : function(jsonData) {
				window.alert("加载失败");
			},
			async : true,//异步
			url : "${contextPath}/servlet/ItemServlet?task=saveData&date=" + Math.random() + "", /*设置post提交到的页面*/
			type : "post", /*设置表单以post方法提交*/
			dataType : "json" /*设置返回值类型为文本*/
		});
	}
</script>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=add_product"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>添加商品</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/ItemServlet?task=list" class="actionBtn">商品列表</a>
					添加商品
				</h3>
				<form id="itemForm" action="#" method="post" enctype="multipart/form-data">
					<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
						<tr>
							<td width="90" align="right">商品名称</td>
							<td>
								<input style="width: 250px;" type="text" id="name" name="name" value="" size="80" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right">商品分类</td>
							<td>
								<select style="width: 262px;" id="itemCategory" name="itemCategory">
									<c:forEach var="category" items="${categoryList}">
										<optgroup label="${category.classname}"></optgroup>
										<c:forEach var="childcategory" items="${category.childCategory}">
											<option value="${childcategory.cid}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${childcategory.classname}</option>
										</c:forEach>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">商品价格</td>
							<td>
								<input style="width: 150px;" type="text" id="price" name="price" value="" size="40" class="inpMain" />
							</td>
						</tr>
						<!-- 						<tr> -->
						<!-- 							<td align="right">添加时间：</td> -->
						<!-- 							<td><input type="text" id="addtime" name="addtime" -->
						<%-- 								value="${addtime}" size="50" class="inpMain" /></td> --%>
						<!-- 						</tr> -->
						<tr>
							<td align="right">添加时间：</td>
							<td>
								<input style="width: 150px; font-size: 14px;" type="date" name="dcdate" id="dcdate" size="50" value="${addtime}" class="inpMain" />
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">商品描述</td>
							<td>
								<textarea id="description" name="description" style="width: 780px; height: 220px;" class="textArea"></textarea>
							</td>
						</tr>
						<tr>
							<td align="right">缩略图</td>
							<td>
								<input type="file" name="image" size="38" class="inpFlie" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input id="saveButton" name="saveButton" class="btn" type="button" value="保存数据" onclick="saveData();" />
								<input style="margin-left: 132px" name="reset" class="btnGray" type="reset" value="重置输入" />
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
