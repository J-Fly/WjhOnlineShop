package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import service.CategoryBeanService;
import service.ItemBeanService;
import service.impl.CategoryBeanServiceImpl;
import service.impl.ItemBeanServiceImpl;
import utils.DateUtil;
import utils.GlobalUtil;
import utils.JsonUtil;
import utils.PageUtil;
import utils.ResponseData;

@WebServlet(name = "ItemServlet", urlPatterns = "/servlet/ItemServlet")
public class ItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task.equals("list")) {
			this.list(request, response);
		} else if (task.equals("addItem")) {
			this.addItem(request, response);
		} else if (task.equals("saveData")) {
			this.saveData(request, response);
		} else if (task.equals("edit")) {
			this.editPage(request, response);
		} else if (task.equals("modify")) {
			this.modifyItem(request, response);
		} else if (task.equals("deleteItem")) {
			this.deleteItem(request, response);
		} else if (task.equals("deleteSelectedItem")) {
			this.deleteSelectedItem(request, response);
		} else if (task.equals("showDetail")) {
			this.showDetail(request, response);
		}
	}

	/**
	 * Title: showDetail
	 * Description: 跳转到商品详情页
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月24日  
	*/
	private void showDetail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		ItemBean itemBean = null;
		HttpSession session=request.getSession();
		String itemid = request.getParameter("itemid");
		ItemBeanService itemBeanService = new ItemBeanServiceImpl();
		itemBean = itemBeanService.getItemById(itemid);
		System.out.println("itemBean=="+itemBean);
		try {
			out = response.getWriter();
			if (itemBean != null) {
				responseData.setFlag(true);
				session.setAttribute("itemBeanDetail", itemBean);
//				request.getRequestDispatcher("/guest/showdetails.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			responseData.setFlag(false);
			responseData.setMessage("商品详情获取失败");
		}

		String jsonStr = JsonUtil.parseString(responseData);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	/**
	 * Title: deleteSelectedItem
	 * Description: 删除选中的商品
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年8月14日  
	*/
	private void deleteSelectedItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		// 1.获取客户端传过来的参数
		String delete_itemid = request.getParameter("delete_itemid");
		String delete_itemids[] = delete_itemid.split(",");
		List<String> deleteIdList = new ArrayList<String>();
		for (String string : delete_itemids) {
			deleteIdList.add(string);
		}

		ItemBeanService itemBeanService = new ItemBeanServiceImpl();
		try {
			out = response.getWriter();
			for (String id : deleteIdList) {
				itemBeanService.deleteItem(id);
			}
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
	 * Title: deleteItem
	 * Description: 删除商品
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月29日  
	*/
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		// 1.获取客户端传过来的参数
		String id = request.getParameter("id");
		System.out.println("id=" + id);
		ItemBeanService itemBeanService = new ItemBeanServiceImpl();
		try {
			out = response.getWriter();
			itemBeanService.deleteItem(id);
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
	 * Title: modifyItem
	 * Description: 修改商品信息
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	private void modifyItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("233333333333333333..........");
		response.setContentType("text/html");
		PrintWriter out = null;
		ResponseData responseData = new ResponseData();
		Map<String, Object> dataMap = new LinkedHashMap<>();
		CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
		ItemBeanService itemBeanService = new ItemBeanServiceImpl();
		String filename = "";
		String filepath = "";
		String itemid = request.getParameter("id");// 获取超链接传过来的id
		ItemBean old_itemBean = itemBeanService.getItemById(itemid);
		String old_filename = GlobalUtil.nullToStr(old_itemBean.getFilename());
		String old_filepath = GlobalUtil.nullToStr(old_itemBean.getFilepath());

		try {
			out = response.getWriter();
			// 1.获取表单中提交的数据
			FileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);
			List<FileItem> itemList = fileUpload.parseRequest(request);
			/*
			 * 修改需要判断： 1.判断原来原中是否有图片的信息。 如果有图片的信息，用户上传了图片，那么使用新的图片覆盖旧的图片；
			 * 用户没有上传图片，那么使用旧的图片信息。 2、如果原来没有图片的信息 : 用户上传了图片，那么使用上传的图片信息
			 */
			boolean isUploadFile = false;
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
					isUploadFile = true;
					// 说明是上传组件
					filename = fileItem.getName();
					if (GlobalUtil.isNotNull(filename)) {
						// 说明有新文件上传
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

			if (isUploadFile == false) {
				// 用户没有重新上传文件
				dataMap.put("filename", old_filename);
				dataMap.put("filepath", old_filepath);
				// System.out.println("old_filename=" + old_filename);
				// System.out.println("old_filepath=" + old_filepath);
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
			// System.out.println(itemBean);
			// 3.调用service保存类别

			itemBeanService.modifyItem(itemBean);
			responseData.setFlag(true);
			responseData.setMessage("商品数据修改成功，点击确定刷新页面");
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
	 * Title: edit
	 * Description: 跳转到商品编辑页面函数 
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	private void editPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			// 1.获取大类别和小类别的集合
			CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
			List<CategoryBean> categoryList = categoryBeanService.getCategoryList();

			// 2.根据id号，获取表中的商品信息
			String itemid = request.getParameter("id");
			ItemBeanService itemBeanService = new ItemBeanServiceImpl();
			ItemBean itemBean = itemBeanService.getItemById(itemid);
			// System.out.println(itemBean);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("itemBean", itemBean);
			request.getRequestDispatcher("/admin/item/editproduct.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			// 1.获取大类别和小类别的集合
			CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
			List<CategoryBean> categoryList = categoryBeanService.getCategoryList();
			// 2.获取当前系统时间
			String addtime = DateUtil.getSystemDate();

			request.setAttribute("addtime", addtime);
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/admin/item/addproduct.jsp").forward(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Title: list
	 * Description: 展示商品列表
	 * @param request
	 * @param response
	 * @author wjh
	 * @date 2020年7月22日
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// 类别列表
		CategoryBeanService categoryBeanService = new CategoryBeanServiceImpl();
		List<CategoryBean> categoryBeanList = categoryBeanService.getCategoryList();
		request.setAttribute("categoryList", categoryBeanList);
		// 获取查询条件
		String p_categoryid = request.getParameter("p_categoryid");
		String p_itemname = request.getParameter("p_itemname");
		// 封装查询条件（将p_categoryid转换为maxid和minid）
		// System.out.println("p_categoryid="+p_categoryid);
		// System.out.println("p_itemname="+p_itemname);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (GlobalUtil.isNotNull(p_categoryid)) {
			CategoryBean categoryBean = categoryBeanService.getCategoryByCid(Integer.parseInt(p_categoryid));
			int maxid = categoryBean.getParentid();
			int minid = 0;
			if (maxid == 0) {
				// 说明选择的是大类别
				maxid = categoryBean.getCid();
			} else {
				minid = categoryBean.getCid();
			}

			System.out.println("maxid=" + maxid + "\n" + "minid=" + minid);
			searchMap.put("maxid", maxid);
			searchMap.put("minid", minid);
		}
		searchMap.put("p_itemname", p_itemname);
		// System.out.println(searchMap);
		try {
			ItemBeanService itemBeanService = new ItemBeanServiceImpl();
			// 实例化分页对象
			PageUtil pageUtil = new PageUtil(request);
			pageUtil.setPageSize(12);
			// 获取分页的四个变量
			// 1.总记录数
			pageUtil.setRsCount(itemBeanService.getItemCount(searchMap));
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
			List<ItemBean> itemList = itemBeanService.getItemList(startIndex, pageSize, searchMap);
			// 将查询条件存放到作用域中
			request.setAttribute("p_categoryid", p_categoryid);
			request.setAttribute("p_itemname", p_itemname);
			request.setAttribute("pageTool", pageTool);
			request.setAttribute("itemList", itemList);
			request.getRequestDispatcher("/admin/item/product.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
