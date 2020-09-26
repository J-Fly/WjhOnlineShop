package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CategoryBean;
import bean.ItemBean;
import bean.OrderBean;
import bean.OrderItemBean;
import bean.UserBean;
import bean.User_Order;
import service.CartService;
import service.CategoryBeanService;
import service.ItemBeanService;
import service.OrderBeanService;
import service.OrderItemBeanService;
import service.impl.CartServiceImpl;
import service.impl.CategoryBeanServiceImpl;
import service.impl.ItemBeanServiceImpl;
import service.impl.OrderBeanServiceImpl;
import service.impl.OrderItemBeanServiceImpl;
import utils.GlobalUtil;
import utils.JsonUtil;
import utils.PageUtil;
import utils.ResponseData;

@WebServlet(name = "OrderServlet", urlPatterns = "/servlet/OrderServlet")
public class OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("newOrder")) {
			this.newOrder(request, response);
		} else if (task.equals("getOrderList")) {
			this.getOrderList(request, response);
		} else if (task.equals("payOrder")) {
			this.payOrder(request, response);
		} else if (task.equals("list")) {
			this.list(request, response);
		} else if (task.equals("orderDetail")) {
			this.orderDetail(request, response);
		} else if (task.equals("edit")) {
			this.edit(request, response);
		} else if (task.equals("modify")) {
			this.modify(request, response);
		} else if (task.equals("deliver")) {
			this.deliver(request, response);
		} else if (task.equals("statistics")) {
			this.statistics(request, response);
		} else if (task.equals("currentDayOrders")) {
			this.currentDayOrders(request, response);
		} else if (task.equals("searchList")) {
			this.searchList(request, response);
		}
	}

	/**
	 * Title: searchList
	 * Description: 根据查询条件展示订单列表
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月10日  
	*/
	private void searchList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		// 获取查询条件
		String p_ispay = request.getParameter("p_ispay");
		String p_username = request.getParameter("p_username");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_ispay", p_ispay);
		searchMap.put("p_username", p_username);
		// System.out.println(searchMap);
		try {
			OrderBeanService orderBeanService = new OrderBeanServiceImpl();
			// 实例化分页对象
			PageUtil pageUtil = new PageUtil(request);
			pageUtil.setPageSize(12);
			// 获取分页的四个变量
			// 1.总记录数
			pageUtil.setRsCount(orderBeanService.getOrderCount(searchMap));
			// 2.当前页码
			int currentPage = pageUtil.getCurrentPage();
			// 3.总页数
			int pageCount = pageUtil.getPageCount();
			// 4.每页显示多少条
			int pageSize = pageUtil.getPageSize();

			// 产生工具栏
			String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
			// 设置每页显示的起始位置
			int startIndex = (currentPage - 1) * pageSize;
			List<OrderBean> orderBeans = orderBeanService.getOrderList(startIndex, pageSize, searchMap);
			// 将查询条件存放到作用域中
			request.setAttribute("p_ispay", p_ispay);
			request.setAttribute("p_username", p_username);
			request.setAttribute("pageTool", pageTool);
			request.setAttribute("orderList", orderBeans);
			request.getRequestDispatcher("/admin/order/order.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Title: currentDayOrders
	 * Description: 查看用户当日所有订单
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月10日  
	*/
	private void currentDayOrders(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		String ouser = request.getParameter("ouser");
		String odate = request.getParameter("odate");
		// 实例化分页对象
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(12);
		// 获取分页的四个变量
		// 1.总记录数
		pageUtil.setRsCount(orderBeanService.getDayOrderCount(ouser, odate));
		// 2.当前页码
		int currentPage = pageUtil.getCurrentPage();
		// 3.总页数
		int pageCount = pageUtil.getPageCount();
		// 4.每页显示多少条
		int pageSize = pageUtil.getPageSize();

		// 产生工具栏
		String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
		// 设置每页显示的起始位置
		int startIndex = (currentPage - 1) * pageSize;
		List<OrderBean> orderBeans = orderBeanService.getOrderList(startIndex, pageSize, ouser, odate);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("orderList", orderBeans);
		System.out.println(orderBeans);
		try {
			request.getRequestDispatcher("/admin/order/order.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: statistics
	 * Description: 跳转到订单统计页面
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月10日  
	*/
	private void statistics(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		// 获取查询条件
		String p_odate = request.getParameter("p_odate");
		String p_username = request.getParameter("p_username");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_odate", p_odate);
		searchMap.put("p_username", p_username);
		System.out.println(p_odate);
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		// 实例化分页对象
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(10);
		// 获取分页的四个变量
		// 1.总记录数
		pageUtil.setRsCount(orderBeanService.getUserOrdersCount(searchMap));
		// 2.当前页码
		int currentPage = pageUtil.getCurrentPage();
		// 3.总页数
		int pageCount = pageUtil.getPageCount();
		// 4.每页显示多少条
		int pageSize = pageUtil.getPageSize();

		// 产生工具栏
		String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
		// 设置每页显示的起始位置
		int startIndex = (currentPage - 1) * pageSize;
		List<User_Order> user_Orders = orderBeanService.getUserOrders(startIndex, pageSize, searchMap);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("p_odate", p_odate);
		request.setAttribute("p_username", p_username);
		request.setAttribute("user_Orders", user_Orders);
		System.out.println(user_Orders);
		try {
			request.getRequestDispatcher("/admin/order/orderStatistics.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: deliver
	 * Description: 管理员发货
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月10日  
	*/
	private void deliver(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		String orderid = request.getParameter("orderid");
		OrderBean orderBean = orderBeanService.getOrderBeanByOrderId(orderid);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		try {
			out = response.getWriter();
			if (orderBean.getPaytype().equals("是")) {
				orderBean.setSendtype("是");
				Date adate = new Date();
				orderBean.setAdate(adate);
				orderBean.setAuser(userBean.getUsername());
				orderBeanService.updateOrderBean(orderBean);
				responseData.setFlag(true);
				responseData.setMessage("发货成功，点击确定刷新页面");
			} else {
				responseData.setFlag(false);
				responseData.setMessage("用户还未付款，不可发货");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("发货失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: modify
	 * Description: 修改订单信息
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月10日  
	*/
	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		String orderid = request.getParameter("orderid");
		String getaddress = request.getParameter("getaddress");
		String getpostcode = request.getParameter("getpostcode");
		String getphone = request.getParameter("getphone");
		String getemail = request.getParameter("getemail");
		System.out.println(orderid + "\t" + getaddress + "\t" + getpostcode + "\t" + getphone + "\t" + getemail);
		OrderBean orderBean = orderBeanService.getOrderBeanByOrderId(orderid);
		orderBean.setGetaddress(getaddress);
		orderBean.setGetemail(getemail);
		orderBean.setGetphone(getphone);
		orderBean.setGetpostcode(getpostcode);
		try {
			orderBeanService.updateOrderBean(orderBean);
			out = response.getWriter();
			responseData.setFlag(true);
			responseData.setMessage("订单信息修改成功，点击确定刷新页面");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("修改失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: edit
	 * Description: 跳转到订单编辑页面
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月9日  
	*/
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String orderid = request.getParameter("orderid");
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		try {
			// 获取订单信息
			OrderBean orderBean = orderBeanService.getOrderBeanByOrderId(orderid);

			request.setAttribute("editOrder", orderBean);
			request.getRequestDispatcher("/admin/order/editOrder.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: orderDetail
	 * Description: 订单详情页
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月9日  
	*/
	private void orderDetail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		OrderItemBeanService orderItemBeanService = new OrderItemBeanServiceImpl();
		String orderid = request.getParameter("orderid");
		// 实例化分页对象
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(5);
		// 获取分页的四个变量
		// 1.总记录数
		pageUtil.setRsCount(orderItemBeanService.getOrderItemCount(orderid));
		// 2.当前页码
		int currentPage = pageUtil.getCurrentPage();
		// 3.总页数
		int pageCount = pageUtil.getPageCount();
		// 4.每页显示多少条
		int pageSize = pageUtil.getPageSize();

		// 产生工具栏
		String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
		// 设置每页显示的起始位置
		int startIndex = (currentPage - 1) * pageSize;
		List<OrderItemBean> OrderItemBeans = orderItemBeanService.getOrderItemList(startIndex, pageSize, orderid);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("OrderItemList", OrderItemBeans);
		System.out.println(OrderItemBeans);
		try {
			request.getRequestDispatcher("/admin/order/orderDetail.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: list
	 * Description: 管理员端展示订单
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月9日  
	*/
	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		// 实例化分页对象
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(12);
		// 获取分页的四个变量
		// 1.总记录数
		pageUtil.setRsCount(orderBeanService.getOrderCount("0"));
		// 2.当前页码
		int currentPage = pageUtil.getCurrentPage();
		// 3.总页数
		int pageCount = pageUtil.getPageCount();
		// 4.每页显示多少条
		int pageSize = pageUtil.getPageSize();

		// 产生工具栏
		String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
		// 设置每页显示的起始位置
		int startIndex = (currentPage - 1) * pageSize;
		List<OrderBean> orderBeans = orderBeanService.getOrderList(startIndex, pageSize, "0");
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("orderList", orderBeans);
		System.out.println(orderBeans);
		try {
			request.getRequestDispatcher("/admin/order/order.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: payOrder
	 * Description: 用户付款
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月9日  
	*/
	private void payOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String orderid = request.getParameter("orderid");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		OrderBean orderBean = null;
		HttpSession session = request.getSession();
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		try {
			out = response.getWriter();
			orderBean = orderBeanService.getOrderBeanByOrderId(orderid);
			System.out.println(orderBean);
			if (orderBean.getPaytype().equals("否")) {
				orderBean.setPaytype("是");
				orderBeanService.updateOrderBean(orderBean);
				responseData.setFlag(true);
				responseData.setMessage("付款成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("付款失败，错误信息：" + e.getMessage());
		}

		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();

	}

	/**
	 * Title: getOrderList
	 * Description: 根据uid获取订单列表
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月8日  
	*/
	private void getOrderList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		OrderBeanService orderBeanService = new OrderBeanServiceImpl();
		// 实例化分页对象
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(8);
		// 获取分页的四个变量
		// 1.总记录数
		pageUtil.setRsCount(orderBeanService.getOrderCount(uid));
		// 2.当前页码
		int currentPage = pageUtil.getCurrentPage();
		// 3.总页数
		int pageCount = pageUtil.getPageCount();
		// 4.每页显示多少条
		int pageSize = pageUtil.getPageSize();

		// 产生工具栏
		String pageTool = pageUtil.createPageTool(PageUtil.BbsText);
		// 设置每页显示的起始位置
		int startIndex = (currentPage - 1) * pageSize;
		List<OrderBean> orderBeans = orderBeanService.getOrderList(startIndex, pageSize, uid);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("orderList", orderBeans);
		try {
			request.getRequestDispatcher("/guest/showOrderForm.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Title: newOrder
	 * Description: 提交订单
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月7日  
	*/
	private void newOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		HttpSession session = request.getSession();
		CartBean cartBean = null;
		UserBean regUserBean = null;
		String msg = request.getParameter("msg");
		Set<Integer> set = new HashSet<>();
		try {
			out = response.getWriter();
			// 调用业务方法
			if (session.getAttribute("regUserBean") == null) {
				responseData.setFlag(false);
				responseData.setMessage("您还未登录系统，请先登录再提交订单");
			} else if (session.getAttribute("cartBean") == null) {
				throw new RuntimeException("会话中的购物车为空，提交订单失败");
			} else {
				regUserBean = (UserBean) session.getAttribute("regUserBean");
				cartBean = (CartBean) session.getAttribute("cartBean");
				List<ItemBean> itemList = cartBean.getItemList();
				// 删除购物车中的商品
				Iterator<ItemBean> iterator = itemList.iterator();
				while (iterator.hasNext()) {
					ItemBean rowBean = iterator.next();
					// System.out.println("rowBean==" + rowBean);
					int cid = rowBean.getMinid();
					set.add(cid);
				}
				int mctypesize = set.size();
				// System.out.println("mctypesize=" + mctypesize);
				OrderBeanService orderBeanService = new OrderBeanServiceImpl();
				orderBeanService.insert(regUserBean, cartBean, mctypesize, msg, itemList);
				cartBean.setTotalCount(0);
				cartBean.getItemList().clear();
				cartBean.setTotalPrice(new BigDecimal(0.00));
				// 计算购物车中的金额信息
				CartService cartService = new CartServiceImpl();
				cartBean = cartService.updateCart(cartBean);
				// 重新将cartBean放入到session中
				session.setAttribute("cartBean", cartBean);
				responseData.setFlag(true);
				responseData.setData(cartBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("提交失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		// System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
