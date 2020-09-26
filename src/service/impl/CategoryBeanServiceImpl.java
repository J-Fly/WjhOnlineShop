package service.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import bean.CategoryBean;
import dao.CategoryBeanDao;
import dao.impl.CategoryBeanDaoImpl;
import service.CategoryBeanService;
import utils.C3P0Util;

public class CategoryBeanServiceImpl implements CategoryBeanService {
	
	
	CategoryBeanDao categoryBeanDao = new CategoryBeanDaoImpl();

	@Override
	public List<CategoryBean> getCategoryList() {
		// TODO Auto-generated method stub
		return categoryBeanDao.getCategoryList();
	}

	@Override
	public int getMaxCid() {
		// TODO Auto-generated method stub
		return categoryBeanDao.getMaxCid();
	}

	@Override
	public List<CategoryBean> getParentList() {
		// TODO Auto-generated method stub
		return categoryBeanDao.getParentList();
	}

	@Override
	public void insertCategory(CategoryBean categoryBean) {
		// TODO Auto-generated method stub
		categoryBeanDao.insertCategory(categoryBean);
	}

	@Override
	public void deleteCategory(String cid, String parentid) throws Exception {
		// TODO Auto-generated method stub
		if (cid==null||cid.equals("")) {
			throw new Exception("删除的类别编号为空，无法执行删除操作");
		}else {
			categoryBeanDao.deleteCategory(cid,parentid);
		}
	}

	/**
	 * Title: getCategoryByCid
	 * Description: 根据类别编号查找类别
	 * @param cid
	 * @return CategoryBean
	 * @see service.CategoryBeanService#getCategoryByCid(int)
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	@Override
	public CategoryBean getCategoryByCid(int cid) {
		// TODO Auto-generated method stub
		return categoryBeanDao.getCategoryByCid(cid);
	}

	/**
	 * Title: updateCategory
	 * Description: 修改类别信息业务层接口实现方法
	 * @param categoryBean
	 * @see service.CategoryBeanService#updateCategory(bean.CategoryBean)
	 * @author wjh
	 * @date 2020年7月30日  
	*/
	@Override
	public void updateCategory(CategoryBean categoryBean) {
		// TODO Auto-generated method stub
		categoryBeanDao.updateCategory(categoryBean);
	}

}
