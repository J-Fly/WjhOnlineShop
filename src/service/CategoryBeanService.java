package service;

import java.util.List;

import bean.CategoryBean;

public interface CategoryBeanService {
	List<CategoryBean> getCategoryList();

	int getMaxCid();

	List<CategoryBean> getParentList();

	void insertCategory(CategoryBean categoryBean);

	void deleteCategory(String cid, String parentid) throws Exception;
	
	CategoryBean getCategoryByCid(int cid);

	/**
	 * Title: updateCategory
	 * Description: 修改类别信息业务层接口方法
	 * @param categoryBean
	 * @author wjh
	 * @date 2020年7月30日  
	*/
	void updateCategory(CategoryBean categoryBean);
}
