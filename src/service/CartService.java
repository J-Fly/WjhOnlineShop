package service;

import bean.CartBean;

public interface CartService {

	/**
	 * Title: updateCart
	 * Description: 更新购物车数据业务层接口方法
	 * @param cartBean
	 * @return
	 * @author wjh
	 * @date 2020年8月4日  
	*/
	CartBean updateCart(CartBean cartBean);
	
}
