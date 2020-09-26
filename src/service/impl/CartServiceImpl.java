package service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import bean.CartBean;
import bean.ItemBean;
import service.CartService;

public class CartServiceImpl implements CartService{

	/**
	 * Title: updateCart
	 * Description: 更新购物车数据业务层接口实现方法
	 * @param cartBean
	 * @return
	 * @see service.CartService#updateCart(bean.CartBean)
	 * @author wjh
	 * @date 2020年8月4日  
	*/
	@Override
	public CartBean updateCart(CartBean cartBean) {
		// TODO Auto-generated method stub
		List<ItemBean> itemList = cartBean.getItemList();
		int totalCount=0;
		BigDecimal totalPrice=new BigDecimal(0.00);
		String totalPrice_format="0.00";
		for(ItemBean itemBean:itemList){
			int singleCount = itemBean.getSingleCount();
			BigDecimal itemPrice=itemBean.getPrice();
			BigDecimal singlePrice=itemPrice.multiply(new BigDecimal(singleCount));
			itemBean.setSinglePrice(singlePrice);
			totalCount+=singleCount;
			totalPrice=totalPrice.add(singlePrice);
		}
		DecimalFormat decimalFormat=new DecimalFormat("#0.00");
		totalPrice_format=decimalFormat.format(totalPrice);
		cartBean.setTotalCount(totalCount);
		cartBean.setTotalPrice(totalPrice);
		cartBean.setTotalPrice_format(totalPrice_format);
		return cartBean;
	}

}
