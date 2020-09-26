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

@WebServlet(name = "UserServlet", urlPatterns = "/servlet/UserServlet")
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("sysList")) {
			this.sysList(request, response);
		} else if (task.equals("regList")) {
			this.regList(request, response);
		} else if (task.equals("editSelf")) {
			this.editSelf(request, response);
		} else if (task.equals("changePwd")) {
			this.changePwd(request, response);
		} else if (task.equals("edit")) {
			this.editPage(request, response);
		} else if (task.equals("addSysAdmin")) {
			this.addSysAdmin(request, response);
		} else if (task.equals("addRegUser")) {
			this.addRegUser(request, response);
		} else if (task.equals("modifySysAdmin")) {
			this.modifySysAdmin(request, response);
		} else if (task.equals("delete")) {
			this.delete(request, response);
		}
	}

	/**
	 * Title: changePwd
	 * Description: 修改密码
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	private void changePwd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			String newpwd = request.getParameter("newpwd");
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
	 * Title: addRegUser
	 * Description: 添加注册用户
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	private void addRegUser(HttpServletRequest request, HttpServletResponse response) {
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
			responseData.setMessage("用户数据添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("添加失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: modifySysAdmin
	 * Description: 修改系统管理员用户信息
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	private void modifySysAdmin(HttpServletRequest request, HttpServletResponse response) {
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
			System.out.println("userBean===" + userBean);
			// 3.调用service添加系统用户
			UserBeanService userBeanService = new UserBeanServiceImpl();
			userBeanService.updateRegUser(userBean);
			userBeanService.updateSysAdminUser(userBean);
			if (request.getParameter("self").equals("1")) {
				if (session.getAttribute("regUserBean") != null) {
					userBean = userBeanService.getUserById(uid);
					session.removeAttribute("regUserBean");
					session.setAttribute("regUserBean", userBean);
					System.out.println("更换session中的用户：" + userBean);
				}
			}
			responseData.setFlag(true);
			responseData.setMessage("个人信息修改成功");
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
	 * Title: addSysadmin
	 * Description: 添加系统管理员用户
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月30日  
	*/
	private void addSysAdmin(HttpServletRequest request, HttpServletResponse response) {
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
				dataMap.put("level", 5);
			}

			// 2.将数据封装到bean中
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(userBean, dataMap);
			// System.out.println(userBean);
			// 3.调用service添加系统用户
			UserBeanService userBeanService = new UserBeanServiceImpl();
			userBeanService.insertSysAdminUser(userBean);
			responseData.setFlag(true);
			responseData.setMessage("用户数据添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("添加失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: editSelf
	 * Description: 修改当前用户的信息
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	private void editSelf(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		UserBean userBean = null;
		int userid = 0;
		UserBeanService userBeanService = new UserBeanServiceImpl();
		HttpSession session = request.getSession();
		if (session.getAttribute("userBean") != null) {
			UserBean session_user = (UserBean) session.getAttribute("userBean");
			userid = session_user.getUid();
			try {
				userBean = userBeanService.getUserById(userid);
				System.out.println(userBean);
			} catch (Exception e1) {
				// TODO Auto-generated catch bislock
				e1.printStackTrace();
			}
			try {
				request.getRequestDispatcher("/admin/user/modifySelf.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			request.setAttribute("userBean", userBean);
		}
	}

	/**
	 * Title: regList
	 * Description: 展示注册用户列表
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	private void regList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		// 获取查询条件
		String p_regusername = request.getParameter("p_regusername");
		String p_regrealname = request.getParameter("p_regrealname");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_regusername", p_regusername);
		searchMap.put("p_regrealname", p_regrealname);
		try {
			UserBeanService userBeanService = new UserBeanServiceImpl();
			// 实例化分页对象
			PageUtil pageUtil = new PageUtil(request);
			pageUtil.setPageSize(12);
			// 获取分页的四个变量
			// 1.总记录数
			pageUtil.setRsCount(userBeanService.getUserCount(UserType.regUser, searchMap));
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
			List<UserBean> userList = userBeanService.getUserList(startIndex, pageSize, UserType.regUser, searchMap);
			request.setAttribute("p_regusername", p_regusername);
			request.setAttribute("p_regrealname", p_regrealname);
			request.setAttribute("pageTool", pageTool);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/admin/user/reguser_list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Title: edit
	 * Description: 跳转到用户编辑页面函数 
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	private void editPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			// 根据uid号，获取表中的用户信息
			Integer uid = Integer.parseInt(request.getParameter("uid"));
			UserBeanService userBeanService = new UserBeanServiceImpl();
			// System.out.println(itemBean);
			UserBean modifySysAdmin = userBeanService.getUserById(uid);
			// System.out.println("modifySysAdmin==" + modifySysAdmin);
			request.setAttribute("modifySysAdmin", modifySysAdmin);
			if (modifySysAdmin.getLevel() == 5) {
				request.getRequestDispatcher("/admin/user/editSysAdminUser.jsp").forward(request, response);
			} else if (modifySysAdmin.getLevel() == 9) {
				request.getRequestDispatcher("/admin/user/editRegUser.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: deleteCategory Description: 删除客户端中选择的类别
	 * 
	 * @param request
	 * @param response
	 * @author wjh
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		// 1.获取客户端传过来的参数
		Integer uid = Integer.parseInt(request.getParameter("uid"));

		UserBeanService userBeanService = new UserBeanServiceImpl();
		try {
			out = response.getWriter();
			userBeanService.deleteUser(uid);
			responseData.setFlag(true);
		} catch (Exception e) {
			// 处理删除时发生的异常
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("删除失败，错误信息：" + e.getMessage());
		}

		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: saveData
	 * Description: 保存商品数据到数据库中
	 * @param request
	 * @param response
	 * @author wjh
	 */
	private void saveData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		Map<String, Object> dataMap = new LinkedHashMap<>();
		CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
		String filename = "";
		String filepath = "";
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
					if (filed_name.equals("dcdate")) {
						Date dcdate = DateUtil.strToDate(filed_value);
						dataMap.put(filed_name, dcdate);
						continue;
					}
					dataMap.put(filed_name, filed_value);
				} else {
					// 说明是上传组件
					filename = fileItem.getName();
					if (GlobalUtil.isNotNull(filename)) {
						filepath = DateUtil.getSystemTimeStamp() + "_" + filename;

						// System.out.println("上传的文件名称 = " + filename);
						InputStream inputStream = fileItem.getInputStream();
						// 定位到uploadfiles文件夹的路径。
						// String uploadDir =
						// this.getServletContext().getRealPath("/upload");
						// 调试项目时使用绝对路径
						String uploadDir = "D:/java/dfrz_Workspace/WjhOnlineShop/WebContent/upload";
						// System.out.println("uploadDir = " + uploadDir);
						OutputStream outputStream = new FileOutputStream(uploadDir + "/" + filepath);

						// 流的数据的复制，使用common-io包中的方法。
						IOUtils.copy(inputStream, outputStream);
						// 关闭流
						IOUtils.closeQuietly(inputStream);
						IOUtils.closeQuietly(outputStream);
					}
					dataMap.put("filename", filename);
					dataMap.put("filepath", filepath);
				}
			}

			if (dataMap != null) {
				String classid = (String) dataMap.get("itemCategory");
				int cid = Integer.parseInt(classid);
				CategoryBean categoryBean = categoryBeanService.getCategoryByCid(cid);
				if (categoryBean != null) {
					int maxid = categoryBean.getParentid();
					int minid = categoryBean.getCid();
					dataMap.put("maxid", maxid);
					dataMap.put("minid", minid);
				}
			}
			// System.out.println(dataMap);

			// 2.将数据封装到bean中
			ItemBean itemBean = new ItemBean();
			BeanUtils.copyProperties(itemBean, dataMap);
			System.out.println(itemBean);
			// 3.调用service保存类别
			ItemBeanService itemBeanService = new ItemBeanServiceImpl();
			itemBeanService.insertItem(itemBean);
			responseData.setFlag(true);
			responseData.setMessage("商品数据添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setFlag(false);
			responseData.setMessage("添加失败，错误信息：" + e.getMessage());
		}
		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * 
	 * Title: addItem
	 * Description: 跳转添加商品数据
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日
	 */
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		UserBeanService userBeanService = new UserBeanServiceImpl();
		try {
			// 1.获取大类别和小类别的集合

			// 2.获取当前系统时间
			String addtime = DateUtil.getSystemDate();

			request.getRequestDispatcher("/admin/item/addproduct.jsp").forward(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Title: sysList
	 * Description: 展示系统管理员界面列表
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日
	 */
	private void sysList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// 获取查询条件
		String p_adminusername = request.getParameter("p_adminusername");
		String p_adminrealname = request.getParameter("p_adminrealname");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_adminusername", p_adminusername);
		searchMap.put("p_adminrealname", p_adminrealname);
		try {
			UserBeanService userBeanService = new UserBeanServiceImpl();
			// 实例化分页对象
			PageUtil pageUtil = new PageUtil(request);
			pageUtil.setPageSize(12);
			// 获取分页的四个变量
			// 1.总记录数
			pageUtil.setRsCount(userBeanService.getUserCount(UserType.sysAdmin, searchMap));
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
			List<UserBean> userList = userBeanService.getUserList(startIndex, pageSize, UserType.sysAdmin, searchMap);
			request.setAttribute("p_adminusername", p_adminusername);
			request.setAttribute("p_adminrealname", p_adminrealname);
			request.setAttribute("pageTool", pageTool);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/admin/user/sysadmin_list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
