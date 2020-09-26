package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.sun.xml.internal.bind.v2.model.core.ID;

import bean.CategoryBean;
import bean.ItemBean;
import bean.UserBean;
import service.CategoryBeanService;
import service.ItemBeanService;
import service.UserBeanService;
import service.impl.CategoryBeanServiceImpl;
import service.impl.ItemBeanServiceImpl;
import service.impl.UserBeanServiceImpl;
import utils.DateUtil;
import utils.GlobalUtil;
import utils.GlobalUtil.UserType;
import utils.JsonUtil;
import utils.PageUtil;
import utils.ResponseData;

@WebServlet(name = "RegServlet", urlPatterns = "/servlet/RegServlet")
public class RegServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("login")) {
			this.login(request, response);
		} else if (task.equals("modifySelf")) {
			this.modifySelf(request, response);
		} else if (task.equals("check")) {
			this.check(request, response);
		} else if (task.equals("register")) {
			this.register(request, response);
		} else if (task.equals("updatePass")) {
			this.updatePass(request, response);
		} else if (task.equals("findPass")) {
			this.findPass(request, response);
		}
	}

	/**
	 * Title: findPass
	 * Description: 忘记密码
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月28日  
	*/
	private void findPass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		try {
			UserBeanService userBeanService = new UserBeanServiceImpl();
			out = response.getWriter();
			// 1.获取表单中提交的数据
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			System.out.println("调用changePwd........");
			// 2.调用service查找密码
			UserBean userBean = userBeanService.findByUserName(username);
			if (userBean.getUsername().equals(username) && userBean.getRealname().equals(realname)
					&& userBean.getEmail().equals(email) && userBean.getPhone().equals(phone)) {
				responseData.setMessage("密码找回成功，请牢记您的密码：" + userBean.getPassword());
			}
			responseData.setFlag(true);

		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("找回失败，错误信息：" + e.getMessage());
		}
		// 讲对象转换为json格式的字符串
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: updatePass
	 * Description: 注册用户修改密码
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月11日  
	*/
	private void updatePass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			String newpwd = request.getParameter("newpassword");
			String uid = request.getParameter("uid");
			System.out.println("调用changePwd........");
			// 2.调用service修改密码
			UserBeanService userBeanService = new UserBeanServiceImpl();
			userBeanService.changePwd(uid, newpwd);
			responseData.setFlag(true);
			responseData.setMessage("密码修改成功，请重新登录");

		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("修改失败，错误信息：" + e.getMessage());
		}
		// 讲对象转换为json格式的字符串
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: register
	 * Description: 新用户注册
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月11日  
	*/
	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		Map<String, Object> dataMap = new LinkedHashMap<>();
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			FileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);
			List<FileItem> itemList = fileUpload.parseRequest(request);
			for (FileItem fileItem : itemList) {
				if (fileItem.isFormField()) {
					// 说明是表单组件
					String filed_name = fileItem.getFieldName();
					String filed_value = fileItem.getString("UTF-8");
					if (filed_name.equals("birthday")) {
						Date birthday = DateUtil.strToDate(filed_value);
						dataMap.put(filed_name, birthday);
						continue;
					}
					dataMap.put(filed_name, filed_value);
				}
			}

			if (dataMap != null) {
				String regdate1 = DateUtil.getSystemDate();
				System.out.println(regdate1);
				Date regdate = DateUtil.strToDate(regdate1);
				dataMap.put("regdate", regdate);
				dataMap.put("islock", 0);
				dataMap.put("logtime", 0);
				dataMap.put("level", 9);
			}

			// 2.将数据封装到bean中
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(userBean, dataMap);
			// System.out.println(userBean);
			// 3.调用service添加系统用户
			UserBeanService userBeanService = new UserBeanServiceImpl();
			userBeanService.insertRegUser(userBean);
			responseData.setFlag(true);
			responseData.setMessage("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("注册失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: check
	 * Description: 检查用户名是否存在
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月11日  
	*/
	private void check(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		String username = request.getParameter("username");
		UserBeanService userBeanService = new UserBeanServiceImpl();
		try {
			out = response.getWriter();
			UserBean userBean = userBeanService.findByUserName(username);
			if (userBean != null) {
				responseData.setFlag(false);
				responseData.setMessage("用户名已存在");
			} else {
				responseData.setFlag(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("注册失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: modifySelf
	 * Description: 注册用户修改个人信息
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	private void modifySelf(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		ResponseData responseData = new ResponseData();
		Map<String, Object> dataMap = new LinkedHashMap<>();
		HttpSession session = request.getSession();
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			FileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);
			List<FileItem> itemList = fileUpload.parseRequest(request);
			for (FileItem fileItem : itemList) {
				if (fileItem.isFormField()) {
					// 说明是表单组件
					String filed_name = fileItem.getFieldName();
					String filed_value = fileItem.getString("UTF-8");
					if (filed_name.equals("birthday")) {
						Date birthday = DateUtil.strToDate(filed_value);
						dataMap.put(filed_name, birthday);
						continue;
					}
					dataMap.put(filed_name, filed_value);
				}
			}

			// 2.将数据封装到bean中
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(userBean, dataMap);
			userBean.setUid(uid);
			// System.out.println("userBean===" + userBean);
			// 3.调用service添加系统用户
			UserBeanService userBeanService = new UserBeanServiceImpl();
			userBeanService.updateRegUser(userBean);

			if (session.getAttribute("regUserBean") != null) {
				userBean = userBeanService.getUserById(uid);
				session.removeAttribute("regUserBean");
				session.setAttribute("regUserBean", userBean);
				System.out.println("更换session中的用户：" + userBean);
			}

			responseData.setFlag(true);
			responseData.setMessage("用户数据修改成功");
		} catch (Exception e) {
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
	 * Title: login
	 * Description: 注册用户登录函数
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月5日  
	*/
	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}
}
