package dao;

import java.util.List;

import bean.OrderItemBean;

public interface OrderItemBeanDao {

	int getOrderItemCount(String orderid);


	List<OrderItemBean> getOrderItemList(int startIndex, int pageSize, String orderid);

}
