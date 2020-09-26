package service.impl;

import java.util.List;
import java.util.Map;

import bean.ItemBean;
import dao.CategoryBeanDao;
import dao.ItemBeanDao;
import dao.impl.CategoryBeanDaoImpl;
import dao.impl.ItemBeanDaoImpl;
import service.ItemBeanService;

/**
 * Title: ItemBeanServiceImpl Description: 商品的业务层实现类
 * 
 * @author wjh
 * @date 2020年7月20日
 */
public class ItemBeanServiceImpl implements ItemBeanService {

	ItemBeanDao itemBeanDao = new ItemBeanDaoImpl();
	CategoryBeanDao categoryBeanDao = new CategoryBeanDaoImpl();

	/**
	 * Title: getItemList 
	 * Description: 获取商品列表
	 * @return List<ItemBean>
	 * @author wjh
	 */
	@Override
	public List<ItemBean> getItemList(int startIndex, int pageSize, Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		List<ItemBean> itemList = itemBeanDao.getItemList(startIndex, pageSize, searchMap);
		int maxid, minid;
		String maxname, minname;
		Map<Integer, String> categoryMap = categoryBeanDao.getCategoryMap();
		if (itemList != null && itemList.size() > 0) {
			for (ItemBean itemBean : itemList) {
				maxid = itemBean.getMaxid();
				minid = itemBean.getMinid();
				maxname = categoryMap.get(maxid);
				minname = categoryMap.get(minid);
				itemBean.setMaxname(maxname);
				itemBean.setMinname(minname);
			}
		} 
		return itemList;
	}

	/**
	 * Title: getItemCount 
	 * Description: 获取商品总记录数
	 * @return int
	 * @author wjh
	 */
	@Override
	public int getItemCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return itemBeanDao.getItemCount(searchMap);
	}

	/**
	 * 
	 * Title: insertItem
	 * Description: 添加商品
	 * @param itemBean
	 * @throws Exception
	 * @see service.ItemBeanService#saveItem(bean.ItemBean)
	 */
	@Override
	public void insertItem(ItemBean itemBean) throws Exception {
		// TODO Auto-generated method stub
		if (itemBean.getName() == null || itemBean.getName().equals("")) {
			throw new Exception("商品名称为空，无法执行添加操作");
		} else {
			itemBeanDao.insertItem(itemBean);
		}
	}

	/**
	 * Title: getItemById
	 * Description: 根据id获取商品数据业务层接口方法   
	 * @param itemid
	 * @return ItemBean
	 * @see service.ItemBeanService#getItemById(java.lang.String)
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	@Override
	public ItemBean getItemById(String itemid) {
		// TODO Auto-generated method stub
		ItemBean itemBean = itemBeanDao.getItemById(itemid);
		if (itemBean != null) {
			Map<Integer, String> categoryMap = categoryBeanDao.getCategoryMap();
			int maxid, minid;
			String maxname, minname;
			maxid = itemBean.getMaxid();
			minid = itemBean.getMinid();
			maxname = categoryMap.get(maxid);
			minname = categoryMap.get(minid);
			itemBean.setMaxname(maxname);
			itemBean.setMinname(minname);
		}
		return itemBean;
	}

	/**
	 * Title: modifyItem
	 * Description: 修改商品数据
	 * @param itemBean
	 * @see service.ItemBeanService#modifyItem(bean.ItemBean)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月23日  
	*/
	@Override
	public void modifyItem(ItemBean itemBean) throws Exception {
		// TODO Auto-generated method stub
		if (itemBean.getName() == null || itemBean.getName().equals("")) {
			throw new Exception("商品名称为空，无法执行修改操作");
		} else {
			itemBeanDao.modifyItem(itemBean);
		}

	}

	/**
	 * Title: deleteItem
	 * Description: 修改商品数据业务层接口实现方法
	 * @param id
	 * @see service.ItemBeanService#deleteItem(java.lang.String)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	@Override
	public void deleteItem(String id) throws Exception {
		// TODO Auto-generated method stub
		if (id==null||id.equals("")) {
			throw new Exception("删除的商品编号为空，无法执行删除操作");
		}else {
			itemBeanDao.deleteItem(id);
		}
		
	}

}
