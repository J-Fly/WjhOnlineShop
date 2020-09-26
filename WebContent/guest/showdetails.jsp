<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/guest/style.css" />
<script type="text/javascript" src="${contextPath}/js/jquery.min.js"></script>
<style>
.main_iframe {
	Z-INDEX: 1;
	VISIBILITY: inherit;
	WIDTH: 100%;
	HEIGHT: 92%
}
</style>
<script type="text/javascript" src="${contextPath}/js/boxOver.js"></script>
<script type="text/javascript">
function addToCart(itemid) {
		var addURL="${contextPath}/servlet/CartServlet?task=add&itemid="+itemid+"&date="+Math.random()+"";
		jQuery.get(addURL,function(jsonData){
			var flag=jsonData.flag;
			if (flag==true) {
				var cartBean=jsonData.data;
				var totalCount=cartBean.totalCount;
				var totalPrice_format=cartBean.totalPrice_format;			
// 				self.top.location.reload();
// 				window.top.location.reload();
// 				console.log(window.parent.document.getElementById("p_totalCount"));
				window.parent.document.getElementById("p_totalCount").innerHTML="商品数量："+totalCount+" 件"; 
				window.parent.document.getElementById("p_totalPrice").innerHTML="商品金额："+totalPrice_format+" ￥"; 
				window.alert(jsonData.message);
			}else {
				window.alert(jsonData.message);
			}
		},"json");
	}
</script>
</head>
<body>

	<div id="">
		<table border="0" cellpadding="0" cellspacing="0" align="center" width=100% height=400px>
			<tr>
				<td class="cart_center_content" width=60%>

					<div class="cart_center_content">
						<div class="center_title_bar">${itemBeanDetail.name}</div>
						<div class="cart_prod_box_big">
							<div class="cart_top_prod_box_big"></div>
							<div class="cart_center_prod_box_big">
								<div class="product_img_big">
									<div>
										<img src="${contextPath}/upload/${itemBeanDetail.filepath}" alt="${itemBeanDetail.filename}" title="${itemBeanDetail.filename}" border="0" width="144px" height="144px" />
									</div>
								</div>
								<div class="details_big_box">
									<div class="product_title_big">${itemBeanDetail.name}</div>
									<div class="specifications">
										商品分类:
										<span class="blue">${itemBeanDetail.maxname }</span>
										<br />
										品牌产商:
										<span class="blue">${itemBeanDetail.minname }</span>
										<br />
										商品编号:
										<span class="blue">${itemBeanDetail.id }</span>
										<br />
										商品价格:
										<span class="blue">${itemBeanDetail.price}￥</span>
										<br />
									</div>
									<div class="prod_price_big">
										<span class="reduce"></span>
										<span class="price"></span>
									</div>
									<a href="javascript:void(0);" onclick="addToCart(${itemBeanDetail.id});" class="addtocart">添加到购物车</a>
								</div>
							</div>
							<div class="cart_bottom_prod_box_big"></div>
						</div>
						<div class="center_title_bar">商品描述</div>
						<div class="cart_prod_box_big">

							<table border=0 cellspacing="2">
								<tr>
									<td>
										<p style="font-size: 14px; width: 100%; padding-left: 0px; text-indent: 2em;">${itemBeanDetail.description }</p>
									</td>
								</tr>

							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- end of main_container -->
</body>
</html>