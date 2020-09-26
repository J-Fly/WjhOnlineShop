package service;

import java.util.List;

import bean.OrderItemBean;

public interface OrderItemBeanService {

	
	int getOrderItemCount(String orderid);

	List<OrderItemBean> getOrderItemList(int startIndex, int pageSize, String orderid);

}
