package dao;

import java.util.List;
import java.util.Map;

import bean.CategoryBean;
import bean.ItemBean;

public interface ItemBeanDao {
	
	/**Title: getItemList  
	 * Description: 获取商品列表dao层接口方法 
	 * @return List<ItemBean> 
	 * @author wjh
	 * @param searchMap 
	 */  
	List<ItemBean> getItemList(int startIndex,int pageSize, Map<String, Object> searchMap);

	/**Title: getItemCount  
	 * Description: 获取商品总记录数dao层接口方法   
	 * @return int 
	 * @author wjh
	 * @param searchMap 
	 */  
	int getItemCount(Map<String, Object> searchMap);

	/**
	 *  
	 * Title: insertItem
	 * Description: 添加商品数据dao层接口方法
	 * @param itemBean
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月22日
	 */
	void insertItem(ItemBean itemBean) throws Exception;

	/**
	 * Title: getItemById
	 * Description: 根据id查找商品数据dao层接口方法
	 * @param itemid
	 * @return ItemBean
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	ItemBean getItemById(String itemid);

	/**
	 * Title: modifyItem
	 * Description: 修改商品数据dao层接口
	 * @param itemBean
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月23日  
	*/
	void modifyItem(ItemBean itemBean) throws Exception;

	/**
	 * Title: deleteItem
	 * Description: 删除商品dao层接口方法
	 * @param id
	 * @author wjh
	 * @date 2020年7月29日  
	*/
	void deleteItem(String id);
	
}
