package dao;

import java.util.List;
import java.util.Map;

import bean.CategoryBean;

public interface CategoryBeanDao {
	
	List<CategoryBean> getCategoryList();

	int getMaxCid();

	List<CategoryBean> getParentList();

	void insertCategory(CategoryBean categoryBean);

	void deleteCategory(String cid, String parentid) throws Exception;

	/**Title: getCategoryMap  
	 * Description:   
	 * @return  
	 * @author wjh
	 */  
	Map<Integer, String> getCategoryMap();

	/**
	 * Title: getCategoryByCid
	 * Description: 根据类别编号返回类别对象dao层接口
	 * @param cid
	 * @return CategoryBean
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	CategoryBean getCategoryByCid(int cid);

	/**
	 * Title: updateCategory
	 * Description: 修改类别信息dao层接口方法
	 * @param categoryBean
	 * @author wjh
	 * @date 2020年7月30日  
	*/
	void updateCategory(CategoryBean categoryBean);
	
}
