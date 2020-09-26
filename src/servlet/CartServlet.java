package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CategoryBean;
import bean.ItemBean;
import bean.UserBean;
import service.CartService;
import service.CategoryBeanService;
import service.ItemBeanService;
import service.UserBeanService;
import service.impl.CartServiceImpl;
import service.impl.CategoryBeanServiceImpl;
import service.impl.ItemBeanServiceImpl;
import service.impl.UserBeanServiceImpl;
import utils.JsonUtil;
import utils.ResponseData;

@WebServlet(name = "CartServlet", urlPatterns = "/servlet/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("list")) {
			this.list(request, response);
		} else if (task.equals("add")) {
			this.addToCart(request, response);
		} else if (task.equals("saveData")) {
			this.saveData(request, response);
		} else if (task.equals("clear")) {
			this.clearCart(request, response);
		} else if (task.equals("delete")) {
			this.deleteCart(request, response);
		} else if (task.equals("update")) {
			this.updateCart(request, response);
		} else if (task.equals("list_newpage")) {
			this.list_newpage(request, response);
		}
	}

	/**
	 * Title: list_newpage
	 * Description: 整个页面跳转到购物车
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月7日  
	*/
	private void list_newpage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		CartBean cartBean = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("cartBean") == null) {
			cartBean = new CartBean();
		} else {
			cartBean = (CartBean) session.getAttribute("cartBean");
		}
		try {

			request.setAttribute("cartBean", cartBean);
			request.getRequestDispatcher("/guest/showCar_2.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: updateCart
	 * Description: 更新购物车中的商品数量和价格
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月5日  
	*/
	private void updateCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		HttpSession session = request.getSession();
		CartBean cartBean = null;
		String itemid = request.getParameter("itemid");
		String newCount=request.getParameter("newCount");
		try {
			out = response.getWriter();
			// 调用业务方法
			if (session.getAttribute("cartBean") == null) {
				throw new RuntimeException("会话中的购物车为空，移除失败");
			} else {
				cartBean = (CartBean) session.getAttribute("cartBean");
			}
			List<ItemBean> itemList = cartBean.getItemList();
			// 删除购物车中的商品
			Iterator<ItemBean> iterator = itemList.iterator();
			while (iterator.hasNext()) {
				ItemBean rowBean = iterator.next();
				int row_itemId = rowBean.getId();
				if (Integer.parseInt(itemid)==row_itemId) {
					rowBean.setSingleCount(Integer.parseInt(newCount));
					break;
				}
			}
			// 计算购物车中的金额信息
			CartService cartService = new CartServiceImpl();
			cartBean = cartService.updateCart(cartBean);
			// 重新将cartBean放入到session中
			session.setAttribute("cartBean", cartBean);
			responseData.setFlag(true);
			responseData.setData(cartBean);
		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(true);
			responseData.setMessage("移除失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		// System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: deleteCart
	 * Description: 删除购物车中的商品
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月5日  
	*/
	private void deleteCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		HttpSession session = request.getSession();
		CartBean cartBean = null;
		String itemid = request.getParameter("itemid");
		try {
			out = response.getWriter();
			// 调用业务方法
			if (session.getAttribute("cartBean") == null) {
				throw new RuntimeException("会话中的购物车为空，移除失败");
			} else {
				cartBean = (CartBean) session.getAttribute("cartBean");
			}
			List<ItemBean> itemList = cartBean.getItemList();
			// 删除购物车中的商品
			Iterator<ItemBean> iterator = itemList.iterator();
			while (iterator.hasNext()) {
				ItemBean rowBean = iterator.next();
				int row_itemId = rowBean.getId();
				if (Integer.parseInt(itemid)==row_itemId) {
					iterator.remove();
				}
			}
			// 计算购物车中的金额信息
			CartService cartService = new CartServiceImpl();
			cartBean = cartService.updateCart(cartBean);
			// 重新将cartBean放入到session中
			session.setAttribute("cartBean", cartBean);
			responseData.setFlag(true);
			responseData.setData(cartBean);
		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(true);
			responseData.setMessage("移除失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		// System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: clearCart  
	 * Description: 清空购物车  
	 * @param request
	 * @param response  
	 * @author wjh
	 */
	private void clearCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		HttpSession session = request.getSession();
		CartBean cartBean = null;
		try {
			out = response.getWriter();
			// 调用业务方法
			if (session.getAttribute("cartBean") == null) {
				cartBean = new CartBean();
			} else {
				cartBean = (CartBean) session.getAttribute("cartBean");
			}
			cartBean.setTotalCount(0);
			cartBean.getItemList().clear();
			cartBean.setTotalPrice(new BigDecimal(0.00));

			// 计算购物车中的金额信息
			CartService cartService = new CartServiceImpl();
			cartBean = cartService.updateCart(cartBean);
			// 重新将cartBean放入到session中
			session.setAttribute("cartBean", cartBean);
			responseData.setFlag(true);
			responseData.setMessage("成功清空购物车所有商品");
			responseData.setData(cartBean);
		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(true);
			responseData.setMessage("清空购物车失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		// System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: saveData 
	 * Description: 保存类别到数据库中
	 * @param request
	 * @param response
	 * @author wjh
	 */
	private void saveData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			ResponseData responseData = new ResponseData();
			// 1.获取表单中提交的数据
			String parentid = request.getParameter("parentid");
			String cid = request.getParameter("cid");
			String classname = request.getParameter("classname");
			if (classname == null || classname.equals("")) {
				responseData.setFlag(false);
				responseData.setMessage("类别名不能为空");
				System.out.println("类别名不能为空");
			} else {
				// 2.将数据封装到bean中
				CategoryBean categoryBean = new CategoryBean(Integer.parseInt(cid), classname,
						Integer.parseInt(parentid));
				// 3.调用service保存类别
				CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
				categoryBeanService.insertCategory(categoryBean);
				responseData.setFlag(true);
				responseData.setMessage("数据添加成功，是否继续添加？");
			}
			PrintWriter out = response.getWriter();
			// 讲对象转换为json格式的字符串
			String jsonStr = JsonUtil.parseString(responseData);
			out.print(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: addToCart  
	 * Description:添加商品到购物车  
	 * @param request
	 * @param response  
	 * @author wjh
	 */
	private void addToCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		HttpSession session = request.getSession();
		CartBean cartBean = null;
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			String itemid = request.getParameter("itemid");
			// 调用业务方法
			ItemBeanService itemBeanService = new ItemBeanServiceImpl();
			ItemBean itemBean = itemBeanService.getItemById(itemid);
			if (session.getAttribute("cartBean") == null) {
				cartBean = new CartBean();
			} else {
				cartBean = (CartBean) session.getAttribute("cartBean");
			}
			List<ItemBean> itemList = cartBean.getItemList();
			// 在将商品添加到购物车之前，先将商品合并
			boolean isExist = false;
			Iterator<ItemBean> iterator = itemList.iterator();
			while (iterator.hasNext()) {
				ItemBean rowBean = iterator.next();
				int row_itemId = rowBean.getId();
				if (row_itemId == Integer.parseInt(itemid)) {
					// 数量加1
					rowBean.setSingleCount(rowBean.getSingleCount() + 1);
					isExist = true;
					break;
				}
			}
			if (isExist == false) {
				itemList.add(itemBean);
			}
			// 计算购物车中的金额信息
			CartService cartService = new CartServiceImpl();
			cartBean = cartService.updateCart(cartBean);
			// 重新将cartBean放入到session中
			session.setAttribute("cartBean", cartBean);
			responseData.setFlag(true);
			responseData.setMessage("成功添加购物车");
			responseData.setData(cartBean);
		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(true);
			responseData.setMessage("添加购物车失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
//		System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		CartBean cartBean = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("cartBean") == null) {
			cartBean = new CartBean();
		} else {
			cartBean = (CartBean) session.getAttribute("cartBean");
		}
		try {
			request.setAttribute("cartBean", cartBean);
			request.getRequestDispatcher("/guest/showCar.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
