package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.OrderBean;
import bean.OrderItemBean;
import dao.OrderItemBeanDao;
import utils.C3P0Util;

public class OrderItemBeanDaoImpl implements OrderItemBeanDao {
	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public int getOrderItemCount(String orderid) {
		// TODO Auto-generated method stub
		Long orderCountLong = null;
		try {

			orderCountLong = runner.query("select count(nid) from t_order_item where orderid = " + orderid,
					new ScalarHandler<Long>());

			String tString = orderCountLong.toString();
			int orderCount = Integer.parseInt(tString);
			if (orderCount > 0) {
				return orderCount;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<OrderItemBean> getOrderItemList(int startIndex, int pageSize, String orderid) {
		List<OrderItemBean> orderItemList = null;
		try {
			if (orderid.equals("0")) {
				orderItemList = runner.query(
						"select * from t_order_item order by nid desc limit " + startIndex + "," + pageSize + "",
						new BeanListHandler<OrderItemBean>(OrderItemBean.class));
			} else {
				orderItemList = runner.query("select * from t_order_item where orderid= " + orderid + " order by nid desc limit "
						+ startIndex + "," + pageSize + "", new BeanListHandler<OrderItemBean>(OrderItemBean.class));
			}
			// System.out.println(itemBeans);
			// System.out.println("getItemList="+"select * from t_mc where
			// isdel=0 "+ this.getSearchSql(searchMap)+" order by id desc limit
			// " + startIndex + "," + pageSize + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return orderItemList;
	}

}
