package service.impl;

import java.util.List;

import bean.OrderItemBean;
import dao.OrderItemBeanDao;
import dao.impl.OrderItemBeanDaoImpl;
import service.OrderItemBeanService;

public class OrderItemBeanServiceImpl implements OrderItemBeanService{
	OrderItemBeanDao orderItemBeanDao=new OrderItemBeanDaoImpl();

	@Override
	public int getOrderItemCount(String orderid) {
		// TODO Auto-generated method stub
		return orderItemBeanDao.getOrderItemCount(orderid);
	}

	@Override
	public List<OrderItemBean> getOrderItemList(int startIndex, int pageSize, String orderid) {
		// TODO Auto-generated method stub
		return orderItemBeanDao.getOrderItemList(startIndex,pageSize,orderid);
	}
	
}
