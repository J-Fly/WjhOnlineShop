package service;

import java.util.List;
import java.util.Map;

import bean.UserBean;
import utils.GlobalUtil.UserType;

public interface UserBeanService {
	UserBean login(UserBean user,int level);

	/**
	 * Title: getUserCount
	 * Description: 获取用户数量业务层接口方法
	 * @param sysadmin
	 * @return int
	 * @author wjh
	 * @param searchMap 
	 * @date 2020年7月28日  
	*/
	int getUserCount(UserType userType, Map<String, Object> searchMap);

	/**
	 * Title: getUserList
	 * Description: 获取用户信息列表业务层接口方法
	 * @param startIndex
	 * @param pageSize
	 * @param sysadmin
	 * @return List<UserBean>
	 * @author wjh
	 * @param searchMap 
	 * @date 2020年7月28日  
	*/
	List<UserBean> getUserList(int startIndex, int pageSize, UserType userType, Map<String, Object> searchMap);

	/**
	 * Title: getUserById
	 * Description: 根据uid查找用户信息业务层接口方法
	 * @param userid
	 * @return UserBean
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	UserBean getUserById(int userid) throws Exception;

	/**
	 * Title: insertSysAdminUser
	 * Description: 添加系统管理员用户业务层接口方法 
	 * @param userBean
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	void insertSysAdminUser(UserBean userBean);

	/**
	 * Title: updateSysAdminUser
	 * Description: 修改系统管理员用户信息业务层接口方法 
	 * @param userBean
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	void updateSysAdminUser(UserBean userBean);

	/**
	 * Title: deleteUser
	 * Description: 根据id删除用户信息业务层接口方法
	 * @param uid
	 * @author wjh
	 * @date 2020年8月1日  
	*/
	void deleteUser(Integer uid);

	/**
	 * Title: insertRegUser
	 * Description: 添加注册用户业务层接口方法 
	 * @param userBean
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	void insertRegUser(UserBean userBean);

	/**
	 * Title: changePwd
	 * Description: 修改登录用户密码业务层接口方法 
	 * @param uid
	 * @param newpwd
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	void changePwd(String uid, String newpwd);

	/**
	 * Title: updateRegUser
	 * Description: 注册用户修改个人信息
	 * @param userBean
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	void updateRegUser(UserBean userBean);

	/**
	 * Title: findByUserName
	 * Description: 根据用户名查找用户信息业务层接口方法
	 * @param username
	 * @return
	 * @author wjh
	 * @date 2020年8月11日  
	*/
	UserBean findByUserName(String username);
}
