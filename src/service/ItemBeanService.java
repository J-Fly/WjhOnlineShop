package service;

import java.util.List;
import java.util.Map;

import bean.ItemBean;

/**Title: ItemBeanService 
* Description: 商品业务层接口 
* @author wjh
* @date 2020年7月20日  
*/
public interface ItemBeanService {

	/**Title: getItemList  
	 * Description: 获取商品列表业务层接口方法  
	 * @return List<ItemBean> 
	 * @author wjh
	 * @param searchMap 
	 */  
	List<ItemBean> getItemList(int startIndex,int pageSize, Map<String, Object> searchMap);

	/**Title: getItemCount  
	 * Description: 获取商品总记录数业务层接口方法    
	 * @return int 
	 * @author wjh
	 * @param searchMap 
	 */  
	int getItemCount(Map<String, Object> searchMap);

	/**Title: insertItem  
	 * Description: 添加商品数据业务层接口方法      
	 * @param itemBean  
	 * @author wjh
	 * @throws Exception 
	 */  
	void insertItem(ItemBean itemBean) throws Exception;

	/**
	 * Title: getItemById
	 * Description: 根据id获取商品数据业务层接口方法      
	 * @param itemid
	 * @return ItemBean
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	ItemBean getItemById(String itemid);

	/**
	 * Title: modifyItem
	 * Description: 修改商品数据业务层接口方法
	 * @param itemBean
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月23日  
	*/
	void modifyItem(ItemBean itemBean) throws Exception;

	/**
	 * Title: deleteItem
	 * Description: 删除商品业务层接口方法
	 * @param id
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	void deleteItem(String id) throws Exception;

}
