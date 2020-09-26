package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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

import bean.UserBean;
import service.UserBeanService;
import service.impl.UserBeanServiceImpl;
import utils.JsonUtil;
import utils.ResponseData;

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("getRandomCode")) {
			this.getRandomCode(request, response);
		} else if (task.equals("loginAdmin")) {
			this.loginAdmin(request, response);
		} else if (task.equals("loginRegUser")) {
			this.loginRegUser(request, response);
		} else if (task.equals("logoutAdmin")) {
			this.logoutAdmin(request, response);
		} else if (task.equals("logoutRegUser")) {
			this.logoutRegUser(request, response);
		}
	}

	/**
	 * Title: logoutRegUser
	 * Description: 注册用户退出系统
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月7日  
	*/
	private void logoutRegUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (session.getAttribute("regUserBean") != null) {
			session.removeAttribute("regUserBean");
		}
		String loginURL = request.getContextPath() + "/servlet/MainServlet?task=guestMainPage";
		try {
			response.sendRedirect(loginURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: loginRegUser
	 * Description: 注册用户登录
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	private void loginRegUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ResponseData responseData = new ResponseData();
		UserBean user = new UserBean();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("username=" + username);
		UserBeanService userBeanService = new UserBeanServiceImpl();
		if (userBeanService.login(user, 9) != null) {
			HttpSession session = request.getSession();
			// 1.将用户存入session
			user = userBeanService.login(user, 9);
			// System.out.println("登录用户："+user);
			session.setMaxInactiveInterval(-1);
			session.setAttribute("regUserBean", user);
			// 2.设置返回消息
			responseData.setFlag(true);
			responseData.setMessage("登录成功");
		} else {
			responseData.setFlag(false);
			responseData.setMessage("用户名密码不匹配，请重新输入");
			// System.out.println("用户名密码不匹配，请重新输入");
		}

		response.setContentType("text/html");
		try {
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
	 * Title: logoutAdmin
	 * Description: 管理员退出系统
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月29日  
	*/
	private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (session.getAttribute("userBean") != null) {
			session.removeAttribute("userBean");
		}
		String loginURL = request.getContextPath() + "/admin/login.jsp";
		try {
			response.sendRedirect(loginURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loginAdmin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserBeanService userBeanService = new UserBeanServiceImpl();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		ResponseData responseData = new ResponseData();
		// UserBean findUser=userBeanService.findByUserName(username);
		// int level=findUser.getLevel();
		UserBean user = new UserBean();
		user.setUsername(username);
		user.setPassword(password);

		if (userBeanService.login(user, 1) != null) {
			HttpSession session = request.getSession();
			// 1.将用户存入session
			user = userBeanService.login(user, 1);
			// System.out.println("登录用户："+user);
			session.setMaxInactiveInterval(-1);
			session.setAttribute("userBean", user);
			// 2.将session中的验证码清空
			session.removeAttribute("randomCode");
			// 3.设置返回消息
			responseData.setFlag(true);
			responseData.setMessage("超级管理员登录成功");
			responseData.setData("1");
		} else if (userBeanService.login(user, 5) != null) {
			HttpSession session = request.getSession();
			// 1.将用户存入session
			user = userBeanService.login(user, 5);
			// System.out.println("登录用户："+user);
			session.setMaxInactiveInterval(-1);
			session.setAttribute("userBean", user);
			// 2.将session中的验证码清空
			session.removeAttribute("randomCode");
			// 3.设置返回消息
			responseData.setFlag(true);
			responseData.setMessage("管理员登录成功");
			responseData.setData("5");
		} else {
			responseData.setFlag(false);
			responseData.setMessage("用户名密码不匹配，请重新输入");
			System.out.println("用户名密码不匹配，请重新输入");
		}

		response.setContentType("text/html");
		try {
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

	private void getRandomCode(HttpServletRequest request, HttpServletResponse response) {
		String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		String arr[] = new String[4];
		String b = "";
		for (int i = 0; i < 4; i++) {
			int n = r.nextInt(62);
			arr[i] = str.substring(n, n + 1);
			b += arr[i];
		}
		// System.out.println("验证码："+b);
		HttpSession session = request.getSession();
		session.setAttribute("randomCode", b);
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}

}
