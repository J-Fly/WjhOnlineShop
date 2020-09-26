<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 添加分类</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>

<script type="text/javascript" language="javascript">
	function saveData() {
		var formData = jQuery("#categoryForm").serializeArray();
		var url = "${contextPath}/servlet/CategoryServlet?task=saveData&date=" + Math.random() + "";
		jQuery.post(url, formData, function(jsonData) {
			if (jsonData.flag == false) {
				window.alert(jsonData.message);
			} else {
				var confirmFlag = window.confirm(jsonData.message)
				if (confirmFlag == true) {
					if ($("#parentid")[0][0].selected == true) {
						location.reload(true);
					}else {
	 					jQuery("#classname").val("");
	 					//类别编号+1
	 					var maxcid = jQuery("#cid").val();
	 					var nowcid = parseInt(maxcid) + 1;
	 					jQuery("#cid").val(nowcid);
					}
				} else {
					var url = "${contextPath}/servlet/CategoryServlet?task=list&date=" + Math.random() + "";
					window.top.location.href = url;
				}
			}
		}, "json");
	}
</script>

</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=add_category"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心<b>></b><strong>添加分类</strong>
			</div>
			<div class="mainBox"
				style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/CategoryServlet?task=list"
						class="actionBtn">商品分类</a>添加分类
				</h3>
				<form id="categoryForm" action="#" method="post">
					<table width="100%" border="0" cellpadding="8" cellspacing="0"
						class="tableBasic">
						<tr>
							<td align="right">上级分类</td>
							<td><select name="parentid" id="parentid"
								style="width: 272px">
									<option value="0">作为一级分类</option>
									<c:forEach var="pcategory" items="${parentList}">
										<option value="${pcategory.cid}"
											${pid==pcategory.cid ? "selected='selected'":""}>${pcategory.classname}</option>
									</c:forEach>

							</select></td>
						</tr>
						<tr>
							<td align="right">类别编码</td>
							<td><input type="text" name="cid" id="cid" value="${maxcid}"
								readonly="readonly" size="40" class="inpMain" /></td>
						</tr>
						<tr>
							<td width="80" align="right">类别名称</td>
							<td><input type="text" name="classname" id="classname"
								value="" size="40" class="inpMain" /></td>
						</tr>

						<!-- 						<tr> -->
						<!-- 							<td align="right">简单描述</td> -->
						<!-- 							<td><textarea name="description" cols="60" rows="4" -->
						<!-- 									class="textArea"></textarea></td> -->
						<!-- 						</tr> -->
						<tr>
							<td></td>
							<td><input name="submit" class="btn" type="button"
								value="保存数据" onclick="saveData();" /> <input
								style="margin-left: 132px" name="reset" class="btnGray"
								type="reset" value="重置输入" /></td>
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