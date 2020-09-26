package dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.CategoryBean;
import bean.ItemBean;
import dao.ItemBeanDao;
import utils.C3P0Util;
import utils.GlobalUtil;

/**Title: ItemBeanDaoImpl 
* Description: 持久层接口实现类 
* @author wjh
* @date 2020年7月20日  
*/
public class ItemBeanDaoImpl implements ItemBeanDao {

	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	public String getSearchSql(Map<String, Object> searchMap) {
		StringBuilder whereSQL = new StringBuilder();
		whereSQL.setLength(0);

		String maxid = String.valueOf(searchMap.get("maxid"));
		String minid = String.valueOf(searchMap.get("minid"));
		String p_itemname = String.valueOf(searchMap.get("p_itemname"));
		String searchKeyWord=String.valueOf(searchMap.get("searchKeyWord"));
		
		if (GlobalUtil.isNotNull(maxid) && maxid.equals("0") == false) {
			whereSQL.append(" and maxid = '" + maxid + "'");
		}
		if (GlobalUtil.isNotNull(minid) && minid.equals("0") == false) {
			whereSQL.append(" and minid = '" + minid + "'");
		}
		if (GlobalUtil.isNotNull(p_itemname)) {
			whereSQL.append(" and name like '%" + p_itemname + "%'");
		}
		if (GlobalUtil.isNotNull(searchKeyWord)) {
			whereSQL.append(" and (name like '%" + searchKeyWord + "%' or description like '%"+searchKeyWord+"%') ");
		}
		
		return whereSQL.toString();
	}

	/**Title: getItemList  
	 * Description: 获取商品列表  
	 * @return List<ItemBean> 
	 * @author wjh
	 */
	@Override
	public List<ItemBean> getItemList(int startIndex, int pageSize, Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		List<ItemBean> itemBeans = null;
		try {
			itemBeans = runner.query(
					"select * from t_mc where isdel=0 "+ this.getSearchSql(searchMap)+" order by id desc limit " + startIndex + "," + pageSize + "",
					new BeanListHandler<ItemBean>(ItemBean.class));
			// System.out.println(itemBeans);
//			System.out.println("getItemList="+"select * from t_mc where isdel=0 "+ this.getSearchSql(searchMap)+" order by id desc limit " + startIndex + "," + pageSize + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return itemBeans;
	}

	/**
	 *   
	 * Title: getItemCount
	 * Description: 获取商品总记录数 
	 * @return int
	 * @see dao.ItemBeanDao#getItemCount()
	 * @author wjh
	 * @date 2020年7月22日
	 */
	@Override
	public int getItemCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		try {
			Long itemCountLong = runner.query("select count(id) from t_mc where isdel =0 "+this.getSearchSql(searchMap), new ScalarHandler<Long>());
			// sql语句count(列名)返回的并不是int类型，而是long型值，调整代码进行类型转换
//			System.out.println("getItemCount="+"select count(id) from t_mc where isdel =0 "+this.getSearchSql(searchMap));
			String tString = itemCountLong.toString();
			int itemCount = Integer.parseInt(tString);
			if (itemCount > 0) {
				return itemCount;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Title: insertItem
	 * Description: 添加商品数据到数据库中
	 * @param itemBean
	 * @return
	 * @see dao.ItemBeanDao#saveItem(bean.ItemBean)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月22日  
	*/
	@Override
	public void insertItem(ItemBean itemBean) throws Exception {
		String name = itemBean.getName();
		String description = itemBean.getDescription();
		BigDecimal price = itemBean.getPrice();
		String filepath = itemBean.getFilepath();
		String filename = itemBean.getFilename();
		int isdel = 0;
		java.sql.Date dcDate = new java.sql.Date(itemBean.getDcdate().getTime());
		int maxid = itemBean.getMaxid();
		int minid = itemBean.getMinid();

		String sql = "insert into t_mc (name,description,price,filename,filepath,isdel,dcdate,maxid,minid) "
				+ "values('" + name + "','" + description + "'," + price + ",'" + filename + "','" + filepath + "',"
				+ isdel + ",'" + dcDate + "'," + maxid + "," + minid + ")";
		System.out.println(sql);
		try {
			runner.update(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("数据库持久层异常");
		}
	}

	/**
	 * Title: getItemById
	 * Description: 根据id查找商品数据
	 * @param itemid
	 * @return ItemBean
	 * @see dao.ItemBeanDao#getItemById(java.lang.String)
	 * @author wjh
	 * @date 2020年7月22日  
	*/
	@Override
	public ItemBean getItemById(String itemid) {
		// TODO Auto-generated method stub
		ItemBean itemBean = null;
		try {
			itemBean = runner.query("select * from t_mc where id = " + itemid + "",
					new BeanHandler<ItemBean>(ItemBean.class));
			//设置取出对象时，默认singleCount=1
			itemBean.setSingleCount(1);
			// System.out.println(itemBeans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return itemBean;
	}

	/**
	 * Title: modifyItem
	 * Description: 修改商品数据
	 * @param itemBean
	 * @see dao.ItemBeanDao#modifyItem(bean.ItemBean)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月23日  
	*/
	@Override
	public void modifyItem(ItemBean itemBean) throws Exception {
		// TODO Auto-generated method stub
		String name = itemBean.getName();
		String description = itemBean.getDescription();
		BigDecimal price = itemBean.getPrice();
		String filepath = itemBean.getFilepath();
		String filename = itemBean.getFilename();
		int isdel = 0;
		java.sql.Date dcDate = new java.sql.Date(itemBean.getDcdate().getTime());
		int maxid = itemBean.getMaxid();
		int minid = itemBean.getMinid();

		String sql = "update t_mc set name='" + name + "',description='" + description + "',price=" + price
				+ ",filename='" + filename + "',filepath='" + filepath + "',isdel=" + isdel + ",dcdate='" + dcDate
				+ "',maxid=" + maxid + ",minid=" + minid + " where id=" + itemBean.getId() + "";
		System.out.println(sql);
		try {
			runner.update(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("数据库持久层异常");
		}
	}

	/**
	 * Title: deleteItem
	 * Description: 删除商品dao层接口实现方法
	 * @param id
	 * @see dao.ItemBeanDao#deleteItem(java.lang.String)
	 * @author wjh
	 * @date 2020年7月29日  
	*/
	@Override
	public void deleteItem(String id) {
		// TODO Auto-generated method stub
		String sql = "update t_mc set isdel = 1 where id="+id+"";
		// System.out.println(sql);
		try {
			runner.update(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
