package service;

import java.util.List;
import java.util.Map;

import bean.CartBean;
import bean.ItemBean;
import bean.OrderBean;
import bean.UserBean;
import bean.User_Order;

public interface OrderBeanService {

	/**
	 * Title: insert
	 * Description: 提交订单业务层接口方法
	 * @param cartBean
	 * @param mctypesize
	 * @param msg
	 * @author wjh
	 * @param regUserBean 
	 * @param itemList 
	 * @throws Exception 
	 * @date 2020年8月7日  
	*/
	void insert(UserBean regUserBean, CartBean cartBean, int mctypesize, String msg, List<ItemBean> itemList)
			throws Exception;

	List<OrderBean> getOrderList(int startIndex, int pageSize, String uid);

	int getOrderCount(String uid);

	OrderBean getOrderBeanByOrderId(String orderid);

	void updateOrderBean(OrderBean orderBean) throws Exception;

	List<User_Order> getUserOrders(int startIndex, int pageSize, Map<String, Object> searchMap);

	int getUserOrdersCount(Map<String, Object> searchMap);

	int getDayOrderCount(String ouser, String odate);

	List<OrderBean> getOrderList(int startIndex, int pageSize, String ouser, String odate);

	int getOrderCount(Map<String, Object> searchMap);

	List<OrderBean> getOrderList(int startIndex, int pageSize, Map<String, Object> searchMap);

}
