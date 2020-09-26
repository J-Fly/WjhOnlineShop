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

import bean.CategoryBean;
import bean.UserBean;
import service.CategoryBeanService;
import service.UserBeanService;
import service.impl.CategoryBeanServiceImpl;
import service.impl.UserBeanServiceImpl;
import utils.JsonUtil;
import utils.ResponseData;

@WebServlet(name = "CategoryServlet", urlPatterns = "/servlet/CategoryServlet")
public class CategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("list")) {
			this.list(request, response);
		} else if (task.equals("addCategory")) {
			this.addCategory(request, response);
		} else if (task.equals("saveData")) {
			this.saveData(request, response);
		} else if (task.equals("deleteCategory")) {
			this.deleteCategory(request, response);
		} else if (task.equals("edit")) {
			this.edit(request, response);
		} else if (task.equals("updateCategory")) {
			this.updateCategory(request, response);
		}
	}

	/**
	 * Title: updateCategory
	 * Description: 保存修改后的类别数据
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月30日  
	*/
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			String parentid = request.getParameter("parentid");
			String cid = request.getParameter("cid");
			String classname = request.getParameter("classname");
			if (classname == null || classname.equals("")) {
				responseData.setFlag(false);
				responseData.setMessage("类别名不能为空");
			} else {
				// 2.将数据封装到bean中
				CategoryBean categoryBean = new CategoryBean(Integer.parseInt(cid), classname,
						Integer.parseInt(parentid));
				// 3.调用service保存类别
				CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
				categoryBeanService.updateCategory(categoryBean);
				responseData.setFlag(true);
				responseData.setMessage("数据添加成功，是否继续添加？");
			}

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
	 * Title: edit
	 * Description: 跳转到编辑分类页面
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月29日  
	*/
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ResponseData responseData = new ResponseData();
		PrintWriter out = null;
		CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
		int cid = Integer.parseInt(request.getParameter("cid"));
		try {
			out = response.getWriter();
			// 1.根基cid获取类别对象
			CategoryBean categoryBean = categoryBeanService.getCategoryByCid(cid);
			System.out.println("要修改的类别：" + categoryBean);
			List<CategoryBean> parentList = categoryBeanService.getParentList();
			if (categoryBean.getParentid() != 0) {
				// 说明修改的是小类别
				int pid = categoryBean.getParentid();
				int cidupdate = categoryBean.getCid();
				request.setAttribute("cid", cidupdate);
				request.setAttribute("editcategoryBean", categoryBean);
				request.setAttribute("parentList", parentList);
				request.setAttribute("pid", pid);
				request.getRequestDispatcher("/admin/category/update_product_category.jsp").forward(request, response);
			}

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: deleteCategory  
	 * Description: 删除客户端中选择的类别  
	 * @param request
	 * @param response  
	 * @author wjh
	 */
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		// 1.获取客户端传过来的参数
		String parentid = request.getParameter("parentid");
		String cid = request.getParameter("cid");
		try {
			out = response.getWriter();
			categoryBeanService.deleteCategory(cid, parentid);
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
	 * Title: addCategory  
	 * Description:添加类别   
	 * @param request
	 * @param response  
	 * @author wjh
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
			// 1.获取类别编码的最大id号
			int maxcid = categoryBeanService.getMaxCid();
			// 2.获取大类别的集合
			List<CategoryBean> parentList = categoryBeanService.getParentList();

			// 3.获取传送的父类id参数，添加子类功能中
			String pid = request.getParameter("pid");
			request.setAttribute("pid", pid);

			request.setAttribute("maxcid", maxcid);
			request.setAttribute("parentList", parentList);
			request.getRequestDispatcher("/admin/category/add_product_category.jsp").forward(request, response);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
			List<CategoryBean> categoryBeans = categoryBeanService.getCategoryList();
			// System.out.println(categoryBeans);
			request.setAttribute("categoryList", categoryBeans);
			request.getRequestDispatcher("/admin/category/product_category.jsp").forward(request, response);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
