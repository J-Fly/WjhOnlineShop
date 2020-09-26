package service.impl;

import java.util.List;
import java.util.Map;

import bean.UserBean;
import dao.UserBeanDao;
import dao.impl.UserBeanDaoImpl;
import service.UserBeanService;
import utils.GlobalUtil.UserType;

public class UserBeanServiceImpl implements UserBeanService {

	UserBeanDao userBeanDao = new UserBeanDaoImpl();

	@Override
	public UserBean login(UserBean user,int level) {
		// TODO Auto-generated method stub

		return userBeanDao.findByUserNameAndPassword(user.getUsername(), user.getPassword(), level);
	}

	/**
	 * Title: getUserCount
	 * Description: 获取用户数量业务层接口实现方法
	 * @param sysadmin
	 * @return int
	 * @see service.UserBeanService#getUserCount(utils.GlobalUtil.UserType)
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	@Override
	public int getUserCount(UserType userType,Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return userBeanDao.getUserCount(userType,searchMap);
	}

	/**
	 * Title: getUserList
	 * Description: 获取用户信息列表业务层接口实现方法
	 * @param startIndex
	 * @param pageSize
	 * @param userType
	 * @return List<UserBean>
	 * @see service.UserBeanService#getUserList(int, int, utils.GlobalUtil.UserType)
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	@Override
	public List<UserBean> getUserList(int startIndex, int pageSize, UserType userType,Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return userBeanDao.getUserList(startIndex,pageSize,userType,searchMap);
	}

	/**
	 * Title: getUserById
	 * Description: 根据uid查找用户信息业务层接口实现方法
	 * @param userid
	 * @return UserBean
	 * @see service.UserBeanService#getUserById(int)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	@Override
	public UserBean getUserById(int userid) throws Exception {
		// TODO Auto-generated method stub
		if (userid<=0) {
			throw new Exception("用户id号错误");
		}
		return userBeanDao.getUserById(userid);
	}

	/**
	 * Title: insertSysAdminUser
	 * Description: 添加系统用户业务层接口实现方法
	 * @param userBean
	 * @see service.UserBeanService#insertSysAdminUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	@Override
	public void insertSysAdminUser(UserBean userBean) {
		// TODO Auto-generated method stub
		userBeanDao.insertSysAdminUser(userBean);
	}

	/**
	 * Title: updateSysAdminUser
	 * Description: 修改系统管理员用户信息业务层接口实现方法
	 * @param userBean
	 * @see service.UserBeanService#updateSysAdminUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	@Override
	public void updateSysAdminUser(UserBean userBean) {
		// TODO Auto-generated method stub
		userBeanDao.updateSysAdminUser(userBean);
	}

	/**
	 * Title: deleteUser
	 * Description: 根据id删除用户信息业务层接口实现方法
	 * @param uid
	 * @see service.UserBeanService#deleteUser(java.lang.Integer)
	 * @author wjh
	 * @date 2020年8月1日  
	*/
	@Override
	public void deleteUser(Integer uid) {
		// TODO Auto-generated method stub
		userBeanDao.deleteUser(uid);
	}

	/**
	 * Title: insertRegUser
	 * Description: 添加注册用户业务层接口实现方法 
	 * @param userBean
	 * @see service.UserBeanService#insertRegUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	@Override
	public void insertRegUser(UserBean userBean) {
		// TODO Auto-generated method stub
		userBeanDao.insertRegUser(userBean);
	}

	/**
	 * Title: changePwd
	 * Description: 修改登录用户密码业务层接口实现方法 
	 * @param uid
	 * @param newpwd
	 * @see service.UserBeanService#changePwd(java.lang.String, java.lang.String)
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	@Override
	public void changePwd(String uid, String newpwd) {
		// TODO Auto-generated method stub
		userBeanDao.changePwd(uid,newpwd);
	}

	/**
	 * Title: updateRegUser
	 * Description: 注册用户修改个人信息业务层接口实现方法
	 * @param userBean
	 * @see service.UserBeanService#updateRegUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	@Override
	public void updateRegUser(UserBean userBean) {
		// TODO Auto-generated method stub
		userBeanDao.updateRegUser(userBean);
	}

	/**
	 * Title: findByUserName
	 * Description: 根据用户名查找用户信息业务层接口实现方法 
	 * @param username
	 * @return
	 * @author wjh
	 * @date 2020年8月11日  
	*/
	@Override
	public UserBean findByUserName(String username) {
		// TODO Auto-generated method stub
		return userBeanDao.findByUserName(username);
	}

}
