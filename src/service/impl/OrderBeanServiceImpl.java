package service.impl;

import java.util.List;
import java.util.Map;

import bean.CartBean;
import bean.ItemBean;
import bean.OrderBean;
import bean.UserBean;
import bean.User_Order;
import dao.OrderBeanDao;
import dao.impl.OrderBeanDaoImpl;
import service.OrderBeanService;

public class OrderBeanServiceImpl implements OrderBeanService{
	OrderBeanDao orderBeanDao =new OrderBeanDaoImpl();
	
	/**
	 * Title: insert
	 * Description: 提交订单业务层接口实现方法
	 * @param cartBean
	 * @param mctypesize
	 * @param msg
	 * @see service.OrderBeanService#insert(bean.CartBean, int, java.lang.String)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年8月7日  
	*/
	@Override
	public void insert(UserBean regUserBean,CartBean cartBean, int mctypesize, String msg,List<ItemBean> itemList) throws Exception {
		// TODO Auto-generated method stub
		orderBeanDao.insert(regUserBean,cartBean,mctypesize,msg,itemList);
	}

	
	@Override
	public List<OrderBean> getOrderList(int startIndex, int pageSize,String uid) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderList(startIndex,pageSize,uid);
	}

	@Override
	public int getOrderCount(String uid) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderCount(uid);
	}



	@Override
	public OrderBean getOrderBeanByOrderId(String orderid) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderBeanByOrderId(orderid);
	}


	@Override
	public void updateOrderBean(OrderBean orderBean) throws Exception {
		// TODO Auto-generated method stub
		orderBeanDao.updateOrderBean(orderBean);
	}


	@Override
	public List<User_Order> getUserOrders(int startIndex, int pageSize,Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return orderBeanDao.getUserOrders(startIndex,pageSize,searchMap);
	}


	@Override
	public int getUserOrdersCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return orderBeanDao.getUserOrdersCount(searchMap);
	}



	@Override
	public int getDayOrderCount(String ouser, String odate) {
		// TODO Auto-generated method stub
		return orderBeanDao.getDayOrderCount(ouser,odate);
	}

	@Override
	public List<OrderBean> getOrderList(int startIndex, int pageSize, String ouser, String odate) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderList(startIndex,pageSize,ouser,odate);
	}


	@Override
	public int getOrderCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderCount(searchMap);
	}

	@Override
	public List<OrderBean> getOrderList(int startIndex, int pageSize, Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return orderBeanDao.getOrderList(startIndex, pageSize, searchMap);
	}
	
}
