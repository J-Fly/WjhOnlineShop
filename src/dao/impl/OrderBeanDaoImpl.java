package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.CartBean;
import bean.ItemBean;
import bean.OrderBean;
import bean.UserBean;
import bean.User_Order;
import dao.OrderBeanDao;
import utils.C3P0Util;
import utils.DateUtil;
import utils.GlobalUtil;

public class OrderBeanDaoImpl implements OrderBeanDao {
	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	public String getSearchSql(Map<String, Object> searchMap) {
		StringBuilder whereSQL = new StringBuilder();
		whereSQL.setLength(0);
		String p_ispay = String.valueOf(searchMap.get("p_ispay"));
		String p_username = String.valueOf(searchMap.get("p_username"));
		String p_odate = String.valueOf(searchMap.get("p_odate"));
		if (GlobalUtil.isNotNull(p_ispay)) {
			whereSQL.append(" and paytype = '" + p_ispay + "'");
		}
		if (GlobalUtil.isNotNull(p_username)) {
			whereSQL.append(" and ouser = '" + p_username + "'");
		}
		if (GlobalUtil.isNotNull(p_odate)) {
			whereSQL.append(" and odate like '%" + p_odate + "%'");
		}
		return whereSQL.toString();
	}

	@Override
	public int getMaxCid() {
		// TODO Auto-generated method stub
		try {
			Integer maxcid = runner.query("select max(orderid) from t_order", new ScalarHandler<Integer>());
			// System.out.println("maxcid=" + maxcid);
			if (maxcid >= 1) {
				return maxcid + 1;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Title: insert
	 * Description: 提交订单dao层接口实现方法
	 * @param cartBean
	 * @param mctypesize
	 * @param msg
	 * @see dao.OrderBeanDao#insert(bean.CartBean, int, java.lang.String)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年8月7日  
	*/
	@Override
	public void insert(UserBean regUserBean, CartBean cartBean, int mctypesize, String msg, List<ItemBean> itemList)
			throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = C3P0Util.getConnection();
			// 设置事务不自动提交
			connection.setAutoCommit(false);
			String sql1 = null;
			int maxid = this.getMaxCid();
			System.out.println(maxid);
			// 向订单表插入数据
			sql1 = "insert into t_order (orderid,ouser,odate,paytype,sendtype,mctypesize,mcsize,totalprice,status,msg,getname,getaddress,getpostcode,getphone,getemail) values("
					+ maxid + "," + regUserBean.getUsername() + ",NOW(),'否','否'," + mctypesize + ","
					+ cartBean.getTotalCount() + "," + cartBean.getTotalPrice_format() + "," + 1 + ",'" + msg + "','"
					+ regUserBean.getRealname() + "','" + regUserBean.getAddress() + "','" + regUserBean.getPostcode()
					+ "','" + regUserBean.getPhone() + "','" + regUserBean.getEmail() + "')";
			System.out.println(sql1);
			runner.update(connection, sql1, null);

			// 向订单明细表插入数据
			for (ItemBean itemBean : itemList) {
				String sql2 = "insert into t_order_item(orderid,itemid,itemname,itemdescription,itemimg,count,price,totalprice) values("
						+ maxid + "," + itemBean.getId() + ",'" + itemBean.getName() + "','" + itemBean.getDescription()
						+ "','" + itemBean.getFilepath() + "'," + itemBean.getSingleCount() + "," + itemBean.getPrice()
						+ "," + itemBean.getSinglePrice() + ")";
				System.out.println(sql2);
				runner.update(connection, sql2, null);
			}

			connection.commit();
		} catch (Exception e) {
			// 处理事务中发生的异常,进行回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			C3P0Util.closeAll(connection, null, null);
		}

	}

	@Override
	public List<OrderBean> getOrderList(int startIndex, int pageSize, String uid) {
		List<OrderBean> orderBeans = null;
		try {
			if (uid.equals("0")) {
				orderBeans = runner.query(
						"select * from t_order order by orderid desc limit " + startIndex + "," + pageSize + "",
						new BeanListHandler<OrderBean>(OrderBean.class));
			} else {
				orderBeans = runner.query("select * from t_order where ouser= " + uid + " order by orderid desc limit "
						+ startIndex + "," + pageSize + "", new BeanListHandler<OrderBean>(OrderBean.class));
			}
			// System.out.println(itemBeans);
			// System.out.println("getItemList="+"select * from t_mc where
			// isdel=0 "+ this.getSearchSql(searchMap)+" order by id desc limit
			// " + startIndex + "," + pageSize + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return orderBeans;
	}

	@Override
	public int getOrderCount(String uid) {
		Long orderCountLong = null;
		try {
			if (uid.equals("0")) {
				orderCountLong = runner.query("select count(orderid) from t_order", new ScalarHandler<Long>());
			} else {
				orderCountLong = runner.query("select count(orderid) from t_order where ouser = " + uid,
						new ScalarHandler<Long>());
			}

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
	public OrderBean getOrderBeanByOrderId(String orderid) {
		// TODO Auto-generated method stub
		OrderBean orderBean = null;
		try {
			orderBean = runner.query("select * from t_order where orderid = " + orderid + "",
					new BeanHandler<OrderBean>(OrderBean.class));
			// System.out.println(itemBeans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return orderBean;
	}

	@Override
	public void updateOrderBean(OrderBean orderBean) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update t_order set ouser='" + orderBean.getOuser() + "',odate='" + orderBean.getOdate()
				+ "',paytype='" + orderBean.getPaytype() + "',sendtype='" + orderBean.getSendtype() + "',mctypesize="
				+ orderBean.getMctypesize() + ",mcsize=" + orderBean.getMcsize() + ",totalprice="
				+ orderBean.getTotalprice() + ",status=" + orderBean.getStatus() + ",msg='" + orderBean.getMsg()
				+ "',auser='" + orderBean.getAuser() + "',getname='" + orderBean.getGetname() + "',getaddress='"
				+ orderBean.getGetaddress() + "',getpostcode='" + orderBean.getGetpostcode() + "',getphone='"
				+ orderBean.getGetphone() + "',getemail='" + orderBean.getGetemail() + "'";

		if (orderBean.getAdate() != null) {
			String adate = DateUtil.getSystemTime(orderBean.getAdate());
			sql = sql + ",adate='" + adate + "' where orderid=" + orderBean.getOrderid() + "";
		} else {
			sql = sql + " where orderid=" + orderBean.getOrderid() + "";
		}
		System.out.println(sql);
		try {
			runner.update(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("数据库持久层异常");
		}
	}

	@Override
	public List<User_Order> getUserOrders(int startIndex, int pageSize, Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner();
		Connection User_Order = null;
		List<User_Order> user_Orders = null;
		try {

			user_Orders = runner
					.query("SELECT ouser,getname,DATE_FORMAT(odate,'%Y-%m-%d') AS odate,SUM(if(paytype='是',1,0)) AS pay_count,SUM(if(paytype='否',1,0)) AS nopay_count , SUM(if(sendtype='是',1,0)) AS send_count,SUM(if(sendtype='否',1,0)) AS nosend_count  from t_order where 1=1 "
							+ this.getSearchSql(searchMap) + " GROUP BY DATE_FORMAT(odate,'%Y-%m-%d'),ouser limit "
							+ startIndex + "," + pageSize + "", new BeanListHandler<User_Order>(User_Order.class));
			System.out.println(
					"SELECT ouser,getname,DATE_FORMAT(odate,'%Y-%m-%d') AS odate,SUM(if(paytype='是',1,0)) AS pay_count,SUM(if(paytype='否',1,0)) AS nopay_count , SUM(if(sendtype='是',1,0)) AS send_count,SUM(if(sendtype='否',1,0)) AS nosend_count  from t_order where 1=1 "
							+ this.getSearchSql(searchMap) + " GROUP BY DATE_FORMAT(odate,'%Y-%m-%d'),ouser limit "
							+ startIndex + "," + pageSize + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return user_Orders;
	}

	@Override
	public int getUserOrdersCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		Long orderCountLong = null;
		try {

			orderCountLong = runner.query(
					"select count(1) from (SELECT ouser,getname,DATE_FORMAT(odate,'%Y-%m-%d') AS odate,SUM(if(paytype='是',1,0)) AS pay_count,SUM(if(paytype='否',1,0)) AS nopay_count , SUM(if(sendtype='是',1,0)) AS send_count,SUM(if(sendtype='否',1,0)) AS nosend_count  from t_order where 1=1 "
							+ this.getSearchSql(searchMap) + " GROUP BY DATE_FORMAT(odate,'%Y-%m-%d'),ouser) t_order",
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
	public int getDayOrderCount(String ouser, String odate) {
		Long orderCountLong = null;
		try {

			orderCountLong = runner.query("select count(orderid) from t_order where ouser = " + ouser
					+ " and DATE_FORMAT(odate, '%Y-%m-%d')='" + odate + "'", new ScalarHandler<Long>());

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
	public List<OrderBean> getOrderList(int startIndex, int pageSize, String ouser, String odate) {
		// TODO Auto-generated method stub
		List<OrderBean> orderBeans = null;
		try {

			orderBeans = runner.query(
					"select * from t_order where ouser= " + ouser + " and DATE_FORMAT(odate, '%Y-%m-%d') = '" + odate
							+ "' order by orderid desc limit " + startIndex + "," + pageSize + "",
					new BeanListHandler<OrderBean>(OrderBean.class));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return orderBeans;
	}

	@Override
	public int getOrderCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		try {
			Long orderCountLong = runner.query(
					"select count(orderid) from t_order where 1=1 " + this.getSearchSql(searchMap),
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
		return -1;
	}

	@Override
	public List<OrderBean> getOrderList(int startIndex, int pageSize, Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		List<OrderBean> orderBeans = null;
		try {
			orderBeans = runner.query("select * from t_order where 1=1 " + this.getSearchSql(searchMap)
					+ " order by orderid desc limit " + startIndex + "," + pageSize + "",
					new BeanListHandler<OrderBean>(OrderBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return orderBeans;
	}

}
