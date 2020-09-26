<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 商品分类</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" language="javascript">
	function deleteCategory(cid, parentid, thisObj) {
		var testBody = $("#testBody");
		var confirmMsg = "";
		if (parentid == 0) {
			//说明删除是大类
			confirmMsg = "是否删除大类，删除大类会级联删除小类";
		} else {
			confirmMsg = "是否删除小类？";
		}
	
		var confirmFlag = window.confirm(confirmMsg);
		if (confirmFlag == true) {
			//发起ajax请求，后端趔除，前端要移除对应的行。
			var delURL = "${contextPath}/servlet/CategoryServlet?task=deleteCategory&cid=" + cid + "&parentid=" + parentid + "&date=" + Math.random() + "";
			jQuery.get(delURL, function(jsonData) {
				var flag = jsonData.flag;
				var message = jsonData.message;
				if (flag == true) {
					//在页面中删除行
					if (parentid == 0) {
						//删除的是大类别
						var childNodes = document.getElementsByName("child_" + cid);
						if (childNodes != null) {
							for (var i = 0; i < childNodes.length; i++) {
								var tNode = childNodes[i];
								testBody[0].removeChild(tNode);
								i--;
							}
						}
					}
					//删除自身
	
					var trNode = $("#tr_" + cid);
					if (trNode != null) {
						//转换为dom对象才能使用removeChild方法
						testBody[0].removeChild(trNode[0]);
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
		<c:import url="/admin/common/left.jsp?type=category"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>商品分类</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/CategoryServlet?task=addCategory" class="actionBtn add">添加分类</a>
					商品分类
				</h3>
				<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<th width="10%" align="center">类别编号</th>
						<th width="60%" align="center">类别名称</th>
						<th width="10%" align="center">操作</th>
					</tr>
					<tbody id="testBody">
						<c:forEach var="categoryBean" items="${categoryList}">
							<tr id="tr_${categoryBean.cid}">
								<td align="center">${categoryBean.cid}</td>
								<td align="center">
									<a href="#" style="padding-right: 30px;">${categoryBean.classname}</a>
								</td>
								<td align="center">
									<a href="${contextPath}/servlet/CategoryServlet?task=addCategory&amp;pid=${categoryBean.cid}">添加子类 | </a>
									<a href="#">编辑</a>
									|
									<a href="#" onclick="deleteCategory(${categoryBean.cid},${categoryBean.parentid},this);">删除</a>
								</td>
							</tr>
							<c:forEach var="childCategoryBean" items="${categoryBean.childCategory}">
								<tr id="tr_${childCategoryBean.cid}" name="child_${childCategoryBean.parentid}">
									<td align="center">${childCategoryBean.cid}</td>
									<td align="left">
										<a href="javascript:void(0);" style="padding-left: 435px;">—${childCategoryBean.classname}</a>
									</td>
									<td align="right">
										<span style="padding-right: 6px;">
											<a href="${contextPath}/servlet/CategoryServlet?task=edit&cid=${childCategoryBean.cid}">编辑</a>
											|
											<a href="#" onclick="deleteCategory(${childCategoryBean.cid},${childCategoryBean.parentid},this);">删除</a>
										</span>
									</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<div class="clear"></div>
	</div>
</body>
</html>