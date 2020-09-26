<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理中心 - 商品列表</title>
<meta name="Copyright" content="Douco Design." />
<link href="${contextPath}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/global.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.autoTextarea.js"></script>

<script type="text/javascript">
	function deleteItem(id,thisObj) {
		var testBody = $("#testBody");
		var confirmMsg = "确定删除该商品？";
		var confirmFlag = window.confirm(confirmMsg);
		if (confirmFlag == true) {
			//发起ajax请求，后端趔除，前端要移除对应的行。
			var delURL = "${contextPath}/servlet/ItemServlet?task=deleteItem&id=" + id + "&date=" + Math.random() + "";
			jQuery.get(delURL, function(jsonData) {
				var flag = jsonData.flag;
				var message = jsonData.message;
				if (flag == true) {
					//删除商品
					var trNode = $("#item" + id);
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
	
	function checkAll(form) {
	    for (var i = 0; i < form.elements.length; i++) {
	        var e = form.elements[i];
	        if (e.name != 'chkall' && e.disabled != true) e.checked = form.chkall.checked;
	    }
	}
	
	function deleteSelectedItem(id,thisObj) {
		var testBody = $("#testBody");
		var itemidArray = new Array();
		var itemid_chkArray=document.getElementsByName("itemid_chk");
		for(var i=0;i<itemid_chkArray.length;i++){
			var checkObj=itemid_chkArray[i];
			if (checkObj.checked == true) {
				var itemid = checkObj.value;
				itemidArray.push(itemid);
			}
		}
		if (itemidArray.length<=0) {
			window.alert("您还未选中要删除的商品");
		}else {
			var delete_itemid=itemidArray.join(",");
			var confirmMsg = "是否删除选中的商品";
			var confirmFlag = window.confirm(confirmMsg);
			if (confirmFlag == true) {
				//发起ajax请求，后端趔除，前端要移除对应的行。
				var delURL = "${contextPath}/servlet/ItemServlet?task=deleteSelectedItem&delete_itemid=" + delete_itemid + "&date=" + Math.random() + "";
				jQuery.get(delURL, function(jsonData) {
					var flag = jsonData.flag;
					var message = jsonData.message;
					if (flag == true) {
						//删除商品
						for(var i=0;i<itemidArray.length;i++){
							var trNode = $("#item" + itemidArray[i]);
							if (trNode != null) {
								//转换为dom对象才能使用removeChild方法
								testBody[0].removeChild(trNode[0]);
								
							}
						}
						window.location.reload();
					} else {
						window.alert(message);
					}
				}, "json");
			}
		}
	}
</script>
</head>
<body>
	<div id="dcWrap">
		<c:import url="/admin/common/header.jsp"></c:import>
		<c:import url="/admin/common/left.jsp?type=product"></c:import>
		<div id="dcMain">
			<!-- 当前位置 -->
			<div id="urHere">
				商城后台管理中心
				<b>></b>
				<strong>商品列表</strong>
			</div>
			<div class="mainBox" style="height: auto !important; height: 550px; min-height: 550px;">
				<h3>
					<a href="${contextPath}/servlet/ItemServlet?task=addItem" class="actionBtn add">添加商品</a>
					商品列表
				</h3>
				<div class="filter">
					<form action="${contextPath}/servlet/ItemServlet?task=list" method="post">
						<select name="p_categoryid" style="width: 100px;">
							<option value="">所有类别</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.cid}" ${p_categoryid == category.cid ? "selected='selected'" : ""}>${category.classname}</option>
								<c:forEach var="childcategory" items="${category.childCategory}">
									<option value="${childcategory.cid}" ${p_categoryid == childcategory.cid ? "selected='selected'" : ""}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${childcategory.classname}</option>
								</c:forEach>
							</c:forEach>
						</select>
						&nbsp;&nbsp;商品名称：
						<input name="p_itemname" type="text" class="inpMain" value="${p_itemname}" size="20" />
						<input name="submit" class="btnGray" type="submit" value="筛选" />
					</form>
					<span>
						<a class="btnGray" href="#" onclick="deleteSelectedItem();">批量删除商品</a>
						<!-- 						<a class="btnGray" href="#">开始筛选首页商品</a> -->
					</span>
				</div>
				<div id="list">
					<form name="action" method="post" action="product.php?rec=action">
						<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
							<tr>
								<th width="22" align="center">
									<input name='chkall' type='checkbox' id='chkall' onclick='checkAll(this.form)' value='check'>
								</th>
								<th width="40" align="center">编号</th>
								<th width="80" align="center">商品名称</th>
								<th width="80" align="center">商品价格</th>
								<th width="80" align="center">商品类别</th>
								<th width="80" align="center">品牌</th>
								<th width="80" align="center">添加日期</th>
								<th width="80" align="center">操作</th>
							</tr>
							<tbody id="testBody">
								<c:forEach var="itemBean" items="${itemList}">
									<tr id="item${itemBean.id}">
										<td align="center">
											<input type="checkbox" name="itemid_chk" value="${itemBean.id}" />
										</td>
										<td align="center">${itemBean.id}</td>
										<td align="center">
											<a href="#">${itemBean.name}</a>
										</td>
										<td align="center">
											<fmt:formatNumber value="${itemBean.price}" pattern="#.00"></fmt:formatNumber>
											￥
										</td>
										<%-- 									<td align="center"><p style="text-indent: 2em;"align="left";>${itemBean.description}</p></td> --%>
										<td align="center">
											<a href="#">${itemBean.maxname}</a>
										</td>
										<td align="center">
											<a href="#">${itemBean.minname}</a>
										</td>
										<td align="center">${itemBean.dcdate}</td>
										<td align="center">
											<a href="${contextPath}/servlet/ItemServlet?task=edit&id=${itemBean.id}">编辑</a>
											|
											<a href="#" onclick="deleteItem(${itemBean.id},this);">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
				<div class="clear"></div>
				<div class="pager">${pageTool}</div>
			</div>
		</div>
		<div class="clear"></div>
		<c:import url="/admin/common/footer.jsp"></c:import>
		<div class="clear"></div>
	</div>
</body>
</html>
