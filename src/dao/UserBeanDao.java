/**
 * Userbean的持久层接口
 */
package dao;

import java.util.List;
import java.util.Map;

import bean.UserBean;
import utils.GlobalUtil.UserType;

public interface UserBeanDao {
	void save(UserBean userBean);
	
	UserBean findByUserName(String userName);
	
	UserBean findByUserNameAndPassword(String userName,String password,int level);

	List<UserBean> findAll();

	/**
	 * Title: getUserCount
	 * Description: 获取用户数量dao层接口方法
	 * @param userType
	 * @return int
	 * @author wjh
	 * @param searchMap 
	 * @date 2020年7月28日  
	*/
	int getUserCount(UserType userType, Map<String, Object> searchMap);

	/**
	 * Title: getUserList
	 * Description: 获取用户信息列表dao层接口方法 
	 * @param startIndex
	 * @param pageSize
	 * @param userType
	 * @return List<UserBean>
	 * @author wjh
	 * @param searchMap 
	 * @date 2020年7月28日  
	*/
	List<UserBean> getUserList(int startIndex, int pageSize, UserType userType, Map<String, Object> searchMap);

	/**
	 * Title: getUserById
	 * Description: 根据uid查找用户信息dao层接口方法
	 * @param userid
	 * @return UserBean
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	UserBean getUserById(int userid) throws Exception;

	/**
	 * Title: insertSysAdminUser
	 * Description: 添加系统用户业务层接口方法
	 * @param userBean
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	void insertSysAdminUser(UserBean userBean);

	/**
	 * Title: updateSysAdminUser
	 * Description: 修改系统管理员用户信息dao层接口方法
	 * @param userBean
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	void updateSysAdminUser(UserBean userBean);

	/**
	 * Title: deleteUser
	 * Description: 根据uid删除用户信息dao层接口方法
	 * @param uid
	 * @author wjh
	 * @date 2020年8月1日  
	*/
	void deleteUser(Integer uid);

	/**
	 * Title: insertRegUser
	 * Description: 添加注册用户dao层接口方法 
	 * @param userBean
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	void insertRegUser(UserBean userBean);

	/**
	 * Title: changePwd
	 * Description: 修改登录用户密码dao层接口方法 
	 * @param uid
	 * @param newpwd
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	void changePwd(String uid, String newpwd);

	/**
	 * Title: updateRegUser
	 * Description: 注册用户修改个人信息dao层接口方法
	 * @param userBean
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	void updateRegUser(UserBean userBean);
}
